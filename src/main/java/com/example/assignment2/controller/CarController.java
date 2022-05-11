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

    @PostMapping("/addcar")
    public Car addCar(@RequestBody Car car){
        return carService.saveCar(car);
    }

    @GetMapping( "/car/page/{pageSize},{pageNo}")
    public List<Car> findAllCar(@PathVariable int pageSize,@PathVariable  int pageNo){
        PageRequest pageable = PageRequest.of(pageNo,pageSize);
        return this.carService.findAllCar(pageable).getContent();
    }

    @GetMapping("car/{id}")
    public Car findCarById(@PathVariable("id") Long id){
        return carService.findCarById(id);
    }
    @DeleteMapping("deletecar/{id}")
    public void  deleteCarById(@PathVariable("id") Long id){
        carService.deleteCar(id);
    }
    @PutMapping("/update/car")
    public Car updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }
}

