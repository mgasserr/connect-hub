package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import Backend.Notifications.NotiFactory;
import frontend.general.Home;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultListModel;

public class ViewSuggestedGroups extends javax.swing.JFrame {

    Account acc;
    private Databases Database = Databases.getInstance();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ViewSuggestedGroups(Account acc) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
        groupsList.setPreferredSize(new Dimension(258, 286));
        listModel.clear();
        for (Group group : Group.getGroups()) {
            if (!(group.isMember(this.acc.getUsername()))) {
                if (!(group.getCreator().getUsername().equals(this.acc.getUsername()))) {
                    listModel.addElement(group.getName());
                }
            }
        }
        groupsList.setModel(listModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RequestToJoin = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupsList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Suggested Groups");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        RequestToJoin.setBackground(new java.awt.Color(255, 102, 153));
        RequestToJoin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        RequestToJoin.setText("REQUEST TO JOIN");
        RequestToJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RequestToJoinActionPerformed(evt);
            }
        });

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("Suggested groups");

        jScrollPane1.setViewportView(groupsList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(Home)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(RequestToJoin)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RequestToJoin)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RequestToJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RequestToJoinActionPerformed
        Database.read();
        errorText.setText("");
        int i = groupsList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No groups selected");
        } else if (Database.getGroup(groupsList.getSelectedValue()).isMember(acc.getUsername()) || Database.getGroup(groupsList.getSelectedValue()).getCreator().getUsername().equalsIgnoreCase(acc.getUsername())) {
            errorText.setText("Already in group");
        } else if (Database.getGroup(groupsList.getSelectedValue()).isRequest(acc.getUsername())) {
            errorText.setText("Already requested");
        } else {
            Database.getGroup(groupsList.getSelectedValue()).addRequest(acc.getUsername());
            NotiFactory NF = new NotiFactory();
            for (Account account : Database.getGroup(groupsList.getSelectedValue()).getAdmins()) {
                Database.getAccount(account.getUsername()).addNotification(NF.CreateNoti("MemberRequest", null, false, groupsList.getSelectedValue(), acc.getUsername(), null));
            }
            Database.save();
            groupsList.setModel(listModel);
            errorText.setForeground(Color.black);
            errorText.setText("Request to join sent!");
        }
    }//GEN-LAST:event_RequestToJoinActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton RequestToJoin;
    private javax.swing.JLabel errorText;
    private javax.swing.JList<String> groupsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
