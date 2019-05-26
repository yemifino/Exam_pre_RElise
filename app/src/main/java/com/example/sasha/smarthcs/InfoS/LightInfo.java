package com.example.sasha.smarthcs.InfoS;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.sasha.smarthcs.Main_1.MainActivity;
import com.example.sasha.smarthcs.R;
import com.example.sasha.smarthcs.Recycler_Adapters.RecyclerViewAdapter3;

public class LightInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_info);

        TextView resource = findViewById(R.id.resource_l);
        resource.setTextSize(27);
        resource.setText("Потрачено"+":"+" "+(String.valueOf((int)((Integer.valueOf(MainActivity.GSV.get(2))/MainActivity.light_ideal))) +" " +"Р/МВт/ч"));
        TextView sum = findViewById(R.id.sum_l);
        sum.setTextSize(27);
        sum.setText(MainActivity.GSV.get(2)+" "+"Рублей");
        RecyclerView bill_list = findViewById(R.id.bill_list);
        bill_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        RecyclerViewAdapter3 adapter = new RecyclerViewAdapter3(getResources());
        bill_list.setAdapter(adapter);
    }
}
