package com.skiefboom.cocktailexplorerwithapi.data.database.models;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.skiefboom.cocktailexplorerwithapi.data.database.CocktailExplorerDB;

import java.io.Serializable;

@Table(database = CocktailExplorerDB.class)
public class Ingredient extends BaseModel<Ingredient> implements Serializable {

    @Expose
    @PrimaryKey(autoincrement = true)
    public int id;

    @Expose
    @Column
    public String drinkId;

    @Expose
    @Column
    public String ingredientId;

    @Expose
    @Column
    public String text;

    @Expose
    @Column
    public String textPlain;

    @Expose
    @Column
    public String type;
}
