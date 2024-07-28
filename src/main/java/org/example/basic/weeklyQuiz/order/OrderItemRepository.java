package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrders(@Param("orders") Orders orders);

    @Query("select i.menu.menuName as menuName, i.menu.category as category, COUNT(i) as cnt " +
            "from OrderItem i " +
            "group by i.menu.menuId " +
            "order by cnt desc limit 3")
    List<TopMenuInterface> findTop3Menus();

}
