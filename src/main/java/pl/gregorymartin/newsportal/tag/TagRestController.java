package pl.gregorymartin.newsportal.tag;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostFactory;
import pl.gregorymartin.newsportal.post.dto.PostReadModel;
import pl.gregorymartin.newsportal.post.dto.PostWriteModel;
import pl.gregorymartin.newsportal.tag.dto.TagReadModel;
import pl.gregorymartin.newsportal.tag.dto.TagWriteModel;

import java.net.URI;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tag")
//todo
@CrossOrigin
class TagRestController {
    private final TagService service;

    TagRestController(final TagService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<TagReadModel>> readAll(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Tag> tags = service.getTags(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(TagFactory.toDto(tags));
    }

    @GetMapping
    public ResponseEntity<TagReadModel> readSingle(@RequestParam int id) {
        Tag tag = service.getSingleTag(id);
        return ResponseEntity.ok(TagFactory.toDto(tag));
    }

    @PatchMapping("/edit")
    public ResponseEntity<TagReadModel> update(@RequestBody TagWriteModel source, @RequestParam int id) {
        Tag tag = TagFactory.toEntity(source);
        tag.setId(id);
        Tag result = service.editTagName(tag);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(TagFactory.toDto(result));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam long id) {
        service.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
