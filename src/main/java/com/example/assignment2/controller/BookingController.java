package com.example.assignment2.controller;
import com.example.assignment2.entity.Car;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.service.CarService;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CarService carService;

    public boolean checkAuthorization(String userID, String Booker) {
        boolean author = false;
        if (userID.equalsIgnoreCase(Booker)) {
            author = true;
        }
        return author;
    }


    @PostMapping("/addbooking")
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping("/addbooking/{bookingID}/available")
    public List<Car> findCarByStatusTrue() throws IOException {
        return carService.findCarByStatusTrue();
    }

    //localhost:8080/Booking/page/3,0
    @GetMapping("/booking/page/{pageSize},{pageNo}")
    public List<Booking> findAllBooking(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return this.bookingService.findAllBooking(pageable).getContent();
    }

    @PutMapping("/addbooking/{booker}/available/{userID}")
    public Booking updateBooking(@RequestBody Booking booking,
                                 @PathVariable("userID") String userID,
                                 @PathVariable("booker") String booker) {
        if (checkAuthorization(userID, booker)) {
            return bookingService.updateBooking(booking);
        } else {
            System.out.println("you dont have right to do it");
            return null;
        }
    }

    @GetMapping("booking/{id}")
    public Booking findBookingById(@PathVariable("id") Long id) {
        return bookingService.findBookingById(id);
    }

    @GetMapping("booking/filter/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}")
    public List<Booking> filterBookingByDate(@PathVariable("dayStart") int dayStart,
                                             @PathVariable("monthStart") int monthStart,
                                             @PathVariable("yearStart") int yearStart,
                                             @PathVariable("dayEnd") int dayEnd,
                                             @PathVariable("monthEnd") int monthEnd,
                                             @PathVariable("yearEnd") int yearEnd){
        LocalDate start = LocalDate.of(yearStart,monthStart,dayStart);
        LocalDate end = LocalDate.of(yearEnd,monthEnd,dayEnd);
        return bookingService.filterBooking(start, end);
    }
}
