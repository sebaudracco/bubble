package com.facebook.ads.internal.p052j;

import java.util.ArrayList;
import java.util.List;

public class C1999b {
    private static final List<C1998a> f4700a = new ArrayList();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m6320a() {
        /*
        r1 = f4700a;
        monitor-enter(r1);
        r0 = f4700a;	 Catch:{ all -> 0x003a }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x003a }
        if (r0 == 0) goto L_0x0010;
    L_0x000b:
        r0 = "";
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
    L_0x000f:
        return r0;
    L_0x0010:
        r0 = new java.util.ArrayList;	 Catch:{ all -> 0x003a }
        r2 = f4700a;	 Catch:{ all -> 0x003a }
        r0.<init>(r2);	 Catch:{ all -> 0x003a }
        r2 = f4700a;	 Catch:{ all -> 0x003a }
        r2.clear();	 Catch:{ all -> 0x003a }
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
        r1 = new org.json.JSONArray;
        r1.<init>();
        r2 = r0.iterator();
    L_0x0026:
        r0 = r2.hasNext();
        if (r0 == 0) goto L_0x003d;
    L_0x002c:
        r0 = r2.next();
        r0 = (com.facebook.ads.internal.p052j.C1998a) r0;
        r0 = r0.m6319a();
        r1.put(r0);
        goto L_0x0026;
    L_0x003a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
        throw r0;
    L_0x003d:
        r0 = r1.toString();
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.ads.internal.j.b.a():java.lang.String");
    }

    public static void m6321a(C1998a c1998a) {
        synchronized (f4700a) {
            f4700a.add(c1998a);
        }
    }
}
