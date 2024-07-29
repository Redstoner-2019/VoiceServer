package me.redstoner2019;

import javax.sound.sampled.*;
import java.io.*;
import java.net.*;

public class VoiceClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 22;
    private static String voiceChat = "uid";

    public static void main(String[] args) throws IOException, LineUnavailableException {
        Socket socket = new Socket(SERVER_ADDRESS,SERVER_PORT);

        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

        AudioFormat format = new AudioFormat(44100, 16, 1, true, true);
        TargetDataLine microphone = AudioSystem.getTargetDataLine(format);
        microphone.open(format);
        microphone.start();

        SourceDataLine speakers = AudioSystem.getSourceDataLine(format);
        speakers.open(format);
        speakers.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        DataPacket data = (DataPacket) input.readObject();
                        speakers.write(data.getData(), 0,data.getData().length);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t.start();

        while (true) {
            byte[] buffer = new byte[1024];
            microphone.read(buffer,0,buffer.length);
            DataPacket data = new DataPacket(buffer,voiceChat);
            output.writeObject(data);
            output.flush();
        }
    }
}

