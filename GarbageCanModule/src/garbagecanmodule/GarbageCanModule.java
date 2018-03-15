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
public class GarbageCanModule {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //creates a sensor and a garbage can and links these.
        //starts run method of sensor that will simulate data and give it to
        //the garbage can.
        Sensor sensor1 = new Sensor();
        GarbageCan garbageCan1 = new GarbageCan(1, "Nieuwe Blekstraat 1", new Status());
        sensor1.linkGarbageCan(garbageCan1);
        new Thread(sensor1).start();
        
    }
    
}
