/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s3017834
 * Attribute of GarbageCollector. Listens to incoming messages from 
 * citizens via a ServerSocket.s
 */
class InputHandler implements Runnable {
    private DatagramSocket inputSocket;
    private int port;
    private boolean running;
    private GarbageCollector garbageCollector;
    
    public InputHandler(GarbageCollector garbageCollector) {
        //this.port = read from config file
        this.port=8900;
        this.running=true;
        this.garbageCollector=garbageCollector;
        setSocket();
        startInputHandler();
    }
    
    private void setSocket() {
        try {
            this.inputSocket = new DatagramSocket(port);
            this.inputSocket.setSoTimeout(0); //0 means infinite timeout
        } catch (SocketException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startInputHandler() {
        new Thread(this).start();
    }
    
    private void receiveMessage() {
        try {
            byte[] buffer = new byte[10000];
            System.out.println("Waiting for message... ");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            inputSocket.receive(packet);
            ByteArrayInputStream baos = new ByteArrayInputStream(buffer);
            ObjectInputStream oos = new ObjectInputStream(baos);
            String message = (String)oos.readObject();
            System.out.println("Garbage collector received message: " + message);
            this.garbageCollector.addMessage(LocalDateTime.now().withNano(0) + " : " + message);
            this.garbageCollector.dashboard.updateMessageList();
        } catch (IOException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while(running) {
            receiveMessage();
        }
        this.inputSocket.close();
    }
    
    
}
