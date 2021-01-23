package pl.gregorymartin.newsportal.tag;

import pl.gregorymartin.newsportal.tag.dto.TagQueryReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TagQueryFactory {

    /*Tag Read*/
    public static List<TagQueryReadModel> toDto(List<Tag> tag) {
        if (tag != null) {
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
