package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.tag.dto.TagReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class PostQueryReadModel {
    private long id;
    private String title;
    private String lead;
    private String content;

    private String photoUrl;
    private String photoSource;

    private long authorId;

    private String categoryName;

    private List<String> tags;
}
