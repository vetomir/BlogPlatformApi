package pl.gregorymartin.newsportal.post;


import pl.gregorymartin.newsportal.post.dto.PostEditPhoto;
import pl.gregorymartin.newsportal.post.dto.PostEditText;

import java.util.List;
import java.util.stream.Collectors;

public class PostPhotoFactory {

    /*Post Write*/
    public static List<Post> toEntity(List<PostEditPhoto> postEditPhoto) {
        return postEditPhoto.stream()
                .map(PostPhotoFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Post toEntity(PostEditPhoto postEditPhoto) {
        Post post = new Post();
        post.setPhotoUrl(postEditPhoto.getPhotoUrl());
        post.setPhotoSource(postEditPhoto.getPhotoSource());
        return post;
    }
}
