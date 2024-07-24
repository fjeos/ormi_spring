package org.example.basic.weeklyQuiz.store;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column
    private String storeName;

    @Column
    private String storeAddress;

    @Column
    private String storeCallNumber;

    public void updateStore(StoreDTO storeDTO) {
        this.storeName = storeDTO.getStoreName();
        this.storeAddress = storeDTO.getStoreAddress();
        this.storeCallNumber = storeDTO.getStoreCallNumber();
    }
}
