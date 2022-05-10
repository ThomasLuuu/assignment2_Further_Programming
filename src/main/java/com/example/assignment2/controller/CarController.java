package com.example.assignment2.controller;



import com.example.assignment2.service.CarService;
import com.example.assignment2.entity.Car;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/available")
    public List<Car> findCarByStatusTrue( HttpServletResponse res) throws IOException {
        System.out.println("You have done this ");
//        res.sendRedirect("localhost:8080/car");
        return carService.findCarByStatusTrue();
    }
    @GetMapping("/index")
    public String index( HttpServletResponse res) throws IOException {
        // model has the attribute 'message'
        System.out.println("try it");
        res.sendRedirect("localhost:8080/");
        return "null";
    }

}
