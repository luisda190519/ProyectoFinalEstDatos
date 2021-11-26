package soundUtils;

import Utils.Message;
import Utils.SoundPacket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class MicrofonoThread extends Thread {

    public static double amplification = 1.0;
    private ObjectOutputStream toServer;
    private TargetDataLine mic;

    public MicrofonoThread(ObjectOutputStream toServer) throws LineUnavailableException {
        this.toServer = toServer;
        //abrir el microfono
        AudioFormat af = SoundPacket.defaultFormat;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, null);
        mic = (TargetDataLine) (AudioSystem.getLine(info));
        mic.open(af);
        mic.start();
    }

    @Override
    public void run() {
        while (true) {
            if (mic.available() >= SoundPacket.defaultDataLenght) {
                byte[] buff = new byte[SoundPacket.defaultDataLenght];
                while (mic.available() >= SoundPacket.defaultDataLenght) {
                    mic.read(buff, 0, buff.length);
                }
                try {
                    long tot = 0;
                    for (int i = 0; i < buff.length; i++) {
                        buff[i] *= amplification;
                        tot += Math.abs(buff[i]);
                    }
                    tot *= 2.5;
                    tot /= buff.length;
                    //mandar los paquetes
                    Message m = null;
                    if (tot == 0) {
                        m = new Message(-1, -1, new SoundPacket(null));
                    } else {
                        //comprimir los paquetes de sonido con GZIP
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        GZIPOutputStream go = new GZIPOutputStream(baos);
                        go.write(buff);
                        go.flush();
                        go.close();
                        baos.flush();
                        baos.close();
                        m = new Message(-1, -1, new SoundPacket(baos.toByteArray()));
                    }
                    toServer.writeObject(m);
                } catch (IOException ex) {
                    stop();
                }
            } else {
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MicrofonoThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
