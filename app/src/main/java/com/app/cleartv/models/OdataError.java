package com.app.cleartv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OdataError {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("message")
    @Expose
    private Message message;
    @SerializedName("innererror")
    @Expose
    private Innererror innererror;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Innererror getInnererror() {
        return innererror;
    }

    public void setInnererror(Innererror innererror) {
        this.innererror = innererror;
    }

}
