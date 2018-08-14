package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class WifiSerializer implements JsonDeserializer<Wifi>, JsonSerializer<Wifi> {
    public Wifi deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String str = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Wifi wifi = new Wifi();
        wifi.setSsid(jsonObject.get("a") != null ? jsonObject.get("a").getAsString() : null);
        wifi.setCapabilities(jsonObject.get("b") != null ? jsonObject.get("b").getAsString() : null);
        wifi.setLinkSpeed(jsonObject.get("c") != null ? Integer.valueOf(jsonObject.get("c").getAsInt()) : null);
        wifi.setRssi(jsonObject.get("d") != null ? Integer.valueOf(jsonObject.get("d").getAsInt()) : null);
        if (jsonObject.get("f") != null) {
            str = jsonObject.get("f").getAsString();
        }
        wifi.setBssid(str);
        return wifi;
    }

    public JsonElement serialize(Wifi wifi, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("a", wifi.getSsid());
        jsonObject.addProperty("b", wifi.getCapabilities());
        jsonObject.addProperty("c", wifi.getLinkSpeed());
        jsonObject.addProperty("d", wifi.getRssi());
        jsonObject.addProperty("f", wifi.getBssid());
        return jsonObject;
    }
}
