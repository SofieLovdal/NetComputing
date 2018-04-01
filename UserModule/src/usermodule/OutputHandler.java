/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s3017834
 * Takes care of broadcasting a message to all Garbage collectors via a socket
 * (P2P connection) if the button "send message" in the UI has been clicked.
 * 
 */
class OutputHandler {
    private DatagramSocket socket;
    
    public OutputHandler() {
        setSocket();
    }
    
    private void setSocket() {
        try {
            this.socket=new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(String message, ArrayList<InetAddress> addressList, ArrayList<Integer> portList)  {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
            os.writeObject(message);
	    
	    byte[] data = out.toByteArray();
            
            for(InetAddress address: addressList) {
                DatagramPacket packet = new DatagramPacket(data, data.length, address, portList.get(addressList.indexOf(address)));
                socket.send(packet);
            }
            os.flush();
	    os.close();
        } catch (IOException e) {
            Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, e);
        } 
    }
}
