package com.appnext.base.receivers;

import android.content.Context;
import android.content.Intent;
import com.appnext.base.C1061b;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class C1080b extends C1079a {
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    protected String m2251a(Boolean bool, String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("connected", bool);
            jSONObject.put("name", str);
            jSONArray.put(jSONObject);
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return jSONArray.toString();
    }
}
