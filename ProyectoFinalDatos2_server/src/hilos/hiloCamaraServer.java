package hilos;

import Server.Server;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class hiloCamaraServer extends Thread {

    private Socket socket;
    private Server server;

    public hiloCamaraServer(Socket socket, Server server) {
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
                    server.transmisionCamera(img);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(hiloCamaraServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
