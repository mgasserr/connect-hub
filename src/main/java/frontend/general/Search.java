package frontend.general;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Databases.NotificationsDatabase;
import Backend.Feed.Group;
import Backend.Notifications.NotiFactory;
import frontend.groups.GroupPage;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Search extends javax.swing.JFrame {

    private Account acc;
    private Databases Database = Databases.getInstance();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private NotificationsDatabase notiDatabase = NotificationsDatabase.getInstance();
    private NotiFactory NF = new NotiFactory();

    public Search(Account acc) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
        searchList.setPreferredSize(new Dimension(258, 286));
        errorText.setText("");
        Database.read();
        notiDatabase.read();
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
        searchList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = searchList.getSelectedIndex();
                    String selectedString = searchList.getSelectedValue();
                    if (selectedIndex != -1) {
                        if (selectedString.endsWith(" -FRIEND")) {
                            viewButton.setVisible(false);
                            leaveButton.setVisible(false);
                            reqtojoinButton.setVisible(false);
                            removefriendButton.setVisible(true);
                            addfriendButton.setVisible(false);
                            blockuserButton.setVisible(true);
                        } else if (selectedString.endsWith(" -USER")) {
                            viewButton.setVisible(false);
                            leaveButton.setVisible(false);
                            reqtojoinButton.setVisible(false);
                            removefriendButton.setVisible(false);
                            addfriendButton.setVisible(true);
                            blockuserButton.setVisible(true);
                        } else if (selectedString.endsWith(" -GROUP")) {
                            for (Group group : Group.getGroups()) {
                                if (group.getName().equals(selectedString.replace(" -GROUP", ""))) {
                                    if ((group.getCreator().getUsername().equals(acc.getUsername()))) {
                                        viewButton.setVisible(true);
                                        leaveButton.setVisible(false);
                                        reqtojoinButton.setVisible(false);
                                        removefriendButton.setVisible(false);
                                        addfriendButton.setVisible(false);
                                        blockuserButton.setVisible(false);
                                        break;
                                    } else if ((group.isMember(acc.getUsername()))) {
                                        viewButton.setVisible(true);
                                        leaveButton.setVisible(true);
                                        reqtojoinButton.setVisible(false);
                                        removefriendButton.setVisible(false);
                                        addfriendButton.setVisible(false);
                                        blockuserButton.setVisible(false);
                                        break;
                                    } else {
                                        viewButton.setVisible(false);
                                        leaveButton.setVisible(false);
                                        reqtojoinButton.setVisible(true);
                                        removefriendButton.setVisible(false);
                                        addfriendButton.setVisible(false);
                                        blockuserButton.setVisible(false);
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        searchList = new javax.swing.JList<>();
        addfriendButton = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        blockuserButton = new javax.swing.JButton();
        removefriendButton = new javax.swing.JButton();
        reqtojoinButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        leaveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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
        jLabel1.setText("Search for Groups or Users");

        jScrollPane1.setViewportView(searchList);

        addfriendButton.setBackground(new java.awt.Color(0, 204, 204));
        addfriendButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addfriendButton.setText("ADD FRIEND");
        addfriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addfriendButtonActionPerformed(evt);
            }
        });

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        errorText.setForeground(new java.awt.Color(255, 0, 0));

        blockuserButton.setBackground(new java.awt.Color(0, 204, 204));
        blockuserButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        blockuserButton.setText("BLOCK");
        blockuserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockuserButtonActionPerformed(evt);
            }
        });

        removefriendButton.setBackground(new java.awt.Color(0, 204, 204));
        removefriendButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        removefriendButton.setText("REMOVE FRIEND");
        removefriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removefriendButtonActionPerformed(evt);
            }
        });

        reqtojoinButton.setBackground(new java.awt.Color(0, 204, 204));
        reqtojoinButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reqtojoinButton.setText("REQUEST TO JOIN");
        reqtojoinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqtojoinButtonActionPerformed(evt);
            }
        });

        viewButton.setBackground(new java.awt.Color(0, 204, 204));
        viewButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        viewButton.setText("VIEW GROUP");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        leaveButton.setBackground(new java.awt.Color(0, 204, 204));
        leaveButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        leaveButton.setText("LEAVE GROUP");
        leaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(searchText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(leaveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blockuserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addfriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removefriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reqtojoinButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Home)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorText, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewButton)
                        .addGap(18, 18, 18)
                        .addComponent(leaveButton)
                        .addGap(18, 18, 18)
                        .addComponent(reqtojoinButton)
                        .addGap(18, 18, 18)
                        .addComponent(removefriendButton)
                        .addGap(18, 18, 18)
                        .addComponent(addfriendButton)
                        .addGap(18, 18, 18)
                        .addComponent(blockuserButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        Database.read();
        notiDatabase.read();
        errorText.setText("");
        if (searchText.getText().equals("")) {
            errorText.setForeground(Color.red);
            errorText.setText("Search field is empty");
        } else {
            listModel.clear();
            ArrayList<Account> alluserslist = Database.getAllAccounts();
            alluserslist.remove(acc.getUsername());
            for (Account user : alluserslist) {
                if (user.getUsername().startsWith(searchText.getText())) {
                    if (Database.getAccount(acc.getUsername()).getFriendsManagement().hasFriend(user.getUsername())) {
                        listModel.addElement(user.getUsername() + " -FRIEND");
                    } else {
                        listModel.addElement(user.getUsername() + " -USER");
                    }
                }
            }
            for (Group group : Group.getGroups()) {
                if (group.getName().startsWith(searchText.getText())) {
                    listModel.addElement(group.getName() + " -GROUP");
                }
            }
            searchList.setModel(listModel);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void addfriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addfriendButtonActionPerformed
        Database.read();
        notiDatabase.read();
        errorText.setText("");
        int i = searchList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No account selected");
        } else {
            Database.getAccount(acc.getUsername()).getFriendsManagement().sendFriendRequest(searchList.getSelectedValue().replace(" -USER", ""), acc.getUsername());
            Database.getAccount(searchList.getSelectedValue().replace(" -USER", "")).addNotification(NF.CreateNoti("FriendRequest", null, false, acc.getUsername(), null, null));
            Database.save();
            notiDatabase.save();
            listModel.clear();
            searchList.setModel(listModel);
            searchText.setText("");
            errorText.setForeground(Color.black);
            errorText.setText("Friend request sent!");
        }
    }//GEN-LAST:event_addfriendButtonActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void blockuserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockuserButtonActionPerformed
        errorText.setText("");
        int i = searchList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No user selected");
        } else {
            Database.read();
            notiDatabase.read();
            String tempuser = null;
            if (searchList.getSelectedValue().endsWith(" -USER")) {
                tempuser = searchList.getSelectedValue().replace(" -USER", "");
            } else if (searchList.getSelectedValue().endsWith(" -FRIEND")) {
                tempuser = searchList.getSelectedValue().replace(" -FRIEND", "");
            }
            Database.getAccount(acc.getUsername()).getFriendsManagement().Block(tempuser, acc.getUsername());
            Database.save();
            notiDatabase.save();
            listModel.clear();
            searchList.setModel(listModel);
            searchText.setText("");
            errorText.setForeground(Color.black);
            errorText.setText("Account blocked!");
        }
    }//GEN-LAST:event_blockuserButtonActionPerformed

    private void removefriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removefriendButtonActionPerformed
        // TODO add your handling code here:
        errorText.setText("");
        int i = searchList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No user selected");
        } else {
            Database.read();
            Database.getAccount(acc.getUsername()).getFriendsManagement().deleteFriend(searchList.getSelectedValue().replace(" -FRIEND", ""), acc.getUsername());
            Database.save();
            listModel.clear();
            searchList.setModel(listModel);
            errorText.setForeground(Color.black);
            errorText.setText("Friend removed!");
        }
    }//GEN-LAST:event_removefriendButtonActionPerformed

    private void reqtojoinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqtojoinButtonActionPerformed
        Database.read();
        notiDatabase.read();
        errorText.setText("");
        int i = searchList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No group selected");
        } else if (Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).isRequest(acc.getUsername())) {
            errorText.setText("Already requested");
        } else {
            Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).addRequest(acc.getUsername());
            for (Account account : Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).getAdmins()) {
                Database.getAccount(account.getUsername()).addNotification(NF.CreateNoti("MemberRequest", null, false, searchList.getSelectedValue().replace(" -GROUP", ""), acc.getUsername(), null));
            }
            Database.save();
            notiDatabase.save();
            listModel.clear();
            searchList.setModel(listModel);
            searchText.setText("");
            errorText.setForeground(Color.black);
            errorText.setText("Request to join sent!");
        }
    }//GEN-LAST:event_reqtojoinButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        Database.read();
        errorText.setText("");
        int i = searchList.getSelectedIndex();
        if (i == -1) {
            errorText.setForeground(Color.red);
            errorText.setText("No group selected");
        } else {
            GroupPage GP = new GroupPage(Database.getAccount(acc.getUsername()), Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")));
            Database.save();
            GP.setVisible(true);
            this.setVisible(false);
            listModel.clear();
            searchList.setModel(listModel);
            searchText.setText("");
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void leaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveButtonActionPerformed
        Database.read();
        if (Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).isAdmin(acc.getUsername())) {
            Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).removeAdmin(acc.getUsername());
            Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).removeMember(acc.getUsername());
        } else {
            Database.getGroup(searchList.getSelectedValue().replace(" -GROUP", "")).removeMember(acc.getUsername());
        }
        Database.save();
    }//GEN-LAST:event_leaveButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton addfriendButton;
    private javax.swing.JButton blockuserButton;
    private javax.swing.JLabel errorText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton leaveButton;
    private javax.swing.JButton removefriendButton;
    private javax.swing.JButton reqtojoinButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JList<String> searchList;
    private javax.swing.JTextField searchText;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
