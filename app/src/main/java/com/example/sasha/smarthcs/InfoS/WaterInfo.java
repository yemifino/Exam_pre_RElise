package com.example.sasha.smarthcs.InfoS;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.sasha.smarthcs.Main_1.MainActivity;
import com.example.sasha.smarthcs.R;
import com.example.sasha.smarthcs.Recycler_Adapters.RecyclerViewAdapter4;

public class WaterInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_info);
        int j = MainActivity.index;


        TextView resource = findViewById(R.id.resource_w);
        resource.setTextSize(27);
        resource.setText("Потрачено"+":"+" "+(String.valueOf((int)((Integer.valueOf(MainActivity.GSV.get(2))/MainActivity.water_ideal))) +" " +"м³"));
        TextView sum = findViewById(R.id.sum_w);
        sum.setTextSize(27);
        sum.setText(MainActivity.GSV.get(2)+" "+"Рублей");
        RecyclerView bill_list = findViewById(R.id.bill_list);
        bill_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        RecyclerViewAdapter4 adapter = new RecyclerViewAdapter4(getResources());
        bill_list.setAdapter(adapter);
    }
}
