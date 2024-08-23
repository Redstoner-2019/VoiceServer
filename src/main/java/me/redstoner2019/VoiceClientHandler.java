package me.redstoner2019;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VoiceClientHandler {
    public ObjectOutputStream output;
    private ObjectInputStream in;
    public String voiceChatID = "";

    public VoiceClientHandler(java.net.Socket socket) throws Exception {
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        new Thread(() -> {
            while (true) {
                try{
                    DataPacket d = (DataPacket) in.readObject();
                    voiceChatID = d.getVc();
                    //System.out.println("Data length " + d.getData().length);
                    for(VoiceClientHandler v : VoiceServer.clientHandlers){
                        //if(v == this) continue;
                        if(v.voiceChatID.equals(voiceChatID)){
                        //System.out.println("relaying");
                        List<String> users = VoiceServer.servers.getOrDefault(d.getVc(),new ArrayList<>());
                        if(!users.contains(d.getSenderUUID())){
                            users.add(d.getSenderUUID());
                        }
                        VoiceServer.servers.put(d.getVc(),users);

                        ServerDataPacket dataPacket = new ServerDataPacket(d.getData(),d.getVc(),d.getSenderUUID(),users);
                        v.output.writeObject(dataPacket);
                        v.output.flush();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    try {
                        output.close();
                        in.close();
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    VoiceServer.clientHandlers.remove(this);
                    System.out.println("Removing");
                    break;
                }
            }
        }).start();


    }
}
