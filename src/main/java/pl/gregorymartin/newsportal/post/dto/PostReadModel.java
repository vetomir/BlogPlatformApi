package pl.gregorymartin.newsportal.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.gregorymartin.newsportal.appUser.dto.AppUserQueryReadModel;
import pl.gregorymartin.newsportal.category.dto.CategoryQueryReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentQueryReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.tag.dto.TagQueryReadModel;

import java.util.List;

@Builder
@Getter
@Setter
public
class PostReadModel {
    private long id;
    private String title;
    private String lead;
    private String content;

    private String photoUrl;
    private String photoSource;

    private boolean published;
    private String createdOn;

    private AppUserQueryReadModel author;

    private CategoryQueryReadModel category;

    private List<TagQueryReadModel> tags;

    private List<CommentQueryReadModel> comments;
}
