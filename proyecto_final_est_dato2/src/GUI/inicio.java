package GUI;

import cliente.MicThread;
import cliente.SoundPacket;
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
import servidor.Log;
import servidor.Server;
import servidor.Utils;
import static cliente.cliente.chatTable;
import java.awt.event.WindowAdapter;
import javax.swing.JPanel;

public class inicio extends javax.swing.JFrame {

    private cliente cliente = new cliente();
    private ImageIcon foto;
    private String fotoPath;

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
        side = new javax.swing.JPanel();
        chatBtn = new javax.swing.JButton();
        vozBtn = new javax.swing.JButton();
        userPic = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        jmensaje = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
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
        chat.setPreferredSize(new java.awt.Dimension(1200, 700));
        chat.setResizable(false);
        chat.setSize(new java.awt.Dimension(1200, 700));

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

        panel.setBackground(new java.awt.Color(44, 47, 51));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jmensaje.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jmensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmensajeActionPerformed(evt);
            }
        });
        panel.add(jmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 670, 40));

        jScrollPane2.setBorder(null);

        table1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table1.setEnabled(false);
        jScrollPane2.setViewportView(table1);

        panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 670, 580));

        jPanel1.setBackground(new java.awt.Color(35, 39, 42));

        jScrollPane3.setBorder(null);

        table2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table2.setEnabled(false);
        jScrollPane3.setViewportView(table2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(44, 47, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout chatLayout = new javax.swing.GroupLayout(chat.getContentPane());
        chat.getContentPane().setLayout(chatLayout);
        chatLayout.setHorizontalGroup(
            chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatLayout.createSequentialGroup()
                .addComponent(side, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(243, Short.MAX_VALUE)))
        );
        chatLayout.setVerticalGroup(
            chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(side, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(44, 47, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        joinMeeting.setBackground(new java.awt.Color(114, 137, 218));
        joinMeeting.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        joinMeeting.setForeground(new java.awt.Color(255, 255, 255));
        joinMeeting.setText("Iniciar reunion");
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

        choosePicBtn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        choosePicBtn.setText("Escoja su foto de perfil");
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
            cliente.enviarMensaje(nameField.getText(), jmensaje.getText());
            jmensaje.setText("");
        }
    }//GEN-LAST:event_jmensajeActionPerformed

    private void vozBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vozBtnActionPerformed
        foto = (ImageIcon) userPic.getIcon();
        vozGUI voz = new vozGUI(cliente, foto, chat);
        voz.setVisible(true);
        voz.setLocationRelativeTo(null);
        chat.setVisible(false);
        JPanel panel = voz.getPanel();
        añadirPanel(panel, voz);
    }//GEN-LAST:event_vozBtnActionPerformed
    private void chatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chatBtnActionPerformed

    private void joinMeetingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinMeetingActionPerformed
        if (nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite su nombre");
        } else if (buscarNombre(nameField.getText())) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre");
        } else {
            this.setVisible(false);
            cliente.insertarCliente(nameField.getText(), fotoPath);
            cliente.makeFile();
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

    public void añadirPanel(JPanel panel, vozGUI voz) {
        cliente.setPanel(panel, voz);
    }

    public cliente getCliente() {
        return cliente;
    }

    public void deleteUserProfile() {
        File inputFile = cliente.getArchivo();
        File tempFile = new File("data/myTempFile.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String lineToRemove = cliente.getNombre() + "," + cliente.getFoto();
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
            System.out.println("error"+e);
        }
    }

    public void exitProcedure() {
        deleteUserProfile();
        this.dispose();
        System.exit(0);
    }

    public boolean buscarNombre(String nombreBuscar) {
        File file = cliente.getArchivo();
        try (Scanner entrada = new Scanner(file)) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                String data[] = linea.split(",");
                String nombre = data[0];
                String dir = data[1];
                if (nombre.equals(nombreBuscar)) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println("error");
        }
        return false;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTable getTable12() {
        return table2;
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
    private javax.swing.JFrame chat;
    private javax.swing.JButton chatBtn;
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
    private javax.swing.JTextField jmensaje;
    private javax.swing.JButton joinMeeting;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField portField;
    private javax.swing.JPanel side;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JLabel title;
    private javax.swing.JButton userPic;
    private javax.swing.JButton vozBtn;
    // End of variables declaration//GEN-END:variables
}
