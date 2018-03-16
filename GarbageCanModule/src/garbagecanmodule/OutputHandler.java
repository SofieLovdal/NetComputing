/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package garbagecanmodule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s3017834
 */
public class OutputHandler {
    
    private InetAddress serverAddress;
    private DatagramSocket outputSocket;
    private int serverPort;
    
    public OutputHandler() {
        setServerAddress();
        setSocket();
    }
    
    /*serverAddress==localhost for now*/
    private void setServerAddress() {
        try {
            this.serverAddress = InetAddress.getByName(null);
        } catch (UnknownHostException ex) {
            Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.serverPort = 2000; 
    }
    
    private void setSocket() {
        try {
            this.outputSocket=new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void sendStatus(GarbageCan garbageCan) {
        //possibly make new thread here so that each send is handled by a different thread
        //or earlier, just so it does not block on send.
        ObjectOutputStream oos = null; 
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream(10000); //is this proper size
            oos = new ObjectOutputStream(baos);
            oos.writeObject(garbageCan);
            final byte[] data = baos.toByteArray();
            //can the old packet be reused?
            DatagramPacket packet = new DatagramPacket(data, data.length, this.serverAddress, this.serverPort);
            this.outputSocket.send(packet);
            System.out.println("Status sent");
        } catch (IOException e) { // add more specific exceptions?
            Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
