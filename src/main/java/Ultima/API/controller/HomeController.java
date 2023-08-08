package Ultima.API.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class HomeController {

    static final String HOME = "/";
@GetMapping(HOME)
    public String home(){
        return "home";
    }
}
