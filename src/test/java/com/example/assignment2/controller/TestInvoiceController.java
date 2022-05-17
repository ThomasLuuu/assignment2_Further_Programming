package com.example.assignment2.controller;
import com.example.assignment2.controller.CarController;
import com.example.assignment2.entity.*;
import com.example.assignment2.service.CarService;
import com.example.assignment2.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class TestInvoiceController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private InvoiceService service;

    //Setup
    Customer customer1 = new Customer("Thomas","0833386258");
    Customer customer2 = new Customer("Duc","0933993399");
    Customer customer3 = new Customer("Tin","069696969");

    Driver driver1 = new Driver("Thor","666AAA","0789789789");
    Driver driver2 = new Driver("Thanos","777PPP","0123123123");
    Driver driver3 = new Driver("Vision","999SSS","0456456456");

    Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,driver1);
    Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,driver2);
    Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,driver3);

    Booking booking1 = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,customer1,car1);
    Booking booking2 = new Booking("New York","China","1:00","24:00",2222,customer2,car2);
    Booking booking3 = new Booking("Wakanda","Lost Kingdom","1:00","2:00",69.69,customer3,car3);

    Invoice invoice1 = new Invoice(9,customer1, driver1, booking1);
    Invoice invoice2 = new Invoice(10, customer2, driver2,booking2);
    Invoice invoice3 = new Invoice(11,customer3, driver3, booking3);

    List<Invoice> invoiceList = new ArrayList<>(Arrays.asList(invoice1,invoice2,invoice3));

    LocalDate localDate1 = LocalDate.now();
    LocalDate localDate2 = LocalDate.now();
    LocalDate localDate3 = LocalDate.now();

    @Test
    public void testFindInvoiceById() throws Exception{
        invoice1.setInvoice_id(1L);
        when(service.findInvoiceById(1L)).thenReturn(invoice1);
        mvc.perform(MockMvcRequestBuilders.get("/invoice/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));
    }
    @Test
    public void testFindAllInvoice() throws Exception {
        PageRequest pageable = PageRequest.of(0, 3);
        when(service.findAllInvoice(pageable)).thenReturn(new PageImpl<>(invoiceList));
        mvc.perform(MockMvcRequestBuilders.get("/invoice/page/{pageSize},{pageNo}",3,0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{},{}]"));
    }



}
