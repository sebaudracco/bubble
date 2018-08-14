package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Event;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import com.cuebiq.cuebiqsdk.utils.WifiList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mobfox.sdk.networking.RequestParams;
import java.lang.reflect.Type;
import java.util.Iterator;

public class InformationSerializer implements JsonDeserializer<Information>, JsonSerializer<Information> {
    public Information deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        long j = 0;
        Boolean bool = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Information information = new Information();
        information.setEvent((Event) jsonDeserializationContext.deserialize(jsonObject.get("a"), Event.class));
        information.setGeo((Geo) jsonDeserializationContext.deserialize(jsonObject.get("d"), Geo.class));
        information.setTimestamp(Long.valueOf(jsonObject.get("e") != null ? jsonObject.get("e").getAsLong() : 0));
        information.setBatteryLevel(jsonObject.get("f") != null ? Float.valueOf(jsonObject.get("f").getAsFloat()) : null);
        WifiList wifiList = new WifiList();
        if (jsonObject.has("g")) {
            Iterator it = jsonObject.getAsJsonArray("g").iterator();
            while (it.hasNext()) {
                wifiList.add((Wifi) jsonDeserializationContext.deserialize((JsonElement) it.next(), Wifi.class));
            }
        }
        information.setWifis(wifiList);
        information.setDebugMode(jsonObject.get(RequestParams.f9035H) != null ? Boolean.valueOf(jsonObject.get(RequestParams.f9035H).getAsBoolean()) : null);
        information.setDataConnectionType(jsonObject.get("j") != null ? jsonObject.get("j").getAsString() : null);
        if (jsonObject.get("k") != null) {
            j = jsonObject.get("k").getAsLong();
        }
        information.setLastSeen(Long.valueOf(j));
        information.setIsRoaming(jsonObject.get("l") != null ? Boolean.valueOf(jsonObject.get("l").getAsBoolean()) : null);
        information.setIpAddressV4(jsonObject.get("n") != null ? jsonObject.get("n").getAsString() : null);
        information.setIpAddressV6(jsonObject.get("o") != null ? jsonObject.get("o").getAsString() : null);
        if (jsonObject.get("p") != null) {
            bool = Boolean.valueOf(jsonObject.get("p").getAsBoolean());
        }
        information.setGeofence(bool);
        return information;
    }

    public JsonElement serialize(Information information, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.add("a", jsonSerializationContext.serialize(information.getEvent()));
        jsonObject.add("d", jsonSerializationContext.serialize(information.getGeo()));
        jsonObject.addProperty("e", information.getTimestamp());
        jsonObject.addProperty("f", information.getBatteryLevel());
        jsonObject.add("g", jsonSerializationContext.serialize(information.getWifis()));
        jsonObject.addProperty(RequestParams.f9035H, information.getDebugMode());
        jsonObject.addProperty("j", information.getDataConnectionType());
        jsonObject.addProperty("k", information.getLastSeen());
        jsonObject.addProperty("l", information.getIsRoaming());
        jsonObject.addProperty("n", information.getIpAddressV4());
        jsonObject.addProperty("o", information.getIpAddressV6());
        jsonObject.addProperty("p", information.getGeofence());
        return jsonObject;
    }
}
