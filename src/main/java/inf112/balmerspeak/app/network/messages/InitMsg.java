package inf112.balmerspeak.app.network.messages;

public class InitMsg {
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
