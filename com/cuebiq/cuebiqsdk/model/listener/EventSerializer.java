package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.wrapper.Event;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class EventSerializer implements JsonDeserializer<Event>, JsonSerializer<Event> {
    public Event deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String str = null;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Event event = new Event();
        event.setName(jsonObject.get("a") != null ? jsonObject.get("a").getAsString() : null);
        event.seteInfo1(jsonObject.get("b") != null ? jsonObject.get("b").getAsString() : null);
        event.seteInfo2(jsonObject.get("c") != null ? jsonObject.get("c").getAsString() : null);
        event.seteInfo3(jsonObject.get("d") != null ? jsonObject.get("d").getAsString() : null);
        if (jsonObject.get("e") != null) {
            str = jsonObject.get("e").getAsString();
        }
        event.seteInfo4(str);
        return event;
    }

    public JsonElement serialize(Event event, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("a", event.getName());
        jsonObject.addProperty("b", event.geteInfo1());
        jsonObject.addProperty("c", event.geteInfo2());
        jsonObject.addProperty("d", event.geteInfo3());
        jsonObject.addProperty("e", event.geteInfo4());
        return jsonObject;
    }
}
