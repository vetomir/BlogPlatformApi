package pl.gregorymartin.newsportal.appUser;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.gregorymartin.newsportal.comment.Comment;
import pl.gregorymartin.newsportal.post.Post;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_users")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "username cannot be blank")
    @Email(message = "this is not email address")
    private String username;
    @NotBlank(message = "password cannot be blank")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "nickname cannot be blank")
    private String nickname;

    private String name;
    private String surname;

    private String photoUrl;

    private boolean locked = true;
    private boolean nonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id",  insertable = false)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id",  insertable = false)
    private Set<Post> posts = new HashSet<>();

    public AppUser() {
        role = Role.ROLE_USER;
        photoUrl = "https://cdn.onlinewebfonts.com/svg/img_569204.png";
    }

    AppUser(@NotBlank(message = "username cannot be blank") final String username, @NotBlank(message = "password cannot be blank") final String password, @NotBlank(message = "name cannot be blank") final String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;

        role = Role.ROLE_USER;
        photoUrl = "https://cdn.onlinewebfonts.com/svg/img_569204.png";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(role.getUserRole()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return nonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    void updateProfile(AppUser source) {
        this.nickname = source.nickname;
        this.name = source.name;
        this.surname = source.surname;
    }

    void updateCredentials(AppUser source){
        this.username = source.username;
        this.password = source.password;
    }

    void updatePhoto(AppUser source){
        this.photoUrl = source.photoUrl;
    }

    void toggleRole(){
        if(role == Role.ROLE_USER){
            this.role = Role.ROLE_ADMIN;
        }
        else
            this.role = Role.ROLE_USER;
    }

    void toggleBlock() throws IllegalAccessException {
        if(role == Role.ROLE_ADMIN){
            throw new IllegalAccessException("Admin cannot be blocked");
        }
        locked = !locked;
        nonExpired = !nonExpired;
        credentialsNonExpired = !credentialsNonExpired;
        enabled = !enabled;
    }
}
