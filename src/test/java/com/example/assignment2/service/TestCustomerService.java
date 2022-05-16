package com.example.assignment2.service;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CustomerRepository;
import com.example.assignment2.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestCustomerService {
    @InjectMocks
    private CustomerService service;
    @Mock
    private CustomerRepository repository;


    @Test
    public void findCustomerTest() {
        Mockito.when(repository.findById(1L)).thenReturn(
                Optional.of(new Customer("Thomas", "")));

        Customer customer = service.findCustomerById(1L);

        assertEquals("Thomas", customer.getName());
    }

    @Test
    public void findAllCustomerTest(){
        PageRequest pageable = PageRequest.of(0, 3);
        Mockito.when(repository.findAllCustomer(pageable)).thenReturn(new PageImpl<>(Arrays.asList(
                new Customer("Thomas",""),
                new Customer("Duc",""),
                new Customer("Tin","")
        )));
        Page<Customer> pageRes = service.findAllCustomer(pageable);
        assertEquals(3,pageRes.getContent().size());
        assertEquals("Thomas",pageRes.getContent().get(0).getName());
        assertEquals("Duc",pageRes.getContent().get(1).getName());
        assertEquals("Tin",pageRes.getContent().get(2).getName());
    }

    @Test
    public void searchCustomerTest(){
        Mockito.when(repository.searchCustomer("Duc")).thenReturn(Arrays.asList(
                new Customer("Duc","")
        ));
        List<Customer> customerList = service.searchCustomer("Duc");
        assertEquals("Duc",customerList.get(0).getName());
    }
}
