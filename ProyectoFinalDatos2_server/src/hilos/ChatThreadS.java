package hilos;

import Server.Server;
import com.github.sarxos.webcam.Webcam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;

public class ChatThreadS extends Thread {

    private Socket socket;
    private Server server;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ChatThreadS(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void enviarMensaje(String mensaje) throws IOException {
        writer.write(mensaje);
        writer.write("\r\n");
        writer.flush();
    }

    @Override
    public void run() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(dataInputStream));
            writer = new BufferedWriter(new OutputStreamWriter(dataOutputStream));

            while (true) {
                String data = reader.readLine().trim();

                if (data.equals("/addUser")) {
                    data = reader.readLine().trim();
                    String name = data;
                    data = reader.readLine().trim();
                    String path = data;

                    boolean nameRepeted = false;

                    for (String nombre : server.getNames()) {
                        if (name.equals(nombre)) {
                            nameRepeted = true;
                        }
                    }

                    if (!nameRepeted) {
                        server.addName(name);
                        server.transmision("/clear");
                        server.addUsuarios(name + "," + path);
                        server.transmision("/nuevoUsuario");

                        for (String usuario : server.getNames()) {
                            server.transmision(usuario);
                        }

                        server.transmision("/finUsuario");
                        data = reader.readLine().trim();
                    } else {
                        server.transmision(this, "/nameRepeted");
                    }

                } else if (data.equals("/deleteUser")) {
                    server.transmision("/deleteUser");
                    data = reader.readLine().trim();
                    server.transmision(data);
                    server.deleteUser(this, data);
                } else if (data.equals("/cameraOn")) {
                    server.transmision("/cameraOn");
                    data = reader.readLine().trim();
                    server.transmision(data);
                } else if (data.equals("/cameraOff")) {
                    server.transmision("/cameraOff");
                    data = reader.readLine().trim();
                    server.transmision(data);
                } else {
                    server.transmision(data);
                }

            }
        } catch (Exception e) {
        }
    }
}
