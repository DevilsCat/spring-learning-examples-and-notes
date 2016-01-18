package com.yu.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    
    /**
     * This is a dummy point cut for reusing.
     */
    @Pointcut("execution(void com.yu.spring.aop.Camera.snap())")
    public void cameraSnap() {
        
    }
    
    // Advice: advice camera's snap. 
    @Before(value = "cameraSnap()")
    public void aboutToTakePhoto() {
        System.out.println("About to take photo...");
    }
}
