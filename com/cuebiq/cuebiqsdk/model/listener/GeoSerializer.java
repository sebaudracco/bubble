package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class GeoSerializer implements JsonDeserializer<Geo>, JsonSerializer<Geo> {
    public Geo deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Double d = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Geo geo = new Geo();
        geo.setAltitude(jsonObject.get("a") != null ? Double.valueOf(jsonObject.get("a").getAsDouble()) : null);
        geo.setHaccuracy(jsonObject.get("b") != null ? Float.valueOf(jsonObject.get("b").getAsFloat()) : null);
        geo.setLatitude(jsonObject.get("c") != null ? Double.valueOf(jsonObject.get("c").getAsDouble()) : null);
        if (jsonObject.get("d") != null) {
            d = Double.valueOf(jsonObject.get("d").getAsDouble());
        }
        geo.setLongitude(d);
        return geo;
    }

    public JsonElement serialize(Geo geo, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("a", geo.getAltitude());
        jsonObject.addProperty("b", geo.getHaccuracy());
        jsonObject.addProperty("c", geo.getLatitude());
        jsonObject.addProperty("d", geo.getLongitude());
        return jsonObject;
    }
}
