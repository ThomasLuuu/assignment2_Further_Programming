package com.example.assignment2.controller;



import com.example.assignment2.service.CarService;
import com.example.assignment2.entity.Car;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping
    public List<Car> findAllCar(){
        return carService.findAllCar();
    }

    @GetMapping( "/{id}")
    public Optional<Car> findCarById(@PathVariable("id") String id){
        System.out.println(id);
        return carService.findCarById(id);
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car){
        return carService.saveCar(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable("id") String id){
        carService.deleteCar(id);
    }


}
