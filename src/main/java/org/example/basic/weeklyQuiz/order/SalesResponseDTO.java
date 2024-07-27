package org.example.basic.weeklyQuiz.order;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SalesResponseDTO {

    private Long storeId;
    private int totalPrice;

}
