package pl.gregorymartin.newsportal.appUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Page<AppUser> findAll(Pageable page);

    UserDetails findAllByUsername(String username);

    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByNickname(String nickname);

    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
