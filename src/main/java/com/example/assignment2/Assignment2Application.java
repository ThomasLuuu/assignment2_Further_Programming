package com.example.assignment2;

import com.example.assignment2.entity.Customer;
import com.example.assignment2.entity.Car;
import com.example.assignment2.entity.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment2Application {

	public static synchronized SessionFactory getSessionFactory(Customer customer) {
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(customer.getClass())
				.buildSessionFactory();
		return factory;
	}

	public static synchronized SessionFactory getSessionFactory(Car car) {
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(car.getClass())
				.buildSessionFactory();
		return factory;
	}

	public static synchronized SessionFactory getSessionFactory(Driver driver) {
		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(driver.getClass())
				.buildSessionFactory();
		return factory;
	}

	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
		System.out.println("The system is running!!");

		//Create a dummy customer to invoke the class in getSessionFactory() method
		Customer dummyCustomer = new Customer();
		Car dummyCar = new Car();
		Driver dummyDriver = new Driver();

//		SessionFactory factory = new Configuration()
//				.configure()
//				.addAnnotatedClass(Customer.class)
//				.buildSessionFactory();

		SessionFactory customerFactory = getSessionFactory(dummyCustomer);
		SessionFactory carFactory = getSessionFactory(dummyCar);
		SessionFactory driverFactory = getSessionFactory(dummyDriver);

		try (customerFactory; Session session = customerFactory.getCurrentSession()) {
			Customer customer = new Customer("s3753740", "Khang", 10009000);
			Customer customer1 = new Customer("1000", "Duy", 10008000);
			Customer customer2 = new Customer("2000", "Marcus", 19098910);
			Customer customer3 = new Customer("3000", "Josh", 123456789);

			session.beginTransaction();
			session.save(customer);
			session.save(customer1);
			session.save(customer2);
			session.save(customer3);
			session.getTransaction().commit();
			System.out.println("Customers added successfully!");
		}

		try (carFactory; Session session = carFactory.getCurrentSession()) {
			Car car1 = new Car(100000L, "001", "dummy",
					"SUV", "black", "yes", 300000L, "61A-10319", 56000L);
			Car car2 = new Car(200000L, "001", "dummy",
					"SUV", "red", "no", 400000L, "61A-10319", 60000L);
			Car car3 = new Car(300000L, "001", "dummy",
					"SUV", "blue", "yes", 500000L, "61A-10319", 76000L);
			Car car4 = new Car(400000L, "001", "dummy",
					"SUV", "orange", "no", 600000L, "61A-10319", 90000L);

			session.beginTransaction();
			session.save(car1);
			session.save(car2);
			session.save(car3);
			session.save(car4);
			session.getTransaction().commit();
			System.out.println("Cars added successfully");
		}

		try (driverFactory; Session session = driverFactory.getCurrentSession()) {
			Driver driver1 = new Driver("1", "12345", 90867474, "3.5");
			Driver driver2 = new Driver("2", "33456", 90006700, "5.0");
			Driver driver3 = new Driver("3", "45698", 10003065, "2.7");
			Driver driver4 = new Driver("4", "78659", 20078695, "4.1");

			session.beginTransaction();
			session.save(driver1);
			session.save(driver2);
			session.save(driver3);
			session.save(driver4);
			session.getTransaction().commit();
			System.out.println("Drivers added successfully");
		}
	}
}
