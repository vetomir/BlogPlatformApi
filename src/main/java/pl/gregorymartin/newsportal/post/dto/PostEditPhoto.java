package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public
class PostEditPhoto {
    @URL
    @NotNull
    private String photoUrl;
    private String photoSource;
}
