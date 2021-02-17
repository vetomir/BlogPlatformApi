package pl.gregorymartin.newsportal.post;

import pl.gregorymartin.newsportal.appUser.AppUserQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostHeader;
import pl.gregorymartin.newsportal.post.dto.PostHint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostHintFactory {
    /*Post Read*/
    public static List<PostHint> toDto(List<Post> post) {
        if (post != null) {
            return post.stream()
                    .map(PostHintFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static PostHint toDto(Post post) {
        return PostHint.builder()
                .id(post.getId())
                .title(post.getTitle())
                .published(post.isPublished())
                .categoryName(post.getCategory().getName())
                .build();
    }
}
