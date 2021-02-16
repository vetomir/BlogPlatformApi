package pl.gregorymartin.newsportal.comment;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("commentWarmup")
@DependsOn("postWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final CommentService service;
    private String loremContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dignissim vehicula elementum. Ut ante erat, sagittis vitae ex eu, interdum consectetur mauris.";

    Warmup(final CommentService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        if(service.getComments().size() == 0){
            loadData();
        }
    }
    void loadData(){
        //service.addPost(new Post("Title 1", "Lorem", "Lorem ipsum dolor sit amet, "));
        service.addComment(new Comment(loremContent, 0), 1 , 2);
        service.addComment(new Comment(loremContent, 0), 2 , 1);
        service.addComment(new Comment(loremContent, 0), 3 , 3);
        service.addComment(new Comment(loremContent, 1), 1 , 2);
        service.addComment(new Comment(loremContent, 1), 2 , 4);
        service.addComment(new Comment(loremContent, 1), 3 , 6);
        service.addComment(new Comment(loremContent, 1), 2 , 7);
        service.addComment(new Comment(loremContent, 1), 1 , 8);
        service.addComment(new Comment(loremContent, 1), 3 , 8);
        service.addComment(new Comment(loremContent, 1), 2 , 8);
        service.addComment(new Comment(loremContent, 1), 2 , 9);
        service.addComment(new Comment(loremContent, 1), 1 , 10);
        service.addComment(new Comment(loremContent, 1), 3 , 2);
        service.addComment(new Comment(loremContent, 1), 1 , 10);
        service.addComment(new Comment(loremContent, 1), 2 , 1);
    }
}
