package com.inmobi.commons.core.configs;

import com.inmobi.commons.p112a.C3106b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: RootConfig */
public class C3128g extends C3045a {
    private static final Object f7678g = new Object();
    private int f7679a = 3;
    private int f7680b = 60;
    private int f7681c = 3;
    private List<C3126a> f7682d = new ArrayList();
    private C3127b f7683e = new C3127b();
    private JSONObject f7684f = new JSONObject();
    private boolean f7685h = false;

    /* compiled from: RootConfig */
    static final class C3126a {
        private String f7672a;
        private long f7673b;
        private String f7674c;
        private String f7675d;

        C3126a() {
        }

        public String m10223a() {
            return this.f7672a;
        }

        public Long m10224b() {
            return Long.valueOf(this.f7673b);
        }

        public String m10225c() {
            return this.f7674c;
        }

        public String m10226d() {
            return this.f7675d;
        }
    }

    /* compiled from: RootConfig */
    public static final class C3127b {
        private String f7676a = C3106b.m10098c();
        private String f7677b = C3106b.m10102g();

        public String m10231a() {
            return this.f7676a;
        }

        public String m10232b() {
            return this.f7677b;
        }
    }

    public String mo6231a() {
        return "root";
    }

    public JSONObject mo6233b() throws JSONException {
        JSONObject b = super.mo6233b();
        JSONArray jSONArray = new JSONArray();
        b.put("maxRetries", this.f7679a);
        b.put("retryInterval", this.f7680b);
        b.put("waitTime", this.f7681c);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", this.f7683e.f7676a);
        jSONObject.put("url", this.f7683e.f7677b);
        b.put("latestSdkInfo", jSONObject);
        synchronized (f7678g) {
            for (int i = 0; i < this.f7682d.size(); i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", ((C3126a) this.f7682d.get(i)).f7672a);
                jSONObject2.put("expiry", ((C3126a) this.f7682d.get(i)).f7673b);
                jSONObject2.put("protocol", ((C3126a) this.f7682d.get(i)).f7674c);
                jSONObject2.put("url", ((C3126a) this.f7682d.get(i)).f7675d);
                jSONArray.put(jSONObject2);
            }
        }
        b.put("components", jSONArray);
        b.put("monetizationDisabled", this.f7685h);
        return b;
    }

