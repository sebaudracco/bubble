package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.yandex.metrica.impl.C4511p.C4510a;
import com.yandex.metrica.impl.ob.C4503t;
import com.yandex.metrica.impl.ob.cw;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C4372h {
    String f11846a;
    String f11847b;
    int f11848c;
    int f11849d;
    int f11850e;
    private C4379a f11851f = new C4379a();
    private String f11852g;
    private String f11853h;
    private String f11854i;
    private Bundle f11855j;
    private int f11856k = 2;
    private String f11857l;

    private static final class C4379a {
        Location f11868a;
        String f11869b;
        Integer f11870c;

        private C4379a() {
        }
    }

    public C4372h(C4372h c4372h) {
        if (c4372h != null) {
            this.f11846a = c4372h.m15058a();
            this.f11847b = c4372h.m15061b();
            this.f11848c = c4372h.m15062c();
            this.f11849d = c4372h.m15065d();
            this.f11852g = c4372h.m15077k();
            this.f11854i = c4372h.m15078l();
            this.f11853h = c4372h.m15075i();
            this.f11851f.f11868a = c4372h.m15068e();
            this.f11851f.f11869b = c4372h.m15071f();
            this.f11851f.f11870c = c4372h.m15074h();
            this.f11855j = c4372h.m15076j();
            this.f11850e = c4372h.m15081o();
            this.f11856k = c4372h.m15082p();
            this.f11857l = c4372h.m15083q();
        }
    }

    public C4372h(String str, String str2, int i) {
        this.f11846a = str2;
        this.f11848c = i;
        this.f11847b = str;
    }

    public String m15058a() {
        return this.f11846a;
    }

    public C4372h mo7031b(String str) {
        this.f11846a = str;
        return this;
    }

    public String m15061b() {
        return this.f11847b;
    }

    public C4372h mo7032c(String str) {
        this.f11847b = str;
        return this;
    }

    public int m15062c() {
        return this.f11848c;
    }

    public C4372h m15053a(int i) {
        this.f11848c = i;
        return this;
    }

    public int m15065d() {
        return this.f11849d;
    }

    public C4372h m15059b(int i) {
        this.f11849d = i;
        return this;
    }

    public Location m15068e() {
        return this.f11851f.f11868a;
    }

    C4372h m15054a(Location location) {
        this.f11851f.f11868a = location;
        return this;
    }

    String m15071f() {
        return this.f11851f.f11869b;
    }

    JSONArray m15073g() {
        try {
            return new JSONArray(this.f11851f.f11869b);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    C4372h m15067d(String str) {
        this.f11851f.f11869b = str;
        return this;
    }

    Integer m15074h() {
        return this.f11851f.f11870c;
    }

    C4372h m15055a(Integer num) {
        this.f11851f.f11870c = num;
        return this;
    }

    String m15075i() {
        return this.f11853h;
    }

    public Bundle m15076j() {
        return this.f11855j;
    }

    C4372h m15069e(String str) {
        this.f11853h = str;
        return this;
    }

    C4372h m15057a(String str, String str2) {
        if (this.f11855j == null) {
            this.f11855j = new Bundle();
        }
        this.f11855j.putString(str, str2);
        return this;
    }

    public String m15077k() {
        return this.f11852g;
    }

    public C4372h mo7030a(String str) {
        this.f11852g = str;
        return this;
    }

    public String m15078l() {
        return this.f11854i;
    }

    public C4372h m15070f(String str) {
        this.f11854i = str;
        return this;
    }

    protected C4372h m15063c(int i) {
        this.f11850e = i;
        return this;
    }

    protected C4372h m15066d(int i) {
        this.f11856k = i;
        return this;
    }

    protected C4372h m15072g(String str) {
        this.f11857l = str;
        return this;
    }

    public boolean m15079m() {
        return this.f11846a == null;
    }

    public boolean m15080n() {
        return C4510a.EVENT_TYPE_UNDEFINED.m16188a() == this.f11848c;
    }

    public int m15081o() {
        return this.f11850e;
    }

    public int m15082p() {
        return this.f11856k;
    }

    public String m15083q() {
        return this.f11857l;
    }

    Bundle m15052a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("CounterReport.Event", this.f11846a);
        bundle2.putString("CounterReport.Value", this.f11847b);
        bundle2.putInt("CounterReport.Type", this.f11848c);
        bundle2.putInt("CounterReport.CustomType", this.f11849d);
        bundle2.putString("CounterReport.Wifi", this.f11851f.f11869b);
        bundle2.putByteArray("CounterReport.GeoLocation", C4543y.m16308b(this.f11851f.f11868a));
        bundle2.putInt("CounterReport.TRUNCATED", this.f11850e);
        bundle2.putInt("CounterReport.ConnectionType", this.f11856k);
        bundle2.putString("CounterReport.CellularConnectionType", this.f11857l);
        if (this.f11851f.f11870c != null) {
            bundle2.putInt("CounterReport.CellId", this.f11851f.f11870c.intValue());
        }
        if (this.f11853h != null) {
            bundle2.putString("CounterReport.Environment", this.f11853h);
        }
        if (this.f11852g != null) {
            bundle2.putString("CounterReport.UserInfo", this.f11852g);
        }
        if (this.f11854i != null) {
            bundle2.putString("CounterReport.PackageName", this.f11854i);
        }
        if (this.f11855j != null) {
            bundle2.putBundle("CounterReport.AppEnvironmentDiff", this.f11855j);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBundle("CounterReport.Object", bundle2);
        return bundle;
    }

    public static C4372h m15051b(Bundle bundle) {
        int i = 0;
        Bundle bundle2 = bundle.containsKey("CounterReport.Object") ? bundle.getBundle("CounterReport.Object") : new Bundle();
        Object obj = bundle2.get("CounterReport.TRUNCATED");
        if (obj != null) {
            if (obj instanceof Boolean) {
                int i2;
                if (((Boolean) obj).booleanValue()) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                i = i2;
            } else if (obj instanceof Integer) {
                i = ((Integer) obj).intValue();
            }
        }
        C4372h f = new C4372h().m15053a(bundle2.getInt("CounterReport.Type", C4510a.EVENT_TYPE_UNDEFINED.m16188a())).m15059b(bundle2.getInt("CounterReport.CustomType")).m15054a(C4543y.m16304a(bundle2.getByteArray("CounterReport.GeoLocation"))).mo7032c(bi.m14961b(bundle2.getString("CounterReport.Value"), "")).mo7030a(bundle2.getString("CounterReport.UserInfo")).m15069e(bundle2.getString("CounterReport.Environment")).m15067d(bundle2.getString("CounterReport.Wifi")).m15055a((Integer) bundle2.get("CounterReport.CellId")).mo7031b(bundle2.getString("CounterReport.Event")).m15070f(bundle2.getString("CounterReport.PackageName"));
        f.f11855j = bundle2.getBundle("CounterReport.AppEnvironmentDiff");
        return f.m15063c(i).m15066d(bundle2.getInt("CounterReport.ConnectionType")).m15072g(bundle2.getString("CounterReport.CellularConnectionType"));
    }

    public static C4372h m15048a(C4372h c4372h, C4510a c4510a) {
        C4372h c4372h2 = new C4372h(c4372h);
        c4372h2.mo7031b(c4510a.m16189b());
        c4372h2.m15053a(c4510a.m16188a());
        return c4372h2;
    }

    public static C4372h m15050a(C4503t c4503t, C4372h c4372h) {
        Context m = c4503t.mo7114m();
        C4515t a = new C4515t(c4372h.m15061b()).m16228a();
        try {
            if (c4503t.m16138G()) {
                a.m16229a(m);
            }
            if (c4503t.mo7111h().m14800H()) {
                a.m16230a(m, c4503t.mo7111h().m14801I());
            }
        } catch (Exception e) {
        }
        C4372h c4372h2 = new C4372h(c4372h);
        c4372h2.m15053a(C4510a.EVENT_TYPE_IDENTITY.m16188a()).mo7032c(a.m16233d());
        return c4372h2;
    }

    public static C4372h m15049a(C4372h c4372h, List<cw> list) {
        String jSONObject;
        C4372h c4372h2 = new C4372h(c4372h);
        String str = "";
        try {
            JSONArray jSONArray = new JSONArray();
            for (cw cwVar : list) {
                jSONArray.put(new JSONObject().put("name", cwVar.m15620b()).put("granted", cwVar.m15619a()));
            }
            jSONObject = new JSONObject().put("permissions", jSONArray).toString();
        } catch (JSONException e) {
            jSONObject = str;
        }
        return c4372h2.m15053a(C4510a.EVENT_TYPE_PERMISSIONS.m16188a()).mo7032c(jSONObject);
    }

    public String toString() {
        return String.format(Locale.US, "[event: %s, type: %d, value: %s]", new Object[]{this.f11846a, Integer.valueOf(this.f11848c), this.f11847b});
    }
}
