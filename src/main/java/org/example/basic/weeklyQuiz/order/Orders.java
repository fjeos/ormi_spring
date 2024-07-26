package org.example.basic.weeklyQuiz.order;

import jakarta.persistence.*;
import lombok.*;
import org.example.basic.weeklyQuiz.store.Store;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column
    private LocalDateTime createdAt;

    @Column
    private String state;

    @Column
    private int totalPrice;

    public void changeState(String state) {
        this.state = state;
    }

    public void calcTotal(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
