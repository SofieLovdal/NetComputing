
package dataservermodule;

/**
 *
 * @author Sofie Lovdal and Elisa Oostwal
 */
public class DataServerModule {

    /**
     * @param args the command line arguments
     * Starts up data server which retrieves messages from the
     * message queue and updates a list of GarbageCans. The list is
     * used to update the subsequent REST server.
     */
    public static void main(String[] args) {
       DataServer server = new DataServer();
       new Thread(server).start();
    }
    
}
