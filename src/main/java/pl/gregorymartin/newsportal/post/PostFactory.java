package pl.gregorymartin.newsportal.post;


import pl.gregorymartin.newsportal.appUser.AppUserQueryFactory;
import pl.gregorymartin.newsportal.category.CategoryQueryFactory;
import pl.gregorymartin.newsportal.comment.CommentQueryFactory;
import pl.gregorymartin.newsportal.comment.dto.CommentQueryReadModel;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagQueryFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PostFactory {

    /*Post Write*/
    public static List<Post> toEntity(List<PostWriteModel> postWriteModel) {
        return postWriteModel.stream()
                .map(PostFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Post toEntity(PostWriteModel postWriteModel) {
        Post post = new Post();
        post.setTitle(postWriteModel.getTitle());
        post.setLead(postWriteModel.getLead());
        post.setContent(postWriteModel.getContent());
        post.setPhotoUrl(postWriteModel.getPhotoUrl());
        post.setPhotoSource(postWriteModel.getPhotoSource());
        if (postWriteModel.getTags() != null) {
            post.setTags(
                    postWriteModel.getTags().stream()
                            .filter(x -> !x.isBlank())
                            .distinct()
                            .map(Tag::new)
                            .collect(Collectors.toSet()));
        }
        return post;
    }

    /*Post Read*/
    public static List<PostReadModel> toDto(List<Post> post) {
        if (post != null) {
            return post.stream()
                    .map(PostFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static PostReadModel toDto(Post post) {
        List<CommentQueryReadModel> comments = CommentQueryFactory.toDto(new ArrayList<>(post.getComments()));
        comments = comments.stream().sorted(Comparator.comparing(CommentQueryReadModel::getId).reversed()).collect(Collectors.toList());

        return PostReadModel.builder()
                .id(post.getId())
                .title(post.getTitle())
                .lead(post.getLead())
                .content(post.getContent())
                .createdOn(post.formatCreatedOn())
                .author(AppUserQueryFactory.toDto(post.getAppUser()))
                .photoUrl(post.getPhotoUrl())
                .photoSource(post.getPhotoSource())
                .published(post.isPublished())
                .category(CategoryQueryFactory.toDto(post.getCategory()))
                .tags(TagQueryFactory.toDto(new ArrayList<>( post.getTags() )))
                .comments(comments)
                .build();
    }
}
