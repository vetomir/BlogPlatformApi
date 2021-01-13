package pl.gregorymartin.newsportal.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class CategoryService {
    private final CategoryRepository categoryRepository;

    CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<Category> showAllCategories(){
        return categoryRepository.findAll();
    }

    Category addCategory(Category source){
        return categoryRepository.save(source);
    }

    @Transactional
    Category editCategory(Category source){
        Optional<Category> category = categoryRepository.findById(source.getId());
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category is not exist");
        }
        category.get().setName(source.getName());
        category.get().setParentCategoryId(source.getParentCategoryId());
        return category.get();
    }

    boolean deleteCategory(long id){
        if(!categoryRepository.existsById(id)){
            throw new IllegalArgumentException("Category is not exist");
        }
        categoryRepository.deleteById(id);
        return true;
    }


}
