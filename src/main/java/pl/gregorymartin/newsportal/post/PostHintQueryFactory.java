package pl.gregorymartin.newsportal.post;

import pl.gregorymartin.newsportal.appUser.AppUserQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostHint;
import pl.gregorymartin.newsportal.post.dto.PostHintQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostHintQueryFactory {
    /*Post Read*/
    public static List<PostHintQuery> toDto(List<Post> post) {
        if (post != null) {
            return post.stream()
                    .map(PostHintQueryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static PostHintQuery toDto(Post post) {
        return PostHintQuery.builder()
                .id(post.getId())
                .title(post.getTitle())
                .lead(post.getLead())
                .photoUrl(post.getPhotoUrl())
                .createdOn(post.formatCreatedOn())
                .author(AppUserQueryFactory.toDto(post.getAppUser()))
                .categoryName(post.getCategory().getName())
                .build();
    }
}
