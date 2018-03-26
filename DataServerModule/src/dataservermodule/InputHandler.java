/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataservermodule;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 *
 * @author Sofie Lovdal and Elisa Oostwal
 */
public class InputHandler extends Thread {
    
    private final static String QUEUE_NAME = "hello";
    Channel myChannel;
    ConnectionFactory factory;
    Connection myConnection;

    InputHandler() {
        
    }
    
    public GarbageCan popMessage() {
        GarbageCan gc = null;
        //pop message from queue
        return gc;
    }
        
}
