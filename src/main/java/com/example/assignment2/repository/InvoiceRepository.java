package com.example.assignment2.repository;
import com.example.assignment2.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
    Optional<Invoice> findById(Integer id);
}
