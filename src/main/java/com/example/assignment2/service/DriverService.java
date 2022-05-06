package com.example.assignment2.service;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DriverService {
    @Autowired
    private final DriverRepository driverRepository;
    public DriverService(DriverRepository driverRepository){
        this.driverRepository = driverRepository;
    }

    //Read
    public List<Driver> findAllDriver(){return driverRepository.findAll();}
    public Optional<Driver> findDriverbyId(Integer id){
        return driverRepository.findById(id);
    }
    //Create
    public Driver saveDriver(Driver driver){return  driverRepository.save(driver);}
    //Update
    public Driver updateDriver(Driver driver){return  driverRepository.save(driver);}
    //Delete
    public void deleteDriver(Integer id){driverRepository.deleteById(id.toString());}

}
