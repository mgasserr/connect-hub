package frontend.settings;

import Backend.Account.Account;
import Backend.Authentication.PasswordHash;
import Backend.Databases.Databases;
import frontend.general.Home;
import javax.swing.JOptionPane;

public class changePass extends javax.swing.JFrame {

    Account acc;
    Settings S;
    Databases Database = Databases.getInstance();

    public changePass(Account acc, Settings aThis) {
        initComponents();
        this.acc = acc;
        S = aThis;
        this.setLocationRelativeTo(null);
        setResizable(false);
        Database.read();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        oldpass = new javax.swing.JPasswordField();
        newpass = new javax.swing.JPasswordField();
        newpassconf = new javax.swing.JPasswordField();
        doneButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        Home = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change Password");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("New Password");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ENTER OLD PASSWORD");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("ENTER NEW PASSWORD");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("CONFIRM NEW PASSWORD");

        oldpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldpassActionPerformed(evt);
            }
        });

        newpassconf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newpassconfActionPerformed(evt);
            }
        });

        doneButton.setBackground(new java.awt.Color(0, 204, 153));
        doneButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        doneButton.setText("DONE");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(0, 204, 153));
        backButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        backButton.setText("BACK");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Home))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(doneButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 353, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(oldpass, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newpass, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(newpassconf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(oldpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(newpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(newpassconf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(doneButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void oldpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldpassActionPerformed

    private void newpassconfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newpassconfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newpassconfActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        if (this.oldpass.getText().equals("") || this.newpass.getText().equals("") || this.newpassconf.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Fill all feilds");
        } else {
            String old = oldpass.getText();
            String newpassword = newpass.getText();
            String newpassconfirm = newpassconf.getText();
            Database.read();
            switch (PasswordHash.changePassword(Database.getAccount(acc.getUsername()), old, newpassword, newpassconfirm)) {
                case "INVALIDOLDPASS" -> {
                    JOptionPane.showMessageDialog(this, "Invalid old password.");
                }
                case "INVALIDCONFIRMPASS" -> {
                    JOptionPane.showMessageDialog(this, "New password doesn't match");
                }
                case "PASSWORDCHANGED" -> {
                    JOptionPane.showMessageDialog(this, "Password changed successfully!");
                    Database.save();
                    dispose();
                    S.setVisible(true);
                }
            }

        }
    }//GEN-LAST:event_doneButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        S.setVisible(true);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton backButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField newpass;
    private javax.swing.JPasswordField newpassconf;
    private javax.swing.JPasswordField oldpass;
    // End of variables declaration//GEN-END:variables
}
