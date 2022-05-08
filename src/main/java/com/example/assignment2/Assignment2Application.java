package com.example.assignment2;

import com.example.assignment2.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
		System.out.println("The system is running!!");

		SessionFactory factory = new Configuration()
				.configure()
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Customer customer = new Customer("s3753740", "Khang", 10009000);
			session.beginTransaction();
			session.save(customer);
			session.getTransaction().commit();
			System.out.println("Customer added successfully!");
		} finally {
			session.close();
			factory.close();
		}
	}
}
