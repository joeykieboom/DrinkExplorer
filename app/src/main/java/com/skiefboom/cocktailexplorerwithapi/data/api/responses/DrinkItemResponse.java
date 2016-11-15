package com.skiefboom.cocktailexplorerwithapi.data.api.responses;

import com.google.gson.annotations.Expose;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;

public class DrinkItemResponse {

    @Expose
    public Drink drink;
}
