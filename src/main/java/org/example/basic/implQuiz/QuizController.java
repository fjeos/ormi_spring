package org.example.basic.implQuiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    Long nextId = 1L;
    List<Product> products = new ArrayList<>();

    @GetMapping
    public String test(Model model) {
        User user = new User("max", "max@gmail.com", true, "1234");
        model.addAttribute("user", user);

        return "quiz";
    }

    @GetMapping("/list")
    public String list(Model model){
        products.add(new Product(nextId++, "모자", 1000));
        products.add(new Product(nextId++, "자켓", 49392));
        products.add(new Product(nextId++, "티", 1000));
        products.add(new Product(nextId++, "신발", 64699));
        products.add(new Product(nextId++, "노트북", 59839385));
        model.addAttribute("products", products);

        return "quiz/products";
    }

    @GetMapping("/new")
    public String newUserForm(Model model, User user){
        model.addAttribute("user", user);
        return "quiz/form";
    }

    @PostMapping
    public String printUser(@ModelAttribute User user){
        System.out.println("이름 = " + user.getUsername());
        System.out.println("이메일 = " + user.getEmail());
        System.out.println("비밀번호 = " + user.getPassword());

        return "redirect:/quiz";
    }
}
