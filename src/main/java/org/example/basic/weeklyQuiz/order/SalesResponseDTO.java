package org.example.basic.weeklyQuiz.order;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SalesResponseDTO {

    private int totalPrice;
    private Long storeId;

}
