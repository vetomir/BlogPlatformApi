package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserQueryReadModel;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;

@Builder
@Getter
@Setter
public
class PostHeader {
    private long id;
    private String title;
    private String lead;
    private boolean published;
    private String photoUrl;
    private String author;
    private String categoryName;
}
