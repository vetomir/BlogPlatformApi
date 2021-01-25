package pl.gregorymartin.newsportal.comment;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.comment.dto.CommentReadModel;
import pl.gregorymartin.newsportal.comment.dto.CommentWriteModel;
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
@RequestMapping("/api/comments")
//todo
@CrossOrigin
class CommentRestController {
    private final CommentService service;

    CommentRestController(final CommentService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CommentReadModel>> readAll(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Comment> comments = service.getComments(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(CommentFactory.toDto(comments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentReadModel> readSingle(@PathVariable(name = "id") int commentId) {
        Comment comment = service.getSingleComment(commentId);
        return ResponseEntity.ok(CommentFactory.toDto(comment));
    }

/*    @GetMapping("/parent/{id}")
    public ResponseEntity<List<CommentReadModel>> readParentComments(@PathVariable int id, @RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Comment> comments = service.getCommentsByParent(id, pageNumber, sortDirection, sortByVariable);
        return ResponseEntity.ok(CommentFactory.toDto(comments));
    }*/

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentReadModel>> readByAppUser(@PathVariable int id, @RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Comment> comments = service.getCommentsByAppUser(id, pageNumber, sortDirection, sortByVariable);
        return ResponseEntity.ok(CommentFactory.toDto(comments));
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<List<CommentReadModel>> readByPost(@PathVariable int id, @RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Comment> comments = service.getCommentsByPost(id, pageNumber, sortDirection, sortByVariable);
        return ResponseEntity.ok(CommentFactory.toDto(comments));
    }

    @PostMapping
    public ResponseEntity<CommentReadModel> create(@RequestBody CommentWriteModel comment, @RequestParam(name = "id") long postId, Authentication authentication/*, @RequestParam(name = "user-id") long userId*/) {
        AppUser appUser = (AppUser) authentication.getPrincipal();

        Comment result = service.addComment(CommentFactory.toEntity(comment), appUser.getId(), postId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(CommentFactory.toDto(result));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentReadModel> update(@PathVariable(name = "id") long commentId, @RequestBody CommentWriteModel comment/*, @RequestParam(name = "user-id") long userId*/) {
        Comment result = service.editComment(CommentFactory.toEntity(comment), commentId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(CommentFactory.toDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long commentId) {
        service.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
