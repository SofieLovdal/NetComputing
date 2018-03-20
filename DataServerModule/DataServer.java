package netcomputing.garbage;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author Elisa Oostwal
 */
public class DataServer implements Runnable {
    
    private boolean isRunning;
    
    /* Port the REST server is connected to. */
    private int serverPort;
    /* Port the message queue is connected to. */
    private int MQPort;
	
    /* Address of the server (currently localhost) */
    private final InetAddress serverAddress = null;
    
    private final OutputHandler outputHandler;
    
    private final InputHandler inputHandler;
    
    private ArrayList<GarbageCan> garbageCans;
    
    /**
    * Constucts a new data server, provided a port to which the server is connected.
    * 
    * @param serverPort: port to which the server is connected.
    * @param MQPort: port to which the message queue is connected.
    */
    public DataServer(int serverPort, int MQPort) {
        inputHandler = new InputHandler(MQPort);
        outputHandler = new OutputHandler(serverPort);
    }
    
    @Override
    public void run() {
        
        while (isRunning) {
            // receive garbage can data from message queue
            GarbageCan gc = inputHandler.receiveGarbageData();
            
            // update the already existing list of garbage cans
            // first we need to check whether some garbage is already in the list
            Boolean alreadyThere = false;
            for (int idx=0; idx<garbageCans.size(); idx++) {
                if (gc.getID() == garbageCans.get(idx).getID()) {
                    // replace the old garbage can (status) by the new one
                    garbageCans.set(idx,gc);
                    alreadyThere = true;
                    break;
                }
            }
            // if the garbage can received is not yet in the list, add it
            if (alreadyThere == false) garbageCans.add(gc);
            
            // send data to REST server
            outputHandler.sendGarbageData(garbageCans);
        }
        
    }
    
    
}
