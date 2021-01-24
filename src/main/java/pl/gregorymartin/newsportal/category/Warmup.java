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
        if(service.getCategories().size() == 0){
            loadData();
        }

    }
    void loadData(){
        //Fixed and Irremovable Category
        Category Uncategorized = new Category("Uncategorized", 0);
        Uncategorized.setFixed(true);
        service.addCategory(Uncategorized);


        service.addCategory(new Category("Category 1", 0));
        service.addCategory(new Category("Category 2", 0));
        service.addCategory(new Category("Category 3", 0));
    }

}
