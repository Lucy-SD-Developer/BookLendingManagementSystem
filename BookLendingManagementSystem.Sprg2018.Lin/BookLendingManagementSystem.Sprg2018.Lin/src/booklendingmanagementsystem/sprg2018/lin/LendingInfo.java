/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package booklendingmanagementsystem.sprg2018.lin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lu Lin
 */
public class LendingInfo {
    protected int borrowID;
    protected int clientID;
    protected int bookID;
    protected Date dateOut;
    protected Date dateIn;
    protected int fine;
    
    public void calculateFine() {
        
        if ((dateIn.getTime() - dateOut.getTime())/(24*60*60*1000) <= 15) {
            this.fine = 0;
        } else if ((dateIn.getTime() - dateOut.getTime())/(24* 60 * 60 * 1000) <= 16) {
            this.fine = 1;
        } else {
        this.fine = (int)(((dateIn.getTime() - dateOut.getTime())/(24*60*60*1000))- 15); }
    }
}

