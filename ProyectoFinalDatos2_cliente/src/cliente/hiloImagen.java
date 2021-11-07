package cliente;

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

public class hiloImagen extends Thread {

    private Socket socket;
    private cliente cliente;
    private hiloChatCliente hc;

    public hiloImagen(Socket socket, cliente cliente, hiloChatCliente hc) throws IOException {
        this.socket = socket;
        this.cliente = cliente;
        this.hc = hc;
    }

    @Override
    public void run() {
        try {
            int i = 0, tamaño = 0;
            while (true) {
                if (i < tamaño + 1) {
                    BufferedImage img = ImageIO.read(socket.getInputStream());
                    if (img != null) {
                        cliente.addClientImage(img);
                        System.out.println("entre");
                        i++;
                    }
                } else {
                    tamaño = cliente.getImages().size();
                    i = 0;
                    synchronized (this) {
                        wait();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
