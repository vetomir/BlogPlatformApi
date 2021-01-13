package pl.gregorymartin.newsportal.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class CategoryQueryReadModel {
    private long id;
    private String name;
}
