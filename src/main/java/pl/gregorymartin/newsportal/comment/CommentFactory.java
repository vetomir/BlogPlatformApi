package pl.gregorymartin.newsportal.comment;

import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserQueryFactory;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.appUser.dto.AppUserWriteModel;
import pl.gregorymartin.newsportal.comment.dto.CommentQueryReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentWriteModel;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CommentFactory {
    /*AppUser Write*/
    public static List<Comment> toEntity(List<CommentWriteModel> commentWriteModel) {
        return commentWriteModel.stream()
                .map(CommentFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static Comment toEntity(CommentWriteModel commentWriteModel) {
        Comment comment = new Comment();
        comment.setContent(commentWriteModel.getContent());
        return comment;
    }
    /*AppUser Read*/
    public static List<CommentReadModel> toDto(List<Comment> comments) {
        if(comments != null){
            return comments.stream()
                    .map(CommentFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static CommentReadModel toDto(Comment comments) {
        return CommentReadModel.builder()
                .id(comments.getId())
                .commentedPost(PostQueryFactory.toDto(comments.getPost()))
                .content(comments.getContent())
                .author(AppUserQueryFactory.toDto(comments.getAuthor()))
                .build();
    }
}

