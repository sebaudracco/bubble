package com.facebook.ads.internal.p069m;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p056q.p057a.C2122m;
import com.facebook.ads.internal.p056q.p076c.C2147e;
import com.facebook.ads.internal.p062e.C1964a;
import com.facebook.ads.internal.p062e.C1973d;
import com.facebook.ads.internal.p066i.C1992a;
import com.facebook.ads.internal.p069m.C2007a.C2006a;
import java.util.Map;

public class C2014d implements C2012c {
    private static final String f4744a = C2014d.class.getSimpleName();
    private static double f4745b;
    private static String f4746c;
    private static volatile boolean f4747d = false;
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    private static C2014d f4748h;
    private final C2011b f4749e;
    private final C1973d f4750f;
    private final Context f4751g;

    @VisibleForTesting
    private C2014d(Context context) {
        this.f4751g = context.getApplicationContext();
        this.f4750f = new C1973d(context);
        this.f4749e = new C2011b(context, new C2017g(context, this.f4750f));
        this.f4749e.m6398b();
        C2014d.m6415b(context);
    }

    public static C2014d m6413a(Context context) {
        if (f4748h == null) {
            f4748h = new C2014d(context.getApplicationContext());
        }
        return f4748h;
    }

    private void m6414a(final C2007a c2007a) {
        if (c2007a.m6379g()) {
            this.f4750f.m6220a(c2007a.m6373a(), c2007a.m6380h().f4755c, c2007a.m6381i().toString(), c2007a.m6374b(), c2007a.m6375c(), c2007a.m6376d(), c2007a.m6377e(), new C1964a<String>(this) {
                final /* synthetic */ C2014d f4743b;

                public void mo3706a(int i, String str) {
                    super.mo3706a(i, str);
                }

                public void m6411a(String str) {
                    super.mo3707a(str);
                    if (c2007a.m6378f()) {
                        this.f4743b.f4749e.m6397a();
                    } else {
                        this.f4743b.f4749e.m6398b();
                    }
                }
            });
        } else {
            Log.e(f4744a, "Attempting to log an invalid " + c2007a.m6381i() + " event.");
        }
    }

    private static synchronized void m6415b(Context context) {
        synchronized (C2014d.class) {
            if (!f4747d) {
                C1992a.m6302a(context).m6303a();
                C2122m.m6804a();
                f4745b = C2122m.m6805b();
                f4746c = C2122m.m6806c();
                f4747d = true;
            }
        }
    }

    public void mo3708a(String str) {
        new C2147e(this.f4751g).execute(new String[]{str});
    }

    public void mo3709a(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.IMMEDIATE).m6366a(C2016f.IMPRESSION).m6369a(true).m6370a());
        }
    }

    public void mo3710a(String str, Map<String, String> map, String str2, C2015e c2015e) {
        m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(c2015e).m6366a(C2016f.m6426a(str2)).m6369a(true).m6370a());
    }

    public void mo3711b(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.IMMEDIATE).m6366a(C2016f.INVALIDATION).m6369a(false).m6370a());
        }
    }

    public void mo3712c(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.IMMEDIATE).m6366a(C2016f.OPEN_LINK).m6369a(true).m6370a());
        }
    }

    public void mo3713d(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.IMMEDIATE).m6366a(C2016f.VIDEO).m6369a(true).m6370a());
        }
    }

    public void mo3714e(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.DEFERRED).m6366a(C2016f.NATIVE_VIEW).m6369a(false).m6370a());
        }
    }

    public void mo3715f(String str, Map<String, String> map) {
        m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.DEFERRED).m6366a(C2016f.BROWSER_SESSION).m6369a(false).m6370a());
    }

    public void mo3716g(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.IMMEDIATE).m6366a(C2016f.STORE).m6369a(true).m6370a());
        }
    }

    public void mo3717h(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m6414a(new C2006a().m6367a(str).m6364a(f4745b).m6371b(f4746c).m6368a((Map) map).m6365a(C2015e.DEFERRED).m6366a(C2016f.CLOSE).m6369a(true).m6370a());
        }
    }
}
