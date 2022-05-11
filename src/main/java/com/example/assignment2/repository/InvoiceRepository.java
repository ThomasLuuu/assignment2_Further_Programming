package com.example.assignment2.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Invoice;
import java.util.Optional;
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    Optional<Invoice> findById(Long id);
}
