package org.example.basic.weeklyQuiz.order;

import org.example.basic.weeklyQuiz.menu.Menu;
import org.example.basic.weeklyQuiz.menu.MenuRepository;
import org.example.basic.weeklyQuiz.store.Store;
import org.example.basic.weeklyQuiz.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * MenuRepository에서 Menu를 찾아 반환하는 메서드
     * @param item OrderItemDTO에서 menuId를 꺼냄
     * @return 찾은 Menu 갹채, 못 찾으면 Exception 발생
     */
    private Menu findMenu(OrderItemDTO item) {
        Menu findMenu = menuRepository.findById(item.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
        return findMenu;
    }

    /**
     * OrdersRepository에서 Orders를 찾는 메서드
     * @param orderId 찾으려는 주문의 Id
     * @return 찾은 Orders 객체, 못 찾으면 Exception 발생
     */
    private Orders getOrders(Long orderId) {
        Orders findOrder = ordersRepository.findById(orderId).
                orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        return findOrder;
    }


    /**
     * 주문을 생성하는 메서드
     * storeId로 가게를 찾고, order을 생성한 후 orderItemDTO에 담겨있는 메뉴들을 DB에 저장
     * @param storeId 주문이 들어온 가게 id
     * @param orderItemDTOS 주문한 메뉴들의 정보가 담겨있는 DTO
     */
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
            Menu findMenu = findMenu(item);
            orderItemRepository.save(OrderItem.builder()
                    .orders(order)
                    .menu(findMenu)
                    .amount(item.getAmount())
                    .build());
        }

    }

    /**
     * 주문을 수정하는 메서드
     * @param orderId 수정하려는 주문의 Id
     * @param orderItemDTOS 수정하려는 주문 정보가 담긴 DTO
     * @return 수정이 완료된 주문 정보
     */
    public List<OrderItemDTO> updateOrder(Long orderId, OrderItemDTO[] orderItemDTOS) {
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        Orders findOrder = getOrders(orderId);
        List<OrderItem> orderItemList = orderItemRepository.findByOrders(findOrder);

        for (int i = 0; i < orderItemList.size(); i++) {
            Menu menu = findMenu(orderItemDTOS[i]);
            orderItemList.get(i).updateOrderItem(findOrder, menu, orderItemDTOS[i]);
            orderItemDTOList.add(OrderItemDTO.builder()
                    .menuId(orderItemDTOS[i].getMenuId())
                    .amount(orderItemDTOS[i].getAmount())
                    .build());
        }
        return orderItemDTOList;
    }

    /**
     * 주문을 완료하는 메서드
     * orderId로 주문을 찾고, RequestParameter로 받은 type의 완료/취소 값에 따라 로직 수행
     * @param orderId 완료/취소할 주문의 id
     * @param type 주문을 완료할건지 취소할건지
     * @return 완료된 order의 총액, 취소이면 -1
     */
    public int completeOrder(Long orderId, String type) {
        // 주문을 완료함
        if (type.equals("완료")) {
            Orders order = getOrders(orderId);
            order.changeState("완료");

            // 주문에 연결되어있는 orderItem들을 가져와 총액을 계산
            List<OrderItem> orderItems = orderItemRepository.findByOrders(order);
            int totalPrice = 0;
            for (OrderItem orderItem : orderItems) {
                totalPrice += orderItem.getMenu().getPrice() * orderItem.getAmount();
            }
            order.calcTotal(totalPrice);
            return totalPrice;
        } else if (type.equals("취소")) {     // 주문을 취소함
            Orders order = getOrders(orderId);
            order.changeState("취소");
            order.calcTotal(-1);
        }
        return -1;
    }

    /**
     * 특정 기간 중 매장별 총 매출액을 조회하는 메서드
     * String으로 기간 값을 입력받아 DateTimeForamtter로 LocalDateTime으로 변환하여 전달
     * @param salesRequestDTO 조회를 시작할 날짜, 끝낼 날짜가 담겨있는 DTO
     * @return 해당 기간의 매장별 매출 총액
     */
    public List<SalesResponseInterface> getSalesByStores(SalesRequestDTO salesRequestDTO) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        LocalDateTime start = LocalDateTime.parse(salesRequestDTO.getStart(), format);
        LocalDateTime end = LocalDateTime.parse(salesRequestDTO.getEnd(), format);
        return ordersRepository.calculateSales(start, end);

    }

    /**
     * 가장 인기있는 메뉴 Top3를 조회하는 메서드
     * @return 가장 주문량이 많은 메뉴 상위 3개
     */
    public List<TopMenuInterface> getTopSalesMenus() {
        return orderItemRepository.findTop3Menus();

    }
}
