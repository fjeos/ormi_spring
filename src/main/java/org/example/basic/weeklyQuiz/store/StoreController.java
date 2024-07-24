package org.example.basic.weeklyQuiz.store;

import org.example.basic.weeklyQuiz.customer.CustomerDTO;
import org.example.basic.weeklyQuiz.customer.CustomerService;
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

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> finStoreById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(storeService.findStoreById(id));
    }

    @PostMapping
    public void createStore(@RequestBody StoreDTO storeDTO) {
        storeService.createStore(storeDTO);
    }

    @PutMapping("/{id}")
    public void updateStore(@PathVariable("id") Long id, @RequestBody StoreDTO storeDTO) {
        storeService.updateStore(id, storeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable("id") Long id) {
        return storeService.deleteStore(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
