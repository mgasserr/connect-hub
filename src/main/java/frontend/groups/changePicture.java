package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import frontend.general.Home;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class changePicture extends javax.swing.JFrame {

    Account acc;
    Group g;
    Databases Database = Databases.getInstance();
    String path;

    public changePicture(Account acc, Group group) {
        initComponents();
        Database.read();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
        this.g = group;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        picture = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        choosepicButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chnage Group Picture");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel2.setText("Change Group Picture");

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        choosepicButton.setBackground(new java.awt.Color(0, 204, 204));
        choosepicButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        choosepicButton.setText("UPLOAD PICTURE");
        choosepicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosepicButtonActionPerformed(evt);
            }
        });

        confirmButton.setBackground(new java.awt.Color(0, 204, 204));
        confirmButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmButton.setText("CONFIRM");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(choosepicButton)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Home)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmButton)
                            .addComponent(picture, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(187, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(picture, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(choosepicButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(confirmButton)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void choosepicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosepicButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        Database.read();
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            File tempfile = fc.getSelectedFile();
            if (!tempfile.getName().endsWith(".png") && !tempfile.getName().endsWith(".jpg") && !tempfile.getName().endsWith(".jpeg")) {
                JOptionPane.showMessageDialog(this, "Please choose a .png/.jpg/.jpeg file only.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                this.path = tempfile.getAbsolutePath();
                ImageIcon img = new ImageIcon(path);
                this.picture.setIcon(img);
                Database.save();
            }
        }
    }//GEN-LAST:event_choosepicButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        Database.read();
        if (this.picture.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Choose profile photo");
        } else {
            Database.getGroup(g.getName()).setPicture(path);
            Database.save();
            Home home = new Home(acc);
            home.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton choosepicButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel picture;
    // End of variables declaration//GEN-END:variables
}
