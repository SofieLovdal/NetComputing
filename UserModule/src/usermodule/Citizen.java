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
public class Citizen extends User {
    
    public Citizen() {
        super();
    }
    
    /*When a citizen sends a message, their message window displays a confirmation
    and the time the message was sent.*/
    @Override
    public void displayMessageConfirmation() {
        this.messages.add("You sent a message to the garbage collectors at " + LocalDateTime.now());
        this.dashboard.updateMessageList(messages);
    }
}
