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

        service.addAppUser(new AppUser("test1@test.pl","test123","incurable_optimist123", "Arthur", "Schopenhauer", "https://krainaparadoksow.pl/wp-content/uploads/2018/10/arthur-schopenhauer.jpg"));
        service.addAppUser(new AppUser("test2@test.pl","test123","the_hydraulik", "Lech", "Wałęsa", "https://ocdn.eu/pulscms-transforms/1/b2pk9kpTURBXy85MzJmYTg0NmNkOGQxN2M5MWM3ZjI5MDY2YzBiNDRjMC5qcGeTlQMAzKbNFMPNC62TBc0DFM0BvJMJpmNlYzYyZQaBoTAB/lech-walesa.jpg"));
        service.addAppUser(new AppUser("test3@test.pl","test123","princ", "Mieszko", "Pierwszy", "https://tolstoj.eu/wp-content/uploads/2016/10/10zeta.jpg"));
        service.addAppUser(new AppUser("test4@test.pl","test123","langosz", "Stefan", "Batory", "https://kurierhistoryczny.pl/uploads/articles/387/batory_rqx9ox.jpg"));
        service.addAppUser(new AppUser("test5@test.pl","test123","strong_full", "Ferdynand", "Kiepski", "https://ocdn.eu/pulscms-transforms/1/zQMk9kpTURBXy9hMjJjZTczYTVhODAwYjlhYTY0NGQ2YjlkMzY0YzYwYS5qcGeSlQMDzQJUzRAfzQkSlQLNAwcAw8OBoTAF"));
    }
}
