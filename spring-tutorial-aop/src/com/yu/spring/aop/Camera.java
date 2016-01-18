package com.yu.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Camera {
    // Defines the join point.
    public void snap() {
        System.out.println("SNAP!");
    }
    
}
