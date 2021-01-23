package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public
class AppUserWriteModel {
    @NotBlank(message = "username cannot be blank")
    private String username;
    @NotBlank(message = "name cannot be blank")
    private String nickname;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "password cannot be blank")
    private String password2;

    private String name;
    private String surname;
}
