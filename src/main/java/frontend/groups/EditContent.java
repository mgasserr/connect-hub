package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EditContent extends javax.swing.JFrame {

    Account acc;
    Group g;
    String temp;
    String path;
    GroupPage gp;
    Databases Database = Databases.getInstance();

    public EditContent(Account acc, Group group, String temp, GroupPage gp) {
        initComponents();
        Database.read();
        this.acc = acc;
        this.g = group;
        this.temp = temp;
        this.gp = gp;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newCaption = new javax.swing.JButton();
        newPicture = new javax.swing.JButton();
        caption = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        CONFIRM = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Post");

        newCaption.setBackground(new java.awt.Color(0, 204, 204));
        newCaption.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        newCaption.setText("CHANGE CAPTION");
        newCaption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCaptionActionPerformed(evt);
            }
        });

        newPicture.setBackground(new java.awt.Color(0, 204, 204));
        newPicture.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        newPicture.setText("CHANGE PICTURE");
        newPicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPictureActionPerformed(evt);
            }
        });

        jLabel1.setText("New Caption:");

        CONFIRM.setBackground(new java.awt.Color(0, 204, 204));
        CONFIRM.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CONFIRM.setText("CONFIRM");
        CONFIRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CONFIRMActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caption, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(newCaption, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(CONFIRM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(newCaption)
                .addGap(18, 18, 18)
                .addComponent(newPicture)
                .addGap(18, 18, 18)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(CONFIRM)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newCaptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCaptionActionPerformed
        if (caption.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter the new caption!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Map<String, String> c = new HashMap<>();
            c.put("Text", caption.getText());
            c.put("Path", Database.getGroup(g.getName()).getcontent(temp).getContentMap().get("Path"));
            Database.getGroup(g.getName()).getcontent(temp).setContentMap(c);
            errorText.setForeground(Color.green);
            errorText.setText("Caption changed!");
        }
    }//GEN-LAST:event_newCaptionActionPerformed

    private void newPictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPictureActionPerformed
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            File tempfile = fc.getSelectedFile();
            if (!tempfile.getName().endsWith(".png") && !tempfile.getName().endsWith(".jpg") && !tempfile.getName().endsWith(".jpeg")) {
                JOptionPane.showMessageDialog(this, "Please choose a .png/.jpg/.jpeg file only.", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                this.path = tempfile.getAbsolutePath();
                Map<String, String> c = new HashMap<>();
                c.put("Text", Database.getGroup(g.getName()).getcontent(temp).getContentMap().get("Text"));
                c.put("Path", path);
                Database.getGroup(g.getName()).getcontent(temp).setContentMap(c);
                errorText.setForeground(Color.green);
                errorText.setText("Picture changed!");
            }
        }
    }//GEN-LAST:event_newPictureActionPerformed

    private void CONFIRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CONFIRMActionPerformed
        Database.save();
        dispose();
        GroupPage GP = new GroupPage(acc, g);
        gp.setVisible(false);
        GP.setVisible(true);
    }//GEN-LAST:event_CONFIRMActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CONFIRM;
    private javax.swing.JTextField caption;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton newCaption;
    private javax.swing.JButton newPicture;
    // End of variables declaration//GEN-END:variables
}
