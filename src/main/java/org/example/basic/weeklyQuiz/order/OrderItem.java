package org.example.basic.weeklyQuiz.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.basic.weeklyQuiz.menu.Menu;
import org.hibernate.query.Order;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    private int amount;

    public void updateOrderItem(Orders order, Menu menu, OrderItemDTO orderItemDTO) {
        this.orders = order;
        this.menu = menu;
        this.amount = orderItemDTO.getAmount();
    }
}
