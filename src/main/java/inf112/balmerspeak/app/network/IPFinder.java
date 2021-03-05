package inf112.balmerspeak.app.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IPFinder {

    public static String get() throws IOException {
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String
        System.out.println(ip);
        return ip;
    };
}
