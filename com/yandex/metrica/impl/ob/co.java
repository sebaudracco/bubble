package com.yandex.metrica.impl.ob;

import java.security.cert.X509Certificate;
import javax.net.ssl.SSLSocketFactory;

public class co {
    String f12119a;
    private String f12120b;
    private SSLSocketFactory f12121c;
    private fj f12122d = new C44341(this);

    class C44341 implements fj {
        final /* synthetic */ co f12117a;

        C44341(co coVar) {
            this.f12117a = coVar;
        }

        public String mo7067a() {
            return this.f12117a.f12120b;
        }
    }

    private static class C4435a {
        static final co f12118a = new co();
    }

    public static co m15605a() {
        return C4435a.f12118a;
    }

    co() {
    }

    public synchronized SSLSocketFactory m15609b() {
        return this.f12121c;
    }

    public synchronized boolean m15610c() {
        return this.f12121c != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m15608a(android.content.Context r5, java.lang.String r6, java.lang.String r7) {
        /*
        r4 = this;
        r1 = 0;
        r0 = 1;
        monitor-enter(r4);
        r2 = android.text.TextUtils.isEmpty(r7);	 Catch:{ all -> 0x0076 }
        if (r2 == 0) goto L_0x005c;
    L_0x0009:
        r2 = "https://certificate.mobile.yandex.net/api/v1/pins";
        r3 = r2;
    L_0x000d:
        r2 = android.text.TextUtils.isEmpty(r6);	 Catch:{ all -> 0x0076 }
        if (r2 != 0) goto L_0x0074;
    L_0x0013:
        r2 = r4.m15610c();	 Catch:{ all -> 0x0076 }
        if (r2 == 0) goto L_0x0072;
    L_0x0019:
        r2 = r4.f12119a;	 Catch:{ all -> 0x0076 }
        r2 = r3.equals(r2);	 Catch:{ all -> 0x0076 }
        if (r2 == 0) goto L_0x0072;
    L_0x0021:
        r2 = r0;
    L_0x0022:
        if (r2 != 0) goto L_0x0074;
    L_0x0024:
        if (r0 == 0) goto L_0x005a;
    L_0x0026:
        r4.f12120b = r6;	 Catch:{ all -> 0x0076 }
        r4.f12119a = r3;	 Catch:{ all -> 0x0076 }
        r0 = new com.yandex.metrica.impl.ob.fd;	 Catch:{ all -> 0x0076 }
        r1 = r4.f12122d;	 Catch:{ all -> 0x0076 }
        r2 = 1;
        r3 = 1;
        r0.<init>(r1, r2, r3);	 Catch:{ all -> 0x0076 }
        r1 = m15607d();	 Catch:{ all -> 0x0076 }
        if (r1 == 0) goto L_0x005a;
    L_0x0039:
        r2 = new java.util.ArrayList;	 Catch:{ all -> 0x0076 }
        r2.<init>();	 Catch:{ all -> 0x0076 }
        r2.add(r1);	 Catch:{ all -> 0x0076 }
        r1 = r4.f12119a;	 Catch:{ all -> 0x0076 }
        r0.m15992a(r1, r2);	 Catch:{ all -> 0x0076 }
        r1 = new com.yandex.metrica.impl.ob.ew;	 Catch:{ all -> 0x0076 }
        r1.<init>(r5, r0);	 Catch:{ all -> 0x0076 }
        r0 = new com.yandex.metrica.impl.ob.ez;	 Catch:{ Exception -> 0x0079 }
        r0.<init>(r1);	 Catch:{ Exception -> 0x0079 }
        r0 = r0.m15976a();	 Catch:{ Exception -> 0x0079 }
        r0 = r0.getSocketFactory();	 Catch:{ Exception -> 0x0079 }
        r4.f12121c = r0;	 Catch:{ Exception -> 0x0079 }
    L_0x005a:
        monitor-exit(r4);
        return;
    L_0x005c:
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0076 }
        r2.<init>();	 Catch:{ all -> 0x0076 }
        r2 = r2.append(r7);	 Catch:{ all -> 0x0076 }
        r3 = "/api/v1/pins";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0076 }
        r2 = r2.toString();	 Catch:{ all -> 0x0076 }
        r3 = r2;
        goto L_0x000d;
    L_0x0072:
        r2 = r1;
        goto L_0x0022;
    L_0x0074:
        r0 = r1;
        goto L_0x0024;
    L_0x0076:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x0079:
        r0 = move-exception;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.co.a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static X509Certificate m15607d() {
        try {
            String[] a = C4389a.m15137a();
            if (a != null && a.length > 0) {
                return ex.m15975a(a[0]);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
