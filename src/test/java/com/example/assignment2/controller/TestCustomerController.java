package com.example.assignment2.controller;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CustomerRepository;
import com.example.assignment2.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
//@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestCustomerController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Mock
    private CustomerRepository repository;

    Customer customer1 = new Customer("Thomas", "0833386258");
    Customer customer2 = new Customer("Duc", "0933993399");
    Customer customer3 = new Customer("Tin", "069696969");
    Customer newCus = new Customer("Khang","");

   //Get Request
    @Test
    public void findCustomerByIdTest() throws Exception {
        when(customerService.findCustomerById(1L)).thenReturn(customer1);
        mvc.perform(MockMvcRequestBuilders
                .get("/customer/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    public void testFindAllCustomer() throws Exception {
        //Test request
        PageRequest pageable = PageRequest.of(0, 3);
        List<Customer> bookingList = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));
        Page<Customer> pageRes = new PageImpl<>(bookingList);
        when(customerService.findAllCustomer(pageable)).thenReturn(pageRes);
        mvc.perform(MockMvcRequestBuilders.get("/customer/page/{pageSize},{pageNo}",3,0).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{},{}]"));

    }

    @Test
    public void testSearchCustomer() throws Exception{
        //Test Request
        List<Customer> customerList = new ArrayList<>(Arrays.asList(customer2));
        when(customerService.searchCustomer("Duc")).thenReturn(customerList);
        mvc.perform(MockMvcRequestBuilders.get("/search/{searchInput}","Duc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{}]"));
    }

    //Test POST Request
    @Test
    public void testAddCustomer() throws Exception{
        String json = objectMapper.writeValueAsString(newCus);
        when(customerService.saveCustomer(newCus)).thenReturn(newCus);
        mvc.perform(MockMvcRequestBuilders.post("/addcustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    //Test PUT Request
    @Test
    public void testUpdateCustomer() throws Exception{
        Customer customer = new Customer();
        customer.setName("Trung Tin");
        String json = objectMapper.writeValueAsString(customer);
        when(customerService.saveCustomer(newCus)).thenReturn(customer);
        mvc.perform(MockMvcRequestBuilders.put("/update/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}
