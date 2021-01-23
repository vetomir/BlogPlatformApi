package pl.gregorymartin.newsportal.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public
class CommentWriteModel {
    @NotBlank(message = "content cannot be blank")
    private String content;

    private long parentId;
}
