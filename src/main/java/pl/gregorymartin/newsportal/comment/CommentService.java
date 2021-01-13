package pl.gregorymartin.newsportal.comment;

import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserRepository;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostRepository;

import java.util.List;
import java.util.Optional;

class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;

    CommentService(final CommentRepository commentRepository, final PostRepository postRepository, final AppUserRepository appUserRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.appUserRepository = appUserRepository;
    }

    List<Comment> findAll(){
        return commentRepository.findAll();
    }
    Comment addComment(Comment source, long userId, long postId){
        Optional<AppUser> user = appUserRepository.findById(userId);
        if(user.isEmpty()){
            throw new IllegalArgumentException("User is not present");
        }
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            
        }

        return commentRepository.save(source);
    }
}
