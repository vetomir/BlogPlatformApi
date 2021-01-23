package pl.gregorymartin.newsportal.category;

import pl.gregorymartin.newsportal.category.dto.CategoryQueryReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryQueryFactory {
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
