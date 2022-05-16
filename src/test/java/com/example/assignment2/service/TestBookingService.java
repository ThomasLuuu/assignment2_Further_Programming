package com.example.assignment2.service;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.repository.BookingRepository;
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

    Booking booking1 = new Booking("Sai Gon","","","",100,null,null);
    Booking booking2 = new Booking("New York","","","",100,null,null);
    Booking booking3 = new Booking("Wakanda","","","",100,null,null);
    LocalDate localDate1 = LocalDate.now();
    LocalDate localDate2 = LocalDate.now();
    LocalDate localDate3 = LocalDate.now();
    List<Booking> bookingFilteredList = new ArrayList<>(Arrays.asList(booking1,booking2)); // from 1-1-2022 to 1-1-2023



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
        booking1.setDate(localDate1);
        booking2.setDate(localDate2.plusMonths(1L));
        booking3.setDate(localDate3.plusYears(1L)); // 16-5-2023
        int startDay = 1;int endDay =1 ;int startMonth = 1;int endMonth = 1;
        int startYear = 2022;
        int endYear = 2023;
        LocalDate start = LocalDate.of(startYear,startMonth,startDay);
        LocalDate end = LocalDate.of(endYear,endMonth,endDay);
        Mockito.when(repository.findByDateBetween(start,end)).thenReturn(bookingFilteredList);
        List<Booking> filtered = bookingService.filterBooking(start,end);

        LocalDate expectedDate1 = LocalDate.of(2022,5,16);
        LocalDate expectedDate2 = LocalDate.of(2022,6,16);
        LocalDate expectedDate3 = LocalDate.of(2023,5,16);
        assertEquals(expectedDate1,filtered.get(0).getDate());
        assertEquals(expectedDate2,filtered.get(1).getDate());
        assertEquals(expectedDate3,booking3.getDate());

    }

}
