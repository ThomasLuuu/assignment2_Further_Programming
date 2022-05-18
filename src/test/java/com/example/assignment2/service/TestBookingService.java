package com.example.assignment2.service;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.repository.BookingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class TestBookingService {
    @InjectMocks
    private BookingService bookingService;
    @Mock
    private BookingRepository repository;

    Booking booking1 = new Booking("Sai Gon","","","",100,null,null,null);
    Booking booking2 = new Booking("New York","","","",100,null,null,null);
    Booking booking3 = new Booking("Wakanda","","","",100,null,null,null);
    LocalDate localDate1 = LocalDate.of(2022,5,17);
    LocalDate localDate2 = LocalDate.of(2022,5,17);
    LocalDate localDate3 = LocalDate.of(2022,5,17);
    LocalDate start = LocalDate.of(2022,1,1);
    LocalDate end = LocalDate.of(2023,1,1);

    List<Booking> bookingList = new ArrayList<>(Arrays.asList(booking1,booking2,booking3));
    List<Booking> filteredBooking = new ArrayList<>();

    @Before
    public void setUp(){
        booking1.setDate(localDate1);
        booking2.setDate(localDate2);
        booking3.setDate(localDate3.plusYears(1L));
        for (Booking booking: bookingList
        ) {
            if (booking.getDate().isBefore(end)){
                filteredBooking.add(booking);
            }
        }
    }



    @Test
    public void findAllBookingTest(){
        PageRequest pageable = PageRequest.of(0, 3);
        List<Booking> bookingList = new ArrayList<>(Arrays.asList(booking1,booking2,booking3));
        Mockito.when(repository.findALlBooking(pageable)).thenReturn(new PageImpl<>(bookingList));
        Page<Booking> pageRes = bookingService.findAllBooking(pageable);
        assertEquals(3,pageRes.getContent().size());
        assertEquals("Sai Gon", pageRes.getContent().get(0).getStatLocation());
        assertEquals("New York", pageRes.getContent().get(1).getStatLocation());
        assertEquals("Wakanda", pageRes.getContent().get(2).getStatLocation());
    }
    @Test
    public void findByDateBetweenTest(){
        Mockito.when(repository.findByDateBetween(start,end)).thenReturn(filteredBooking);
        List<Booking> filtered = bookingService.filterBooking(start,end);

        LocalDate expectedDate1 = LocalDate.of(2022,5,17);
        LocalDate expectedDate2 = LocalDate.of(2022,5,17);
        LocalDate expectedDate3 = LocalDate.of(2023,5,17);
        assertEquals(expectedDate1,filtered.get(0).getDate());
        assertEquals(expectedDate2,filtered.get(1).getDate());
        assertEquals(expectedDate3,booking3.getDate());
    }
}
