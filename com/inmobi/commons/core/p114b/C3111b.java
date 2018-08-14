package com.inmobi.commons.core.p114b;

import android.util.Log;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CrashEvent */
public class C3111b extends C3110g {
    private static final String f7616a = C3111b.class.getSimpleName();

    public C3111b(Thread thread, Throwable th) {
        super("crashReporting", "CrashEvent");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", th.getClass().getSimpleName());
            jSONObject.put("message", th.getMessage());
            jSONObject.put("stack", Log.getStackTraceString(th));
            jSONObject.put("thread", thread.getName());
            m10122a(jSONObject.toString());
        } catch (JSONException e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7616a, "JSONException: " + e);
        }
    }
}
