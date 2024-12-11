package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import frontend.general.Home;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.DefaultListModel;

public class FindGroup extends javax.swing.JFrame {

    Account acc;
    Databases Database = Databases.getInstance();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public FindGroup(Account acc) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
        groupsList.setPreferredSize(new Dimension(258, 286));
        errorText.setText("");
        Database.read();
        searchText.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchText.getText().equals("Search...")) {
                    searchText.setText("");
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchText.getText().isEmpty()) {
                    searchText.setText("Search...");
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        groupsList = new javax.swing.JList<>();
        RequestToJoin = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Find Group");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setViewportView(groupsList);

        RequestToJoin.setBackground(new java.awt.Color(0, 204, 204));
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

        searchText.setText("Search...");
        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(0, 204, 204));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchButton.setText("SEARCH");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("Find Group");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(181, 181, 181)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RequestToJoin)
                            .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home)
                    .addComponent(jLabel1))
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(RequestToJoin)))
                .addContainerGap(40, Short.MAX_VALUE))
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
        } else if (Database.getGroup(groupsList.getSelectedValue()).isMember(acc.getUsername()) || Database.getGroup(groupsList.getSelectedValue()).getCreator().getUsername().equalsIgnoreCase(acc.getUsername())) 
        {
            errorText.setText("Already in group");
        }else if(Database.getGroup(groupsList.getSelectedValue()).isRequest(acc.getUsername())){
            errorText.setText("Already requested");
        }
        else {
            Database.getGroup(groupsList.getSelectedValue()).addRequest(acc.getUsername());
            Database.save();
            listModel.clear();
            groupsList.setModel(listModel);
            searchText.setText("");
            errorText.setForeground(Color.black);
            errorText.setText("Request to join sent!");
        }
    }//GEN-LAST:event_RequestToJoinActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
    }//GEN-LAST:event_searchTextActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        Database.read();
        errorText.setText("");
        if (searchText.getText().equals("")) {
            errorText.setForeground(Color.red);
            errorText.setText("Search field is empty");
        } else {
            listModel.clear();
            for (Group group : Group.getGroups()) {
                if (group.getName().startsWith(searchText.getText())) {
                    listModel.addElement(group.getName());
                }
            }
            groupsList.setModel(listModel);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton RequestToJoin;
    private javax.swing.JLabel errorText;
    private javax.swing.JList<String> groupsList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchText;
    // End of variables declaration//GEN-END:variables
}
