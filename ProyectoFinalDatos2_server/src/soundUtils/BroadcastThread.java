package soundUtils;

import Server.Server;
import Utils.Log;
import Utils.Message;
import Utils.Utils;
import java.util.ArrayList;

public class BroadcastThread extends Thread {

    private Server s;

    public BroadcastThread(Server s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ArrayList<ClientConnection> toRemove = new ArrayList<ClientConnection>();
                for (ClientConnection cc : s.getClients()) {
                    if (!cc.isAlive()) {
                        Log.add("dead connection closed: " + cc.getInetAddress() + ":" + cc.getPort() + " on port " + s.port);
                        toRemove.add(cc);
                    }
                }
                s.getClients().removeAll(toRemove);
                if (s.getBroadCastQueue().isEmpty()) {
                    Utils.sleep(10);
                    continue;
                } else {
                    Message m = s.getBroadCastQueue().get(0);
                    for (ClientConnection cc : s.getClients()) {
                        if (cc.getChId() != m.getChId()) {
                            cc.addToQueue(m);
                        }
                    }
                    s.getBroadCastQueue().remove(m);
                }
            } catch (Throwable t) {
            }
        }
    }
}
