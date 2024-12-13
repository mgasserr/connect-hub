package frontend.groups.groupsInfo;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Databases.NotificationsDatabase;
import Backend.Feed.Group;
import Backend.Notifications.NotiFactory;
import frontend.general.Home;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ViewMembersList extends javax.swing.JFrame {

    private Account acc;
    private Group g;
    private Databases Database = Databases.getInstance();
    private NotificationsDatabase notiDatabase = NotificationsDatabase.getInstance();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private NotiFactory NF = new NotiFactory();

    public ViewMembersList(Account acc, Group group) {
        initComponents();
        Database.read();
        notiDatabase.read();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
        this.g = group;
        usersList.setPreferredSize(new Dimension(258, 286));
        errorText.setText("");
        listModel.clear();
        for (Account members : Database.getGroup(g.getName()).getMembers()) {
            listModel.addElement(members.getUsername() + " -" + members.getStatus().toString());
        }
        usersList.setModel(listModel);
        usersList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = usersList.getSelectedIndex();
                    String selectedString = usersList.getSelectedValue();
                    if (selectedIndex != -1) {
                        if (selectedString.replace(" -OFFLINE", "").equals(acc.getUsername()) || selectedString.replace(" -ONLINE", "").equals(acc.getUsername())) {
                            removeButton.setVisible(false);
                        } else {
                            removeButton.setVisible(true);
                        }
                    }
                }
            }
        });
        Promote.setVisible(true);
        Demote.setVisible(true);
        removeButton.setVisible(true);
        if (Database.getGroup(g.getName()).isMember(acc.getUsername())) {
            Promote.setVisible(false);
            Demote.setVisible(false);
            removeButton.setVisible(false);
        }
        if (Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
            removeButton.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Promote = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        removeButton = new javax.swing.JButton();
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

        Promote.setBackground(new java.awt.Color(0, 204, 153));
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

        removeButton.setBackground(new java.awt.Color(0, 204, 153));
        removeButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        removeButton.setText("REMOVE");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Demote.setBackground(new java.awt.Color(0, 204, 153));
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
                        .addComponent(removeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Home)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(removeButton))
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
            notiDatabase.read();
            if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
                if (Database.getGroup(g.getName()).isAdmin(usernamelist)) {
                    errorText.setText("User already admin");
                } else {
                    Database.getGroup(g.getName()).addAdmin(usernamelist);
                    Database.getAccount(usernamelist).addNotification(NF.CreateNoti("RoleChange", null, false, g.getName(), "Admin", null));
                    Database.save();
                    notiDatabase.save();
                    listModel.clear();
                    for (Account member : Database.getGroup(g.getName()).getMembers()) {
                        listModel.addElement(member.getUsername() + " -" + member.getStatus().toString());
                    }
                    usersList.setModel(listModel);
                    errorText.setForeground(Color.black);
                    errorText.setText("Promoted successfully");
                }
            } else {
                errorText.setForeground(Color.black);
                errorText.setText("You must be primary admin");
            }
        }
    }//GEN-LAST:event_PromoteActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
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
            notiDatabase.read();
            if (acc.getUsername().equals(g.getCreator().getUsername())) {
                // Creator logic
                if (Database.getGroup(g.getName()).isAdmin(usernamelist)) {
                    Database.getGroup(g.getName()).removeAdmin(usernamelist);
                }
                if (Database.getGroup(g.getName()).isMember(usernamelist)) {
                    Database.getGroup(g.getName()).removeMember(usernamelist);
                    Database.getAccount(usernamelist).removeALLGroupNotisbyGroupName(g.getName());
                    errorText.setForeground(Color.green);
                    errorText.setText("Member removed!");
                }
            } else if (Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
                // Admin logic
                if (Database.getGroup(g.getName()).isAdmin(usernamelist) || g.getCreator().getUsername().equals(usernamelist)) {
                    errorText.setForeground(Color.red);
                    errorText.setText("You can't remove someone in the same position or higher!");
                } else {
                    Database.getGroup(g.getName()).removeMember(usernamelist);
                    Database.getAccount(usernamelist).removeALLGroupNotisbyGroupName(g.getName());
                    errorText.setForeground(Color.green);
                    errorText.setText("Member removed!");
                }
            } else {
                // No permission
                errorText.setForeground(Color.red);
                errorText.setText("You don't have permission to remove this member!");
            }
            Database.save();
            notiDatabase.save();
            listModel.clear();
            for (Account member : Database.getGroup(g.getName()).getMembers()) {
                listModel.addElement(member.getUsername() + " -" + member.getStatus().toString());
            }
            usersList.setModel(listModel);

        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void DemoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DemoteActionPerformed
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
            notiDatabase.read();
            if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
                if (!Database.getGroup(g.getName()).isAdmin(usernamelist)) {
                    errorText.setText("User is not admin");
                } else {
                    Database.getGroup(g.getName()).removeAdmin(usernamelist);
                    Database.getAccount(usernamelist).addNotification(NF.CreateNoti("RoleChange", null, false, g.getName(), "Member", null));
                    Database.save();
                    notiDatabase.save();
                    listModel.clear();
                    for (Account member : Database.getGroup(g.getName()).getMembers()) {
                        listModel.addElement(member.getUsername() + " -" + member.getStatus().toString());
                    }
                    usersList.setModel(listModel);
                    errorText.setForeground(Color.black);
                    errorText.setText("Demoted successfully");
                }
            } else {
                errorText.setForeground(Color.black);
                errorText.setText("You must be primary admin");
            }
        }


    }//GEN-LAST:event_DemoteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Demote;
    private javax.swing.JButton Home;
    private javax.swing.JButton Promote;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JList<String> usersList;
    // End of variables declaration//GEN-END:variables
}
