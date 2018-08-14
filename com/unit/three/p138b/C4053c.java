package com.unit.three.p138b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.unit.three.CoreService;
import com.unit.three.p139a.C4037a;
import com.unit.three.p139a.C4042b;
import com.unit.three.p139a.C4047d;
import com.unit.three.p139a.C4047d.C4035c;
import com.unit.three.p139a.C4049f;
import com.unit.three.p139a.C4049f.C4036a;
import com.unit.three.p140d.C4083a;
import com.unit.three.p141c.C4076c;
import com.unit.three.p141c.C4078f;
import com.unit.three.p143e.C4090d;

public class C4053c {
    private static C4053c f9364a;
    private static volatile boolean f9365c = false;
    private Context f9366b;

    private C4053c() {
    }

    public static C4053c m12503a() {
        if (f9364a == null) {
            synchronized (C4053c.class) {
                if (f9364a == null) {
                    f9364a = new C4053c();
                }
            }
        }
        return f9364a;
    }

    public static void m12504a(Context context) {
        C4036a a = C4037a.m12449a();
        C4042b.m12462a().m12465a(context);
        C4047d.m12474a().m12487a((C4035c) a);
        C4049f.m12492a().m12493a(a);
    }

    static /* synthetic */ boolean m12506a(C4053c c4053c, String str, int i) {
        return (TextUtils.isEmpty(str) || i == -1) ? false : true;
    }

    public static void m12507b(Context context) {
        C4037a.m12449a();
        C4037a.m12451a(context);
    }

    static /* synthetic */ void m12508b(C4053c c4053c, Context context) {
        if (C4053c.m12512e(context)) {
            context.startService(new Intent(context, CoreService.class));
        } else {
            new Thread(new C4055e(c4053c, context)).start();
        }
    }

    static /* synthetic */ void m12509b(C4053c c4053c, String str, int i) {
        C4067j.m12536a().m12545a(str, i);
        C4059h.m12523a().m12525a(new C4056f(c4053c));
        new C4076c(str).m12555b();
    }

    public static long m12510c() {
        long k = C4078f.m12575k();
        if (k == -1) {
            return 0;
        }
        C4083a.m12599a(C4053c.m12503a().f9366b);
        long e = C4083a.m12597a().m12581e();
        k = System.currentTimeMillis() - k;
        return e - k > 0 ? e - k : 0;
    }

    public static void m12511d() {
        try {
            C4067j.m12536a().m12546b();
        } catch (Exception e) {
        }
    }

    private static boolean m12512e(Context context) {
        try {
            return context.getPackageManager().resolveService(new Intent(context, CoreService.class), 65536) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    private void m12513f(Context context) {
        C4083a.m12599a(this.f9366b).m12601a(new C4058g(this, context));
    }

    public final void m12514a(Context context, String str, int i, String str2) {
        C4067j.m12536a().m12546b();
        Object obj = (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? null : 1;
        if (obj != null) {
            this.f9366b = context.getApplicationContext();
            f9365c = str.equals(C4078f.m12576l());
            C4078f.m12566a(C4053c.m12503a().f9366b, "_proxy_address", str);
            C4078f.m12566a(C4053c.m12503a().f9366b, "_proxy_port", Integer.valueOf(11113));
            C4078f.m12566a(C4053c.m12503a().f9366b, "_proxy_app_key", str2);
            C4059h.m12523a();
            C4059h.m12524a(context);
            if (C4090d.m12625a(context)) {
                m12513f(context);
            } else {
                C4059h.m12523a().m12525a(new C4054d(this, context));
            }
            Log.i("ThreeSDK", "init successed");
        }
    }

    public final Context m12515b() {
        return this.f9366b;
    }

    public final void m12516c(Context context) {
        this.f9366b = context.getApplicationContext();
    }

    public final String m12517d(Context context) {
        if (context == null) {
            return "";
        }
        this.f9366b = context.getApplicationContext();
        return C4078f.m12572h();
    }
}
