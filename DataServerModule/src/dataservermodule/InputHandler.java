/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservermodule;

import com.rabbitmq.client.*;
import garbagecanmodule.GarbageCan;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Sofie Lovdal and Elisa Oostwal
 * This class communicates with the RabbitMQ message queue. It pops messages
 * and inserts them in the message queue of the server.
 */
public class InputHandler extends Thread {
    
    private final static String QUEUE_NAME = "hello";
    Channel myChannel;
    ConnectionFactory factory;
    Connection myConnection;
    private DataServer server;

    public InputHandler(DataServer server) {
        this.server = server;
        this.factory = new ConnectionFactory();
        this.factory.setUsername("sofie");
        this.factory.setPassword("password");
        this.factory.setHost("145.100.226.78");
        this.connectToMQ();
    }
    
    public void connectToMQ() {
        try {
            myConnection = factory.newConnection();
            myChannel = myConnection.createChannel();
            myChannel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println("Waiting for messages. To exit press CTRL+C");
        } catch (IOException | TimeoutException e) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void popMessage() {
        Consumer consumer = new DefaultConsumer(myChannel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                GarbageCan gc = parseBytes(body);
                InputHandler.this.server.insertGarbageCan(gc);
            }
        };
        try {
            myChannel.basicConsume(QUEUE_NAME, true, consumer);       
        } catch (IOException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public GarbageCan parseBytes(byte[] message) {
        GarbageCan can=null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(message);
            ObjectInputStream ois = new ObjectInputStream(bis);
            can = (GarbageCan) ois.readObject();
            ois.close();
            bis.close();
        }
        catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(GarbageCan.class.getName()).log(Level.SEVERE, null, e);
        }
        return can;
    }
        
}

    
 