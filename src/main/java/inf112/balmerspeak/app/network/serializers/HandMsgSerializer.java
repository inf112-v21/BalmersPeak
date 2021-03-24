package inf112.balmerspeak.app.network.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import inf112.balmerspeak.app.network.messages.HandMsg;

public class HandMsgSerializer extends Serializer<HandMsg> {
    @Override
    public void write(Kryo kryo, Output output, HandMsg handReadyMsg) {
        output.writeBoolean(handReadyMsg.isReady());
    }

    @Override
    public HandMsg read(Kryo kryo, Input input, Class<HandMsg> aClass) {
        return new HandMsg(input.readBoolean());
    }
}
