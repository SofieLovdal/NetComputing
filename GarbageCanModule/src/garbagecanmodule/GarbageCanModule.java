package garbagecanmodule;

/**
 * @author Sofie Lovdal
 * 
 * Starts two garbage cans to simulate events.
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
        GarbageCan garbageCan1 = new GarbageCan(3, "Nieuwe Blekstraat 1");
        sensor1.linkGarbageCan(garbageCan1);
        new Thread(sensor1).start();
        
        Sensor sensor2 = new Sensor();
        GarbageCan garbageCan2 = new GarbageCan(4, "Nijenborgh 5");
        sensor2.linkGarbageCan(garbageCan2);
        new Thread(sensor2).start();
        
    }
    
}
