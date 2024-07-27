package org.example.basic.weeklyQuiz.menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {


    List<Menu> findByCategory(@Param("category") String category);
}
