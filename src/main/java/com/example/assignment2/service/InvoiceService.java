package com.example.assignment2.service;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {
    @Autowired
    private final InvoiceRepository invoiceRepository;
    public InvoiceService(InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
    }
    //Read
    public List<Invoice> findAllInvoice(){return invoiceRepository.findAll();}
    public Optional<Invoice> findInvoiceById(Integer id){
        return invoiceRepository.findById(id);
    }
    //Create
    public Invoice saveInvoice(Invoice invoice){return  invoiceRepository.save(invoice);}
    //Update
    public Invoice updateInvoice(Invoice invoice){return  invoiceRepository.save(invoice);}
    //Delete
    public void deleteInvoice(Integer id){invoiceRepository.deleteById(id);}

}
