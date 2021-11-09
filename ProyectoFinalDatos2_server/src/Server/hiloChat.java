package Server;

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

public class hiloChat extends Thread {

    private Socket socket;
    private Server server;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;
    private BufferedReader reader;
    private BufferedWriter writer;

    public hiloChat(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void enviarMensaje(String mensaje) throws IOException {
        writer.write(mensaje);
    }

    public void flush() throws IOException {
        writer.flush();
    }

    public void enviarMensaje(BufferedImage image) throws IOException {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        ImageIO.write(image, "png", ous);
        socket.getOutputStream().write(ous.toByteArray());
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
                System.out.println("Recivido " + data);

                if (data.equals("/addUser")) {
                    data = reader.readLine().trim();
                    String name = data;
                    data = reader.readLine().trim();
                    String path = data;

                    server.addName(name);
                    ImageIcon icon = new ImageIcon(path);
                    BufferedImage bi = ImageIO.read(new File(path));
                    
                    server.addImages(bi);
                    server.addUsuarios(name + "," + path);

                    server.transmision("/nuevoUsuario");
                    server.transmision("\r\n");
                    server.flush();

                    for (String usuario : server.getNames()) {
                        server.transmision(usuario);
                        server.transmision("\r\n");
                        server.flush();
                    }

                    server.transmision("/finUsuario");
                    server.transmision("\r\n");
                    server.flush();
                    data = reader.readLine().trim();

                } else if (data.equals("/deleteUser")) {
                    server.deleteUser(this);
                } else {
                    server.transmision(data);
                    server.transmision("\r\n");
                    server.flush();
                }

            }
        } catch (Exception e) {
        }

    }

}
