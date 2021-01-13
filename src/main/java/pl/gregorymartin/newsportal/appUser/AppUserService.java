package pl.gregorymartin.newsportal.appUser;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
class AppUserService {
    private final AppUserRepository appUserRepository;

    AppUserService(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    List<AppUser> getAllUsers(){
        return appUserRepository.findAll();
    }

    AppUser addAppUser(AppUser source){
        if(appUserRepository.existsByNickname(source.getNickname())){
            throw new IllegalArgumentException("User with nickname:" + source.getNickname() + " is already exists.");
        }
        if(appUserRepository.existsByUsername(source.getUsername())){
            throw new IllegalArgumentException("Email:" + source.getUsername() + " is already in use.");
        }
        return appUserRepository.save(source);
    }

    @Transactional
    AppUser editAppUserProfile(AppUser source){
        Optional<AppUser> appUser = appUserRepository.findById(source.getId());
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }
        appUser.get().updateProfile(source);
        return appUser.get();
    }

    @Transactional
    AppUser editAppUserCredentials(AppUser source){
        Optional<AppUser> appUser = appUserRepository.findById(source.getId());
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }
        appUser.get().updateCredentials(source);
        return appUser.get();
    }

    @Transactional
    AppUser editAppUserPhoto(AppUser source){
        Optional<AppUser> appUser = appUserRepository.findById(source.getId());
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }
        appUser.get().setPhotoUrl(source.getPhotoUrl());
        return appUser.get();

    }

    boolean deleteAppUser(long id){
        if(!appUserRepository.existsById(id)){
            throw new IllegalArgumentException("User is not present");
        }
        appUserRepository.deleteById(id);
        return true;
    }
}
