package com.example.assignment2.repository;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    @Query(value = "SELECT * FROM Invoice",
            countQuery = "select count(*) from Invoice ",
            nativeQuery = true)
    Page<Invoice> findAllInvoice(Pageable pageable);

    List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate);

//    @Query(value = "SELECT v FROM Invoice v where v.customer_id = :customer_id",nativeQuery = true)
//    List<Invoice> findByCustomerAndDateBetween(@Param("customer_id") Long customer_id,LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * from Invoice i where  i.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Invoice> findViaDate(LocalDate startDate, LocalDate endDate);


}
