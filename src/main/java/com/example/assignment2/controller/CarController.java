package com.example.assignment2.controller;



import com.example.assignment2.service.CarService;
import com.example.assignment2.entity.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/car")
public class CarController {
    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping
    public List<Car> findAllCar(){
        List <Car> carList =  carService.findAllCar();
        Collections.sort(carList, Comparator.comparingLong(Car::getID));
        return carList;
    }

    @GetMapping( "/{id}")
    public Optional<Car> findCarById(@PathVariable("id") Long id){
        System.out.println("this is: " + id);
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

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCarById(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return "delete succesfully";
    }
}
