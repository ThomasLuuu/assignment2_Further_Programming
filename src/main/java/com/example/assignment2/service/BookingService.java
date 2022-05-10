package com.example.assignment2.service;

import com.example.assignment2.entity.Booking;
import com.example.assignment2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepository;
    public BookingService(BookingRepository bookingRepository){
        this.bookingRepository = bookingRepository;
    }
    //Read
    public List<Booking> findAllBooking(){return bookingRepository.findAll();}
    public Optional<Booking> findBookingById(Long id){
        return bookingRepository.findById(id);
    }
    //Create
    public Booking saveBooking(Booking booking){return bookingRepository.save(booking);}

    //Update
    public  Booking updateBooking(Booking booking){return bookingRepository.save(booking);}
    //Delete
    public void  deleteBooking(Long id){
        bookingRepository.deleteById(id);
    };



}
