package pl.gregorymartin.newsportal.appUser;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.dto.*;

import javax.persistence.Access;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
class AppUserRestController {
    private final AppUserService service;

    AppUserRestController(final AppUserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppUserReadModel>> readAll(
            @RequestParam(required = false) Integer page,
            Sort.Direction sort,
            String sortBy
            ) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<AppUser> appUsers = service.getUsers(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(AppUserFactory.toDto(appUsers));
    }

    @GetMapping("/me")
    public ResponseEntity<AppUserReadModel> readLoggedUser(Authentication authentication) throws IllegalAccessException {
        if(authentication == null){
            throw new IllegalAccessException("You must to logged in first");
        }
        AppUser authUser = (AppUser) authentication.getPrincipal();

        AppUser appUser = service.getSingleAppUser(authUser.getId());
        return ResponseEntity.ok(AppUserFactory.toDto(appUser));
    }

    @GetMapping
    public ResponseEntity<AppUserReadModel> readSingle(@RequestParam(name = "id") int userId) {
        AppUser appUser = service.getSingleAppUser(userId);
        return ResponseEntity.ok(AppUserFactory.toDto(appUser));
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<AppUserReadModel> readSingle(@PathVariable(name = "nickname") String userNickname) {
        AppUser appUser = service.getSingleAppUserByNickname(userNickname);
        return ResponseEntity.ok(AppUserFactory.toDto(appUser));
    }

    @PostMapping
    public ResponseEntity<AppUserReadModel> create(@RequestBody AppUserWriteModel source/*, @RequestParam(name = "user-id") long userId*/) {
        if(!source.getPassword().equals(source.getPasswordRepeat())){
            throw new IllegalArgumentException("passwords are not the same");
        }
        AppUser result = service.addAppUser(AppUserFactory.toEntity(source));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }

    @PatchMapping("/{id}/credentials")
    public ResponseEntity<AppUserQueryReadModel> updateCredentials(@PathVariable(name = "id") int userId, @RequestBody @Valid AppUserEditCredentials source) {
        if(!source.getPassword().equals(source.getPasswordRepeat())){
            throw new IllegalArgumentException("passwords are not the same");
        }
        System.out.println(source);
        AppUser appUser = AppUserEditCredentialsFactory.toEntity(source);
        appUser.setId(userId);
        AppUser result = service.editAppUserCredentials(appUser);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserQueryFactory.toDto(result));
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<AppUserReadModel> updateProfile(@PathVariable(name = "id") int userId, @RequestBody @Valid AppUserEditProfile source) {
        AppUser appUser = AppUserEditProfileFactory.toEntity(source);
        appUser.setId(userId);
        AppUser result = service.editAppUserProfile(appUser);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }

    @PatchMapping("/{id}/photo")
    public ResponseEntity<AppUserReadModel> updatePhoto(@PathVariable(name = "id") int userId, @RequestBody AppUserEditPhoto source) {
        AppUser appUser = AppUserEditPhotoFactory.toEntity(source);
        appUser.setId(userId);
        AppUser result = service.editAppUserPhoto(appUser);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }
    @PatchMapping("/{id}/admin")
    public ResponseEntity<AppUserReadModel> updateRole(@PathVariable(name = "id") int userId) {
        AppUser result = service.toggleAdmin(userId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }
    @PatchMapping("/{id}/block")
    public ResponseEntity<AppUserReadModel> updateBlock(@PathVariable(name = "id") int userId) throws IllegalAccessException {
        AppUser result = service.toggleBlock(userId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }

/*    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") int userId) {
        service.deleteAppUser(userId);
        return ResponseEntity.noContent().build();
    }*/
}
