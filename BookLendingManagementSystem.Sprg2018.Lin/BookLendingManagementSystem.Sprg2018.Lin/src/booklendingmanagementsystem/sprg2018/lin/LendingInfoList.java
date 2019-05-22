/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklendingmanagementsystem.sprg2018.lin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author Lu Lin
 */
public class LendingInfoList implements Model{
    protected ArrayList<LendingInfo> collection = new ArrayList();
    protected View view;
    
    public String lendBook(int clientID, int bookID, Date date) {
        int borrowed = 0;
        // First make sure the book is not lent
        for (int i = 0; i < collection.size(); i++) {
            LendingInfo current = collection.get(i);
            if (current.bookID == bookID && current.dateIn == null) {
                return "This book is unavailable.";
            } else if (current.clientID == clientID && current.dateIn == null) {
                borrowed++;
            }
        }
        
        // Second make sure the client hasn't borrowed three books
        if (borrowed >= 3) {
            return "Client has reached the maximum number of books that can be borrowed";
        }
        
        LendingInfo newLending = new LendingInfo();
        newLending.bookID = bookID;
        newLending.clientID = clientID;
        newLending.dateOut = date;
        collection.add(newLending);
        return "";
    }
    
    public void returnBook(int bookID, Date date) {
        for (int i = 0; i < collection.size(); i++) {
            LendingInfo current = collection.get(i);
            if (current.bookID == bookID) {
                current.dateIn = date;
                current.calculateFine();
            }
        }
    }
    
    public ArrayList<LendingHistory> lendingHistory(int bookID) {
        ArrayList<LendingHistory> history = new ArrayList();
        for (int i = 0; i < collection.size(); i++) {
            LendingInfo current = collection.get(i);
            if (current.bookID == bookID) {
                LendingHistory entry = new LendingHistory();
                entry.clientID = current.clientID;
                entry.lentDate = current.dateOut;
                entry.fine = current.fine;
                history.add(entry);
            }
        }
        history.sort((LendingHistory a, LendingHistory b) 
                -> a.lentDate.compareTo(b.lentDate));
        return history;
    }
}
