package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.CoverageSettings;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class CoverageSettingsSerializer implements JsonDeserializer<CoverageSettings> {
    public CoverageSettings deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        boolean z = false;
        JsonObject jsonObject = (JsonObject) jsonElement;
        CoverageSettings coverageSettings = new CoverageSettings();
        coverageSettings.setD(Integer.valueOf(jsonObject.get("d") != null ? jsonObject.get("d").getAsInt() : 0));
        coverageSettings.setCountry(jsonObject.get("country") != null ? jsonObject.get("country").getAsString() : "");
        if (jsonObject.get("countryOpen") != null) {
            z = jsonObject.get("countryOpen").getAsBoolean();
        }
        coverageSettings.setCountryOpen(z);
        coverageSettings.setIsGDPRCountry(jsonObject.get("gdprcountry") != null ? jsonObject.get("gdprcountry").getAsBoolean() : true);
        return coverageSettings;
    }
}
