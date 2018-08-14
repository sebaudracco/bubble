package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.BluetoothDevice;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class BluetoothDeviceSerializer implements JsonDeserializer<BluetoothDevice>, JsonSerializer<BluetoothDevice> {
    public BluetoothDevice deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Integer num = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        BluetoothDevice bluetoothDevice = new BluetoothDevice();
        bluetoothDevice.setName(jsonObject.get("a") != null ? jsonObject.get("a").getAsString() : null);
        bluetoothDevice.setType(jsonObject.get("b") != null ? Integer.valueOf(jsonObject.get("b").getAsInt()) : null);
        if (jsonObject.get("e") != null) {
            num = Integer.valueOf(jsonObject.get("e").getAsInt());
        }
        bluetoothDevice.setDeviceClass(num);
        return bluetoothDevice;
    }

    public JsonElement serialize(BluetoothDevice bluetoothDevice, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("a", bluetoothDevice.getName());
        jsonObject.addProperty("b", bluetoothDevice.getType());
        jsonObject.addProperty("e", bluetoothDevice.getDeviceClass());
        return jsonObject;
    }
}
