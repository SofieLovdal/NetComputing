package dataservermodule;
import java.util.ArrayList;
import garbagecanmodule.GarbageCan;

/**
 * @author Sofie Lovdal and Elisa Oostwal
 * 
 * The data server keeps track of the status of all garbage cans which are connected to it.
 * It is connected to a message queue from which it pops messages.
 * Each message contains a garbage can with its updated status.
 * 
 */
public class DataServer implements Runnable {
    
    private boolean isRunning;
    private ArrayList<GarbageCan> garbageCans;
    private InputHandler inputHandler;

    public DataServer() {
        this.isRunning=true;
        this.garbageCans=new ArrayList<>();
        this.inputHandler=new InputHandler(this);
    }
    
    public ArrayList<GarbageCan> getGarbageCans() {
    	return garbageCans;
    }

    /**
     * Insert new/updated garbagecan into list.
     * Remove old entry of garbagecan with same ID.
     * 
     * TODO: Possibly implement this in a more effective way,
     * e.g with static indices */
    public void insertGarbageCan(GarbageCan gc) {
        Boolean inserted=false;
        for (GarbageCan item : this.garbageCans) {
            if(gc.getID() == item.getID()) {
                this.garbageCans.remove(item);
                this.garbageCans.add(gc);
                inserted=true;
                break;
            }
        }
        if (inserted==false) {
            this.garbageCans.add(gc);
        }
    }
    
    public void printList() {
        for(GarbageCan can : this.garbageCans) 
            System.out.println(can.toString());
    }

    /**
     * Run the dataserver. Constantly pop messages from the message queue.
     */
    @Override
    public void run() {
        while (isRunning) {
            inputHandler.popMessage();
            //printList();
        }
    }

}
