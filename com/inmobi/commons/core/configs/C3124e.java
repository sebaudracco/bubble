package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.commons.p112a.C3105a;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ConfigNetworkRequest */
final class C3124e extends NetworkRequest {
    private static final String f7663d = C3124e.class.getSimpleName();
    private static String f7664h = "http://config.inmobi.com/config-server/v1/config/secure.cfg";
    private int f7665e;
    private int f7666f;
    private Map<String, C3045a> f7667g;

    public C3124e(Map<String, C3045a> map, C3169d c3169d, String str, int i, int i2) {
        RequestType requestType = RequestType.POST;
        if (str == null || str.trim().length() == 0) {
            str = f7664h;
        }
        super(requestType, str, true, c3169d);
        this.f7667g = map;
        this.f7665e = i;
        this.f7666f = i2;
    }

    public void mo6238a() {
        super.mo6238a();
        this.c.put("p", m10201e());
        this.c.put("im-accid", C3105a.m10081c());
    }

    private String m10201e() {
        C3122c c3122c = new C3122c();
        String str = "";
        try {
            JSONArray jSONArray = new JSONArray();
            for (Entry entry : this.f7667g.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("n", entry.getKey());
                jSONObject.put("t", c3122c.m10198b((String) entry.getKey()));
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f7663d, "Problem while creating config network request's payload.", e);
            return str;
        }
    }

    public Map<String, C3045a> m10204b() {
        return this.f7667g;
    }

    public int m10205c() {
        return this.f7665e;
    }

    public int m10206d() {
        return this.f7666f;
    }

    public void mo6239a(String str) {
        this.f7667g.remove(str);
    }
}
