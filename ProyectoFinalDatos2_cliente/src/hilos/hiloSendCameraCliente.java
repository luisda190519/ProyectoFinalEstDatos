package hilos;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class hiloSendCameraCliente extends Thread {

    private Socket socket;
    private boolean cameraOn = false;

    public hiloSendCameraCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedImage image;
            Webcam.setAutoOpenMode(true);

            synchronized (this) {
                this.wait();
                while (true) {
                    while (cameraOn) {
                        Webcam cam = Webcam.getDefault();
                        image = cam.getImage();
                        ByteArrayOutputStream ous = new ByteArrayOutputStream();
                        ImageIO.write(image, "png", ous);
                        socket.getOutputStream().write(ous.toByteArray());
                    }
                    
                    this.wait();
                }
            }

        } catch (IOException e) {
            System.out.println("error " + e);
        } catch (InterruptedException ex) {
            Logger.getLogger(hiloSendCameraCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCameraOn(boolean cameraOn) {
        this.cameraOn = cameraOn;
    }

}
