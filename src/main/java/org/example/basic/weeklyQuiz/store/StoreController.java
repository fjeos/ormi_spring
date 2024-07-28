package org.example.basic.weeklyQuiz.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;
    @Autowired
    public StoreController(StoreService customerService) {
        this.storeService = customerService;
    }

    // 가게 목록 조회
    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    // 특정 가게 조회
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> finStoreById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(storeService.findStoreById(id));
    }

    // 가게 생성
    @PostMapping
    public void createStore(@RequestBody StoreDTO storeDTO) {
        storeService.createStore(storeDTO);
    }

    // 가게 정보 수정
    @PutMapping("/{id}")
    public void updateStore(@PathVariable("id") Long id, @RequestBody StoreDTO storeDTO) {
        storeService.updateStore(id, storeDTO);
    }

    // 가게 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long id) {
        return storeService.deleteStore(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
