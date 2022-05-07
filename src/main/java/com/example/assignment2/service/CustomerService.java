package com.example.assignment2.service;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    //Read
    public List<Customer> findAllCustomer(){return customerRepository.findAll();}
    public Optional<Customer> findCustomerById(Integer id){
        return customerRepository.findById(id);
    }
    //Create
    public Customer saveCustomer(Customer customer){return customerRepository.save(customer);}
    //Update
    public Customer updateCustomer(Customer customer){return customerRepository.save(customer);}
    //Delete
    public void deleteCustomer(Integer id){customerRepository.deleteById(id);}
}

