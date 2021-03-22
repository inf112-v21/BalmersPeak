package inf112.balmerspeak.app.network.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import inf112.balmerspeak.app.Player;
import org.javatuples.Pair;

public class PlayerSerializer extends Serializer<Player> {


    @Override
    public void write(Kryo kryo, Output output, Player player) {
        // Write coords
        output.writeInt(player.getCoords().getValue0());
        output.writeInt(player.getCoords().getValue0());
        // Write username and ip
        output.writeString(player.getUsername());
        output.writeString(player.getIpAddress());
    }

    @Override
    public Player read(Kryo kryo, Input input, Class<Player> aClass) {
        // Read coords
        Pair<Integer,Integer> coords = new Pair(input.readInt(), input.readInt());
        // Read username and IP and return player object
        return new Player(coords, input.readString(), input.readString());
    }
}
