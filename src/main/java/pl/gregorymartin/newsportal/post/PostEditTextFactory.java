package pl.gregorymartin.newsportal.post;


import pl.gregorymartin.newsportal.appUser.AppUserQueryFactory;
import pl.gregorymartin.newsportal.category.CategoryQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostEditText;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostEditTextFactory {

    /*Post Write*/
    public static List<Post> toEntity(List<PostEditText> postEditTexts) {
        return postEditTexts.stream()
                .map(PostEditTextFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Post toEntity(PostEditText postEditTexts) {
        Post post = new Post();
        post.setTitle(postEditTexts.getTitle());
        post.setLead(postEditTexts.getLead());
        post.setContent(postEditTexts.getContent());
        return post;
    }
}
