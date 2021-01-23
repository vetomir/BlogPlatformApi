package pl.gregorymartin.newsportal.category;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Category Name cannot be blank")
    private String name;

    private long parentCategoryId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",  insertable = false)
    private Set<Post> posts = new HashSet<>();

    public Category() {
    }

    Category(@NotBlank(message = "Category Name cannot be blank") final String name, final long parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }
}
