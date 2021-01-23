package pl.gregorymartin.newsportal.tag;

import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostFactory;
import pl.gregorymartin.newsportal.post.PostQueryFactory;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.dto.TagReadModel;
import pl.gregorymartin.newsportal.tag.dto.TagWriteModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagFactory {

    /*Tag Write*/
    public static List<Tag> toEntity(List<TagWriteModel> postWriteModel) {
        return postWriteModel.stream()
                .map(TagFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Tag toEntity(TagWriteModel postWriteModel) {
        Tag tag = new Tag();
        tag.setName(postWriteModel.getName());
        return tag;
    }

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
                .posts(PostQueryFactory.toDto(new ArrayList<>(tag.getPosts())))
                .build();
    }
}

