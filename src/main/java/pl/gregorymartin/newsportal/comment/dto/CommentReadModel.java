package pl.gregorymartin.newsportal.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserQueryReadModel;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

@Builder
@Getter
@Setter
public
class CommentReadModel {
    private long id;
    private AppUserQueryReadModel author;
    private String createdOn;
    private String content;
    private CommentQueryReadModel parentComment;

    private PostQueryReadModel commentedPost;
}
