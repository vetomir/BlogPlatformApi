package pl.gregorymartin.newsportal.category;

import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @OneToMany
    private List<Post> posts;

    public Category() {
    }

    Category(@NotBlank(message = "Category Name cannot be blank") final String name, final long parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }
}
