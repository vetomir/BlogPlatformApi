package pl.gregorymartin.newsportal.appUser;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public
class AppUserService {
    private static final int PAGE_SIZE = 28;

    private final AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    AppUserService(final AppUserRepository appUserRepository, final PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

    public List<AppUser> getUsers(int page, Sort.Direction sort, String sortBy) {

        return appUserRepository.findAll(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    public AppUser getSingleAppUser(long appUserId){
        Optional<AppUser> appUser = appUserRepository.findById(appUserId);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("User (ID:" + appUserId + ") is not Exist");
        }
        return appUser.get();
    }
    AppUser getSingleAppUser(String username){
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("Email: " + username + " is not Exist");
        }
        return appUser.get();
    }

    AppUser getSingleAppUserByNickname(String nickname){
        Optional<AppUser> appUser = appUserRepository.findByNickname(nickname);
        if(appUser.isEmpty()){
            throw new IllegalArgumentException("Nickname: " + nickname + " is not Exist");
        }
        return appUser.get();
    }

    AppUser addAppUser(AppUser source){
        if(appUserRepository.existsByUsername(source.getUsername())){
            throw new IllegalArgumentException("Username: " + source.getUsername() + " is already in use");
        }
        if(appUserRepository.existsByNickname(source.getNickname())){
            throw new IllegalArgumentException("Nickname: " + source.getNickname() + " is already in use");
        }

        source.setPassword(passwordEncoder.encode(source.getPassword()));

        return appUserRepository.save(source);
    }

    @Transactional
    AppUser editAppUserProfile(AppUser source){
        AppUser appUser = getSingleAppUser(source.getId());
        appUser.updateProfile(source);
        return appUserRepository.save(appUser);
    }

    @Transactional
    AppUser editAppUserCredentials(AppUser source){
        AppUser appUser = getSingleAppUser(source.getId());
        source.setPassword(passwordEncoder.encode(source.getPassword()));
        appUser.updateCredentials(source);
        return appUserRepository.save(appUser);
    }

    @Transactional
    AppUser editAppUserPhoto(AppUser source){
        AppUser appUser = getSingleAppUser(source.getId());
        appUser.updatePhoto(source);
        return appUserRepository.save(appUser);

    }

    @Transactional
    AppUser toggleAdmin(long userId){
        AppUser appUser = getSingleAppUser(userId);
        appUser.toggleRole();
        return appUserRepository.save(appUser);

    }

    @Transactional
    AppUser toggleBlock(long userId) throws IllegalAccessException {
        AppUser appUser = getSingleAppUser(userId);
        appUser.toggleBlock();
        return appUserRepository.save(appUser);

    }

    boolean deleteAppUser(long userId){
        AppUser appUser = getSingleAppUser(userId);
        appUserRepository.delete(appUser);
        return true;
    }
}
