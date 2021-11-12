package GUI;

import Utils.SoundPacket;
import cliente.cliente;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import Utils.Utils;
import cliente.MicThread;
import static cliente.cliente.chatTable;
import java.awt.event.WindowAdapter;
import javax.swing.JPanel;

public class inicio extends javax.swing.JFrame {

    private cliente cliente;
    private ImageIcon foto;
    private String fotoPath;
    public boolean camaraPrendida = false;
    private ImageIcon videoOn = new ImageIcon("src/imagenes/3.png");
    private ImageIcon videoOff = new ImageIcon("src/imagenes/4.png");
    private ImageIcon audioOn = new ImageIcon("src/imagenes/1.png");
    private ImageIcon audioOff = new ImageIcon("src/imagenes/2.png");

    public inicio() {
        initComponents();
        fotoPath = "imagenes/defaultfoto.png";

        table1.setOpaque(false);
        table1.setShowGrid(false);
        table1.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) table1.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        table1.getTableHeader().setOpaque(false);
        table1.getTableHeader().setBackground(new Color(0, 0, 0, 0.6f));
        table1.getTableHeader().setForeground(new Color(44, 47, 51));
        jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        table1.setTableHeader(null);

        table2.setOpaque(false);
        table2.setShowGrid(false);
        table2.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) table2.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane3.setOpaque(false);
        jScrollPane3.getViewport().setOpaque(false);
        table2.getTableHeader().setOpaque(false);
        table2.getTableHeader().setBackground(new Color(0, 0, 0, 0.6f));
        table2.getTableHeader().setForeground(new Color(44, 47, 51));
        jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        table2.setTableHeader(null);

        jaudio.setIcon(audioOff);
        jvideo.setIcon(videoOff);

        chat.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        chat.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chat = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        side = new javax.swing.JPanel();
        chatBtn = new javax.swing.JButton();
        vozBtn = new javax.swing.JButton();
        userPic = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jmensaje = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        users = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        call = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        micVol = new javax.swing.JSlider();
        jvideo = new javax.swing.JButton();
        jaudio = new javax.swing.JButton();
        side1 = new javax.swing.JPanel();
        chatBtn1 = new javax.swing.JButton();
        vozBtn1 = new javax.swing.JButton();
        userPic1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        callPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        joinMeeting = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        portField = new javax.swing.JTextField();
        ipField = new javax.swing.JTextField();
        choosePicBtn = new javax.swing.JButton();
        title = new javax.swing.JLabel();

        chat.setBackground(new java.awt.Color(35, 39, 42));
        chat.setResizable(false);
        chat.setSize(new java.awt.Dimension(1200, 700));

        jPanel3.setBackground(new java.awt.Color(44, 47, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        side.add(chatBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 160, 40));

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
        side.add(vozBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 160, 40));

        userPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/defaultfoto.png"))); // NOI18N
        userPic.setBorder(null);
        side.add(userPic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 120));

        jPanel3.add(side, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 700));

        panel.setBackground(new java.awt.Color(44, 47, 51));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jmensaje.setBackground(new java.awt.Color(153, 170, 181));
        jmensaje.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jmensaje.setForeground(new java.awt.Color(255, 255, 255));
        jmensaje.setBorder(null);
        jmensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmensajeActionPerformed(evt);
            }
        });
        panel.add(jmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 660, 40));

        jScrollPane2.setBorder(null);

        table1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table1.setForeground(new java.awt.Color(255, 255, 255));
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table1.setEnabled(false);
        jScrollPane2.setViewportView(table1);

        panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 660, 580));

        jPanel3.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 720, 700));

        users.setBackground(new java.awt.Color(35, 39, 42));

        jScrollPane3.setBorder(null);

        table2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table2.setForeground(new java.awt.Color(255, 255, 255));
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table2.setEnabled(false);
        jScrollPane3.setViewportView(table2);

        javax.swing.GroupLayout usersLayout = new javax.swing.GroupLayout(users);
        users.setLayout(usersLayout);
        usersLayout.setHorizontalGroup(
            usersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        usersLayout.setVerticalGroup(
            usersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(users, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 0, -1, 700));

        javax.swing.GroupLayout chatLayout = new javax.swing.GroupLayout(chat.getContentPane());
        chat.getContentPane().setLayout(chatLayout);
        chatLayout.setHorizontalGroup(
            chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        chatLayout.setVerticalGroup(
            chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        call.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        call.setResizable(false);

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

        jvideo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/4.png"))); // NOI18N
        jvideo.setBorder(null);
        jvideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jvideoActionPerformed(evt);
            }
        });
        jPanel1.add(jvideo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 570, 90, 90));

        jaudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/2.png"))); // NOI18N
        jaudio.setBorder(null);
        jaudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jaudioActionPerformed(evt);
            }
        });
        jPanel1.add(jaudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, 90, 90));

        side1.setBackground(new java.awt.Color(35, 39, 42));
        side1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chatBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i1.png"))); // NOI18N
        chatBtn1.setBorder(null);
        chatBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chatBtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                chatBtn1MouseExited(evt);
            }
        });
        chatBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatBtn1ActionPerformed(evt);
            }
        });
        side1.add(chatBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 170, 40));

        vozBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i3.png"))); // NOI18N
        vozBtn1.setBorder(null);
        vozBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vozBtn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vozBtn1MouseExited(evt);
            }
        });
        vozBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vozBtn1ActionPerformed(evt);
            }
        });
        side1.add(vozBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 170, 40));

        userPic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/defaultfoto.png"))); // NOI18N
        userPic1.setBorder(null);
        side1.add(userPic1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 150, 120));

        jPanel1.add(side1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        jScrollPane4.setBorder(null);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(150, 150));

        callPanel.setBackground(new java.awt.Color(44, 47, 51));
        callPanel.setLayout(new java.awt.GridBagLayout());
        jScrollPane4.setViewportView(callPanel);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 930, 530));

        javax.swing.GroupLayout callLayout = new javax.swing.GroupLayout(call.getContentPane());
        call.getContentPane().setLayout(callLayout);
        callLayout.setHorizontalGroup(
            callLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        );
        callLayout.setVerticalGroup(
            callLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(44, 47, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        joinMeeting.setBackground(new java.awt.Color(114, 137, 218));
        joinMeeting.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        joinMeeting.setForeground(new java.awt.Color(255, 255, 255));
        joinMeeting.setText("Iniciar reunion");
        joinMeeting.setBorder(null);
        joinMeeting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinMeetingActionPerformed(evt);
            }
        });
        jPanel2.add(joinMeeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 580, 790, 50));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 138, 36));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Foto de perfil");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 159, 36));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ID de reunion");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 168, 36));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Direccion IP");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, 138, 36));

        nameField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jPanel2.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 480, -1));

        portField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        portField.setText("2003");
        jPanel2.add(portField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 480, -1));

        ipField.setEditable(false);
        ipField.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        ipField.setText("localhost");
        jPanel2.add(ipField, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 480, -1));

        choosePicBtn.setBackground(new java.awt.Color(114, 137, 218));
        choosePicBtn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        choosePicBtn.setForeground(new java.awt.Color(255, 255, 255));
        choosePicBtn.setText("Escoja su foto de perfil");
        choosePicBtn.setBorder(null);
        choosePicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosePicBtnActionPerformed(evt);
            }
        });
        jPanel2.add(choosePicBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 480, 40));

        title.setFont(new java.awt.Font("Dialog", 0, 48)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Me.Chat");
        jPanel2.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 42, 1200, 85));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmensajeActionPerformed
        if (!jmensaje.getText().equals("")) {
            cliente.getHc().enviarMensaje(nameField.getText(), jmensaje.getText());
            jmensaje.setText("");
        }
    }//GEN-LAST:event_jmensajeActionPerformed

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    private void vozBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vozBtnActionPerformed
        foto = (ImageIcon) userPic.getIcon();
        userPic1.setIcon(foto);
        call.setSize(1200, 750);
        call.setVisible(true);
        call.setLocationRelativeTo(null);
        MicThread.amplification = 0;
        chat.setVisible(false);
    }//GEN-LAST:event_vozBtnActionPerformed
    private void chatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chatBtnActionPerformed

    private void joinMeetingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinMeetingActionPerformed
        if (nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite su nombre");
        } else {
            this.setVisible(false);
            cliente.connect(ipField.getText(), Integer.parseInt(portField.getText()));
            cliente.getHc().insertarCliente(nameField.getText(), fotoPath);
            chat.setVisible(true);
            chat.setLocationRelativeTo(null);
            chat.setSize(1230, 730); //778,520
        }
    }//GEN-LAST:event_joinMeetingActionPerformed

    private void choosePicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosePicBtnActionPerformed
        FileDialog dialog = new FileDialog((Frame) null, "Seleccione una foto");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String fileChooser = dialog.getDirectory() + dialog.getFile();
        fotoPath = fileChooser;
        foto = new ImageIcon(fileChooser);
        Image img = foto.getImage();
        Image newimg = img.getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH);
        foto = new ImageIcon(newimg);

        userPic.setIcon(foto);
    }//GEN-LAST:event_choosePicBtnActionPerformed

    private void chatBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtnMouseEntered
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i2.png")));
    }//GEN-LAST:event_chatBtnMouseEntered

    private void chatBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtnMouseExited
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i1.png")));
    }//GEN-LAST:event_chatBtnMouseExited

    private void vozBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vozBtnMouseEntered
        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i4.png")));
    }//GEN-LAST:event_vozBtnMouseEntered

    private void vozBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vozBtnMouseExited
        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i3.png")));
    }//GEN-LAST:event_vozBtnMouseExited

    private void micVolStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_micVolStateChanged
        if (jaudio.getIcon() == audioOn) {
            MicThread.amplification = ((double) (micVol.getValue())) / 100.0;
        }
    }//GEN-LAST:event_micVolStateChanged

    private void jvideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jvideoActionPerformed
        if (jvideo.getIcon() == videoOff) {
            jvideo.setIcon(videoOn);
            camaraPrendida = true;
        } else {
            jvideo.setIcon(videoOff);
            camaraPrendida = false;
        }
    }//GEN-LAST:event_jvideoActionPerformed

    private void jaudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jaudioActionPerformed
        if (jaudio.getIcon() == audioOff) {
            jaudio.setIcon(audioOn);
            MicThread.amplification = ((double) (micVol.getValue())) / 100.0;
        } else {
            jaudio.setIcon(audioOff);
            MicThread.amplification = 0;
        }
    }//GEN-LAST:event_jaudioActionPerformed

    private void chatBtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtn1MouseEntered
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i2.png")));
    }//GEN-LAST:event_chatBtn1MouseEntered

    private void chatBtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatBtn1MouseExited
        chatBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i1.png")));
    }//GEN-LAST:event_chatBtn1MouseExited

    private void chatBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatBtn1ActionPerformed
        chat.setVisible(true);
        chat.setLocationRelativeTo(null);
        call.setVisible(false);
    }//GEN-LAST:event_chatBtn1ActionPerformed

    private void vozBtn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vozBtn1MouseEntered
        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i4.png")));
    }//GEN-LAST:event_vozBtn1MouseEntered

    private void vozBtn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vozBtn1MouseExited
        vozBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/i3.png")));
    }//GEN-LAST:event_vozBtn1MouseExited

    private void vozBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vozBtn1ActionPerformed

    }//GEN-LAST:event_vozBtn1ActionPerformed

    public JPanel getCallPanel() {
        return callPanel;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void exitProcedure() {
        cliente.getHc().deleteUser();
        this.dispose();
        System.exit(0);
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable12() {
        return table2;
    }

    public boolean isCamaraPrendida() {
        return camaraPrendida;
    }

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
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame call;
    private javax.swing.JPanel callPanel;
    private javax.swing.JFrame chat;
    private javax.swing.JButton chatBtn;
    private javax.swing.JButton chatBtn1;
    private javax.swing.JButton choosePicBtn;
    private javax.swing.JTextField ipField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jaudio;
    private javax.swing.JTextField jmensaje;
    private javax.swing.JButton joinMeeting;
    private javax.swing.JButton jvideo;
    private javax.swing.JSlider micVol;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField portField;
    private javax.swing.JPanel side;
    private javax.swing.JPanel side1;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JLabel title;
    private javax.swing.JButton userPic;
    private javax.swing.JButton userPic1;
    private javax.swing.JPanel users;
    private javax.swing.JButton vozBtn;
    private javax.swing.JButton vozBtn1;
    // End of variables declaration//GEN-END:variables

}
