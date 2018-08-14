package com.bgjd.ici.p025b;

import android.content.SharedPreferences.Editor;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1408j.C1404b;

public class C1422r implements C1401f {
    private static final String f2195a = "LOGRESP";
    private JSONObject f2196b;
    private JSONObject f2197c;
    private C1395h f2198d;

    public C1422r(C1395h c1395h, JSONObject jSONObject) {
        this.f2198d = c1395h;
        this.f2196b = jSONObject;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo2311a() {
        /*
        r22 = this;
        r0 = r22;
        r2 = r0.f2196b;
        r3 = "status";
        r2 = r2.isNull(r3);
        if (r2 != 0) goto L_0x0592;
    L_0x000d:
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r2 = r2.getPreferences();	 Catch:{ JSONException -> 0x010d }
        r5 = r2.edit();	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "status";
        r2 = r2.getInt(r3);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r3 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r4 = "next";
        r6 = r3.getInt(r4);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r3 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r4 = "settings";
        r3 = r3.has(r4);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x0068;
    L_0x003c:
        r0 = r22;
        r3 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r4 = "settings";
        r3 = r3.getString(r4);	 Catch:{ JSONException -> 0x010d }
        r4 = r3.length();	 Catch:{ JSONException -> 0x010d }
        r7 = 5;
        if (r4 <= r7) goto L_0x0068;
    L_0x004e:
        r4 = "settings";
        r5.putString(r4, r3);	 Catch:{ JSONException -> 0x010d }
        r4 = new com.bgjd.ici.json.JSONObject;	 Catch:{ JSONException -> 0x05a1 }
        r4.<init>(r3);	 Catch:{ JSONException -> 0x05a1 }
        r0 = r22;
        r0.f2197c = r4;	 Catch:{ JSONException -> 0x05a1 }
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x05a1 }
        r0 = r22;
        r4 = r0.f2197c;	 Catch:{ JSONException -> 0x05a1 }
        r3.mo2215b(r4);	 Catch:{ JSONException -> 0x05a1 }
    L_0x0068:
        switch(r2) {
            case 200: goto L_0x008c;
            case 400: goto L_0x0466;
            case 401: goto L_0x046e;
            case 600: goto L_0x047e;
            case 700: goto L_0x048c;
            case 800: goto L_0x04ee;
            case 801: goto L_0x0514;
            case 802: goto L_0x053a;
            case 803: goto L_0x0560;
            default: goto L_0x006b;
        };
    L_0x006b:
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r4 = (long) r6;	 Catch:{ JSONException -> 0x010d }
        r2.mo2223c(r4);	 Catch:{ JSONException -> 0x010d }
    L_0x0073:
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ Exception -> 0x059e }
        r2 = r2.getContext();	 Catch:{ Exception -> 0x059e }
        com.bgjd.ici.p029d.C1451c.m2986a(r2);	 Catch:{ Exception -> 0x059e }
        r2 = com.bgjd.ici.p029d.C1451c.m2987b();	 Catch:{ Exception -> 0x059e }
        if (r2 == 0) goto L_0x008b;
    L_0x0084:
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ Exception -> 0x059e }
        r2.mo2185L();	 Catch:{ Exception -> 0x059e }
    L_0x008b:
        return;
    L_0x008c:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "logstatus";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x02e6;
    L_0x0099:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "logstatus";
        r7 = r2.getJSONObject(r3);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r2 = r2.mo2176D();	 Catch:{ JSONException -> 0x010d }
        r2 = r2.keySet();	 Catch:{ JSONException -> 0x010d }
        r8 = r2.iterator();	 Catch:{ JSONException -> 0x010d }
        r9 = new com.bgjd.ici.c.a;	 Catch:{ JSONException -> 0x010d }
        r2 = new com.bgjd.ici.b.m;	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r2.<init>(r3);	 Catch:{ JSONException -> 0x010d }
        r9.<init>(r2);	 Catch:{ JSONException -> 0x010d }
        r9.open();	 Catch:{ JSONException -> 0x010d }
        r2 = r9.IsConnected();	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x02e3;
    L_0x00cb:
        r2 = r8.hasNext();	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x02e3;
    L_0x00d1:
        r2 = r8.next();	 Catch:{ JSONException -> 0x010d }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x010d }
        r3 = r7.has(r2);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x00cb;
    L_0x00dd:
        r3 = "email";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x011a;
    L_0x00e6:
        r3 = r7.getInt(r2);	 Catch:{ JSONException -> 0x010d }
        if (r3 <= 0) goto L_0x00ef;
    L_0x00ec:
        r5.putInt(r2, r3);	 Catch:{ JSONException -> 0x010d }
    L_0x00ef:
        r2 = "LOGRESP";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x010d }
        r10 = 0;
        r11 = "Logged ACT : %s";
        r12 = 1;
        r12 = new java.lang.Object[r12];	 Catch:{ JSONException -> 0x010d }
        r13 = 0;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ JSONException -> 0x010d }
        r12[r13] = r3;	 Catch:{ JSONException -> 0x010d }
        r3 = java.lang.String.format(r11, r12);	 Catch:{ JSONException -> 0x010d }
        r4[r10] = r3;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r4);	 Catch:{ JSONException -> 0x010d }
        goto L_0x00cb;
    L_0x010d:
        r2 = move-exception;
        r0 = r22;
        r2 = r0.f2198d;
        r4 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r2.mo2223c(r4);
        goto L_0x0073;
    L_0x011a:
        r3 = "browsing";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x0188;
    L_0x0123:
        r10 = r7.getJSONObject(r2);	 Catch:{ JSONException -> 0x010d }
        if (r10 == 0) goto L_0x00cb;
    L_0x0129:
        r3 = com.bgjd.ici.p031f.C1490c.class;
        r4 = com.bgjd.ici.p030e.C1479d.class;
        r3 = r9.getMapper(r3, r4);	 Catch:{ JSONException -> 0x010d }
        r3 = (com.bgjd.ici.p031f.C1490c) r3;	 Catch:{ JSONException -> 0x010d }
        r11 = r10.keys();	 Catch:{ JSONException -> 0x010d }
    L_0x0137:
        r4 = r11.hasNext();	 Catch:{ JSONException -> 0x010d }
        if (r4 == 0) goto L_0x017f;
    L_0x013d:
        r4 = r11.next();	 Catch:{ JSONException -> 0x010d }
        r4 = (java.lang.String) r4;	 Catch:{ JSONException -> 0x010d }
        r12 = r10.getLong(r4);	 Catch:{ JSONException -> 0x010d }
        r14 = r3.m3175a(r12, r4);	 Catch:{ JSONException -> 0x010d }
        r16 = "LOGRESP";
        r17 = 1;
        r0 = r17;
        r0 = new java.lang.String[r0];	 Catch:{ JSONException -> 0x010d }
        r17 = r0;
        r18 = 0;
        r19 = "Logged BRW %s : Status %s Value %s";
        r20 = 3;
        r0 = r20;
        r0 = new java.lang.Object[r0];	 Catch:{ JSONException -> 0x010d }
        r20 = r0;
        r21 = 0;
        r20[r21] = r4;	 Catch:{ JSONException -> 0x010d }
        r4 = 1;
        r14 = java.lang.Long.valueOf(r14);	 Catch:{ JSONException -> 0x010d }
        r20[r4] = r14;	 Catch:{ JSONException -> 0x010d }
        r4 = 2;
        r12 = java.lang.Long.valueOf(r12);	 Catch:{ JSONException -> 0x010d }
        r20[r4] = r12;	 Catch:{ JSONException -> 0x010d }
        r4 = java.lang.String.format(r19, r20);	 Catch:{ JSONException -> 0x010d }
        r17[r18] = r4;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r16, r17);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0137;
    L_0x017f:
        r3 = r10.toString();	 Catch:{ JSONException -> 0x010d }
        r5.putString(r2, r3);	 Catch:{ JSONException -> 0x010d }
        goto L_0x00cb;
    L_0x0188:
        r3 = "package";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x01e7;
    L_0x0191:
        r10 = r7.getLong(r2);	 Catch:{ JSONException -> 0x010d }
        r12 = 0;
        r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r3 <= 0) goto L_0x00cb;
    L_0x019b:
        r5.putLong(r2, r10);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r3 = r2.getPackageMaxId();	 Catch:{ JSONException -> 0x010d }
        r2 = com.bgjd.ici.p031f.C1491d.class;
        r4 = com.bgjd.ici.p030e.C1481f.class;
        r2 = r9.getMapper(r2, r4);	 Catch:{ JSONException -> 0x010d }
        r2 = (com.bgjd.ici.p031f.C1491d) r2;	 Catch:{ JSONException -> 0x010d }
        r12 = r2.m3180a(r3, r10);	 Catch:{ JSONException -> 0x010d }
        r2 = "LOGRESP";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x010d }
        r14 = 0;
        r15 = "Logged PKG : Status %s Value %s Max ID %s";
        r16 = 3;
        r0 = r16;
        r0 = new java.lang.Object[r0];	 Catch:{ JSONException -> 0x010d }
        r16 = r0;
        r17 = 0;
        r12 = java.lang.Long.valueOf(r12);	 Catch:{ JSONException -> 0x010d }
        r16[r17] = r12;	 Catch:{ JSONException -> 0x010d }
        r12 = 1;
        r10 = java.lang.Long.valueOf(r10);	 Catch:{ JSONException -> 0x010d }
        r16[r12] = r10;	 Catch:{ JSONException -> 0x010d }
        r10 = 2;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ JSONException -> 0x010d }
        r16[r10] = r3;	 Catch:{ JSONException -> 0x010d }
        r3 = java.lang.String.format(r15, r16);	 Catch:{ JSONException -> 0x010d }
        r4[r14] = r3;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r4);	 Catch:{ JSONException -> 0x010d }
        goto L_0x00cb;
    L_0x01e7:
        r3 = "process";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x0232;
    L_0x01f0:
        r10 = r7.getLong(r2);	 Catch:{ JSONException -> 0x010d }
        r12 = 0;
        r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r3 <= 0) goto L_0x00cb;
    L_0x01fa:
        r5.putLong(r2, r10);	 Catch:{ JSONException -> 0x010d }
        r2 = com.bgjd.ici.p031f.C1493f.class;
        r3 = com.bgjd.ici.p030e.C1486i.class;
        r2 = r9.getMapper(r2, r3);	 Catch:{ JSONException -> 0x010d }
        r2 = (com.bgjd.ici.p031f.C1493f) r2;	 Catch:{ JSONException -> 0x010d }
        r2 = r2.m3189a(r10);	 Catch:{ JSONException -> 0x010d }
        r4 = "LOGRESP";
        r12 = 1;
        r12 = new java.lang.String[r12];	 Catch:{ JSONException -> 0x010d }
        r13 = 0;
        r14 = "Logged PROCS : Status %s Value %s";
        r15 = 2;
        r15 = new java.lang.Object[r15];	 Catch:{ JSONException -> 0x010d }
        r16 = 0;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ JSONException -> 0x010d }
        r15[r16] = r2;	 Catch:{ JSONException -> 0x010d }
        r2 = 1;
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ JSONException -> 0x010d }
        r15[r2] = r3;	 Catch:{ JSONException -> 0x010d }
        r2 = java.lang.String.format(r14, r15);	 Catch:{ JSONException -> 0x010d }
        r12[r13] = r2;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r4, r12);	 Catch:{ JSONException -> 0x010d }
        goto L_0x00cb;
    L_0x0232:
        r3 = "beacons";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x0285;
    L_0x023b:
        r10 = r7.getLong(r2);	 Catch:{ JSONException -> 0x010d }
        r12 = 0;
        r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r3 <= 0) goto L_0x00cb;
    L_0x0245:
        r5.putLong(r2, r10);	 Catch:{ JSONException -> 0x010d }
        r2 = com.bgjd.ici.p031f.C1488a.class;
        r3 = com.bgjd.ici.p030e.C1476a.class;
        r2 = r9.getMapper(r2, r3);	 Catch:{ JSONException -> 0x010d }
        r2 = (com.bgjd.ici.p031f.C1488a) r2;	 Catch:{ JSONException -> 0x010d }
        r12 = r2.m3163a(r10);	 Catch:{ JSONException -> 0x010d }
        r3 = "LOGRESP";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x010d }
        r14 = 0;
        r15 = "Logged BCON : Status %s Value %s";
        r16 = 2;
        r0 = r16;
        r0 = new java.lang.Object[r0];	 Catch:{ JSONException -> 0x010d }
        r16 = r0;
        r17 = 0;
        r12 = java.lang.Long.valueOf(r12);	 Catch:{ JSONException -> 0x010d }
        r16[r17] = r12;	 Catch:{ JSONException -> 0x010d }
        r12 = 1;
        r13 = java.lang.Long.valueOf(r10);	 Catch:{ JSONException -> 0x010d }
        r16[r12] = r13;	 Catch:{ JSONException -> 0x010d }
        r12 = java.lang.String.format(r15, r16);	 Catch:{ JSONException -> 0x010d }
        r4[r14] = r12;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r3, r4);	 Catch:{ JSONException -> 0x010d }
        r2.m3166b(r10);	 Catch:{ JSONException -> 0x010d }
        goto L_0x00cb;
    L_0x0285:
        r3 = "ptrlogs";
        r3 = r2.equalsIgnoreCase(r3);	 Catch:{ JSONException -> 0x010d }
        if (r3 == 0) goto L_0x00cb;
    L_0x028e:
        r10 = r7.getLong(r2);	 Catch:{ JSONException -> 0x010d }
        r12 = 0;
        r3 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r3 <= 0) goto L_0x00cb;
    L_0x0298:
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r3 = r3.mo2176D();	 Catch:{ JSONException -> 0x010d }
        r3 = r3.get(r2);	 Catch:{ JSONException -> 0x010d }
        r3 = (java.lang.Long) r3;	 Catch:{ JSONException -> 0x010d }
        r12 = r3.longValue();	 Catch:{ JSONException -> 0x010d }
        r5.putLong(r2, r12);	 Catch:{ JSONException -> 0x010d }
        r2 = com.bgjd.ici.p031f.C1492e.class;
        r3 = com.bgjd.ici.p030e.C1482g.class;
        r2 = r9.getMapper(r2, r3);	 Catch:{ JSONException -> 0x010d }
        r2 = (com.bgjd.ici.p031f.C1492e) r2;	 Catch:{ JSONException -> 0x010d }
        r3 = 2;
        r2 = r2.m3188b(r3, r12);	 Catch:{ JSONException -> 0x010d }
        r4 = "LOGRESP";
        r12 = 1;
        r12 = new java.lang.String[r12];	 Catch:{ JSONException -> 0x010d }
        r13 = 0;
        r14 = "Logged PTR LOGS : Status %s Value %s";
        r15 = 2;
        r15 = new java.lang.Object[r15];	 Catch:{ JSONException -> 0x010d }
        r16 = 0;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ JSONException -> 0x010d }
        r15[r16] = r2;	 Catch:{ JSONException -> 0x010d }
        r2 = 1;
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ JSONException -> 0x010d }
        r15[r2] = r3;	 Catch:{ JSONException -> 0x010d }
        r2 = java.lang.String.format(r14, r15);	 Catch:{ JSONException -> 0x010d }
        r12[r13] = r2;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r4, r12);	 Catch:{ JSONException -> 0x010d }
        goto L_0x00cb;
    L_0x02e3:
        r9.close();	 Catch:{ JSONException -> 0x010d }
    L_0x02e6:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "type";
        r2 = r2.getInt(r3);	 Catch:{ JSONException -> 0x010d }
        r3 = 3;
        if (r2 != r3) goto L_0x02fc;
    L_0x02f4:
        r2 = 0;
        r0 = r22;
        r0.m2934a(r5, r2);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x02fc:
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r6 = (long) r6;	 Catch:{ JSONException -> 0x010d }
        r2.mo2223c(r6);	 Catch:{ JSONException -> 0x010d }
        r5.commit();	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "notify";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x03bb;
    L_0x0314:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "notify";
        r2 = r2.getBoolean(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x03bb;
    L_0x0321:
        r2 = 0;
        r0 = r22;
        r3 = r0.f2196b;	 Catch:{ Exception -> 0x0420 }
        r4 = "logged";
        r3 = r3.has(r4);	 Catch:{ Exception -> 0x0420 }
        if (r3 == 0) goto L_0x033a;
    L_0x032f:
        r0 = r22;
        r3 = r0.f2196b;	 Catch:{ Exception -> 0x0420 }
        r4 = "logged";
        r2 = r3.getBoolean(r4);	 Catch:{ Exception -> 0x0420 }
    L_0x033a:
        if (r2 == 0) goto L_0x03bb;
    L_0x033c:
        r0 = r22;
        r2 = r0.f2197c;	 Catch:{ JSONException -> 0x010d }
        r3 = "notification";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x03bb;
    L_0x0349:
        r0 = r22;
        r2 = r0.f2197c;	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3 = "notification";
        r2 = r2.getJSONObject(r3);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3 = "title";
        r3 = r2.getString(r3);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r4 = "message";
        r4 = r2.getString(r4);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r6 = "url";
        r6 = r2.getString(r6);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r2 = r2.getContext();	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r2 = r2.getApplicationInfo();	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r2 = r2.labelRes;	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r0 = r22;
        r7 = r0.f2198d;	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r7 = r7.getContext();	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r2 = r7.getString(r2);	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
        r7 = "#appname";
        r2 = r3.replace(r7, r2);	 Catch:{ Exception -> 0x0433, JSONException -> 0x0440 }
    L_0x038a:
        r3 = r2.length();	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        if (r3 <= 0) goto L_0x03bb;
    L_0x0390:
        r3 = r4.length();	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        if (r3 <= 0) goto L_0x03bb;
    L_0x0396:
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3.mo2270k(r2);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3.mo2272l(r4);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3.mo2274m(r6);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3 = new com.bgjd.ici.e.e;	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3.<init>(r2, r4, r6);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r2 = r2.getContext();	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        r3.mo2329a(r2);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
    L_0x03bb:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "reminder";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x0073;
    L_0x03c8:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "eula";
        r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x010d }
        r3 = "eula";
        r5.putString(r3, r2);	 Catch:{ JSONException -> 0x010d }
        r5.commit();	 Catch:{ JSONException -> 0x010d }
        r2 = new android.content.Intent;	 Catch:{ JSONException -> 0x010d }
        r2.<init>();	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r3 = r3.getContext();	 Catch:{ JSONException -> 0x010d }
        r4 = "com.bgjd.ici.MarketEula";
        r2.setClassName(r3, r4);	 Catch:{ JSONException -> 0x010d }
        r3 = "sandbox";
        r0 = r22;
        r4 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r4 = r4.IsSandbox();	 Catch:{ JSONException -> 0x010d }
        r2.putExtra(r3, r4);	 Catch:{ JSONException -> 0x010d }
        r3 = "reminder";
        r4 = 1;
        r2.putExtra(r3, r4);	 Catch:{ JSONException -> 0x010d }
        r3 = 268435456; // 0x10000000 float:2.5243549E-29 double:1.32624737E-315;
        r2.setFlags(r3);	 Catch:{ JSONException -> 0x010d }
        r3 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        r2.addFlags(r3);	 Catch:{ JSONException -> 0x010d }
        r3 = 67108864; // 0x4000000 float:1.5046328E-36 double:3.31561842E-316;
        r2.addFlags(r3);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r3 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r3 = r3.getContext();	 Catch:{ JSONException -> 0x010d }
        r3.startActivity(r2);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x0420:
        r3 = move-exception;
        r4 = "LOGRESP";
        r6 = 1;
        r6 = new java.lang.String[r6];	 Catch:{ JSONException -> 0x010d }
        r7 = 0;
        r8 = r3.getMessage();	 Catch:{ JSONException -> 0x010d }
        r6[r7] = r8;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2898a(r4, r3, r6);	 Catch:{ JSONException -> 0x010d }
        goto L_0x033a;
    L_0x0433:
        r2 = move-exception;
        r2 = "#appname";
        r7 = "";
        r2 = r3.replace(r2, r7);	 Catch:{ JSONException -> 0x0440, Exception -> 0x0453 }
        goto L_0x038a;
    L_0x0440:
        r2 = move-exception;
        r3 = "LOGRESP";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x010d }
        r6 = 0;
        r7 = r2.getMessage();	 Catch:{ JSONException -> 0x010d }
        r4[r6] = r7;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2898a(r3, r2, r4);	 Catch:{ JSONException -> 0x010d }
        goto L_0x03bb;
    L_0x0453:
        r2 = move-exception;
        r3 = "LOGRESP";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ JSONException -> 0x010d }
        r6 = 0;
        r7 = r2.getMessage();	 Catch:{ JSONException -> 0x010d }
        r4[r6] = r7;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2898a(r3, r2, r4);	 Catch:{ JSONException -> 0x010d }
        goto L_0x03bb;
    L_0x0466:
        r2 = 1;
        r0 = r22;
        r0.m2934a(r5, r2);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x046e:
        r2 = 1;
        r0 = r22;
        r0.m2934a(r5, r2);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r3 = 1;
        r2.mo2235f(r3);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x047e:
        r0 = r22;
        r0.m2933a(r5);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r2.mo2185L();	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x048c:
        r0 = r22;
        r2 = r0.f2197c;	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x04c0;
    L_0x0492:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "logger";
        r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x04c0;
    L_0x049f:
        r3 = r2.length();	 Catch:{ JSONException -> 0x010d }
        if (r3 <= 0) goto L_0x04c0;
    L_0x04a5:
        r0 = r22;
        r3 = r0.f2197c;	 Catch:{ JSONException -> 0x010d }
        r4 = "logger";
        r3.put(r4, r2);	 Catch:{ JSONException -> 0x010d }
        r2 = "settings";
        r0 = r22;
        r3 = r0.f2197c;	 Catch:{ JSONException -> 0x010d }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x010d }
        r5.putString(r2, r3);	 Catch:{ JSONException -> 0x010d }
        r5.commit();	 Catch:{ JSONException -> 0x010d }
    L_0x04c0:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "message";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x04e4;
    L_0x04cd:
        r2 = "LOGRESP";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x010d }
        r4 = 0;
        r0 = r22;
        r5 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r7 = "message";
        r5 = r5.getString(r7);	 Catch:{ JSONException -> 0x010d }
        r3[r4] = r5;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r3);	 Catch:{ JSONException -> 0x010d }
    L_0x04e4:
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r4 = (long) r6;	 Catch:{ JSONException -> 0x010d }
        r2.mo2223c(r4);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x04ee:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "message";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x0073;
    L_0x04fb:
        r2 = "LOGRESP";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x010d }
        r4 = 0;
        r0 = r22;
        r5 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r6 = "message";
        r5 = r5.getString(r6);	 Catch:{ JSONException -> 0x010d }
        r3[r4] = r5;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r3);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x0514:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "message";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x0073;
    L_0x0521:
        r2 = "LOGRESP";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x010d }
        r4 = 0;
        r0 = r22;
        r5 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r6 = "message";
        r5 = r5.getString(r6);	 Catch:{ JSONException -> 0x010d }
        r3[r4] = r5;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r3);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x053a:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "message";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x0073;
    L_0x0547:
        r2 = "LOGRESP";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x010d }
        r4 = 0;
        r0 = r22;
        r5 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r6 = "message";
        r5 = r5.getString(r6);	 Catch:{ JSONException -> 0x010d }
        r3[r4] = r5;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r3);	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x0560:
        r0 = r22;
        r2 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r3 = "message";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x010d }
        if (r2 == 0) goto L_0x0584;
    L_0x056d:
        r2 = "LOGRESP";
        r3 = 1;
        r3 = new java.lang.String[r3];	 Catch:{ JSONException -> 0x010d }
        r4 = 0;
        r0 = r22;
        r6 = r0.f2196b;	 Catch:{ JSONException -> 0x010d }
        r7 = "message";
        r6 = r6.getString(r7);	 Catch:{ JSONException -> 0x010d }
        r3[r4] = r6;	 Catch:{ JSONException -> 0x010d }
        com.bgjd.ici.p025b.C1402i.m2901b(r2, r3);	 Catch:{ JSONException -> 0x010d }
    L_0x0584:
        r0 = r22;
        r0.m2933a(r5);	 Catch:{ JSONException -> 0x010d }
        r0 = r22;
        r2 = r0.f2198d;	 Catch:{ JSONException -> 0x010d }
        r2.mo2185L();	 Catch:{ JSONException -> 0x010d }
        goto L_0x0073;
    L_0x0592:
        r0 = r22;
        r2 = r0.f2198d;
        r4 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r2.mo2223c(r4);
        goto L_0x0073;
    L_0x059e:
        r2 = move-exception;
        goto L_0x008b;
    L_0x05a1:
        r3 = move-exception;
        goto L_0x0068;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.b.r.a():void");
    }

    private void m2933a(Editor editor) {
        editor.putBoolean(C1404b.f2101E, true);
        editor.putBoolean(C1404b.f2100D, true);
        editor.putBoolean(C1404b.f2103G, false);
        editor.commit();
    }

    private void m2934a(Editor editor, boolean z) {
        editor.putBoolean(C1404b.f2101E, false);
        editor.putBoolean(C1404b.f2100D, false);
        editor.putBoolean(C1404b.f2102F, false);
        editor.putBoolean(C1404b.f2103G, false);
        editor.putString(C1404b.f2104H, "{}");
        editor.putBoolean(C1404b.aO, false);
        if (z) {
            editor.putString(C1404b.f2107K, "{}");
        }
        editor.commit();
    }
}
