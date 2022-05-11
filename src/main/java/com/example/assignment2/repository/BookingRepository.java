package com.example.assignment2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assignment2.entity.Booking;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query(value = "SELECT * FROM Booking",
    countQuery = "select count(*) from Booking ",
    nativeQuery = true)
    Page<Booking> findALlBooking(Pageable pageable);
}
