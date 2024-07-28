package org.example.basic.weeklyQuiz.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{

    // 특정 기간 중 매장별 매출 총액 계산
    @Query("select sum(o.totalPrice) as totalPrice, o.store.storeId as storeId " +
            "from Orders o " +
            "where o.state = '완료' and o.createdAt between :start and :end " +
            "group by o.store")
    List<SalesResponseInterface> calculateSales(@Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);

}
