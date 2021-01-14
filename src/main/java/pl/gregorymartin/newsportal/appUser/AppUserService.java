package pl.gregorymartin.newsportal.appUser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public
class AppUserService {
    private final AppUserRepository appUserRepository;

    AppUserService(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    public AppUser getSingleAppUser(long appUserId){
        Optional<AppUser> appUser = appUserRepository.findById(appUserId);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User Is not Exist");
        }
        return appUser.get();
    }
    AppUser getSingleAppUser(String username){
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("Email: " + username + " is already in use");
        }
        return appUser.get();
    }

    AppUser getSingleAppUserByNickname(String nickname){
        Optional<AppUser> appUser = appUserRepository.findByNickname(nickname);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("Nickname: " + nickname + " is already in use");
        }
        return appUser.get();
    }

    AppUser addAppUser(AppUser source){
        getSingleAppUser(source.getUsername());
        getSingleAppUserByNickname(source.getNickname());

        return appUserRepository.save(source);
    }

    @Transactional
    AppUser editAppUserProfile(AppUser source){
        AppUser appUser = getSingleAppUser(source.getId());
        appUser.updateProfile(source);
        return appUser;
    }

    @Transactional
    AppUser editAppUserCredentials(AppUser source){
        AppUser appUser = getSingleAppUser(source.getId());
        appUser.updateCredentials(source);
        return appUser;
    }

    @Transactional
    AppUser editAppUserPhoto(AppUser source){
        AppUser appUser = getSingleAppUser(source.getId());
        appUser.setPhotoUrl(source.getPhotoUrl());
        return appUser;

    }

    boolean deleteAppUser(long id){
        AppUser appUser = getSingleAppUser(id);
        appUserRepository.delete(appUser);
        return true;
    }
}
