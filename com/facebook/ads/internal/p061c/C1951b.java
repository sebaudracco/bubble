package com.facebook.ads.internal.p061c;

public class C1951b {
    public static String f4525a = "";
    public static String f4526b = "";
    public static boolean f4527c = false;
    public static String f4528d = "";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m6157a(android.content.Context r4) {
        /*
        r1 = 0;
        r0 = "SDKIDFA";
        r2 = 0;
        r2 = r4.getSharedPreferences(r0, r2);	 Catch:{ Exception -> 0x00d0 }
        r0 = "attributionId";
        r0 = r2.contains(r0);	 Catch:{ Exception -> 0x00d0 }
        if (r0 == 0) goto L_0x001e;
    L_0x0012:
        r0 = "attributionId";
        r3 = "";
        r0 = r2.getString(r0, r3);	 Catch:{ Exception -> 0x00d0 }
        f4525a = r0;	 Catch:{ Exception -> 0x00d0 }
    L_0x001e:
        r0 = "advertisingId";
        r0 = r2.contains(r0);	 Catch:{ Exception -> 0x00d0 }
        if (r0 == 0) goto L_0x0046;
    L_0x0027:
        r0 = "advertisingId";
        r3 = "";
        r0 = r2.getString(r0, r3);	 Catch:{ Exception -> 0x00d0 }
        f4526b = r0;	 Catch:{ Exception -> 0x00d0 }
        r0 = "limitAdTracking";
        r3 = f4527c;	 Catch:{ Exception -> 0x00d0 }
        r0 = r2.getBoolean(r0, r3);	 Catch:{ Exception -> 0x00d0 }
        f4527c = r0;	 Catch:{ Exception -> 0x00d0 }
        r0 = com.facebook.ads.internal.p061c.C1950a.C1949c.SHARED_PREFS;	 Catch:{ Exception -> 0x00d0 }
        r0 = r0.name();	 Catch:{ Exception -> 0x00d0 }
        f4528d = r0;	 Catch:{ Exception -> 0x00d0 }
    L_0x0046:
        r0 = r4.getContentResolver();	 Catch:{ Exception -> 0x00b6 }
        r0 = com.facebook.ads.internal.p061c.C1953c.m6158a(r0);	 Catch:{ Exception -> 0x00b6 }
    L_0x004e:
        if (r0 == 0) goto L_0x0058;
    L_0x0050:
        r3 = r0.f4529a;	 Catch:{ Exception -> 0x00d0 }
        if (r3 == 0) goto L_0x0058;
    L_0x0054:
        r3 = r0.f4529a;	 Catch:{ Exception -> 0x00d0 }
        f4525a = r3;	 Catch:{ Exception -> 0x00d0 }
    L_0x0058:
        r3 = com.facebook.ads.internal.p056q.p057a.C2108b.m6766a();	 Catch:{ Exception -> 0x00d0 }
        if (r3 == 0) goto L_0x0070;
    L_0x005e:
        r3 = "aid_override";
        r3 = com.facebook.ads.internal.p056q.p057a.C2108b.m6767b(r3);	 Catch:{ Exception -> 0x00d0 }
        if (r3 == 0) goto L_0x0070;
    L_0x0067:
        r3 = "aid_override";
        r3 = com.facebook.ads.internal.p056q.p057a.C2108b.m6765a(r3);	 Catch:{ Exception -> 0x00d0 }
        f4525a = r3;	 Catch:{ Exception -> 0x00d0 }
    L_0x0070:
        r0 = com.facebook.ads.internal.p061c.C1950a.m6151a(r4, r0);	 Catch:{ Exception -> 0x00c3 }
    L_0x0074:
        if (r0 == 0) goto L_0x0096;
    L_0x0076:
        r1 = r0.m6154a();	 Catch:{ Exception -> 0x00d0 }
        r3 = r0.m6155b();	 Catch:{ Exception -> 0x00d0 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ Exception -> 0x00d0 }
        if (r1 == 0) goto L_0x0096;
    L_0x0084:
        f4526b = r1;	 Catch:{ Exception -> 0x00d0 }
        r1 = r3.booleanValue();	 Catch:{ Exception -> 0x00d0 }
        f4527c = r1;	 Catch:{ Exception -> 0x00d0 }
        r0 = r0.m6156c();	 Catch:{ Exception -> 0x00d0 }
        r0 = r0.name();	 Catch:{ Exception -> 0x00d0 }
        f4528d = r0;	 Catch:{ Exception -> 0x00d0 }
    L_0x0096:
        r0 = r2.edit();	 Catch:{ Exception -> 0x00d0 }
        r1 = "attributionId";
        r2 = f4525a;	 Catch:{ Exception -> 0x00d0 }
        r0.putString(r1, r2);	 Catch:{ Exception -> 0x00d0 }
        r1 = "advertisingId";
        r2 = f4526b;	 Catch:{ Exception -> 0x00d0 }
        r0.putString(r1, r2);	 Catch:{ Exception -> 0x00d0 }
        r1 = "limitAdTracking";
        r2 = f4527c;	 Catch:{ Exception -> 0x00d0 }
        r0.putBoolean(r1, r2);	 Catch:{ Exception -> 0x00d0 }
        r0.apply();	 Catch:{ Exception -> 0x00d0 }
    L_0x00b5:
        return;
    L_0x00b6:
        r0 = move-exception;
        r3 = "Error retrieving attribution id from fb4a";
        r0 = com.facebook.ads.internal.p052j.C1998a.m6318a(r0, r3);	 Catch:{ Exception -> 0x00d0 }
        com.facebook.ads.internal.p052j.C1999b.m6321a(r0);	 Catch:{ Exception -> 0x00d0 }
        r0 = r1;
        goto L_0x004e;
    L_0x00c3:
        r0 = move-exception;
        r3 = "Error retrieving advertising id from Google Play Services";
        r0 = com.facebook.ads.internal.p052j.C1998a.m6318a(r0, r3);	 Catch:{ Exception -> 0x00d0 }
        com.facebook.ads.internal.p052j.C1999b.m6321a(r0);	 Catch:{ Exception -> 0x00d0 }
        r0 = r1;
        goto L_0x0074;
    L_0x00d0:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x00b5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.c.b.a(android.content.Context):void");
    }
}
