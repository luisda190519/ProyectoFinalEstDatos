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
import com.github.sarxos.webcam.WebcamStreamer;

public class CamaraThreadC extends Thread {

    private int port;
    private String ip;
    private Socket socket;
    private cliente cliente;
    private String nameOwner = "";
    private String nameAux;
    private boolean cameraOn = false;
    private EnviarImagenCamaraThreadC sendCamera;

    public CamaraThreadC(int port, String ip, cliente cliente) throws IOException {
        this.port = port;
        this.ip = ip;
        this.cliente = cliente;
        socket = new Socket(ip, port + 3);
        sendCamera = new EnviarImagenCamaraThreadC(socket);
        sendCamera.start();
    }

    @Override
    public void run() {
        int i = 0;
        boolean aux = true;
        synchronized (this) {
            try {
                this.wait();
                while (true) {
                    try {
                        if (cameraOn && nameAux.equals(nameOwner) && aux) {
                            sendCamera.setCameraOn(true);
                            aux = false;
                            synchronized (sendCamera) {
                                sendCamera.notify();
                            }
                        }

                        if (cameraOn) {
                            BufferedImage img = ImageIO.read(socket.getInputStream());
                            if (img != null) {
                                cliente.cameraOn(img, cliente.getIndexCamera(nameAux));
                            }
                        } else if (!cameraOn) {
                            sendCamera.setCameraOn(false);
                            cliente.stopCamera(cliente.getIndexCamera(nameAux));
                            aux = true;
                            this.wait();
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(CamaraThreadC.class.getName()).log(Level.SEVERE, null, ex);
                        sendCamera.setCameraOn(false);
                        cliente.stopCamera(cliente.getIndexCamera(nameAux));
                        aux = true;
                        this.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CamaraThreadC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(CamaraThreadC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public String getNameAux() {
        return nameAux;
    }

    public void setCameraOn(boolean cameraOn) {
        this.cameraOn = cameraOn;
    }

    public void setNameCameraStart(String name) {
        this.nameAux = name;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

}
