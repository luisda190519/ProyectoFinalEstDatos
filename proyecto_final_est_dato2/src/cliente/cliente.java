package cliente;

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

public class cliente implements Runnable {

    BufferedWriter writer;
    BufferedReader reader;
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

    public cliente() {
        try {
            Socket socketClient = new Socket("localhost", 2003);
            this.writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            this.reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public static void main(String[] args) {
        inicio = new inicio();
        inicio.setVisible(true);
        inicio.setLocationRelativeTo(null);
        inicio.setSize(1200, 700);
        chatTable = inicio.getTable1();
        usuariosConectados = inicio.getTable12();
        propiedadesTabla2();

        Thread t1 = new Thread(inicio.getCliente());
        t1.start();
    }

    public void enviarMensaje(String nombre, String mensaje) {
        try {
            writer.write(nombre + ": " + mensaje + "\n");
            writer.write(foto);
            writer.write("\r\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void insertarCliente(String nombre, String foto) {
        try {
            this.nombre = nombre;
            this.foto = foto;
            makeFile();
            usuariosConectadosModel.setRowCount(0);
            writer.write("/addUser\n");
            writer.write(this.nombre + "\n");
            writer.write(foto + "\n");
            writer.write("/closeUser");
            writer.write("\r\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void updateUsers(JPanel panel, ImageIcon imagen) {
        panel.add(new userPanel(imagen));
    }

    @Override
    public void run() {
        try {
            String msg = "", texto = "";
            int i = 0, j = 0, aux = 0;

            while ((msg = reader.readLine()) != null) {
                System.out.println(msg);

                if (msg.equals("/nuevoUsuario")) {
                    msg = reader.readLine();
                    while (!msg.equals("/finUsuario")) {
                        String data[] = msg.split(",");
                        ImageIcon icon = new ImageIcon(data[1]);
                        Image img = icon.getImage();
                        Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                        icon = new ImageIcon(newimg);
                        //usuariosConectadosModel.addRow(new Object[]{new JLabel(icon), data[0]});

                        usuariosConectadosModel.setRowCount(0);
                        try (Scanner entrada = new Scanner(archivo)) {
                            while (entrada.hasNextLine()) {
                                String linea = entrada.nextLine();
                                String data2[] = linea.split(",");
                                icon = new ImageIcon(data2[1]);
                                img = icon.getImage();
                                newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                icon = new ImageIcon(newimg);
                                usuariosConectadosModel.addRow(new Object[]{new JLabel(icon), data2[0]});
                            }
                        } catch (Exception ex) {
                            System.out.println("error");
                        }

                        msg = reader.readLine();
                    }

                    System.out.println("el fin es " + msg);

                } else {
                    System.out.println("entre " + msg);
                    texto = msg;
                    msg = reader.readLine();
                    String path = msg;
                    foto2 = new ImageIcon(path);
                    Image img = foto2.getImage();
                    Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    foto2 = new ImageIcon(newimg);
                    chatModel.addRow(new Object[]{new JLabel(foto2), texto});

                }

//                if (i == 0 && !msg.equals(foto)) {
//                    //chat.append(msg + "\n");
//                    texto = msg;
//                    i++;
//                } else {
//                    if (tablaCreada == true) {
//                        if (j == 0) {
//                            buscarPosx(aux);
//                            buscarPosy(aux);
//                            setTableIcon(msg);
//                            j++;
//                            aux++;
//                        }
//                    }
//
//                    if (!texto.equals("") && !texto.equals(nombre)) {
//                        foto2 = new ImageIcon(msg);
//                        Image img = foto2.getImage();
//                        Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
//                        foto2 = new ImageIcon(newimg);
//
//                        chatModel.addRow(new Object[]{new JLabel(foto2), texto});
//                        //tabla2.changeSelection(tabla2.getRowCount() - 1, 0, false, false);
//                        chatTable.scrollRectToVisible(chatTable.getCellRect(chatTable.getRowCount() - 1, chatTable.getColumnCount(), true));
//                        System.out.println(msg);
//                    }
//
//                    i = 0;
//                    j = 0;
//
//                }
            }

        } catch (Exception e) {
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
                updateUsers(this.panel, icon);
            }
        } catch (Exception ex) {
            System.out.println("error");
        }

    }

    public static void propiedadesTabla2() {
        chatTable.setDefaultRenderer(Object.class, new imgTabla());
        chatTable.setRowHeight(50);
        chatTable.setModel(chatModel);
        usuariosConectados.setDefaultRenderer(Object.class, new imgTabla());
        usuariosConectados.setRowHeight(50);
        usuariosConectados.setModel(usuariosConectadosModel);
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

    public static byte[] toByteArray(BufferedImage bi, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

    public static final byte[] getImageBytes(Webcam webcam, String format) {
        return ImageUtils.toByteArray(webcam.getImage(), format);
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
