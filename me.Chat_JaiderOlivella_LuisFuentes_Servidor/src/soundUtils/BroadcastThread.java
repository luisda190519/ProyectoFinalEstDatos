package soundUtils;

import Server.Server;
import Utils.Message;
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
                ArrayList<ConexionAudioThreadS> toRemove = new ArrayList<ConexionAudioThreadS>();
                for (ConexionAudioThreadS cc : s.getClients()) {
                    if (!cc.isAlive()) {
                        toRemove.add(cc);
                    }
                }
                s.getClients().removeAll(toRemove);
                if (s.getBroadCastQueue().isEmpty()) {
                    sleep(10);
                    continue;
                } else {
                    Message m = s.getBroadCastQueue().get(0);
                    for (ConexionAudioThreadS cc : s.getClients()) {
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
