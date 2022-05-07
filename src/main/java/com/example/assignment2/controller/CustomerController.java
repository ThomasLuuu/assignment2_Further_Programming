package com.example.assignment2.controller;

import com.example.assignment2.service.CustomerService;
import com.example.assignment2.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping
    public List<Customer> findAllCustomer(){return customerService.findAllCustomer();}

    @GetMapping("/{id}")
    public  Optional<Customer> findCustomerById(@PathVariable("id") Integer id){
        System.out.println("This is: " + id);
        return customerService.findCustomerById(id);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer){return customerService.saveCustomer(customer);}
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){return customerService.updateCustomer(customer);}

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @DeleteMapping
    public String deleteCustomer(@PathVariable("id") Integer id){
        customerService.deleteCustomer(id);
        return "delete successfully";
    }
}
