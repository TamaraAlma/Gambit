package com.example.idilika.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.idilika.adapter.Adapter;
import com.example.idilika.api.ApiService;
import com.example.idilika.api.RetroClient;
import com.example.idilika.model.Dish;
import com.example.idilika.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    ArrayList<Dish> dishes;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ApiService apiService = RetroClient.getClient().create(ApiService.class);
        Call<ArrayList<Dish>> call = apiService.getDishes("1");
        call.enqueue(new Callback<ArrayList<Dish>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Dish>> call,
                                   @NonNull Response<ArrayList<Dish>> response) {
                dishes = response.body();
                Adapter adapter = new Adapter(dishes);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Dish>> call, @NonNull Throwable t) {
                Log.d("TAG", "Response = " + t.getMessage());
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.basket_menu:
                startActivity(new Intent(SecondActivity.this, BasketActivity.class));
                return true;
            default:
        return super.onOptionsItemSelected(item);}
    }

}

