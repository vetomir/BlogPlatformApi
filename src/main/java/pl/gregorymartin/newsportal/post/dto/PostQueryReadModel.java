package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public
class PostQueryReadModel {
    private long id;
    private String title;
    private boolean published;
    private String lead;
    private String photoUrl;
    private String author;
    private String categoryName;
    private String createdOn;
    private List<String> tags;
}
