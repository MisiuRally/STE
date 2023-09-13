package STE.API.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";

    @GetMapping(name = HOME)
    public String home() {
        return "home";
    }
}
