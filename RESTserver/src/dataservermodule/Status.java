package dataservermodule;

import java.io.Serializable;

/**
 * @author Sofie Lovdal & Elisa Oostwal
 * 
 */
public class Status implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int percentageFull;
    private boolean malfunctioning;
    
    public Status() {
        this.percentageFull=0;
        this.malfunctioning=false;
    }
    
    public void setStatus(int percentageFull, boolean malfunctioning) {
        this.percentageFull=percentageFull;
        this.malfunctioning=malfunctioning;
    }
    
    public int getPercentageFull() {
        return this.percentageFull;
    }
    
    public void setPercentageFull(int percentageFull) {
        this.percentageFull=percentageFull;
    }
    
    public boolean getMalfunctioning() {
        return this.malfunctioning;
    }
    
    public void setMalfunctioning(boolean malfunctioning) {
        this.malfunctioning=malfunctioning;
    }
    
}
