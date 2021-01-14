package pl.gregorymartin.newsportal.post;

import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostQueryFactory {
    /*Post Read*/
    public static List<PostQueryReadModel> toDto(List<Post> post) {
        if (post != null) {
            return post.stream()
                    .map(PostQueryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static PostQueryReadModel toDto(Post post) {
        return PostQueryReadModel.builder()
                .id(post.getId())
                .title(post.getTitle())
                .lead(post.getLead())
                .content(post.getContent())
                .author(post.getAuthor().getNickname())
                .photoUrl(post.getPhotoUrl())
                .photoSource(post.getPhotoSource())
                .categoryName(post.getCategory().getName())
                .tags(post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
