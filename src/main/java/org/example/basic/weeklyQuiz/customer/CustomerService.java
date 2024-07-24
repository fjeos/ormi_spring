package org.example.basic.weeklyQuiz.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDTO::toCustomerDTO)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public CustomerDTO findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerDTO::toCustomerDTO)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
    }

    public void createCustomer(CustomerDTO customerDTO) {
        customerRepository.save(CustomerDTO.toCustomer(customerDTO));
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

        customer.updateCustomer(customerDTO);
    }


    public boolean deleteCustomer(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {customerRepository.delete(customer); return true;})
                .orElse(false);
    }
}
