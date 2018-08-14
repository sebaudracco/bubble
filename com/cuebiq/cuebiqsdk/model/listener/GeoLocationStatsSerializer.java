package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.GeoLocationStats;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class GeoLocationStatsSerializer implements JsonDeserializer<GeoLocationStats>, JsonSerializer<GeoLocationStats> {
    public GeoLocationStats deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String str = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        GeoLocationStats geoLocationStats = new GeoLocationStats();
        geoLocationStats.setGoogleAid(jsonObject.get("googleAid") != null ? jsonObject.get("googleAid").getAsString() : null);
        geoLocationStats.setIsGAIDOptout(jsonObject.get("isGAIDOptout") != null ? Boolean.valueOf(jsonObject.get("isGAIDOptout").getAsBoolean()) : null);
        geoLocationStats.setLocationON(jsonObject.get("locationON") != null ? Boolean.valueOf(jsonObject.get("locationON").getAsBoolean()) : null);
        geoLocationStats.setAfterCoverage(jsonObject.get("afterCoverage") != null ? Boolean.valueOf(jsonObject.get("afterCoverage").getAsBoolean()) : null);
        geoLocationStats.setWifiEnabled(jsonObject.get("wifiEnabled") != null ? Boolean.valueOf(jsonObject.get("wifiEnabled").getAsBoolean()) : null);
        geoLocationStats.setWifiAlwaysScanning(jsonObject.get("wifiAlwaysScanning") != null ? Boolean.valueOf(jsonObject.get("wifiAlwaysScanning").getAsBoolean()) : null);
        geoLocationStats.setAppKey(jsonObject.get("appKey") != null ? jsonObject.get("appKey").getAsString() : null);
        geoLocationStats.setMcc(jsonObject.get("mcc") != null ? jsonObject.get("mcc").getAsString() : null);
        if (jsonObject.get("mnc") != null) {
            str = jsonObject.get("mnc").getAsString();
        }
        geoLocationStats.setMnc(str);
        return geoLocationStats;
    }

    public JsonElement serialize(GeoLocationStats geoLocationStats, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("googleAid", geoLocationStats.getGoogleAid());
        jsonObject.addProperty("isGAIDOptout", geoLocationStats.isGAIDOptout());
        jsonObject.addProperty("locationON", geoLocationStats.isLocationON());
        jsonObject.addProperty("afterCoverage", geoLocationStats.isAfterCoverage());
        jsonObject.addProperty("wifiEnabled", geoLocationStats.isWifiEnabled());
        jsonObject.addProperty("wifiAlwaysScanning", geoLocationStats.isWifiAlwaysScanning());
        jsonObject.addProperty("appKey", geoLocationStats.getAppKey());
        jsonObject.addProperty("mcc", geoLocationStats.getMcc());
        jsonObject.addProperty("mnc", geoLocationStats.getMnc());
        return jsonObject;
    }
}
