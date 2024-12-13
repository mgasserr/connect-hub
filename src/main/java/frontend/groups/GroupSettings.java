package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import frontend.general.Home;

public class GroupSettings extends javax.swing.JFrame {

    Account acc;
    Group g;
    Databases Database = Databases.getInstance();

    public GroupSettings(Account acc, Group group) {
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

        changePicture = new javax.swing.JButton();
        changeDescriptio = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        deleteGroup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Group Settings");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        changePicture.setBackground(new java.awt.Color(0, 204, 204));
        changePicture.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changePicture.setText("CHANGE GROUP PICTURE");
        changePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePictureActionPerformed(evt);
            }
        });

        changeDescriptio.setBackground(new java.awt.Color(0, 204, 204));
        changeDescriptio.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        changeDescriptio.setText("CHANGE GROUP DESCRIPTION");
        changeDescriptio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeDescriptioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel3.setText("Settings");

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        deleteGroup.setBackground(new java.awt.Color(0, 204, 204));
        deleteGroup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteGroup.setText("DELETE GROUP");
        deleteGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGroupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(230, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(changeDescriptio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(changePicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(194, 194, 194))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(498, 498, 498)
                    .addComponent(Home)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(changePicture)
                .addGap(62, 62, 62)
                .addComponent(changeDescriptio)
                .addGap(62, 62, 62)
                .addComponent(deleteGroup)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(Home))
                    .addContainerGap(303, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing

    private void changePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePictureActionPerformed
        changePicture CP = new changePicture(acc, g);
        CP.setVisible(true);
        dispose();
    }//GEN-LAST:event_changePictureActionPerformed

    private void changeDescriptioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeDescriptioActionPerformed
        changeDescription CD = new changeDescription(acc, g);
        CD.setVisible(true);
        dispose();
    }//GEN-LAST:event_changeDescriptioActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void deleteGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGroupActionPerformed
        Database.read();
        g.removeGroup(Database.getGroup(g.getName()), g.getCreator().getUsername());
        System.out.println(Group.getGroups().size() + "kk");
        Database.save();
        System.out.println(Group.getGroups().size() + "kkk");
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_deleteGroupActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton changeDescriptio;
    private javax.swing.JButton changePicture;
    private javax.swing.JButton deleteGroup;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
