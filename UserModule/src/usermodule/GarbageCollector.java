/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

import java.time.LocalDateTime;

/**
 *
 * @author s3017834
 */
public class GarbageCollector extends User {
    
    private InputHandler inputHandler;
    
    public GarbageCollector() {
        super();
        this.inputHandler=new InputHandler(this);
    }

    @Override
    public void displayMessage() {
        //what should happen here?
        this.messages.add("You sent a message to the other garbage collectors at " + LocalDateTime.now());
        this.dashboard.updateMessageList(messages);
    }
    
    
}
