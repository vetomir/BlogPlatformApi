package pl.gregorymartin.newsportal.post;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagFactory;
import pl.gregorymartin.newsportal.tag.TagQueryFactory;
import pl.gregorymartin.newsportal.tag.dto.TagWriteModel;

import java.net.URI;
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

    @GetMapping("/list")
    public ResponseEntity<List<PostReadModel>> readAll(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Post> posts = service.getPosts(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(PostFactory.toDto(posts));
    }

    @GetMapping
    public ResponseEntity<PostReadModel> readSingle(@RequestParam int id) {
        Post post = service.getSinglePost(id);
        return ResponseEntity.ok(PostFactory.toDto(post));
    }

    @PostMapping
    public ResponseEntity<PostReadModel> create(@RequestBody PostWriteModel post, Authentication authentication/*, @RequestParam(name = "user-id") long userId*/) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Post result = service.addPost(PostFactory.toEntity(post), post.getCategoryId(), appUser.getId());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping
    public ResponseEntity<PostReadModel> update(@RequestBody PostWriteModel post/*, @RequestParam(name = "user-id") long userId*/) {
        Post result = service.editLeadAndContent(PostFactory.toEntity(post));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/tags")
    public ResponseEntity<PostReadModel> updateTags(@RequestBody List<TagWriteModel> tags, @RequestParam long id/*, @RequestParam(name = "user-id") long userId*/) {
        Post result = service.editTags(id , (Set<Tag>) TagFactory.toEntity(tags));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/category")
    public ResponseEntity<PostReadModel> updateCategory(@RequestParam long postId, @RequestParam long categoryId) {
        Post result = service.editCategory(postId, categoryId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }
    @PatchMapping("/publish")
    public ResponseEntity<PostReadModel> togglePublish(@RequestParam long postId) {
        Post result = service.togglePublishPost(postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam long id) {
        service.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
