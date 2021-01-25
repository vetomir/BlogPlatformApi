package pl.gregorymartin.newsportal.post;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.category.Category;
import pl.gregorymartin.newsportal.comment.Comment;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.utils.Audit;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "posts")
public
class Post extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 5, message = "Title sould have more than 5 letters")
    private String title;
    @URL
    private String photoUrl = "https://images.unsplash.com/photo-1586253633232-8161270c5b6e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80";
    private String photoSource = "Unsplash.com";
    @NotBlank
    @Column( length = 400 )
    @Size(min = 50, message = "Lead should have more than 50 symbols")
    @Size(max = 400, message = "Lead should have less than 400 symbols")
    private String lead;

    @NotBlank
    @Column(length = 10000)
    @Size(min = 400, message = "Content should have more than 400 symbols")
    @Size(max = 10000, message = "Content should have less than 10000 symbols")
    private String content;

    private boolean published = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "posts_tags",
            joinColumns = @JoinColumn(
                    name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id",  insertable = false)
    private Set<Comment> comments = new HashSet<>();

    public Post() {

    }

    Post(@NotBlank final String title, @NotBlank final String lead, @NotBlank final String content) {
        this.title = title;
        this.lead = lead;
        this.content = content;
    }

    Post(@NotBlank final String title, @NotBlank @Size(min = 50, message = "Lead should have more than 50 symbols") @Size(max = 400, message = "Lead should have less than 400 symbols") final String lead, @NotBlank @Size(min = 400, message = "Content should have more than 400 symbols") @Size(max = 10000, message = "Content should have less than 10000 symbols") final String content, final AppUser appUser, final Category category, final Set<Tag> tags) {
        this.title = title;
        this.lead = lead;
        this.content = content;
        this.appUser = appUser;
        this.category = category;
        this.tags = tags;
    }
}
