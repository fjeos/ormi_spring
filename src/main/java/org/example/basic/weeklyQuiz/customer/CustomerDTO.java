package org.example.basic.weeklyQuiz.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String name;
    private String phoneNumber;
    private String address;

    public static Customer toCustomer(CustomerDTO storeDTO) {
        return Customer.builder()
                .name(storeDTO.getName())
                .phoneNumber(storeDTO.getPhoneNumber())
                .address(storeDTO.getAddress())
                .build();
    }

    public static CustomerDTO toCustomerDTO(Customer customer) {
        return CustomerDTO.builder()
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }
}
