package pl.gregorymartin.newsportal.appUser;

import pl.gregorymartin.newsportal.appUser.dto.AppUserEditPhoto;
import pl.gregorymartin.newsportal.appUser.dto.AppUserEditProfile;

import java.util.List;
import java.util.stream.Collectors;

class AppUserEditPhotoFactory {
    /*AppUser Write*/
    public static List<AppUser> toEntity(List<AppUserEditPhoto> appUserEditPhotos) {
        return appUserEditPhotos.stream()
                .map(AppUserEditPhotoFactory::toEntity)
                .collect(Collectors.toList());
    }

    public static AppUser toEntity(AppUserEditPhoto appUserEditPhotos) {
        AppUser appUser = new AppUser();
        appUser.setPhotoUrl(appUserEditPhotos.getPhotoUrl());
        return appUser;
    }
}
