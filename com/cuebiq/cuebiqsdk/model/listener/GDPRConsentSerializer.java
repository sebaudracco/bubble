package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.GDPRConsent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class GDPRConsentSerializer implements JsonSerializer<GDPRConsent> {
    public JsonElement serialize(GDPRConsent gDPRConsent, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("deviceId", gDPRConsent.getId());
        jsonObject.addProperty("whichPopupUserGrantsConsentIn", Integer.valueOf(gDPRConsent.getWhichPopup()));
        jsonObject.addProperty("os", gDPRConsent.getOs());
        return jsonObject;
    }
}
