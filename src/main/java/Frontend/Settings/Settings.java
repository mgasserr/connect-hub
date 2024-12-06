/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.settings;

import Backend.Account.Account;
import Backend.Authentication.Register;
import Backend.Databases.Database;
import frontend.general.Home;
import frontend.general.Startup;

/**
 *
 * @author Zeina Hazem
 */
public class Settings extends javax.swing.JFrame {

   Account acc;
    public Settings(Account acc) {
        initComponents();
        this.acc=acc;
        this.setLocationRelativeTo(null);
        setResizable(false);
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        profilepic = new javax.swing.JLabel();
        coverpic = new javax.swing.JLabel();
        changepassButton = new javax.swing.JButton();
        changeprofileButton = new javax.swing.JButton();
        changecoverButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        changebioButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        Home = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Settings");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        changepassButton.setBackground(new java.awt.Color(0, 204, 204));
        changepassButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changepassButton.setText("CHANGE PASSWORD");
        changepassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changepassButtonActionPerformed(evt);
            }
        });

        changeprofileButton.setBackground(new java.awt.Color(0, 204, 204));
        changeprofileButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changeprofileButton.setText("CHANGE PROFILE PICTURE");
        changeprofileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeprofileButtonActionPerformed(evt);
            }
        });

        changecoverButton.setBackground(new java.awt.Color(0, 204, 204));
        changecoverButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changecoverButton.setText("CHANGE COVER PHOTO");
        changecoverButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changecoverButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel3.setText("Settings");

        changebioButton.setBackground(new java.awt.Color(0, 204, 204));
        changebioButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changebioButton.setText("CHANGE BIO");
        changebioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changebioButtonActionPerformed(evt);
            }
        });

        logoutButton.setBackground(new java.awt.Color(0, 204, 204));
        logoutButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logoutButton.setText("LOGOUT");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changeprofileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changepassButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changecoverButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(changebioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coverpic, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profilepic, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Home)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(username)
                            .addComponent(profilepic)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Home))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email)
                            .addComponent(coverpic))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(changepassButton)
                        .addGap(48, 48, 48)
                        .addComponent(changeprofileButton)
                        .addGap(50, 50, 50)
                        .addComponent(changecoverButton)
                        .addGap(43, 43, 43)
                        .addComponent(changebioButton)
                        .addGap(46, 46, 46)
                        .addComponent(logoutButton)
                        .addContainerGap(134, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changepassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changepassButtonActionPerformed
        changePass CP= new changePass(acc,this);
        CP.setVisible(true);
        dispose();
    }//GEN-LAST:event_changepassButtonActionPerformed

    private void changeprofileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeprofileButtonActionPerformed
       changeProfilePic CPR=new changeProfilePic(acc,this);
        CPR.setVisible(true);
        dispose();
    }//GEN-LAST:event_changeprofileButtonActionPerformed

    private void changebioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changebioButtonActionPerformed
        changeBio CB=new changeBio(acc,this);
        CB.setVisible(true);
        dispose();
    }//GEN-LAST:event_changebioButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
      Database.saveAll();
    }//GEN-LAST:event_formWindowClosing

    private void changecoverButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changecoverButtonActionPerformed
        changeCoverPic CC= new changeCoverPic(acc,this);
        CC.setVisible(true);
        dispose();
    }//GEN-LAST:event_changecoverButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        Register R=Register.getInstance();
        R.logout(acc);
        Startup S= new Startup();
        S.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Database.refreshDatabase();
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton changebioButton;
    private javax.swing.JButton changecoverButton;
    private javax.swing.JButton changepassButton;
    private javax.swing.JButton changeprofileButton;
    private javax.swing.JLabel coverpic;
    private javax.swing.JLabel email;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel profilepic;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
