package com.integralads.avid.library.inmobi.session.internal;

import android.content.Context;
import com.integralads.avid.library.inmobi.C3289b;
import com.integralads.avid.library.inmobi.session.C3329g;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: InternalAvidAdSessionContext */
public class C3334b {
    private String f8490a;
    private C3329g f8491b;
    private String f8492c;
    private String f8493d;

    public C3334b(Context context, String str, String str2, String str3, C3329g c3329g) {
        C3289b.m11196a().m11197a(context);
        this.f8490a = str;
        this.f8491b = c3329g;
        this.f8492c = str2;
        this.f8493d = str3;
    }

    public String m11419a() {
        return this.f8490a;
    }

    public JSONObject m11420b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("avidAdSessionId", this.f8490a);
            jSONObject.put("bundleIdentifier", C3289b.m11196a().m11198b());
            jSONObject.put("partner", C3289b.m11196a().m11200d());
            jSONObject.put("partnerVersion", this.f8491b.m11369a());
            jSONObject.put("avidLibraryVersion", C3289b.m11196a().m11199c());
            jSONObject.put("avidAdSessionType", this.f8492c);
            jSONObject.put("mediaType", this.f8493d);
            jSONObject.put("isDeferred", this.f8491b.m11370b());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
