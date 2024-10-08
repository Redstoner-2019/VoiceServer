package me.redstoner2019;

import java.io.Serializable;

public class DataPacket implements Serializable {
    private byte[] data;
    private String vc;
    private String senderUUID;

    public DataPacket(byte[] data, String vc, String senderUUID) {
        this.data = data;
        this.vc = vc;
        this.senderUUID = senderUUID;
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
}
