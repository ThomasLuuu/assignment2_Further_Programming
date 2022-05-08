package com.example.assignment2;

import com.example.assignment2.entity.Customer;
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

	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
		System.out.println("The system is running!!");
		
		//Create a dummy customer to invoke the class in getSessionFactory() method
		Customer dummyCustomer = new Customer();

//		SessionFactory factory = new Configuration()
//				.configure()
//				.addAnnotatedClass(Customer.class)
//				.buildSessionFactory();

		SessionFactory factory = getSessionFactory(dummyCustomer);
		Session session = factory.getCurrentSession();

		try {
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
			System.out.println("Customer added successfully!");
		} finally {
			session.close();
			factory.close();
		}
	}
}
