package com.example.sasha.smarthcs.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Request {
    @SerializedName("login") String request;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Request(String request) {
        this.request = request;
    }
}
