package com.example.assignment2.service;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.InvoiceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestInvoiceService {
    @InjectMocks
    private InvoiceService service;
    @Mock
    private InvoiceRepository repository;

    //Set Up
    Invoice invoice1 = new Invoice(9,null, null);
    Invoice invoice2 = new Invoice(10, null, null);
    Invoice invoice3 = new Invoice(11,null, null);

    LocalDate localDate1 = LocalDate.of(2022,5,17);
    LocalDate localDate2 = LocalDate.of(2022,5,17);
    LocalDate localDate3 = LocalDate.of(2022,5,17);
    LocalDate start = LocalDate.of(2022,1,1);
    LocalDate end = LocalDate.of(2023,1,1);

    List<Invoice> invoiceList = new ArrayList<>(Arrays.asList(invoice1,invoice2,invoice3));
    List<Invoice> filteredList = new ArrayList<>();
    @Before
    public void setUp(){
        invoice1.setDate(localDate1);
        invoice2.setDate(localDate2);
        invoice3.setDate(localDate3.plusYears(1L));
        invoice1.setCustomer(new Customer("Duc","0933993399"));
        for (Invoice invoice: invoiceList
             ) {
            if (invoice.getDate().isBefore(end)){
                filteredList.add(invoice);
            }
        }
    }

    @Test
    public void findAllInvoiceTest(){
        PageRequest pageable = PageRequest.of(0, 3);
        when(repository.findAllInvoice(pageable)).thenReturn(new PageImpl<>(invoiceList));
        Page<Invoice> pageInvoice = service.findAllInvoice(pageable);
        Assert.assertEquals(3,pageInvoice.getContent().size());
        Assert.assertNull(pageInvoice.getContent().get(0).getCustomer());
        Assert.assertNull(pageInvoice.getContent().get(1).getCustomer());
        Assert.assertNull(pageInvoice.getContent().get(2).getCustomer());
    }

    @Test
    public void findByDateBetweenTest(){
        when(repository.findByDateBetween(start,end)).thenReturn(filteredList);
        List<Invoice> testFilterInvoice = service.filterInvoice(start,end);

        LocalDate expectedDate1 = LocalDate.of(2022,5,17);
        LocalDate expectedDate2 = LocalDate.of(2022,5,17);
        LocalDate expectedDate3 = LocalDate.of(2023,5,17);

        Assert.assertEquals(2,testFilterInvoice.size());
        Assert.assertEquals(expectedDate1,testFilterInvoice.get(0).getDate());
        Assert.assertEquals(expectedDate2,testFilterInvoice.get(1).getDate());
        //Assert.assertEquals(expectedDate3,testFilterInvoice.get(1).getDate()); Wrong result because the expected date does not equal to the data date.
        Assert.assertEquals("Duc",testFilterInvoice.get(0).getCustomer().getName());
    }

}
