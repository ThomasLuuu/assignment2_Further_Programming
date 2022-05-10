package com.example.assignment2.controller;
import com.example.assignment2.service.CarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserPathController {
    private final CarService carService;

    public UserPathController(CarService carService) {
        this.carService = carService;
    }


}
