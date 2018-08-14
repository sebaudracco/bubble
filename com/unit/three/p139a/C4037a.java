package com.unit.three.p139a;

import android.content.Context;
import android.content.Intent;
import com.unit.three.p138b.C4053c;
import com.unit.three.p139a.C4042b.C4038a;
import com.unit.three.p139a.C4042b.C4041d;
import com.unit.three.p139a.C4047d.C4035c;
import com.unit.three.p139a.C4049f.C4036a;
import java.util.concurrent.LinkedBlockingQueue;

public class C4037a implements C4035c, C4036a {
    private static C4037a f9323a;

    private C4037a() {
    }

    public static C4037a m12449a() {
        if (f9323a == null) {
            synchronized (C4037a.class) {
                if (f9323a == null) {
                    f9323a = new C4037a();
                }
            }
        }
        return f9323a;
    }

    public static void m12450a(long j, int i, boolean z) {
        C4047d.m12474a().m12486a(j, i, z);
    }

    public static void m12451a(Context context) {
        C4042b.m12462a().m12467b(context);
    }

    public static void m12452a(C4041d c4041d) {
        C4042b.m12462a().m12466a(c4041d);
    }

    public static void m12453a(Throwable th) {
        C4049f.m12492a().m12494a(th);
    }

    public static void m12454a(LinkedBlockingQueue linkedBlockingQueue) {
        C4047d.m12474a().m12488a(linkedBlockingQueue);
    }

    public static void m12455a(LinkedBlockingQueue linkedBlockingQueue, boolean z) {
        C4047d.m12474a().m12489a(linkedBlockingQueue, z);
    }

    public static void m12456b() {
        C4042b.m12462a();
        C4042b.m12464a(C4053c.m12503a().m12515b(), C4038a.EVENT_RECONNECT, new Intent(C4038a.EVENT_RECONNECT.m12460a()));
    }

    public static void m12457c() {
        C4047d.m12474a().m12490b();
    }

    public final void mo6913d() {
        C4037a.m12449a();
        C4037a.m12456b();
    }

    public final void mo6914e() {
        C4037a.m12455a(null, true);
    }
}
