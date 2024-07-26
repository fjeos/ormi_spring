package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.example.basic.weeklyQuiz.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{

    @Query("select o from Orders o where o.store = :store")
    List<Orders> findByStoreId(@Param("store") Store store);
}
