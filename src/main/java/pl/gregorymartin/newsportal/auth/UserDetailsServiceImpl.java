package pl.gregorymartin.newsportal.auth;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository repository;

    public UserDetailsServiceImpl(final AppUserRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(final String s) {
        AppUser user = repository.findAllByUsername(s);
        if (user == null){
            throw new IllegalAccessException("User with this email address is not present");
        }
        if(!(user.isAccountNonExpired()
                && user.isAccountNonLocked()
                && user.isCredentialsNonExpired()
                && user.isEnabled())){
            throw new IllegalAccessException("User account is locked, please check Your email");

        }
        return user;

    }
}
