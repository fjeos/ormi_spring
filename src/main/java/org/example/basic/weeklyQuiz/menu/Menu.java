package org.example.basic.weeklyQuiz.menu;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column
    private String menuName;

    @Column
    private String category;

    @Column
    private int price;

    @Column
    private String explanation;

    public void updateMenu(MenuDTO menuDTO) {
        this.menuName = menuDTO.getMenuName();
        this.category = menuDTO.getCategory();
        this.price = menuDTO.getPrice();
        this.explanation = menuDTO.getExplanation();
    }
}
