package com.example.assignment2;

import com.example.assignment2.controller.BookingController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.BookingRepository;
import com.example.assignment2.service.BookingService;
import com.example.assignment2.service.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import net.minidev.json.JSONObject;
import org.junit.Assert;
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

    Booking booking1 = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,null,car1);
    Booking booking2 = new Booking("New York","China","1:00","24:00",2222,null,car2);
    Booking booking3 = new Booking("Wakanda","Lost Kingdom","1:00","2:00",69.69,null,car3);
    Booking bookingNew = new Booking("Su Van Hanh","Lost Kingdom","1:00","2:00",69.69,null,car3);

    LocalDate localDate1 = LocalDate.now();
    LocalDate localDate2 = LocalDate.now();
    LocalDate localDate3 = LocalDate.now();
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

        //Test Data
        PageRequest pageableDataTest = PageRequest.of(0, 3);
        when(repository.findALlBooking(pageableDataTest)).thenReturn(new PageImpl<>(Arrays.asList(booking1,booking2,booking3)));
        Page<Booking> pageBooking = bookingService.findAllBooking(pageableDataTest);
        System.out.println(pageBooking.getContent());
        Assert.assertEquals("Sai Gon",pageBooking.getContent().get(0).getStatLocation());
        Assert.assertEquals("New York",pageBooking.getContent().get(1).getStatLocation());
        Assert.assertEquals("Wakanda",pageBooking.getContent().get(2).getStatLocation());
//        Assert.assertEquals("Wakandas",pageBooking.getContent().get(2).getStatLocation()); // Wrong result, Uncomment and run this function, then debug
    }

    @Test
    public void testFindBookingById() throws Exception{
        when(bookingService.findBookingById(1L)).thenReturn(booking1);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/{id}",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));

        //Test Data
        Booking booking = bookingService.findBookingById(1L);
        Assert.assertEquals("Sai Gon", booking.getStatLocation());
//        Assert.assertEquals("SaiGon", booking.getStatLocation()); // Wrong result, Uncomment and run this function, then debug

    }
    @Test
    public void testFindCarByStatus() throws Exception{
        List<Car> carList = new ArrayList<>(Arrays.asList(car1,car2,car3));
        List<Car> carsAvailable = new ArrayList<>();
        List<Car> carsNotAvailable = new ArrayList<>();
        for (Car car: carList
             ) {
            if (car.isStatus()){
                carsAvailable.add(car);
            }else {
                carsNotAvailable.add(car);
            }
        }
        when(carService.findCarByStatusTrue()).thenReturn(carsAvailable);
        mockMvc.perform(MockMvcRequestBuilders.get("/addbooking/{bookingID}/available",1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{}]"));

        //Test Data
        List<Car> carTest = carService.findCarByStatusTrue();
        Assert.assertTrue(carTest.get(0).isStatus());
        // Check if car status is false
        Assert.assertFalse(carsNotAvailable.get(0).isStatus());
//        Assert.assertFalse(carTest.get(0).isStatus()); // Wrong result, Uncomment and run this function, then debug
    }
    @Test
    public void testFilterBookingByDate() throws Exception{
        booking1.setDate(localDate1);
        booking2.setDate(localDate2.plusMonths(1L));
        booking3.setDate(localDate3.plusYears(1L)); // 16-5-2023
        List<Booking> bookingList = new ArrayList<>(Arrays.asList(booking1,booking2)); // from 1-1-2022 to 1-1-2023
        int startDay = 1;int endDay =1 ;int startMonth = 1;int endMonth = 1;
        int startYear = 2022;
        int endYear = 2023;
        LocalDate start = LocalDate.of(startYear,startMonth,startDay);
        LocalDate end = LocalDate.of(endYear,endMonth,endDay);
        when(bookingService.filterBooking(start,end)).thenReturn(bookingList);
        mockMvc.perform(MockMvcRequestBuilders.get("/booking/filter/{dayStart},{monthStart},{yearStart}/{dayEnd},{monthEnd},{yearEnd}",1,1,2022,1,1,2023)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));

        //Test Data if date is match
        LocalDate expectedDate1 = LocalDate.of(2022,5,16);
        LocalDate expectedDate2 = LocalDate.of(2022,6,16);
        LocalDate expectedDate3 = LocalDate.of(2023,5,16); // Test out of bound filter
        when(repository.findByDateBetween(start,end)).thenReturn(Arrays.asList(booking1,booking2,booking3));
        List<Booking> newListBooking = bookingService.filterBooking(start,end);
        Assert.assertEquals(expectedDate1,newListBooking.get(0).getDate());
        Assert.assertEquals(expectedDate2,newListBooking.get(1).getDate());
        Assert.assertEquals(expectedDate3,booking3.getDate());
        // There is 1 booking not in filter => Size will = 2
        Assert.assertEquals(2,newListBooking.size());

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

        //Test Data
        Booking booking = bookingService.saveBooking(bookingNew);
        Assert.assertEquals("Su Van Hanh", booking.getStatLocation());
//        Assert.assertEquals("Le Dai Hanh", booking.getStatLocation());// Wrong result, Uncomment and run this function, then debug

    }

    //Test PUT
    @Test
    public void testUpdateBooking() throws Exception{
        Customer customer = new Customer();
        Booking newBooking = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,customer,car1);
        customer.setName("Anh Duc");
        newBooking.setStatLocation("Hai Phong");
        customer.setCustomer_id(1L);
        newBooking.setCustomer(customer);
        String jsonData = objectMapper.writeValueAsString(newBooking);
//        when(bookingService.updateBooking(newBooking)).thenReturn(newBooking);
        BDDMockito.given(bookingService.updateBooking(newBooking)).willReturn(newBooking);
        mockMvc.perform(MockMvcRequestBuilders.put("/addbooking/{booker}/available/{userID}",1,1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        // Test Data
        Booking booking = bookingService.updateBooking(newBooking);
        Assert.assertEquals("Hai Phong",booking.getStatLocation());
        Assert.assertEquals("Anh Duc", booking.getCustomer().getName());
//        Assert.assertEquals("Duc Anh", booking.getCustomer().getName()); // Wrong result, Uncomment and run this function, then debug

    }



}

