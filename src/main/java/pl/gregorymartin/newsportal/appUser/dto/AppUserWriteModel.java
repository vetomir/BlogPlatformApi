package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

import java.util.List;

@Getter
@Setter
public
class AppUserWriteModel {
    private String username;
    private String nickname;
    private String name;
    private String surname;
    private String password;
    private String password2;
}
