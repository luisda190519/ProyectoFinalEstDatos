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

    public hiloChatCliente(Socket socket, cliente c) throws IOException {
        this.socketClient = socket;
        this.c = c;
        dataInputStream = new DataInputStream(socketClient.getInputStream());
        dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
        this.writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
        this.reader = new BufferedReader(new InputStreamReader(dataInputStream));
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

                        c.getUsuariosConectadosModel().setRowCount(0);
                        try (Scanner entrada = new Scanner(archivo)) {
                            while (entrada.hasNextLine()) {
                                String linea = entrada.nextLine();
                                String data2[] = linea.split(",");
                                icon = new ImageIcon(data2[1]);
                                img = icon.getImage();
                                newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                                icon = new ImageIcon(newimg);
                                c.getUsuariosConectadosModel().addRow(new Object[]{new JLabel(icon), data2[0]});
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
                    c.getChatModel().addRow(new Object[]{new JLabel(foto2), texto});

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
