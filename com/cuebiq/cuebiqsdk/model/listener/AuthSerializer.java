package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class AuthSerializer implements JsonDeserializer<Auth>, JsonSerializer<Auth> {
    public Auth deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String str = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Auth auth = new Auth();
        auth.setAppPackageName(jsonObject.get("b") != null ? jsonObject.get("b").getAsString() : null);
        auth.setAppVersion(jsonObject.get("c") != null ? jsonObject.get("c").getAsString() : null);
        auth.setAppKey(jsonObject.get("d") != null ? jsonObject.get("d").getAsString() : null);
        auth.setAnonymousID(jsonObject.get("e") != null ? jsonObject.get("e").getAsString() : null);
        auth.setGoogleAdvertiserID(jsonObject.get("f") != null ? jsonObject.get("f").getAsString() : null);
        if (jsonObject.get("j") != null) {
            str = jsonObject.get("j").getAsString();
        }
        auth.setCustomPublisherID(str);
        return auth;
    }

    public JsonElement serialize(Auth auth, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("b", auth.getAppPackageName());
        jsonObject.addProperty("c", auth.getAppVersion());
        jsonObject.addProperty("d", auth.getAppKey());
        jsonObject.addProperty("e", auth.getAnonymousID());
        jsonObject.addProperty("f", auth.getGoogleAdvertiserID());
        jsonObject.addProperty("j", auth.getCustomPublisherID());
        return jsonObject;
    }
}
