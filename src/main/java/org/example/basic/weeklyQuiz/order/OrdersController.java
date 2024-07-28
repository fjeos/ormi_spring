package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /*// storeId의 주문 목록 조회
    @GetMapping("/{storeId}")
    public ResponseEntity<OrdersResponseDTO> getOrdersList(@PathVariable("storeId") Long storeId) {
        return ResponseEntity.ok(ordersService.getOrdersList(storeId));
    }*/

    // 주문 생성
    @PostMapping("/{storeId}")
    public void makeOrder(@PathVariable("storeId") Long storeId,
                          @RequestBody OrderItemDTO[] orderItemDTOS) {
        ordersService.makeOrder(storeId, orderItemDTOS);
    }

    // 주문 완료, 취소
    @PatchMapping("/{orderId}")
    public int completeOrder(@PathVariable("orderId") Long orderId,
                             @RequestParam("type") String type) {
        return ordersService.completeOrder(orderId, type);
    }

    // 각 매장별 특정기간 동안의 매출 조회
    @GetMapping("/sales")
    public ResponseEntity<List<SalesResponseInterface>> getSalesByStores(@RequestBody SalesRequestDTO salesRequestDTO) {
        return ResponseEntity.ok(ordersService.getSalesByStores(salesRequestDTO));
    }

    // 가장 인기있는 메뉴 조회
    @GetMapping("/top-sales")
    public ResponseEntity<List<TopMenuInterface>> getTopSalesMenus() {
        return ResponseEntity.ok(ordersService.getTopSalesMenus());
    }
}
