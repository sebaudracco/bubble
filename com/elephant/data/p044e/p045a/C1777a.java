package com.elephant.data.p044e.p045a;

import android.content.Context;
import com.elephant.data.p048g.C1814b;
import java.net.HttpURLConnection;

public abstract class C1777a {
    public static final String f3716b = C1814b.eW;
    protected HttpURLConnection f3717a;
    private Context f3718c;

    static {
        String str = C1814b.eS;
        str = C1814b.eT;
        str = C1814b.eU;
        str = C1814b.eV;
        str = C1814b.eX;
    }

    public C1777a(Context context) {
        this.f3718c = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int m5126a(int r11, java.lang.String r12) {
        /*
        r10 = this;
        r2 = 2;
        r1 = 0;
        r3 = 1;
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = com.elephant.data.p039b.p040a.C1731a.f3561d;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0.<init>(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = 1030; // 0x406 float:1.443E-42 double:5.09E-321;
        if (r11 != r4) goto L_0x009f;
    L_0x000e:
        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0.<init>(r12);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r5 = r0;
    L_0x0014:
        r4 = 0;
        r0 = r10.f3718c;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0 = com.elephant.data.p048g.C1816d.m5324o(r0);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        if (r0 == 0) goto L_0x008d;
    L_0x001d:
        r0 = r10.f3718c;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0 = com.elephant.data.p048g.C1816d.m5325p(r0);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        if (r0 == r3) goto L_0x008d;
    L_0x0025:
        r0 = r10.f3718c;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r0 = com.elephant.data.p048g.C1816d.m5325p(r0);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        if (r0 != r2) goto L_0x0073;
    L_0x002d:
        r6 = android.net.Proxy.getDefaultHost();	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r7 = android.net.Proxy.getDefaultPort();	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r0 = new java.net.Proxy;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r8 = java.net.Proxy.Type.HTTP;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r9 = new java.net.InetSocketAddress;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r9.<init>(r6, r7);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r0.<init>(r8, r9);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
    L_0x0041:
        if (r0 == 0) goto L_0x008f;
    L_0x0043:
        r0 = r5.openConnection(r0);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
    L_0x0049:
        r4 = 1;
        r0.setDoOutput(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = 1;
        r0.setDoInput(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = com.elephant.data.p048g.C1814b.eZ;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0.setRequestMethod(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = 0;
        r0.setUseCaches(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = 1;
        r0.setInstanceFollowRedirects(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setConnectTimeout(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r0.setReadTimeout(r4);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r4 = com.elephant.data.p048g.C1814b.fa;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r5 = com.elephant.data.p048g.C1814b.fb;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0.setRequestProperty(r4, r5);	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r10.f3717a = r0;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0 = r1;
    L_0x0072:
        return r0;
    L_0x0073:
        r0 = r10.f3718c;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r6 = com.elephant.data.p048g.C1816d.m5327r(r0);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r0 = r10.f3718c;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r7 = com.elephant.data.p048g.C1816d.m5328s(r0);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r0 = new java.net.Proxy;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r8 = java.net.Proxy.Type.HTTP;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r9 = new java.net.InetSocketAddress;	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r9.<init>(r6, r7);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        r0.<init>(r8, r9);	 Catch:{ Exception -> 0x008c, MalformedURLException -> 0x0096 }
        goto L_0x0041;
    L_0x008c:
        r0 = move-exception;
    L_0x008d:
        r0 = r4;
        goto L_0x0041;
    L_0x008f:
        r0 = r5.openConnection();	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ MalformedURLException -> 0x0096, Exception -> 0x0099 }
        goto L_0x0049;
    L_0x0096:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0072;
    L_0x0099:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r3;
        goto L_0x0072;
    L_0x009f:
        r5 = r0;
        goto L_0x0014;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.e.a.a.a(int, java.lang.String):int");
    }

    public final synchronized void m5127a(C1783g c1783g) {
        if (2 == m5126a(c1783g.f3735a, c1783g.f3740f)) {
            c1783g.f3738d = 2;
        } else {
            mo3538b(c1783g);
        }
    }

    public abstract void mo3538b(C1783g c1783g);
}
