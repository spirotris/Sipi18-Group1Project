package com.sipi.groupOne.ui;

import com.sipi.groupOne.BotProject;
import java.awt.Dimension;
import java.awt.Toolkit;

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
        ircChannelLabel = new javax.swing.JLabel();
        ircServerLabel = new javax.swing.JLabel();
        ircNickTextField = new javax.swing.JTextField();
        ircServerTextField = new javax.swing.JTextField();
        ircChannelTextField = new javax.swing.JTextField();
        headerLabel = new javax.swing.JLabel();
        launchButton = new javax.swing.JButton();
        logfileCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IRC Bot Setup Window");
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);

        ircNickLabel.setText("Bot nickname");

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

        logfileCheckBox.setText("Save logfile");
        logfileCheckBox.setToolTipText("Saves PISG-friendly logfile to ./irclog-<todaysdate>.log");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ircServerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ircChannelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ircNickLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ircServerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(ircNickTextField)
                            .addComponent(ircChannelTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(headerLabel)
                        .addGap(0, 106, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logfileCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                    .addComponent(logfileCheckBox)
                    .addComponent(launchButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void launchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchButtonActionPerformed
        BotProject bot;
        Thread t = new Thread(bot = new BotProject(
                ircServerTextField.getText(),
                ircNickTextField.getText(),
                ircChannelTextField.getText(),
                logfileCheckBox.isSelected()));
        MainFrame ui = new MainFrame(bot);
        bot.setUi(ui);
        t.start();
        this.dispose();
    }//GEN-LAST:event_launchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel ircChannelLabel;
    private javax.swing.JTextField ircChannelTextField;
    private javax.swing.JLabel ircNickLabel;
    private javax.swing.JTextField ircNickTextField;
    private javax.swing.JLabel ircServerLabel;
    private javax.swing.JTextField ircServerTextField;
    private javax.swing.JButton launchButton;
    private javax.swing.JCheckBox logfileCheckBox;
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
