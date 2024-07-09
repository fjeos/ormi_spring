package org.example.basic.day4;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("name", "굿굿베리굿");
        model.addAttribute("message", "메시지");

        model.addAttribute("subtitle", "Daily Quiz 3번");
        model.addAttribute("style", "color:aqua");
        model.addAttribute("content", "th:text 타입에 들어갈 내용입니다.");
        model.addAttribute("border", "border:solid");
        model.addAttribute("btn_color", "color:green");

        return "index";
    }
}
