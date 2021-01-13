package pl.gregorymartin.newsportal.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.newsportal.category.Category;
import pl.gregorymartin.newsportal.category.CategoryRepository;
import pl.gregorymartin.newsportal.tag.Tag;
import pl.gregorymartin.newsportal.tag.TagRepository;
import pl.gregorymartin.newsportal.tag.TagService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;
    private final TagService tagService;

    PostService(final PostRepository postRepository, final TagRepository tagRepository, final CategoryRepository categoryRepository, final TagService tagService) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
        this.tagService = tagService;
    }

    List<Post> showPosts(){
        return postRepository.findAll();
    }

    Post addPost(Post source, long categoryId, long userId){
        Optional<Category> category = categoryRepository.findById(source.getCategory().getId());
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category is not exist");
        }
        source.setCategory(category.get());

        if(!source.getTags().isEmpty()){
            source.getTags().stream().forEach(tagService::addTag);
        }
        return postRepository.save(source);
    }

    @Transactional
    Post editLeadAndContent(Post source){
        Optional<Post> post = postRepository.findById(source.getId());
        if(post.isEmpty()){
            throw new IllegalArgumentException("Post is not exist");
        }
        post.get().setLead(source.getLead());
        post.get().setContent(source.getContent());
        return post.get();
    }

    @Transactional
    Post editCategory(long postId, long categoryId){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new IllegalArgumentException("Post is not exist");
        }
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new IllegalArgumentException("Category is not exist");
        }
        post.get().setCategory(category.get());
        return post.get();
    }

    @Transactional
    Post editTags(long postId, Set<Tag> tags){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new IllegalArgumentException("Post is not exist");
        }
        tags.forEach(tagService::addTag);
        post.get().setTags(tags);

        return post.get();
    }

    @Transactional
    void togglePublishPost(long postId){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new IllegalArgumentException("Post is not exist");
        }
        post.get().setPublished(!post.get().isPublished());
    }

    void deletePost(long id){
        if(postRepository.existsById(id)){
            throw new IllegalArgumentException("Post is not exist");
        }
        postRepository.deleteById(id);
    }
}
