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
public class ClientList implements Model {
    protected ArrayList<Client> collection = new ArrayList();
    protected View view;
    
    public void addClient(Client client) {
        client.id = collection.size();
        collection.add(client);
    }
    
    public void deleteClient(String firstName, String lastName) {
        Client toDelete = this.searchClient(firstName, lastName);
        if (toDelete != null) {
            toDelete.markAsDeleted();
        }
    }
    
    public Client searchClient(String firstName, String lastName) {
        for (int i = 0; i < collection.size(); i++) {
            Client current = collection.get(i);
            if (current.firstName.equals(firstName) 
                    && current.lastName.equals(lastName)
                    && !current.deleted) {
                return current;
            }
        }
        return null;
    }
    
    public Client searchClientById(int id) {
        for (int i = 0; i < collection.size(); i++) {
            Client current = collection.get(i);
            if (current.id == id && !current.deleted) {
                return current;
            }
        }
        return null;
    }
}
