/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booklendingmanagementsystem.sprg2018.lin;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Lu Lin
 */
public class App extends javax.swing.JFrame implements View {
    
    private final ClientList clientList = new ClientList();
    private final ClientController clientController 
            = new ClientController(clientList);
    
    private final BookList bookList = new BookList();
    private final LendingInfoList lendingInfoList = new LendingInfoList();
    private final BookController bookController
            = new BookController(bookList, lendingInfoList);
    
    private File clientFile;
    private File bookFile;
    private File lendingInfoFile;
    
    private final SimpleDateFormat df = new SimpleDateFormat("mm/dd/yyyy");
    
    /**
     * Creates new form App
     */
    public App() {
        initComponents();
        
        clientList.view = this;
        bookList.view = this;
        lendingInfoList.view = this;
                
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Choose where your data files are located");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File directory = chooser.getSelectedFile();
            try {
                clientFile = new File(directory, "clients.txt");
                bookFile = new File(directory, "books.txt");
                lendingInfoFile = new File(directory, "lending_info.txt");
                
                clientFile.createNewFile();
                bookFile.createNewFile();
                lendingInfoFile.createNewFile();
                
                readModel();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Error creating/reading data file. Exiting Application.");
                System.exit(0);
            }
            
            
        } else {
            JOptionPane.showMessageDialog(this,
                "Unknown data file location. Exiting Application.");
            System.exit(0);
        }
    }
    
    private void readModel() throws Exception {
        FileInputStream iFile;
        BufferedReader r;
        String line;
        // Read clients
        iFile = new FileInputStream(clientFile);
        r = new BufferedReader(new InputStreamReader(iFile));
        line = r.readLine();
        while (line != null && line.length() != 0) {
            String[] tokens = line.split("\\$");
            int id = Integer.parseInt(tokens[0]);
            String firstName = tokens[1];
            String lastName = tokens[2];
            boolean deleted = Boolean.parseBoolean(tokens[3]);
            Client client = new Client(firstName, lastName);
            client.id = id;
            client.deleted = deleted;
            clientList.collection.add(client);
            line = r.readLine();
        }
        r.close();
        
        // Read books
        iFile = new FileInputStream(bookFile);
        r = new BufferedReader(new InputStreamReader(iFile));
        line = r.readLine();
        while (line != null && line.length() != 0) {
            String[] tokens = line.split("\\$");
            int id = Integer.parseInt(tokens[0]);
            String title = tokens[1];
            boolean lent = Boolean.parseBoolean(tokens[2]);
            Book book = new Book();
            book.id = id;
            book.title = title;
            book.lent = lent;
            bookList.collection.add(book);
            line = r.readLine();
        }
        r.close();
        
        // Read lending info        
        iFile = new FileInputStream(lendingInfoFile);
        r = new BufferedReader(new InputStreamReader(iFile));
        line = r.readLine();
        while (line != null && line.length() != 0) {
            String[] tokens = line.split("\\$");
            int id = Integer.parseInt(tokens[0]);
            int clientID = Integer.parseInt(tokens[1]);
            int bookID = Integer.parseInt(tokens[2]);
            Date dateOut = tokens[3].equals("N/A") ? null : this.df.parse(tokens[3]);
            Date dateIn = tokens[4].equals("N/A") ? null : this.df.parse(tokens[4]);
            int fine = Integer.parseInt(tokens[5]);
            
            LendingInfo info = new LendingInfo();
            info.borrowID = id;
            info.clientID = clientID;
            info.bookID = bookID;
            info.dateOut = dateOut;
            info.dateIn = dateIn;
            info.fine = fine;
            
            lendingInfoList.collection.add(info);
            line = r.readLine();
        }
        r.close();
    }
    
    private void writeModel() throws Exception {
        FileOutputStream oFile;
        BufferedWriter w;
        // Write clients
        oFile = new FileOutputStream(clientFile);
        w = new BufferedWriter(new OutputStreamWriter(oFile));
        for (int i = 0; i < clientList.collection.size(); i++) {
            Client current = clientList.collection.get(i);
            w.write(current.id + "$" + current.firstName + "$" + current.lastName + "$" + current.deleted);
            w.newLine();
        }
        w.close();
        
        // Write books
        oFile = new FileOutputStream(bookFile);
        w = new BufferedWriter(new OutputStreamWriter(oFile));
        for (int i = 0; i < bookList.collection.size(); i++) {
            Book current = bookList.collection.get(i);
            w.write(current.id + "$" + current.title + "$" + current.lent);
            w.newLine();
        }
        w.close();
        // Write lending info
        oFile = new FileOutputStream(lendingInfoFile);
        w = new BufferedWriter(new OutputStreamWriter(oFile));
        for (int i = 0; i < lendingInfoList.collection.size(); i++) {
            LendingInfo current = lendingInfoList.collection.get(i);
            w.write(current.borrowID + "$" 
                    + current.clientID + "$" 
                    + current.bookID + "$"
                    + (current.dateOut != null ? df.format(current.dateOut) : "N/A") + "$"
                    + (current.dateIn != null ? df.format(current.dateIn) : "N/A") + "$"
                    + current.fine
            );
            w.newLine();
        }
        w.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabMain = new javax.swing.JTabbedPane();
        panelClient = new javax.swing.JPanel();
        btnCreateClient = new javax.swing.JButton();
        btnDeleteClient = new javax.swing.JButton();
        btnSearchClient = new javax.swing.JButton();
        btnDisplayAllClients = new javax.swing.JButton();
        lblCreateClient = new javax.swing.JLabel();
        txtFirstNameAdd = new javax.swing.JTextField();
        txtLastNameAdd = new javax.swing.JTextField();
        lblDeleteClient = new javax.swing.JLabel();
        txtFirstNameDel = new javax.swing.JTextField();
        txtLastNameDel = new javax.swing.JTextField();
        lblDeleteClient1 = new javax.swing.JLabel();
        txtFirstNameSearch = new javax.swing.JTextField();
        txtLastNameSearch = new javax.swing.JTextField();
        panelBook = new javax.swing.JPanel();
        lblAddBook = new javax.swing.JLabel();
        txtBookTitle = new javax.swing.JTextField();
        btnAddBook = new javax.swing.JButton();
        lblLendBook = new javax.swing.JLabel();
        txtClientIdLend = new javax.swing.JTextField();
        btnLendBook = new javax.swing.JButton();
        lblReturnBook = new javax.swing.JLabel();
        btnReturnBook = new javax.swing.JButton();
        btnShowLent = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();
        lblHistory = new javax.swing.JLabel();
        txtHistoryID = new javax.swing.JTextField();
        btnLendingHistory = new javax.swing.JButton();
        lblClientIdLend = new javax.swing.JLabel();
        lblBookIdLend = new javax.swing.JLabel();
        txtBookIdLend = new javax.swing.JTextField();
        txtLendDate = new javax.swing.JFormattedTextField();
        lblLendDate = new javax.swing.JLabel();
        lblBookIdReturn = new javax.swing.JLabel();
        txtBookIdReturn = new javax.swing.JTextField();
        lblReturnDate = new javax.swing.JLabel();
        txtReturnDate = new javax.swing.JFormattedTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnCreateClient.setText("Create");
        btnCreateClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClientActionPerformed(evt);
            }
        });

        btnDeleteClient.setText("Delete");
        btnDeleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClientActionPerformed(evt);
            }
        });

        btnSearchClient.setText("Search");
        btnSearchClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchClientActionPerformed(evt);
            }
        });

        btnDisplayAllClients.setText("Display all clients");
        btnDisplayAllClients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayAllClientsActionPerformed(evt);
            }
        });

        lblCreateClient.setText("Create a client");

        txtFirstNameAdd.setText("First Name ...");

        txtLastNameAdd.setText("Last Name ...");

        lblDeleteClient.setText("Delete a client");

        txtFirstNameDel.setText("First Name ...");

        txtLastNameDel.setText("Last Name ...");

        lblDeleteClient1.setText("Search a client");

        txtFirstNameSearch.setText("First Name ...");

        txtLastNameSearch.setText("Last Name ...");

        javax.swing.GroupLayout panelClientLayout = new javax.swing.GroupLayout(panelClient);
        panelClient.setLayout(panelClientLayout);
        panelClientLayout.setHorizontalGroup(
            panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClientLayout.createSequentialGroup()
                        .addComponent(lblCreateClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClientLayout.createSequentialGroup()
                        .addComponent(lblDeleteClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstNameDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastNameDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClientLayout.createSequentialGroup()
                        .addComponent(lblDeleteClient1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteClient)
                    .addComponent(btnCreateClient)
                    .addComponent(btnSearchClient))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClientLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDisplayAllClients)
                .addGap(122, 122, 122))
        );
        panelClientLayout.setVerticalGroup(
            panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClientLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreateClient)
                    .addComponent(lblCreateClient)
                    .addComponent(txtFirstNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteClient)
                    .addComponent(lblDeleteClient)
                    .addComponent(txtFirstNameDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastNameDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchClient)
                    .addComponent(lblDeleteClient1)
                    .addComponent(txtFirstNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastNameSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(btnDisplayAllClients)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        tabMain.addTab("Client", panelClient);

        lblAddBook.setText("Add a book");

        txtBookTitle.setText("Title...");

        btnAddBook.setText("Add");
        btnAddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBookActionPerformed(evt);
            }
        });

        lblLendBook.setText("Lend a book");

        txtClientIdLend.setText("Client ID");

        btnLendBook.setText("Lend");
        btnLendBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLendBookActionPerformed(evt);
            }
        });

        lblReturnBook.setText("Return a book");

        btnReturnBook.setText("Return");
        btnReturnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnBookActionPerformed(evt);
            }
        });

        btnShowLent.setText("Show lent books");
        btnShowLent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowLentActionPerformed(evt);
            }
        });

        btnShowAll.setText("Show all books");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        lblHistory.setText("Lending History");

        txtHistoryID.setText("Book ID");

        btnLendingHistory.setText("Display");
        btnLendingHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLendingHistoryActionPerformed(evt);
            }
        });

        lblClientIdLend.setText("Client ID");

        lblBookIdLend.setText("Book ID");

        txtBookIdLend.setText("Book ID");

        txtLendDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("mm/dd/yyyy"))));
        txtLendDate.setText("mm/dd/yyyy");

        lblLendDate.setText("Date");

        lblBookIdReturn.setText("Book ID");

        txtBookIdReturn.setText("Book ID");

        lblReturnDate.setText("Date");

        txtReturnDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("mm/dd/yyyy"))));
        txtReturnDate.setText("mm/dd/yyyy");

        javax.swing.GroupLayout panelBookLayout = new javax.swing.GroupLayout(panelBook);
        panelBook.setLayout(panelBookLayout);
        panelBookLayout.setHorizontalGroup(
            panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBookLayout.createSequentialGroup()
                        .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBookLayout.createSequentialGroup()
                                .addComponent(lblAddBook)
                                .addGap(35, 35, 35)
                                .addComponent(txtBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAddBook))
                            .addComponent(btnShowAll)
                            .addComponent(btnShowLent)
                            .addGroup(panelBookLayout.createSequentialGroup()
                                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelBookLayout.createSequentialGroup()
                                        .addComponent(lblClientIdLend)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtClientIdLend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBookLayout.createSequentialGroup()
                                        .addComponent(lblLendDate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtLendDate)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBookLayout.createSequentialGroup()
                                        .addComponent(lblBookIdLend)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBookIdLend, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnLendBook)))
                            .addComponent(lblLendBook)
                            .addComponent(lblReturnBook))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBookLayout.createSequentialGroup()
                        .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBookLayout.createSequentialGroup()
                                .addComponent(lblBookIdReturn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBookIdReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblReturnDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReturnBook))
                            .addGroup(panelBookLayout.createSequentialGroup()
                                .addComponent(lblHistory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHistoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLendingHistory)))
                        .addGap(0, 144, Short.MAX_VALUE))))
        );
        panelBookLayout.setVerticalGroup(
            panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddBook)
                    .addComponent(txtBookTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddBook))
                .addGap(26, 26, 26)
                .addComponent(lblLendBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClientIdLend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBookIdLend)
                    .addComponent(txtBookIdLend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClientIdLend))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLendBook)
                    .addComponent(lblLendDate)
                    .addComponent(txtLendDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblReturnBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBookIdReturn)
                    .addComponent(txtBookIdReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblReturnDate)
                    .addComponent(txtReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReturnBook))
                .addGap(18, 18, 18)
                .addGroup(panelBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHistory)
                    .addComponent(txtHistoryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLendingHistory))
                .addGap(18, 18, 18)
                .addComponent(btnShowAll)
                .addGap(18, 18, 18)
                .addComponent(btnShowLent)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        tabMain.addTab("Book", panelBook);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMain, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tabMain.getAccessibleContext().setAccessibleName("");
        tabMain.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchClientActionPerformed
        Client result = clientController.searchClient(txtFirstNameSearch.getText(),
                txtLastNameSearch.getText());
        JDialog dialog = new JDialog();
        ClientView clientView = new ClientView(result);
        dialog.getContentPane().add(clientView);
        dialog.setLocationRelativeTo(this);
        dialog.setSize(200, 200);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnSearchClientActionPerformed

    private void btnDisplayAllClientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayAllClientsActionPerformed
        displayAllClients();
    }//GEN-LAST:event_btnDisplayAllClientsActionPerformed

    private void btnDeleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClientActionPerformed
        clientController.deleteClient(txtFirstNameDel.getText(),
                txtLastNameDel.getText());
        JOptionPane.showMessageDialog(this,
                "Client deleted.");
        displayAllClients();
    }//GEN-LAST:event_btnDeleteClientActionPerformed

    private void btnCreateClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClientActionPerformed
        clientController.createClient(txtFirstNameAdd.getText(), 
                txtLastNameAdd.getText());
        JOptionPane.showMessageDialog(this,
                "Client created.");
        displayAllClients();
    }//GEN-LAST:event_btnCreateClientActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            writeModel();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error saving. Data files may be corrupted.");
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnShowLentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowLentActionPerformed
        displayBooks(bookController.getLentBooks());
    }//GEN-LAST:event_btnShowLentActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        displayBooks(bookController.getAllBooks());
    }//GEN-LAST:event_btnShowAllActionPerformed

    private void btnAddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBookActionPerformed
        bookController.addBook(txtBookTitle.getText());
        JOptionPane.showMessageDialog(this,
                "Book added.");
        displayBooks(bookController.getAllBooks());
    }//GEN-LAST:event_btnAddBookActionPerformed

    private void btnLendBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLendBookActionPerformed
        try {
            String errorMsg = bookController.lendBook(
                Integer.parseInt(txtClientIdLend.getText()), 
                Integer.parseInt(txtBookIdLend.getText()), 
                df.parse(txtLendDate.getText())
            );
            if (errorMsg.length() > 0) {
                JOptionPane.showMessageDialog(this,
                    errorMsg);
                return;
            }
            
            JOptionPane.showMessageDialog(this,
                "Book lent.");
            displayBooks(bookController.getAllBooks());
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Invalid input.");
        }
    }//GEN-LAST:event_btnLendBookActionPerformed

    private void btnReturnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnBookActionPerformed
        try {
            bookController.returnBook(
                Integer.parseInt(txtBookIdReturn.getText()), 
                df.parse(txtReturnDate.getText())
            );
            JOptionPane.showMessageDialog(this,
                "Book returned.");
            displayBooks(bookController.getAllBooks());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Invalid input.");
        }
    }//GEN-LAST:event_btnReturnBookActionPerformed

    private void btnLendingHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLendingHistoryActionPerformed
        try {
            ArrayList<LendingHistory> history = bookController.getLendingHistory(
                Integer.parseInt(txtHistoryID.getText()));
            
            String output = "";
            for (int i = 0; i < history.size(); i++) {
                int clientId = history.get(i).clientID;
                Client client = clientController.searchClientById(clientId);
                output += "Name: " + client.firstName + " " + client.lastName;
                output += "; Date of lending: " + df.format(history.get(i).lentDate);
                output += "; Fine: " + history.get(i).fine;
                output += "\n";
            }
            JDialog dialog = new JDialog();
            JTextArea textArea = new JTextArea();
            textArea.setText(output);
            dialog.getContentPane().add(textArea);
            dialog.setLocationRelativeTo(this);
            dialog.setSize(500, 500);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Invalid input.");
        }
    }//GEN-LAST:event_btnLendingHistoryActionPerformed

    private void displayAllClients() {
        JDialog dialog = new JDialog();
        ClientListView clientListView = new ClientListView(clientList);
        dialog.getContentPane().add(clientListView);
        dialog.setLocationRelativeTo(this);
        dialog.setSize(500, 500);
        dialog.setVisible(true);
    }
    
    private void displayBooks(ArrayList<Book> list) {
        JDialog dialog = new JDialog();
        BookListView bookListView = new BookListView(list);
        dialog.getContentPane().add(bookListView);
        dialog.setLocationRelativeTo(this);
        dialog.setSize(500, 500);
        dialog.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBook;
    private javax.swing.JButton btnCreateClient;
    private javax.swing.JButton btnDeleteClient;
    private javax.swing.JButton btnDisplayAllClients;
    private javax.swing.JButton btnLendBook;
    private javax.swing.JButton btnLendingHistory;
    private javax.swing.JButton btnReturnBook;
    private javax.swing.JButton btnSearchClient;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnShowLent;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAddBook;
    private javax.swing.JLabel lblBookIdLend;
    private javax.swing.JLabel lblBookIdReturn;
    private javax.swing.JLabel lblClientIdLend;
    private javax.swing.JLabel lblCreateClient;
    private javax.swing.JLabel lblDeleteClient;
    private javax.swing.JLabel lblDeleteClient1;
    private javax.swing.JLabel lblHistory;
    private javax.swing.JLabel lblLendBook;
    private javax.swing.JLabel lblLendDate;
    private javax.swing.JLabel lblReturnBook;
    private javax.swing.JLabel lblReturnDate;
    private javax.swing.JPanel panelBook;
    private javax.swing.JPanel panelClient;
    private javax.swing.JTabbedPane tabMain;
    private javax.swing.JTextField txtBookIdLend;
    private javax.swing.JTextField txtBookIdReturn;
    private javax.swing.JTextField txtBookTitle;
    private javax.swing.JTextField txtClientIdLend;
    private javax.swing.JTextField txtFirstNameAdd;
    private javax.swing.JTextField txtFirstNameDel;
    private javax.swing.JTextField txtFirstNameSearch;
    private javax.swing.JTextField txtHistoryID;
    private javax.swing.JTextField txtLastNameAdd;
    private javax.swing.JTextField txtLastNameDel;
    private javax.swing.JTextField txtLastNameSearch;
    private javax.swing.JFormattedTextField txtLendDate;
    private javax.swing.JFormattedTextField txtReturnDate;
    // End of variables declaration//GEN-END:variables
}
