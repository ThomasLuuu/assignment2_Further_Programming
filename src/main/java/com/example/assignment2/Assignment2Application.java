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
		return new Configuration()
				.configure()
				.addAnnotatedClass(customer.getClass())
				.buildSessionFactory();
	}

	public static synchronized SessionFactory getSessionFactory(Car car) {
		return new Configuration()
				.configure()
				.addAnnotatedClass(car.getClass())
				.buildSessionFactory();
	}

	public static synchronized SessionFactory getSessionFactory(Driver driver) {
		return new Configuration()
				.configure()
				.addAnnotatedClass(driver.getClass())
				.buildSessionFactory();
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
			Customer customer = new Customer("c001", "Khang", "0911111");
			Customer customer1 = new Customer("c002", "Duy", "0911112");
			Customer customer2 = new Customer("c003", "Marcus", "0911113");
			Customer customer3 = new Customer("c004", "Josh", "0911114");

			session.beginTransaction();
			session.save(customer);
			session.save(customer1);
			session.save(customer2);
			session.save(customer3);
			session.getTransaction().commit();
			System.out.println("Customers added successfully!");
		}

		try (carFactory; Session session = carFactory.getCurrentSession()) {
			Car car1 = new Car( "50E-23122 ", "Germany",
					"SUV", "Black", "yes", "B", "61A-10319", 10.0);
			Car car2 = new Car("51E-83726", "Germany",
					"SUV", "White", "no", "C", "61A-10319",10.6);
			Car car3 = new Car("50E-72637", "Italy",
					"SUV", "Blue", "yes", "B", "61A-10319", 9.8);
			Car car4 = new Car("51E-82591", "China",
					"SUV", "Red", "no", "A", "61A-10319", 6.5);

			session.beginTransaction();
			session.save(car1);
			session.save(car2);
			session.save(car3);
			session.save(car4);
			session.getTransaction().commit();
			System.out.println("Cars added successfully");
		}

		try (driverFactory; Session session = driverFactory.getCurrentSession()) {
			Driver driver1 = new Driver("d001", "12345", "90867474", 3.1);
			Driver driver2 = new Driver("d002", "33456", "90006700", 4.0);
			Driver driver3 = new Driver("d003", "45698", "90003065", 3.9);
			Driver driver4 = new Driver("d004", "78659", "90078695", 2.1);

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
