/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netcomputing.garbage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s2468255
 */
public class InputHandler extends Thread {
    
    private InetAddress serverAddress;
    
    private DatagramSocket inputSocket;
    
    private int serverPort;
    
    public InputHandler(int serverPort) {
        this.serverPort = serverPort;
        
        try {
            inputSocket = new DatagramSocket(serverPort);
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
     * Receive the garbage can information from the message queue.
     * 
     * The message queue sends one garbage can at a time,
     * but the data server contains a list of garbage cans.
     * @return 
     */
    public GarbageCan receiveGarbageData() {
        GarbageCan gc = new GarbageCan();
        
        try {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            inputSocket.receive(receivePacket);
            ByteArrayInputStream in = new ByteArrayInputStream(receivePacket.getData());
            ObjectInputStream is = new ObjectInputStream(in);
            
            try {
                gc = (GarbageCan) is.readObject();
            } catch (ClassNotFoundException e) {
                Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, e);
            }
            
        } catch (IOException e) {
            Logger.getLogger(DataServer.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return gc;
        
    }
    
}
