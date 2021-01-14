package pl.gregorymartin.newsportal.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

@Builder
@Getter
@Setter
public
class CommentQueryReadModel {
    private long id;
    private String authorName;
    private String content;
}
