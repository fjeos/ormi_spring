package org.example.basic.weeklyQuiz.order;

import lombok.*;
import org.example.basic.weeklyQuiz.menu.Menu;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long menuId;
    private String state;
    private int totalPrice;

    public static OrdersDTO toDTO(Orders orders) {
        return OrdersDTO.builder()
                .menuId(orders.getMenu().getMenuId())
                .state(orders.getState())
                .totalPrice(orders.getTotalPrice())
                .build();
    }

    public static Orders toOrders(OrdersDTO ordersDTO, Menu menu) {
        return Orders.builder()
                .menu(menu)
                .state(ordersDTO.getState())
                .totalPrice(ordersDTO.getTotalPrice())
                .build();
    }
}