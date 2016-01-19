package com.yu.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    
    /**
     * This is a dummy point cut for reusing.
     * ".." is a wildcard for any snap() function 
     * "*" for any return type and Camera Mehods.
     */
    @Pointcut("execution(* com.yu.spring.aop.Camera.*(..))")
    public void cameraSnap() {}
    
    
    @Pointcut("target(com.yu.spring.aop.Camera)")
    public void somePointcut() {}
    
    /**
     * JointPoint stores all value passed into join cut.
     * @param jp
     */
    @Before("somePointcut()")
    public void somePointcutDemo(JoinPoint jp) {
        System.out.println("************BEFORE DEMO****************");
        
        for (Object obj : jp.getArgs()) {
            System.out.println("Arg: " + obj);
        }
    }
    
    @Pointcut("args(exposure, aperture)")
    public void someOtherPointcut(int exposure, double aperture) {}
    
    @Pointcut("target(com.yu.spring.aop.Camera)")
    public void targetCamera() {}
    
    /**
     * This is useful!
     * @param exposure
     * @param aperture
     */
    @Before("targetCamera() && someOtherPointcut(exposure, aperture)")
    public void someOtherPointcutDemo(int exposure, double aperture) {
        System.out.println("*********Get Args: BEFORE DEMO*********");
        
        System.out.printf("exposure %d, aperture %.2f\n", exposure, aperture);
    }
}
