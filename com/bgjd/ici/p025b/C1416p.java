package com.bgjd.ici.p025b;

import android.util.Log;
import com.bgjd.ici.p027g.C1415b;
import com.bgjd.ici.p027g.C1494a;
import java.lang.Thread.UncaughtExceptionHandler;

public class C1416p implements C1415b, UncaughtExceptionHandler {
    private C1395h f2180a = null;
    private C1494a f2181b = null;
    private String f2182c = "";

    public C1416p(C1395h c1395h) {
        this.f2180a = c1395h;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r9, java.lang.Throwable r10) {
        /*
        r8 = this;
        r6 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r2 = 1;
        r0 = 0;
        r1 = new java.io.StringWriter;
        r1.<init>();
        r3 = new java.io.PrintWriter;
        r3.<init>(r1);
        r10.printStackTrace(r3);
        r3 = new com.bgjd.ici.json.JSONObject;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.<init>();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "appid";
        r5 = "cb53175e16d6f93b89fea5e45b9934c1ee38b3f4";
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "sdkid";
        r5 = 3;
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "uid";
        r5 = 1693; // 0x69d float:2.372E-42 double:8.365E-321;
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "apikey";
        r5 = "c79cfc0d4d4e7285f8b7e975ad35c984d8dd1ae6";
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "package";
        r5 = r8.f2180a;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r5 = r5.getContext();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r5 = r5.getPackageName();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "brand";
        r5 = android.os.Build.BRAND;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "device";
        r5 = android.os.Build.DEVICE;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "model";
        r5 = android.os.Build.MODEL;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "id";
        r5 = android.os.Build.ID;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "prod";
        r5 = android.os.Build.PRODUCT;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "release";
        r5 = android.os.Build.VERSION.RELEASE;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "incremental";
        r5 = android.os.Build.VERSION.INCREMENTAL;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r5);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = "Error";
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3.put(r4, r1);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r1 = r3.toString();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r8.f2182c = r1;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r1 = "%s/api/v1/error";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = 0;
        r5 = "https://apidm.airpush.com";
        r3[r4] = r5;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r1 = java.lang.String.format(r1, r3);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r3 = java.net.URI.create(r1);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r4 = r3.getScheme();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r1 = -1;
        r5 = r4.hashCode();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        switch(r5) {
            case 3213448: goto L_0x00c1;
            case 99617003: goto L_0x00cb;
            default: goto L_0x00b0;
        };	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
    L_0x00b0:
        r0 = r1;
    L_0x00b1:
        switch(r0) {
            case 0: goto L_0x00d6;
            case 1: goto L_0x00e5;
            default: goto L_0x00b4;
        };	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
    L_0x00b4:
        r0 = new com.bgjd.ici.g.d;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r0.<init>(r3, r8);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r8.f2181b = r0;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
    L_0x00bb:
        r0 = r8.f2181b;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r0.mo2330a();	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
    L_0x00c0:
        return;
    L_0x00c1:
        r2 = "http";
        r2 = r4.equals(r2);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        if (r2 == 0) goto L_0x00b0;
    L_0x00ca:
        goto L_0x00b1;
    L_0x00cb:
        r0 = "https";
        r0 = r4.equals(r0);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        if (r0 == 0) goto L_0x00b0;
    L_0x00d4:
        r0 = r2;
        goto L_0x00b1;
    L_0x00d6:
        r0 = new com.bgjd.ici.g.c;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r0.<init>(r3, r8);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r8.f2181b = r0;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        goto L_0x00bb;
    L_0x00de:
        r0 = move-exception;
        r0 = r8.f2180a;
        r0.mo2223c(r6);
        goto L_0x00c0;
    L_0x00e5:
        r0 = new com.bgjd.ici.g.d;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r0.<init>(r3, r8);	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        r8.f2181b = r0;	 Catch:{ JSONException -> 0x00de, Exception -> 0x00ed }
        goto L_0x00bb;
    L_0x00ed:
        r0 = move-exception;
        r0 = r8.f2180a;
        r0.mo2223c(r6);
        goto L_0x00c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.b.p.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    public void mo2304a() {
        Log.i("MKT", "Reporting Error...");
        Log.i("MKT", this.f2182c);
        this.f2181b.mo2331a(this.f2182c);
    }

    public void mo2307a(String str) {
        Log.i("MKT", "Error Successfully Reported...");
        Log.i("MKT", str);
        this.f2180a.mo2223c(300000);
        this.f2181b.mo2333b();
    }

    public void mo2308a(byte[] bArr) {
        this.f2180a.mo2223c(300000);
        this.f2181b.mo2333b();
    }

    public void mo2305a(int i, String str) {
    }

    public void mo2306a(Exception exception) {
        Log.i("MKT", "Error Post " + exception.getMessage());
        Log.i("MKT", exception.getMessage());
        this.f2180a.mo2223c(300000);
        this.f2181b.mo2333b();
    }
}
