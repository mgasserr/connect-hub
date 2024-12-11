package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import frontend.general.Home;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultListModel;

public class ViewRequestsList extends javax.swing.JFrame {

    Account acc;
    Group g;
    private Databases Database = Databases.getInstance();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ViewRequestsList(Account acc, Group group) {
        initComponents();
        this.acc = acc;
        this.g = group;
        usersList.setPreferredSize(new Dimension(258, 286));
        errorText.setText("");
        Database.read();
        listModel.clear();
        for (Account members : Database.getGroup(g.getName()).getMembers()) {
            listModel.addElement(members.getUsername() + " -" + members.getStatus().toString());
        }
        usersList.setModel(listModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        errorText = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        Decline = new javax.swing.JButton();
        Accept = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Requests List");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setViewportView(usersList);

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Decline.setBackground(new java.awt.Color(0, 204, 204));
        Decline.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Decline.setText("Decline");
        Decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeclineActionPerformed(evt);
            }
        });

        Accept.setBackground(new java.awt.Color(0, 204, 204));
        Accept.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Accept.setText("Accept");
        Accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("View requests List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Accept)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Decline))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Home)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(Accept)
                    .addComponent(Decline))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void DeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeclineActionPerformed
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
            Database.getGroup(g.getName()).addMember(usernamelist);
            Database.getGroup(g.getName()).removeRequest(usernamelist);
            Database.getAccount(usernamelist).addGroup(g);
            //acc.getGroup(g.getName()).addAdmin(usernamelist);
            Database.save();
            listModel.clear();
            for (Account member : Database.getGroup(g.getName()).getRequests()) {
                listModel.addElement(member.getUsername() + " -" + member.getStatus().toString());
            }
            usersList.setModel(listModel);
            errorText.setForeground(Color.black);
            errorText.setText("Accepted successfully");
        }
    }//GEN-LAST:event_DeclineActionPerformed

    private void AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcceptActionPerformed
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
            Database.getGroup(g.getName()).addMember(usernamelist);
            Database.getGroup(g.getName()).removeRequest(usernamelist);
            Database.getAccount(usernamelist).addGroup(g);
            //acc.getGroup(g.getName()).addAdmin(usernamelist);
            Database.save();
            listModel.clear();
            for (Account member : Database.getGroup(g.getName()).getRequests()) {
                listModel.addElement(member.getUsername() + " -" + member.getStatus().toString());
            }
            usersList.setModel(listModel);
            errorText.setForeground(Color.black);
            errorText.setText("Accepted successfully");
        }
    }//GEN-LAST:event_AcceptActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Accept;
    private javax.swing.JButton Decline;
    private javax.swing.JButton Home;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> usersList;
    // End of variables declaration//GEN-END:variables
}
