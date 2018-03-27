/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservermodule;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author Sofie Lovdal and Elisa Oostwal
 * This class communicates with the RabbitMQ message queue. It pops messages
 * and returns them to the server for processing.
 */
public class InputHandler extends Thread {
    
    private final static String QUEUE_NAME = "hello";
    Channel myChannel;
    ConnectionFactory factory;
    Connection myConnection;

    public InputHandler() {
        this.factory = new ConnectionFactory();
        factory.setUsername("sofie");
        factory.setPassword("password");
        factory.setHost("145.100.227.123");
        connectToMQ();
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
            GarbageCan gc = null;
            gc = gc.parseBytes(body);
            System.out.println("Received GarbageCan with ID: '" + gc.getID() + "'");
        }
        };
        try {
            myChannel.basicConsume(QUEUE_NAME, true, consumer);
            //return gc; change this later to return garbage can
        } catch (IOException ex) {
            Logger.getLogger(InputHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}

    
 