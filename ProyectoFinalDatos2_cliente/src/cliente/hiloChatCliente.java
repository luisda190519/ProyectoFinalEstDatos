package cliente;

import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class hiloChatCliente extends Thread {

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
    private hiloImagen hi;

    public hiloChatCliente(Socket socket, cliente c) throws IOException {
        this.socketClient = socket;
        this.c = c;
        dataInputStream = new DataInputStream(socketClient.getInputStream());
        dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
        this.writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
        this.reader = new BufferedReader(new InputStreamReader(dataInputStream));

        hi = new hiloImagen(socketClient, c, this);
        hi.start();

    }

    public void enviarMensaje(String nombre, String mensaje) {
        try {
            writer.write("\n");
            writer.write("/starMessage\n");
            writer.write(nombre + ": " + mensaje + "\n");
            writer.write(foto);
            writer.write("\n/stopMessage");
            writer.write("\r\n");
            writer.flush();
        } catch (Exception e) {
            System.out.println("error " + e);
        }
    }

    public void deleteUser() {
        try {
            writer.write("/deleteUser\n");
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
            c.getUsuariosConectadosModel().setRowCount(0);
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

    public void esperar() {
        try {
            hi.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(hiloChatCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            String msg = "", texto = "";
            int i = 0, j = 0, aux = 0;
            sleep(500);

            while ((msg = reader.readLine()) != null) {
                msg = reader.readLine();
                if (msg.equals("/nuevoUsuario")) {
                    c.getUsuariosConectadosModel().setRowCount(0);
                    msg = reader.readLine();
                    while (!msg.equals("/finUsuario")) {
                        if (i > 0) {
                            hi.notify();
                        }
                        c.updateImages(msg, i);
                        msg = reader.readLine();
                        i++;
                    }

                } else if (msg.equals("/starMessage")) {
                    msg = reader.readLine();
                    texto = msg;
                    msg = reader.readLine();
                    String path = msg;
                    foto2 = c.getImages().get(c.getIndex(msg));
                    Image img = foto2.getImage();
                    Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    foto2 = new ImageIcon(newimg);
                    c.getChatModel().addRow(new Object[]{new JLabel(foto2), texto});
                    msg = reader.readLine();
                }
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
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

    public String getFoto() {
        return foto;
    }

    public String getNombre() {
        return nombre;
    }

}
