package com.example.sasha.smarthcs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sasha.smarthcs.InfoS.GazInfo;
import com.example.sasha.smarthcs.InfoS.LightInfo;
import com.example.sasha.smarthcs.InfoS.TotalInfo;
import com.example.sasha.smarthcs.InfoS.WaterInfo;
import com.example.sasha.smarthcs.Main_1.MainActivity;
import com.example.sasha.smarthcs.Main_1.RecyclerViewAdapter;
import com.example.sasha.smarthcs.Retrofit.API;
import com.example.sasha.smarthcs.Retrofit.POST_GSV;
import com.example.sasha.smarthcs.Retrofit.has_gas;
import com.example.sasha.smarthcs.Retrofit.has_light;
import com.example.sasha.smarthcs.Retrofit.has_total;
import com.example.sasha.smarthcs.Retrofit.has_water;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.money.android.sdk.Amount;
import ru.yandex.money.android.sdk.Checkout;
import ru.yandex.money.android.sdk.Configuration;
import ru.yandex.money.android.sdk.PaymentMethodType;
import ru.yandex.money.android.sdk.ShopParameters;

public class ProfileActivity extends AppCompatActivity {
    public static ArrayList<String> Gas_cards = new ArrayList<>();
    public static ArrayList<String> Water_cards = new ArrayList<>();
    public static ArrayList<String> Light_cards = new ArrayList<>();
    public static ArrayList<String> Total_cards = new ArrayList<>();
    public String gas="gas";
    public static ArrayList<Integer> cards3 = new ArrayList<>();
    public int g=0;
    private static final String LOCAL = "http://192.168.1.9:5000/";
     public  static String removeChar(String i,int pos){
         return i.substring(0,pos)+i.substring(pos+1);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView login = findViewById(R.id.textlogin);
        cards3.add(0,4);
        cards3.add(1,3);
        cards3.add(2,2);
        cards3.add(3,1);
        cards3.add(4,12);
        cards3.add(5,11);
onGas();
onLight();
onWater();
onTotal();


        /*Retrofit retrofit = new Retrofit.Builder() .baseUrl(LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        API api = retrofit.create(API.class);
        Call<Reply> call = api.getFullResources(new Request(MainActivity.LOGIN),"gas");
        // TODO add rand() (or make good input)
        call.enqueue(new Callback<Reply>() {
            @Override
            public void onResponse(Call<Reply> call, Response<Reply> response) {
                if (MainActivity.cards2.get(0).now.sum_g == 0) for (int i = 0; i < 30; i++) MainActivity.cards2.get(i).now.sum_g += Integer.valueOf(response.body().reply);
            }

            @Override
            public void onFailure(Call<Reply> call, Throwable t) {

            }
        });
        Call<Reply> call2 = api.getFullResources(new Request(MainActivity.LOGIN),"light");
        call2.enqueue(new Callback<Reply>() {
            @Override
            public void onResponse(Call<Reply> call, Response<Reply> response) {
                if (MainActivity.cards2.get(0).now.sum_l == 0) for (int i = 0; i < 30; i++) MainActivity.cards2.get(i).now.sum_l += Integer.valueOf(response.body().reply);
            }

            @Override
            public void onFailure(Call<Reply> call, Throwable t) {

            }
        });
        Call<Reply> call3 = api.getFullResources(new Request(MainActivity.LOGIN),"water");
        call3.enqueue(new Callback<Reply>() {
            @Override
            public void onResponse(Call<Reply> call, Response<Reply> response) {
                if (MainActivity.cards2.get(0).now.sum_w == 0) for (int i = 0; i < 30; i++) MainActivity.cards2.get(i).now.sum_w += Integer.valueOf(response.body().reply);
            }

            @Override
            public void onFailure(Call<Reply> call, Throwable t) {

            }
        });*/
        RecyclerView bill_list = findViewById(R.id.biil_list);
        bill_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getResources(), new RecyclerViewAdapter.OnResourceSelected() {
            @Override
            public void onResourcesSelected(int pos) {
                if(pos == 0) {
                    startWaterInfo();
                }
                else if(pos == 1) {
                    startGazInfo();
                }
                else if(pos == 2) {
                    startLightInfo();
                }
                else if(pos == 3) {
                    startTotalInfo();
                }
            }
        });

