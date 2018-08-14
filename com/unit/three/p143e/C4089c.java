package com.unit.three.p143e;

import android.net.Uri.Builder;
import android.text.TextUtils;
import java.util.Map;
import java.util.Map.Entry;

public final class C4089c {
    private int f9468a = 1000;
    private int f9469b = 1;
    private String f9470c;

    private String m12618a(String str, String str2, String str3, int i, int i2) {
        String str4 = "";
        int i3 = 0;
        while (i3 < this.f9469b) {
            String b = m12619b(str, str2, str3, 0, 0);
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            try {
                Thread.sleep((long) (this.f9468a * i3));
            } catch (Exception e) {
                e.printStackTrace();
            }
            i3++;
            str4 = b;
        }
        return str4;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m12619b(java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11, int r12) {
        /*
        r7 = this;
        r3 = 0;
        r4 = "";
        r0 = android.net.Uri.parse(r8);	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r1 = r7.f9470c;	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        if (r1 == 0) goto L_0x0019;
    L_0x0010:
        r1 = "isz";
        r0 = r0.getQueryParameter(r1);	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r7.f9470c = r0;	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
    L_0x0019:
        r0 = new java.net.URL;	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r0.<init>(r8);	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r0 = r0.openConnection();	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ SSLException -> 0x0100, Exception -> 0x00de }
        r1 = "accept";
        r2 = "*/*";
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = "connection";
        r2 = "Keep-Alive";
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = "POST";
        r0.setRequestMethod(r1);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = "Content-Type";
        r2 = "application/x-www-form-urlencoded";
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = "charset";
        r2 = "utf-8";
        r0.setRequestProperty(r1, r2);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = android.text.TextUtils.isEmpty(r10);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        if (r1 == 0) goto L_0x005b;
    L_0x0054:
        com.unit.three.p138b.C4053c.m12503a();	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r10 = com.unit.three.p143e.C4090d.m12621a();	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
    L_0x005b:
        r1 = "User-Agent";
        r0.setRequestProperty(r1, r10);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        if (r11 <= 0) goto L_0x00a8;
    L_0x006f:
        r2 = r11;
    L_0x0070:
        if (r12 <= 0) goto L_0x00ad;
    L_0x0072:
        r1 = r12;
    L_0x0073:
        r0.setReadTimeout(r2);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r0.setConnectTimeout(r1);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r2 = new java.io.OutputStreamWriter;	 Catch:{ all -> 0x00b1 }
        r1 = r0.getOutputStream();	 Catch:{ all -> 0x00b1 }
        r5 = "UTF-8";
        r2.<init>(r1, r5);	 Catch:{ all -> 0x00b1 }
        r2.write(r9);	 Catch:{ all -> 0x010b }
        r2.close();	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r0.getResponseCode();	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r2 = com.unit.three.p143e.C4090d.m12623a(r0);	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
        r1 = "1";
        r3 = r7.f9470c;	 Catch:{ SSLException -> 0x0106, Exception -> 0x00fc, all -> 0x00f1 }
        r1 = r1.equals(r3);	 Catch:{ SSLException -> 0x0106, Exception -> 0x00fc, all -> 0x00f1 }
        if (r1 == 0) goto L_0x010f;
    L_0x009d:
        r1 = com.unit.three.p143e.C4090d.m12635c(r2);	 Catch:{ SSLException -> 0x0106, Exception -> 0x00fc, all -> 0x00f1 }
    L_0x00a1:
        if (r0 == 0) goto L_0x010d;
    L_0x00a3:
        r0.disconnect();
        r0 = r1;
    L_0x00a7:
        return r0;
    L_0x00a8:
        r1 = 180000; // 0x2bf20 float:2.52234E-40 double:8.8932E-319;
        r2 = r1;
        goto L_0x0070;
    L_0x00ad:
        r1 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        goto L_0x0073;
    L_0x00b1:
        r1 = move-exception;
        r2 = r3;
    L_0x00b3:
        if (r2 == 0) goto L_0x00b8;
    L_0x00b5:
        r2.close();	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
    L_0x00b8:
        throw r1;	 Catch:{ SSLException -> 0x00b9, Exception -> 0x00f8, all -> 0x00f1 }
    L_0x00b9:
        r1 = move-exception;
        r6 = r0;
        r3 = r10;
        r0 = r4;
    L_0x00bd:
        r1.printStackTrace();	 Catch:{ all -> 0x00f5 }
        r1 = com.unit.three.p143e.C4090d.m12627a(r8);	 Catch:{ all -> 0x00f5 }
        if (r1 == 0) goto L_0x00d8;
    L_0x00c6:
        r1 = com.unit.three.p143e.C4090d.m12631b(r8);	 Catch:{ all -> 0x00f5 }
        r0 = r7;
        r2 = r9;
        r4 = r11;
        r5 = r12;
        r0 = r0.m12619b(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x00f5 }
        if (r6 == 0) goto L_0x00a7;
    L_0x00d4:
        r6.disconnect();
        goto L_0x00a7;
    L_0x00d8:
        if (r6 == 0) goto L_0x00a7;
    L_0x00da:
        r6.disconnect();
        goto L_0x00a7;
    L_0x00de:
        r0 = move-exception;
        r1 = r0;
        r0 = r4;
    L_0x00e1:
        r1.printStackTrace();	 Catch:{ all -> 0x00ea }
        if (r3 == 0) goto L_0x00a7;
    L_0x00e6:
        r3.disconnect();
        goto L_0x00a7;
    L_0x00ea:
        r0 = move-exception;
    L_0x00eb:
        if (r3 == 0) goto L_0x00f0;
    L_0x00ed:
        r3.disconnect();
    L_0x00f0:
        throw r0;
    L_0x00f1:
        r1 = move-exception;
        r3 = r0;
        r0 = r1;
        goto L_0x00eb;
    L_0x00f5:
        r0 = move-exception;
        r3 = r6;
        goto L_0x00eb;
    L_0x00f8:
        r1 = move-exception;
        r3 = r0;
        r0 = r4;
        goto L_0x00e1;
    L_0x00fc:
        r1 = move-exception;
        r3 = r0;
        r0 = r2;
        goto L_0x00e1;
    L_0x0100:
        r0 = move-exception;
        r1 = r0;
        r6 = r3;
        r0 = r4;
        r3 = r10;
        goto L_0x00bd;
    L_0x0106:
        r1 = move-exception;
        r6 = r0;
        r3 = r10;
        r0 = r2;
        goto L_0x00bd;
    L_0x010b:
        r1 = move-exception;
        goto L_0x00b3;
    L_0x010d:
        r0 = r1;
        goto L_0x00a7;
    L_0x010f:
        r1 = r2;
        goto L_0x00a1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.three.e.c.b(java.lang.String, java.lang.String, java.lang.String, int, int):java.lang.String");
    }

    public final String m12620a(String str, Map map, String str2) {
        if (map.isEmpty()) {
            return null;
        }
        String str3;
        if (map == null || map.isEmpty()) {
            str3 = "";
        } else {
            Builder builder = new Builder();
            for (Entry entry : map.entrySet()) {
                builder.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            str3 = builder.toString().substring(1);
        }
        return m12618a(str, str3, str2, 0, 0);
    }
}
