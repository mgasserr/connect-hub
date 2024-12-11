package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import frontend.general.Home;
import java.awt.Color;
import javax.swing.DefaultListModel;

public class ViewMembersList extends javax.swing.JFrame {

    Account acc;
    private Databases Database = Databases.getInstance();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ViewMembersList() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Promote = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        Remove = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        Demote = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Members List");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Promote.setBackground(new java.awt.Color(0, 204, 204));
        Promote.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Promote.setText("PROMOTE");
        Promote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PromoteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("View members List");

        jScrollPane1.setViewportView(usersList);

        Remove.setBackground(new java.awt.Color(0, 204, 204));
        Remove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Remove.setText("REMOVE");
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Demote.setBackground(new java.awt.Color(0, 204, 204));
        Demote.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Demote.setText("DEMOTE");
        Demote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DemoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Promote)
                        .addGap(72, 72, 72)
                        .addComponent(Demote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Remove))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Home)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Home))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Promote)
                    .addComponent(Demote)
                    .addComponent(Remove))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PromoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PromoteActionPerformed
        errorText.setText("");
        int i = usersList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No users selected");
        } else {
            String usernamelist = usersList.getSelectedValue();
            if (usernamelist.contains(" -ONLINE")) {
                usernamelist = usernamelist.replace(" -ONLINE", "");
            } else if (usernamelist.contains(" -OFFLINE")) {
                usernamelist = usernamelist.replace(" -OFFLINE", "");
            }
            Database.read();
            acc.getFriendsManagement().Block(usernamelist, acc.getUsername());
            Database.save();
            listModel.clear();
            for (Account user : Database.getFriendsDATABASE(acc.getUsername())) {
                listModel.addElement(user.getUsername() + " -" + user.getStatus().toString());
            }
            usersList.setModel(listModel);
            errorText.setForeground(Color.black);
            errorText.setText("Friend blocked!");
        }
    }//GEN-LAST:event_PromoteActionPerformed

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        // TODO add your handling code here:
        errorText.setText("");
        int i = usersList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No users selected");
        } else {
            String usernamelist = usersList.getSelectedValue();
            if (usernamelist.contains(" -ONLINE")) {
                usernamelist = usernamelist.replace(" -ONLINE", "");
            } else if (usernamelist.contains(" -OFFLINE")) {
                usernamelist = usernamelist.replace(" -OFFLINE", "");
            }
            Database.read();
            acc.getFriendsManagement().deleteFriend(usernamelist, acc.getUsername());
            Database.save();
            listModel.clear();
            for (Account user : Database.getFriendsDATABASE(acc.getUsername())) {
                listModel.addElement(user.getUsername() + " -" + user.getStatus().toString());
            }
            usersList.setModel(listModel);
            errorText.setForeground(Color.black);
            errorText.setText("Friend removed!");
        }
    }//GEN-LAST:event_RemoveActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void DemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DemoteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Demote;
    private javax.swing.JButton Home;
    private javax.swing.JButton Promote;
    private javax.swing.JButton Remove;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> usersList;
    // End of variables declaration//GEN-END:variables
}
