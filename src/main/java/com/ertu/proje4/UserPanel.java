/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ertu.proje4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author ergul
 */
public class UserPanel extends javax.swing.JFrame {

    /**
     * Creates new form UserPanel
     */
    public UserPanel(String uuid) {
        this.uuid = uuid;
        initComponents();
        
        try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username, 
                DBCredentials.db_password)) {
            // Retrieve the user's info to be displayed
            String query = "SELECT email, password, fullname, birthday FROM members WHERE uuid = ?";
            
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, uuid);
                
                ResultSet result_set = statement.executeQuery();
                if (result_set.next()) {
                    String admin_email = result_set.getString("email");
                    String admin_password = result_set.getString("password");
                    String admin_fullname = result_set.getString("fullname");
                    
                    java.sql.Date sql_date = result_set.getDate("birthday");
                    LocalDate admin_birthday = sql_date.toLocalDate();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String birthday_str = admin_birthday.format(dateFormatter);
                    
                    nameLabel.setText(admin_fullname);
                    emailLabel.setText(admin_email);
                    
                    String password_str = "*".repeat(admin_password.length());
                    passwordLabel.setText(password_str);
                    
                    birthdayLabel.setText(birthday_str);
                    
                    uuidLabel.setText(uuid);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Database error!", JOptionPane.ERROR_MESSAGE);
        }
        
        try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username, 
                DBCredentials.db_password)) {
            // Check the database to see if the user borrowed a book
            String query = "SELECT * FROM borrows WHERE user_uuid = ?";
            
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, uuid);
                
                ResultSet result_set = statement.executeQuery();
                // If the user borrowed one...
                if (result_set.next()) {
                    book_taken = true;
                    String book_uuid = result_set.getString("book_uuid");
                    // Retrieve the book's information to be displayed
                    String book_query = "SELECT * FROM books where uuid = ?";
                    
                    try (PreparedStatement book_statement = conn.prepareStatement(book_query)) {
                        book_statement.setString(1, book_uuid);
                        
                        ResultSet book_rs = book_statement.executeQuery();
                        if (book_rs.next()) {
                            String book_name = book_rs.getString("name");
                            String book_author = book_rs.getString("author");
                            String book_isbn = book_rs.getString("isbn");
                            String book_publisher = book_rs.getString("publisher");
                            int book_edition = book_rs.getInt("edition");
                            String book_category = book_rs.getString("category");
                            
                            java.sql.Date book_date = book_rs.getDate("duedate");
                            String book_date_str = book_date.toLocalDate().format(date_formatter);
                            
                            bookNameLabel.setText(book_name);
                            bookAuthorLabel.setText(book_author);
                            bookISBNLabel.setText(book_isbn);
                            bookPublisherLabel.setText(book_publisher);
                            bookEditionLabel.setText(String.valueOf(book_edition));
                            bookCategoryLabel.setText(book_category);
                            bookDateLabel.setText(book_date_str);
                            bookUUIDLabel.setText(book_uuid);
                        }
                    }
                } else {
                    // If the user didn't borrow any book, then set the initial text to show the situation.
                    book_taken = false;
                    bookNameLabel.setText("You don't have a book");
                    bookAuthorLabel.setText("You don't have a book");
                    bookISBNLabel.setText("You don't have a book");
                    bookPublisherLabel.setText("You don't have a book");
                    bookEditionLabel.setText("You don't have a book");
                    bookCategoryLabel.setText("You don't have a book");
                    bookDateLabel.setText("You don't have a book");
                    bookUUIDLabel.setText("You don't have a book");
                }
            }  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Database error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        birthdayLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        uuidLabel = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bookNameLabel = new javax.swing.JLabel();
        bookAuthorLabel = new javax.swing.JLabel();
        bookPublisherLabel = new javax.swing.JLabel();
        bookEditionLabel = new javax.swing.JLabel();
        bookCategoryLabel = new javax.swing.JLabel();
        bookISBNLabel = new javax.swing.JLabel();
        bookDateLabel = new javax.swing.JLabel();
        bookUUIDLabel = new javax.swing.JLabel();
        selectReturnButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Welcome!", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Name:");

        nameLabel.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Email:");

        emailLabel.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Birthday:");

        birthdayLabel.setText("__/__/____");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Password:");

        passwordLabel.setText("jLabel8");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("UUID:");

        uuidLabel.setText("jLabel10");

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(birthdayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uuidLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logOutButton)
                .addGap(18, 18, 18)
                .addComponent(updateButton)
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameLabel))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(emailLabel))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(passwordLabel))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(birthdayLabel))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(uuidLabel))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateButton)
                    .addComponent(logOutButton))
                .addGap(16, 16, 16))
        );

        jTabbedPane1.addTab("INFORMATION", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Book", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12))); // NOI18N

        jLabel2.setText("Name:");

        jLabel4.setText("Author:");

        jLabel6.setText("Edition:");

        jLabel8.setText("Publisher:");

        jLabel10.setText("Category:");

        jLabel11.setText("ISBN:");

        jLabel12.setText("Due Date:");

        jLabel13.setText("UUID:");

        bookNameLabel.setText("jLabel14");

        bookAuthorLabel.setText("jLabel15");

        bookPublisherLabel.setText("jLabel16");

        bookEditionLabel.setText("jLabel17");

        bookCategoryLabel.setText("jLabel18");

        bookISBNLabel.setText("jLabel19");

        bookDateLabel.setText("jLabel20");

        bookUUIDLabel.setText("jLabel21");

        selectReturnButton.setText("Select/Return");
        selectReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectReturnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(selectReturnButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(bookUUIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(109, 109, 109)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bookAuthorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookPublisherLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookEditionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookISBNLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bookNameLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(bookAuthorLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(bookPublisherLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(bookEditionLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(bookCategoryLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(bookISBNLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(bookDateLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(bookUUIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectReturnButton)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("BOOK", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // Redirect the user to account update panel with the info set as initial texts
        AccountUpdate update_panel = new AccountUpdate(this, true, uuid, nameLabel.getText(),
            birthdayLabel.getText(), passwordLabel.getText(), emailLabel.getText());
        update_panel.setLocationRelativeTo(this);
        update_panel.setVisible(true);
    }//GEN-LAST:event_updateButtonActionPerformed

    private void selectReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectReturnButtonActionPerformed
        if (book_taken) {
            // If the user borrowed a book, then return it
            try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username,
                    DBCredentials.db_password)) {
                String book_uuid = bookUUIDLabel.getText();
                // First query deletes the book from borrowed ones' list
                // Second query updates the book's info to convey it is available
                String borrow_query = "DELETE FROM borrows WHERE user_uuid = '" + uuid + "'";
                String book_query = "UPDATE books SET duedate = NULL WHERE uuid = '" + book_uuid + "'";
                
                // Queries are executed in a batch
                try (Statement statement = conn.createStatement()) {
                    statement.addBatch(borrow_query);
                    statement.addBatch(book_query);
                    int[] result = statement.executeBatch();
                    // Check if both queries are successful
                    if (result[0] > 0 && result[1] > 0) {
                        JOptionPane.showMessageDialog(this, "Returned the book successfully.\nNow you can borrow another one.");
                    } else {
                        JOptionPane.showMessageDialog(this, "An error occurred in the backend. Contact the system admin",
                                "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Database error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // If the user doesn't have any book, then redirect him/her to book search panel
            BookPanel book_panel = new BookPanel(this, true, uuid);
            book_panel.setLocationRelativeTo(this);
            book_panel.setVisible(true);
        }
    }//GEN-LAST:event_selectReturnButtonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // Redisplay the info every time the window gains focus(eg. returning from account update or book borrowing)
        // to show updated info
        try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username, 
                DBCredentials.db_password)) {
            String query = "SELECT email, password, fullname, birthday FROM members WHERE uuid = ?";
            
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, uuid);
                
                ResultSet result_set = statement.executeQuery();
                if (result_set.next()) {
                    String admin_email = result_set.getString("email");
                    String admin_password = result_set.getString("password");
                    String admin_fullname = result_set.getString("fullname");
                    
                    java.sql.Date sql_date = result_set.getDate("birthday");
                    LocalDate admin_birthday = sql_date.toLocalDate();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String birthday_str = admin_birthday.format(dateFormatter);
                    
                    nameLabel.setText(admin_fullname);
                    emailLabel.setText(admin_email);
                    
                    String password_str = "*".repeat(admin_password.length());
                    passwordLabel.setText(password_str);
                    
                    birthdayLabel.setText(birthday_str);
                    
                    uuidLabel.setText(uuid);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Database error!", JOptionPane.ERROR_MESSAGE);
        }
        
        try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username, 
                DBCredentials.db_password)) {
            String query = "SELECT * FROM borrows WHERE user_uuid = ?";
            
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, uuid);
                
                ResultSet result_set = statement.executeQuery();
                if (result_set.next()) {
                    book_taken = true;
                    String book_uuid = result_set.getString("book_uuid");
                    String book_query = "SELECT * FROM books where uuid = ?";
                    
                    try (PreparedStatement book_statement = conn.prepareStatement(book_query)) {
                        book_statement.setString(1, book_uuid);
                        
                        ResultSet book_rs = book_statement.executeQuery();
                        if (book_rs.next()) {
                            String book_name = book_rs.getString("name");
                            String book_author = book_rs.getString("author");
                            String book_isbn = book_rs.getString("isbn");
                            String book_publisher = book_rs.getString("publisher");
                            int book_edition = book_rs.getInt("edition");
                            String book_category = book_rs.getString("category");
                            
                            java.sql.Date book_date = book_rs.getDate("duedate");
                            String book_date_str = book_date.toLocalDate().format(date_formatter);
                            
                            bookNameLabel.setText(book_name);
                            bookAuthorLabel.setText(book_author);
                            bookISBNLabel.setText(book_isbn);
                            bookPublisherLabel.setText(book_publisher);
                            bookEditionLabel.setText(String.valueOf(book_edition));
                            bookCategoryLabel.setText(book_category);
                            bookDateLabel.setText(book_date_str);
                            bookUUIDLabel.setText(book_uuid);
                        }
                    }
                } else {
                    book_taken = false;
                    bookNameLabel.setText("You don't have a book");
                    bookAuthorLabel.setText("You don't have a book");
                    bookISBNLabel.setText("You don't have a book");
                    bookPublisherLabel.setText("You don't have a book");
                    bookEditionLabel.setText("You don't have a book");
                    bookCategoryLabel.setText("You don't have a book");
                    bookDateLabel.setText("You don't have a book");
                    bookUUIDLabel.setText("You don't have a book");
                }
            }  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Database error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // Redirect to login frame and dispose of this class
        Login login_frame = new Login();
        login_frame.setLocationRelativeTo(this);
        login_frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logOutButtonActionPerformed

    private DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String uuid;
    private boolean book_taken;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel birthdayLabel;
    private javax.swing.JLabel bookAuthorLabel;
    private javax.swing.JLabel bookCategoryLabel;
    private javax.swing.JLabel bookDateLabel;
    private javax.swing.JLabel bookEditionLabel;
    private javax.swing.JLabel bookISBNLabel;
    private javax.swing.JLabel bookNameLabel;
    private javax.swing.JLabel bookPublisherLabel;
    private javax.swing.JLabel bookUUIDLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton logOutButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton selectReturnButton;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel uuidLabel;
    // End of variables declaration//GEN-END:variables
}
