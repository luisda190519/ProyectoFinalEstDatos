package Server;

import hilos.hiloImagenServer;
import hilos.hiloCamaraServer;
import hilos.hiloChatServer;
import soundUtils.ClientConnection;
import Utils.Log;
import Utils.Message;
import Utils.Utils;
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
    private ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();
    public int port;
    private ArrayList<String> usuarios = new ArrayList<String>();
    private ServerSocket s;
    private static ArrayList<hiloChatServer> hilosChat = new ArrayList<hiloChatServer>();
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private static ArrayList<hiloImagenServer> hiloImagen = new ArrayList<hiloImagenServer>();
    private ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<hiloCamaraServer> hiloCamaras = new ArrayList<hiloCamaraServer>();

    public Server(int port) throws IOException {
        this.port = port;
        ServerSocket s = new ServerSocket(this.port);
        ServerSocket s2 = new ServerSocket(this.port + 1);
        ServerSocket s3 = new ServerSocket(this.port + 2);
        ServerSocket s4 = new ServerSocket(this.port + 3);

        Log.add("Port " + this.port + 2 + ": server started");
        BroadcastThread bt = new BroadcastThread(this);
        bt.start();

        while (true) {
            Socket socket = s.accept();
            this.socket = socket;
            hiloChatServer hiloChat = new hiloChatServer(socket, this);
            hiloChat.start();
            hilosChat.add(hiloChat);

            Socket socket2 = s2.accept();
            hiloImagenServer hi = new hiloImagenServer(socket2);
            hi.start();
            hiloImagen.add(hi);

            Socket socket3 = s3.accept();
            ClientConnection cc = new ClientConnection(this, socket3);
            cc.start();
            this.addToClients(cc);
            Log.add("new client " + socket3.getInetAddress() + ":" + socket3.getPort() + " on port " + this.port + 2);

            Socket socket4 = s4.accept();
            hiloCamaraServer hcs = new hiloCamaraServer(socket4, this);
            hcs.start();
            hiloCamaras.add(hcs);

        }
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    public ArrayList<Message> getBroadCastQueue() {
        return broadCastQueue;
    }

    public ArrayList<ClientConnection> getClients() {
        return clients;
    }

    public void flush() throws IOException {
        for (hiloChatServer hc : hilosChat) {
            hc.flush();
        }
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public void addUsuarios(String usuario) {
        this.usuarios.add(usuario);
    }

    public void addImages(BufferedImage image) throws IOException {
        images.add(image);
        this.transmision();
    }

    public void transmision(String mensaje) throws IOException {
        for (hiloChatServer hc : hilosChat) {
            hc.enviarMensaje(mensaje);
        }
    }

    public void transmision(hiloChatServer hc, String mensaje) throws IOException {
        hc.enviarMensaje(mensaje);
    }

    public void transmision() throws IOException {
        for (hiloImagenServer hiImagen : hiloImagen) {
            for (BufferedImage bi : images) {
                hiImagen.enviarMensaje(bi);
            }
        }
    }

    public void transmisionCamera(BufferedImage image) throws IOException {
        for (hiloCamaraServer hcs : hiloCamaras) {
            hcs.enviarMensaje(image);
        }
    }

    public int getNumeroCliente() {
        return client.size();
    }

    public void deleteUser(hiloChatServer hc, String name) {
        int index = hilosChat.indexOf(hc);
        hilosChat.remove(index);
        hiloImagen.remove(index);
        hiloCamaras.remove(index);
        index = names.indexOf(name);
        names.remove(index);
        images.remove(index);
    }

    public void addToBroadcastQueue(Message m) { //add a message to the broadcast queue. this method is used by all ClientConnection instances
        try {
            broadCastQueue.add(m);
        } catch (Throwable t) {
            //mutex error, try again
            Utils.sleep(1);
            addToBroadcastQueue(m);
        }
    }

    private void addToClients(ClientConnection cc) {
        try {
            clients.add(cc); //add the new connection to the list of connections
        } catch (Throwable t) {
            //mutex error, try again
            Utils.sleep(1);
            addToClients(cc);
        }
    }

    public void addName(String name) {
        names.add(name);
    }

    public ArrayList<String> getNames() {
        return names;
    }

}
