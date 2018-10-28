package com.example.asherif.sahlapp.Region.Network.Model;

import com.google.gson.annotations.SerializedName;

public class File {
    /**
     * request
     * mserial : number we send it for creating new file,
     */
    @SerializedName("Serial")
    private int mSerial;
    @SerializedName("Scode")
    private int mScode;
    @SerializedName("FileName")
    private String mFileName;
    @SerializedName("Remarks")
    private String mRemarks;

    /**
     * response
     * mSerial_ : number of file we created in DB , this serial what we
     * will us in send file content.
     */
    @SerializedName("ResultStatus_")
    private int mResultStatus;
    @SerializedName("Serial_")
    private int mSerial_;

    public File(){

    }
    public File(int mSerial, int mScode, String mFileName, String mRemarks) {
        this.mSerial = mSerial;
        this.mScode = mScode;
        this.mFileName = mFileName;
        this.mRemarks = mRemarks;
    }

    public int getmResultStatus() {
        return mResultStatus;
    }

    public int getmSerial_() {
        return mSerial_;
    }
}
