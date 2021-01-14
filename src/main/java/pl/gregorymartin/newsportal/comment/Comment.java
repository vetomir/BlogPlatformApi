package pl.gregorymartin.newsportal.comment;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.utils.Audit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne
    private Post post;

    @NotNull
    @ManyToOne
    private AppUser author;

    @NotBlank(message = "Content cannot be blank")
    private String content;

    private long parentCommentId;

    public Comment() {
    }
}
