package com.example.assignment2.controller;
import com.example.assignment2.entity.Car;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.service.CarService;
import com.example.assignment2.entity.Booking;
import com.example.assignment2.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CarController {
    @Autowired
    private CarService carService;


    //METHOD CREATE CAR
    @PostMapping("/addcar")
    public Car addCar(@RequestBody Car car){
        return carService.saveCar(car);
    }

    //METHOD READ CAR WITH PAGE SIZE & NO
    @GetMapping( "/car/page/{pageSize},{pageNo}")
    public List<Car> findAllCar(@PathVariable int pageSize,@PathVariable  int pageNo){
        PageRequest pageable = PageRequest.of(pageNo,pageSize);
        return this.carService.findAllCar(pageable).getContent();
    }

    //METHOD READ CAR WITH CAR ID
    @GetMapping("car/{id}")
    public Car findCarById(@PathVariable("id") Long id){
        return carService.findCarById(id);
    }

    //METHOD DELETE CAR WITH CAR ID
    @DeleteMapping("deletecar/{id}")
    public void  deleteCarById(@PathVariable("id") Long id){
        carService.deleteCar(id);
    }

    //METHOD UPDATE CAR
    @PutMapping("/update/car")
    public Car updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }


    //METHOD READ CARS THAT DON'T HAVE DRIVER
    @GetMapping("/usedcar")
    public String getDay(){
        return carService.getDay();
    }
}

