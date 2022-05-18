package com.example.assignment2.service;

import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import com.example.assignment2.repository.CarRepository;
import org.junit.Assert;
import org.junit.Before;
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
public class TestCarService {
    @InjectMocks
    private CarService service;
    @Mock
    private CarRepository repository;

    Driver driver1 = new Driver("Thor","666AAA","0789789789");
    Driver driver2 = new Driver("Thanos","777PPP","0123123123");
    Driver driver3 = new Driver("Vision","999SSS","0456456456");

    Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,driver1);
    Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,driver2);
    Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,driver3);
    List<Car> carList = new ArrayList<>(Arrays.asList(car1,car2,car3));
    List<Car> availableCar = new ArrayList<>();
    List<Car> listNullDriver = new ArrayList<>();

    @Before
    public void setUp(){
        //Car status
        for (Car car:carList
        ) {
            if (!car.isStatus()){
                availableCar.add(car);
            }
        }
        // Check null driver
        for (Car car:carList
        ) {
            if (car.getDriver() == null){
                listNullDriver.add(car);
            }
        }
    }

    @Test
    public void findCarByStatusTest(){
        Mockito.when(repository.findCarViaStatus()).thenReturn(availableCar);
        List<Car> testedList = service.findCarByStatusTrue();
        Assert.assertEquals("Lexus",testedList.get(0).getMake());
        Assert.assertEquals("G63",testedList.get(1).getMake());
    }

    @Test
    public void findCarNullDriverTest(){
        Mockito.when(repository.findCarNullDriver()).thenReturn(listNullDriver);
        List<Car>testedList = service.findCarByNullDriver();
        Assert.assertEquals(0,testedList.size());
    }

    @Test
    public void findAllCarTest(){
        PageRequest pageable = PageRequest.of(0, 3);
        Mockito.when(repository.findAllCar(pageable)).thenReturn(new PageImpl<>(carList));
        Page<Car> carPage = service.findAllCar(pageable);
        Assert.assertEquals("Lexus", carPage.getContent().get(0).getMake());
        Assert.assertEquals("Vios", carPage.getContent().get(1).getMake());
        Assert.assertEquals("G63", carPage.getContent().get(2).getMake());
    }
}
