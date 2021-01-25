package pl.gregorymartin.newsportal.post;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.post.dto.PostEditPhoto;
import pl.gregorymartin.newsportal.post.dto.PostEditText;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagFactory;
import pl.gregorymartin.newsportal.tag.dto.TagWriteModel;

import java.net.URI;
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

    @GetMapping("/list")
    public ResponseEntity<List<PostReadModel>> readAll(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Post> posts = service.getPosts(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(PostFactory.toDto(posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostReadModel> readSingle(@PathVariable(name = "id") int postId) {
        Post post = service.getSinglePost(postId);
        return ResponseEntity.ok(PostFactory.toDto(post));
    }

    @PostMapping
    public ResponseEntity<PostReadModel> create(@RequestBody PostWriteModel post, Authentication authentication/*, @RequestParam(name = "user-id") long userId*/) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        Post result = service.addPost(PostFactory.toEntity(post), post.getCategoryId(), appUser.getId());
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostReadModel> update(@PathVariable(name = "id") long postId, @RequestBody PostEditText post/*, @RequestParam(name = "user-id") long userId*/) {
        Post result = service.editTitleLeadAndContent(PostEditTextFactory.toEntity(post), postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/photo")
    public ResponseEntity<PostReadModel> updatePhoto(@PathVariable(name = "id") long postId, @RequestBody PostEditPhoto post/*, @RequestParam(name = "user-id") long userId*/) {
        Post result = service.editPhotoAndSource(PostPhotoFactory.toEntity(post), postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/category")
    public ResponseEntity<PostReadModel> updateCategory(@PathVariable(name = "id") long postId, @RequestParam(name = "id") long categoryId) {
        Post result = service.editCategory(postId, categoryId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(PostFactory.toDto(result));
    }

    @PatchMapping("/{id}/tags")
    public ResponseEntity<PostReadModel> updateTags(@PathVariable(name = "id") long postId, @RequestBody List<TagWriteModel> tags/*, @RequestParam(name = "user-id") long userId*/) {
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
        service.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
