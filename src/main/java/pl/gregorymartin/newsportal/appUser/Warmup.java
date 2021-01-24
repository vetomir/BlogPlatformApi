package pl.gregorymartin.newsportal.appUser;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("appUserWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final AppUserService service;

    Warmup(final AppUserService service) {
        this.service = service;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent contextRefreshedEvent) {

        if(service.getUsers().size() == 0){
            loadData();
        }

    }
    void loadData(){

        service.addAppUser(new AppUser("test1@test.pl","test123","test_user1"));
        service.addAppUser(new AppUser("test2@test.pl","test123","test_user2"));
        service.addAppUser(new AppUser("test3@test.pl","test123","test_user3"));
    }
}
