package pl.gregorymartin.newsportal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ServerStatusController {
    @GetMapping("/api/status")
    public String getStatus(){
        return "true";
    }
}
