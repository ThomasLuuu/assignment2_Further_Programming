package com.example.assignment2.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Invoice;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    @Query(value = "SELECT * FROM Invoice",
            countQuery = "select count(*) from Invoice ",
            nativeQuery = true)
    Page<Invoice> findAllInvoice(Pageable pageable);
}
