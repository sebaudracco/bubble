package com.facebook.ads.internal.p055d;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class C1960b {
    private static final String f4543a = C1960b.class.getSimpleName();
    private static final ExecutorService f4544b = Executors.newSingleThreadExecutor();
    private static final ExecutorService f4545c = Executors.newFixedThreadPool(5);
    private final Handler f4546d = new Handler();
    private final C1961c f4547e;
    private final C1963d f4548f;
    private final List<Callable<Boolean>> f4549g;

    private class C1958a implements Callable<Boolean> {
        final /* synthetic */ C1960b f4537a;
        private final String f4538b;
        private final int f4539c;
        private final int f4540d;

        public C1958a(C1960b c1960b, String str, int i, int i2) {
            this.f4537a = c1960b;
            this.f4538b = str;
            this.f4539c = i;
            this.f4540d = i2;
        }

        public Boolean m6162a() {
            this.f4537a.f4547e.m6181a(this.f4538b, this.f4539c, this.f4540d);
            return Boolean.valueOf(true);
        }

        public /* synthetic */ Object call() {
            return m6162a();
        }
    }

    private class C1959b implements Callable<Boolean> {
        final /* synthetic */ C1960b f4541a;
        private final String f4542b;

        public C1959b(C1960b c1960b, String str) {
            this.f4541a = c1960b;
            this.f4542b = str;
        }

        public Boolean m6163a() {
            this.f4541a.f4548f.m6185a(this.f4542b);
            return Boolean.valueOf(true);
        }

        public /* synthetic */ Object call() {
            return m6163a();
        }
    }

    public C1960b(Context context) {
        this.f4547e = C1961c.m6174a(context);
        this.f4548f = C1963d.m6183a(context);
        this.f4549g = new ArrayList();
    }

    public void m6169a(@Nullable final C1850a c1850a) {
        final ArrayList arrayList = new ArrayList(this.f4549g);
        f4544b.execute(new Runnable(this) {
            final /* synthetic */ C1960b f4536c;

            class C19551 implements Runnable {
                final /* synthetic */ C19571 f4532a;

                C19551(C19571 c19571) {
                    this.f4532a = c19571;
                }

                public void run() {
                    if (c1850a != null) {
                        c1850a.mo3588b();
                    }
                }
            }

            class C19562 implements Runnable {
                final /* synthetic */ C19571 f4533a;

                C19562(C19571 c19571) {
                    this.f4533a = c19571;
                }

                public void run() {
                    if (c1850a != null) {
                        c1850a.mo3587a();
                    }
                }
            }

            public void run() {
                Throwable e;
                List<Future> arrayList = new ArrayList(arrayList.size());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(C1960b.f4545c.submit((Callable) it.next()));
                }
                try {
                    for (Future future : arrayList) {
                        future.get();
                    }
                    this.f4536c.f4546d.post(new C19562(this));
                } catch (InterruptedException e2) {
                    e = e2;
                    Log.e(C1960b.f4543a, "Exception while executing cache downloads.", e);
                    this.f4536c.f4546d.post(new C19551(this));
                } catch (ExecutionException e3) {
                    e = e3;
                    Log.e(C1960b.f4543a, "Exception while executing cache downloads.", e);
                    this.f4536c.f4546d.post(new C19551(this));
                }
            }
        });
        this.f4549g.clear();
    }

    public void m6170a(String str) {
        this.f4549g.add(new C1959b(this, str));
    }

    public void m6171a(String str, int i, int i2) {
        this.f4549g.add(new C1958a(this, str, i, i2));
    }

    public String m6172b(String str) {
        return this.f4548f.m6186b(str);
    }
}
