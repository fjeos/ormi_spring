package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("select menu from OrderItem where orders.ordersId = :ordersId")
    List<Menu> findMenuByOrdersId(@Param("ordersId") Long ordersId);

    List<OrderItem> findByOrders(@Param("orders") Orders orders);
}
