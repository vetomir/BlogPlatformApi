package pl.gregorymartin.newsportal.appUser;

import pl.gregorymartin.newsportal.appUser.dto.AppUserEditCredentials;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.appUser.dto.AppUserWriteModel;
import pl.gregorymartin.newsportal.comment.CommentQueryFactory;
import pl.gregorymartin.newsportal.post.PostQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AppUserEditCredentialsFactory {
    /*AppUser Write*/
    public static List<AppUser> toEntity(List<AppUserEditCredentials> appUserWriteModel) {
        return appUserWriteModel.stream()
                .map(AppUserEditCredentialsFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static AppUser toEntity(AppUserEditCredentials appUserWriteModel) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserWriteModel.getUsername());
        appUser.setPassword(appUserWriteModel.getPassword());
        return appUser;
    }
}
