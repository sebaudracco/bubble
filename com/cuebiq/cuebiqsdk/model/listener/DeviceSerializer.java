package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mobfox.sdk.networking.RequestParams;
import java.lang.reflect.Type;

public class DeviceSerializer implements JsonDeserializer<Device>, JsonSerializer<Device> {
    public Device deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String str = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Device device = new Device();
        device.setBrand(jsonObject.get("a") != null ? jsonObject.get("a").getAsString() : null);
        device.setDeviceType(jsonObject.get("b") != null ? jsonObject.get("b").getAsString() : null);
        device.setIsGoogleAdvIDDisabled(jsonObject.get("e") != null ? Boolean.valueOf(jsonObject.get("e").getAsBoolean()) : null);
        device.setManufacturer(jsonObject.get("g") != null ? jsonObject.get("g").getAsString() : null);
        device.setModel(jsonObject.get(RequestParams.f9035H) != null ? jsonObject.get(RequestParams.f9035H).getAsString() : null);
        device.setOsv(jsonObject.get(RequestParams.IP) != null ? Integer.valueOf(jsonObject.get(RequestParams.IP).getAsInt()) : null);
        device.setProduct(jsonObject.get("j") != null ? jsonObject.get("j").getAsString() : null);
        device.setLocale(jsonObject.get("l") != null ? jsonObject.get("l").getAsString() : null);
        device.setScreenSize(jsonObject.get(RequestParams.f9036M) != null ? jsonObject.get(RequestParams.f9036M).getAsString() : null);
        device.setTimezone(jsonObject.get("n") != null ? jsonObject.get("n").getAsString() : null);
        device.setTimezoneOffset(jsonObject.get("o") != null ? Integer.valueOf(jsonObject.get("o").getAsInt()) : null);
        device.setCarrierCode(jsonObject.get("s") != null ? jsonObject.get("s").getAsString() : null);
        device.setUserAgent(jsonObject.get("t") != null ? jsonObject.get("t").getAsString() : null);
        device.setBluetoothActive(jsonObject.get("w") != null ? Boolean.valueOf(jsonObject.get("w").getAsBoolean()) : null);
        if (jsonObject.get("x") != null) {
            str = jsonObject.get("x").getAsString();
        }
        device.setCarrierName(str);
        return device;
    }

    public JsonElement serialize(Device device, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("a", device.getBrand());
        jsonObject.addProperty("b", device.getDeviceType());
        jsonObject.addProperty("e", device.getIsGoogleAdvIDDisabled());
        jsonObject.addProperty("g", device.getManufacturer());
        jsonObject.addProperty(RequestParams.f9035H, device.getModel());
        jsonObject.addProperty(RequestParams.IP, device.getOsv());
        jsonObject.addProperty("j", device.getProduct());
        jsonObject.addProperty("l", device.getLocale());
        jsonObject.addProperty(RequestParams.f9036M, device.getScreenSize());
        jsonObject.addProperty("n", device.getTimezone());
        jsonObject.addProperty("o", device.getTimezoneOffset());
        jsonObject.addProperty("s", device.getCarrierCode());
        jsonObject.addProperty("t", device.getUserAgent());
        jsonObject.addProperty("w", device.getBluetoothActive());
        jsonObject.addProperty("x", device.getCarrierName());
        return jsonObject;
    }
}
