package soundUtils;

import Server.Server;
import Utils.Log;
import Utils.Message;
import Utils.Utils;
import java.util.ArrayList;

public class BroadcastThread extends Thread {

    private Server server;
    private ArrayList<Message> broadCastQueue = new ArrayList<Message>();
    private ArrayList<ClientConnection> clients = new ArrayList<ClientConnection>();

    public BroadcastThread(Server server) {
        this.server = server;
        this.broadCastQueue = server.getBroadCastQueue();
        this.clients = server.getClients();
    }

    @Override
    public void run() {
        while (true) {
            try {
                ArrayList<ClientConnection> toRemove = new ArrayList<ClientConnection>();
                for (ClientConnection cc : clients) {
                    if (!cc.isAlive()) {
                        Log.add("dead connection closed: " + cc.getInetAddress() + ":" + cc.getPort() + " on port ");
                        toRemove.add(cc);
                    }
                }
                clients.removeAll(toRemove);
                if (broadCastQueue.isEmpty()) {
                    Utils.sleep(10);
                    continue;
                } else {
                    Message m = broadCastQueue.get(0);
                    for (ClientConnection cc : clients) {
                        if (cc.getChId() != m.getChId()) {
                            cc.addToQueue(m);
                        }
                    }
                    broadCastQueue.remove(m);
                }
            } catch (Throwable t) {

            }
        }
    }

}
