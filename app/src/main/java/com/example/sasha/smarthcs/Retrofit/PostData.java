package com.example.sasha.smarthcs.Retrofit;

import com.google.gson.annotations.SerializedName;

public class PostData {
    @SerializedName
            ("login") private String login;
    public PostData(String login){
        super();
        this.login=login;

    }
}
