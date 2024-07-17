package org.example.basic.dailyQuiz0717;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String title;
    private String author;
    private String isbn;
    private int price;
    private String publishedYear;
}
