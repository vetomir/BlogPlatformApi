package pl.gregorymartin.newsportal.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public
class CommentQueryReadModel {
    private long id;
    private String authorNickname;
    private String authorAvatar;
    private String createdOn;
    private String content;
}
