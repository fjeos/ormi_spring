package org.example.basic.weeklyQuiz.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // 주문 생성
    @PostMapping
    public void makeOrder(@RequestBody OrdersDTO orderDTO) {
        ordersService.makeOrder(orderDTO);
    }

    // 주문 완료
    @PatchMapping("/{id}")
    public int completeOrder(@PathVariable("id") Long id,
                             @RequestParam("type") String type) {
        return ordersService.completeOrder(id, type);
    }
}
