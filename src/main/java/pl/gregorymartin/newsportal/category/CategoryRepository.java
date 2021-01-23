package pl.gregorymartin.newsportal.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.gregorymartin.newsportal.comment.Comment;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAll(Pageable page);

    @Query("Select c.posts From Category c where c.id = ?1 order by c.id desc ")
    Page<Post> getPostByCategory(long postId, Pageable page);
}
