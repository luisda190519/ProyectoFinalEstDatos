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
    public JPanel panel;
    public String foto;
    public ArrayList<String> fotos = new ArrayList<String>();
    public boolean tablaCreada = false;
    public ImageIcon foto2;
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

    public cliente getCliente() {
        return cliente;
    }

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

    public hiloChatCliente getHc() {
        return hc;
    }

    public void connect(String ip, int port) {
        try {
            socketClient = new Socket(ip, port);
            hc = new hiloChatCliente(socketClient, cliente);
            hc.start();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void updateUsers() {
        for (userPanel u : userPanels) {
            panel.add(u);
        }
    }

    public void setPanel(JPanel panel, vozGUI voz) {
        this.panel = panel;
        this.panel.removeAll();
        this.panel.setLayout(new GridLayout(5, 5));
        this.camaraPrendida = voz.isCamaraPrendida();
        this.tablaCreada = true;
        updatePanels();
    }

    public void updatePanels() {
        this.panel.removeAll();
        this.panel.setLayout(new GridLayout(5, 5));

        try (Scanner entrada = new Scanner(archivo)) {
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                String data[] = linea.split(",");
                ImageIcon icon = new ImageIcon(data[1]);
                Image img = icon.getImage();
                Image newimg = img.getScaledInstance(150, 96, java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                userPanel u = new userPanel(icon);
                userPanels.add(u);
                updateUsers();
            }
        } catch (Exception ex) {
            System.out.println("error");
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
        foto2 = new ImageIcon(foto);
        Image img = foto2.getImage();
        Image newimg = img.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
        foto2 = new ImageIcon(newimg);
        BufferedImage br;
        Webcam cam = Webcam.getDefault();
        //fill(foto);

        if (camaraPrendida == true) {
            while (camaraPrendida == true) {
                cam.open();
                br = cam.getImage();
                foto2 = new ImageIcon(br);
                Image newimg2 = br.getScaledInstance(95, 95, java.awt.Image.SCALE_SMOOTH);
                foto2 = new ImageIcon(newimg2);
            }
        } else {
            //fill(foto);
            cam.close();
        }

    }

    public static DefaultTableModel getChatModel() {
        return chatModel;
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

    public void makeFile() {
        if (!archivo.exists()) {
            folder.mkdir();
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                System.out.println("error");
            }
        }
        try (FileWriter fw = new FileWriter(archivo.getAbsoluteFile(), true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nombre + "," + foto);
            bw.newLine();
            bw.flush();
            bw.close();
            fw.close();

        } catch (Exception e) {
            System.out.println("error");
        }

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
