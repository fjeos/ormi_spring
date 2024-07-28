package org.example.basic.weeklyQuiz.order;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SalesRequestDTO {
    private String start;
    private String end;
}
