package pl.gregorymartin.newsportal.appUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAllByUsername(String username);
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByNickname(String nickname);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
