package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public
class AppUserEditProfile {
    @NotBlank(message = "nickname cannot be blank")
    private String nickname;
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "name cannot be blank")
    private String surname;
}
