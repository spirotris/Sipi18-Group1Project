/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipi.groupOne.ui;

import com.sipi.groupOne.BotProject;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

/**
 *
 * @author SpiroTris
 */
public class launchGUI extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form launchGUI
     */
    public launchGUI() {
        initComponents();
        CenteredFrame(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ircNickLabel = new javax.swing.JLabel();
        guiPromptCombo = new javax.swing.JComboBox<>();
        logfilePromptLabel = new javax.swing.JLabel();
        logfilePromptCombo = new javax.swing.JComboBox<>();
        guiPromptLabel = new javax.swing.JLabel();
        ircChannelLabel = new javax.swing.JLabel();
        ircServerLabel = new javax.swing.JLabel();
        ircNickTextField = new javax.swing.JTextField();
        ircServerTextField = new javax.swing.JTextField();
        ircChannelTextField = new javax.swing.JTextField();
        headerLabel = new javax.swing.JLabel();
        launchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IRC Bot Setup Window");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        ircNickLabel.setText("Bot nickname");

        guiPromptCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        guiPromptCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guiPromptComboActionPerformed(evt);
            }
        });

        logfilePromptLabel.setText("Save log file");

        logfilePromptCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        logfilePromptCombo.setSelectedIndex(1);
        logfilePromptCombo.setEnabled(false);
        logfilePromptCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logfilePromptComboActionPerformed(evt);
            }
        });

        guiPromptLabel.setText("Use Graphic Interface");

        ircChannelLabel.setText("IRC Channel");

        ircServerLabel.setText("IRC server");

        ircNickTextField.setText("BotenAnna");

        ircServerTextField.setText("port80a.se.quakenet.org");

        ircChannelTextField.setText("#group1-lernia");

        headerLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        headerLabel.setText("IRC Bot Setup");

        launchButton.setText("Launch Bot");
        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logfilePromptLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ircNickLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ircServerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ircChannelLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guiPromptLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ircChannelTextField)
                    .addComponent(ircNickTextField)
                    .addComponent(ircServerTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(logfilePromptCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(guiPromptCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(launchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(headerLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ircNickLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ircNickTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ircServerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ircServerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ircChannelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ircChannelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guiPromptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guiPromptCombo))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logfilePromptLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logfilePromptCombo)
                    .addComponent(launchButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guiPromptComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guiPromptComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guiPromptComboActionPerformed

    private void logfilePromptComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logfilePromptComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logfilePromptComboActionPerformed

    private void launchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchButtonActionPerformed
        BotProject bot;
        Thread t = new Thread(bot = new BotProject(
                ircServerTextField.getText(),
                ircNickTextField.getText(),
                ircChannelTextField.getText(),
                false));
        MainFrame ui = new MainFrame(bot);
        bot.setUi(ui);
        t.start();
        this.dispose();
    }//GEN-LAST:event_launchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> guiPromptCombo;
    private javax.swing.JLabel guiPromptLabel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel ircChannelLabel;
    private javax.swing.JTextField ircChannelTextField;
    private javax.swing.JLabel ircNickLabel;
    private javax.swing.JTextField ircNickTextField;
    private javax.swing.JLabel ircServerLabel;
    private javax.swing.JTextField ircServerTextField;
    private javax.swing.JButton launchButton;
    private javax.swing.JComboBox<String> logfilePromptCombo;
    private javax.swing.JLabel logfilePromptLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new launchGUI().setVisible(true);
                
            }
        });
    }
    
    public static void CenteredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - objFrame.getWidth()) / 2;
        int iCoordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(iCoordX, iCoordY); 
    } 
}
