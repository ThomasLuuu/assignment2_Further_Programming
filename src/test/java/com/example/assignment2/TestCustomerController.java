package com.example.assignment2;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
//@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestCustomerController {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void findCustomerByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/customer/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindAllCustomer() throws Exception {
        PageRequest pageable = PageRequest.of(0, 3);
        Customer customer1 = new Customer("Thomas", "0833386258");
        Customer customer2 = new Customer("Duc", "0933993399");
        Customer customer3 = new Customer("Tin", "069696969");
        List<Customer> bookingList = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));
        Page<Customer> pageRes = new PageImpl<>(bookingList);
        when(customerService.findAllCustomer(pageable)).thenReturn(pageRes);
        mvc.perform(MockMvcRequestBuilders.get("/customer/page/3,0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

//    @Test
//    public void deleteCustomerByIdTest() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.delete("/customer/delete/{id}", 1))
//                .andExpect(status().isAccepted());
//    }
}
