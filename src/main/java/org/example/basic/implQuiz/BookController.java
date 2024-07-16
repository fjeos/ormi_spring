package org.example.basic.implQuiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = findBookById(id);
        model.addAttribute("book", book);
        return "books/editBook";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book editBook) {
        updateBook(editBook, findBookById(editBook.getId()));
        return "redirect:/books";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        books.removeIf(book -> book.getId() == id);
        return "redirect:/books";
    }

    private Book findBookById(Long id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 id의 책이 존재하지 않습니다."));
    }

    private void updateBook(Book editBook, Book book) {
        book.setAuthor(editBook.getAuthor());
        book.setTitle(editBook.getTitle());
        book.setPublicationYear(editBook.getPublicationYear());
    }
}
