package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Content;
import Backend.Feed.Group;
import frontend.general.DisplayContent;
import frontend.general.Home;
import java.nio.file.Path;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GroupPage extends javax.swing.JFrame {

    Account acc;
    Group g;
    Databases Database = Databases.getInstance();
    String[] temp;

    public GroupPage(Account acc, Group group) {
        initComponents();
        Database.read();
        this.setLocationRelativeTo(null);
        setResizable(false);

        this.acc = acc;
        this.g = group;
        Picture.setIcon(Database.getGroup(group.getName()).getPicture());
        name.setText(Database.getGroup(group.getName()).getName());
        description.setText(Database.getGroup(group.getName()).getDescription());

        DefaultListModel<String> postsFeedModel = new DefaultListModel<>(); // Initialize DefaultListModel
        for (int j = 0; j < Database.getGroup(g.getName()).getContent().size(); j++) {
            // Extract content details
            String time = Database.getGroup(g.getName()).getContent().get(j).getTime().toString();
            String text = (String) Database.getGroup(g.getName()).getContent().get(j).getContentMap().get("Text");
            String path = (String) Database.getGroup(g.getName()).getContent().get(j).getContentMap().get("Path");
            // Format the data for display
            String listItem = String.format("%s~%s~%s~%s~%s", "Content", Database.getAccountbyID(Database.getGroup(g.getName()).getContent().get(j).getAuthorId()).getUsername(), time, text != null ? text : "No Text", path != null ? path : "No Path");
            // Add the formatted string to the DefaultListModel
            postsFeedModel.addElement(listItem);
        }
        postsList.setModel(postsFeedModel);

        Settings.setVisible(false);
        ViewRequests.setVisible(false);
        DeleteContent.setVisible(false);
        EditContent.setVisible(false);
        Leave.setVisible(true);
        
        if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
            Settings.setVisible(true);
            ViewRequests.setVisible(true);
            DeleteContent.setVisible(true);
            EditContent.setVisible(true);
            Leave.setVisible(false);
        }
        if (Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
            ViewRequests.setVisible(true);
            DeleteContent.setVisible(true);
            EditContent.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Picture = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        postsList = new javax.swing.JList<>();
        VIEW = new javax.swing.JButton();
        AddContent = new javax.swing.JButton();
        Leave = new javax.swing.JButton();
        ViewMembers = new javax.swing.JButton();
        ViewRequests = new javax.swing.JButton();
        Settings = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        DeleteContent = new javax.swing.JButton();
        EditContent = new javax.swing.JButton();
        name = new javax.swing.JLabel();
        description = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Group page");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Name:");

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel2.setText("Group");

        jLabel3.setText("Description:");

        jLabel4.setText("Picture:");

        jScrollPane1.setViewportView(postsList);

        VIEW.setBackground(new java.awt.Color(0, 204, 204));
        VIEW.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        VIEW.setText(" VIEW POST");
        VIEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEWActionPerformed(evt);
            }
        });

        AddContent.setBackground(new java.awt.Color(0, 204, 204));
        AddContent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AddContent.setText("ADD POST");
        AddContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddContentActionPerformed(evt);
            }
        });

        Leave.setBackground(new java.awt.Color(0, 204, 204));
        Leave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Leave.setText("LEAVE");
        Leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveActionPerformed(evt);
            }
        });

        ViewMembers.setBackground(new java.awt.Color(0, 204, 204));
        ViewMembers.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ViewMembers.setText("VIEW MEMBERS");
        ViewMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewMembersActionPerformed(evt);
            }
        });

        ViewRequests.setBackground(new java.awt.Color(0, 204, 204));
        ViewRequests.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ViewRequests.setText("VIEW REQUESTS");
        ViewRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRequestsActionPerformed(evt);
            }
        });

        Settings.setBackground(new java.awt.Color(0, 204, 204));
        Settings.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Settings.setText("SETTINGS");
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });

        Home.setBackground(new java.awt.Color(0, 204, 204));
        Home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Home.setText("HOME");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        DeleteContent.setBackground(new java.awt.Color(0, 204, 204));
        DeleteContent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DeleteContent.setText("DELETE POST");
        DeleteContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteContentActionPerformed(evt);
            }
        });

        EditContent.setBackground(new java.awt.Color(0, 204, 204));
        EditContent.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EditContent.setText("EDIT POST");
        EditContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditContentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AddContent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DeleteContent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EditContent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(VIEW))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(Home, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Settings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ViewMembers)
                                .addComponent(Leave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ViewRequests, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel4)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Home)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ViewRequests)
                        .addGap(12, 12, 12)
                        .addComponent(ViewMembers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Settings))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddContent)
                    .addComponent(DeleteContent)
                    .addComponent(EditContent)
                    .addComponent(VIEW)
                    .addComponent(Leave))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddContentActionPerformed
        AddPost c = new AddPost(acc, g);
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_AddContentActionPerformed

    private void VIEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VIEWActionPerformed
        int i = postsList.getSelectedIndex();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Choose a content to view!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String line = postsList.getSelectedValue();
            String[] temp = line.split("~");
            DisplayContent c = new DisplayContent(acc, temp);
            this.temp = temp;
            c.setVisible(true);
        }    }//GEN-LAST:event_VIEWActionPerformed

    private void ViewMembersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMembersActionPerformed
        ViewMembersList V = new ViewMembersList(acc, g);
        V.setVisible(true);
        dispose();
    }//GEN-LAST:event_ViewMembersActionPerformed

    private void LeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveActionPerformed
        Database.read();
        if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
            JOptionPane.showMessageDialog(this, "The creator can't leave the group!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
            Database.getGroup(g.getName()).removeAdmin(acc.getUsername());
            Database.getGroup(g.getName()).removeMember(acc.getUsername());
            //Database.getAccount(acc.getUsername()).removeGroup(g);
        } else {
            Database.getGroup(g.getName()).removeMember(acc.getUsername());
            //Database.getAccount(acc.getUsername()).removeGroup(g);
        }
        Database.save();
        Home h = new Home(acc);
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LeaveActionPerformed

    private void ViewRequestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRequestsActionPerformed
        if (Database.getGroup(g.getName()).isAdmin(acc.getUsername()) || Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
            ViewRequestsList R = new ViewRequestsList(acc, g);
            R.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Only admins have access!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ViewRequestsActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsActionPerformed
        if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
            GroupSettings S = new GroupSettings(acc, g);
            S.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Only admins have access!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SettingsActionPerformed

    private void DeleteContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteContentActionPerformed
        if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername()) || Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
            int i = postsList.getSelectedIndex();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "Choose a content to delete!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                String line = postsList.getSelectedValue();
                String[] temp = line.split("~");
                Path dest = Path.of(temp[4]);
                String temp1 = dest.getFileName().toString();
                String temp2 = temp1.substring(0, temp1.length() - 4);
                Database.getGroup(g.getName()).removeContent(Database.getGroup(g.getName()).getcontent(temp2));
                Database.save();
                DefaultListModel x = (DefaultListModel) postsList.getModel();
                x.removeElementAt(i);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must be admin or above to delete a post!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteContentActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing

    private void EditContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditContentActionPerformed
        if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername()) || Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
            int i = postsList.getSelectedIndex();
            if (i == -1) {
                JOptionPane.showMessageDialog(this, "Choose a content to edit!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                String line = postsList.getSelectedValue();
                String[] temp = line.split("~");
                Path dest = Path.of(temp[4]);
                String temp1 = dest.getFileName().toString();
                String temp2 = temp1.substring(0, temp1.length() - 4);
                EditContent EC = new EditContent(acc, g, temp2, this);
                EC.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "You must be admin or above to delete a post!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_EditContentActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddContent;
    private javax.swing.JButton DeleteContent;
    private javax.swing.JButton EditContent;
    private javax.swing.JButton Home;
    private javax.swing.JButton Leave;
    private javax.swing.JLabel Picture;
    private javax.swing.JButton Settings;
    private javax.swing.JButton VIEW;
    private javax.swing.JButton ViewMembers;
    private javax.swing.JButton ViewRequests;
    private javax.swing.JLabel description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel name;
    private javax.swing.JList<String> postsList;
    // End of variables declaration//GEN-END:variables
}
