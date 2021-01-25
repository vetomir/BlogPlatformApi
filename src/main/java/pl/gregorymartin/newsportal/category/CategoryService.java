package pl.gregorymartin.newsportal.category;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public
class CategoryService {
    private static final int PAGE_SIZE = 28;
    private final CategoryRepository categoryRepository;

    CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public List<Category> getCategories(int page, Sort.Direction sort, String sortBy) {

        return categoryRepository.findAll(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    public Category getSingleCategory(long categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category (ID:" + categoryId + ") is not exist");
        }
        return category.get();
    }

    Category addCategory(Category source){
        if(source.getParentCategoryId() != 0){
            Category parentCategory = getSingleCategory(source.getParentCategoryId());
            source.setParentCategoryId(parentCategory.getId());
        }
        return categoryRepository.save(source);
    }

    @Transactional
    Category editCategory(Category source) throws IllegalAccessException {
        Category category = getSingleCategory(source.getId());
        if(category.isFixed()){
            throw new IllegalAccessException("Category '" + category.getName() + "' (ID: " + category.getId() + ") is fixed, cannot be edited");
        }
        category.setName(source.getName());
        if(source.getParentCategoryId() != 0){
            category.setParentCategoryId(source.getParentCategoryId());
        }
        return categoryRepository.save(category);
    }

    boolean deleteCategory(long id) throws IllegalAccessException {
        Category category = getSingleCategory(id);
        if(category.isFixed()){
            throw new IllegalAccessException("Category '" + category.getName() + "' (ID: " + category.getId() + ") is fixed, cannot be removed");
        }
        categoryRepository.delete(category);
        return true;
    }
}
