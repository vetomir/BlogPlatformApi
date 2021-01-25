package pl.gregorymartin.newsportal.appUser;

import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.appUser.dto.AppUserWriteModel;
import pl.gregorymartin.newsportal.comment.Comment;
import pl.gregorymartin.newsportal.comment.CommentQueryFactory;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AppUserFactory {
    /*AppUser Write*/
    public static List<AppUser> toEntity(List<AppUserWriteModel> appUserWriteModel) {
        return appUserWriteModel.stream()
                .map(AppUserFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static AppUser toEntity(AppUserWriteModel appUserWriteModel) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserWriteModel.getUsername());
        appUser.setPassword(appUserWriteModel.getPassword());
        appUser.setNickname(appUserWriteModel.getNickname());
        appUser.setName(appUserWriteModel.getName());
        appUser.setSurname(appUserWriteModel.getSurname());
        return appUser;
    }
    /*AppUser Read*/
    public static List<AppUserReadModel> toDto(List<AppUser> appUser) {
        if(appUser != null){
            return appUser.stream()
                    .map(AppUserFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static AppUserReadModel toDto(AppUser appUser) {
        return AppUserReadModel.builder()
                .id(appUser.getId())
                .email(appUser.getUsername())
                .nickname(appUser.getNickname())
                .name(appUser.getName())
                .surname(appUser.getSurname())
                .photoUrl(appUser.getPhotoUrl())
                .comments(CommentQueryFactory.toDto(new ArrayList<>(appUser.getComments())))
                .posts(PostQueryFactory.toDto(new ArrayList<>(appUser.getPosts())))
                .build();
    }
}
