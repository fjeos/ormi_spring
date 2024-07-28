package org.example.basic.weeklyQuiz.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // 전달받은 order에 연결되어있는 orderItem들을 조회
    List<OrderItem> findByOrders(@Param("orders") Orders orders);

    // 주문량이 가장 많은 메뉴 상위 3개 조회
    @Query("select i.menu.menuName as menuName, i.menu.category as category, COUNT(i) as cnt " +
            "from OrderItem i " +
            "group by i.menu.menuId " +
            "order by cnt desc limit 3")
    List<TopMenuInterface> findTop3Menus();

}
