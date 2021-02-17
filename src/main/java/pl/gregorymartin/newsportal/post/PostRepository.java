package pl.gregorymartin.newsportal.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.gregorymartin.newsportal.comment.Comment;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    @Query("Select p From Post p where p.published = true")
    Page<Post> findAllPublished(Pageable page);


    @Query("Select p From Post p where p.content like %?1%")
    Page<Post> findAllByContainedQuery(String query, Pageable pageable);

    @Query("Select count (p) From Post p where p.content like %?1%")
    long getSizeOfAllByContainedQuery(String query);
}
