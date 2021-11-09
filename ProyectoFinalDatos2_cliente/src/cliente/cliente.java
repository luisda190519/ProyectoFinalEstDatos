package cliente;

import GUI.changeCellColor;
import GUI.imgTabla;
import GUI.inicio;
import GUI.userPanel;
import GUI.vozGUI;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.util.ImageUtils;
import static com.github.sarxos.webcam.util.ImageUtils.toByteArray;
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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class cliente {

    String nombre;
    public String foto;
    public ArrayList<String> fotos = new ArrayList<String>();
    public boolean tablaCreada = false;
    public ImageIcon clientUserPic;
    public static JTable chatTable;
    public static JTable usuariosConectados;
    private File folder = new File("data");
    private File archivo = new File(folder, "usuarios.txt");
    private static inicio inicio;
    private boolean camaraPrendida;
    private static String titulos[] = {"", ""};
    private static DefaultTableModel chatModel = new DefaultTableModel(null, titulos);
    private static DefaultTableModel usuariosConectadosModel = new DefaultTableModel(null, titulos);
    private ArrayList<userPanel> userPanels = new ArrayList<userPanel>();
    private Socket socketClient;
    private hiloChatCliente hc;
    private static cliente cliente;
    private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
    private hiloImagen hi;
    private ArrayList<String> names = new ArrayList<String>();

    public cliente getCliente() {
        return cliente;
    }

    public cliente() {

    }

    public ImageIcon searchPic(String path) {
        for (ImageIcon icon : images) {
            if (icon.equals(path)) {
                return icon;
            }
        }
        return null;
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

    public hiloChatCliente getHc() {
        return hc;
    }

    public void connect(String ip, int port) {
        try {
            socketClient = new Socket(ip, port);
            hc = new hiloChatCliente(socketClient, cliente);
            hc.start();

            Socket s = new Socket(ip, port + 1);
            hiloImagen hi = new hiloImagen(s, cliente);
            hi.start();

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void updateUsers() {
        for (userPanel u : userPanels) {
            inicio.getCallPanel().add(u);
        }
    }

    public void updatePanels() {
        inicio.getCallPanel().removeAll();
        userPanels.clear();
        inicio.getCallPanel().setLayout(new GridLayout(5, 5));

        for (ImageIcon icon : images) {
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(150, 96, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newimg);
            userPanel u = new userPanel(icon);
            userPanels.add(u);
            updateUsers();
        }

    }

    public static void propiedadesTabla2() {
        chatTable.setDefaultRenderer(Object.class, new imgTabla());
        chatTable.setRowHeight(50);
        chatTable.setModel(chatModel);
        chatTable.getColumnModel().getColumn(1).setCellRenderer(new changeCellColor(44, 47, 51));
        usuariosConectados.setDefaultRenderer(Object.class, new imgTabla());
        usuariosConectados.setRowHeight(50);
        usuariosConectados.setModel(usuariosConectadosModel);
        usuariosConectados.getColumnModel().getColumn(1).setCellRenderer(new changeCellColor(35, 39, 42));
    }

    public void setTableIcon(String foto) throws IOException {
        clientUserPic = new ImageIcon(foto);
        Image img = clientUserPic.getImage();
        Image newimg = img.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
        clientUserPic = new ImageIcon(newimg);
        BufferedImage br;
        Webcam cam = Webcam.getDefault();
        //fill(foto);

        if (camaraPrendida == true) {
            while (camaraPrendida == true) {
                cam.open();
                br = cam.getImage();
                clientUserPic = new ImageIcon(br);
                Image newimg2 = br.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
                clientUserPic = new ImageIcon(newimg2);
            }
        } else {
            //fill(foto);
            cam.close();
        }

    }

    public static DefaultTableModel getChatModel() {
        return chatModel;
    }

    public void addClientImage(BufferedImage img) {
        ImageIcon icon = new ImageIcon(img);
        images.add(icon);
    }

    public void clearImages() {
        images.clear();
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
                System.out.println("el data 0 es " + data[0] + " y el i es " + i);
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

    public File getFolder() {
        return folder;
    }

    public String getNombre() {
        return nombre;
    }

    public static inicio getInicio() {
        return inicio;
    }

    public File getArchivo() {
        return archivo;
    }

    public String getFoto() {
        return foto;
    }

}
