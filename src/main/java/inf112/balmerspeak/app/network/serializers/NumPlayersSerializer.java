package inf112.balmerspeak.app.network.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import inf112.balmerspeak.app.network.messages.NumPlayers;

public class NumPlayersSerializer extends Serializer<NumPlayers> {


    @Override
    public void write(Kryo kryo, Output output, NumPlayers numPlayers) {
        output.writeInt(numPlayers.getNumPlayers());
    }

    @Override
    public NumPlayers read(Kryo kryo, Input input, Class<NumPlayers> aClass) {
        return new NumPlayers(input.readInt());
    }
}
