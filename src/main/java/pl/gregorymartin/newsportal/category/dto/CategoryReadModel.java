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
class CategoryReadModel {
    private long id;
    private String name;
    private String parent;

    private List<PostQueryReadModel> posts;

}
