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

    //  METHOD CREATE BOOKING
    @PostMapping("/addbooking")
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    // METHOD READ CAR AVAILABLE WHEN BOOKING
    @GetMapping("/addbooking/available")
    public List<Car> findCarByStatusTrue() throws IOException {
        return carService.findCarByStatusTrue();
    }

    //METHOD READ BOOKING WITH PAGE SIZE & NO
    @RequestMapping(value ="/booking/page/{pageSize},{pageNo}", method = RequestMethod.GET)
    public List<Booking> findAllBooking(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        return this.bookingService.findAllBooking(pageable).getContent();
    }

    //METHOD UPDATE BOOKING WITH DRIVER INFORMATION
    @PutMapping("/addbooking/available/true")
    public Booking updateBooking(@RequestBody Booking booking) {
            return bookingService.updateBooking(booking);

    }

    //METHOD READ BOOKING WITH BOOKING'S ID
    @GetMapping("booking/{id}")
    public Booking findBookingById(@PathVariable("id") Long id) {
        return bookingService.findBookingById(id);
    }

    //METHOD READ BOOKING WITH START AND END DATE
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
