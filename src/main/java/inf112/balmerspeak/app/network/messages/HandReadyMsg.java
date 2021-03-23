package inf112.balmerspeak.app.network.messages;

public class HandReadyMsg {

    private boolean ready;

    public HandReadyMsg(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return this.ready;
    }
}
