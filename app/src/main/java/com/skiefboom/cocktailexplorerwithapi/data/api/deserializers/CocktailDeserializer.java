package com.skiefboom.cocktailexplorerwithapi.data.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink_Table;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Ingredient;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Taste;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CocktailDeserializer extends JsonDeserializer<Drink> {

    @Override
    public Drink deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject responseObject = json.getAsJsonObject();

        Drink drink = new Drink();
        drink.name = getString(responseObject, "name");
        drink.descriptionPlain = getString(responseObject, "descriptionPlain");
        drink.drinkId = getString(responseObject, "id");
        drink.rating = getInt(responseObject, "rating");
        drink.isAlcoholic = getBool(responseObject, "isAlcoholic");
        drink.isHot = getBool(responseObject, "isHot");
        drink.story = getString(responseObject, "story");
        drink.saved = false;

        if (responseObject.has("ingredients")) {

            List<Ingredient> ingredients = context.deserialize(responseObject.getAsJsonArray("ingredients"), new TypeToken<ArrayList<Ingredient>>() {}.getType());

            for (Ingredient ingredient : ingredients) {
                ingredient.drinkId = drink.drinkId;
                ingredient.save();
            }

            drink.ingredients = ingredients;
        }

        if (responseObject.has("tastes")) {

            List<Taste> tastes = context.deserialize(responseObject.getAsJsonArray("tastes"), new TypeToken<ArrayList<Taste>>() {}.getType());

            for (Taste taste : tastes) {
                taste.drinkId = drink.drinkId;
                taste.save();
            }

            drink.tastes = tastes;
        }

        drink.save();

        return drink;
    }
}
