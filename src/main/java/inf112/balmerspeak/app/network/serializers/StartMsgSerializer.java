package inf112.balmerspeak.app.network.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import inf112.balmerspeak.app.network.messages.StartMsg;
import org.javatuples.Pair;

public class StartMsgSerializer extends Serializer<StartMsg> {

    @Override
    public void write(Kryo kryo, Output output, StartMsg startMsg) {
        output.writeInt(startMsg.getX());
        output.writeInt(startMsg.getY());
    }

    @Override
    public StartMsg read(Kryo kryo, Input input, Class<StartMsg> aClass) {
        return new StartMsg(new Pair(input.readInt(), input.readInt()));
    }
}
