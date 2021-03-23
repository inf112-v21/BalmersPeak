package inf112.balmerspeak.app.network.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import inf112.balmerspeak.app.network.messages.HandReadyMsg;

public class HandReadyMsgSerializer extends Serializer<HandReadyMsg> {
    @Override
    public void write(Kryo kryo, Output output, HandReadyMsg handReadyMsg) {
        output.writeBoolean(handReadyMsg.isReady());
    }

    @Override
    public HandReadyMsg read(Kryo kryo, Input input, Class<HandReadyMsg> aClass) {
        return new HandReadyMsg(input.readBoolean());
    }
}
