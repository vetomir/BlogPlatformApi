package pl.gregorymartin.newsportal.comment;

import pl.gregorymartin.newsportal.comment.dto.CommentQueryReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentQueryFactory {
    /*AppUser Read*/
    public static List<CommentQueryReadModel> toDto(List<Comment> comments) {
        if (comments != null) {
            return comments.stream()
                    .map(CommentQueryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static CommentQueryReadModel toDto(Comment comments) {
        return CommentQueryReadModel.builder()
                .id(comments.getId())
                .authorName(comments.getAppUser().getName())
                .createdOn(comments.formatCreatedOn())
                .content(comments.getContent())
                .build();
    }
}
