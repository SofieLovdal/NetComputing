package dataservermodule;

import garbagecanmodule.GarbageCan;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author Sofie Lovdal and Elisa Oostwal
 */
public class DataServer implements Runnable {
    
    private boolean isRunning;
    /* Port the REST server is connected to. */
    private int RESTPort;
    /* Address of the server (currently localhost) */
    private final InetAddress serverAddress = null; 
    private OutputHandler outputHandler;
    private ArrayList<GarbageCan> garbageCans;
    private InputHandler inputHandler;

    /*
    * @param serverPort: port to which the server is connected.
    */
    public DataServer(int serverPort) {
        outputHandler = new OutputHandler(serverPort);
    }

    DataServer() {
        this.isRunning=true;
        this.inputHandler=new InputHandler(this);
        this.garbageCans=new ArrayList<>();
        //this.startOutputHandler();
    }

    private void startOutputHandler() {
        new Thread(this.outputHandler).start();
    }

    /*Insert new garbagecan into list. Remove old entry of garbagecan with same ID.*/
    /*Possibly make this in a more effective way, e.g with static indices*/
    public void insertGarbageCan(GarbageCan gc) {
        Boolean inserted=false;
        for(GarbageCan item : this.garbageCans) {
            if(gc.getID() == item.getID()) {
                this.garbageCans.remove(item);
                this.garbageCans.add(gc);
                inserted=true;
                break;
            }
        }
        if(inserted==false) {
            this.garbageCans.add(gc);
        }
    }
    
    public void printList() {
        for(GarbageCan can : this.garbageCans) 
            System.out.println(can.toString());
    }

    @Override
    public void run() {
        while (isRunning) {
            inputHandler.popMessage();
            printList();
        }
    }

}
