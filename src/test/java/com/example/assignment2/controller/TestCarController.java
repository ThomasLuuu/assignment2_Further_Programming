package com.example.assignment2.controller;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Driver;
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
@WebMvcTest(CarController.class)
public class TestCarController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CarService service;

    Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,null);
    Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,null);
    Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,null);

    @Test
    public void testFindAllCars() throws Exception{
        PageRequest pageable = PageRequest.of(0, 3);
        List<Car> carList = new ArrayList<>(Arrays.asList(car1, car2, car3));
        Page<Car> pageRes = new PageImpl<>(carList);
        when(service.findAllCar(pageable)).thenReturn(pageRes);
        mvc.perform(MockMvcRequestBuilders.get("/car/page/{pageSize},{pageNo}",3,0).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("[{},{},{}]"));
    }

    @Test
    public void testFindCarById() throws Exception{
        car1.setCar_id(1L);
        when(service.findCarById(1L)).thenReturn(car1);
        mvc.perform(MockMvcRequestBuilders.get("/car/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json("{}"));
    }

    @Test
    public void testAddCar() throws Exception{
        Car newCar = new Car("Porsche", "c1","pink","not convertible", "3.9", "61A-99999",9,false,null);
        String json = objectMapper.writeValueAsString(newCar);
        when(service.saveCar(newCar)).thenReturn(newCar);
        mvc.perform(MockMvcRequestBuilders.post("/addcar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
    @Test
    public void testUpdateCar() throws Exception{
        Car updatedCar = new Car("Porsche", "c1","pink","not convertible", "3.9", "61A-99999",9,false,null);
        String json = objectMapper.writeValueAsString(updatedCar);
        when(service.saveCar(car1)).thenReturn(updatedCar);
        mvc.perform(MockMvcRequestBuilders.put("/update/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void testGetDay() throws Exception{
        //Test request
        // Functionality will be inside service package (TestCarService.java)
        mvc.perform(MockMvcRequestBuilders.get("/usedcar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }



}
