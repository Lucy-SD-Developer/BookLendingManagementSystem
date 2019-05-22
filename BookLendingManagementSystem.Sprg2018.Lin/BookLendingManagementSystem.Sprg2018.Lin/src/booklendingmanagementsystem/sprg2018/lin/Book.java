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
public class Book {
    protected int id;
    protected String title;
    protected boolean lent;
    
    public String toString() {
        return "Id: " + id + " Title: " + title 
            + " Lent: " + lent;
    }
}
