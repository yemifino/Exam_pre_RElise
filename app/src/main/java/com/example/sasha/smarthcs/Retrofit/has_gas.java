package com.example.sasha.smarthcs.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class has_gas {
    @SerializedName("cosstg")
    private List<String> gas;

    public List<String> getGas() {
        return gas;
    }

    public void setGas(List<String> gas) {
        this.gas = gas;
    }
}
