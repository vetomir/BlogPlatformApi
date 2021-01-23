package pl.gregorymartin.newsportal.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.Set;

@Component("postWarmup")
@Lazy
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final PostService service;
    private String loremLead = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dignissim vehicula elementum. Ut ante erat, sagittis vitae ex eu, interdum consectetur mauris. Etiam accumsan est at neque laoreet, quis tincidunt mi sollicitudin. Cras dapibus est nibh.";

    private String loremContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dignissim vehicula elementum. Ut ante erat, sagittis vitae ex eu, interdum consectetur mauris. Etiam accumsan est at neque laoreet, quis tincidunt mi sollicitudin. Cras dapibus est nibh. Praesent sit amet cursus velit, vitae elementum est. Pellentesque faucibus euismod turpis in posuere. Sed varius lorem at eros mattis, a ullamcorper massa auctor. In ullamcorper a velit sed finibus. Etiam lorem sem, scelerisque non est in, finibus tincidunt risus. Integer ac risus ac nisl maximus consequat eget in urna. Nulla molestie nunc id erat sodales, vitae feugiat arcu viverra. Mauris commodo bibendum nulla, pellentesque facilisis nulla fringilla ut. Integer sagittis eget ex id semper. Morbi lacus mi, malesuada sed fermentum bibendum, tristique in tortor. Donec pretium ornare sapien ut posuere. In hac habitasse platea dictumst.Praesent egestas tincidunt augue, eget vulputate erat imperdiet sit amet. Praesent eu ex et justo feugiat tincidunt. Integer feugiat in est ac consectetur. Nam pretium et turpis ut imperdiet. Vestibulum bibendum ex et convallis pharetra. Pellentesque vulputate, ex nec sagittis ullamcorper, leo orci eleifend leo, eu molestie ex orci in sapien. Etiam egestas, est eu vestibulum varius, ante orci eleifend magna, suscipit ullamcorper nunc lectus vitae lacus. Sed massa velit, convallis id lacinia quis, dapibus nec odio. Nunc quis est ac sem blandit facilisis vitae id lacus. Cras egestas velit euismod, lobortis quam a, vehicula sem. Vestibulum vel nisi vehicula, pellentesque neque ac, ornare nisl. In hac habitasse platea dictumst. Nam tempus dignissim faucibus. Mauris ut dapibus mauris. In maximus sapien eget elit viverra, vitae cursus erat finibus. Vivamus iaculis eu ligula in molestie. Morbi diam erat, ornare in nisl et, pellentesque sodales diam. Proin vitae tincidunt lorem, nec aliquet purus. Aliquam ornare libero sit amet nulla ullamcorper lacinia. Curabitur vestibulum quam ac mauris dignissim sagittis. Quisque gravida sed lectus at interdum. Morbi fermentum leo odio, in fermentum diam vulputate sit amet. Etiam malesuada auctor sapien, eget placerat ipsum tristique sed. Nam neque lectus, semper ac augue nec, suscipit lacinia quam. Proin consequat turpis nisl, et blandit mauris tempor eget. Aenean tincidunt eros urna, lacinia pretium sapien luctus eu. Maecenas sed pretium enim, in venenatis nisl. Nulla vestibulum in est id feugiat. Donec luctus mattis eros sit amet feugiat. Etiam volutpat viverra velit eget commodo. Aenean justo ipsum, convallis ac vehicula nec, pretium pretium turpis. Aliquam maximus massa vel massa semper, vel dictum turpis accumsan. Quisque congue, odio quis fringilla viverra, ipsum nunc ultricies purus, nec gravida nibh arcu eleifend mauris. In vitae volutpat lorem. Praesent aliquam iaculis fermentum. Vivamus eget leo in ex ultrices sodales. Sed ut sem ornare, consectetur orci faucibus, finibus nulla.";

    Warmup(final PostService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        //loadData();

    }
    void loadData(){
        samplePost("Post 1", 1, 1);
        samplePost("Post 2", 1, 1);
        samplePost("Post 3", 1, 1);
        samplePost("Post 4", 1, 2);
        samplePost("Post 5", 1, 2);
        samplePost("Post 6", 1, 2);
        samplePost("Post 7", 1, 3);
        samplePost("Post 8", 1, 3);
        samplePost("Post 9", 1, 3);

    }

    void samplePost(String title, int category, int user){
        Post post = new Post(title, loremLead, loremContent);
        post.setTags(Set.of(new Tag("Tag1"),new Tag("Tag2"),new Tag("Tag3")));
        service.addPost(post, category, user);
    }
}
