package com.inmobi.signals;

import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.p115d.C3110g;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: CarbGetListNetworkResponse */
public class C3261c {
    private static final String f8199a = C3261c.class.getSimpleName();
    private C3143c f8200b;
    private boolean f8201c = true;
    private List<C3262d> f8202d;
    private String f8203e;
    private int f8204f = 0;

    public C3261c(C3143c c3143c) {
        this.f8200b = c3143c;
        this.f8202d = new ArrayList();
        m10916f();
        if (this.f8201c) {
            try {
                C3135c.m10255a().m10279a(new C3110g("signals", "InvalidCarbGetResponse"));
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8199a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
            }
        }
    }

    private void m10916f() {
        if (this.f8200b.m10351a()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8199a, "Error response for Carb list received. Error code:" + this.f8200b.m10355e().m10332a());
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f8200b.m10352b());
            if (jSONObject.getBoolean("success")) {
                jSONObject = jSONObject.getJSONObject("data");
                this.f8203e = jSONObject.getString("req_id");
                JSONArray jSONArray = jSONObject.getJSONArray("p_apps");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("bid", null);
                    String optString2 = jSONObject2.optString("inm_id", null);
                    if (!(optString == null || optString2 == null || optString2.trim().length() <= 0)) {
                        this.f8202d.add(new C3262d(optString, optString2));
                    }
                    this.f8204f = i + 1;
                }
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8199a, "Error response for Carb list received. Error code:" + jSONObject.optInt("errorCode"));
            }
            this.f8201c = false;
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8199a, "Bad response for Carb list received.", e);
        }
    }

    public boolean m10917a() {
        return this.f8201c;
    }

    public List<C3262d> m10918b() {
        return this.f8202d;
    }

    public String m10919c() {
        return this.f8203e;
    }

    public int m10920d() {
        return this.f8204f;
    }

    public boolean m10921e() {
        return this.f8204f == 0;
    }
}
