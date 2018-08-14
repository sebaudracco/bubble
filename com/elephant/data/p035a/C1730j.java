package com.elephant.data.p035a;

import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import com.elephant.data.p041c.C1732a;
import com.elephant.data.p041c.C1735d;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;

public final class C1730j {
    public static final String f3550a = C1814b.kq;
    private static UriMatcher f3551c = new UriMatcher(-1);
    private static String f3552d = C1814b.kr;
    private static String f3553e = C1814b.ks;
    private static C1730j f3554f;
    private volatile long f3555b = 0;
    private byte[] f3556g = new byte[0];
    private C1735d f3557h;

    static {
        String str = C1814b.kp;
    }

    private C1730j() {
    }

    public static C1730j m4984a() {
        if (f3554f == null) {
            f3554f = new C1730j();
        }
        return f3554f;
    }

    public static String m4985b() {
        return null;
    }

    public final int m4986a(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        String str2 = null;
        switch (f3551c.match(uri)) {
            case 1:
                str2 = C1732a.f3563a;
                break;
        }
        if (str2 != null) {
            try {
                return this.f3557h.m4994a(str2, contentValues, str, strArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public final int m4987a(Uri uri, String str, String[] strArr) {
        String str2 = null;
        switch (f3551c.match(uri)) {
            case 1:
                str2 = C1732a.f3563a;
                break;
        }
        if (str2 != null) {
            try {
                return this.f3557h.m4995a(str2, str, strArr);
            } catch (OutOfMemoryError e) {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.database.Cursor m4988a(android.net.Uri r9, java.lang.String[] r10, java.lang.String r11, java.lang.String[] r12, java.lang.String r13) {
        /*
        r8 = this;
        r6 = 0;
        r7 = r8.f3556g;
        monitor-enter(r7);
        r0 = r8.f3555b;	 Catch:{ all -> 0x0043 }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x001c;
    L_0x000c:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0043 }
        r2 = r8.f3555b;	 Catch:{ all -> 0x0043 }
        r0 = r0 - r2;
        r2 = 100;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x001c;
    L_0x0019:
        monitor-exit(r7);	 Catch:{ all -> 0x0043 }
        r0 = r6;
    L_0x001b:
        return r0;
    L_0x001c:
        r0 = f3551c;	 Catch:{ all -> 0x0043 }
        r0 = r0.match(r9);	 Catch:{ all -> 0x0043 }
        switch(r0) {
            case 1: goto L_0x0046;
            default: goto L_0x0025;
        };
    L_0x0025:
        r1 = r6;
    L_0x0026:
        if (r1 == 0) goto L_0x004f;
    L_0x0028:
        r0 = r8.f3557h;	 Catch:{ Exception -> 0x0049 }
        r2 = r10;
        r3 = r11;
        r4 = r12;
        r5 = r13;
        r6 = r0.m4997a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0049 }
        if (r6 == 0) goto L_0x0040;
    L_0x0034:
        r0 = r6.getCount();	 Catch:{ Exception -> 0x0049 }
        if (r0 <= 0) goto L_0x0040;
    L_0x003a:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0049 }
        r8.f3555b = r0;	 Catch:{ Exception -> 0x0049 }
    L_0x0040:
        r0 = r6;
    L_0x0041:
        monitor-exit(r7);	 Catch:{ all -> 0x0043 }
        goto L_0x001b;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        r1 = com.elephant.data.p041c.C1732a.f3563a;	 Catch:{ all -> 0x0043 }
        goto L_0x0026;
    L_0x0049:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0043 }
        r0 = r6;
        goto L_0x0041;
    L_0x004f:
        r0 = r6;
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.a.j.a(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    public final Uri m4989a(Uri uri, ContentValues contentValues) {
        String str;
        switch (f3551c.match(uri)) {
            case 1:
                str = C1732a.f3563a;
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            try {
                if (this.f3557h.m4996a(str, contentValues) > 0) {
                    return uri;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final void m4990a(Context context) {
        f3551c.addURI(C1816d.m5329t(context), f3550a, 1);
        f3551c.addURI(C1816d.m5329t(context), f3552d, 2);
        f3551c.addURI(C1816d.m5329t(context), f3553e, 3);
        this.f3557h = new C1735d(context);
    }
}
