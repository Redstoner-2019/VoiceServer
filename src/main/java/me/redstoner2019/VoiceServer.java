package me.redstoner2019;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VoiceServer {

    public static List<VoiceClientHandler> clientHandlers = new ArrayList<>();
    public static HashMap<String,List<String>> servers = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(55);
        while (true) {
            Socket s = ss.accept();
            System.out.println("Connection");
            VoiceClientHandler handler = new VoiceClientHandler(s);
            clientHandlers.add(handler);
        }
    }
}
