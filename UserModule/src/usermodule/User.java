/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

import garbagecanmodule.GarbageCan;
import garbagecanmodule.Status;
import java.net.InetAddress;
import java.util.ArrayList;
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
        //These will be received from the network later, just adding a static list for testing purposes
        //this.garbageCans.add(new GarbageCan(1, "Donald Duckstreet 5", new Status()));
        //this.garbageCans.add(new GarbageCan(2, "Kalle Ankagatan 5", new Status()));
        
        initializeUI();
        
        requestGarbageCanList();
        requestGarbageCollectorAddresses();
        this.outputHandler=new OutputHandler();
    }
    
    private void initializeUI() {
        this.dashboard=new UI(this);
        frame = new JFrame("Garbage Can App Groningen");
        frame.add(dashboard);
        frame.setSize(1040, 600);
        frame.setVisible(true);
    }
    
    private void requestGarbageCanList() { //public/protected?
        //GET info from rest server
        //PARSE Json object. Make into list of Garbage Cans. Update this.garbageCans.
        dashboard.updateGarbageCanList(this.garbageCans);
    }
    
    private void requestGarbageCollectorAddresses() {
        //GET info form rest server
        //PARSE Json object. Insert into list of garbageCollectorAddresses.
        //update this.garbageCollectorAddresses.
    }
    
    public abstract void displayMessageConfirmation();
}
