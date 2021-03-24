package inf112.balmerspeak.app.network.messages;

import java.io.Serializable;

public class InitMsg implements Serializable {
    // Contains IP and username (maybe later, the bitmap of avatar)
    private final String IP;
    private final String username;

    public InitMsg(String IP, String username) {
        this.IP = IP;
        this.username = username;
    }

    public String getIP() {
        return this.IP;
    }

    public String getUsername() {
        return this.username;
    }
}
