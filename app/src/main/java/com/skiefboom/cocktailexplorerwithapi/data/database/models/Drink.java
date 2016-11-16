package com.skiefboom.cocktailexplorerwithapi.data.database.models;

import com.google.gson.annotations.Expose;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.skiefboom.cocktailexplorerwithapi.data.database.CocktailExplorerDB;

import java.io.Serializable;

@Table(database = CocktailExplorerDB.class)
public class Drink extends BaseModel<Drink> implements Serializable {

    @Expose
    @PrimaryKey(autoincrement = true)
    public int id;

    @Expose
    @Column
    public String descriptionPlain;

    @Expose
    @Column
    public String name;

    @Expose
    @Column
    public String drinkId;

    @Expose
    @Column
    public int rating;

    @Expose
    @Column
    public boolean isAlcoholic;

    @Expose
    @Column
    public boolean isHot;

    @Expose
    @Column
    public String story;

    @Expose
    @Column
    public boolean saved;

    public static Drink getById(int id) {

        return SQLite.select()
                .from(Drink.class)
                .where(Drink_Table.id.eq(id))
                .querySingle();
    }
}
