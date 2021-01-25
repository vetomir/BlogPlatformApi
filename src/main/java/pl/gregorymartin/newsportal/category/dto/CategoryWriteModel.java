package pl.gregorymartin.newsportal.category.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryWriteModel {
    private long id;
    @NotBlank(message = "category name cannot be blank")
    private String name;
    private long parent = 0;
}
