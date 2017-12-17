package pl.kluczify.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by jedrek on 14.12.17.
 */
@RestController
public class WebController {
    @RequestMapping("/")
    public ResponseEntity<String> home()
    {
        return ResponseEntity.ok("XD");
    }
}
