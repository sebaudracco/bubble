package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RequestSerializer implements JsonDeserializer<TrackRequest>, JsonSerializer<TrackRequest> {
    public TrackRequest deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Iterator it;
        JsonObject jsonObject = (JsonObject) jsonElement;
        TrackRequest trackRequest = new TrackRequest();
        trackRequest.setAuth((Auth) jsonDeserializationContext.deserialize(jsonObject.get("a"), Auth.class));
        trackRequest.setDevice((Device) jsonDeserializationContext.deserialize(jsonObject.get("b"), Device.class));
        if (jsonObject.has("c")) {
            InformationList informationList = new InformationList();
            it = jsonObject.getAsJsonArray("c").iterator();
            while (it.hasNext()) {
                informationList.add((Information) jsonDeserializationContext.deserialize((JsonElement) it.next(), Information.class));
            }
            trackRequest.setInformation(informationList);
        }
        if (jsonObject.has("e")) {
            List arrayList = new ArrayList();
            it = jsonObject.getAsJsonArray("e").iterator();
            while (it.hasNext()) {
                arrayList.add((BluetoothDevice) jsonDeserializationContext.deserialize((JsonElement) it.next(), BluetoothDevice.class));
            }
            trackRequest.setPairedDevices(arrayList);
        }
        trackRequest.setSettingsVersion(jsonObject.get("v") != null ? Integer.valueOf(jsonObject.get("v").getAsInt()) : null);
        return trackRequest;
    }

    public JsonElement serialize(TrackRequest trackRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.add("a", jsonSerializationContext.serialize(trackRequest.getAuth()));
        jsonObject.add("b", jsonSerializationContext.serialize(trackRequest.getDevice()));
        jsonObject.add("c", jsonSerializationContext.serialize(trackRequest.getInformation()));
        jsonObject.add("e", jsonSerializationContext.serialize(trackRequest.getPairedDevices()));
        jsonObject.addProperty("v", trackRequest.getSettingsVersion());
        return jsonObject;
    }
}
