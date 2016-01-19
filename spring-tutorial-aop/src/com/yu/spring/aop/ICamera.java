package com.yu.spring.aop;

/**
 * Interface as proxy of {@link Camera}}
 * @author xiaoy
 *
 */
public interface ICamera {

    // Defines the join point.
    public abstract void snap();

    public abstract void snap(int exposure);
    
    void snap(double exposure, int aperture);
    
    void snap(int exposure, double aperture);

    public abstract String snap(String name);

    public abstract void snapNighttime();

    public abstract void snap(double exposure);

}