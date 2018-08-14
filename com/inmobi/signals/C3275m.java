package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.signals.p120b.C3254a;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: IceWifiSample */
public class C3275m {
    private static final String f8238a = C3275m.class.getSimpleName();
    private long f8239b;
    private C3254a f8240c;
    private List<C3254a> f8241d;
    private Map<String, String> f8242e;

    public C3275m() {
        m10971a(Calendar.getInstance().getTimeInMillis());
    }

    public void m10971a(long j) {
        this.f8239b = j;
    }

    public void m10974a(Map<String, String> map) {
        this.f8242e = map;
    }

    public void m10972a(C3254a c3254a) {
        this.f8240c = c3254a;
    }

    public void m10973a(List<C3254a> list) {
        this.f8241d = list;
    }

    public boolean m10975a() {
        return (this.f8240c == null && this.f8241d == null) ? false : true;
    }

    public JSONObject m10976b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.f8239b);
            if (!(this.f8242e == null || this.f8242e.isEmpty())) {
                for (Entry entry : this.f8242e.entrySet()) {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                }
            }
            if (this.f8240c != null) {
                jSONObject.put("c-ap", this.f8240c.m10886b());
            }
            JSONArray jSONArray = new JSONArray();
            if (this.f8241d != null) {
                for (int i = 0; i < this.f8241d.size(); i++) {
                    jSONArray.put(((C3254a) this.f8241d.get(i)).m10886b());
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("v-ap", jSONArray);
                }
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8238a, "Error while converting IceWifiCellSample to string.", e);
        }
        return jSONObject;
    }
}
