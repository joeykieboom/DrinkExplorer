package com.skiefboom.cocktailexplorerwithapi.data.database.models;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.skiefboom.cocktailexplorerwithapi.data.database.CocktailExplorerDB;

import java.io.Serializable;

@Table(database = CocktailExplorerDB.class)
public class Taste extends BaseModel<Taste> implements Serializable {

    @PrimaryKey(autoincrement = true)
    @Expose
    public int id;

    @Expose
    @Column
    public String drinkId;

    @Expose
    @Column
    public String tasteId;

    @Expose
    @Column
    public String text;
}
