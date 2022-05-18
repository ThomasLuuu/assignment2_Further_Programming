package com.example.assignment2.controller;
import com.example.assignment2.controller.CarController;
import com.example.assignment2.entity.*;
import com.example.assignment2.service.CarService;
import com.example.assignment2.service.InvoiceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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

    Invoice invoice1 = new Invoice(9,customer1, driver1);
    Invoice invoice2 = new Invoice(10, customer2, driver2);
    Invoice invoice3 = new Invoice(11,customer3, driver3);


    List<Invoice> invoiceList = new ArrayList<>(Arrays.asList(invoice1,invoice2,invoice3));
    List<Invoice> filteredInvoice = new ArrayList<>();
    List<Invoice> filteredInvoiceByDriverId = new ArrayList<>();
    List<Invoice> filteredInvoiceByCustomerId = new ArrayList<>();

    LocalDate localDate1 = LocalDate.of(2022,5,17);
    LocalDate localDate2 = LocalDate.of(2022,5,17);
    LocalDate localDate3 = LocalDate.of(2022,5,17);
    LocalDate start = LocalDate.of(2022,1,1);
    LocalDate end = LocalDate.of(2023,1,1);

    @Before
    public void setUp(){
        invoice1.setDate(localDate1);
        invoice2.setDate(localDate2);
        invoice3.setDate(localDate3.plusYears(1L));
        invoice1.getCustomer().setCustomer_id(1L);
        invoice2.getCustomer().setCustomer_id(1L);
        invoice3.getCustomer().setCustomer_id(1L);
        invoice1.getDriver().setDriver_id(1L);
        invoice2.getDriver().setDriver_id(1L);
        invoice3.getDriver().setDriver_id(1L);
        //Filter invoice
        for (Invoice invoice:invoiceList
        ) {
            if (invoice.getDriver().getDriver_id() == 1L && invoice.getDate().isBefore(end) && invoice.getDate().isAfter(start)){
                filteredInvoice.add(invoice);
            }
        }
        //Filter invoice by driver ID
        for (Invoice invoice:invoiceList
        ) {
            if (invoice.getDriver().getDriver_id() == 1L && invoice.getDate().isBefore(end) && invoice.getDate().isAfter(start)){
                filteredInvoiceByDriverId.add(invoice);
            }
        }
        //Filter invoice by customer ID
        for (Invoice invoice:invoiceList
        ) {
            if (invoice.getCustomer().getCustomer_id() == 1L && invoice.getDate().isBefore(end) && invoice.getDate().isAfter(start)){
                filteredInvoiceByCustomerId.add(invoice);
            }
        }
    }
    @Test
    public void testFilterInvoice() throws Exception {
        when(service.filterInvoice(start,end)).thenReturn(filteredInvoice);
        mvc.perform(MockMvcRequestBuilders.get("/invoice/filter/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,2022,1,1,2023)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));
    }

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

    @Test
    public void testFilterInvoiceByCustomer() throws Exception {
        when(service.customerIn(1L,start,end)).thenReturn(filteredInvoiceByCustomerId);
        mvc.perform(MockMvcRequestBuilders.get("/invoice/customer/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,1,2022,1,1,2023)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void testFilterInvoiceByDriver() throws Exception {
        when(service.customerIn(1L,start,end)).thenReturn(filteredInvoiceByDriverId);
        mvc.perform(MockMvcRequestBuilders.get("/invoice/customer/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,1,2022,1,1,2023)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void testRevenueByDriver() throws Exception{
        int revenue = 0;
        for (Invoice invoice: filteredInvoiceByDriverId
             ) {
            revenue += invoice.getTotal_charge();
        }
        String revenueString = Integer.toString(revenue);

        when(service.revenueDriver(1L,start,end)).thenReturn("Total revenue is " + revenueString);
        mvc.perform(MockMvcRequestBuilders.get("/invoice/revenue/driver/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,1,2022,1,1,2023)
                .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andExpect(content().string("Total revenue is " + revenueString));
    }

    @Test
    public void testRevenueByCustomer() throws Exception{
        int revenue = 0;
        for (Invoice invoice: filteredInvoiceByCustomerId
        ) {
            revenue += invoice.getTotal_charge();
        }
        String revenueString = Integer.toString(revenue);
        when(service.revenueCustomer(1L,start,end)).thenReturn("Total revenue is " + revenueString);
        mvc.perform(MockMvcRequestBuilders.get("/invoice/revenue/customer/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,1,2022,1,1,2023)
                        .accept(MediaType.ALL))
                .andExpect(status().is(200))
                .andExpect(content().string("Total revenue is " + revenueString));
    }
}
