package me.redstoner2019;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerDataPacket implements Serializable {
    private byte[] data;
    private String vc;
    private String senderUUID;
    private List<String> usersInChannel;

    public ServerDataPacket(byte[] data, String vc, String senderUUID, List<String> usersInChannel) {
        this.data = data;
        this.vc = vc;
        this.senderUUID = senderUUID;
        this.usersInChannel = usersInChannel;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getSenderUUID() {
        return senderUUID;
    }

    public void setSenderUUID(String senderUUID) {
        this.senderUUID = senderUUID;
    }

    public List<String> getUsersInChannel() {
        return usersInChannel;
    }

    public void setUsersInChannel(List<String> usersInChannel) {
        this.usersInChannel = usersInChannel;
    }
}
