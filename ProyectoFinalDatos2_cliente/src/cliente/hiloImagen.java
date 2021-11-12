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
    private int tamaño = 0;

    public hiloImagen(Socket socket, cliente cliente) throws IOException {
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
            int i = 0;
            while (true) {
                BufferedImage img = ImageIO.read(socket.getInputStream());
                if (img != null) {
                    cliente.addClientImage(img);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
