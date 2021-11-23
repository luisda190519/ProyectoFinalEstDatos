package hilos;

import cliente.cliente;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImagenesThreadC extends Thread {

    private Socket socket;
    private cliente cliente;
    private ChatThreadC hc;
    private int tamaño = 0;

    public ImagenesThreadC(Socket socket, cliente cliente) throws IOException {
        this.socket = socket;
        this.cliente = cliente;
        this.hc = hc;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    @Override
    public void run() {
        try {
            sleep(100);
            while (true) {
                BufferedImage img = ImageIO.read(socket.getInputStream());
                if (img != null) {
                    cliente.addClientImage(img);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
