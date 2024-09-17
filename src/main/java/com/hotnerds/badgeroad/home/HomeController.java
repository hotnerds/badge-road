package com.hotnerds.badgeroad.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "login";
    }
}
