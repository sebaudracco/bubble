package com.inmobi.signals;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3164f;
import com.inmobi.signals.activityrecognition.C3251a;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: IceSample */
public class C3274l {
    private static final String f8232a = C3274l.class.getSimpleName();
    private long f8233b;
    private Map<String, Object> f8234c;
    private C3164f f8235d;
    private List<C3275m> f8236e;
    private List<C3251a> f8237f;

    public C3274l() {
        m10966a(Calendar.getInstance().getTimeInMillis());
    }

    public void m10966a(long j) {
        this.f8233b = j;
    }

    public void m10969a(Map<String, Object> map) {
        this.f8234c = map;
    }

    public void m10967a(C3164f c3164f) {
        this.f8235d = c3164f;
    }

    public void m10968a(List<C3275m> list) {
        this.f8236e = list;
    }

    public void m10970b(List<C3251a> list) {
        this.f8237f = list;
    }

    public JSONObject m10965a() {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ts", this.f8233b);
            if (!(this.f8234c == null || this.f8234c.isEmpty())) {
                jSONObject.put("l", new JSONObject(this.f8234c));
            }
            if (this.f8235d != null) {
                jSONObject.put("s", this.f8235d.m10492b());
            }
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.f8236e.size(); i2++) {
                jSONArray.put(((C3275m) this.f8236e.get(i2)).m10976b());
            }
            if (jSONArray.length() > 0) {
                jSONObject.put("w", jSONArray);
            }
            if (this.f8237f != null) {
                JSONArray jSONArray2 = new JSONArray();
                while (i < this.f8237f.size()) {
                    jSONArray2.put(((C3251a) this.f8237f.get(i)).m10872a());
                    i++;
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("ar", jSONArray2);
                }
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f8232a, "Error while converting IceSample to string.", e);
        }
        return jSONObject;
    }
}
