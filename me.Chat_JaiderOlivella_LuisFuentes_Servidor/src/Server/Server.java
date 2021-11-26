package Server;

import hilos.ImagenesThreadS;
import hilos.CamaraThreadS;
import hilos.ChatThreadS;
import soundUtils.ConexionAudioThreadS;
import Utils.Message;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.teleal.cling.UpnpService;
import org.teleal.cling.UpnpServiceImpl;
import org.teleal.cling.support.igd.PortMappingListener;
import org.teleal.cling.support.model.PortMapping;
import soundUtils.BroadcastThread;

public class Server {

    private Socket socket;
    private static Vector client = new Vector();
    private ArrayList<Message> broadCastQueue = new ArrayList<Message>();
    private ArrayList<ConexionAudioThreadS> clients = new ArrayList<ConexionAudioThreadS>();
    public int port;
    private ArrayList<String> usuarios = new ArrayList<String>();
    private ServerSocket s;
    private static ArrayList<ChatThreadS> hilosChat = new ArrayList<ChatThreadS>();
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private static ArrayList<ImagenesThreadS> hiloImagen = new ArrayList<ImagenesThreadS>();
    private ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<CamaraThreadS> hiloCamaras = new ArrayList<CamaraThreadS>();

    public Server(int port) throws IOException {
        this.port = port;
        ServerSocket s = new ServerSocket(this.port);
        ServerSocket s2 = new ServerSocket(this.port + 1);
        ServerSocket s3 = new ServerSocket(this.port + 2);
        ServerSocket s4 = new ServerSocket(this.port + 3);

        BroadcastThread bt = new BroadcastThread(this);
        bt.start();

        while (true) {
            Socket socket = s.accept();
            this.socket = socket;
            ChatThreadS hiloChat = new ChatThreadS(socket, this);
            hiloChat.start();
            hilosChat.add(hiloChat);

            Socket socket2 = s2.accept();
            ImagenesThreadS hi = new ImagenesThreadS(socket2, this);
            hi.start();
            hiloImagen.add(hi);

            Socket socket3 = s3.accept();
            ConexionAudioThreadS cc = new ConexionAudioThreadS(this, socket3);
            cc.start();
            this.addToClients(cc);

            Socket socket4 = s4.accept();
            CamaraThreadS hcs = new CamaraThreadS(socket4, this);
            hcs.start();
            hiloCamaras.add(hcs);

        }
    }

    public void transmision(String mensaje) throws IOException {
        for (ChatThreadS hc : hilosChat) {
            hc.enviarMensaje(mensaje);
        }
    }

    public void transmision(ChatThreadS hc, String mensaje) throws IOException {
        hc.enviarMensaje(mensaje);
    }

    public void transmision() throws IOException {
        for (ImagenesThreadS hiImagen : hiloImagen) {
            for (BufferedImage bi : images) {
                hiImagen.enviarMensaje(bi);
            }
        }
    }

    public void transmisionCamera(BufferedImage image) throws IOException {
        for (CamaraThreadS hcs : hiloCamaras) {
            hcs.enviarMensaje(image);
        }
    }

    public void deleteUser(ChatThreadS hc, String name) {
        int index = hilosChat.indexOf(hc);
        hilosChat.remove(index);
        hiloImagen.remove(index);
        hiloCamaras.remove(index);
        index = names.indexOf(name);
        names.remove(index);
        images.remove(index);
    }

    public void addToBroadcastQueue(Message m) {
        try {
            broadCastQueue.add(m);
        } catch (Throwable t) {
            addToBroadcastQueue(m);
        }
    }

    private void addToClients(ConexionAudioThreadS cc) {
        try {
            clients.add(cc);
        } catch (Throwable t) {
            addToClients(cc);
        }
    }

    public void addUsuarios(String usuario) {
        this.usuarios.add(usuario);
    }

    public void addImages(BufferedImage image) throws IOException {
        images.add(image);
        this.transmision();
    }

    public void addName(String name) {
        names.add(name);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    public ArrayList<Message> getBroadCastQueue() {
        return broadCastQueue;
    }

    public ArrayList<ConexionAudioThreadS> getClients() {
        return clients;
    }

    public int getNumeroCliente() {
        return client.size();
    }

}
