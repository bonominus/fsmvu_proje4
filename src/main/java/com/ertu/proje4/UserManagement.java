/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ertu.proje4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ergul
 */

// This class functions pretty much the same with AdminManagement. The difference is 
// that admin controls the user accounts here instead of the admin accounts.
public class UserManagement extends javax.swing.JDialog {

    /**
     * Creates new form UserManagement
     */
    public UserManagement(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        model = (DefaultTableModel)usersTable.getModel();
        date_formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    private void updateTable() {
        // Empty the table
        model.setRowCount(0);
        // Retrieve all the unprivileged users
        try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username, 
                DBCredentials.db_password)) {
            String query = "SELECT * from members WHERE admin = 0";
            
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                ResultSet result_set = statement.executeQuery();
                
                // Iterate through the users and add them to the table
                while (result_set.next()) {
                    String admin_fullname = result_set.getString("fullname");
                    String admin_uuid = result_set.getString("uuid");
                    String admin_email = result_set.getString("email");
                    
                    String admin_password = result_set.getString("password");
                    String admin_password_str = "*".repeat(admin_password.length());
                    
                    java.sql.Date sql_date = result_set.getDate("birthday");
                    LocalDate admin_birthday = sql_date.toLocalDate();
                    String admin_birthday_str = admin_birthday.format(date_formatter);
                    model.addRow(new Object[]{admin_fullname, admin_birthday_str, admin_email, admin_password_str, admin_uuid});
                }
                result_set.close();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Birthday", "Email", "Password", "UUID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(usersTable);

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        int selected_row = usersTable.getSelectedRow();
        // If the admin selected a user...
        if (selected_row != -1) {
            // Retrieve user's info
            String selected_uuid = (String)usersTable.getValueAt(selected_row, 4);

            String selected_name = (String)usersTable.getValueAt(selected_row, 0);
            String selected_birthday = (String)usersTable.getValueAt(selected_row, 1);
            String selected_email = (String)usersTable.getValueAt(selected_row, 2);
            String selected_password = (String)usersTable.getValueAt(selected_row, 3);

            // Redirect to AccountUpdate with the information used as initial values
            AccountUpdate adm_info_upd = new AccountUpdate((JFrame) this.getOwner(), true, selected_uuid, selected_name,
                selected_birthday, selected_password, selected_email);
            adm_info_upd.setLocationRelativeTo(this);
            adm_info_upd.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "You have to select a user first.",
                "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selected_row = usersTable.getSelectedRow();
        if (selected_row != -1) {
            // If the admin selected a user, get the user's uuid
            String selected_uuid = (String)usersTable.getValueAt(selected_row, 4);

            // Then delete the user
            try (Connection conn = DriverManager.getConnection(DBCredentials.db_url, DBCredentials.db_username, 
                DBCredentials.db_password)) {
                String query = "DELETE FROM members where uuid = ?";

                try (PreparedStatement statement = conn.prepareStatement(query)) {
                    statement.setString(1, selected_uuid);

                    int result = statement.executeUpdate();
                    if (result > 0) {
                        model.removeRow(selected_row);
                        JOptionPane.showMessageDialog(this, "Deleted account successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to delete account",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(),
                            "Database error!", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You have to select a user first.",
                "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // Redirect the admin to user registration frame
        Register admin_register = new Register((JFrame)this.getOwner(), true, false);
        admin_register.setLocationRelativeTo(this);
        admin_register.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // Update the table every time the user gains focus
        updateTable();
    }//GEN-LAST:event_formWindowGainedFocus

    private DefaultTableModel model;
    private DateTimeFormatter date_formatter;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton updateButton;
    private javax.swing.JTable usersTable;
    // End of variables declaration//GEN-END:variables
}
