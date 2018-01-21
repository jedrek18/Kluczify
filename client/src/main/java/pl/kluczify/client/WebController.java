package pl.kluczify.client;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController()
public class WebController {
    @RequestMapping("/openCloseRoom")
    public String openCloseRoom() {
        return "Loading...";
    }
}