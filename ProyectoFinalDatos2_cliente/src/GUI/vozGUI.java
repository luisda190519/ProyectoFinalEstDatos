package GUI;

import VoiceUtils.ClienteVoz;
import VoiceUtils.MicThread;
import VoiceUtils.SoundPacket;
import cliente.cliente;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import Utils.Utils;

public class vozGUI extends javax.swing.JFrame {

    private MicTester micTester;
    private cliente cliente;
    private ImageIcon foto;
    public boolean camaraPrendida = false;
    private JFrame i;
    ImageIcon videoOn = new ImageIcon("src/imagenes/3.png");
    ImageIcon videoOff = new ImageIcon("src/imagenes/4.png");
    ImageIcon audioOn = new ImageIcon("src/imagenes/1.png");
    ImageIcon audioOff = new ImageIcon("src/imagenes/2.png");

    private vozGUI() {
    }

    private class MicTester extends Thread {

        private TargetDataLine mic = null;

        public MicTester() {
        }

        @Override
        public void run() {

            try {
                AudioFormat af = SoundPacket.defaultFormat;
                DataLine.Info info = new DataLine.Info(TargetDataLine.class, null);
                mic = (TargetDataLine) (AudioSystem.getLine(info));
                mic.open(af);
                mic.start();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Microphone not detected.\nPress OK to close this program", "Error", JOptionPane.ERROR_MESSAGE);
            }
            for (;;) {
                Utils.sleep(10);
                if (mic.available() > 0) {
                    byte[] buff = new byte[SoundPacket.defaultDataLenght];
                    mic.read(buff, 0, buff.length);
                    long tot = 0;
                    for (int i = 0; i < buff.length; i++) {
                        tot += MicThread.amplification * Math.abs(buff[i]);
                    }
                    tot *= 2.5;
                    tot /= buff.length;
                }
            }
        }

        private void close() {
            if (mic != null) {
                mic.close();
            }
            stop();
        }

    }

    public vozGUI(cliente cliente, ImageIcon foto, JFrame i) {
        initComponents();
        micTester = new MicTester();
        jaudio.setIcon(audioOff);
        jvideo.setIcon(videoOff);
        this.cliente = cliente;
        this.foto = foto;
        this.i = i;
        userPic.setIcon(foto);
        micTester.start();
        MicThread.amplification = 0;
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();

            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        micVol = new javax.swing.JSlider();
        jvideo = new javax.swing.JButton();
        jaudio = new javax.swing.JButton();
        side = new javax.swing.JPanel();
        chatBtn = new javax.swing.JButton();
        vozBtn = new javax.swing.JButton();
        userPic = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(44, 47, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        micVol.setBackground(new java.awt.Color(44, 47, 51));
        micVol.setMaximum(300);
        micVol.setMinimum(50);
        micVol.setValue(100);
        micVol.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                micVolStateChanged(evt);
            }
        });
        jPanel1.add(micVol, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 610, 330, -1));

        jvideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3.png"))); // NOI18N
        jvideo.setBorder(null);
        jvideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jvideoActionPerformed(evt);
            }
        });
        jPanel1.add(jvideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, 90, 90));

        jaudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1.png"))); // NOI18N
        jaudio.setBorder(null);
        jaudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaudioActionPerformed(evt);
            }
        });
        jPanel1.add(jaudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, 90, 90));

        side.setBackground(new java.awt.Color(35, 39, 42));
        side.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i1.png"))); // NOI18N
        chatBtn.setBorder(null);
        chatBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chatBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chatBtnMouseExited(evt);
            }
        });
        chatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatBtnActionPerformed(evt);
            }
        });
        side.add(chatBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, 40));

        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i3.png"))); // NOI18N
        vozBtn.setBorder(null);
        vozBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vozBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vozBtnMouseExited(evt);
            }
        });
        vozBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vozBtnActionPerformed(evt);
            }
        });
        side.add(vozBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 170, 40));

        userPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/defaultfoto.png"))); // NOI18N
        userPic.setBorder(null);
        side.add(userPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 120));

        jPanel1.add(side, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        jScrollPane3.setBorder(null);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(150, 150));

        panel.setBackground(new java.awt.Color(44, 47, 51));
        panel.setLayout(new java.awt.GridBagLayout());
        jScrollPane3.setViewportView(panel);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 930, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void micVolStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_micVolStateChanged
        if (jaudio.getIcon() == audioOn) {
            MicThread.amplification = ((double) (micVol.getValue())) / 100.0;
        }
    }//GEN-LAST:event_micVolStateChanged

    private void jaudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaudioActionPerformed
        if (jaudio.getIcon() == audioOff) {
            jaudio.setIcon(audioOn);
            MicThread.amplification = ((double) (micVol.getValue())) / 100.0;

            join();
        } else {
            jaudio.setIcon(audioOff);
            MicThread.amplification = 0;
        }
    }//GEN-LAST:event_jaudioActionPerformed
    private void jvideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jvideoActionPerformed
        if (jvideo.getIcon() == videoOff) {
            jvideo.setIcon(videoOn);
            camaraPrendida = true;
        } else {
            jvideo.setIcon(videoOff);
            camaraPrendida = false;
        }
        cliente.getInicio().añadirPanel(this.panel, this);
    }//GEN-LAST:event_jvideoActionPerformed

    private void chatBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtnMouseEntered
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i2.png")));
    }//GEN-LAST:event_chatBtnMouseEntered

    private void chatBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtnMouseExited
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i1.png")));
    }//GEN-LAST:event_chatBtnMouseExited

    private void chatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatBtnActionPerformed
        i.setVisible(true);
        i.setLocationRelativeTo(null);
        this.setVisible(false);
        deleteUserProfile();
        cliente.setPanel(this.panel, this);
    }//GEN-LAST:event_chatBtnActionPerformed

    private void vozBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vozBtnMouseEntered
        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i4.png")));
    }//GEN-LAST:event_vozBtnMouseEntered

    private void vozBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vozBtnMouseExited
        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i3.png")));
    }//GEN-LAST:event_vozBtnMouseExited

    private void vozBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vozBtnActionPerformed

    }//GEN-LAST:event_vozBtnActionPerformed

    public void deleteUserProfile() {
        File inputFile = cliente.getArchivo();
        File tempFile = new File("data/myTempFile.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = cliente.getHc().getNombre() + "," + cliente.getHc().getFoto();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();
            inputFile.delete();
            boolean successful = tempFile.renameTo(inputFile);

        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void exitProcedure() {
        deleteUserProfile();
        cliente.updatePanels();
        this.dispose();
        System.exit(0);
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void join() {
        try {
            new ClienteVoz("127.0.0.1", 1049).start(); //connect to specified server at specified port
        } catch (Exception ex) { //connection failed
            JOptionPane.showMessageDialog(rootPane, ex, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        micTester.close();
    }

    public boolean isCamaraPrendida() {
        return camaraPrendida;
    }

    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vozGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vozGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vozGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vozGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new vozGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chatBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jaudio;
    private javax.swing.JButton jvideo;
    private javax.swing.JSlider micVol;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel side;
    private javax.swing.JButton userPic;
    private javax.swing.JButton vozBtn;
    // End of variables declaration//GEN-END:variables
}
