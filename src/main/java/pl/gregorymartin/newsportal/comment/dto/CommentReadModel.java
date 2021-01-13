package pl.gregorymartin.newsportal.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;

@Builder
@Getter
@Setter
public
class CommentReadModel {
    private long id;
    private AppUserReadModel author;
    private String content;

    private long parentCommentId;
}
