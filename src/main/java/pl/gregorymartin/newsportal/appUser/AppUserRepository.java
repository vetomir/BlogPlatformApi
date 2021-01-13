package pl.gregorymartin.newsportal.appUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAllByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
