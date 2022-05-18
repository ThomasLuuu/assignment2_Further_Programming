package com.example.assignment2.controller;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.BookingRepository;
import com.example.assignment2.service.BookingService;
import com.example.assignment2.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
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

import java.time.LocalDate;
import java.util.*;

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

    @Mock
    private BookingRepository repository;

    Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,null);
    Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,null);
    Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,null);

    Booking booking1 = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,null,car1,null);
    Booking booking2 = new Booking("New York","China","1:00","24:00",2222,null,car2,null);
    Booking booking3 = new Booking("Wakanda","Lost Kingdom","1:00","2:00",69.69,null,car3,null);
    Booking bookingNew = new Booking("Su Van Hanh","Lost Kingdom","1:00","2:00",69.69,null,null,null);

    LocalDate localDate1 = LocalDate.of(2022,5,17);
    LocalDate localDate2 = LocalDate.of(2022,5,17);
    LocalDate localDate3 = LocalDate.of(2022,5,17);
    LocalDate start = LocalDate.of(2022,1,1);
    LocalDate end = LocalDate.of(2023,1,1);

    List<Booking> bookingList = new ArrayList<>(Arrays.asList(booking1,booking2,booking3));
    List<Booking> filteredBooking = new ArrayList<>();
    List<Car> carList = new ArrayList<>(Arrays.asList(car1,car2,car3));
    List<Car> carsAvailable = new ArrayList<>();

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
        for (Car car: carList
        ) {
            if (!car.isStatus()){
                carsAvailable.add(car);
            }
        }
    }


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
        when(bookingService.findBookingById(1L)).thenReturn(booking1);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/{id}",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));

    }

    @Test
    public void testFindCarByStatus() throws Exception{
        when(carService.findCarByStatusTrue()).thenReturn(carsAvailable);
        mockMvc.perform(MockMvcRequestBuilders.get("/addbooking/available",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void testFilterBookingByDate() throws Exception{
        when(bookingService.filterBooking(start,end)).thenReturn(filteredBooking);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/filter/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,2022,1,1,2023)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));
    }

    //Test POST
    @Test
    public void testAddBooking() throws Exception{
        String json = objectMapper.writeValueAsString(bookingNew);
        when(bookingService.saveBooking(bookingNew)).thenReturn(bookingNew);
        mockMvc.perform(MockMvcRequestBuilders.post("/addbooking")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    //Test PUT
    @Test
    public void testUpdateBooking() throws Exception{
        Customer customer = new Customer();
        Booking newBooking = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,customer,car1,null);
        customer.setName("Anh Duc");
        newBooking.setStatLocation("Hai Phong");
        customer.setCustomer_id(1L);
        newBooking.setCustomer(customer);
        String jsonData = objectMapper.writeValueAsString(newBooking);
//        when(bookingService.updateBooking(newBooking)).thenReturn(newBooking);
        BDDMockito.given(bookingService.updateBooking(newBooking)).willReturn(newBooking);
        mockMvc.perform(MockMvcRequestBuilders.put("/addbooking/available/true",1,1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}

