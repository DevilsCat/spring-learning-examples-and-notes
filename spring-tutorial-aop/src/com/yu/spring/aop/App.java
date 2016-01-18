package com.yu.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

   public static void main(String[] args) {
       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/yu/spring/aop/beans.xml");
       
       // Returns the proxy contains aspect code.
       Camera camera = (Camera) context.getBean("camera");
       
       camera.snap();
       
       context.close();
   }
    
}
