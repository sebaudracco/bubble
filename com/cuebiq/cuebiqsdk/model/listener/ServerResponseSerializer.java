package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.wrapper.CoverageSettings;
import com.cuebiq.cuebiqsdk.model.wrapper.ServerResponseV2;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class ServerResponseSerializer implements JsonDeserializer<ServerResponseV2>, JsonSerializer<ServerResponseV2> {
    public ServerResponseV2 deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) jsonElement;
        ServerResponseV2 serverResponseV2 = new ServerResponseV2();
        serverResponseV2.setCs((CoverageSettings) jsonDeserializationContext.deserialize(jsonObject.get("cs"), CoverageSettings.class));
        serverResponseV2.setGs((Settings) jsonDeserializationContext.deserialize(jsonObject.get("gs"), Settings.class));
        return serverResponseV2;
    }

    public JsonElement serialize(ServerResponseV2 serverResponseV2, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.add("cs", jsonSerializationContext.serialize(serverResponseV2.getCs()));
        jsonObject.add("gs", jsonSerializationContext.serialize(serverResponseV2.getGs()));
        return jsonObject;
    }
}
