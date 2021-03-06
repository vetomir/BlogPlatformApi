package pl.gregorymartin.newsportal.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserService;
import pl.gregorymartin.newsportal.category.Category;
import pl.gregorymartin.newsportal.category.CategoryService;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public
class PostService {
    private static final int PAGE_SIZE = 28;

    private final PostRepository postRepository;
    private final TagService tagService;
    private final CategoryService categoryService;
    private final AppUserService appUserService;

    PostService(final PostRepository postRepository, final TagService tagService, final CategoryService categoryService, final AppUserService appUserService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
        this.categoryService = categoryService;
        this.appUserService = appUserService;
    }

    List<Post> getPosts(){
        return postRepository.findAll();
    }

    public List<Post> getPosts(int page, Sort.Direction sort, String sortBy, int number) {

        return postRepository.findAllPublished(
                PageRequest.of(page, number,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    public Post getSinglePost(long postId){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new IllegalArgumentException("Post (ID:" + postId + ") is not exist");
        }
        return post.get();
    }

    Post addPost(Post source, long categoryId, long userId){
        AppUser appUser = appUserService.getSingleAppUser(userId);
        source.setAppUser(appUser);

        Category category = categoryService.getSingleCategory(categoryId);
        source.setCategory(category);

        source.setTags(tagService.saveTags(source.getTags()));

        return postRepository.save(source);
    }

    Post editPost(Post source, long categoryId, long userId){
        AppUser appUser = appUserService.getSingleAppUser(userId);
        source.setAppUser(appUser);

        Category category = categoryService.getSingleCategory(categoryId);
        source.setCategory(category);

        source.setTags(tagService.saveTags(source.getTags()));

        return postRepository.save(source);
    }

    @Transactional
    Post editTitleLeadAndContent(Post source, long postId){
        Post post = getSinglePost(postId);

        post.setTitle(source.getTitle());
        post.setLead(source.getLead());
        post.setContent(source.getContent());
        return postRepository.save(post);
    }

    @Transactional
    Post editPhotoAndSource(Post source, long postId){
        Post post = getSinglePost(postId);

        post.setPhotoUrl(source.getPhotoUrl());
        post.setPhotoSource(source.getPhotoSource());
        return postRepository.save(post);
    }

    @Transactional
    Post editCategory(long postId, long categoryId){
        Post post = getSinglePost(postId);

        Category category = categoryService.getSingleCategory(categoryId);
        post.setCategory(category);

        return postRepository.save(post);
    }


    @Transactional
    Post editTags(long postId, Set<Tag> tags){
        Post post = getSinglePost(postId);

        post.setTags(tagService.saveTags(tags));

        tagService.deleteUnusedTags();
        return postRepository.save(post);
    }

    public long numberOfResultsFromSearchPost( String query){
        return postRepository.getSizeOfAllByContainedQuery(query);
    }
    public Page<Post> searchPosts(String query, int page, Sort.Direction sort, String sortBy, int items) {
        String[] queryArray = query.split(" ");


        List<Post> result = new ArrayList<>();
        for (String q : queryArray) {
            List<Post> allByQueryInTitle = postRepository.findAllByQueryInTitle(q, PageRequest.of(page, items,
                    Sort.by(sort, sortBy)));
            result.addAll(allByQueryInTitle);

            List<Post> allByQueryInLead = postRepository.findAllByQueryInLead(q, PageRequest.of(page, items,
                    Sort.by(sort, sortBy)));
            result.addAll(allByQueryInLead);

            List<Post> allByQueryInContent = postRepository.findAllByQueryInContent(q, PageRequest.of(page, items,
                    Sort.by(sort, sortBy)));
            result.addAll(allByQueryInContent);
        }
        List<Post> resultDto = result.stream()
                .distinct()
                .collect(Collectors.toList());

        PageImpl<Post> postReadModels = new PageImpl<>(
                resultDto, PageRequest.of(page, items,
                Sort.by(sort, sortBy)), items);

        return postReadModels;
    }

    @Transactional
    Post togglePublishPost(long postId){
        Post post = getSinglePost(postId);
        post.setPublished(!post.isPublished());

        return postRepository.save(post);
    }

    void deletePost(long postId){
        Post post = getSinglePost(postId);
        postRepository.deleteById(post.getId());
    }
}
