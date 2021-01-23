package pl.gregorymartin.newsportal.category;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("categoryWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryService service;

    Warmup(final CategoryService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        //loadData();

    }
    void loadData(){
        service.addCategory(new Category("Category", 0));
    }
}
