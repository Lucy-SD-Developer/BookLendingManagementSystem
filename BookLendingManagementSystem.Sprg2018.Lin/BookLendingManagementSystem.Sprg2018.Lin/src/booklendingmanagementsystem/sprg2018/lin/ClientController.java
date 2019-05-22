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
public class ClientController implements Controller{
    private final ClientList clientList;
    
    public ClientController(ClientList clientList) {
        this.clientList = clientList;
    }
    
    public void createClient(String firstName, String lastName) {
        Client newClient = new Client(firstName, lastName);
        clientList.addClient(newClient);
    }
    
    public void deleteClient(String firstName, String lastName) {
        clientList.deleteClient(firstName, lastName);
    }
    
    public Client searchClient(String firstName, String lastName) {
        return clientList.searchClient(firstName, lastName);
    }
    
    public Client searchClientById(int id) {
        return clientList.searchClientById(id);
    }
}
