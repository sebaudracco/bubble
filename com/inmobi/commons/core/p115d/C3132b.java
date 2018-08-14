package com.inmobi.commons.core.p115d;

import android.util.Log;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TelemetryCatchEvent */
public class C3132b extends C3110g {
    private static final String f7689a = C3110g.class.getSimpleName();

    public C3132b(Throwable th) {
        super("crashReporting", "catchEvent");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", th.getClass().getSimpleName());
            jSONObject.put("message", th.getMessage());
            jSONObject.put("stack", Log.getStackTraceString(th));
            jSONObject.put("thread", Thread.currentThread().getName());
            m10122a(jSONObject.toString());
        } catch (JSONException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7689a, "JSONException: " + e);
        }
    }
}
