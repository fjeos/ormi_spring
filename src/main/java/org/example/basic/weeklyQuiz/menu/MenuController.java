package org.example.basic.weeklyQuiz.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // 전체 메뉴 목록 조회
    @GetMapping
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    // 메뉴 상세(단건) 조회
    @GetMapping("/{id}")
    public ResponseEntity<Optional<MenuDTO>> getMenuById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    // 새 메뉴 생성
    @PostMapping
    public void makeMenu(@RequestBody MenuDTO menuDTO) {
        menuService.makeMenu(menuDTO);
    }

    // 메뉴 정보 수정
    @PatchMapping("/{id}")
    public ResponseEntity<MenuDTO> updateMenu(@PathVariable("id") Long id,
                                              @RequestBody MenuDTO menuDTO) {
        return ResponseEntity.ok(menuService.updateMenu(id, menuDTO));
    }

    // 메뉴 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable("id") Long id) {
        return menuService.deleteMenu(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // 특정 카테고리 메뉴 조회
    @GetMapping("/category")
    public ResponseEntity<List<MenuDTO>> getMenusByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(menuService.getMenusByCategory(category));
    }

}
