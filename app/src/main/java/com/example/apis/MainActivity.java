package com.example.apis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.le.AdvertisingSetParameters;
import android.os.Bundle;
//import android.widget.Adapter;
import com.example.apis.CustomAdapter;


import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getposts().enqueue(new Callback<List<PostPojo>>() {
            @Override
            public void onResponse(Call<List<PostPojo>> call, Response<List<PostPojo>> response) {


                if(response.body().size()>0){

                    List<PostPojo> PostList = response.body();

                     Adapter adapter =  new Adapter(MainActivity.this,PostList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    Toast.makeText(MainActivity.this,"List is not empty",Toast.LENGTH_LONG).show();
                }

                else
                {
                    Toast.makeText(MainActivity.this,"List is empty",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<List<PostPojo>> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
}