    public void mo6232a(JSONObject jSONObject) throws JSONException {
        super.mo6232a(jSONObject);
        this.f7679a = jSONObject.getInt("maxRetries");
        this.f7680b = jSONObject.getInt("retryInterval");
        this.f7681c = jSONObject.getInt("waitTime");
        JSONObject jSONObject2 = jSONObject.getJSONObject("latestSdkInfo");
        this.f7683e.f7676a = jSONObject2.getString("version");
        this.f7683e.f7677b = jSONObject2.getString("url");
        JSONArray jSONArray = jSONObject.getJSONArray("components");
        synchronized (f7678g) {
            this.f7682d.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                C3126a c3126a = new C3126a();
                c3126a.f7672a = jSONObject3.getString("type");
                c3126a.f7673b = jSONObject3.getLong("expiry");
                c3126a.f7674c = jSONObject3.getString("protocol");
                c3126a.f7675d = jSONObject3.getString("url");
                this.f7682d.add(c3126a);
            }
        }
        this.f7685h = jSONObject.getBoolean("monetizationDisabled");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo6234c() {
        /*
        r8 = this;
        r1 = 0;
        r0 = r8.f7682d;
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        r0 = r1;
    L_0x0006:
        return r0;
    L_0x0007:
        r0 = r8.f7679a;
        if (r0 < 0) goto L_0x0013;
    L_0x000b:
        r0 = r8.f7680b;
        if (r0 < 0) goto L_0x0013;
    L_0x000f:
        r0 = r8.f7681c;
        if (r0 >= 0) goto L_0x0015;
    L_0x0013:
        r0 = r1;
        goto L_0x0006;
    L_0x0015:
        r0 = r8.f7683e;
        r0 = r0.m10231a();
        r0 = r0.trim();
        r0 = r0.length();
        if (r0 == 0) goto L_0x0043;
    L_0x0025:
        r0 = r8.f7683e;
        r0 = r0.m10232b();
        r2 = "http://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0045;
    L_0x0034:
        r0 = r8.f7683e;
        r0 = r0.m10232b();
        r2 = "https://";
        r0 = r0.startsWith(r2);
        if (r0 != 0) goto L_0x0045;
    L_0x0043:
        r0 = r1;
        goto L_0x0006;
    L_0x0045:
        r3 = f7678g;
        monitor-enter(r3);
        r2 = r1;
    L_0x0049:
        r0 = r8.f7682d;	 Catch:{ all -> 0x00d8 }
        r0 = r0.size();	 Catch:{ all -> 0x00d8 }
        if (r2 >= r0) goto L_0x00d4;
    L_0x0051:
        r0 = r8.f7682d;	 Catch:{ all -> 0x00d8 }
        r0 = r0.get(r2);	 Catch:{ all -> 0x00d8 }
        r0 = (com.inmobi.commons.core.configs.C3128g.C3126a) r0;	 Catch:{ all -> 0x00d8 }
        r4 = r0.m10223a();	 Catch:{ all -> 0x00d8 }
        r4 = r4.trim();	 Catch:{ all -> 0x00d8 }
        r4 = r4.length();	 Catch:{ all -> 0x00d8 }
        if (r4 != 0) goto L_0x006a;
    L_0x0067:
        monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0006;
    L_0x006a:
        r4 = r0.m10224b();	 Catch:{ all -> 0x00d8 }
        r4 = r4.longValue();	 Catch:{ all -> 0x00d8 }
        r6 = 0;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 < 0) goto L_0x0087;
    L_0x0078:
        r4 = r0.m10224b();	 Catch:{ all -> 0x00d8 }
        r4 = r4.longValue();	 Catch:{ all -> 0x00d8 }
        r6 = 864000; // 0xd2f00 float:1.210722E-39 double:4.268727E-318;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x008b;
    L_0x0087:
        monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0006;
    L_0x008b:
        r4 = r0.m10225c();	 Catch:{ all -> 0x00d8 }
        r4 = r4.trim();	 Catch:{ all -> 0x00d8 }
        r4 = r4.length();	 Catch:{ all -> 0x00d8 }
        if (r4 != 0) goto L_0x009d;
    L_0x0099:
        monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0006;
    L_0x009d:
        r4 = r0.m10226d();	 Catch:{ all -> 0x00d8 }
        if (r4 == 0) goto L_0x00cb;
    L_0x00a3:
        r4 = r0.m10226d();	 Catch:{ all -> 0x00d8 }
        r4 = r4.trim();	 Catch:{ all -> 0x00d8 }
        r4 = r4.length();	 Catch:{ all -> 0x00d8 }
        if (r4 == 0) goto L_0x00cb;
    L_0x00b1:
        r4 = r0.m10226d();	 Catch:{ all -> 0x00d8 }
        r5 = "http://";
        r4 = r4.startsWith(r5);	 Catch:{ all -> 0x00d8 }
        if (r4 != 0) goto L_0x00cf;
    L_0x00be:
        r0 = r0.m10226d();	 Catch:{ all -> 0x00d8 }
        r4 = "https://";
        r0 = r0.startsWith(r4);	 Catch:{ all -> 0x00d8 }
        if (r0 != 0) goto L_0x00cf;
    L_0x00cb:
        monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
        r0 = r1;
        goto L_0x0006;
    L_0x00cf:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0049;
    L_0x00d4:
        monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
        r0 = 1;
        goto L_0x0006;
    L_0x00d8:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x00d8 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.configs.g.c():boolean");
    }

    public C3045a mo6235d() {
        return new C3128g();
    }

    public long m10233a(String str) {
        long b;
        synchronized (f7678g) {
            for (int i = 0; i < this.f7682d.size(); i++) {
                C3126a c3126a = (C3126a) this.f7682d.get(i);
                if (str.equals(c3126a.f7672a)) {
                    b = c3126a.f7673b;
                    break;
                }
            }
            b = 86400;
        }
        return b;
    }

    public int m10240e() {
        return this.f7679a;
    }

    public int m10241f() {
        return this.f7680b;
    }

    public int m10242g() {
        return this.f7681c;
    }

    public boolean m10243h() {
        return this.f7685h;
    }

    public String m10236b(String str) {
        String d;
        synchronized (f7678g) {
            for (int i = 0; i < this.f7682d.size(); i++) {
                C3126a c3126a = (C3126a) this.f7682d.get(i);
                if (str.equals(c3126a.f7672a)) {
                    d = c3126a.f7675d;
                    break;
                }
            }
            d = "";
        }
        return d;
    }

    public C3127b m10244i() {
        return this.f7683e;
    }

    public JSONObject m10245j() {
        return this.f7684f;
    }
}
