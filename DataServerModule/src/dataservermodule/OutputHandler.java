package dataservermodule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author s2468255
 */
public class OutputHandler extends Thread {
    
    private InetAddress serverAddress;
    
    private DatagramSocket outputSocket;
    
    private int serverPort;
    
    public OutputHandler(int serverPort) {
        this.serverPort = serverPort;
        
        try {
            outputSocket = new DatagramSocket(serverPort);
        } catch(Exception e) {
            System.out.println("Error: Port server is already in use");
            System.exit(1);
        }
        
        try {
            this.serverAddress = InetAddress.getByName(null);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Send the garbage can information to the REST server.
     * 
     * We send the list of garbage cans to the REST server.
     */
    private void sendGarbageData(ArrayList<GarbageCan> garbageCans) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
            
            os.writeObject(garbageCans);
	    os.flush();
	    os.close();
	    byte[] data = out.toByteArray();
            
            DatagramPacket packet = new DatagramPacket(data, data.length, this.serverAddress, this.serverPort);
            outputSocket.send(packet);
            System.out.println("Garbage can info sent to REST server");
            
        } catch (IOException e) { // add more specific exceptions?
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
