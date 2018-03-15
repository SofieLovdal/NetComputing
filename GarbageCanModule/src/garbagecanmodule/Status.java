/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

/**
 *
 * @author s3017834
 */
public class Status {
    private int percentageFull;
    private boolean malfunctioning;
    
    //default constructor
    public Status() {
        this.percentageFull=0;
        this.malfunctioning=false;
    }
    
    public void setStatus(int percentageFull, boolean malfunctioning) {
        this.percentageFull=percentageFull;
        this.malfunctioning=malfunctioning;
    }
    
    public void setPercentageFull(int percentageFull) {
        this.percentageFull=percentageFull;
    }
    
     public void setMalfunctioning(boolean malfunctioning) {
        this.malfunctioning=malfunctioning;
    }
    
}
