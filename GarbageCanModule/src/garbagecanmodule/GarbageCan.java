/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="garbagecan")
@XmlAccessorType (XmlAccessType.FIELD)
/**
 *
 * @author s3017834
 * Class representation of a garbage can. Every time its sensor updates its
 * status, 
 */
public class GarbageCan implements Serializable{
    @XmlElement(name="id")
    private int ID;
    @XmlElement(name="location")
    private String location;
    @XmlElement(name="status")
    private Status status;
    private transient OutputHandler outputHandler;
    
    
    public GarbageCan() {}
    
    public GarbageCan(int ID, String location) {
        this.ID=ID;
        this.location=location;
        this.status=new Status();
        this.outputHandler = new OutputHandler();
    }
    
    /*The constructor below is used after a garbageCan has been sent over the
      network - no outputhandler or sensor is started.*/
    public GarbageCan(int ID, String location, Status status) {
        this.ID=ID;
        this.location=location;
        this.status=status;
    }
    
    public void updateStatus(Status status) {
        this.status=status;
        System.out.println("Status updated of Garbage Can with ID " + this.ID + 
                            " , status = " + this.status.getPercentageFull() + " " 
                          + this.status.getMalfunctioning());
        this.outputHandler.sendStatus(this);
    }
    
    public int getID() {
        return this.ID;
    }
    
    public Status getStatus() {
        return this.status;
    }
    
    public byte[] getBytes() {
        byte[]bytes;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.flush();
            oos.reset();
            bytes = baos.toByteArray();
            oos.close();
            baos.close();
        } catch(IOException e){
            bytes = new byte[] {};
            Logger.getLogger(GarbageCan.class.getName()).log(Level.SEVERE, null, e);
        }
        return bytes;
    }
    
    public void setID(int ID) {
        this.ID=ID;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
        
    /**
     *
     * @returns the String representation of a Garbage Can,
     * containing all its related information
     */
    @Override
    public String toString() {
        String malfunctioning = this.status.getMalfunctioning()==true ? "" : "not";
        return ("GarbageCan with ID " + this.ID + " located at " 
                + this.location + " is " + this.status.getPercentageFull() + 
                " percent full and is " + malfunctioning + " malfunctioning" );
    }

}
