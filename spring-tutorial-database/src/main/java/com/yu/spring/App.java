package com.yu.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"com/yu/spring/beans/beans.xml");

		OffersDAO offersDao = (OffersDAO) context.getBean("offersDao");
		try {
			printAllOffers(offersDao);
			
			Offer offer1 = new Offer("Dave", "dave@test.com", "Coding Java");
			
			offersDao.create(offer1);
			
			printAllOffers(offersDao);
			
			Offer offer = offersDao.getOffer(2);
			System.out.println("Should be Mike: " + offer);
			
			offer.setName("Mock");
			offersDao.update(offer);
			
			System.out.println("Should be Mock: " + offersDao.getOffer(2));
			
			// Set back to Mike again.
			offer.setName("Mike");
			offersDao.update(offer);
		} catch (CannotGetJdbcConnectionException e) { // fine-grained database
														// exception handling
			System.out.println("Cannot get database connection.");
		} catch (DataAccessException e) { // course-grained database exceptions
											// handling
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}

		(((ClassPathXmlApplicationContext) context)).close();
	}

	private static void printAllOffers(OffersDAO offersDao) {
		List<Offer> offers = offersDao.getOffers();

		for (Offer offer : offers) {
			System.out.println(offer);
		}
	}
}
