package frontend.groups;

import Backend.Account.Account;
import Backend.Databases.Databases;
import Backend.Feed.Group;
import frontend.general.Home;
import javax.swing.JOptionPane;

public class CreateGroup extends javax.swing.JFrame {

    Account acc;
    Databases Database = Databases.getInstance();
    Databases D = Databases.getInstance();

    public CreateGroup(Account acc) {
        initComponents();
        Database.read();
        this.setLocationRelativeTo(null);
        setResizable(false);
        this.acc = acc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Create = new javax.swing.JButton();
        Home = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        Description = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create Group");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("GROUP NAME");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("GROUP DESCRIPTION");

        Create.setBackground(new java.awt.Color(255, 102, 153));
        Create.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Create.setText("CREATE");
        Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateActionPerformed(evt);
            }
        });

        Home.setBackground(new java.awt.Color(255, 102, 153));
        Home.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setText("Create Group");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Home, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(name)
                        .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(Create)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Home))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(Create)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Database.logoutDatabase(acc.getUsername());
    }//GEN-LAST:event_formWindowClosing

    private void CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateActionPerformed
        if (name.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Enter the group name!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            D.read();
            String name = this.name.getText();
            String desc = this.Description.getText();
            for (Group group : Group.getGroups()) {
                if(group.getName().equalsIgnoreCase(name)){
                    JOptionPane.showMessageDialog(this, "Group name is already used!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
            }
            Group g = new Group(acc, name, desc, null);
            g.addGroup(g, acc.getUsername());
            D.save();
            JOptionPane.showMessageDialog(this, " Created successfully.");
        }
    }//GEN-LAST:event_CreateActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        Home home = new Home(acc);
        home.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_HomeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Create;
    private javax.swing.JTextField Description;
    private javax.swing.JButton Home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField name;
    // End of variables declaration//GEN-END:variables
}
