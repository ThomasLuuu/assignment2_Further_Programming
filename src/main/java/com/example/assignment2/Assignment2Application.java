package com.example.assignment2;
import com.example.assignment2.repository.*;
import com.example.assignment2.entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Assignment2Application {



	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(Assignment2Application.class, args);
		System.out.println("The system is running!!");

		//<editor-fold desc="REPOSITORY">
		BookingRepository bookingRepository = configurableApplicationContext.getBean(BookingRepository.class);
		CarRepository carRepository = configurableApplicationContext.getBean(CarRepository.class);
		CustomerRepository customerRepository = configurableApplicationContext.getBean(CustomerRepository.class);
		DriverRepository driverRepository = configurableApplicationContext.getBean(DriverRepository.class);
		InvoiceRepository invoiceRepository = configurableApplicationContext.getBean(InvoiceRepository.class);

		//<editor-fold desc="Driver">
		Driver driver1 = new Driver("Thor","666AAA","0789789789");
		Driver driver2 = new Driver("Thanos","777PPP","0123123123");
		Driver driver3 = new Driver("Vision","999SSS","0456456456");
		List<Driver> drivers = Arrays.asList(driver1,driver2,driver3);
		driverRepository.saveAll(drivers);
		//<editor-fold desc="Customer">
		Customer customer1 = new Customer("Thomas","0833386258");
		Customer customer2 = new Customer("Duc","0933993399");
		Customer customer3 = new Customer("Tin","069696969");
		List<Customer> customers = Arrays.asList(customer1,customer2,customer3);
		customerRepository.saveAll(customers);


		//		<editor-fold desc="Car">
		Car car1 = new Car("Lexus", "c1","pink","not convertible", "3.9", "61A-33333",3,false,driver1);
		Car car2 = new Car("Vios", "c200","black","not convertible", "3", "99A-99999",4,true,driver2);
		Car car3 = new Car("G63", "A8","white","not convertible", "5", "49A-5353",5,false,driver3);
		List<Car> cars = Arrays.asList(car1, car2, car3);
		carRepository.saveAll(cars);



		//<editor-fold desc="Booking">
		Invoice invoice1 = new Invoice(9,customer1,driver1);
		Invoice invoice2 = new Invoice(10, customer2,driver2);
		Invoice invoice3 = new Invoice(11, customer3, driver3);
		List<Invoice> invoices = Arrays.asList(invoice1, invoice2, invoice3);
		invoiceRepository.saveAll(invoices);

		//<editor-fold desc="Booking">
		Booking booking1 = new Booking("Sai Gon","Ha Noi","12:00","22:00",1000,customer1,car1,invoice1);
		Booking booking2 = new Booking("New York","China","1:00","24:00",2222,customer2,car2, invoice2);
		Booking booking3 = new Booking("Wakanda","Lost Kingdom","1:00","2:00",69.69,customer3,car3, invoice3);
		List<Booking> bookings = Arrays.asList(booking1,booking2,booking3);
		bookingRepository.saveAll(bookings);
	}





}
