package com.example.assignment2.service;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;
import java.util.Date;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public Page<Booking> findAllBooking(Pageable pageable){
        return (Page<Booking>) bookingRepository.findALlBooking(pageable);

    }

    public Booking findBookingById(Long id){
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBookingById(Long id){
         bookingRepository.deleteById(id);
    }
    public Booking updateBooking(Booking booking){
        Booking existBooking = bookingRepository.findById(booking.getBooking_id()).orElse(null);
        existBooking.setCar(booking.getCar());
        existBooking.setCustomer(booking.getCustomer());
        existBooking.setDistance(booking.getDistance());
        existBooking.setDropOffDateTime(booking.getDropOffDateTime());
        existBooking.setPickUpDateTime(booking.getPickUpDateTime());
        existBooking.setEndLocation(booking.getEndLocation());
        existBooking.setStatLocation(booking.getStatLocation());
        return bookingRepository.save(existBooking);
    }

    public List<Booking> filterBooking(LocalDate startDate, LocalDate endDate){
        return bookingRepository.findByDateBetween(startDate, endDate);
    }

}
