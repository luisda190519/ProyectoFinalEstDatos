package Utils;

import java.io.Serializable;

public class Message implements Serializable {

    private long chId;
    private long timestamp,
            ttl = 2000;
    private Object data;

    public Message(long chId, long timestamp, Object data) {
        this.chId = chId;
        this.timestamp = timestamp;
        this.data = data;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getChId() {
        return chId;
    }

    public Object getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public void setChId(long chId) {
        this.chId = chId;
    }

}
