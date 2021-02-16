package pl.gregorymartin.newsportal.post;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.gregorymartin.newsportal.tag.Tag;

import java.util.Set;

@Component("postWarmup")
@DependsOn({"categoryWarmup", "appUserWarmup"})
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final PostService service;
    private String loremLead = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dignissim vehicula elementum. Ut ante erat, sagittis vitae ex eu, interdum consectetur mauris. Etiam accumsan est at neque laoreet, quis tincidunt mi sollicitudin. Cras dapibus est nibh.";

    private String loremContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dignissim vehicula elementum. Ut ante erat, sagittis vitae ex eu, interdum consectetur mauris. Etiam accumsan est at neque laoreet, quis tincidunt mi sollicitudin. Cras dapibus est nibh. Praesent sit amet cursus velit, vitae elementum est. Pellentesque faucibus euismod turpis in posuere. Sed varius lorem at eros mattis, a ullamcorper massa auctor. In ullamcorper a velit sed finibus. Etiam lorem sem, scelerisque non est in, finibus tincidunt risus. Integer ac risus ac nisl maximus consequat eget in urna. Nulla molestie nunc id erat sodales, vitae feugiat arcu viverra. Mauris commodo bibendum nulla, pellentesque facilisis nulla fringilla ut. Integer sagittis eget ex id semper. Morbi lacus mi, malesuada sed fermentum bibendum, tristique in tortor. Donec pretium ornare sapien ut posuere. In hac habitasse platea dictumst.Praesent egestas tincidunt augue, eget vulputate erat imperdiet sit amet. Praesent eu ex et justo feugiat tincidunt. Integer feugiat in est ac consectetur. Nam pretium et turpis ut imperdiet. Vestibulum bibendum ex et convallis pharetra. Pellentesque vulputate, ex nec sagittis ullamcorper, leo orci eleifend leo, eu molestie ex orci in sapien. Etiam egestas, est eu vestibulum varius, ante orci eleifend magna, suscipit ullamcorper nunc lectus vitae lacus. Sed massa velit, convallis id lacinia quis, dapibus nec odio. Nunc quis est ac sem blandit facilisis vitae id lacus. Cras egestas velit euismod, lobortis quam a, vehicula sem. Vestibulum vel nisi vehicula, pellentesque neque ac, ornare nisl. In hac habitasse platea dictumst. Nam tempus dignissim faucibus. Mauris ut dapibus mauris. In maximus sapien eget elit viverra, vitae cursus erat finibus. Vivamus iaculis eu ligula in molestie. Morbi diam erat, ornare in nisl et, pellentesque sodales diam. Proin vitae tincidunt lorem, nec aliquet purus. Aliquam ornare libero sit amet nulla ullamcorper lacinia. Curabitur vestibulum quam ac mauris dignissim sagittis. Quisque gravida sed lectus at interdum. Morbi fermentum leo odio, in fermentum diam vulputate sit amet. Etiam malesuada auctor sapien, eget placerat ipsum tristique sed. Nam neque lectus, semper ac augue nec, suscipit lacinia quam. Proin consequat turpis nisl, et blandit mauris tempor eget. Aenean tincidunt eros urna, lacinia pretium sapien luctus eu. Maecenas sed pretium enim, in venenatis nisl. Nulla vestibulum in est id feugiat. Donec luctus mattis eros sit amet feugiat. Etiam volutpat viverra velit eget commodo. Aenean justo ipsum, convallis ac vehicula nec, pretium pretium turpis. Aliquam maximus massa vel massa semper, vel dictum turpis accumsan. Quisque congue, odio quis fringilla viverra, ipsum nunc ultricies purus, nec gravida nibh arcu eleifend mauris. In vitae volutpat lorem. Praesent aliquam iaculis fermentum. Vivamus eget leo in ex ultrices sodales. Sed ut sem ornare, consectetur orci faucibus, finibus nulla.";

    Warmup(final PostService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {
        if(service.getPosts().size() == 0){
            loadData();
        }

    }
    void loadData(){
        samplePost(
                "The ancient Roman technology that is solving the space industry’s antenna problem",
                1,
                1,
                "https://images.unsplash.com/photo-1451187863213-d1bcbaae3fa3?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2000&q=80"
                );
        samplePost("Europe’s armies look for digital sovereignty in new messaging tech",
                2,
                1,
                "https://images.unsplash.com/photo-1483782817618-9804403024ba?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1973&q=80"
                );
        samplePost("Three tips on preparing for an IPO",
                3,
                1,
                "https://images.unsplash.com/photo-1538391543564-047a76156421?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1901&q=80"
                );
        samplePost("European tech workers are moving to the countryside — but will they stick around?",
                1,
                2,
                "https://images.unsplash.com/photo-1457305237443-44c3d5a30b89?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1953&q=80"
                );
        samplePost("Latitude: LocalGlobe’s ‘breakout’ fund breaks cove",
                4,
                2,
                "https://images.unsplash.com/photo-1461749280684-dccba630e2f6?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
                );
        samplePost("2020 was a record year for VC investment in Poland",
                1,
                2,
                "https://images.unsplash.com/photo-1483478550801-ceba5fe50e8e?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
                );
        samplePost("Frontline raises fresh €70m seed fund",
                2,
                3,
                "https://images.unsplash.com/photo-1496171367470-9ed9a91ea931?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
                );
        samplePost("Atomico launches third angel programme",
                2,
                3,
                "https://images.unsplash.com/photo-1593642632823-8f785ba67e45?ixid=MXwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2089&q=80"
                );
        samplePost("Oxford University set to launch up to 20 sustainability startups in two years",
                2,
                3,
                "https://images.unsplash.com/photo-1612827914912-cad82af55539?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1916&q=80"
                );
        samplePost("Leadership after Covid: upgrade please",
                1,
                3,
                "https://images.unsplash.com/photo-1612828898781-9c53031d0420?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1902&q=80"
                );
        samplePost("The Czech startup helping us make better use of our batteries",
                3,
                5,
                "https://images.unsplash.com/photo-1612889823446-ce8426381ed8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1900&q=80"
                );
        samplePost("The most active angel investors in the UK",
                2,
                1,
                "https://images.unsplash.com/photo-1612830079777-54121ec93c40?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2134&q=80"
                );
        samplePost("Checkout.com becomes Europe’s largest unicorn with $15bn valuation",
                2,
                1,
                "https://images.unsplash.com/photo-1612889047716-3128cdd3ce58?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
        );
        samplePost("Oxford University set to launch up to 20 sustainability startups in two years",
                2,
                5,
                "https://images.unsplash.com/photo-1612827914912-cad82af55539?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1916&q=80"
        );
        samplePost("Leadership after Covid: upgrade please",
                1,
                4,
                "https://images.unsplash.com/photo-1612828898781-9c53031d0420?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1902&q=80"
        );
        samplePost("The Czech startup helping us make better use of our batteries",
                3,
                4,
                "https://images.unsplash.com/photo-1612889823446-ce8426381ed8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1900&q=80"
        );
        samplePost("The most active angel investors in the UK",
                2,
                5,
                "https://images.unsplash.com/photo-1612830079777-54121ec93c40?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2134&q=80"
        );
        samplePost("Checkout.com becomes Europe’s largest unicorn with $15bn valuation",
                2,
                4,
                "https://images.unsplash.com/photo-1612889047716-3128cdd3ce58?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1950&q=80"
        );

    }

    void samplePost(String title, int category, int user, String photo){
        Post post = new Post(title, loremLead, loremContent, photo);
        post.setTags(Set.of(new Tag("Tag1"),new Tag("Tag2"),new Tag("Tag3")));
        service.addPost(post, category, user);
    }
}
