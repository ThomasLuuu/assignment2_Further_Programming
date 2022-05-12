package com.example.assignment2.repository;
import com.example.assignment2.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Driver;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT * FROM Driver",
            countQuery = "select count(*) from Driver ",
            nativeQuery = true)
    Page<Driver> findAllDriver(Pageable pageable);

}

