package com.skiefboom.cocktailexplorerwithapi.data.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Drink;

import java.lang.reflect.Type;

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
        drink.save();

        return drink;
    }
}
