/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservermodule;

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
 * @author Sofie Lovdal.
 * Smaller version of GarbageCan object such that server can access
 * the necessary fields and methods.
 */
public class GarbageCan implements Serializable{
    private final int ID;
    private final String location;
    private Status status;
    
    public GarbageCan(int ID, String location, Status status) {
        this.ID=ID;
        this.location=location;
        this.status=status;
    }
    
    public int getID() {
        return this.ID;
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
    
    public GarbageCan parseBytes(byte[] message) {
        GarbageCan can=null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInputStream ois = new ObjectInputStream(bis);
            can = (GarbageCan) ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(GarbageCan.class.getName()).log(Level.SEVERE, null, e);
        }
        return can;
    }
}
