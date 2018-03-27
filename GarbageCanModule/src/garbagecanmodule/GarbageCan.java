/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s3017834
 * Class representation of a garbage can. Every time its sensor updates its
 * status, 
 */
public class GarbageCan implements Serializable{
    private final int ID;
    private final String location;
    private Status status;
    private transient OutputHandler outputHandler;
    
    public GarbageCan(int ID, String location, Status status) {
        this.ID=ID;
        this.location=location;
        this.status=status;
        this.outputHandler = new OutputHandler();
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
