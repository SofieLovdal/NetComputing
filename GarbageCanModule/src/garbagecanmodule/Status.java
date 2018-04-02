/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="status")
@XmlAccessorType (XmlAccessType.FIELD)

/**
 *
 * @author s3017834
 */
public class Status implements Serializable{
    @XmlElement(name="percentageFull")
    private int percentageFull;
    @XmlElement(name="malfunctioning")
    private boolean malfunctioning;
    
    public Status() {
        this.percentageFull=0;
        this.malfunctioning=false;
    }
    
    public Status(int percentageFull, boolean malfunctioning) {
        this.percentageFull = percentageFull;
        this.malfunctioning=malfunctioning;
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
