package com.example.assignment2.controller;

import com.example.assignment2.service.BookingService;
import com.example.assignment2.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/booking")

public class BookingController {
    @Autowired
    private final BookingService bookingService;



    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> findAllBooking(){return bookingService.findAllBooking();}

    @GetMapping("/{id}")
    public  Optional<Booking> findBookingById(@PathVariable("id") Integer id){
        System.out.println("this is: " + id);
        return bookingService.findBookingById(id);
    }

    @PostMapping
    public Booking saveBooking(@RequestBody Booking booking){return bookingService.saveBooking(booking);}

    @PutMapping
    public  Booking updateBooking(@RequestBody Booking booking){return bookingService.updateBooking(booking);}


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBooKingById(@PathVariable("id") Integer id){
        bookingService.deleteBooking(id);
        return "delete succesfully";
    }

}
