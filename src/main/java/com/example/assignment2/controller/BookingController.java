package com.example.assignment2.controller;
import com.example.assignment2.entity.Car;
import com.example.assignment2.service.CarService;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CarService carService;


    @PostMapping("/addBooking")
    public Booking addBooking(@RequestBody Booking booking){
        return bookingService.saveBooking(booking);
    }
    @GetMapping("/addBooking/{bookingID}/available")
    public List<Car> findCarByStatusTrue() throws IOException {
        System.out.println("You have done this ");
        return carService.findCarByStatusTrue();
    }

    @GetMapping( "/Booking/{pageSize},{pageNo}")
    public List<Booking> findAllBooking(@PathVariable int pageSize,@PathVariable  int pageNo){
        PageRequest pageable = PageRequest.of(pageNo,pageSize);
        return this.bookingService.findAllBooking(pageable).getContent();
    }
    @PutMapping("/addBooking/{bookingID}/available/{carID}")
    public Booking updateBooking(@RequestBody Booking booking){
        return bookingService.updateBooking(booking);
    }
    @GetMapping("Booking/{id}")
    public Booking findBookingById(@PathVariable("id") Long id){
        return bookingService.findBookingById(id);
    }
    @DeleteMapping("Delete/{id}")
    public void  deleteBookingById(@PathVariable("id") Long id){
        bookingService.deleteBookingById(id);
    }
}
