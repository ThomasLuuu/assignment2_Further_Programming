package com.example.assignment2.service;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return (Page<Customer>) customerRepository.findAllCustomer(pageable);
    }
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public Customer updateCustomer(Customer customer) {
        Customer customerExist = customerRepository.findById(customer.getCustomer_id()).orElse(null);

        customerExist.setName(customer.getName());
        customerExist.setPhone(customer.getPhone());

        return customerRepository.save(customerExist);
    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> searchCustomer(String search){return customerRepository.searchCustomer(search);}

    public List<Customer> findAll(){return customerRepository.findAll();}
}

