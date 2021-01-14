package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class AppUserQueryReadModel {
    private long id;
    private String username;

    private String nickname;
    private String name;
    private String surname;
}
