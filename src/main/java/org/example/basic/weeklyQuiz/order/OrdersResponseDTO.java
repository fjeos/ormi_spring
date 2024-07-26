package org.example.basic.weeklyQuiz.order;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersResponseDTO {

    private Long storeId;
    private LocalDateTime createdAt;
    private List<OrderItemDTO> orderItemDTOS;
    private String state;
    private int totalPrice;

    public static OrdersResponseDTO toDTO(Orders orders) {
        return OrdersResponseDTO.builder()
                .storeId(orders.getStore().getStoreId())
                .state(orders.getState())
                .totalPrice(orders.getTotalPrice())
                .build();
    }

    public static Orders toOrders(OrdersResponseDTO ordersDTO) {
        return Orders.builder()
                .state(ordersDTO.getState())
                .totalPrice(ordersDTO.getTotalPrice())
                .build();
    }
}