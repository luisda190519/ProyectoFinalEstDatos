package soundUtils;

import Utils.Message;
import Utils.Utils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ClientVoz extends Thread {

    private Socket s;
    private ArrayList<AudioChannel> chs = new ArrayList<AudioChannel>();
    private MicThread st;

    public ClientVoz(String serverIp, int serverPort) throws UnknownHostException, IOException {
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
                Utils.sleep(100);
                st = new MicThread(toServer);
                st.start();
            } catch (Exception e) {
                System.out.println("mic unavailable " + e);
            }
            //envia los datos del server al canal de audio especifico
            while (true) {

                if (s.getInputStream().available() > 0) {
                    Message in = (Message) (fromServer.readObject());
                    AudioChannel sendTo = null;
                    for (AudioChannel ch : chs) {
                        if (ch.getChId() == in.getChId()) {
                            sendTo = ch;
                        }
                    }
                    if (sendTo != null) {
                        sendTo.addToQueue(in);
                    } else {
                        AudioChannel ch = new AudioChannel(in.getChId());
                        ch.addToQueue(in);
                        ch.start();
                        chs.add(ch);
                    }
                } else {
                    ArrayList<AudioChannel> killMe = new ArrayList<AudioChannel>();
                    for (AudioChannel c : chs) {
                        if (c.canKill()) {
                            killMe.add(c);
                        }
                    }
                    for (AudioChannel c : killMe) {
                        c.closeAndKill();
                        chs.remove(c);
                    }
                    Utils.sleep(1);
                }
            }
        } catch (Exception e) {
            System.out.println("client err " + e.toString());
        }
    }
}
