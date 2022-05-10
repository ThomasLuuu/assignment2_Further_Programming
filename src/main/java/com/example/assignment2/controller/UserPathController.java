package com.example.assignment2.controller;
import com.example.assignment2.service.CarService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import com.example.assignment2.service.BookingService;
import com.example.assignment2.entity.Booking;
@RestController
@RequestMapping("/user")
public class UserPathController {
    private final BookingService bookingService;

    public UserPathController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping( value = "/{id}/booking", method = RequestMethod.POST)
    @ResponseBody

    public Booking saveBooking(@RequestBody Booking booking, @PathVariable("id") String id){
        booking.setCustomerID(id);
        return bookingService.saveBooking(booking);
    }

}
