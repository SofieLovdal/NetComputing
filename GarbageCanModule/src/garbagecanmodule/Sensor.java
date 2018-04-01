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
       
    /*Simulates event happening to the garbage can
      The chance of the can malfunctioning is set to 5%
      A random generator gives a number of added garbage for each event*/
    private void simulateEvent() {
        this.status.setPercentageFull(this.status.getPercentageFull() + r.nextInt(10));
        if(r.nextInt(101)>95) {
            this.status.setMalfunctioning(true);
        }
        this.garbageCan.updateStatus(status);
        
        /*If the can is full or malfunctioning, they can can not be used any more.
          The thread sleeps for one minute, simulating waiting for a garbage worker,
          which then empties or fixes the can, reseting the values.*/
        if((this.status.getPercentageFull()>=100) || (this.status.getMalfunctioning() == true)) {
            try {
                System.out.println("Garbage can " + this.garbageCan.getID() + " is full or malfunctioning... waiting for garbage worker");
                sleep(60000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.status.setStatus(0, false);
            this.garbageCan.updateStatus(this.status);
        }
    }
    
    /*Simulates the sensor detecting an event every 5 seconds*/
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
