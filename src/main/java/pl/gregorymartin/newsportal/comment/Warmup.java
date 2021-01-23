package pl.gregorymartin.newsportal.comment;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.newsportal.post.Post;
import pl.gregorymartin.newsportal.post.PostService;

@Component("commentWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final CommentService service;
    private String loremContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dignissim vehicula elementum. Ut ante erat, sagittis vitae ex eu, interdum consectetur mauris.";

    Warmup(final CommentService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        //loadData();

    }
    void loadData(){
        //service.addPost(new Post("Title 1", "Lorem", "Lorem ipsum dolor sit amet, "));
        service.addComment(new Comment(loremContent, 0), 1 , 1);
        service.addComment(new Comment(loremContent, 0), 1 , 1);
        service.addComment(new Comment(loremContent, 0), 1 , 1);
        service.addComment(new Comment(loremContent, 1), 1 , 1);
    }
}
