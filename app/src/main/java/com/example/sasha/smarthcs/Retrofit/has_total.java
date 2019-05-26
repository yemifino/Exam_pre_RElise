package com.example.sasha.smarthcs.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class has_total {
    @SerializedName("cosstw")
    private List<String> total;

    public List<String> getTotal() {
        return total;
    }

    public void setTotal(List<String> total) {
        this.total = total;
    }
}
