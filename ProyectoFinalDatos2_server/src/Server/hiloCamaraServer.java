package Server;

import java.awt.image.BufferedImage;
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

    @Override
    public void run() {

        try {
            BufferedImage img = ImageIO.read(socket.getInputStream());
            server.transmisionCamera(img);

        } catch (IOException ex) {
            Logger.getLogger(hiloCamaraServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
