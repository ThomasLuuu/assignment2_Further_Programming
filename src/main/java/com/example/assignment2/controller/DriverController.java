package com.example.assignment2.controller;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.service.CustomerService;
import com.example.assignment2.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/adddriver")
    public Driver adddriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @GetMapping("/driver/page/{pageSize},{pageNo}")
    public List<Driver> findDriverById(@PathVariable int pageSize, @PathVariable int pageNo) {
        PageRequest pageagle = PageRequest.of(pageNo, pageSize);
        return this.driverService.findAllDriver(pageagle).getContent();
    }

    @GetMapping("/driver/{id}")
    public Driver findDriverById(@PathVariable("id") Long id) {
        return driverService.findDriverById(id);
    }

    @DeleteMapping("/driver/delete/{id}")
    public void deleteDriver(@PathVariable("id") Long id) {
        driverService.deleteDriver(id);
    }

    @PutMapping("/update/driver")
    public Driver updateDriver(@RequestBody Driver driver) {
        return driverService.updateDriver(driver);
    }
}
