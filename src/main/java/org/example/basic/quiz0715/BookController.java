package org.example.basic.quiz0715;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private Long nextId = 1L;
    private List<Book> books = new ArrayList<>();

    @GetMapping
    public String bookList(Model model){
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String addBookForm(Model model, Book book) {
        model.addAttribute("book", book);
        return "books/addBook";
    }
    @PostMapping
    public String addBook(@ModelAttribute Book book){
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

}
