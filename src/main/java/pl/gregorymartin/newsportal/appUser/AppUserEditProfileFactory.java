package pl.gregorymartin.newsportal.appUser;

import pl.gregorymartin.newsportal.appUser.dto.AppUserEditProfile;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.appUser.dto.AppUserWriteModel;
import pl.gregorymartin.newsportal.comment.CommentQueryFactory;
import pl.gregorymartin.newsportal.post.PostQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class AppUserEditProfileFactory {
    /*AppUser Write*/
    public static List<AppUser> toEntity(List<AppUserEditProfile> appUserWriteModel) {
        return appUserWriteModel.stream()
                .map(AppUserEditProfileFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static AppUser toEntity(AppUserEditProfile appUserWriteModel) {
        AppUser appUser = new AppUser();
        appUser.setNickname(appUserWriteModel.getNickname());
        appUser.setName(appUserWriteModel.getName());
        appUser.setSurname(appUserWriteModel.getSurname());
        return appUser;
    }
}
