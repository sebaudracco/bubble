package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.IpAddress;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class IpAddressSerializer implements JsonDeserializer<IpAddress>, JsonSerializer<IpAddress> {
    public IpAddress deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) jsonElement;
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIpAddr(jsonObject.get("ipAddr") != null ? jsonObject.get("ipAddr").getAsString() : null);
        return ipAddress;
    }

    public JsonElement serialize(IpAddress ipAddress, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("ipAddr", ipAddress.getIpAddr());
        return jsonObject;
    }
}
