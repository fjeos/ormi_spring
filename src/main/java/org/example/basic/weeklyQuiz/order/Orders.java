package org.example.basic.weeklyQuiz.order;

import jakarta.persistence.*;
import lombok.*;
import org.example.basic.weeklyQuiz.menu.Menu;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne
    @JoinColumn(name = "MENU_ID")
    private Menu menu;

    @Column
    private String state;

    @Column
    private int totalPrice;


    public void changeState(String state) {
        this.state = state;
    }
}
