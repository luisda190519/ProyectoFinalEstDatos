package cliente;

import soundUtils.MicrofonoThread;
import soundUtils.VozThread;
import hilos.ChatThreadC;
import GUI.changeCellColor;
import GUI.imgTabla;
import GUI.inicio;
import GUI.userPanel;
import Server.Server;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;
import static com.github.sarxos.webcam.util.ImageUtils.toByteArray;
import hilos.ImagenesThreadC;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class cliente {

    String nombre;
    public String foto;
    public ArrayList<String> fotos = new ArrayList<String>();
    public boolean tablaCreada = false;
    public ImageIcon clientUserPic;
    public static JTable chatTable;
    public static JTable usuariosConectados;
    private static inicio inicio;
    private boolean camaraPrendida;
    private static String titulos[] = {"", ""};
    private static DefaultTableModel chatModel = new DefaultTableModel(null, titulos);
    private static DefaultTableModel usuariosConectadosModel = new DefaultTableModel(null, titulos);
    private ArrayList<userPanel> userPanels = new ArrayList<userPanel>();
    private Socket socketClient;
    private ChatThreadC hc;
    private static cliente cliente;
    private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
    private ArrayList<String> names = new ArrayList<String>();
    private int index;
    private Socket s;
    private VozThread cv;

    public cliente() {

    }

    public static void main(String[] args) throws IOException {
        inicio = new inicio();
        inicio.setVisible(true);
        inicio.setLocationRelativeTo(null);
        inicio.setSize(1200, 700);
        chatTable = inicio.getTable1();
        usuariosConectados = inicio.getTable12();
        propiedadesTabla2();

        cliente = new cliente();
        inicio.setCliente(cliente);
    }

    public void connect(String ip, int port) {
        try {
            socketClient = new Socket(ip, port);
            hc = new ChatThreadC(socketClient, cliente, ip, port);
            hc.start();

            s = new Socket(ip, port + 1);
            ImagenesThreadC hi = new ImagenesThreadC(s, cliente);
            hi.start();

            cv = new VozThread(ip, port + 2);
            cv.start();
            MicrofonoThread.amplification = 0;

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void createMeeting(int port) throws IOException {
        new Thread() {
            @Override
            public void run() {
                try {
                    Server servidor = new Server(port);
                } catch (Exception ex) {
                    System.exit(0);
                }
            }
        }.start();
    }

    public void closeSockets() throws IOException {
        socketClient.close();
        s.close();
        cv.closeSocket();
    }

    public void updateUsers() {
        for (userPanel u : userPanels) {
            inicio.getCallPanel().add(u);
        }
        inicio.getCallPanel().revalidate();
        inicio.getCallPanel().repaint();
    }

    public void showMessage(ImageIcon foto2, String msg, String nombre) {
        String data[] = msg.split(":");
        if (data[0].equals(nombre)) {
            getChatModel().addRow(new Object[]{new JLabel(foto2), "tu: " + data[1]});
        } else {
            if (data[1].substring(2, data[1].length()).equals(nombre) && data[1].substring(1, 2).equals("@")) {
                try {
                    getChatModel().addRow(new Object[]{new JLabel(foto2), msg});
                    String soundName = "src/audio/audio.wav";
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                getChatModel().addRow(new Object[]{new JLabel(foto2), msg});
            }
        }
    }

    public void showMessage(ImageIcon foto2, String sender, String msg, String nombre, boolean aux) {
        if (aux) {
            if (sender.equals(nombre)) {
                getChatModel().addRow(new Object[]{new JLabel(foto2), "Te has unido a la reunion "});
            } else {
                getChatModel().addRow(new Object[]{new JLabel(foto2), sender + msg});
            }
        } else {
            if (sender.equals(nombre)) {
                getChatModel().addRow(new Object[]{new JLabel(foto2), "Has avandonado la reunion"});
            } else {
                getChatModel().addRow(new Object[]{new JLabel(foto2), sender + msg});
            }
        }

    }

    public void updatePanels() {
        inicio.getCallPanel().setLayout(new GridLayout(3, 3));
        updateUsers();
    }

    public void cameraOn(BufferedImage img, int index) {
        ImageIcon icon = new ImageIcon(img);
        Image imageChange = icon.getImage();
        Image newimg = imageChange.getScaledInstance(202, 108, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        userPanel u = new userPanel(icon);
        userPanels.set(index, u);
        inicio.getCallPanel().removeAll();
        updatePanels();
    }

    public void stopCamera(int indexCamera) {
        ImageIcon icon = images.get(indexCamera);
        Image imageChange = icon.getImage();
        Image newimg = imageChange.getScaledInstance(202, 108, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        userPanel u = new userPanel(icon);
        userPanels.set(indexCamera, u);
        inicio.getCallPanel().removeAll();
        inicio.getCallPanel().revalidate();
        inicio.getCallPanel().repaint();
        updatePanels();
    }

    public int getIndexCamera(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (name.equals(names.get(i))) {
                return i;
            }
        }
        return 0;
    }

    public static void propiedadesTabla2() {
        chatTable.setDefaultRenderer(Object.class, new imgTabla());
        chatTable.setRowHeight(50);
        chatTable.setModel(chatModel);
        chatTable.getColumnModel().getColumn(1).setCellRenderer(new changeCellColor(255, 255, 255));
        usuariosConectados.setDefaultRenderer(Object.class, new imgTabla());
        usuariosConectados.setRowHeight(50);
        usuariosConectados.setModel(usuariosConectadosModel);
        usuariosConectados.getColumnModel().getColumn(1).setCellRenderer(new changeCellColor(255, 255, 255));
    }

    public static DefaultTableModel getChatModel() {
        return chatModel;
    }

    public void addClientImage(BufferedImage img) {
        ImageIcon icon = new ImageIcon(img);
        images.add(icon);
        Image imageChange = icon.getImage();
        Image newimg = imageChange.getScaledInstance(202, 108, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        userPanel u = new userPanel(icon);
        userPanels.add(u);
    }

    public void deleteUser(String name) {
        int i = names.indexOf(name);
        names.remove(i);
        images.remove(i);
        userPanels.remove(i);
        getUsuariosConectadosModel().removeRow(i);
        inicio.getCallPanel().removeAll();
        inicio.getCallPanel().revalidate();
        inicio.getCallPanel().repaint();
        updatePanels();
    }

    public void clearPanel() {
        images.clear();
        names.clear();
        userPanels.clear();
        inicio.getCallPanel().removeAll();
    }

    public void updateImages(String name, int i) {
        Image img = images.get(i).getImage();
        names.add(name);
        Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newimg);
        usuariosConectadosModel.addRow(new Object[]{new JLabel(icon), name});
    }

    public int getIndex(String msg) {
        String data[] = msg.split(":");
        for (int i = 0; i < names.size(); i++) {
            if (data[0].equals(names.get(i))) {
                return i;
            }
        }
        return 0;
    }

    public int getIndex2(String msg) {
        for (int i = 0; i < names.size(); i++) {
            if (msg.equals(names.get(i))) {
                return i;
            }
        }
        return 0;
    }

    public ArrayList<ImageIcon> getImages() {
        return images;
    }

    public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static final byte[] getImageBytes(Webcam webcam, String format) {
        return ImageUtils.toByteArray(webcam.getImage(), format);
    }

    public static DefaultTableModel getUsuariosConectadosModel() {
        return usuariosConectadosModel;
    }

    public static BufferedImage toBufferedImage(byte[] bytes) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public inicio getInicio() {
        return inicio;
    }

    public String getFoto() {
        return foto;
    }

    public ChatThreadC getHc() {
        return hc;
    }

    public ImageIcon searchPic(String path) {
        for (ImageIcon icon : images) {
            if (icon.equals(path)) {
                return icon;
            }
        }
        return null;
    }

    public cliente getCliente() {
        return cliente;
    }

}
