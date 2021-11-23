package soundUtils;

import Utils.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class VozThread extends Thread {

    private Socket s;
    private ArrayList<CanalAudioThread> chs = new ArrayList<CanalAudioThread>();
    private MicrofonoThread st;

    public VozThread(String serverIp, int serverPort) throws UnknownHostException, IOException {
        s = new Socket(serverIp, serverPort);
    }

    public void closeSocket() throws IOException {
        s.close();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream fromServer = new ObjectInputStream(s.getInputStream());
            ObjectOutputStream toServer = new ObjectOutputStream(s.getOutputStream());
            try {
                sleep(100);
                st = new MicrofonoThread(toServer);
                st.start();
            } catch (Exception e) {
                System.out.println("mic unavailable " + e);
            }
            //envia los datos del server al canal de audio especifico
            while (true) {

                if (s.getInputStream().available() > 0) {
                    Message in = (Message) (fromServer.readObject());
                    CanalAudioThread sendTo = null;
                    for (CanalAudioThread ch : chs) {
                        if (ch.getChId() == in.getChId()) {
                            sendTo = ch;
                        }
                    }
                    if (sendTo != null) {
                        sendTo.addToQueue(in);
                    } else {
                        CanalAudioThread ch = new CanalAudioThread(in.getChId());
                        ch.addToQueue(in);
                        ch.start();
                        chs.add(ch);
                    }
                } else {
                    ArrayList<CanalAudioThread> killMe = new ArrayList<CanalAudioThread>();
                    for (CanalAudioThread c : chs) {
                        if (c.canKill()) {
                            killMe.add(c);
                        }
                    }
                    for (CanalAudioThread c : killMe) {
                        c.closeAndKill();
                        chs.remove(c);
                    }
                    sleep(1);
                }
            }
        } catch (Exception e) {
            System.out.println("client err " + e.toString());
        }
    }
}
