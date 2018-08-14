package com.vungle.publisher;

import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class mc extends vs {
    public /* synthetic */ Object m4358b() throws JSONException {
        return m4357a();
    }

    public JSONObject m4357a() throws JSONException {
        JSONObject a = super.a();
        a.put("privacyPolicyEnabled", true);
        return a;
    }
}
