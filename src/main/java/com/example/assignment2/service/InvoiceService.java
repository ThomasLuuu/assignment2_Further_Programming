package com.example.assignment2.service;

import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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


}
