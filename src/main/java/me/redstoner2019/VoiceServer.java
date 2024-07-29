package me.redstoner2019;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VoiceServer {

    public static List<VoiceClientHandler> clientHandlers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(22);
        while (true) {
            Socket s = ss.accept();
            VoiceClientHandler handler = new VoiceClientHandler(s);
            clientHandlers.add(handler);
        }
    }
}
