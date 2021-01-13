package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.category.dto.CategoryQueryReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.tag.dto.TagReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class PostSingle {
    private long id;
    private String title;
    private String lead;
    private String content;

    private String photoUrl;
    private String photoSource;

    private AppUserReadModel author;

    private CategoryQueryReadModel category;

    private List<TagReadModel> tags;

    private List<CommentReadModel> comments;
}
