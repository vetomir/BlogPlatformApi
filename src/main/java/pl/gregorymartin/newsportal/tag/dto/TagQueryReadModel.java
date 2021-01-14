package pl.gregorymartin.newsportal.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class TagQueryReadModel {
    private long id;
    private String name;
}
