package com.facebook.ads.internal.p065h;

import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class C1990d {
    private static final String f4653c = C1990d.class.getSimpleName();
    private static final AdPlacementType f4654d = AdPlacementType.UNKNOWN;
    public int f4655a = -1;
    public int f4656b = -1;
    private final long f4657e = System.currentTimeMillis();
    private AdPlacementType f4658f = f4654d;
    private int f4659g = 1;
    private int f4660h = 0;
    private int f4661i = 0;
    private int f4662j = 20;
    private int f4663k = 0;
    private int f4664l = 1000;
    private int f4665m = 10000;
    private boolean f4666n = false;
    private List<C1988b> f4667o = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private C1990d(java.util.Map<java.lang.String, java.lang.String> r11) {
        /*
        r10 = this;
        r4 = 1;
        r3 = -1;
        r2 = 0;
        r10.<init>();
        r10.f4655a = r3;
        r10.f4656b = r3;
        r0 = f4654d;
        r10.f4658f = r0;
        r10.f4659g = r4;
        r10.f4660h = r2;
        r10.f4661i = r2;
        r0 = 20;
        r10.f4662j = r0;
        r10.f4663k = r2;
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10.f4664l = r0;
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r10.f4665m = r0;
        r10.f4666n = r2;
        r0 = 0;
        r10.f4667o = r0;
        r0 = java.lang.System.currentTimeMillis();
        r10.f4657e = r0;
        r0 = r11.entrySet();
        r5 = r0.iterator();
    L_0x0035:
        r0 = r5.hasNext();
        if (r0 == 0) goto L_0x020c;
    L_0x003b:
        r0 = r5.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (java.lang.String) r1;
        r6 = r1.hashCode();
        switch(r6) {
            case -1899431321: goto L_0x00dd;
            case -1561601017: goto L_0x008c;
            case -856794442: goto L_0x00c4;
            case -726276175: goto L_0x00d0;
            case -553208868: goto L_0x0097;
            case 3575610: goto L_0x0060;
            case 700812481: goto L_0x006b;
            case 858630459: goto L_0x0076;
            case 1085444827: goto L_0x0081;
            case 1183549815: goto L_0x00b8;
            case 1503616961: goto L_0x00ad;
            case 2002133996: goto L_0x00a2;
            default: goto L_0x004e;
        };
    L_0x004e:
        r1 = r3;
    L_0x004f:
        switch(r1) {
            case 0: goto L_0x0053;
            case 1: goto L_0x00ea;
            case 2: goto L_0x00f8;
            case 3: goto L_0x0106;
            case 4: goto L_0x0114;
            case 5: goto L_0x0122;
            case 6: goto L_0x0134;
            case 7: goto L_0x0142;
            case 8: goto L_0x0150;
            case 9: goto L_0x015e;
            case 10: goto L_0x016c;
            case 11: goto L_0x017a;
            default: goto L_0x0052;
        };
    L_0x0052:
        goto L_0x0035;
    L_0x0053:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = com.facebook.ads.internal.protocol.AdPlacementType.fromString(r0);
        r10.f4658f = r0;
        goto L_0x0035;
    L_0x0060:
        r6 = "type";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x0069:
        r1 = r2;
        goto L_0x004f;
    L_0x006b:
        r6 = "min_viewability_percentage";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x0074:
        r1 = r4;
        goto L_0x004f;
    L_0x0076:
        r6 = "viewability_check_ticker";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x007f:
        r1 = 2;
        goto L_0x004f;
    L_0x0081:
        r6 = "refresh";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x008a:
        r1 = 3;
        goto L_0x004f;
    L_0x008c:
        r6 = "refresh_threshold";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x0095:
        r1 = 4;
        goto L_0x004f;
    L_0x0097:
        r6 = "cacheable";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00a0:
        r1 = 5;
        goto L_0x004f;
    L_0x00a2:
        r6 = "placement_width";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00ab:
        r1 = 6;
        goto L_0x004f;
    L_0x00ad:
        r6 = "placement_height";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00b6:
        r1 = 7;
        goto L_0x004f;
    L_0x00b8:
        r6 = "viewability_check_initial_delay";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00c1:
        r1 = 8;
        goto L_0x004f;
    L_0x00c4:
        r6 = "viewability_check_interval";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00cd:
        r1 = 9;
        goto L_0x004f;
    L_0x00d0:
        r6 = "request_timeout";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00d9:
        r1 = 10;
        goto L_0x004f;
    L_0x00dd:
        r6 = "conv_tracking_data";
        r1 = r1.equals(r6);
        if (r1 == 0) goto L_0x004e;
    L_0x00e6:
        r1 = 11;
        goto L_0x004f;
    L_0x00ea:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4659g = r0;
        goto L_0x0035;
    L_0x00f8:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4660h = r0;
        goto L_0x0035;
    L_0x0106:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4661i = r0;
        goto L_0x0035;
    L_0x0114:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4662j = r0;
        goto L_0x0035;
    L_0x0122:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Boolean.valueOf(r0);
        r0 = r0.booleanValue();
        r10.f4666n = r0;
        goto L_0x0035;
    L_0x0134:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4655a = r0;
        goto L_0x0035;
    L_0x0142:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4656b = r0;
        goto L_0x0035;
    L_0x0150:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4663k = r0;
        goto L_0x0035;
    L_0x015e:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4664l = r0;
        goto L_0x0035;
    L_0x016c:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = java.lang.Integer.parseInt(r0);
        r10.f4665m = r0;
        goto L_0x0035;
    L_0x017a:
        r0 = r0.getValue();
        r0 = (java.lang.String) r0;
        r0 = com.facebook.ads.internal.p065h.C1988b.m6283a(r0);
        r10.f4667o = r0;
        r1 = android.webkit.CookieManager.getInstance();	 Catch:{ Exception -> 0x01ef }
        r6 = r1.acceptCookie();	 Catch:{ Exception -> 0x01ef }
        r0 = 1;
        r1.setAcceptCookie(r0);	 Catch:{ Exception -> 0x01ef }
        r0 = r10.f4667o;	 Catch:{ Exception -> 0x01ef }
        r7 = r0.iterator();	 Catch:{ Exception -> 0x01ef }
    L_0x0198:
        r0 = r7.hasNext();	 Catch:{ Exception -> 0x01ef }
        if (r0 == 0) goto L_0x01fa;
    L_0x019e:
        r0 = r7.next();	 Catch:{ Exception -> 0x01ef }
        r0 = (com.facebook.ads.internal.p065h.C1988b) r0;	 Catch:{ Exception -> 0x01ef }
        r8 = r0.m6285b();	 Catch:{ Exception -> 0x01ef }
        if (r8 == 0) goto L_0x0198;
    L_0x01aa:
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01ef }
        r8.<init>();	 Catch:{ Exception -> 0x01ef }
        r9 = r0.f4645b;	 Catch:{ Exception -> 0x01ef }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = "=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = r0.f4646c;	 Catch:{ Exception -> 0x01ef }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = ";Domain=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = r0.f4644a;	 Catch:{ Exception -> 0x01ef }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = ";Expires=";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = r0.m6284a();	 Catch:{ Exception -> 0x01ef }
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r9 = ";path=/";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x01ef }
        r8 = r8.toString();	 Catch:{ Exception -> 0x01ef }
        r0 = r0.f4644a;	 Catch:{ Exception -> 0x01ef }
        r1.setCookie(r0, r8);	 Catch:{ Exception -> 0x01ef }
        goto L_0x0198;
    L_0x01ef:
        r0 = move-exception;
        r1 = f4653c;
        r6 = "Failed to set cookie.";
        android.util.Log.w(r1, r6, r0);
        goto L_0x0035;
    L_0x01fa:
        r0 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01ef }
        r7 = 21;
        if (r0 >= r7) goto L_0x0207;
    L_0x0200:
        r0 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ Exception -> 0x01ef }
        r0.startSync();	 Catch:{ Exception -> 0x01ef }
    L_0x0207:
        r1.setAcceptCookie(r6);	 Catch:{ Exception -> 0x01ef }
        goto L_0x0035;
    L_0x020c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.h.d.<init>(java.util.Map):void");
    }

    public static C1990d m6291a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Iterator keys = jSONObject.keys();
        Map hashMap = new HashMap();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, String.valueOf(jSONObject.opt(str)));
        }
        return new C1990d(hashMap);
    }

    public long m6292a() {
        return this.f4657e;
    }

    public AdPlacementType m6293b() {
        return this.f4658f;
    }

    public long m6294c() {
        return (long) (this.f4661i * 1000);
    }

    public long m6295d() {
        return (long) (this.f4662j * 1000);
    }

    public boolean m6296e() {
        return this.f4666n;
    }

    public int m6297f() {
        return this.f4659g;
    }

    public int m6298g() {
        return this.f4660h;
    }

    public int m6299h() {
        return this.f4663k;
    }

    public int m6300i() {
        return this.f4664l;
    }

    public int m6301j() {
        return this.f4665m;
    }
}
