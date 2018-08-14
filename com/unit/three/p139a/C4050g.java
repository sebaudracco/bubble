package com.unit.three.p139a;

import android.util.Log;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C4050g {
    private static C4050g f9357a;
    private HashMap f9358b = new HashMap();
    private HashMap f9359c = new HashMap();

    private C4050g() {
    }

    public static C4050g m12495a() {
        if (f9357a == null) {
            synchronized (C4050g.class) {
                if (f9357a == null) {
                    f9357a = new C4050g();
                }
            }
        }
        return f9357a;
    }

    public final void m12496a(String str) {
        Log.i("WebSiteDataCollector", "addStartTime:" + str);
        synchronized ((str + "|").intern()) {
            this.f9359c.put(str, Long.valueOf(System.currentTimeMillis()));
        }
    }

    public final void m12497b() {
        this.f9358b.clear();
    }

    public final void m12498b(String str) {
        Log.i("WebSiteDataCollector", "addEndTime:" + str);
        synchronized ((str + "|").intern()) {
            if (this.f9359c.containsKey(str)) {
                long currentTimeMillis = System.currentTimeMillis() - ((Long) this.f9359c.get(str)).longValue();
                long longValue = this.f9358b.containsKey(str) ? ((Long) this.f9358b.get(str)).longValue() : 0;
                this.f9358b.put(str, Long.valueOf(longValue == 0 ? currentTimeMillis : (long) (((float) (longValue + currentTimeMillis)) / 2.0f)));
                this.f9359c.remove(str);
            }
        }
    }

    public final byte[] m12499c() {
        JSONArray jSONArray = new JSONArray();
        for (Entry entry : this.f9358b.entrySet()) {
            try {
                jSONArray.put(new JSONObject().put("ip", entry.getKey()).put("delay", entry.getValue()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray.toString().getBytes();
    }
}
