package soundUtils;

import Server.Server;
import Utils.Log;
import Utils.Message;
import Utils.SoundPacket;
import Utils.Utils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection extends Thread {

    private Server serv;
    private Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private long chId;
    private ArrayList<Message> toSend = new ArrayList<Message>();

    public InetAddress getInetAddress() {
        return s.getInetAddress();
    }

    public int getPort() {
        return s.getPort();
    }

    public long getChId() {
        return chId;
    }

    public ClientConnection(Server serv, Socket s) {
        this.serv = serv;
        this.s = s;
        byte[] addr = s.getInetAddress().getAddress();
        chId = (addr[0] << 48 | addr[1] << 32 | addr[2] << 24 | addr[3] << 16) + s.getPort();
    }

    public void addToQueue(Message m) {
        try {
            toSend.add(m);
        } catch (Throwable t) {
        }
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            try {
                s.close();
                Log.add("ERROR " + getInetAddress() + ":" + getPort() + " " + ex);
            } catch (IOException ex1) {
            }
            stop();
        }
        while (true) {
            try {
                if (s.getInputStream().available() > 0) {
                    Message toBroadcast = (Message) in.readObject();
                    if (toBroadcast.getChId() == -1) {
                        toBroadcast.setChId(chId);
                        toBroadcast.setTimestamp(System.nanoTime() / 1000000L);
                        serv.addToBroadcastQueue(toBroadcast);
                    } else {
                        continue;
                    }
                }
                try {
                    if (!toSend.isEmpty()) {
                        Message toClient = toSend.get(0);
                        if (!(toClient.getData() instanceof SoundPacket) || toClient.getTimestamp() + toClient.getTtl() < System.nanoTime() / 1000000L) {
                            Log.add("dropping packet from " + toClient.getChId() + " to " + chId);
                            continue;
                        }
                        out.writeObject(toClient);
                        toSend.remove(toClient);
                    } else {
                        Utils.sleep(10);
                    }
                } catch (Throwable t) {
                    if (t instanceof IOException) {
                        throw (Exception) t;
                    } else {
                        System.out.println("cc fixmutex");
                        continue;
                    }
                }
            } catch (Exception ex) {
                try {
                    s.close();
                } catch (IOException ex1) {
                }
                stop();
            }
        }

    }
}
