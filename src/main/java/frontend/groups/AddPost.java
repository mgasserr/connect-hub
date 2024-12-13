package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Databases.NotificationsDatabase;
import Backend.Feed.Content;
import Backend.Feed.ContentFactory;
import Backend.Feed.Group;
import Backend.Notifications.NotiFactory;
import frontend.general.Home;
import java.awt.Dimension;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class AddPost extends javax.swing.JFrame {

    Account acc;
    Group g;
    private Databases Database = Databases.getInstance();
    private NotificationsDatabase notiDatabase = NotificationsDatabase.getInstance();
    private ContentFactory F = new ContentFactory();
    private String imagePath;

    public AddPost(Account acc, Group group) {
        initComponents();
        Database.read();
        notiDatabase.read();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
        this.g = group;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        picture = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        caption = new javax.swing.JTextField();
        choosepicButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        PostorStory = new javax.swing.JComboBox<>();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Content");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        choosepicButton.setBackground(new java.awt.Color(0, 204, 204));
        choosepicButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        choosepicButton.setText("UPLOAD PICTURE");
        choosepicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosepicButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("ENTER CAPTION");

        jLabel2.setText("How would you like your picture to be posted?");

        PostorStory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Post", "Story" }));
        PostorStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostorStoryActionPerformed(evt);
            }
        });

        confirmButton.setBackground(new java.awt.Color(0, 204, 204));
        confirmButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmButton.setText("CONFIRM");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(confirmButton)
                .addContainerGap(225, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Home)
                    .addGap(36, 36, 36)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(48, 48, 48)
                            .addComponent(PostorStory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(choosepicButton)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(caption, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                .addComponent(picture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap(27, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(411, Short.MAX_VALUE)
                .addComponent(confirmButton)
                .addGap(38, 38, 38))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Home)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(119, 119, 119)
                                    .addComponent(choosepicButton))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(picture, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(88, 88, 88)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(caption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(PostorStory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(99, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    private void choosepicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosepicButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Profile Picture");
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
            picture.setIcon(new ImageIcon(imagePath));
        }
    }//GEN-LAST:event_choosepicButtonActionPerformed

    private void PostorStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostorStoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PostorStoryActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        Map<String, String> map = new HashMap<>();
        if (caption.getText().equals("") && picture.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "At least add picture or caption!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (picture.getIcon() == null) {
            map.put("Text", caption.getText());
            map.put("Path", "");
        } else if (caption.getText().equals("")) {
            map.put("Text", "");
            map.put("Path", imagePath);
        } else {
            map.put("Text", caption.getText());
            map.put("Path", imagePath);
        }
        Content c;
        Database.read();
        notiDatabase.read();
        String type = (String) PostorStory.getSelectedItem();
        if (type.equals("Post")) {
            c = F.Feed("Post", acc.getUserId(), map, null);
        } else {
            c = F.Feed("Story", acc.getUserId(), map, null);
        }
        NotiFactory NF = new NotiFactory();
        String[] temp = new String[5];
        temp[0] = "Content";
        temp[1] = acc.getUsername();
        temp[2] = c.getTime().toString();
        temp[3] = c.getContentMap().get("Text");
        temp[4] = c.getContentMap().get("Path");
        for (Account account : Database.getGroup(g.getName()).getMembers()) {
            if (!account.getUsername().equals(acc.getUsername())) {
                Database.getAccount(account.getUsername()).addNotification(NF.CreateNoti("NewPost", null, false, g.getName(), type, temp));
            }
        }
        Database.getGroup(g.getName()).addContent(c);
        Database.save();
        notiDatabase.save();
        JOptionPane.showMessageDialog(this, type + " posted successfully.");
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JComboBox<String> PostorStory;
    private javax.swing.JTextField caption;
    private javax.swing.JButton choosepicButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel picture;
    // End of variables declaration//GEN-END:variables
}
