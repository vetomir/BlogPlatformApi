package pl.gregorymartin.newsportal.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.newsportal.post.Post;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("Select t From Tag t where t.name = ?1")
    Optional<Tag> findByName(String name);
    Optional<Tag> getByName(String name);
    Page<Tag> findAll(Pageable page);


    @Transactional
    @Modifying
    @Query("delete from Tag t where t.posts is empty")
    void deleteUnusedTags();
}
