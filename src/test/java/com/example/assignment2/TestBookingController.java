package com.example.assignment2;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.service.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestBookingController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;


    @Test
    public void testFindAllBooking() throws Exception{
        PageRequest pageable = PageRequest.of(0, 3);
        Booking booking1 = new Booking("Sai Gon","","","",1000,null,null,null);
        Booking booking2 = new Booking("New York","","","",2222,null,null,null);
        Booking booking3 = new Booking("Wakanda","","","",69.69,null,null,null);
        List<Booking> bookingList = new ArrayList<>(Arrays.asList(booking1,booking2,booking3));
        Page<Booking> pageRes = new PageImpl<>(bookingList);
        when (bookingService.findAllBooking(pageable)).thenReturn(pageRes);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/page/3,0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

}
