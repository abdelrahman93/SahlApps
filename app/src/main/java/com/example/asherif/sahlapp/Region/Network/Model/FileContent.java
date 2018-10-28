package com.example.asherif.sahlapp.Region.Network.Model;

import com.google.gson.annotations.SerializedName;

public class FileContent {
    @SerializedName("Serial")
 private int serial;
    @SerializedName("FileID")
    private int fieldID;
    @SerializedName("ItemID")
    private int itemID;
    @SerializedName("Qty")
    private int quantity;


    @SerializedName("ResultStatus_")
    private int mResultStatus;
    @SerializedName("Serial_")
    private int mSerial_;
    public FileContent(){

    }

    public FileContent(int serial, int fieldID, int itemID, int quantity) {
        this.serial = serial;
        this.fieldID = fieldID;
        this.itemID = itemID;
        this.quantity = quantity;
    }

    public int getmResultStatus() {
        return mResultStatus;
    }

    public int getmSerial_() {
        return mSerial_;
    }
}
