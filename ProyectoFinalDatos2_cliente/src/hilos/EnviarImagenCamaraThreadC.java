package hilos;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class EnviarImagenCamaraThreadC extends Thread {

    private Socket socket;
    private boolean cameraOn = false;

    public EnviarImagenCamaraThreadC(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedImage image;
            Webcam.setAutoOpenMode(true);

            synchronized (this) {
                this.wait();
                int i = 0;
                while (true) {
                    while (cameraOn) {
                        Webcam cam = Webcam.getDefault();
                        image = cam.getImage();
                        byte[] bytes = WebcamUtils.getImageBytes(cam, "png");
                        socket.getOutputStream().write(bytes);
                    }
                    this.wait();
                }
            }

        } catch (IOException e) {
            System.out.println("error " + e);
        } catch (InterruptedException ex) {
            Logger.getLogger(EnviarImagenCamaraThreadC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCameraOn(boolean cameraOn) {
        this.cameraOn = cameraOn;
    }

}
