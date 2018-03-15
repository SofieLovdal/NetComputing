/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s3017834
 */
public class Sensor implements Runnable{
    private GarbageCan garbageCan;
    private Status status;
    Random r;
    
    public Sensor() {
        this.status=new Status();
        this.r=new Random();
    }    
    
    public void linkGarbageCan(GarbageCan garbageCan) {
        this.garbageCan=garbageCan;
    }
       
    //simulates some data
    //probably improve this method later to give some more
    //realistic data
    private void simulateEvent() {
        
        this.status.setPercentageFull(r.nextInt(101));
        this.status.setMalfunctioning(r.nextBoolean());
        this.garbageCan.updateStatus(status);
    }
    
    //The run method simulates an event (garbage added, for example)
    //every 5 seconds.
    @Override
    public void run() {
        while(true) {
            simulateEvent();
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
