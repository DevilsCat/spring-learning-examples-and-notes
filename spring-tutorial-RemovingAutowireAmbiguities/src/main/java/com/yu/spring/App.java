package com.yu.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
	
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context= new ClassPathXmlApplicationContext("com/yu/spring/beans/beans.xml");
		
		Logger logger = (Logger)context.getBean("logger");
		
		logger.writeConsole("Hello there");
		logger.writeFile("Hi again");
		
		(((ClassPathXmlApplicationContext)context)).close();
	}

}
