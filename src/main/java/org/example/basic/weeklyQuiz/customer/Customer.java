package org.example.basic.weeklyQuiz.customer;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column
    private String name;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    public void updateCustomer(CustomerDTO customerDTO){
        this.name = customerDTO.getName();
        this.phoneNumber = customerDTO.getPhoneNumber();
        this.address = customerDTO.getAddress();
    }
}
