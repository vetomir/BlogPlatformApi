package pl.gregorymartin.newsportal.category;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public
class CategoryService {
    private final CategoryRepository categoryRepository;

    CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getSingleCategory(long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category is not exist");
        }
        return category.get();
    }

    Category addCategory(Category source){
        return categoryRepository.save(source);
    }

    @Transactional
    Category editCategory(Category source){
        Category category = getSingleCategory(source.getId());

        category.setName(source.getName());
        category.setParentCategoryId(source.getParentCategoryId());
        return category;
    }

    boolean deleteCategory(long id){
        Category category = getSingleCategory(id);
        categoryRepository.delete(category);
        return true;
    }
}
