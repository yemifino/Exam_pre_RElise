package com.example.sasha.smarthcs.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResursePOL {
    @SerializedName("previous")
    @Expose
    private List<String> array = null;
    @SerializedName("price")
    @Expose
private String price;
    @SerializedName("money")
    @Expose
    private String money;
    public List<String> getArray() {
        return array;
    }

    public void setArray(List<String> array) {
        this.array = array;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
