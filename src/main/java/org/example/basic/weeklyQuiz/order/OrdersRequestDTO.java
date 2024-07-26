package org.example.basic.weeklyQuiz.order;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersRequestDTO {

    private List<OrderItemDTO> orderItemDTOS;

}
