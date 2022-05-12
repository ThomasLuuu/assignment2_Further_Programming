package com.example.assignment2.service;


import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Car;
import com.example.assignment2.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
    public Page<Car> findAllCar(Pageable pageable){
        return (Page<Car>) carRepository.findAllCar(pageable);

    }
    public Car  findCarById(Long id){
        return carRepository.findById(id).orElse(null);
    }
    //Create
    public Car saveCar(Car car){
        return carRepository.save(car);
    }
    //Update
    public Car updateCar(Car car){
        Car carExist = carRepository.findById(car.getCar_id()).orElse(null);
        carExist.setColor(car.getColor());
        carExist.setConvertible(car.getConvertible());
        carExist.setDriver(car.getDriver());
        carExist.setLicensePlate(car.getLicensePlate());
        carExist.setModel(car.getModel());
        carExist.setMake(car.getMake());
        carExist.setRatePerKm(car.getRatePerKm());
        carExist.setRating(car.getRating());
        carExist.setStatus(car.isStatus());
        return carRepository.save(carExist);
    }
    public Car updateDriver(Car car){
        Car carExist = carRepository.findById(car.getCar_id()).orElse(null);
        carExist.setDriver(car.getDriver());
        return carRepository.save(carExist);


    }
    //Delete
    public void deleteCar(Long id){
        carRepository.deleteById(id);
    }
    public List<Car> findCarByStatusTrue(){return carRepository.findCarViaStatus();}

    public List<Car> findCarByNullDriver(){return  carRepository.findCarNullDriver();}

//    public void updateCarDriver(){return carRepository.updateCarDriver(Long id, Car car)}

}
