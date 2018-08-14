package com.fyber;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.fyber.mediation.ProviderRequester;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.p086a.C2408a;
import com.fyber.p086a.C2408a.C2407a;
import com.fyber.p094b.C2504d;
import com.fyber.p094b.p096b.C2493d;
import com.fyber.p094b.p099c.C2499d;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.p097a.C2633l;
import com.fyber.requesters.p097a.p098a.C2595e;
import com.fyber.requesters.p097a.p098a.C2596p;
import com.fyber.requesters.p097a.p098a.C2597a;
import com.fyber.requesters.p097a.p098a.C2598b;
import com.fyber.requesters.p097a.p098a.C2606i;
import com.fyber.requesters.p097a.p098a.C2610j.C2609a;
import com.fyber.requesters.p097a.p098a.C2616n;
import com.fyber.requesters.p097a.p098a.C2616n.C2615a;
import com.fyber.requesters.p097a.p098a.C2617o;
import com.fyber.requesters.p097a.p098a.C2618q;
import com.fyber.utils.C2647e;
import com.fyber.utils.C2656g;
import com.fyber.utils.C2665m;
import com.fyber.utils.C2683w;
import com.fyber.utils.FyberLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* compiled from: Configurations */
public final class C2409a {
    public static C2409a f5994a = new C2409a();
    static final Handler f5995b = new Handler(Looper.getMainLooper());
    final Fyber$Settings f5996c;
    C2408a f5997d;
    C2407a f5998e;
    private final C2656g f5999f;
    private final C2504d f6000g;
    private List<C2596p<C2580a>> f6001h;
    private List<C2595e<C2580a>> f6002i;
    private C2616n f6003j;
    private C2633l f6004k;
    private C2499d f6005l;
    private C2493d f6006m;

    private C2409a() {
        this.f5996c = Fyber$Settings.f5981a;
        this.f5999f = null;
        this.f6000g = null;
        this.f6001h = null;
        this.f6002i = null;
        this.f6005l = null;
        this.f6006m = null;
        this.f5997d = C2408a.f5990a;
    }

    public C2409a(@NonNull String str, @NonNull Context context) {
        if (C2656g.m8490f()) {
            if (C2665m.m8524b(21)) {
                CookieSyncManager.createInstance(context);
            }
            C2647e.m8471a(context);
            this.f5996c = new Fyber$Settings();
            this.f6000g = new C2504d();
            this.f6003j = new C2615a().m8384a();
            this.f6004k = new C2633l(context);
            C2618q c2618q = new C2618q();
            C2597a c2597a = new C2597a();
            C2598b c2598b = new C2598b();
            C2606i c2606i = new C2606i();
            C2617o c2617o = new C2617o();
            this.f6001h = new ArrayList();
            this.f6002i = new ArrayList();
            this.f6001h.add(c2597a);
            this.f6001h.add(c2598b);
            this.f6001h.add(c2606i);
            this.f6001h.add(c2617o);
            this.f6002i.add(c2618q);
            this.f6002i.add(c2597a);
            this.f6002i.add(c2598b);
            this.f6002i.add(c2606i);
            WeakReference weakReference = new WeakReference(context);
            this.f6005l = new C2499d(weakReference);
            this.f6006m = new C2493d(weakReference);
        } else {
            if (FyberLogger.isLogging()) {
                FyberLogger.m8451i("Configurations", RequestError.DEVICE_NOT_SUPPORTED.getDescription());
            } else {
                Log.i("Configurations", RequestError.DEVICE_NOT_SUPPORTED.getDescription());
            }
            this.f5996c = Fyber$Settings.f5981a;
            this.f6000g = null;
            this.f6001h = null;
            this.f6002i = null;
            this.f6005l = null;
            this.f6006m = null;
        }
        this.f5997d = C2408a.f5990a;
        this.f5998e = new C2407a(str).m7588b(C2683w.m8583a(context));
        this.f5999f = C2656g.m8482a(context);
    }

    public final C2656g m7597a() {
        return this.f5999f;
    }

    public final Fyber$Settings m7601b() {
        return this.f5996c;
    }

    public final Map<String, String> m7602c() {
        return this.f5996c.f5982b;
    }

    public final C2616n m7603d() {
        return this.f6003j;
    }

    public final C2633l m7604e() {
        return this.f6004k;
    }

    public final <T> Future<T> m7599a(Callable<T> callable) {
        return this.f6000g.submit(callable);
    }

    public final void m7600a(Runnable runnable) {
        this.f6000g.execute(runnable);
    }

    public final <R, E extends Exception> C2609a<R, E> m7596a(ProviderRequester<R, E> providerRequester) {
        return new C2609a(providerRequester).m8369a(this.f6001h).m8371b(this.f6002i);
    }

    public final C2499d m7605f() {
        return this.f6005l;
    }

    public final C2493d m7606g() {
        return this.f6006m;
    }

    public final boolean m7607h() {
        return this.f5997d != C2408a.f5990a;
    }

    public final C2408a m7608i() {
        return this.f5997d;
    }

    public final Object m7598a(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case -1806042539:
                if (str.equals("CLOSE_ON_REDIRECT")) {
                    obj = null;
                    break;
                }
                break;
            case -1153623547:
                if (str.equals("SHOULD_NOTIFY_ON_USER_ENGAGED")) {
                    obj = 2;
                    break;
                }
                break;
            case 87151057:
                if (str.equals("NOTIFY_USER_ON_REWARD")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return Boolean.valueOf(this.f5996c.f5985e);
            case 1:
                return Boolean.valueOf(this.f5996c.f5984d);
            case 2:
                return Boolean.valueOf(this.f5996c.f5983c);
            default:
                return null;
        }
    }

    public static void m7595b(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f5995b.post(runnable);
        }
    }
}
