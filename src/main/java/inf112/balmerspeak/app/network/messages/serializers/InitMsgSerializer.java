package inf112.balmerspeak.app.network.messages.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import inf112.balmerspeak.app.network.messages.InitMsg;

public class InitMsgSerializer extends Serializer<InitMsg> {


    @Override
    public void write(Kryo kryo, Output output, InitMsg initMsg) {
        output.writeString(initMsg.getIP());
        output.writeString(initMsg.getUsername());

    }

    @Override
    public InitMsg read(Kryo kryo, Input input, Class<InitMsg> aClass) {
        return new InitMsg(input.readString(), input.readString());
    }
}
