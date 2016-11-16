package com.skiefboom.cocktailexplorerwithapi.data.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.skiefboom.cocktailexplorerwithapi.data.database.models.Taste;

import java.lang.reflect.Type;

public class TasteDeserializer extends JsonDeserializer<Taste> {

    @Override
    public Taste deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject responseObject = json.getAsJsonObject();

        Taste taste = new Taste();
        taste.tasteId = getString(responseObject, "id");
        taste.text = getString(responseObject, "text");

        taste.save();

        return taste;
    }
}
