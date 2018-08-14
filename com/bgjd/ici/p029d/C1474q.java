package com.bgjd.ici.p029d;

import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1409k;

public class C1474q extends C1409k<JSONObject> {
    private static final String f2340f = "MKTPLGINF";

    public /* synthetic */ Object mo2325d() {
        return m3061f();
    }

    public C1474q(C1395h c1395h) {
        super(c1395h);
        this.b = "sdk-plugin";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bgjd.ici.json.JSONObject m3061f() {
        /*
        r18 = this;
        r4 = new com.bgjd.ici.json.JSONObject;
        r4.<init>();
        r5 = new com.bgjd.ici.json.JSONArray;
        r5.<init>();
        r0 = r18;
        r2 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r6 = r2.mo2281q();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r6 == 0) goto L_0x009f;
    L_0x0014:
        r2 = r6.length();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r2 <= 0) goto L_0x009f;
    L_0x001a:
        r0 = r18;
        r2 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r2 = r2.getExtensionId();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r0 = r18;
        r3 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r3 = r3.mo2180H();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r7 = r3.toLowerCase();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r0 = r18;
        r3 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r3 = r3.mo2275n(r7);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r3 <= 0) goto L_0x0108;
    L_0x0038:
        if (r3 == r2) goto L_0x0108;
    L_0x003a:
        r3 = com.bgjd.ici.plugin.C1532j.m3310b();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r8 = r3.mo2358a();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r2 = r8.keySet();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r9 = r2.iterator();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
    L_0x004a:
        r2 = r9.hasNext();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r2 == 0) goto L_0x0105;
    L_0x0050:
        r2 = r9.next();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10 = r8.get(r2);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r11 = r10.getClass();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = com.bgjd.ici.p030e.C1485h.C1484a.C1483a.m3107c();	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r13 = 0;
        r13 = new java.lang.Class[r13];	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r11 = r11.getMethod(r12, r13);	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r12 = 0;
        r12 = new java.lang.Object[r12];	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r11.invoke(r10, r12);	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r3.mo2362d(r2);	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r0 = r18;
        r10 = r0.a;	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        r12 = 0;
        r10.mo2206a(r2, r12);	 Catch:{ NoSuchMethodException -> 0x007c, IllegalAccessException -> 0x00ba, IllegalArgumentException -> 0x00df, InvocationTargetException -> 0x00f2 }
        goto L_0x004a;
    L_0x007c:
        r2 = move-exception;
        r10 = "MKTPLGINF";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = 0;
        r13 = r2.getMessage();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r11[r12] = r13;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        com.bgjd.ici.p025b.C1402i.m2898a(r10, r2, r11);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        goto L_0x004a;
    L_0x008e:
        r2 = move-exception;
        r3 = "MKTPLGINF";
        r6 = 1;
        r6 = new java.lang.String[r6];
        r7 = 0;
        r8 = r2.getMessage();
        r6[r7] = r8;
        com.bgjd.ici.p025b.C1402i.m2898a(r3, r2, r6);
    L_0x009f:
        r2 = "a";
        r4.put(r2, r5);	 Catch:{ JSONException -> 0x0300 }
        r2 = "b";
        r3 = r5.length();	 Catch:{ JSONException -> 0x0300 }
        r4.put(r2, r3);	 Catch:{ JSONException -> 0x0300 }
        r2 = "c";
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x0300 }
        r4.put(r2, r6);	 Catch:{ JSONException -> 0x0300 }
    L_0x00b9:
        return r4;
    L_0x00ba:
        r2 = move-exception;
        r10 = "MKTPLGINF";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = 0;
        r13 = r2.getMessage();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r11[r12] = r13;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        com.bgjd.ici.p025b.C1402i.m2898a(r10, r2, r11);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        goto L_0x004a;
    L_0x00cd:
        r2 = move-exception;
        r3 = "MKTPLGINF";
        r6 = 1;
        r6 = new java.lang.String[r6];
        r7 = 0;
        r8 = r2.getMessage();
        r6[r7] = r8;
        com.bgjd.ici.p025b.C1402i.m2898a(r3, r2, r6);
        goto L_0x009f;
    L_0x00df:
        r2 = move-exception;
        r10 = "MKTPLGINF";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = 0;
        r13 = r2.getMessage();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r11[r12] = r13;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        com.bgjd.ici.p025b.C1402i.m2898a(r10, r2, r11);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        goto L_0x004a;
    L_0x00f2:
        r2 = move-exception;
        r10 = "MKTPLGINF";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = 0;
        r13 = r2.getMessage();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r11[r12] = r13;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        com.bgjd.ici.p025b.C1402i.m2898a(r10, r2, r11);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        goto L_0x004a;
    L_0x0105:
        r2 = 0;
        com.bgjd.ici.MarketBeaconService.f2015a = r2;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
    L_0x0108:
        r2 = 0;
        r3 = r2;
    L_0x010a:
        r2 = r6.length();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r3 >= r2) goto L_0x009f;
    L_0x0110:
        r2 = r6.getJSONObject(r3);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r2 == 0) goto L_0x01fd;
    L_0x0116:
        r8 = "name";
        r8 = r2.has(r8);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r8 == 0) goto L_0x01fd;
    L_0x011f:
        r8 = "class";
        r8 = r2.has(r8);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r8 == 0) goto L_0x01fd;
    L_0x0128:
        r8 = "minsdk";
        r8 = r2.has(r8);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r8 == 0) goto L_0x01fd;
    L_0x0131:
        r8 = new com.bgjd.ici.e.h;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r8.<init>(r2);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r9 = new com.bgjd.ici.json.JSONObject;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r9.<init>();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10 = new com.bgjd.ici.plugin.f;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r0 = r18;
        r2 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10.<init>(r7, r8, r2);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r11 = r8.m3130c();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r2 < r11) goto L_0x01fd;
    L_0x014c:
        r2 = r8.m3126a();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        if (r2 == 0) goto L_0x01fd;
    L_0x0152:
        r2 = 0;
        r0 = r18;
        r11 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = r8.m3127b();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r12 = r11.mo2233f(r12);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r14 = 0;
        r11 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r11 != 0) goto L_0x0221;
    L_0x0165:
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        if (r10 == 0) goto L_0x0183;
    L_0x016b:
        r11 = r10.mo2351f();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        if (r11 == 0) goto L_0x0183;
    L_0x0171:
        r11 = r10.mo2346a();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        if (r11 == 0) goto L_0x0183;
    L_0x0177:
        r0 = r18;
        r2 = r0.a;	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = r8.m3127b();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2.mo2206a(r11, r12);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2 = 1;
    L_0x0183:
        r11 = "id";
        r14 = r8.m3136f();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r11, r14);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "name";
        r14 = r8.m3127b();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r11, r14);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "version";
        r14 = r8.m3137g();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r11, r14);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "type";
        r14 = r8.m3138h();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r11, r14);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "minsdk";
        r14 = r8.m3130c();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r11, r14);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "timestamp";
        r9.put(r11, r12);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "is_running";
        r12 = r10.mo2353h();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r11, r12);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r11 = "is_started";
        r9.put(r11, r2);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2 = "is_valid";
        r10 = r10.mo2352g();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r9.put(r2, r10);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2 = new com.bgjd.ici.json.JSONObject;	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2.<init>();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r10 = "a";
        r11 = r8.m3137g();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2.put(r10, r11);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r10 = "b";
        r2.put(r10, r7);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r10 = "c";
        r11 = r8.m3136f();	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r2.put(r10, r11);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r10 = "d";
        r2.put(r10, r9);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
        r5.put(r2);	 Catch:{ Exception -> 0x0202, JSONException -> 0x008e }
    L_0x01fd:
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x010a;
    L_0x0202:
        r2 = move-exception;
        r0 = r18;
        r9 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r8 = r8.m3127b();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10 = 0;
        r9.mo2206a(r8, r10);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r8 = "MKTPLGINF";
        r9 = 1;
        r9 = new java.lang.String[r9];	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10 = 0;
        r11 = r2.getMessage();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r9[r10] = r11;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        com.bgjd.ici.p025b.C1402i.m2898a(r8, r2, r9);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        goto L_0x01fd;
    L_0x0221:
        r14 = 0;
        r2 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1));
        if (r2 <= 0) goto L_0x01fd;
    L_0x0227:
        r2 = r8.m3135e();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        if (r2 <= 0) goto L_0x01fd;
    L_0x022d:
        r2 = java.util.Calendar.getInstance();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r14 = r2.getTimeInMillis();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r14 = r14 - r12;
        r16 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r14 = r14 / r16;
        r2 = r8.m3135e();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r0 = (long) r2;	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r16 = r0;
        r2 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1));
        if (r2 < 0) goto L_0x0277;
    L_0x0246:
        if (r10 == 0) goto L_0x01fd;
    L_0x0248:
        r2 = r10.mo2351f();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        if (r2 == 0) goto L_0x01fd;
    L_0x024e:
        r2 = r10.mo2353h();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        if (r2 == 0) goto L_0x01fd;
    L_0x0254:
        r10.mo2348c();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        goto L_0x01fd;
    L_0x0258:
        r2 = move-exception;
        r0 = r18;
        r9 = r0.a;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r8 = r8.m3127b();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10 = 0;
        r9.mo2206a(r8, r10);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r8 = "MKTPLGINF";
        r9 = 1;
        r9 = new java.lang.String[r9];	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r10 = 0;
        r11 = r2.getMessage();	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        r9[r10] = r11;	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        com.bgjd.ici.p025b.C1402i.m2898a(r8, r2, r9);	 Catch:{ JSONException -> 0x008e, Exception -> 0x00cd }
        goto L_0x01fd;
    L_0x0277:
        r2 = r10.mo2351f();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        if (r2 == 0) goto L_0x01fd;
    L_0x027d:
        r2 = r10.mo2353h();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        if (r2 == 0) goto L_0x01fd;
    L_0x0283:
        r2 = "id";
        r11 = r8.m3136f();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "name";
        r11 = r8.m3127b();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "version";
        r11 = r8.m3137g();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "type";
        r11 = r8.m3138h();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "minsdk";
        r11 = r8.m3130c();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "timestamp";
        r9.put(r2, r12);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "is_running";
        r11 = r10.mo2353h();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "is_started";
        r11 = 1;
        r9.put(r2, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = "is_valid";
        r10 = r10.mo2352g();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r9.put(r2, r10);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2 = new com.bgjd.ici.json.JSONObject;	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2.<init>();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r10 = "a";
        r11 = r8.m3137g();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2.put(r10, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r10 = "b";
        r2.put(r10, r7);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r10 = "c";
        r11 = r8.m3136f();	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r2.put(r10, r11);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r10 = "d";
        r2.put(r10, r9);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        r5.put(r2);	 Catch:{ Exception -> 0x0258, JSONException -> 0x008e }
        goto L_0x01fd;
    L_0x0300:
        r2 = move-exception;
        r3 = "MKTPLGINF";
        r5 = 1;
        r5 = new java.lang.String[r5];
        r6 = 0;
        r7 = r2.getMessage();
        r5[r6] = r7;
        com.bgjd.ici.p025b.C1402i.m2898a(r3, r2, r5);
        goto L_0x00b9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.d.q.f():com.bgjd.ici.json.JSONObject");
    }
}
