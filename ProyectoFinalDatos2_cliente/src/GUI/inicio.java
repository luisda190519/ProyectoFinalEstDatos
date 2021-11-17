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
import soundUtils.MicThread;
import static cliente.cliente.chatTable;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class inicio extends javax.swing.JFrame {

    private cliente cliente;
    private ImageIcon foto;
    private String fotoPath;
    public boolean camaraPrendida = false;
    private ImageIcon videoOn = new ImageIcon("src/imagenes/3.png");
    private ImageIcon videoOff = new ImageIcon("src/imagenes/4.png");
    private ImageIcon audioOn = new ImageIcon("src/imagenes/1.png");
    private ImageIcon audioOff = new ImageIcon("src/imagenes/2.png");
    private int xMouse, yMouse;

    public inicio() {
        initComponents();
        fotoPath = "src/imagenes/defaultfoto.png";

        setTableProperties(table1, jScrollPane2);
        setTableProperties(table2, jScrollPane3);

        jaudio.setIcon(audioOff);
        jvideo.setIcon(videoOff);

        addExitOperation(chat);
        addExitOperation(call);
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
        jPanel4 = new javax.swing.JPanel();
        nombreApp = new javax.swing.JLabel();
        sloganApp = new javax.swing.JLabel();
        separadorIcono = new javax.swing.JSeparator();
        iconoDer = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        iniciarTitulo = new javax.swing.JLabel();
        imagenLabel = new javax.swing.JLabel();
        ipField = new javax.swing.JTextField();
        separadorNombre = new javax.swing.JSeparator();
        idLabel = new javax.swing.JLabel();
        separadorNombre1 = new javax.swing.JSeparator();
        direccionLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        separadorNombre2 = new javax.swing.JSeparator();
        portField = new javax.swing.JTextField();
        barraSuperior = new javax.swing.JPanel();
        cerrarBoton = new javax.swing.JPanel();
        cerrarLabel = new javax.swing.JLabel();
        choosePicBtn = new javax.swing.JPanel();
        choosePicLabel = new javax.swing.JLabel();
        joinMeetingBtn = new javax.swing.JPanel();
        reunionLabel = new javax.swing.JLabel();
        startMeeting = new javax.swing.JPanel();
        reunionLabel1 = new javax.swing.JLabel();
        generatePort = new javax.swing.JButton();

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

        jPanel1.add(side1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 700));

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
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        nombreApp.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        nombreApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombreApp.setText("me.Chat");

        sloganApp.setFont(new java.awt.Font("Verdana", 2, 14)); // NOI18N
        sloganApp.setText("Conecta.Comunica.Comparte");

        iconoDer.setBackground(new java.awt.Color(255, 255, 255));
        iconoDer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoDer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imagenVideo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(separadorIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sloganApp)
                            .addComponent(nombreApp, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(168, 168, 168))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(iconoDer, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(nombreApp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separadorIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(sloganApp)
                .addGap(29, 29, 29)
                .addComponent(iconoDer, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 580, 700));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 30, 30));

        iniciarTitulo.setFont(new java.awt.Font("Verdana", 3, 36)); // NOI18N
        iniciarTitulo.setText("Iniciar");
        jPanel2.add(iniciarTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        imagenLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        imagenLabel.setText("Foto de Perfil");
        jPanel2.add(imagenLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, -1, -1));

        ipField.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        ipField.setForeground(new java.awt.Color(204, 204, 204));
        ipField.setText("localhost");
        ipField.setBorder(null);
        ipField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ipFieldMousePressed(evt);
            }
        });
        jPanel2.add(ipField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 530, 440, 40));
        jPanel2.add(separadorNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 440, 10));

        idLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        idLabel.setText("ID de la Reunión");
        jPanel2.add(idLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));
        jPanel2.add(separadorNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, 440, 10));

        direccionLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        direccionLabel.setText("Dirección IP");
        jPanel2.add(direccionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, -1));

        nameField.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        nameField.setForeground(new java.awt.Color(204, 204, 204));
        nameField.setText("Mi nombre...");
        nameField.setBorder(null);
        nameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nameFieldMousePressed(evt);
            }
        });
        jPanel2.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 440, 40));

        nombreLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        nombreLabel.setText("Nombre");
        jPanel2.add(nombreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));
        jPanel2.add(separadorNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 440, 10));

        portField.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        portField.setForeground(new java.awt.Color(204, 204, 204));
        portField.setText("2003");
        portField.setBorder(null);
        portField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                portFieldMousePressed(evt);
            }
        });
        jPanel2.add(portField, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 380, 40));

        barraSuperior.setBackground(new java.awt.Color(255, 255, 255));
        barraSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barraSuperiorMouseDragged(evt);
            }
        });
        barraSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barraSuperiorMousePressed(evt);
            }
        });

        cerrarBoton.setBackground(new java.awt.Color(255, 255, 255));
        cerrarBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrarBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarBotonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarBotonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarBotonMouseExited(evt);
            }
        });

        cerrarLabel.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        cerrarLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cerrarLabel.setText("X");

        javax.swing.GroupLayout cerrarBotonLayout = new javax.swing.GroupLayout(cerrarBoton);
        cerrarBoton.setLayout(cerrarBotonLayout);
        cerrarBotonLayout.setHorizontalGroup(
            cerrarBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cerrarLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        cerrarBotonLayout.setVerticalGroup(
            cerrarBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cerrarBotonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cerrarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout barraSuperiorLayout = new javax.swing.GroupLayout(barraSuperior);
        barraSuperior.setLayout(barraSuperiorLayout);
        barraSuperiorLayout.setHorizontalGroup(
            barraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraSuperiorLayout.createSequentialGroup()
                .addComponent(cerrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 760, Short.MAX_VALUE))
        );
        barraSuperiorLayout.setVerticalGroup(
            barraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraSuperiorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cerrarBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(barraSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 50));

        choosePicBtn.setBackground(new java.awt.Color(204, 204, 255));
        choosePicBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        choosePicBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choosePicBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                choosePicBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                choosePicBtnMouseExited(evt);
            }
        });

        choosePicLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        choosePicLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        choosePicLabel.setText("Seleccionar\n\n");

        javax.swing.GroupLayout choosePicBtnLayout = new javax.swing.GroupLayout(choosePicBtn);
        choosePicBtn.setLayout(choosePicBtnLayout);
        choosePicBtnLayout.setHorizontalGroup(
            choosePicBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(choosePicLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        choosePicBtnLayout.setVerticalGroup(
            choosePicBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, choosePicBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(choosePicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(choosePicBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 440, 40));

        joinMeetingBtn.setBackground(new java.awt.Color(204, 204, 255));
        joinMeetingBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        joinMeetingBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                joinMeetingBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                joinMeetingBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                joinMeetingBtnMouseExited(evt);
            }
        });

        reunionLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        reunionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reunionLabel.setText("Unirse");

        javax.swing.GroupLayout joinMeetingBtnLayout = new javax.swing.GroupLayout(joinMeetingBtn);
        joinMeetingBtn.setLayout(joinMeetingBtnLayout);
        joinMeetingBtnLayout.setHorizontalGroup(
            joinMeetingBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, joinMeetingBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reunionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        joinMeetingBtnLayout.setVerticalGroup(
            joinMeetingBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, joinMeetingBtnLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reunionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(joinMeetingBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 180, 40));

        startMeeting.setBackground(new java.awt.Color(204, 204, 255));
        startMeeting.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startMeeting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMeetingMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startMeetingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                startMeetingMouseExited(evt);
            }
        });

        reunionLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        reunionLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reunionLabel1.setText("Iniciar Reunión");

        javax.swing.GroupLayout startMeetingLayout = new javax.swing.GroupLayout(startMeeting);
        startMeeting.setLayout(startMeetingLayout);
        startMeetingLayout.setHorizontalGroup(
            startMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reunionLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        startMeetingLayout.setVerticalGroup(
            startMeetingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, startMeetingLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reunionLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(startMeeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 620, 180, 40));

        generatePort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePortActionPerformed(evt);
            }
        });
        jPanel2.add(generatePort, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 60, 40));

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
        chat.setVisible(false);
    }//GEN-LAST:event_vozBtnActionPerformed
    private void chatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chatBtnActionPerformed

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
            cliente.getHc().cameraOn(nameField.getText());
        } else {
            jvideo.setIcon(videoOff);
            cliente.getHc().cameraOff(nameField.getText());
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

    private void ipFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ipFieldMousePressed
        if (ipField.getText().equals("localhost...")) {
            ipField.setText("");
            ipField.setForeground(Color.BLACK);
        }

        if (nameField.getText().isEmpty()) {
            nameField.setText("Mi nombre...");
            nameField.setForeground(Color.GRAY);
        }

        if (portField.getText().isEmpty()) {
            portField.setText("1234...");
            portField.setForeground(Color.gray);
        }
    }//GEN-LAST:event_ipFieldMousePressed

    private void nameFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameFieldMousePressed
        if (nameField.getText().equals("Mi nombre...")) {
            nameField.setText("");
            nameField.setForeground(Color.BLACK);
        }

        if (portField.getText().isEmpty()) {
            portField.setText("1234...");
            portField.setForeground(Color.GRAY);
        }

        if (ipField.getText().isEmpty()) {
            ipField.setText("localhost...");
            ipField.setForeground(Color.gray);
        }
    }//GEN-LAST:event_nameFieldMousePressed

    private void portFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portFieldMousePressed
        if (portField.getText().equals("1234...")) {
            portField.setText("");
            portField.setForeground(Color.BLACK);
        }

        if (nameField.getText().isEmpty()) {
            nameField.setText("Mi nombre...");
            nameField.setForeground(Color.GRAY);
        }

        if (ipField.getText().isEmpty()) {
            ipField.setText("localhost...");
            ipField.setForeground(Color.gray);
        }
    }//GEN-LAST:event_portFieldMousePressed

    private void cerrarBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarBotonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_cerrarBotonMouseClicked

    private void cerrarBotonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarBotonMouseEntered
        cerrarBoton.setBackground(Color.RED);
        cerrarLabel.setForeground(Color.WHITE);
    }//GEN-LAST:event_cerrarBotonMouseEntered

    private void cerrarBotonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarBotonMouseExited
        cerrarBoton.setBackground(Color.WHITE);
        cerrarLabel.setForeground(Color.BLACK);
    }//GEN-LAST:event_cerrarBotonMouseExited

    private void barraSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraSuperiorMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_barraSuperiorMouseDragged

    private void barraSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraSuperiorMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_barraSuperiorMousePressed

    private void choosePicBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choosePicBtnMouseClicked
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
    }//GEN-LAST:event_choosePicBtnMouseClicked

    private void choosePicBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choosePicBtnMouseEntered
        choosePicBtn.setBackground(new Color(134, 134, 255));
    }//GEN-LAST:event_choosePicBtnMouseEntered

    private void choosePicBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choosePicBtnMouseExited
        choosePicBtn.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_choosePicBtnMouseExited

    private void joinMeetingBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joinMeetingBtnMouseClicked
        try {
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No hay ninguna reunion empezada con ese id");
            System.exit(0);
        }

    }//GEN-LAST:event_joinMeetingBtnMouseClicked

    public JFrame getChat() {
        return chat;
    }

    private void joinMeetingBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joinMeetingBtnMouseEntered
        joinMeetingBtn.setBackground(new Color(134, 134, 255));
    }//GEN-LAST:event_joinMeetingBtnMouseEntered

    private void joinMeetingBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_joinMeetingBtnMouseExited
        joinMeetingBtn.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_joinMeetingBtnMouseExited

    private void startMeetingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMeetingMouseClicked
        if (nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite su nombre");
        } else {
            try {
                this.setVisible(false);
                cliente.createMeeting(Integer.parseInt(portField.getText()));
                cliente.connect(ipField.getText(), Integer.parseInt(portField.getText()));
                cliente.getHc().insertarCliente(nameField.getText(), fotoPath);
                chat.setVisible(true);
                chat.setLocationRelativeTo(null);
                chat.setSize(1230, 730); //778,520

                String myString = nameField.getText() + " te ha invitado a la reunion\nID de reunion: " + portField.getText();
                StringSelection stringSelection = new StringSelection(myString);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

            } catch (IOException ex) {
                Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_startMeetingMouseClicked

    private void startMeetingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMeetingMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_startMeetingMouseEntered

    private void startMeetingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMeetingMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_startMeetingMouseExited

    private void generatePortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePortActionPerformed
        int n = (int) (Math.random() * (65531 - 49149 + 1) + 49149) + 3;
        portField.setText(n + "");
    }//GEN-LAST:event_generatePortActionPerformed

    public void setTableProperties(JTable table, JScrollPane jScrollPane) {
        table.setOpaque(false);
        table.setShowGrid(false);
        table.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0, 0, 0, 0.6f));
        table.getTableHeader().setForeground(new Color(44, 47, 51));
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        table.setTableHeader(null);
    }

    public void addExitOperation(JFrame frame) {
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
    }

    public JPanel getCallPanel() {
        return callPanel;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void exitProcedure() {
        cliente.getHc().deleteUser(nameField.getText());
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

    public static void main(String args[]) throws InstantiationException {
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
    private javax.swing.JPanel barraSuperior;
    private javax.swing.JFrame call;
    private javax.swing.JPanel callPanel;
    private javax.swing.JPanel cerrarBoton;
    private javax.swing.JLabel cerrarLabel;
    private javax.swing.JFrame chat;
    private javax.swing.JButton chatBtn;
    private javax.swing.JButton chatBtn1;
    private javax.swing.JPanel choosePicBtn;
    private javax.swing.JLabel choosePicLabel;
    private javax.swing.JLabel direccionLabel;
    private javax.swing.JButton generatePort;
    private javax.swing.JLabel iconoDer;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel imagenLabel;
    private javax.swing.JLabel iniciarTitulo;
    private javax.swing.JTextField ipField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jaudio;
    private javax.swing.JTextField jmensaje;
    private javax.swing.JPanel joinMeetingBtn;
    private javax.swing.JButton jvideo;
    private javax.swing.JSlider micVol;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nombreApp;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField portField;
    private javax.swing.JLabel reunionLabel;
    private javax.swing.JLabel reunionLabel1;
    private javax.swing.JSeparator separadorIcono;
    private javax.swing.JSeparator separadorNombre;
    private javax.swing.JSeparator separadorNombre1;
    private javax.swing.JSeparator separadorNombre2;
    private javax.swing.JPanel side;
    private javax.swing.JPanel side1;
    private javax.swing.JLabel sloganApp;
    private javax.swing.JPanel startMeeting;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JButton userPic;
    private javax.swing.JButton userPic1;
    private javax.swing.JPanel users;
    private javax.swing.JButton vozBtn;
    private javax.swing.JButton vozBtn1;
    // End of variables declaration//GEN-END:variables

}
