package com.skiefboom.cocktailexplorerwithapi.data.api.resources;

import com.skiefboom.cocktailexplorerwithapi.data.api.responses.DrinkListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Drinks {

    @GET("drinks")
    Call<DrinkListResponse> getCocktails(@QueryMap Map<String, String> attrs);
}
