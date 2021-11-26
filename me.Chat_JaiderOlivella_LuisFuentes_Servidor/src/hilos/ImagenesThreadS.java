package hilos;

import Server.Server;
import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImagenesThreadS extends Thread {

    private Socket socket;
    private Server server;

    public ImagenesThreadS(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
    }

    public void enviarMensaje(BufferedImage image) throws IOException {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        ImageIO.write(image, "png", ous);
        socket.getOutputStream().write(ous.toByteArray());
    }

    @Override
    public void run() {
        try {
            while (true) {
                BufferedImage img = ImageIO.read(socket.getInputStream());
                if (img != null) {
                    server.addImages(img);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
