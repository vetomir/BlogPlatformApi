package pl.gregorymartin.newsportal.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.gregorymartin.newsportal.category.Category;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAll(Pageable page);

    @Query("Select c From Comment c where c.post.id = ?1 order by c.id desc ")
    Page<Comment> findAllByPost(long postId, Pageable page);

    @Query("Select c From Comment c where c.appUser.id = ?1 order by c.id desc ")
    Page<Comment> findAllByAppUser(long postId, Pageable page);

    @Query("Select c From Comment c where c.parentCommentId = ?1 order by c.id desc ")
    Page<Comment> findAllByParent(long postId, Pageable page);
}
