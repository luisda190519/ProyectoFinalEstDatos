package Utils;

import java.io.Serializable;
import javax.sound.sampled.AudioFormat;

public class SoundPacket implements Serializable {

    public static AudioFormat defaultFormat = new AudioFormat(11025f, 8, 1, true, true);
    public static int defaultDataLenght = 1200;
    private byte[] data;

    public SoundPacket(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

}
