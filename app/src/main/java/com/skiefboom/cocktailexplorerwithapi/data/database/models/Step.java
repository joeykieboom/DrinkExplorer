package com.skiefboom.cocktailexplorerwithapi.data.database.models;


import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;

import java.io.Serializable;

public class Step extends BaseModel<Step> implements Serializable {

    @Expose
    @PrimaryKey(autoincrement = true)
    public int id;

    @Expose
    @Column
    public String drinkId;

    @Expose
    @Column
    public String textPlain;

    @Expose
    @Column
    public String imagePath;

    @Expose
    @Column
    public String imageName;

    @Expose
    @Column
    public String action;

    @Expose
    @Column
    public int quantity;

    @Expose
    @Column
    public String unit;

    @Expose
    @Column
    public String ingredient;

    @Expose
    @Column
    public String ingredientType;

    @Expose
    @Column
    public String container;

    @Expose
    @Column
    public String containerType;
}
