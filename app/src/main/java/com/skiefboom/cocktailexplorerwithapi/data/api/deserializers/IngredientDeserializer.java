package com.skiefboom.cocktailexplorerwithapi.data.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Ingredient;

import java.lang.reflect.Type;

public class IngredientDeserializer extends JsonDeserializer<Ingredient> {

    @Override
    public Ingredient deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject responseObject = json.getAsJsonObject();

        Ingredient ingredient = new Ingredient();

        ingredient.type = getString(responseObject, "type");
        ingredient.ingredientId = getString(responseObject, "id");
        ingredient.text = getString(responseObject, "text");
        ingredient.textPlain = getString(responseObject, "textPlain");

        ingredient.save();

        return ingredient;
    }
}
