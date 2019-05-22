/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklendingmanagementsystem.sprg2018.lin;

import java.util.ArrayList;

/**
 *
 * @author Lu Lin
 */
public class BookList implements Model {
    protected ArrayList<Book> collection = new ArrayList();
    protected View view;
    
    public void addBook(Book book) {
        book.id = this.collection.size();
        this.collection.add(book);
    }
    
    public void lendBook(int id) {
        Book book = findBook(id);
        if (book != null) {
            book.lent = true;
        }
    }
    
    public void returnBook(int id) {
        Book book = findBook(id);
        if (book != null) {
            book.lent = false;
        }
    }
    
    private Book findBook(int id) {
        for (int i = 0; i < collection.size(); i++) {
            Book current = collection.get(i);
            if (current.id == id) {
                return current;
            }
        }
        return null;
    }
    
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> lent = new ArrayList();
        ArrayList<Book> inStore = new ArrayList();
        for (int i = 0; i < collection.size(); i++) {
            Book current = collection.get(i);
            if (current.lent) {
                lent.add(current);
            } else {
                inStore.add(current);
            }
        }
        lent.addAll(inStore);   // Append unborrowed books to the end
        return lent;
    }
    
    public ArrayList<Book> getLentBooks() {
        ArrayList<Book> lent = new ArrayList();
        for (int i = 0; i < collection.size(); i++) {
            Book current = collection.get(i);
            if (current.lent) {
                lent.add(current);
            }
        }
        return lent;
    }
}
