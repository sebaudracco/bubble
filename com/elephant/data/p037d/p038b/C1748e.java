package com.elephant.data.p037d.p038b;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.elephant.data.p048g.C1814b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class C1748e {
    private static final Uri f3611a = Uri.parse(C1814b.gp);
    private static C1748e f3612c;
    private static long f3613n = 0;
    private Context f3614b;
    private List f3615d = new ArrayList();
    private List f3616e = new ArrayList();
    private Map f3617f = new HashMap();
    private final ArrayList f3618g = new ArrayList();
    private final Handler f3619h;
    private final ContentObserver f3620i;
    private boolean f3621j;
    private Timer f3622k;
    private C1749f f3623l;
    private C1750g f3624m = new C1750g(this);

    static {
        String str = C1814b.gq;
    }

    private C1748e(Context context) {
        this.f3614b = context;
        this.f3619h = new Handler(new C1751h(context));
        this.f3620i = new C1745b(context, this.f3619h);
    }

    public static C1748e m5034a(Context context) {
        if (f3612c == null) {
            synchronized (C1748e.class) {
                if (f3612c == null) {
                    f3612c = new C1748e(context.getApplicationContext());
                }
            }
        }
        return f3612c;
    }

    private static void m5036a(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m5037a(java.lang.String r8) {
        /*
        r7 = this;
        r6 = 0;
        r0 = r7.f3614b;
        r0 = com.elephant.data.p046f.p047a.C1800g.m5175a(r0);
        r0.m5176a();
        r0 = r7.f3614b;	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r0 = r0.getContentResolver();	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r1.<init>();	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r2 = com.elephant.data.p048g.C1814b.gs;	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r1 = r1.append(r8);	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r1 = android.net.Uri.parse(r1);	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x00bb, all -> 0x00b8 }
        if (r1 != 0) goto L_0x0035;
    L_0x0031:
        com.elephant.data.p037d.p038b.C1748e.m5036a(r1);
    L_0x0034:
        return;
    L_0x0035:
        r1.moveToNext();	 Catch:{ Throwable -> 0x00a2 }
        r0 = 9;
        r0 = r1.getString(r0);	 Catch:{ Throwable -> 0x00a2 }
        r2 = 15;
        r2 = r1.getString(r2);	 Catch:{ Throwable -> 0x00a2 }
        r3 = 20;
        r3 = r1.getString(r3);	 Catch:{ Throwable -> 0x00a2 }
        r4 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x00a2 }
        if (r4 != 0) goto L_0x00b3;
    L_0x0050:
        r4 = android.net.Uri.parse(r2);	 Catch:{ Throwable -> 0x00a2 }
        r5 = com.elephant.data.p048g.C1814b.gr;	 Catch:{ Throwable -> 0x00a2 }
        r4 = r4.getQueryParameter(r5);	 Catch:{ Throwable -> 0x00a2 }
        r5 = new com.elephant.data.d.b.a;	 Catch:{ Throwable -> 0x00a2 }
        r5.<init>(r3);	 Catch:{ Throwable -> 0x00a2 }
        r5.f3602b = r0;	 Catch:{ Throwable -> 0x00a2 }
        r5.f3601a = r4;	 Catch:{ Throwable -> 0x00a2 }
        r5.f3603c = r2;	 Catch:{ Throwable -> 0x00a2 }
        r0 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x00a2 }
        if (r0 != 0) goto L_0x00b3;
    L_0x006b:
        r2 = r7.f3615d;	 Catch:{ Throwable -> 0x00a2 }
        monitor-enter(r2);	 Catch:{ Throwable -> 0x00a2 }
        r0 = r7.f3615d;	 Catch:{ all -> 0x00aa }
        r0 = r0.contains(r3);	 Catch:{ all -> 0x00aa }
        if (r0 == 0) goto L_0x007b;
    L_0x0076:
        monitor-exit(r2);	 Catch:{ all -> 0x00aa }
        com.elephant.data.p037d.p038b.C1748e.m5036a(r1);
        goto L_0x0034;
    L_0x007b:
        r0 = r7.f3617f;	 Catch:{ all -> 0x00aa }
        r0.put(r3, r5);	 Catch:{ all -> 0x00aa }
        r0 = r7.f3615d;	 Catch:{ all -> 0x00aa }
        r0.add(r3);	 Catch:{ all -> 0x00aa }
        monitor-exit(r2);	 Catch:{ all -> 0x00aa }
        r2 = r7.f3618g;	 Catch:{ Throwable -> 0x00a2 }
        monitor-enter(r2);	 Catch:{ Throwable -> 0x00a2 }
        r0 = r7.f3618g;	 Catch:{ all -> 0x009f }
        r3 = r0.iterator();	 Catch:{ all -> 0x009f }
    L_0x008f:
        r0 = r3.hasNext();	 Catch:{ all -> 0x009f }
        if (r0 == 0) goto L_0x00b2;
    L_0x0095:
        r0 = r3.next();	 Catch:{ all -> 0x009f }
        r0 = (com.elephant.data.p037d.p038b.C1725i) r0;	 Catch:{ all -> 0x009f }
        r0.mo3533a(r5);	 Catch:{ all -> 0x009f }
        goto L_0x008f;
    L_0x009f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x009f }
        throw r0;	 Catch:{ Throwable -> 0x00a2 }
    L_0x00a2:
        r0 = move-exception;
    L_0x00a3:
        r0.printStackTrace();	 Catch:{ all -> 0x00ad }
        com.elephant.data.p037d.p038b.C1748e.m5036a(r1);
        goto L_0x0034;
    L_0x00aa:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x00aa }
        throw r0;	 Catch:{ Throwable -> 0x00a2 }
    L_0x00ad:
        r0 = move-exception;
    L_0x00ae:
        com.elephant.data.p037d.p038b.C1748e.m5036a(r1);
        throw r0;
    L_0x00b2:
        monitor-exit(r2);	 Catch:{ all -> 0x009f }
    L_0x00b3:
        com.elephant.data.p037d.p038b.C1748e.m5036a(r1);
        goto L_0x0034;
    L_0x00b8:
        r0 = move-exception;
        r1 = r6;
        goto L_0x00ae;
    L_0x00bb:
        r0 = move-exception;
        r1 = r6;
        goto L_0x00a3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.elephant.data.d.b.e.a(java.lang.String):void");
    }

    private void m5041c() {
        if (this.f3623l != null) {
            synchronized (this.f3623l) {
                this.f3623l.cancel();
                this.f3623l.f3625a = false;
                this.f3623l = null;
            }
        }
        if (this.f3622k != null) {
            this.f3622k.cancel();
            this.f3622k = null;
        }
    }

    public final void m5045a() {
        this.f3614b.getContentResolver().registerContentObserver(f3611a, true, this.f3620i);
    }

    public final void m5046a(Uri uri) {
        String str;
        if (this.f3621j) {
            try {
                f3613n = System.currentTimeMillis();
                if (!(this.f3617f.size() == 0 || this.f3617f.keySet() == null || this.f3617f.keySet().size() == 0)) {
                    str = (String) this.f3617f.keySet().toArray()[0];
                    if (this.f3623l == null || !this.f3623l.f3625a) {
                        m5041c();
                        if (this.f3623l == null) {
                            this.f3623l = new C1749f(this, str);
                        }
                        if (this.f3622k == null) {
                            this.f3622k = new Timer();
                        }
                        if (!(this.f3622k == null || this.f3623l == null)) {
                            synchronized (this.f3623l) {
                                if (!this.f3623l.f3625a) {
                                    this.f3623l.f3625a = true;
                                    this.f3622k.schedule(this.f3623l, 0, 1500);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (uri != null) {
            List pathSegments = uri.getPathSegments();
            if (pathSegments != null && pathSegments.size() > 1) {
                str = (String) pathSegments.get(1);
                synchronized (this.f3616e) {
                    if (this.f3616e.contains(str)) {
                        return;
                    }
                    this.f3616e.add(str);
                    m5037a(str);
                }
            }
        }
    }

    public final void m5047a(C1725i c1725i, boolean z) {
        this.f3621j = false;
        if (!this.f3618g.contains(c1725i)) {
            synchronized (this.f3618g) {
                this.f3618g.add(c1725i);
            }
        }
    }
}
