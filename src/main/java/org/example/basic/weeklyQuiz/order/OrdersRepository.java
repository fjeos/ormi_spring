package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.example.basic.weeklyQuiz.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{

    @Query("select sum(o.totalPrice) as totalPrice, o.store.storeId as storeId from Orders o where o.state = '완료' and o.createdAt between :start and :end group by o.store")
    List<SalesResponseInterface> calculateSales(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
