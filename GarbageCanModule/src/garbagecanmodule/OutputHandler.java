package garbagecanmodule;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sofie Lovdal
 * 
 * This class takes care of sending the status of the garbage cans
 * to the message queue in front of the server
 */

public class OutputHandler {

  private final static String QUEUE_NAME = "hello";
  Channel myChannel;
  ConnectionFactory factory;
  Connection myConnection;
  
  /* TODO: Use configfiles to set these in order not to hardcode factory attributes.
   * Default portnumber is 5672, otherwise factory.setPort(int) */
  public OutputHandler() {
    this.factory = new ConnectionFactory();
    factory.setUsername("sofie");
    factory.setPassword("password");
    factory.setHost("10.19.0.136");
    connectToMQ(); 
  }
 
  private void connectToMQ() {
    try {
        myConnection = factory.newConnection();
        myChannel = myConnection.createChannel();
        myChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
    } catch (IOException | TimeoutException e) {
        Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, e);
    }    
  }
   
  public void sendStatus(GarbageCan garbagecan) {
    try {
        myChannel.basicPublish("", QUEUE_NAME, null, garbagecan.getBytes());
        System.out.println("Sent garbage can with ID '" + garbagecan.getID() + "'");
    } catch (IOException e) {
        Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, e);
    }
  }
  
  public void closeConnection() {
      try {
          myChannel.close();
          myConnection.close();
      } catch (IOException | TimeoutException e) {
          Logger.getLogger(OutputHandler.class.getName()).log(Level.SEVERE, null, e);
      }
  }
  
}