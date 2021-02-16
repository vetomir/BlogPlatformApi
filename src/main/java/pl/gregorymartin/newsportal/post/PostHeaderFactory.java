package pl.gregorymartin.newsportal.post;

import pl.gregorymartin.newsportal.appUser.AppUserQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostHeader;
import pl.gregorymartin.newsportal.post.dto.PostQueryReadModel;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostHeaderFactory {
    /*Post Read*/
    public static List<PostHeader> toDto(List<Post> post) {
        if (post != null) {
            return post.stream()
                    .map(PostHeaderFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static PostHeader toDto(Post post) {
        return PostHeader.builder()
                .id(post.getId())
                .title(post.getTitle())
                .lead(post.getLead())
                .author(post.getAppUser().getNickname())
                .photoUrl(post.getPhotoUrl())
                .categoryName(post.getCategory().getName())
                .build();
    }
}
