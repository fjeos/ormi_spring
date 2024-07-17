package org.example.basic.dailyQuiz0717;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> bookList = new ArrayList<>();
    private Long id = 1L;

    @GetMapping
    public List<Book> bookLists() {
        return bookList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        Book book = findBookById(id);
        return ResponseEntity.ok(convertToBookDto(book));

    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto) {
        Book newBook = convertToBookEntity(bookDto);
        bookList.add(newBook);
        return ResponseEntity.ok(newBook);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") Long id, @RequestBody Book updateBook) {
        Book book = findBookById(id);
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setIsbn(updateBook.getIsbn());
        book.setPrice(updateBook.getPrice());
        book.setPublishedYear(updateBook.getPublishedYear());

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookList.removeIf(b -> b.getId() == id);
    }

    private BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublishedYear(book.getPublishedYear());

        return bookDto;
    }

    private Book convertToBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setId(id++);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublishedYear(bookDto.getPublishedYear());

        return book;
    }
    private Book findBookById(Long id) {
        Book book = bookList.stream().filter(b -> b.getId() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("도서 없음"));
        return book;
    }
}
