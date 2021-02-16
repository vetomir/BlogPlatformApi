package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public
class PostHint {
    private long id;
    private String title;
    private String categoryName;
}
