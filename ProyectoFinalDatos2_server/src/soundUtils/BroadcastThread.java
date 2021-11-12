package soundUtils;


import Server.Server;
import Utils.Log;
import Utils.Message;
import Utils.Utils;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luisda19
 */
public class BroadcastThread extends Thread {

    private Server s;

    public BroadcastThread(Server s) {
        this.s = s;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                ArrayList<ClientConnection> toRemove = new ArrayList<ClientConnection>(); //create a list of dead connections
                for (ClientConnection cc : s.getClients()) {
                    if (!cc.isAlive()) { //connection is dead, need to be removed
                        Log.add("dead connection closed: " + cc.getInetAddress() + ":" + cc.getPort() + " on port " + s.port);
                        toRemove.add(cc);
                    }
                }
                s.getClients().removeAll(toRemove); //delete all dead connections
                if (s.getBroadCastQueue().isEmpty()) { //nothing to send
                    Utils.sleep(10); //avoid busy wait
                    continue;
                } else { //we got something to broadcast
                    Message m = s.getBroadCastQueue().get(0);
                    for (ClientConnection cc : s.getClients()) { //broadcast the message
                        if (cc.getChId() != m.getChId()) {
                            cc.addToQueue(m);
                        }
                    }
                    s.getBroadCastQueue().remove(m); //remove it from the broadcast queue
                }
            } catch (Throwable t) {
                //mutex error, try again
            }
        }
    }
}
