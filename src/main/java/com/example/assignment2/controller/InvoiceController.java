package com.example.assignment2.controller;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.service.InvoiceService;
import com.example.assignment2.entity.Invoice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public List<Invoice> findAllInvoice(){return invoiceService.findAllInvoice();}
    @GetMapping("/{id}")
    public Optional<Invoice> findInvoiceById(@PathVariable("id") Integer id){
        System.out.println("This is: " + id);
        return invoiceService.findInvoiceById(id);

    }
    @PostMapping
    public Invoice saveInvoice(@RequestBody Invoice invoice){return invoiceService.saveInvoice(invoice);}
    @PutMapping
    public Invoice updateInvoice(@RequestBody Invoice invoice){return invoiceService.updateInvoice(invoice);}

    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteInvoice(@PathVariable("id") Integer id){
        invoiceService.deleteInvoice(id);
        return "delete successfully";

    }

}