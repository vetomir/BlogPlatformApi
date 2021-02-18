package pl.gregorymartin.newsportal.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.post.dto.*;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagFactory;
import pl.gregorymartin.newsportal.tag.dto.TagWriteModel;

import javax.validation.Valid;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts")
//todo
@CrossOrigin
class PostRestController {
    private final PostService service;

    PostRestController(final PostService service) {
        this.service = service;
    }

    @GetMapping()
    public String chuj(){
        return "CHUJ";
    }
    @GetMapping("/list/{type}")
    public ResponseEntity<List<?>> readAllAndMap(
            @PathVariable(required = false) String type,
            @RequestParam(required = false) Integer page,
            Sort.Direction sort,
            String sortBy,
            @RequestParam(required = false) Integer number
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";
        int numberOfPosts = number != null && number >= 0 ? number: 25;

        List<Post> posts = service.getPosts(pageNumber, sortDirection, sortByVariable, numberOfPosts);


        if(type.equals("header")){
            return ResponseEntity.ok(PostHeaderFactory.toDto(posts));
        }
        if(type.equals("hint")) {
            return ResponseEntity.ok(PostHintFactory.toDto(posts));
        }
        if(type.equals("hint-query")) {
            return ResponseEntity.ok(PostHintQueryFactory.toDto(posts));
        }
        if(type.equals("query")) {
            return ResponseEntity.ok(PostQueryFactory.toDto(posts));
        }
        return ResponseEntity.ok(PostFactory.toDto(posts));
    }



    @GetMapping("/search/{query}")
    public ResponseEntity<List<PostReadModel>> search(
            @PathVariable(name = "query") String query,
            @RequestParam(required = false) Integer page,
            Sort.Direction sort,
            String sortBy,
            @RequestParam(required = false) Integer number
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        int numberOfPosts = number != null && number >= 0 ? number: 25;

        Page<Post> posts = service.searchPosts(query, pageNumber, sortDirection, sortByVariable, numberOfPosts);

        return ResponseEntity.ok(PostFactory.toDto(posts.getContent()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReadModel> readSingle(@PathVariable(name = "id") int postId) {
        Post post = service.getSinglePost(postId);
        return ResponseEntity.ok(PostFactory.toDto(post));
    }

    @PostMapping
    public ResponseEntity<PostReadModel> create(@RequestBody @Valid PostWriteModel post, Authentication authentication) throws IllegalAccessException {
        if(authentication == null){
            throw new IllegalAccessException("You must be logged in first");
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Post result = service.addPost(PostFactory.toEntity(post), post.getCategoryId(), appUser.getId());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostReadModel> update(@PathVariable(name = "id") long postId, @RequestBody @Valid PostEditText post) {
        Post result = service.editTitleLeadAndContent(PostEditTextFactory.toEntity(post), postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/photo")
    public ResponseEntity<PostReadModel> updatePhoto(@PathVariable(name = "id") long postId, @RequestBody @Valid PostEditPhoto post) {
        Post result = service.editPhotoAndSource(PostPhotoFactory.toEntity(post), postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/category")
    public ResponseEntity<PostReadModel> updateCategory(@PathVariable(name = "id") long postId, @RequestParam(name = "id") long categoryId) {
        Post result = service.editCategory(postId, categoryId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/tags")
    public ResponseEntity<PostReadModel> updateTags(@PathVariable(name = "id") long postId, @RequestBody List<TagWriteModel> tags) {
        Post result = service.editTags(postId , new HashSet<>(TagFactory.toEntity(tags)));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<PostReadModel> togglePublish(@PathVariable(name = "id") long postId) {
        Post result = service.togglePublishPost(postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long postId) {
        System.out.println(postId);
        service.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
