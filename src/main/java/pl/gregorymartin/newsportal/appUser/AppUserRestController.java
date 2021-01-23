package pl.gregorymartin.newsportal.appUser;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.dto.AppUserReadModel;
import pl.gregorymartin.newsportal.appUser.dto.AppUserWriteModel;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostFactory;
import pl.gregorymartin.newsportal.post.PostService;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagFactory;
import pl.gregorymartin.newsportal.tag.dto.TagWriteModel;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
//todo
@CrossOrigin
class AppUserRestController {
    private final AppUserService service;

    AppUserRestController(final AppUserService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppUserReadModel>> readAll(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<AppUser> appUsers = service.getUsers(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(AppUserFactory.toDto(appUsers));
    }

    @GetMapping
    public ResponseEntity<AppUserReadModel> readSingle(@RequestParam int id) {
        AppUser appUser = service.getSingleAppUser(id);
        return ResponseEntity.ok(AppUserFactory.toDto(appUser));
    }

    @PostMapping
    public ResponseEntity<AppUserReadModel> create(@RequestBody AppUserWriteModel appUser/*, @RequestParam(name = "user-id") long userId*/) {
        if(!appUser.getPassword().equals(appUser.getPassword2())){
            throw new IllegalArgumentException("passwords are not the same");
        }
        AppUser result = service.addAppUser(AppUserFactory.toEntity(appUser));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }

    @PatchMapping("/profile")
    public ResponseEntity<AppUserReadModel> updateProfile(@RequestBody AppUserWriteModel source, @RequestParam(name = "id") long userId/*, @RequestParam(name = "user-id") long userId*/) {
        AppUser appUser = AppUserFactory.toEntity(source);
        appUser.setId(userId);
        AppUser result = service.editAppUserProfile(appUser);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }

    @PatchMapping("/credentials")
    public ResponseEntity<AppUserReadModel> updateCredentials(@RequestBody AppUserWriteModel source, @RequestParam(name = "id") long userId/*, @RequestParam(name = "user-id") long userId*/) {
        AppUser appUser = AppUserFactory.toEntity(source);
        appUser.setId(userId);
        AppUser result = service.editAppUserCredentials(appUser);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }


    @PatchMapping("/photo")
    public ResponseEntity<AppUserReadModel> updatePhoto(@RequestBody AppUserWriteModel source, @RequestParam(name = "id") long userId/*, @RequestParam(name = "user-id") long userId*/) {
        AppUser appUser = AppUserFactory.toEntity(source);
        appUser.setId(userId);
        AppUser result = service.editAppUserPhoto(appUser);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(AppUserFactory.toDto(result));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam long id) {
        service.deleteAppUser(id);
        return ResponseEntity.noContent().build();
    }
}
