package com.yu.spring.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yu.spring.camera.accessories.Lens;

public class App {

   public static void main(String[] args) {
       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/yu/spring/aop/beans.xml");
       
       // Returns the proxy contains aspect code.
       ICamera camera = (ICamera) context.getBean("camera");
       
       Lens lens = (Lens)context.getBean("lens");
       
       camera.snap();
       camera.snap(1000);
       camera.snap("Progue castle");
       camera.snapNighttime();
       camera.snap(500.0, 2);
       camera.snap(400, 2.0);
       lens.zoom(5);
       
       context.close();
   }
    
}
