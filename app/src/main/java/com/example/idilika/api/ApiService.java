package com.example.idilika.api;

import com.example.idilika.model.Dish;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/category/39")
    Call<ArrayList<Dish>> getDishes(
            @Query("page") String page);
}
