package pl.gregorymartin.newsportal.comment;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gregorymartin.newsportal.appUser.AppUser;
import pl.gregorymartin.newsportal.appUser.AppUserService;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private static final int PAGE_SIZE = 28;

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final AppUserService appUserService;

    CommentService(final CommentRepository commentRepository, final PostService postService, final AppUserService appUserService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.appUserService = appUserService;
    }

    List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public List<Comment> getComments(int page, Sort.Direction sort, String sortBy) {

        return commentRepository.findAll(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )
        ).getContent();
    }

    public List<Comment> getCommentsByAppUser(long userId, int page, Sort.Direction sort, String sortBy) {
        appUserService.getSingleAppUser(userId);


        return commentRepository.findAllByAppUser(
                userId,
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )

        ).getContent();
    }

    public List<Comment> getCommentsByPost(long postId, int page, Sort.Direction sort, String sortBy) {

        postService.getSinglePost(postId);

        return commentRepository.findAllByPost(
                postId,
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )

        ).getContent();
    }

    public List<Comment> getCommentsByParent(long parentId, int page, Sort.Direction sort, String sortBy) {

        getSingleComment(parentId);

        return commentRepository.findAllByParent(
                parentId,
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort, sortBy)
                )

        ).getContent();
    }

    Comment getSingleComment(long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty()){
            throw new IllegalArgumentException("Comment (ID:" + commentId + ") is not present");
        }
        return comment.get();
    }


    Comment addComment(Comment source, long userId, long postId){
        AppUser appUser = appUserService.getSingleAppUser(userId);
        Post post = postService.getSinglePost(postId);

        if(source.getParentCommentId() != 0){
            Comment parentComment = getSingleComment(source.getParentCommentId());
            source.setParentCommentId(parentComment.getId());
        }
        source.setAppUser(appUser);
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
