package pl.gregorymartin.newsportal.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public
class CategoryQueryReadModel {
    private long id;
    private String name;
}