        bill_list.setAdapter(adapter);
        Checkout.attach(getSupportFragmentManager());}
    public void onGas(){
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(MainActivity.LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        final API api = retrofit.create(API.class);
        Call<has_gas> call = api.getGas(new POST_GSV(MainActivity.LOGIN));
        call.enqueue(new Callback<has_gas>() {
            @Override
            public void onResponse(@NotNull Call<has_gas> call, @NotNull Response<has_gas> response) {
                assert response.body() != null;
                List<String> arrayList=response.body().getGas();
                Gas_cards.add(0,arrayList.get(0));
                Gas_cards.add(1,arrayList.get(1));
                Gas_cards.add(2,arrayList.get(2));
                Gas_cards.add(3,arrayList.get(3));
                Gas_cards.add(4,arrayList.get(4));
                Gas_cards.add(5,arrayList.get(5));

            }

            @Override
            public void onFailure(Call<has_gas> call, Throwable t) {

            }
        });

    }
    void onTotal(){
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(MainActivity.LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        final API api = retrofit.create(API.class);
        //  Call<has_gas> call = api.getWater(new POST_GSV(MainActivity.LOGIN));
Call<has_total> call=api.getTotal(new POST_GSV(MainActivity.LOGIN));
call.enqueue(new Callback<has_total>() {
    @Override
    public void onResponse(Call<has_total> call, Response<has_total> response) {
        List<String> arrayLst =response.body().getTotal();
        Total_cards.add(0,arrayLst.get(0));
        Total_cards.add(1,arrayLst.get(1));
        Total_cards.add(2,arrayLst.get(2));
        Total_cards.add(3,arrayLst.get(3));
        Total_cards.add(4,arrayLst.get(4));
        Total_cards.add(5,arrayLst.get(5));

    }

    @Override
    public void onFailure(Call<has_total> call, Throwable t) {

    }
});
    }
    public void onWater(){
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(MainActivity.LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        final API api = retrofit.create(API.class);
      //  Call<has_gas> call = api.getWater(new POST_GSV(MainActivity.LOGIN));
        Call<has_water> call=api.getWater(new POST_GSV(MainActivity.LOGIN));
        call.enqueue(new Callback<has_water>() {
            @Override
            public void onResponse(@NotNull Call<has_water> call, @NotNull Response<has_water> response) {
                assert response.body() != null;
                List<String> arrayList=response.body().getWater();
                Water_cards.add(0,arrayList.get(0));
                Water_cards.add(1,arrayList.get(1));
                Water_cards.add(2,arrayList.get(2));
                Water_cards.add(3,arrayList.get(3));
                Water_cards.add(4,arrayList.get(4));
                Water_cards.add(5,arrayList.get(5));

            }

            @Override
            public void onFailure(Call<has_water> call, Throwable t) {

            }

        });

    }
    public void onLight(){
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(MainActivity.LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        final API api = retrofit.create(API.class);
      //  Call<has_gas> call = api.getGas(new POST_GSV(MainActivity.LOGIN));
        Call<has_light>call = api.getLight(new POST_GSV(MainActivity.LOGIN));
        call.enqueue(new Callback<has_light>() {
            @Override
            public void onResponse(@NotNull Call<has_light> call, @NotNull Response<has_light> response) {
                assert response.body() != null;
                List<String> arrayList=response.body().getLight();
                Light_cards.add(0,arrayList.get(0));
                Light_cards.add(1,arrayList.get(1));
                Light_cards.add(2,arrayList.get(2));
               Light_cards.add(3,arrayList.get(3));
                Light_cards.add(4,arrayList.get(4));
                Light_cards.add(5,arrayList.get(5));

            }

            @Override
            public void onFailure(Call<has_light> call, Throwable t) {

            }
        });

    }
   /* void openProfile() {
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        final API api = retrofit.create(API.class);
      Call<ResursePOL> call =api.getFullResources(new PostData(MainActivity.LOGIN), "light" );
        call.enqueue(new Callback<ResursePOL>() {
            @Override
            public void onResponse(@NotNull Call<ResursePOL> call, @NotNull Response<ResursePOL> response) {
                ResursePOL res =response.body();
 //ArrayList<String> arrayList=new ArrayList<>();
// arrayList.add("8709");
// arrayList.add("5450");
// arrayList.add("3736");
                assert response.body() != null;
                List<String> arrayList = response.body().getArray();
               Dat_cards.add(0,new Dat(arrayList.get(0)));
               Dat_cards.add(1,new Dat(arrayList.get(1)));
               Dat_cards.add(2,new Dat(arrayList.get(2)));
               Dat_cards.add(3,new Dat(arrayList.get(3)));
               Dat_cards.add(4,new Dat(arrayList.get(4)));
               Dat_cards.add(5,new Dat(arrayList.get(5)));
               Dat_cards.add(6,new Dat(arrayList.get(6)));
               Dat_cards.add(7,new Dat(arrayList.get(7)));


            }
            @Override
            public void onFailure(Call<ResursePOL> call, Throwable t) {

            }
        });
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Checkout.detach();
    }

    public void buy(View view)
    {
        Toast.makeText(getApplicationContext(), "Оплата...", Toast.LENGTH_LONG).show();
        timeToStartCheckout();
    }

    void timeToStartCheckout() {

        Checkout.configureTestMode(
                new Configuration(
                        true,
                        true,
                        true,
                        1,
                        true,
                        true
                )
        );
        int sum = 0;
        Checkout.tokenize(
                this,
                new Amount(new BigDecimal(String.valueOf(Integer.valueOf(MainActivity.GSV.get(0))+
                        Integer.valueOf(MainActivity.GSV.get(1))+
                        Integer.valueOf(MainActivity.GSV.get(2)))), Currency.getInstance("RUB")),
                new ShopParameters(
                        "Умный ЖКХ",
                        "Оплата ЖКХ",
                        "47",
                        Collections.singleton(PaymentMethodType.BANK_CARD)
                )
        );
    }

    private void startWaterInfo() {
        Intent intent = new Intent(this, WaterInfo.class);
        startActivity(intent);
    }

    private void startLightInfo() {
        Intent intent = new Intent(this, LightInfo.class);
        startActivity(intent);
    }

    private void startGazInfo() {
        Intent intent = new Intent(this, GazInfo.class);
        startActivity(intent);
    }

    private void startTotalInfo() {
        Intent intent = new Intent(this, TotalInfo.class);
        startActivity(intent);
    }

    public void exit(View view)
    {
        Toast.makeText(getApplicationContext(), "Выход...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
