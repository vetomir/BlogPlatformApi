package pl.gregorymartin.newsportal.comment;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.utils.Audit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", updatable = false)
    private Post post;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUser appUser;

    @NotBlank(message = "Content cannot be blank")
    @Size(min = 3, message = "Content should have more than 3 symbols")
    @Size(max = 400, message = "Content should have less than 400 symbols")
    private String content;

    private long parentCommentId;

    public Comment() {
    }

    Comment(@NotBlank(message = "Content cannot be blank") final String content, final long parentCommentId) {
        this.content = content;
        this.parentCommentId = parentCommentId;
    }
}
