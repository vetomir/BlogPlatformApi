package pl.gregorymartin.newsportal.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserService;
import pl.gregorymartin.newsportal.category.Category;
import pl.gregorymartin.newsportal.category.CategoryRepository;
import pl.gregorymartin.newsportal.category.CategoryService;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagRepository;
import pl.gregorymartin.newsportal.tag.TagService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public
class PostService {
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

    public Post getSinglePost(long postId){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new IllegalArgumentException("Post is not exist");
        }
        return post.get();
    }

    Post addPost(Post source, long categoryId, long userId){
        AppUser appUser = appUserService.getSingleAppUser(userId);
        source.setAuthor(appUser);

        Category category = categoryService.getSingleCategory(categoryId);
        source.setCategory(category);

        if(!source.getTags().isEmpty()){
            source.getTags().forEach(tagService::addTag);
        }
        return postRepository.save(source);
    }

    @Transactional
    Post editLeadAndContent(Post source){
        Post post = getSinglePost(source.getId());

        post.setLead(source.getLead());
        post.setContent(source.getContent());
        return post;
    }

    @Transactional
    Post editCategory(long postId, long categoryId){
        Post post = getSinglePost(postId);

        Category category = categoryService.getSingleCategory(categoryId);
        post.setCategory(category);

        return post;
    }

    @Transactional
    Post editTags(long postId, Set<Tag> tags){
        Post post = getSinglePost(postId);

        tags.forEach(tagService::addTag);
        post.setTags(tags);

        return post;
    }

    @Transactional
    void togglePublishPost(long postId){
        Post post = getSinglePost(postId);
        post.setPublished(!post.isPublished());
    }

    void deletePost(long postId){
        Post post = getSinglePost(postId);
        postRepository.delete(post);
    }
}
