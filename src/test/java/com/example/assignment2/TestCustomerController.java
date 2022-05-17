package com.example.assignment2;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CustomerRepository;
import com.example.assignment2.repository.InvoiceRepository;
import com.example.assignment2.service.CustomerService;
import com.example.assignment2.service.InvoiceService;
import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestCustomerController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerController customerController;

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;



//    @Test
//    public void getAllRecords_success() throws Exception {
//        Customer customer1 = new Customer("Thomas","0833386258");
//        Customer customer2 = new Customer("Duc","0933993399");
//        Customer customer3 = new Customer("Tin","069696969");
//        List<Customer> records = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));
//        PageRequest page = PageRequest.of(1, 2);
//        Long id ;
//        Page<Customer> pageRes = new PageImpl<>(records);
//        when(customerRepository.findAllCustomer(page)).thenReturn(pageRes);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/customer/page/2,1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200))
//                .andExpect(jsonPath("$", hasSize(0)))
//                .andExpect((ResultMatcher) jsonPath("$[2].name", is("Duc")));
//    }

    @Test
    public void getCustomerBySearch() throws Exception{
        Customer customer1 = new Customer("Thomas","0833386258");
        Customer customer2 = new Customer("Duc","0933993399");
        Customer customer3 = new Customer("Tin","069696969");
        String url = "/search/Thomas";
        List<Customer> records = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));
        when(customerRepository.searchCustomer("Thomas")).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().is(200))
                        .andExpect((ResultMatcher) jsonPath("$.name", is("Thomas")));
//                        .andExpect(jsonPath("[0].name").value("Thomas"));



    }
}
