package hilos;

import cliente.cliente;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class hiloCamaraCliente extends Thread {

    private int port;
    private String ip;
    private Socket socket;
    private cliente cliente;
    private String name;
    private boolean cameraOn = false;

    public hiloCamaraCliente(int port, String ip, cliente cliente) throws IOException {
        this.port = port;
        this.ip = ip;
        this.cliente = cliente;
        socket = new Socket(ip, port + 3);
    }

    public void sendImage() {
        try {
            BufferedImage image;
            Webcam.setAutoOpenMode(true);
            Webcam cam = Webcam.getDefault();
            image = cam.getImage();
            ByteArrayOutputStream ous = new ByteArrayOutputStream();
            ImageIO.write(image, "png", ous);
            socket.getOutputStream().write(ous.toByteArray());
        } catch (IOException e) {
            System.out.println("error " + e);
        }

    }

    @Override
    public void run() {
        BufferedImage image;
        Webcam cam = Webcam.getDefault();

        while (true) {
            try {
                if (cameraOn) {
                    sendImage();
                }
                BufferedImage img = ImageIO.read(socket.getInputStream());
                if (img != null) {
                    cliente.cameraOn(img, cliente.getIndexCamera(name));
                }
            } catch (IOException ex) {
                //Logger.getLogger(hiloCamaraCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setTheName(String name) {
        this.name = name;
    }

    public void setCameraOn(boolean cameraOn) {
        this.cameraOn = cameraOn;
    }

}
