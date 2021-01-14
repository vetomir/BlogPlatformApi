package pl.gregorymartin.newsportal.category.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryWriteModel {
    private String name;
    private long categoryParentId;
}
