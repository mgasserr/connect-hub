package Frontend.General;

import Frontend.Startup;
import Backend.Account.Account;
<<<<<<< Updated upstream
import Backend.Authentication.Register;
import Backend.Databases.Database;
import Frontend.Friends.FriendsManagement;
import Frontend.Settings.Settings;
import java.lang.String;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
=======
import Backend.Databases.Database;
import Frontend.General.Startup;
import Frontend.Generall.Startup;
>>>>>>> Stashed changes

public class Home extends javax.swing.JFrame {

    private Account acc;

    public Home(Account acc) {
        initComponents();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;

        DefaultListModel<String> newsFeedModel = new DefaultListModel<>(); // Initialize DefaultListModel
        for (int i = 0; i < acc.getFriendsManagement().getFriends().size(); i++) {
            String friendUsername = acc.getFriendsManagement().getFriends().get(i).getUsername();
            for (int j = 0; j < acc.getFriendsManagement().getFriends().get(i).getContentManagement().getContent().size(); j++) {
                // Extract content details
                String time = acc.getFriendsManagement().getFriends().get(i).getContentManagement().getContent().get(j).getTime().toString();
                String text = (String) acc.getFriendsManagement().getFriends().get(i).getContentManagement().getContent().get(j).getContentMap().get("Text");
                String path = (String) acc.getFriendsManagement().getFriends().get(i).getContentManagement().getContent().get(j).getContentMap().get("Path");
                // Format the data for display
                String listItem = String.format("%s|%s|%s|%s", friendUsername, time, text != null ? text : "No Text", path != null ? path : "No Path");
                // Add the formatted string to the DefaultListModel
                newsFeedModel.addElement(listItem);
            }
        }
        newsFeed.setModel(newsFeedModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        newsFeed = new javax.swing.JList<>();
        Friends = new javax.swing.JButton();
        Settings = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        Profile = new javax.swing.JButton();
        NewContent = new javax.swing.JButton();
        LogOut = new javax.swing.JButton();
        View = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel1.setText("CONNECT HUB");

        jScrollPane1.setViewportView(newsFeed);

        Friends.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Images/friends.png"))); // NOI18N
        Friends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FriendsActionPerformed(evt);
            }
        });

        Settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Images/setting.png"))); // NOI18N
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }
        });

        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Images/home.png"))); // NOI18N
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Profile.setIcon(new javax.swing.ImageIcon("C:\\Users\\LEGION\\Documents\\GitHub\\Connect-Hub\\src\\main\\java\\Frontend\\Images\\account.png")); // NOI18N
        Profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProfileActionPerformed(evt);
            }
        });

        NewContent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Images/post.png"))); // NOI18N
        NewContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewContentActionPerformed(evt);
            }
        });

        LogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/Images/logout.png"))); // NOI18N
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });

        View.setBackground(new java.awt.Color(0, 204, 204));
        View.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        View.setText("VIEW");
        View.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(Home)
                        .addGap(18, 18, 18)
                        .addComponent(Profile)
                        .addGap(18, 18, 18)
                        .addComponent(Friends)
                        .addGap(18, 18, 18)
                        .addComponent(NewContent)
                        .addGap(18, 18, 18)
                        .addComponent(Settings)
                        .addGap(18, 18, 18)
                        .addComponent(LogOut)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(View)
                .addGap(254, 254, 254))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home)
                    .addComponent(Settings)
                    .addComponent(jLabel1)
                    .addComponent(NewContent)
                    .addComponent(Friends)
                    .addComponent(LogOut)
                    .addComponent(Profile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(View)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< Updated upstream
    private void ProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProfileActionPerformed
        //GOES TO PROFILE
        MyProfile p = new MyProfile(acc);
        p.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ProfileActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Register.getInstance().logout(acc);
    }//GEN-LAST:event_formWindowClosing

    private void ViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewActionPerformed
        int i = newsFeed.getSelectedIndex();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Choose a content to view!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            String line = newsFeed.getSelectedValue();
            String[] temp = line.split("|");
            DisplayContent c = new DisplayContent(acc, temp);
        }
    }//GEN-LAST:event_ViewActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
        Register.getInstance().logout(acc);
        this.setVisible(false);
    }//GEN-LAST:event_LogOutActionPerformed

    private void SettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsActionPerformed
        //GOES TO SETTINGS
        Settings s = new Settings(acc);
        s.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_SettingsActionPerformed

    private void NewContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewContentActionPerformed
        //ADD NEW CONTENT
        AddContent c = new AddContent(acc);
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_NewContentActionPerformed

    private void FriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FriendsActionPerformed
        //GOES TO FRIENDS MANAGMENT
        //        FriendsManagement f = new FriendsManagement(acc);
        //        f.setVisible(true);
        //        this.setVisible(false);
    }//GEN-LAST:event_FriendsActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        //REFRESH AND GOES BACK TO HOME
        Database.refreshDatabase();
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

=======
    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Database.refreshDatabase();
    }//GEN-LAST:event_HomeActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Startup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home(new Account(null, null, null, null)).setVisible(true);
            }
        });
    }
>>>>>>> Stashed changes

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Friends;
    private javax.swing.JButton Home;
    private javax.swing.JButton LogOut;
    private javax.swing.JButton NewContent;
    private javax.swing.JButton Profile;
    private javax.swing.JButton Settings;
    private javax.swing.JButton View;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> newsFeed;
    // End of variables declaration//GEN-END:variables
}
