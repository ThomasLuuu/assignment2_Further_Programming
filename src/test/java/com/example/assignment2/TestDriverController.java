package com.example.assignment2;

import com.example.assignment2.controller.DriverController;
import com.example.assignment2.controller.CustomerController;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.entity.Customer;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.service.CarService;
import com.example.assignment2.service.CustomerService;
import com.example.assignment2.service.DriverService;
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
@WebMvcTest(DriverController.class)
//@ContextConfiguration(locations = "file:web/WEB-INF/applicationContext.xml")
public class TestDriverController {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @MockBean
    private DriverService driverService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void findDriverByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/driver/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
