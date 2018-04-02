/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

import garbagecanmodule.GarbageCan;
import garbagecanmodule.GarbageCanList;
import garbagecanmodule.Status;
import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ContentHandler;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
    
    /*GET garbagecans from REST server*/
    private void requestGarbageCanList() {  try {
        String address = "http://10.19.0.129:8080/UserManagement/rest/GarbageService/garbagecans";
        
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        int status = con.getResponseCode();
        System.out.println("status code = " + status);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        inputLine=in.readLine();
        System.out.println("inputLine: " + inputLine);
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(GarbageCanList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(inputLine);
            
            GarbageCanList garbageCanList;
            garbageCanList = (GarbageCanList) unmarshaller.unmarshal(reader);
            for(GarbageCan gc : garbageCanList.getGarbageCanList()) {
                System.out.println(gc.toString());
            }
            this.garbageCans=garbageCanList.getGarbageCanList();
        } catch (JAXBException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
              
        in.close();
        con.disconnect();
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        dashboard.updateGarbageCanList();
    }
    
    private void requestGarbageCollectorAddresses() {
        //GET info form rest server
        //PARSE Json object. Insert into list of garbageCollectorAddresses.
        //update this.garbageCollectorAddresses.
    }
    
    public abstract void displayMessageConfirmation();
}
