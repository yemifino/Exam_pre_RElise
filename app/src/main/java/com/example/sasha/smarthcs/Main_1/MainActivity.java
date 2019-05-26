package com.example.sasha.smarthcs.Main_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sasha.smarthcs.Retrofit.API;
import com.example.sasha.smarthcs.some_work_class.Card;
import com.example.sasha.smarthcs.Retrofit.GSV;
import com.example.sasha.smarthcs.Retrofit.POST_GSV;
import com.example.sasha.smarthcs.ProfileActivity;
import com.example.sasha.smarthcs.R;
import com.example.sasha.smarthcs.Retrofit.Reply;
import com.example.sasha.smarthcs.some_work_class.User;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
   public static ArrayList<Card> history = new ArrayList<>();
    static ArrayList<Card> cards = new ArrayList<>();
    public static ArrayList<String> GSV = new ArrayList<>();

    static ArrayList<User> user_base = new ArrayList<>();
    public static Retrofit retrofit;
    public static int index = 0;
    public static double gas_ideal = 9.22;
    public static double water_ideal = 44.56;
    public static double light_ideal = 6.67;

    public static String LOCAL = "http://192.168.1.8:5000/";
    public static String LOGIN;
    public static ArrayList<String> arrayList =new ArrayList<>();
    public static String year="2019";
    public static String year_pre="2018";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.login_button);
        final EditText loginText = findViewById(R.id.login_text);
        final EditText passwordText = findViewById(R.id.password_text);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String cur_name = loginText.getText().toString();
                String cur_password = passwordText.getText().toString();
                Retrofit retrofit = new Retrofit.Builder() .baseUrl(LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();

                API api = retrofit.create(API.class);
                Call<Reply> call = api.login(new Login(cur_name,cur_password));
                call.enqueue(new Callback<Reply>() {
                    @Override
                    public void onResponse(@NotNull Call<Reply> call, @NotNull Response<Reply> response) {

                        if (response.body().reply.equals("1")) {

                            Toast.makeText(getApplicationContext(), "Успешно", Toast.LENGTH_LONG).show();
                            LOGIN = cur_name;
                            openProfile();
                        } else {
                            Toast.makeText(getApplicationContext(), "Ошибка, повторите ввод", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<Reply> call, @NotNull Throwable t) {
                        Toast.makeText(getApplicationContext(), "Ошибка соединения с сервером", Toast.LENGTH_LONG).show();
                    }
                 });
            }
        });
    }


    void openProfile() {
        Retrofit retrofit = new Retrofit.Builder() .baseUrl(LOCAL) .addConverterFactory(GsonConverterFactory.create()) .build();
        final API api = retrofit.create(API.class);
        Call<com.example.sasha.smarthcs.Retrofit.GSV> call = api.getResources(new POST_GSV(LOGIN));

        call.enqueue(new Callback<GSV>() {
            @Override
            public void onResponse(@NotNull Call<GSV> call,@NotNull Response<GSV> response) {
                GSV gsv=response.body();
// ArrayList<String> arrayList=new ArrayList<>();
// arrayList.add("8709");
// arrayList.add("5450");
// arrayList.add("3736");
                List<String> arrayList = response.body().getArray();
                cards.add(new Card("Вода",Integer.valueOf(arrayList.get(0))));

                cards.add(new Card("Газ",Integer.valueOf(arrayList.get(1))));

                cards.add(new Card("Свет",Integer.valueOf(arrayList.get(2))));
                GSV.add(arrayList.get(0));
                GSV.add(arrayList.get(1));
                GSV.add(arrayList.get(2));

                int sum = Integer.valueOf(arrayList.get(0)) + Integer.valueOf(arrayList.get(1)) + Integer.valueOf(arrayList.get(2));

                cards.add(new Card("Итого",sum));
            }
            @Override
            public void onFailure(Call<GSV> call, Throwable t) {

            }
        });
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public static double water_resurse(int cost)
    {
        double res = (double)(cost) / 100;
        return res + 0.005;
    }

    public static double gas_resurse(int cost)
    {
        double res = (double)(cost) / 6.5;
        return res + 0.005;
    }

    public static double light_resurse(int cost)
    {
        double res = (double)(cost) / 500;
        return res + 0.005;
    }

}
