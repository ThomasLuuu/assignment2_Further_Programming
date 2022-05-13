package com.example.assignment2;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.service.BookingService;
import com.example.assignment2.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookingController.class)
//@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestBookingController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookingService bookingService;

    @MockBean
    private CarService carService;

    Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,null);
    Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,null);
    Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,null);

    Booking booking1 = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,null,car1);
    Booking booking2 = new Booking("New York","China","1:00","24:00",2222,null,car2);
    Booking booking3 = new Booking("Wakanda","Lost Kingdom","1:00","2:00",69.69,null,car3);


    // Test GET
    @Test
    public void testFindAllBooking() throws Exception{
        PageRequest pageable = PageRequest.of(0, 3);
        List<Booking> bookingList = new ArrayList<>(Arrays.asList(booking1,booking2,booking3));
        Page<Booking> pageRes = new PageImpl<>(bookingList);
        when (bookingService.findAllBooking(pageable)).thenReturn(pageRes);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/page/{pageSize},{pageNo}",3,0).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{}, {}, {}]"));
    }

    @Test
    public void testFindBookingById() throws Exception{
        when(bookingService.findBookingById(Long.parseLong("1"))).thenReturn(booking1);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/{id}",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));


    }
    @Test
    public void testFindCarByStatus() throws Exception{
        List<Car> carList = new ArrayList<>(Arrays.asList(car1,car2,car3));
        List<Car> carsAvailable = new ArrayList<>();
        for (Car car: carList
             ) {
            if (car.isStatus()){
                carsAvailable.add(car);
            }
        }
        when(carService.findCarByStatusTrue()).thenReturn(carsAvailable);
        mockMvc.perform(MockMvcRequestBuilders.get("/addbooking/{bookingID}/available",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{}]"));

    }

    //Test POST
    @Test
    public void testAddBooking() throws Exception{
        Booking bookingNew = new Booking();
        String json = objectMapper.writeValueAsString(bookingNew);
        when(bookingService.saveBooking(bookingNew)).thenReturn(bookingNew);
        mockMvc.perform(MockMvcRequestBuilders.post("/addbooking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));
    }

    //Test PUT
    @Test
    public void testUpdateBooking() throws Exception{
        Booking newBooking = new Booking();
        Customer customer = new Customer();
        customer.setName("Nguyen Anh Duc");
        customer.setCustomer_id(1L);
        newBooking.setCustomer(customer);
        String json = objectMapper.writeValueAsString(newBooking);
        when(bookingService.updateBooking(newBooking)).thenReturn(newBooking);
        mockMvc.perform(MockMvcRequestBuilders.put("/addbooking/{booker}/available/{userID}",1,customer.getCustomer_id())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
                //.andExpect(jsonPath("name",is(customer.getName())));

    }



}

