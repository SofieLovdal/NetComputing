
package dataservermodule;

/**
 * @author Sofie Lovdal and Elisa Oostwal
 * 
 * Starts a Dataserver.
 */
public class DataServerModule {

    /**
     * Starts up data server which retrieves messages from the
     * message queue and updates a list of GarbageCans.
     * 
     * The REST server will hold a data server, such that it has access
     * to the most recent (updated) list of GarbageCans.
     */
    public static void main(String[] args) {
       DataServer server = new DataServer();
       new Thread(server).start();
    }
    
}
