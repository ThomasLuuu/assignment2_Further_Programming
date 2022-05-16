package com.example.assignment2.service;

import com.example.assignment2.entity.Driver;
import com.example.assignment2.repository.DriverRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestDriverService {
    @InjectMocks
    private DriverService driverService;
    @Mock
    private DriverRepository repository;

    Driver driver1 = new Driver("Batman","","");
    Driver driver2 = new Driver("Superman","","");
    Driver driver3 = new Driver("Joker","","");

    @Test
    public void findAllDriverTest(){
        PageRequest pageable = PageRequest.of(0, 3);
        List<Driver> driverList = new ArrayList<>(Arrays.asList(driver1,driver2,driver3));
        Mockito.when(repository.findAllDriver(pageable)).thenReturn(new PageImpl<>(driverList));
        Page<Driver> pageRes = driverService.findAllDriver(pageable);
        Assert.assertEquals(3,pageRes.getContent().size());
        Assert.assertEquals("Batman",pageRes.getContent().get(0).getName());
        Assert.assertEquals("Superman",pageRes.getContent().get(1).getName());
        Assert.assertEquals("Joker",pageRes.getContent().get(2).getName());

    }
}
