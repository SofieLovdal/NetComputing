/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

import garbagecanmodule.GarbageCan;
import garbagecanmodule.Status;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author s3017834
 */
public abstract class User {
    
    protected ArrayList<GarbageCan> garbageCans;
    protected ArrayList<InetAddress> garbageCollectorAdresses;
    protected ArrayList<Integer> portList;
    protected InetAddress restServerAddress;
    protected UI dashboard;
    protected OutputHandler outputHandler;
    protected ArrayList<String> messages;
    protected JFrame frame;
    
    public User() {
        this.garbageCans = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.garbageCollectorAdresses = new ArrayList<>();
        this.portList=new ArrayList<>();
        
        initializeUI();
        
        initDummyLists();
        
        requestGarbageCanList();
        requestGarbageCollectorAddresses();
        this.outputHandler=new OutputHandler();
    }
    
    public void initDummyLists() {
        //These will be received from the network later, just adding a static list for testing purposes
        this.garbageCans.add(new GarbageCan(1, "Donald Duckstreet 5", new Status(13, false)));
        this.garbageCans.add(new GarbageCan(2, "Kalle Ankagatan 5", new Status(50, true)));
        this.garbageCans.add(new GarbageCan(3, "Hey Helmer! :)", new Status(100, false)));
        
        try {
            this.garbageCollectorAdresses.add(InetAddress.getLocalHost());
            //this.garbageCollectorAdresses.add(InetAddress.getByName("217.105.174.251"));
            //this.garbageCollectorAdresses.add(InetAddress.getByName("84.83.28.185"));
        } catch (UnknownHostException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.portList.add(8900);
    }
    
    private void initializeUI() {
        this.dashboard=new UI(this);
        frame = new JFrame("Garbage Can App Groningen");
        frame.add(dashboard);
        frame.setSize(1040, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public synchronized void addMessage(String message) {
        this.messages.add(message);
    }
    
    private void requestGarbageCanList() { //public/protected?
        //GET info from rest server
        //PARSE Json object. Make into list of Garbage Cans. Update this.garbageCans.
        dashboard.updateGarbageCanList();
    }
    
    private void requestGarbageCollectorAddresses() {
        //GET info form rest server
        //PARSE Json object. Insert into list of garbageCollectorAddresses.
        //update this.garbageCollectorAddresses.
    }
    
    public abstract void displayMessageConfirmation();
}
