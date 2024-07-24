package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.example.basic.weeklyQuiz.menu.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final MenuRepository menuRepository;
    @Autowired
    public OrdersService(OrdersRepository ordersRepository, MenuRepository menuRepository) {
        this.ordersRepository = ordersRepository;
        this.menuRepository = menuRepository;
    }


    public void makeOrder(OrdersDTO ordersDTO) {
        Menu findMenu = findMenu(ordersDTO.getMenuId());

        ordersRepository.save(OrdersDTO.toOrders(ordersDTO, findMenu));

    }

    private Menu findMenu(Long ordersId) {
        Menu findMenu = menuRepository.findById(ordersId)
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
        return findMenu;
    }

    public int completeOrder(Long id, String type) {
        if (type.equals("완료")) {
            Orders order = ordersRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
            order.changeState("완료");
            Menu findMenu = findMenu(order.getMenu().getMenuId());

            int totalPrice = ordersRepository.findMenuByOrdersId(order).stream()
                    .mapToInt(Menu::getPrice).sum();
            findMenu.setPrice(totalPrice);
            return totalPrice;
        } else if (type.equals("취소")) {
            Orders order = ordersRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
            order.changeState("취소");
        }
        return -1;
    }
}
