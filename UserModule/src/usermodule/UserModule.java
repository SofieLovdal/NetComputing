/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule;

/**
 *
 * @author s3017834
 */
public class UserModule {

    /**
     * @param args the command line arguments
     * 
     * This is the application for the users in the TRASH project.
     * Two executables are made from this module, one for the citizens and one 
     * for the garbage collectors. They share a lot of attributes and logic,
     * so they both inherit from the super class user. They have the same UI,
     * but with slightly different logic.
     * In the jar for each user type, a single user of that type is created
     * in the main.
     */
    public static void main(String[] args) {
        Citizen citizen = new Citizen();
        
        GarbageCollector garbageCollector = new GarbageCollector();
    }
    
}
