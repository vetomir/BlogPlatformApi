package pl.gregorymartin.newsportal.category;

import pl.gregorymartin.newsportal.category.dto.CategoryQueryReadModel;
import pl.gregorymartin.newsportal.category.dto.CategoryReadModel;
import pl.gregorymartin.newsportal.category.dto.CategoryWriteModel;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostFactory;
import pl.gregorymartin.newsportal.post.PostQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CategoryFactory {
    /*Category Write*/
    public static List<Category> toEntity(List<CategoryWriteModel> categoryWriteModel) {
        return categoryWriteModel.stream()
                .map(CategoryFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Category toEntity(CategoryWriteModel categoryWriteModel) {
        Category category = new Category();
        category.setParentCategoryId(categoryWriteModel.getCategoryParentId());
        category.setName(categoryWriteModel.getName());

        return category;
    }
    /*Category Read*/
    public static List<CategoryReadModel> toDto(List<Category> category) {
        if(category != null){
            return category.stream()
                    .map(CategoryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static CategoryReadModel toDto(Category category) {
        return CategoryReadModel.builder()
                .id(category.getId())
                .name(category.getName())
                .posts(PostQueryFactory.toDto((List<Post>) category.getPosts()))
                .build();
    }
}
class CategoryQueryFactory {
    /*Category Query Read*/
    public static List<CategoryQueryReadModel> toDto(List<Category> category) {
        if(category != null){
            return category.stream()
                    .map(CategoryQueryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static CategoryQueryReadModel toDto(Category category) {
        return CategoryQueryReadModel.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
