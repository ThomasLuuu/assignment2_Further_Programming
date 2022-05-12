package com.example.assignment2.service;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {

    @Autowired
    private final InvoiceRepository invoiceRepository;
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
    public Page<Invoice> findAllInvoice(Pageable pageable) {
        return (Page<Invoice>) invoiceRepository.findAllInvoice(pageable);
    }
    public Invoice findInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceExist = invoiceRepository.findById(invoice.getInvoice_id()).orElse(null);
        invoiceExist.setTotal_charge(invoice.getTotal_charge());
        return invoiceRepository.save(invoiceExist);
    }

    public List<Invoice> filterInvoice(LocalDate startDate, LocalDate endDate){
        return invoiceRepository.findByDateBetween(startDate, endDate);
    }

    public List<Invoice> customerIn(Long id, LocalDate start, LocalDate end) throws ParseException{
        List<Invoice> customerInvoice = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice: invoices){
            LocalDate checkDate = invoice.getDate();
            if(start.isBefore(checkDate) && end.isAfter(checkDate)){
                if(invoice.getCustomer().getCustomer_id().equals(id)){
                    customerInvoice.add(invoice);
                }
            }
        }
        return customerInvoice;
    }

    public List<Invoice> driverIn(Long id, LocalDate start, LocalDate end) throws ParseException{
        List<Invoice> driverInvoice = new ArrayList<>();
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice: invoices){
            LocalDate checkDate = invoice.getDate();
            if(start.isBefore(checkDate) && end.isAfter(checkDate)){
                if(invoice.getDriver().getDriver_id().equals(id)){
                    driverInvoice.add(invoice);
                }
            }
        }
        return driverInvoice;
    }

    public String revenueDriver(Long id, LocalDate start, LocalDate end) throws ParseException{
        double revenue = 0;
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice: invoices){
            LocalDate checkDate = invoice.getDate();
            if(start.isBefore(checkDate) && end.isAfter(checkDate)){
                if(invoice.getDriver().getDriver_id().equals(id)){

                    revenue += invoice.getTotal_charge();
                }
            }
        }
        return "revenue by driver's id: " + id + " from " + start + " to " + end + "is: " + revenue ;
    }
    public String revenueCustomer(Long id, LocalDate start, LocalDate end) throws ParseException{
        double revenue = 0;
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice: invoices){
            LocalDate checkDate = invoice.getDate();
            if(start.isBefore(checkDate) && end.isAfter(checkDate)){
                if(invoice.getCustomer().getCustomer_id().equals(id)){

                    revenue += invoice.getTotal_charge();
                }
            }
        }
        return "revenue by customer's id: " + id + " from " + start + " to " + end + "is: " + revenue ;
    }
}
