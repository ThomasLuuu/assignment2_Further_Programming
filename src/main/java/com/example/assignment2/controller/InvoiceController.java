package com.example.assignment2.controller;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.CustomerRepository;
import com.example.assignment2.service.DriverService;
import com.example.assignment2.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoice/page/{pageSize},{pageNo}")
    public List<Invoice> findInvoiceById(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageagle = PageRequest.of(pageNo, pageSize);
        return this.invoiceService.findAllInvoice(pageagle).getContent();
    }

    @GetMapping("/invoice/{id}")
    public Invoice findInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.findInvoiceById(id);
    }


    @GetMapping("invoice/filter/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}")
    public List<Invoice> filterBookingByDate(@PathVariable("dayStart") int dayStart,
                                             @PathVariable("monthStart") int monthStart,
                                             @PathVariable("yearStart") int yearStart,
                                             @PathVariable("dayEnd") int dayEnd,
                                             @PathVariable("monthEnd") int monthEnd,
                                             @PathVariable("yearEnd") int yearEnd){
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        return invoiceService.filterInvoice(start, end);
    }

    //METHOD READ INVOICE WITH CUSTOMER ID AND START/END DATE
    @GetMapping("invoice/customer/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}")
    public List<Invoice> filterInvoiceByCustomer(@PathVariable("dayStart") int dayStart,
                                              @PathVariable("monthStart") int monthStart,
                                              @PathVariable("yearStart") int yearStart,
                                              @PathVariable("dayEnd") int dayEnd,
                                              @PathVariable("monthEnd") int monthEnd,
                                              @PathVariable("yearEnd") int yearEnd,
                                                 @PathVariable("id") Long id) throws ParseException{
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        return invoiceService.customerIn(id,start,end);
    }

    //METHOD READ INVOICE WITH DRIVER ID AND START/END DATE
    @GetMapping("invoice/driver/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}")
    public List<Invoice> filterInvoiceByDriver(@PathVariable("dayStart") int dayStart,
                                                 @PathVariable("monthStart") int monthStart,
                                                 @PathVariable("yearStart") int yearStart,
                                                 @PathVariable("dayEnd") int dayEnd,
                                                 @PathVariable("monthEnd") int monthEnd,
                                                 @PathVariable("yearEnd") int yearEnd,
                                                 @PathVariable("id") Long id) throws ParseException{
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        return invoiceService.driverIn(id,start,end);
    }

    //METHOD PRINT REVENUE WITH DRIVER ID AND START/END DATE
    @GetMapping("invoice/revenue/driver/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}")
    public String filterRevenueByDriver(@PathVariable("dayStart") int dayStart,
                                               @PathVariable("monthStart") int monthStart,
                                               @PathVariable("yearStart") int yearStart,
                                               @PathVariable("dayEnd") int dayEnd,
                                               @PathVariable("monthEnd") int monthEnd,
                                               @PathVariable("yearEnd") int yearEnd,
                                               @PathVariable("id") Long id) throws ParseException{
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        return invoiceService.revenueDriver(id,start,end);
    }

    //METHOD PRINT REVENUE WITH CUSTOMER ID AND START/END DATE
    @GetMapping("invoice/revenue/customer/{id}/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}")
    public String filterRevenueByCustomer(@PathVariable("dayStart") int dayStart,
                                                 @PathVariable("monthStart") int monthStart,
                                                 @PathVariable("yearStart") int yearStart,
                                                 @PathVariable("dayEnd") int dayEnd,
                                                 @PathVariable("monthEnd") int monthEnd,
                                                 @PathVariable("yearEnd") int yearEnd,
                                                 @PathVariable("id") Long id) throws ParseException{
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        return invoiceService.revenueCustomer(id,start,end);
    }

}
