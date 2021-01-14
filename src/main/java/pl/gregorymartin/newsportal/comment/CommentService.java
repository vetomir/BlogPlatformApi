package pl.gregorymartin.newsportal.comment;

import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserRepository;
import pl.gregorymartin.newsportal.appUser.AppUserService;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostRepository;
import pl.gregorymartin.newsportal.post.PostService;

import java.util.List;
import java.util.Optional;

class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final AppUserService appUserService;

    CommentService(final CommentRepository commentRepository, final PostService postService, final AppUserService appUserService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.appUserService = appUserService;
    }

    List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    Comment getSingleComment(long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new IllegalArgumentException("Comment is not present");
        }
        return comment.get();
    }


    Comment addComment(Comment source, long userId, long postId){
        AppUser appUser = appUserService.getSingleAppUser(userId);
        Post post = postService.getSinglePost(postId);

        source.setAuthor(appUser);
        source.setPost(post);
        return commentRepository.save(source);
    }

    @Transactional
    Comment editComment(Comment source){
        Comment comment = getSingleComment(source.getId());
        comment.setContent(source.getContent());
        return comment;
    }

    void deleteComment(long commentId){
        Comment comment = getSingleComment(commentId);
        commentRepository.delete(comment);
    }
}
