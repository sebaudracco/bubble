package com.inmobi.signals.activityrecognition;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import org.json.JSONObject;

/* compiled from: ActivityInfo */
public class C3251a {
    private static final String f8174a = C3251a.class.getSimpleName();
    private int f8175b;
    private long f8176c;

    public C3251a(int i, long j) {
        this.f8175b = i;
        this.f8176c = j;
    }

    public JSONObject m10872a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a", this.f8175b);
            jSONObject.put("ts", this.f8176c);
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8174a, "Error while converting WifiInfo to string.", e);
        }
        return jSONObject;
    }
}
