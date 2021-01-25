package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.comment.dto.CommentQueryReadModel;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class AppUserReadModel {
    private long id;
    private String email;

    private String nickname;

    private String name;
    private String surname;
    private String photoUrl;

    private List<PostQueryReadModel> posts;

    private List<CommentQueryReadModel> comments;

}
