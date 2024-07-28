package org.example.basic.weeklyQuiz.menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 특정 카테고리의 메뉴 목록을 찾음
    List<Menu> findByCategory(@Param("category") String category);
}
