package pl.gregorymartin.newsportal.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class TagReadModel {
    private long id;
    private String name;
    private List<PostQueryReadModel> posts;
}
