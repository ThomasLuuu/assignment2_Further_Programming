package com.example.assignment2.service;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;
@Service
public class DriverService {
    @Autowired
    private final DriverRepository driverRepository;
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    public Page<Driver> findAllDriver(Pageable pageable) {
        return (Page<Driver>) driverRepository.findAllDriver(pageable);
    }
    public Driver findDriverById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    public Driver updateDriver(Driver driver) {
        Driver driverExist = driverRepository.findById(driver.getDriver_id()).orElse(null);

        driverExist.setName(driver.getName());
        driverExist.setPhone(driver.getPhone());
        driverExist.setPhone(driver.getPhone());

        return driverRepository.save(driverExist);
    }
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }


}
