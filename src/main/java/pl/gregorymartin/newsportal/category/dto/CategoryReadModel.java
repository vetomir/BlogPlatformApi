package pl.gregorymartin.newsportal.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;

import java.util.List;

@Builder
@Getter
@Setter
class CategoryReadModel {
    private long id;
    private String name;
    private String parent;

    private List<PostQueryReadModel> posts;

}
