package com.example.sasha.smarthcs.some_work_class;

import android.widget.ImageView;

public class Card {
    public String name;
    public int cost;
    ImageView picture;
    public Card(String _name, int _cost) {
        this.cost = _cost;
        this.name = _name;
    }
}
