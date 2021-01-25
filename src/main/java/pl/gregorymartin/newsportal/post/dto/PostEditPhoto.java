package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class PostEditPhoto {
    private String photoUrl;
    private String photoSource;
}
