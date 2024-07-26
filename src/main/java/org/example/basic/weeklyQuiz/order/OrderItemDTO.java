package org.example.basic.weeklyQuiz.order;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long menuId;
    private int amount;
    
}
