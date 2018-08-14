package com.bgjd.ici.p025b;

import com.bgjd.ici.p025b.C1408j.C1403a;

public class C1411n extends C1397l {
    private static final String f2174b = "MKTLOG";

    public C1411n(C1395h c1395h) {
        super(c1395h);
    }

    public C1411n() {
        super(null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.bgjd.ici.json.JSONObject mo2296a() {
        /*
        r14 = this;
        r0 = new com.bgjd.ici.d.i;
        r1 = r14.a;
        r0.<init>(r1);
        r3 = r0.m3018f();
        r0 = new com.bgjd.ici.json.JSONObject;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0.<init>();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = new com.bgjd.ici.d.j;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4 = new com.bgjd.ici.d.f;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = new com.bgjd.ici.d.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r6 = new com.bgjd.ici.d.r;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r6.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r7 = new com.bgjd.ici.d.g;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r7.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r8 = new com.bgjd.ici.d.h;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r8.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r9 = new com.bgjd.ici.d.n;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r9.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r10 = r2.getSupportedFeatures();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r2.mo2195V();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r2 == 0) goto L_0x0384;
    L_0x004f:
        r0 = new com.bgjd.ici.b.o;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0.<init>(r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.m2916a();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 != 0) goto L_0x0381;
    L_0x005c:
        r0 = new com.bgjd.ici.json.JSONObject;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0.<init>();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2 = r0;
    L_0x0062:
        if (r10 == 0) goto L_0x0080;
    L_0x0064:
        r0 = "package";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x0242, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x0080;
    L_0x006d:
        r0 = "pkg";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x0242, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x0080;
    L_0x0076:
        r0 = "pkg";
        r1 = r1.m3021f();	 Catch:{ Exception -> 0x0242, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x0242, JSONException -> 0x0255 }
    L_0x0080:
        if (r10 == 0) goto L_0x009e;
    L_0x0082:
        r0 = "browsing";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x0267, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x009e;
    L_0x008b:
        r0 = "brw";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x0267, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x009e;
    L_0x0094:
        r0 = "brw";
        r1 = r4.m2997f();	 Catch:{ Exception -> 0x0267, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x0267, JSONException -> 0x0255 }
    L_0x009e:
        if (r10 == 0) goto L_0x00bc;
    L_0x00a0:
        r0 = "email";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x028c, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x00bc;
    L_0x00a9:
        r0 = "act";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x028c, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x00bc;
    L_0x00b2:
        r0 = "act";
        r1 = r5.m2971f();	 Catch:{ Exception -> 0x028c, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x028c, JSONException -> 0x0255 }
    L_0x00bc:
        r0 = "procs";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x029f, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x00cf;
    L_0x00c5:
        r0 = "procs";
        r1 = r6.m3063f();	 Catch:{ Exception -> 0x029f, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x029f, JSONException -> 0x0255 }
    L_0x00cf:
        r0 = "prop";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x02b2, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x00e2;
    L_0x00d8:
        r0 = "prop";
        r1 = r7.m3000f();	 Catch:{ Exception -> 0x02b2, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x02b2, JSONException -> 0x0255 }
    L_0x00e2:
        r0 = "beac";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x011e;
    L_0x00eb:
        if (r10 == 0) goto L_0x011e;
    L_0x00ed:
        r0 = "beacon";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x011e;
    L_0x00f6:
        r0 = "BEACON";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        r4 = 0;
        r5 = "Started...";
        r1[r4] = r5;	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        r0 = "beac";
        r1 = r8.m3002f();	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        r0 = "BEACON";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        r4 = 0;
        r5 = "Completed...";
        r1[r4] = r5;	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ Exception -> 0x02c5, JSONException -> 0x0255 }
    L_0x011e:
        r0 = "ptrlog";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x015a;
    L_0x0127:
        if (r10 == 0) goto L_0x015a;
    L_0x0129:
        r0 = "plugins";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x015a;
    L_0x0132:
        r0 = "PTRLOG";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        r4 = 0;
        r5 = "Started...";
        r1[r4] = r5;	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        r0 = "ptrlog";
        r1 = r9.m3033f();	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        r0 = "PTRLOG";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        r4 = 0;
        r5 = "Completed...";
        r1[r4] = r5;	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ Exception -> 0x02d8, JSONException -> 0x0255 }
    L_0x015a:
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.IsSandbox();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 != 0) goto L_0x0364;
    L_0x0162:
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.isAccepted();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 == 0) goto L_0x0364;
    L_0x016a:
        if (r10 == 0) goto L_0x0364;
    L_0x016c:
        r1 = new com.bgjd.ici.d.d;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1.<init>(r0);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = "location";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x0196;
    L_0x017c:
        r0 = "loc";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x0196;
    L_0x0185:
        r0 = "loc";
        r4 = new com.bgjd.ici.d.k;	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
        r5 = r14.a;	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
        r4.<init>(r5);	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
        r4 = r4.m3024f();	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
        r2.put(r0, r4);	 Catch:{ Exception -> 0x02eb, JSONException -> 0x0255 }
    L_0x0196:
        r0 = "bat";
        r0 = r2.has(r0);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 != 0) goto L_0x01c7;
    L_0x019f:
        r0 = "BAT";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r6 = "Started...";
        r4[r5] = r6;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = "bat";
        r1 = r1.m2992f();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r2.put(r0, r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = "BAT";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4 = 0;
        r5 = "Completed...";
        r1[r4] = r5;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
    L_0x01c7:
        r0 = "beacon";
        r0 = r10.has(r0);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 == 0) goto L_0x0364;
    L_0x01d0:
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.mo2198Y();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = "layout";
        r0 = r0.has(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 == 0) goto L_0x0323;
    L_0x01df:
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.mo2198Y();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = "uuids";
        r0 = r0.has(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 == 0) goto L_0x0323;
    L_0x01ee:
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.mo2198Y();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = "layout";
        r4 = r0.getJSONArray(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r0.mo2198Y();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = "uuids";
        r5 = r0.getJSONArray(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = r4.length();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r0 <= 0) goto L_0x0323;
    L_0x020e:
        r6 = new com.bgjd.ici.c.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = new com.bgjd.ici.b.m;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = r14.a;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r6.<init>(r0);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r6.open();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = com.bgjd.ici.p031f.C1489b.class;
        r1 = com.bgjd.ici.p030e.C1478c.class;
        r0 = r6.getMapper(r0, r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0 = (com.bgjd.ici.p031f.C1489b) r0;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = 0;
        r0.m3173d(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = 0;
    L_0x022c:
        r7 = r4.length();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r1 >= r7) goto L_0x02fe;
    L_0x0232:
        r7 = r4.get(r1);	 Catch:{ JSONException -> 0x037e, Exception -> 0x027a }
        if (r7 == 0) goto L_0x023f;
    L_0x0238:
        r7 = r7.toString();	 Catch:{ JSONException -> 0x037e, Exception -> 0x027a }
        r0.m3169a(r7);	 Catch:{ JSONException -> 0x037e, Exception -> 0x027a }
    L_0x023f:
        r1 = r1 + 1;
        goto L_0x022c;
    L_0x0242:
        r0 = move-exception;
        r1 = "MKTLOG";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r12 = 0;
        r13 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r11[r12] = r13;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r11);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x0080;
    L_0x0255:
        r0 = move-exception;
        r1 = "MKTLOG";
        r2 = 1;
        r2 = new java.lang.String[r2];
        r4 = 0;
        r5 = r0.getMessage();
        r2[r4] = r5;
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r2);
    L_0x0266:
        return r3;
    L_0x0267:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r11 = 0;
        r12 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r11] = r12;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x009e;
    L_0x027a:
        r0 = move-exception;
        r1 = "MKTLOG";
        r2 = 1;
        r2 = new java.lang.String[r2];
        r4 = 0;
        r5 = r0.getMessage();
        r2[r4] = r5;
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r2);
        goto L_0x0266;
    L_0x028c:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r11 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r5] = r11;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x00bc;
    L_0x029f:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r6 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r5] = r6;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x00cf;
    L_0x02b2:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r6 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r5] = r6;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x00e2;
    L_0x02c5:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r6 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r5] = r6;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x011e;
    L_0x02d8:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r6 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r5] = r6;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x015a;
    L_0x02eb:
        r0 = move-exception;
        r4 = "MKTLOG";
        r5 = 1;
        r5 = new java.lang.String[r5];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r6 = 0;
        r7 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5[r6] = r7;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r4, r0, r5);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x0196;
    L_0x02fe:
        r1 = r5.length();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r1 <= 0) goto L_0x0320;
    L_0x0304:
        r1 = 0;
        r0.m3171b(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r1 = 0;
    L_0x0309:
        r4 = r5.length();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r1 >= r4) goto L_0x0320;
    L_0x030f:
        r4 = r5.getJSONObject(r1);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        if (r4 == 0) goto L_0x031d;
    L_0x0315:
        r7 = new com.bgjd.ici.e.b;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r7.<init>(r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r0.m3168a(r7);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
    L_0x031d:
        r1 = r1 + 1;
        goto L_0x0309;
    L_0x0320:
        r6.close();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
    L_0x0323:
        r0 = "plugins";
        r0 = r10.has(r0);	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        if (r0 == 0) goto L_0x0364;
    L_0x032c:
        r0 = "plg";
        r0 = r2.has(r0);	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        if (r0 != 0) goto L_0x0364;
    L_0x0335:
        r0 = "PLG";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r4 = 0;
        r5 = "Started...";
        r1[r4] = r5;	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r0 = "plg";
        r1 = new com.bgjd.ici.d.q;	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r4 = r14.a;	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r1.<init>(r4);	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r1 = r1.m3061f();	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r2.put(r0, r1);	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r0 = "PLG";
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        r4 = 0;
        r5 = "Completed...";
        r1[r4] = r5;	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
        com.bgjd.ici.p025b.C1402i.m2901b(r0, r1);	 Catch:{ Exception -> 0x036c, JSONException -> 0x0255 }
    L_0x0364:
        r0 = "details";
        r3.put(r0, r2);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x0266;
    L_0x036c:
        r0 = move-exception;
        r1 = "MKTLOG";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r5 = 0;
        r6 = r0.getMessage();	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        r4[r5] = r6;	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        com.bgjd.ici.p025b.C1402i.m2898a(r1, r0, r4);	 Catch:{ JSONException -> 0x0255, Exception -> 0x027a }
        goto L_0x0364;
    L_0x037e:
        r7 = move-exception;
        goto L_0x023f;
    L_0x0381:
        r2 = r0;
        goto L_0x0062;
    L_0x0384:
        r2 = r0;
        goto L_0x0062;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.b.n.a():com.bgjd.ici.json.JSONObject");
    }

    public String mo2297b() {
        return C1403a.f2082l;
    }
}
