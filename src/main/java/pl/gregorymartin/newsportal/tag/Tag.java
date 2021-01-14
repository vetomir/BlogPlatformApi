package pl.gregorymartin.newsportal.tag;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.Post;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tags")
public
class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany
    private Set<Post> posts;

    public Tag() {
    }

    public Tag(final String name) {
        this.name = name;
    }
}
