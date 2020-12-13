package com.cinema.controllers.simple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Hello!!");
        return "home";
    }
    @GetMapping("/films")
    public String films(Model model) {
        model.addAttribute("title", "Did you see new films");
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Creator by Maxim");
        return "author";
    }

}
