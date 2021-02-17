package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public
class PostWriteModel {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 5, message = "Title sould have more than 5 letters")
    private String title;

    @NotBlank(message = "Lead cannot be blank")
    @Column( length = 400 )
    @Size(min = 50, message = "Lead should have more than 50 symbols")
    @Size(max = 400, message = "Lead should have less than 400 symbols")
    private String lead;

    @NotBlank(message = "Content cannot be blank")
    @Column(length = 10000)
    @Size(min = 400, message = "Content should have more than 400 symbols")
    @Size(max = 10000, message = "Content should have less than 10000 symbols")
    private String content;

    private long categoryId;

    @URL
    @NotNull
    private String photoUrl;
    private String photoSource;
    private List<String> tags;
}
