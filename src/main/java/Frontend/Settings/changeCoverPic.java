/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend.Settings;

import Backend.Account.Account;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeina Hazem
 */
public class changeCoverPic extends javax.swing.JFrame {

   Account acc;
   Settings S;
   String path;
    public changeCoverPic(Account acc, Settings aThis) {
        initComponents();
        this.acc=acc;
        S=aThis;
        this.pictureLabel.setIcon(acc.getProfile().getCoverImg());
        this.setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        choosepicButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        pictureLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Cover Picture");

        choosepicButton.setBackground(new java.awt.Color(0, 204, 204));
        choosepicButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        choosepicButton.setText("UPLOAD COVER PICTURE");
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

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel2.setText("Change Cover Picture");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(confirmButton))
                            .addComponent(choosepicButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel2)))
                .addContainerGap(217, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(choosepicButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(confirmButton)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void choosepicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosepicButtonActionPerformed
       JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            File tempfile = fc.getSelectedFile();
            if (!tempfile.getName().endsWith(".png") && !tempfile.getName().endsWith(".jpg") && !tempfile.getName().endsWith(".jpeg")) {
                JOptionPane.showMessageDialog(this, "Please choose a .png/.jpg/.jpeg file only.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                this.path=tempfile.getAbsolutePath();
                ImageIcon img= new ImageIcon(path);
                this.pictureLabel.setIcon(img);
            }
        }
    }//GEN-LAST:event_choosepicButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        if(this.pictureLabel.getIcon()==null){
            JOptionPane.showMessageDialog(this, "Choose cover photo");
        }else{
            acc.getProfile().setCoverImg(this.path);
            dispose();
            S.setVisible(true);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton choosepicButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel pictureLabel;
    // End of variables declaration//GEN-END:variables
}
