package org.example.basic.weeklyQuiz.store;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

    private String storeName;
    private String storeAddress;
    private String storeCallNumber;

    public static StoreDTO toStoreDTO(Store store) {
        return StoreDTO.builder()
                .storeAddress(store.getStoreAddress())
                .storeName(store.getStoreName())
                .storeCallNumber(store.getStoreCallNumber())
                .build();
    }
    public static Store toStore(StoreDTO storeDTO) {
        return Store.builder()
                .storeAddress(storeDTO.getStoreAddress())
                .storeName(storeDTO.getStoreName())
                .storeCallNumber(storeDTO.getStoreCallNumber())
                .build();
    }
}
