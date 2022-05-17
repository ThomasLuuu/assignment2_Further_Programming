package com.example.assignment2.service;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.BookingRepository;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.repository.InvoiceRepository;
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

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CarRepository carRepository;

    public Booking saveBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public Page<Booking> findAllBooking(Pageable pageable){
        return (Page<Booking>) bookingRepository.findALlBooking(pageable);

    }
    public void createInvoice(Invoice invoice){
        invoiceRepository.save(invoice);
    }
    public Booking findBookingById(Long id){
        return bookingRepository.findById(id).orElse(null);
    }

    public void deleteBookingById(Long id){
         bookingRepository.deleteById(id);
    }

    public void changeCarStatus(Car car){
        Car carExist = carRepository.findById(car.getCar_id()).orElse(null);
        carExist.setStatus(false);
        carRepository.save(carExist);


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
        existBooking.setInvoice(booking.getInvoice());
        existBooking.setCustomer(booking.getInvoice().getCustomer());
        createInvoice(booking.getInvoice());
        changeCarStatus(booking.getCar());
        return bookingRepository.save(existBooking);
    }

    public List<Booking> filterBooking(LocalDate startDate, LocalDate endDate){
        return bookingRepository.findByDateBetween(startDate, endDate);
    }

}
