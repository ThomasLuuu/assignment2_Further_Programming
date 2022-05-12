package com.example.assignment2.controller;

import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.service.DriverService;
import com.example.assignment2.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/addinvoice")
    public Invoice addinvoice(@RequestBody Invoice invoice) {
        return invoiceService.saveInvoice(invoice);
    }

    @GetMapping("/invoice/page/{pageSize},{pageNo}")
    public List<Invoice> findInvoiceById(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageagle = PageRequest.of(pageNo, pageSize);
        return this.invoiceService.findAllInvoice(pageagle).getContent();
    }

    @GetMapping("/invoice/{id}")
    public Invoice findInvoiceById(@PathVariable("id") Long id) {
        return invoiceService.findInvoiceById(id);
    }


    @PutMapping("/update/invoice")
    public Invoice updateInvoice(@RequestBody Invoice invoice) {
        return invoiceService.updateInvoice(invoice);
    }
}
