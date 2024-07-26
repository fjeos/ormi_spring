package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.example.basic.weeklyQuiz.menu.MenuRepository;
import org.example.basic.weeklyQuiz.store.Store;
import org.example.basic.weeklyQuiz.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final MenuRepository menuRepository;
    private final OrderItemRepository orderItemRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, MenuRepository menuRepository,
                         OrderItemRepository orderItemRepository, StoreRepository storeRepository) {
        this.ordersRepository = ordersRepository;
        this.menuRepository = menuRepository;
        this.orderItemRepository = orderItemRepository;
        this.storeRepository = storeRepository;
    }

    public void makeOrder(Long storeId, OrderItemDTO[] orderItemDTOS) {
        Store findStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        Orders order = Orders.builder()
                .store(findStore)
                .createdAt(LocalDateTime.now())
                .state("접수")
                .build();
        ordersRepository.save(order);
        for (OrderItemDTO item : orderItemDTOS) {
            orderItemRepository.save(OrderItem.builder()
                    .orders(order)
                    .menu(findMenu(item.getMenuId()))
                    .amount(item.getAmount())
                    .build());
        }

    }

    private Menu findMenu(Long ordersId) {
        Menu findMenu = menuRepository.findById(ordersId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
        return findMenu;
    }

    public int completeOrder(Long orderId, String type) {
        if (type.equals("완료")) {
            Orders order = ordersRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
            order.changeState("완료");

            List<OrderItem> orderItems = orderItemRepository.findByOrders(order);
            int totalPrice = 0;
            for (OrderItem orderItem : orderItems) {
                totalPrice += orderItem.getMenu().getPrice() * orderItem.getAmount();
            }
            order.calcTotal(totalPrice);
            return totalPrice;
        } else if (type.equals("취소")) {
            Orders order = ordersRepository.findById(orderId)
                    .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
            order.changeState("취소");
        }
        return -1;
    }
}
