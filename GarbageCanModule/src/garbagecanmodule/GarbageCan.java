/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

import java.io.Serializable;

/**
 *
 * @author s3017834
 */
public class GarbageCan implements Serializable{
    private int ID;
    private String location;
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
        this.outputHandler.sendStatus(this);
    }
}
