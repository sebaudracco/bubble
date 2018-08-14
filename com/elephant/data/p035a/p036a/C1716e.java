package com.elephant.data.p035a.p036a;

import android.net.Uri.Builder;
import android.text.TextUtils;
import com.elephant.data.p048g.C1814b;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public final class C1716e {
    private int f3515a = 1000;
    private int f3516b = 1;
    private String f3517c;

    static {
        String str = C1814b.jX;
    }

    private String m4954a(String str, String str2, String str3) {
        String str4 = "";
        for (int i = 0; i < this.f3516b; i++) {
            str4 = m4956b(str, str2, str3);
            if (!TextUtils.isEmpty(str4)) {
                break;
            }
            try {
                Thread.sleep((long) (this.f3515a * i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str4;
    }

    private static void m4955a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m4956b(java.lang.String r10, java.lang.String r11, java.lang.String r12) {
        /*
        r9 = this;
        r3 = 0;
        r2 = "";
        r0 = android.net.Uri.parse(r10);	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r1 = r9.f3517c;	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        if (r1 == 0) goto L_0x0018;
    L_0x0010:
        r1 = com.elephant.data.p048g.C1814b.jY;	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r0 = r0.getQueryParameter(r1);	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r9.f3517c = r0;	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
    L_0x0018:
        r0 = new java.net.URL;	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r0.<init>(r10);	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r0 = r0.openConnection();	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ SSLException -> 0x0123, Exception -> 0x00e4, all -> 0x00f7 }
        r1 = com.elephant.data.p048g.C1814b.jZ;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4 = "*/*";
        r0.setRequestProperty(r1, r4);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = com.elephant.data.p048g.C1814b.ka;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4 = com.elephant.data.p048g.C1814b.kb;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r0.setRequestProperty(r1, r4);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = com.elephant.data.p048g.C1814b.kc;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r0.setRequestMethod(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = com.elephant.data.p048g.C1814b.kd;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4 = com.elephant.data.p048g.C1814b.ke;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r0.setRequestProperty(r1, r4);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = com.elephant.data.p048g.C1814b.kf;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4 = com.elephant.data.p048g.C1814b.kg;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r0.setRequestProperty(r1, r4);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = android.text.TextUtils.isEmpty(r12);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        if (r1 != 0) goto L_0x0050;
    L_0x004b:
        r1 = com.elephant.data.p048g.C1814b.kh;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r0.setRequestProperty(r1, r12);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
    L_0x0050:
        r1 = 0;
        r0.setUseCaches(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = 1;
        r0.setDoOutput(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = 1;
        r0.setDoInput(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = 180000; // 0x2bf20 float:2.52234E-40 double:8.8932E-319;
        r0.setReadTimeout(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r0.setConnectTimeout(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        if (r11 == 0) goto L_0x0137;
    L_0x006a:
        r1 = r11.trim();	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4 = "";
        r1 = r1.equals(r4);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        if (r1 != 0) goto L_0x0137;
    L_0x0077:
        r4 = new java.io.PrintWriter;	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r1 = r0.getOutputStream();	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4.<init>(r1);	 Catch:{ SSLException -> 0x0129, Exception -> 0x0111, all -> 0x0106 }
        r4.print(r11);	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r4.flush();	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
    L_0x0086:
        r1 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r1 = new byte[r1];	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r5 = new java.io.ByteArrayOutputStream;	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r5.<init>();	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
    L_0x008f:
        r6 = r0.getInputStream();	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r6 = r6.read(r1);	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r7 = -1;
        if (r6 == r7) goto L_0x00c0;
    L_0x009a:
        r7 = 0;
        r5.write(r1, r7, r6);	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        goto L_0x008f;
    L_0x009f:
        r1 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r8;
    L_0x00a4:
        r0.printStackTrace();	 Catch:{ all -> 0x010f }
        r0 = com.elephant.data.p048g.C1816d.m5296c(r10);	 Catch:{ all -> 0x010f }
        if (r0 == 0) goto L_0x00b4;
    L_0x00ad:
        r0 = com.elephant.data.p048g.C1816d.m5300d(r10);	 Catch:{ all -> 0x010f }
        r9.m4956b(r0, r11, r12);	 Catch:{ all -> 0x010f }
    L_0x00b4:
        com.elephant.data.p035a.p036a.C1716e.m4955a(r4);
        com.elephant.data.p035a.p036a.C1716e.m4955a(r3);
        if (r2 == 0) goto L_0x00bf;
    L_0x00bc:
        r2.disconnect();
    L_0x00bf:
        return r1;
    L_0x00c0:
        r5.flush();	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r5.close();	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r1 = r5.toString();	 Catch:{ SSLException -> 0x009f, Exception -> 0x0118, all -> 0x010b }
        r2 = com.elephant.data.p048g.C1814b.ki;	 Catch:{ SSLException -> 0x0131, Exception -> 0x011e, all -> 0x010b }
        r5 = r9.f3517c;	 Catch:{ SSLException -> 0x0131, Exception -> 0x011e, all -> 0x010b }
        r2 = r2.equals(r5);	 Catch:{ SSLException -> 0x0131, Exception -> 0x011e, all -> 0x010b }
        if (r2 == 0) goto L_0x00d8;
    L_0x00d4:
        r1 = com.elephant.data.p048g.C1816d.m5280a(r1);	 Catch:{ SSLException -> 0x0131, Exception -> 0x011e, all -> 0x010b }
    L_0x00d8:
        com.elephant.data.p035a.p036a.C1716e.m4955a(r4);
        com.elephant.data.p035a.p036a.C1716e.m4955a(r3);
        if (r0 == 0) goto L_0x00bf;
    L_0x00e0:
        r0.disconnect();
        goto L_0x00bf;
    L_0x00e4:
        r0 = move-exception;
        r1 = r2;
        r4 = r3;
        r2 = r3;
    L_0x00e8:
        r0.printStackTrace();	 Catch:{ all -> 0x010f }
        com.elephant.data.p035a.p036a.C1716e.m4955a(r4);
        com.elephant.data.p035a.p036a.C1716e.m4955a(r3);
        if (r2 == 0) goto L_0x00bf;
    L_0x00f3:
        r2.disconnect();
        goto L_0x00bf;
    L_0x00f7:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x00fa:
        com.elephant.data.p035a.p036a.C1716e.m4955a(r4);
        com.elephant.data.p035a.p036a.C1716e.m4955a(r3);
        if (r2 == 0) goto L_0x0105;
    L_0x0102:
        r2.disconnect();
    L_0x0105:
        throw r0;
    L_0x0106:
        r1 = move-exception;
        r2 = r0;
        r4 = r3;
        r0 = r1;
        goto L_0x00fa;
    L_0x010b:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00fa;
    L_0x010f:
        r0 = move-exception;
        goto L_0x00fa;
    L_0x0111:
        r1 = move-exception;
        r4 = r3;
        r8 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x00e8;
    L_0x0118:
        r1 = move-exception;
        r8 = r1;
        r1 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00e8;
    L_0x011e:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00e8;
    L_0x0123:
        r0 = move-exception;
        r1 = r2;
        r4 = r3;
        r2 = r3;
        goto L_0x00a4;
    L_0x0129:
        r1 = move-exception;
        r4 = r3;
        r8 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r8;
        goto L_0x00a4;
    L_0x0131:
        r2 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x00a4;
    L_0x0137:
        r4 = r3;
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.a.a.e.b(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public final String m4957a(String str, Map map, String str2) {
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
        return m4954a(str, str3, str2);
    }
}
