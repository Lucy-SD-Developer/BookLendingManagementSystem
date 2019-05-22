/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklendingmanagementsystem.sprg2018.lin;

/**
 *
 * @author Lu Lin
 */
public class Client {
    protected String firstName;
    protected String lastName;
    protected boolean deleted = false;
    protected int id;
        
    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
        
    public String getclientName() {
        return firstName + " " + lastName;
    }
    
    public void markAsDeleted() {
        this.deleted = true;
    }
    
    public String toString() {
        return "Id: " + id + " First Name: " + firstName 
            + " Last Name: " + lastName;
    }
}
