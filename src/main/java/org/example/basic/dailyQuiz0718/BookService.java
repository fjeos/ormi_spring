package org.example.basic.dailyQuiz0718;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private List<Book> bookList = new ArrayList<>();
    private Long nextId = 1L;

    // 전체 책 리스트 반환
    public List<BookDto> getAllBooks() {
        return bookList.stream()
                .map(this::convertToBookDto)
                .collect(Collectors.toList());
    }

    // 책 단건 조회
    public BookDto getBookById(Long id) {
        return convertToBookDto(findBookById(id));
    }

    // 새 책 생성
    public BookDto createBook(BookDto bookDto) {
        Book book = convertToBookEntity(bookDto);
        book.setId(nextId++);
        bookList.add(book);
        return convertToBookDto(book);
    }

    // 책 정보 수정
    public BookDto updateBook(Long id, BookDto bookDto) {
        Book book = findBookById(id);
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublishedYear(bookDto.getPublishedYear());
        book.setAuthor(bookDto.getAuthor());
        return convertToBookDto(book);
    }

    // 책 삭제
    public boolean deleteBook(Long id) {
        Book book = findBookById(id);
        return bookList.remove(book);
    }

    // Book -> BookDto
    private BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublishedYear(book.getPublishedYear());
        return bookDto;
    }

    // BookDto -> Book
    private Book convertToBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublishedYear(bookDto.getPublishedYear());
        return book;
    }

    // id로 Book 찾기
    private Book findBookById(Long id) {
        return bookList.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("책이 존재하지 않습니다."));
    }
}
