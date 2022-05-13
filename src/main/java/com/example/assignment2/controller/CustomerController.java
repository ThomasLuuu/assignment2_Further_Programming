package com.example.assignment2.controller;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.service.CarService;
import com.example.assignment2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/addcustomer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customer/page/{pageSize},{pageNo}")
    public List<Customer> findAllCustomer(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageagle = PageRequest.of(pageNo, pageSize);
        return this.customerService.findAllCustomer(pageagle).getContent();
    }

    @GetMapping("/customer/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id) {
        return customerService.findCustomerById(id);
    }

    @DeleteMapping("/customer/delete/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }

    @PutMapping("/update/customer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    
    @GetMapping("/search/{searchInput}")
    public List<Customer> searchCustomer(@PathVariable("searchInput") String searchInput){return customerService.searchCustomer(searchInput);}
}
