package restserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name = "status")

/**
 * @author Sofie Lovdal & Elisa Oostwal
 * 
 * Represents dummy class Status used by the REST server.
 */
public class Status implements Serializable{
	
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
    
    @XmlElement
    public void setPercentageFull(int percentageFull) {
        this.percentageFull=percentageFull;
    }
    
    public boolean getMalfunctioning() {
        return this.malfunctioning;
    }
    
    @XmlElement
    public void setMalfunctioning(boolean malfunctioning) {
        this.malfunctioning=malfunctioning;
    }

    
}
