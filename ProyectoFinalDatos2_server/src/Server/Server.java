package Server;

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
    private int port;
    private ArrayList<String> usuarios = new ArrayList<String>();
    private ServerSocket s;
    private static ArrayList<hiloChat> hilosChat = new ArrayList<hiloChat>();
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    private static ArrayList<hiloImagen> hiloImagen = new ArrayList<hiloImagen>();
    private ArrayList<String> names = new ArrayList<String>();

    public Server() {
    }

    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(2003);
        ServerSocket s2 = new ServerSocket(2004);
        Server server = new Server();

        while (true) {
            Socket socket = s.accept();
            server.socket = socket;
            hiloChat hiloChat = new hiloChat(socket, server);
            hiloChat.start();
            hilosChat.add(hiloChat);

            Socket socket2 = s2.accept();
            hiloImagen hi = new hiloImagen(socket2);
            hi.start();
            hiloImagen.add(hi);

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
        for (hiloChat hc : hilosChat) {
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
        this.transmision(image);
    }

    public void transmision(String mensaje) throws IOException {
        for (hiloChat hc : hilosChat) {
            hc.enviarMensaje(mensaje);
        }
    }

    public void transmision(BufferedImage image) throws IOException {
        for (hiloImagen hiImagen : hiloImagen) {
            for (BufferedImage bi : images) {
                hiImagen.enviarMensaje(bi);
            }
        }
    }

    public int getNumeroCliente() {
        return client.size();
    }

    public void deleteUser(hiloChat hc) {
        hilosChat.remove(hc);
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
