package org.example.basic.quiz0715;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("message", "Hello, Thymeleaf!");

        return "test";
    }
}
