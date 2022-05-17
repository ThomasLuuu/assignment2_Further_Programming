package com.example.assignment2.service;


import com.example.assignment2.entity.Booking;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Invoice;
import com.example.assignment2.repository.CarRepository;
import com.example.assignment2.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class CarService {


    @Autowired
    private final CarRepository carRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

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


    public String getDay(){
        int numDate = 1;
        String result ="";
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        Map<String, Integer> carDate = new HashMap<String, Integer>();
        List<Car> cars = carRepository.findAll();
        for (Car car : cars){
            carDate.put(car.getLicensePlate(),numDate);
            numDate +=1;
        }
        for(String key : carDate.keySet()){
            result =result + key +" " + carDate.get(key) + "\n" ;
        }
        return date + "\n" +"Car    |    Days" + "\n" + result;
    }
}
