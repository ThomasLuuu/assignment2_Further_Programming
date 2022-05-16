package com.example.assignment2.controller;

import com.example.assignment2.controller.DriverController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.service.CarService;
import com.example.assignment2.service.CustomerService;
import com.example.assignment2.service.DriverService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DriverController.class)
//@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestDriverController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CarService carService;

    @MockBean
    private DriverService driverService;

    @MockBean
    private CarRepository carRepository;

    Driver driver1 = new Driver("Thor","666AAA","0789789789");
    Driver driver2 = new Driver("Thanos","777PPP","0123123123");
    Driver driver3 = new Driver("Vision","999SSS","0456456456");

    Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,null);
    Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,null);
    Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,driver1);

    @Test
    public void testFindAllDriver() throws Exception{
        PageRequest pageRequest = PageRequest.of(0,3);
        List<Driver> driverList = new ArrayList<>(Arrays.asList(driver1,driver2,driver3));
        Page<Driver> driverPage = new PageImpl<>(driverList);
        when(driverService.findAllDriver(pageRequest)).thenReturn(driverPage);
        mvc.perform(MockMvcRequestBuilders.get("/driver/page/{pageSize},{pageNo}",3,0)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{},{}]"));
    }

    @Test
    public void findDriverByIdTest() throws Exception {
        when(driverService.findDriverById(1L)).thenReturn(driver1);
        mvc.perform(MockMvcRequestBuilders.get("/driver/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testSignUpCar() throws Exception{
        List<Car> carList = new ArrayList<>(Arrays.asList(car1,car2,car3));
        List<Car> carsAvailable = new ArrayList<>();
        for (Car car: carList
        ) {
            if (car.getDriver() == null){
                carsAvailable.add(car);
            }
        }
        when(carService.findCarByNullDriver()).thenReturn(carsAvailable);
        mvc.perform(MockMvcRequestBuilders.get("/driver/sign/available")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{}]"));
    }

    @Test
    public void testAddDriverToCar()throws Exception{
        String json = objectMapper.writeValueAsString(car1);
        Car newCar1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,null);
        newCar1.setDriver(driver2);
        when(carService.updateCar(car1)).thenReturn(newCar1);
        mvc.perform(MockMvcRequestBuilders.put("/driver/sign/available/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
    @Test
    public void testUpdateDriver()throws Exception{
        String json = objectMapper.writeValueAsString(driver3);
        Driver newDriver = new Driver("Vision","999SSS","0456456456");
        newDriver.setName("Dr.Strange");
        when(driverService.updateDriver(driver3)).thenReturn(newDriver);
        mvc.perform(MockMvcRequestBuilders.put("/update/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testAddDriver()throws Exception{
        Driver newDriver = new Driver("Wanda","6666SSS","234234234");
        String json = objectMapper.writeValueAsString(newDriver);
        when(driverService.saveDriver(newDriver)).thenReturn(newDriver);
        mvc.perform(MockMvcRequestBuilders.put("/update/driver")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

}
