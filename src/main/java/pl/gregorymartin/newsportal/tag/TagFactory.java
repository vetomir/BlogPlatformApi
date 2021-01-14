package pl.gregorymartin.newsportal.tag;

import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostFactory;
import pl.gregorymartin.newsportal.post.PostQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.dto.TagQueryReadModel;
import pl.gregorymartin.newsportal.tag.dto.TagReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class TagFactory {

    /*Tag Read*/
    public static List<TagReadModel> toDto(List<Tag> tag) {
        if(tag != null){
            return tag.stream()
                    .map(TagFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static TagReadModel toDto(Tag tag) {
        return TagReadModel.builder()
                .id(tag.getId())
                .name(tag.getName())
                .posts(PostQueryFactory.toDto((List<Post>) tag.getPosts()))
                .build();
    }
}

class TagQueryFactory {

    /*Tag Read*/
    public static List<TagQueryReadModel> toDto(List<Tag> tag) {
        if(tag != null){
            return tag.stream()
                    .map(TagQueryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static TagQueryReadModel toDto(Tag tag) {
        return TagQueryReadModel.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }
}
