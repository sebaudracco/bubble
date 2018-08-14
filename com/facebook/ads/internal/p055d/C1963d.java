package com.facebook.ads.internal.p055d;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.p071p.p073b.C2085f;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class C1963d {
    private static final String f4555a = C1963d.class.getSimpleName();
    private static C1963d f4556b;
    private final Future<C2085f> f4557c;

    private C1963d(final Context context) {
        this.f4557c = Executors.newSingleThreadExecutor().submit(new Callable<C2085f>(this) {
            final /* synthetic */ C1963d f4554b;

            public C2085f m6182a() {
                return new C2085f(context);
            }

            public /* synthetic */ Object call() {
                return m6182a();
            }
        });
    }

    public static C1963d m6183a(Context context) {
        if (f4556b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (C1963d.class) {
                if (f4556b == null) {
                    f4556b = new C1963d(applicationContext);
                }
            }
        }
        return f4556b;
    }

    @Nullable
    private C2085f m6184a() {
        Throwable e;
        try {
            return (C2085f) this.f4557c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        } catch (TimeoutException e4) {
            e = e4;
        }
        Log.e(f4555a, "Timed out waiting for cache server.", e);
        return null;
    }

    public void m6185a(String str) {
        C2085f a = m6184a();
        if (a != null) {
            a.m6712a(str);
        }
    }

    @Nullable
    public String m6186b(String str) {
        C2085f a = m6184a();
        return a == null ? null : a.m6713b(str);
    }
}
