package Server;

import soundUtils.ClientConnection;
import Utils.Log;
import Utils.Message;
import Utils.Utils;
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
    private ArrayList<String> usuarios;
    private ServerSocket s;
    private static ArrayList<hiloChat> hilosChat = new ArrayList<hiloChat>();

    public Server(Socket socket) {
        try {
            this.socket = socket;
            usuarios = new ArrayList<String>();
        } catch (Exception e) {
            System.out.println("error " + e);
            e.printStackTrace();
        }
    }

    public Server(int port) throws Exception {
        this.port = port;

        try {
            s = new ServerSocket(port);
            Log.add("Port " + port + ": server started");
        } catch (IOException ex) {
            Log.add("Server error " + ex + "(port " + port + ")");
            throw new Exception("Error " + ex);
        }

        BroadcastThread bt = new BroadcastThread(this);
        bt.start();
        
        while (true) {
            try {
                Socket c = s.accept();
                ClientConnection cc = new ClientConnection(this, c); //create a ClientConnection thread
                cc.start();
                addToClients(cc);
                Log.add("new client " + c.getInetAddress() + ":" + c.getPort() + " on port " + port);
            } catch (IOException ex) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket s = new ServerSocket(2003);

        while (true) {
            Socket socket = s.accept();
            Server server = new Server(socket);
            hiloChat hiloChat = new hiloChat(socket, server);
            hiloChat.start();
            hilosChat.add(hiloChat);
            System.out.println(hilosChat.size());

        }
    }

    //El buffered writer hay que ponerle el flush al acabar
    public void flush() throws IOException {
        for (hiloChat hc : hilosChat) {
            hc.flush();
        }
    }

    public void removeUser(hiloChat hc) {
        hilosChat.remove(hc);
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public void addUsuarios(String usuario) {
        this.usuarios.add(usuario);
    }

    //Transmite el mensaje a todos los hilos de chat
    public void transmision(String mensaje) throws IOException {
        for (hiloChat hc : hilosChat) {
            hc.enviarMensaje(mensaje);
        }
    }

    public ArrayList<Message> getBroadCastQueue() {
        return broadCastQueue;
    }

    public ArrayList<ClientConnection> getClients() {
        return clients;
    }

    public int getNumeroCliente() {
        return client.size();
    }

    //add a message to the broadcast queue. this method is used by all ClientConnection instances
    public void addToBroadcastQueue(Message m) {
        try {
            broadCastQueue.add(m);
        } catch (Throwable t) {
            Utils.sleep(1);
            addToBroadcastQueue(m);
        }
    }

    private void addToClients(ClientConnection cc) {
        try {
            clients.add(cc);
        } catch (Throwable t) {
            Utils.sleep(1);
            addToClients(cc);
        }
    }
}
