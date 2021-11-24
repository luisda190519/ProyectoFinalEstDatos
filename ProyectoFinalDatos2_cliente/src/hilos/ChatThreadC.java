package hilos;

import cliente.cliente;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ChatThreadC extends Thread {

    private BufferedWriter writer;
    private BufferedReader reader;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private Socket socketClient;
    private cliente c;
    private String foto;
    private String nombre;
    private ImageIcon foto2;
    private File folder = new File("data");
    private File archivo = new File(folder, "usuarios.txt");
    private int port;
    private String ip;
    private CamaraThreadC hcc;

    public ChatThreadC(Socket socket, cliente c, String ip, int port) throws IOException {
        this.socketClient = socket;
        this.c = c;
        this.port = port;
        this.ip = ip;
        hcc = new CamaraThreadC(port, ip, c);
        hcc.start();

        dataInputStream = new DataInputStream(socketClient.getInputStream());
        dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
        this.writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
        this.reader = new BufferedReader(new InputStreamReader(dataInputStream));
    }

    public void sendMessage(String msg) {
        try {
            writer.write(msg);
            writer.write("\r\n");
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatThreadC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void enviarMensaje(String nombre, String mensaje) {
        try {
            sendMessage("/starMessage");
            sendMessage(nombre + ": " + mensaje + "\n");
            sendMessage("/stopMessage");
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void enviarComands(String nombre, boolean auxiliar) {
        try {
            if (auxiliar) {
                sendMessage("/joinChat");
                sendMessage(nombre);
            } else {
                sendMessage("/leftChat");
                sendMessage(nombre);
            }
        } catch (Exception e) {
            System.out.println("error " + e);
        }

    }

    public void deleteUser(String name) {
        try {
            if (c.isAdmin()) {
                sendMessage("/closeMeeting");
            } else {
                enviarComands(name, false);
                sendMessage("/deleteUser");
                sendMessage(name);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void cameraOn(String name) {
        sendMessage("/cameraOn");
        sendMessage(name);
    }

    public void cameraOff(String name) {
        sendMessage("/cameraOff");
        sendMessage(name);
    }

    public void insertarCliente(String nombre, String foto) {
        try {
            this.nombre = nombre;
            this.foto = foto;
            c.setNombre(this.nombre);
            hcc.setNameOwner(this.nombre);
            c.getUsuariosConectadosModel().setRowCount(0);
            sendMessage("/addUser");
            sendMessage(this.nombre);
            sendMessage(foto);
            sendMessage("/closeUser");
            enviarComands(nombre, true);
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    @Override
    public void run() {
        try {
            String msg = "", texto = "";
            int i = 0;

            while (msg != null) {
                sleep(100);
                msg = reader.readLine();

                if (msg.equals("")) {
                    msg = reader.readLine();
                }

                if (msg.equals("/nuevoUsuario")) {
                    sleep(1000);
                    msg = reader.readLine();
                    c.getUsuariosConectadosModel().setRowCount(0);
                    while (!msg.equals("/finUsuario")) {
                        c.updateImages(msg, i);
                        msg = reader.readLine();
                        i++;
                    }
                    c.updatePanels();
                    c.updateConnectedUsers();
                    c.makeAdmin();

                } else if (msg.equals("/starMessage")) {
                    msg = reader.readLine();
                    foto2 = c.getImages().get(c.getIndex(msg));
                    Image imgage = foto2.getImage();
                    Image newimg = imgage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    foto2 = new ImageIcon(newimg);
                    c.showMessage(foto2, msg, this.nombre);
                    msg = reader.readLine();
                } else if (msg.equals("/clear")) {
                    c.clearPanel();
                    i = 0;
                } else if (msg.equals("/cameraOn")) {
                    msg = reader.readLine();
                    hcc.setNameCameraStart(msg);
                    hcc.setCameraOn(true);
                    synchronized (hcc) {
                        hcc.notify();
                    }
                } else if (msg.equals("/cameraOff")) {
                    msg = reader.readLine();
                    hcc.setNameCameraStart(msg);
                    hcc.setCameraOn(false);
                    c.stopCamera(c.getIndexCamera(msg));
                } else if (msg.equals("/nameRepeted")) {
                    c.getInicio().getChat().setVisible(false);
                    c.getInicio().setVisible(true);
                    socketClient.close();
                    JOptionPane.showMessageDialog(null, "Nombre de usuario ya esta en uso");
                    System.exit(0);
                    break;
                } else if (msg.equals("/deleteUser")) {
                    msg = reader.readLine();
                    c.deleteUser(msg);
                    c.updateConnectedUsers();
                } else if (msg.equals("/joinChat")) {
                    msg = reader.readLine();
                    foto2 = c.getImages().get(c.getIndex2(msg));
                    Image imgage = foto2.getImage();
                    Image newimg = imgage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    foto2 = new ImageIcon(newimg);
                    c.showMessage(foto2, msg, " se ha unido a la reunion", this.nombre, true);
                } else if (msg.equals("/leftChat")) {
                    msg = reader.readLine();
                    foto2 = c.getImages().get(c.getIndex2(msg));
                    Image imgage = foto2.getImage();
                    Image newimg = imgage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    foto2 = new ImageIcon(newimg);
                    c.showMessage(foto2, msg, " abandono la reunion", this.nombre, false);
                } else if (msg.equals("/closeMeeting")) {
                    System.exit(0);
                } else if (msg.equals("/camaraBug")) {
                    c.stopCamera(c.getIndexCamera(hcc.getNameAux()));
                    System.out.println("llegue");
                }
            }

        } catch (Exception e) {
            System.out.println("error " + e);
            Logger.getLogger(ChatThreadC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

}
