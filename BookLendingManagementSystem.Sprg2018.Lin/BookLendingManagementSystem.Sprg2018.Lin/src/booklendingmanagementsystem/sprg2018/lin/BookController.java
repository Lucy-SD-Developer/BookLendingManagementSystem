/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklendingmanagementsystem.sprg2018.lin;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lu Lin
 */
public class BookController implements Controller{
    private final BookList bookList;
    private final LendingInfoList lendingInfoList;
    
    public BookController(BookList bookList, LendingInfoList lendingInfoList) {
        this.bookList = bookList;
        this.lendingInfoList = lendingInfoList;
    }
    
    public void addBook(String title) {
        Book book = new Book();
        book.title = title;
        book.lent = false;
        bookList.addBook(book);
    }
    
    public String lendBook(int clientID, int bookID, Date date) {
        String errorMsg = this.lendingInfoList.lendBook(clientID, bookID, date);
        if(errorMsg.length() > 0) {
            return errorMsg;
        }
        this.bookList.lendBook(bookID);
        return "";
    }
    
    public void returnBook(int bookID, Date date) {
        this.bookList.returnBook(bookID);
        this.lendingInfoList.returnBook(bookID, date);
    }
    
    public ArrayList<Book> getAllBooks() {
        return this.bookList.getAllBooks();
    }
    
    public ArrayList<Book> getLentBooks() {
        return this.bookList.getLentBooks();
    }
    
    public ArrayList<LendingHistory> getLendingHistory(int bookID) {
        return this.lendingInfoList.lendingHistory(bookID);
    }
}
