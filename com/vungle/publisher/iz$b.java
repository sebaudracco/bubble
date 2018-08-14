package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: vungle */
public class iz$b extends vs {
    private final JSONObject f10509a = new JSONObject();

    public /* synthetic */ Object mo6940b() throws JSONException {
        return mo6941a();
    }

    public iz$b(List<iz> list) {
        try {
            for (iz izVar : list) {
                this.f10509a.put(izVar.e(), izVar.h());
            }
        } catch (Throwable e) {
            Logger.m13639e(Logger.PROTOCOL_TAG, "could not parse json", e);
        }
    }

    public JSONObject mo6941a() throws JSONException {
        return this.f10509a;
    }
}
