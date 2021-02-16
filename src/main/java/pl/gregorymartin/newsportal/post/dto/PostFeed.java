package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;

@Builder
@Getter
@Setter
public
class PostFeed {
    private long id;
    private String title;
    private String photoUrl;
    private AppUserReadModel author;
    private String categoryName;
}
