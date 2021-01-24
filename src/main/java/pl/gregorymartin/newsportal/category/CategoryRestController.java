package pl.gregorymartin.newsportal.category;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.gregorymartin.newsportal.category.dto.CategoryReadModel;
import pl.gregorymartin.newsportal.category.dto.CategoryWriteModel;
import pl.gregorymartin.newsportal.utils.IllegalExceptionProcessing;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@IllegalExceptionProcessing
//todo
@CrossOrigin
class CategoryRestController {
    private final CategoryService service;

    CategoryRestController(final CategoryService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryReadModel>> readAll(@RequestParam(required = false) Integer page, Sort.Direction sort, String sortBy
            /*@AuthenticationPrincipal UsernamePasswordAuthenticationToken user*/) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort : Sort.Direction.ASC;
        String sortByVariable = sortBy != null ? sortBy : "id";

        List<Category> comments = service.getCategories(pageNumber, sortDirection, sortByVariable);

        return ResponseEntity.ok(CategoryFactory.toDto(comments));
    }

    @GetMapping
    public ResponseEntity<CategoryReadModel> readSingle(@RequestParam int id) {
        Category category = service.getSingleCategory(id);
        return ResponseEntity.ok(CategoryFactory.toDto(category));
    }

    @PostMapping
    public ResponseEntity<CategoryReadModel> create(@RequestBody @Valid CategoryWriteModel category/*, @RequestParam(name = "user-id") long userId*/) {

        Category result = service.addCategory(CategoryFactory.toEntity(category));

        return ResponseEntity.created(URI.create("/" + result.getId())).body(CategoryFactory.toDto(result));
    }

    @PatchMapping
    public ResponseEntity<CategoryReadModel> update(@RequestBody CategoryWriteModel category/*, @RequestParam(name = "user-id") long userId*/) throws IllegalAccessException {
        Category result = service.editCategory(CategoryFactory.toEntity(category));
        return ResponseEntity.created(URI.create("/" + result.getId())).body(CategoryFactory.toDto(result));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam long id) throws IllegalAccessException {
        service.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
