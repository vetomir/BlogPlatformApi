package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public
class PostWriteModel {
    private String title;
    private String lead;
    private String content;

    private long categoryId;

    private String photoUrl;
    private String photoSource;
    private List<String> tags;
}
