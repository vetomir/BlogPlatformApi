package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public
class AppUserEditCredentials {
    @NotBlank(message = "username cannot be blank")
    @Email(message = "this is not email address")
    private String username;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "password cannot be blank")
    private String passwordRepeat;
}
