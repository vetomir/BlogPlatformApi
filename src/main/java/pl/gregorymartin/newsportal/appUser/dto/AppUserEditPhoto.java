package pl.gregorymartin.newsportal.appUser.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public
class AppUserEditPhoto {
    @NotBlank(message = "photo url cannot be blank")
    @URL(message = "this is not url path")
    private String photoUrl;
}
