package org.example.basic.weeklyQuiz.store;

import org.example.basic.weeklyQuiz.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    @Transactional(readOnly = true)
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll().stream()
                .map(StoreDTO::toStoreDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public StoreDTO findStoreById(Long id) {
        return storeRepository.findById(id)
                .map(StoreDTO::toStoreDTO)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));
    }

    public void createStore(StoreDTO storeDTO) {
        storeRepository.save(StoreDTO.toStore(storeDTO));
    }

    public void updateStore(Long id, StoreDTO storeDTO) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다."));

        store.updateStore(storeDTO);
    }


    public boolean deleteStore(Long id) {
        return storeRepository.findById(id)
                .map(store -> {storeRepository.delete(store); return true;})
                .orElse(false);
    }


}
