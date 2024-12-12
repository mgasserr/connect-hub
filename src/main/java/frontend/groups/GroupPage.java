package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import frontend.general.DisplayContent;
import frontend.general.Home;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class GroupPage extends javax.swing.JFrame {

    Account acc;
    Group g;
    Databases Database = Databases.getInstance();
    String[] temp;

    public GroupPage(Account acc, Group group) {
        initComponents();
        this.acc = acc;
        this.g = group;
        Picture.setIcon(Database.getGroup(group.getName()).getPicture());
        name.setText(Database.getGroup(group.getName()).getName());
        Description.setText(Database.getGroup(group.getName()).getDescription());

        DefaultListModel<String> postsFeedModel = new DefaultListModel<>(); // Initialize DefaultListModel
        for (int j = 0; j < Database.getGroup(g.getName()).getContent().size(); j++) {
            // Extract content details
            String time = Database.getGroup(g.getName()).getContent().get(j).getTime().toString();
            String text = (String) Database.getGroup(g.getName()).getContent().get(j).getContentMap().get("Text");
            String path = (String) Database.getGroup(g.getName()).getContent().get(j).getContentMap().get("Path");
            // Format the data for display
            String listItem = String.format("%s~%s~%s~%s", Database.getAccountbyID(Database.getGroup(g.getName()).getContent().get(j).getAuthorId()).getUsername(), time, text != null ? text : "No Text", path != null ? path : "No Path");
            // Add the formatted string to the DefaultListModel
            postsFeedModel.addElement(listItem);
        }
        postsList.setModel(postsFeedModel);
        
        Settings.setVisible(false);
        ViewRequests.setVisible(false);
        if (Database.getGroup(g.getName()).getCreator().getUsername().equals(acc.getUsername())) {
            Settings.setVisible(true);
            ViewRequests.setVisible(true);
        }
        if (Database.getGroup(g.getName()).isAdmin(acc.getUsername())) {
            ViewRequests.setVisible(true);
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
        name = new javax.swing.JTextField();
        Description = new javax.swing.JTextField();
        ViewRequests = new javax.swing.JButton();
        Settings = new javax.swing.JButton();
        Home = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel2.setText("Group");

        jLabel3.setText("Description");

        jLabel4.setText("Picture");

        jScrollPane1.setViewportView(postsList);

        VIEW.setText("VIEW");
        VIEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VIEWActionPerformed(evt);
            }
        });

        AddContent.setText("POST");
        AddContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddContentActionPerformed(evt);
            }
        });

        Leave.setText("LEAVE");
        Leave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveActionPerformed(evt);
            }
        });

        ViewMembers.setText("VIEW MEMBERS");
        ViewMembers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewMembersActionPerformed(evt);
            }
        });

        ViewRequests.setText("VIEW REQUESTS");
        ViewRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRequestsActionPerformed(evt);
            }
        });

        Settings.setText("SETTINGS");
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });

        Home.setText("HOME");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(AddContent)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(VIEW))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 289, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Leave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ViewMembers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ViewRequests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Home, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Settings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(Picture, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Home)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ViewRequests)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ViewMembers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Settings)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VIEW)
                    .addComponent(AddContent)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddContent;
    private javax.swing.JTextField Description;
    private javax.swing.JButton Home;
    private javax.swing.JButton Leave;
    private javax.swing.JLabel Picture;
    private javax.swing.JButton Settings;
    private javax.swing.JButton VIEW;
    private javax.swing.JButton ViewMembers;
    private javax.swing.JButton ViewRequests;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JList<String> postsList;
    // End of variables declaration//GEN-END:variables
}
