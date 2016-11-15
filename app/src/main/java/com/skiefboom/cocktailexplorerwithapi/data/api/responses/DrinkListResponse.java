package com.skiefboom.cocktailexplorerwithapi.data.api.responses;

import com.google.gson.annotations.Expose;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;

import java.util.List;

public class DrinkListResponse extends ListResponse<Drink> {

    @Expose
    public List<Drink> result;

    public int totalResult;

    public void saveAll() {

        saveAll(result);
    }
}
