package pl.gregorymartin.newsportal.post;


import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.ArrayList;
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
        return PostReadModel.builder()
                .id(post.getId())
                .title(post.getTitle())
                .lead(post.getLead())
                .content(post.getContent())
                //.author(post)
                .photoUrl(post.getPhotoUrl())
                .photoSource(post.getPhotoSource())
                //.category(pos)
                /*.tags(post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))*/
                .build();
    }
}
