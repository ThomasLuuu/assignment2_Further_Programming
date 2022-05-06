package com.example.assignment2.service;

import com.example.assignment2.entity.Car;
import com.example.assignment2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private final CarRepository carRepository;
    public CarService(CarRepository carRepository){
        this.carRepository= carRepository;
    }
    //Read
    public List<Car> findAllCar(){
        return carRepository.findAll();
    }
    public Optional<Car> findCarById(Integer id){
        return carRepository.findById(id);
    }
    //Create
    public Car saveCar(Car car){
        return carRepository.save(car);
    }
    //Update
    public Car updateCar(Car car){
        return carRepository.save(car);
    }
    //Delete
    public void deleteCar(String id){
        carRepository.deleteById(id);
    }

}
