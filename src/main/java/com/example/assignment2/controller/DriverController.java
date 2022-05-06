package com.example.assignment2.controller;
import com.example.assignment2.service.DriverService;
import com.example.assignment2.entity.Driver;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driver")

public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService= driverService;
    }

    @GetMapping
    public List<Driver> findAllDriver(){return driverService.findAllDriver();}
    @GetMapping("/{id}")
    public Optional<Driver> findDriverById(@PathVariable("id") Integer id){
        System.out.println("This is: " + id);
        return driverService.findDriverbyId(id);

    }
    @PostMapping
    public Driver saveDriver(@RequestBody Driver driver){return driverService.saveDriver(driver);}
    @PutMapping
    public Driver updateDriver(@RequestBody Driver driver){return driverService.updateDriver(driver);}
    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable("id") Integer id) {driverService.deleteDriver(id);}
}
