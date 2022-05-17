package com.example.assignment2.controller;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.service.CarService;
import com.example.assignment2.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    //METHOD CREATE DRIVER
    @PostMapping("/adddriver")
    public Driver adddriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    //METHOD READ DRIVER WITH PAGE SIZE & NO
    @GetMapping("/driver/page/{pageSize},{pageNo}")
    public List<Driver> findDriverById(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageagle = PageRequest.of(pageNo, pageSize);
        return this.driverService.findAllDriver(pageagle).getContent();
    }

    //METHOD READ CAR AVAILABLE FOR DRIVER REGISTER
    @GetMapping("driver/sign/available")
    public List<Car> signUpCar() throws IOException {
        return carService.findCarByNullDriver();
    }

    //METHOD SIGN UP CAR BY DRIVER
    @PutMapping("/driver/sign/available/{id}")
    public Car updateCar(@RequestBody Car car,
                         @PathVariable("id") Long id){
        return carService.updateDriver(car);
    }

    //METHOD READ DRIVER WITH ID
    @GetMapping("/driver/{id}")
    public Driver findDriverById(@PathVariable("id") Long id) {
        return driverService.findDriverById(id);
    }

    //METHOD DELETE DRIVER
    @DeleteMapping("/driver/delete/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        driverService.deleteDriver(id);
    }

    //METHOD UPDATE DRIVER
    @PutMapping("/update/driver")
    public Driver updateDriver(@RequestBody Driver driver) {
        return driverService.updateDriver(driver);
    }
}
