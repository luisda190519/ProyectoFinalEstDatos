package hilos;

import Server.Server;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class CamaraThreadS extends Thread {

    private Socket socket;
    private Server server;

    public CamaraThreadS(Socket socket, Server server) {
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
                try {
                    BufferedImage img = ImageIO.read(socket.getInputStream());
                    if (img != null) {
                        server.transmisionCamera(img);
                        sleep(50);
                    }
                } catch (IIOException e) {
                    System.out.println("error " + e);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CamaraThreadS.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(CamaraThreadS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
