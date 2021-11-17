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

public class hiloCamaraCliente extends Thread {

    private int port;
    private String ip;
    private Socket socket;
    private cliente cliente;
    private String nameOwner = "";
    private String nameAux;
    private boolean cameraOn = false;

    public hiloCamaraCliente(int port, String ip, cliente cliente) throws IOException {
        this.port = port;
        this.ip = ip;
        this.cliente = cliente;
        socket = new Socket(ip, port + 3);
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public void sendImage() throws InterruptedException {
        try {
            if (nameAux.equals(nameOwner)) {
                BufferedImage image;
                Webcam.setAutoOpenMode(true);
                Webcam cam = Webcam.getDefault();
                image = cam.getImage();
                ByteArrayOutputStream ous = new ByteArrayOutputStream();
                ImageIO.write(image, "png", ous);
                socket.getOutputStream().write(ous.toByteArray());
                sleep(100);
            }

        } catch (IOException e) {
            System.out.println("error " + e);
        }

    }

    @Override
    public void run() {
        BufferedImage image;
        Webcam cam = Webcam.getDefault();
        int i = 0;

        synchronized (this) {
            try {
                this.wait();
                while (true) {
                    try {
                        if (cameraOn && nameAux.equals(nameOwner)) {
                            sendImage();
                        }

                        if (cameraOn) {
                            BufferedImage img = ImageIO.read(socket.getInputStream());
                            if (img != null) {
                                cliente.cameraOn(img, cliente.getIndexCamera(nameAux));
                            }
                        } else if (!cameraOn) {
                            sleep(100);
                            cliente.stopCamera(cliente.getIndexCamera(nameAux));
                            cam.close();
                            this.wait();
                        }

                        System.out.println("hola " + i);
                        i++;

                    } catch (IOException ex) {
                        Logger.getLogger(hiloCamaraCliente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(hiloCamaraCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(hiloCamaraCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setCameraOn(boolean cameraOn) {
        this.cameraOn = cameraOn;
    }

    public void setNameCameraStart(String name) {
        this.nameAux = name;
    }

}
