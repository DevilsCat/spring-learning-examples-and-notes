package com.yu.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class Camera implements PhotoSnapper, ICamera {
    
    public Camera() {
        System.out.println("Hello from Camera.");
    }
    
    // Defines the join point.
    /* (non-Javadoc)
     * @see com.yu.spring.aop.ICamera#snap()
     */
    @Override
    public void snap() {
        System.out.println("SNAP!");
    }
    
    /* (non-Javadoc)
     * @see com.yu.spring.aop.ICamera#snap(int)
     */
    @Override
    public void snap(int exposure) {
        System.out.println("SNAP! Exposure:" + exposure);
    }
    
    @Override
    public void snap(double exposure) {
        System.out.println("SNAP! Exposure(double):" + exposure);
    }
    
    @Override
    public void snap(double exposure, int aperture) {
        System.out.println("SNAP! Exposure(double): " + exposure + ", " + "Aperture(int): " + aperture);
    }
    
    @Override
    public void snap(int exposure, double aperture) {
        System.out.println("SNAP! Exposure(int): " + exposure + ", " + "Aperture(double): " + aperture);
    }
    
    /* (non-Javadoc)
     * @see com.yu.spring.aop.ICamera#snap(java.lang.String)
     */
    @Override
    public String snap(String name) {
        System.out.println("SNAP! Name:" + name);
        
        return name;
    }
    

    
    /* (non-Javadoc)
     * @see com.yu.spring.aop.ICamera#snapNighttime()
     */
    @Override
    public void snapNighttime() {
        System.out.println("SNAP! Night mode.");
    }
    
}
