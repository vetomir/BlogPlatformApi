package pl.gregorymartin.newsportal.appUser;

import pl.gregorymartin.newsportal.appUser.dto.AppUserQueryReadModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserQueryFactory {

    /*AppUser Read*/
    public static List<AppUserQueryReadModel> toDto(List<AppUser> appUser) {
        if (appUser != null) {
            return appUser.stream()
                    .map(AppUserQueryFactory::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static AppUserQueryReadModel toDto(AppUser appUser) {
        return AppUserQueryReadModel.builder()
                .id(appUser.getId())
                .nickname(appUser.getNickname())
                .name(appUser.getName())
                .surname(appUser.getSurname())
                .build();
    }
}
