package org.example.basic.weeklyQuiz.menu;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private String menuName;
    private String category;
    private int price;
    private String explanation;

    // Entity를 DTO로 변환하는 메서드
    public static MenuDTO toDTO(Menu menu) {
        return MenuDTO.builder()
                .menuName(menu.getMenuName())
                .category(menu.getCategory())
                .price(menu.getPrice())
                .explanation(menu.getExplanation())
                .build();
    }

    // DTO를 Entity로 변환하는 메서드
    public static Menu toMenu(MenuDTO menuDTO) {
        return Menu.builder()
                .menuName(menuDTO.getMenuName())
                .category(menuDTO.getCategory())
                .price(menuDTO.getPrice())
                .explanation(menuDTO.getExplanation())
                .build();
    }
}
