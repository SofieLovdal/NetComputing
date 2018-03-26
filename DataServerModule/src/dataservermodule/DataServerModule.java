
package dataservermodule;

/**
 *
 * @author Sofie Lovdal and Elisa Oostwal
 */
public class DataServerModule {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /*Start up data server*/
       DataServer server = new DataServer();
       new Thread(server).start();
    }
    
}
