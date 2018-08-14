package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.ad.C4309a;
import com.yandex.metrica.impl.ob.C4325i;
import com.yandex.metrica.impl.ob.C4350j;
import com.yandex.metrica.impl.ob.C4484g;
import com.yandex.metrica.impl.ob.C4486k;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class az implements C4309a {
    private final C4347s f11726a;
    private final ad f11727b;
    private final Object f11728c = new Object();
    private final ExecutorService f11729d = Executors.newSingleThreadExecutor();

    public interface C4345c {
        C4372h mo7012a(C4372h c4372h);
    }

    private class C4348b implements Callable<Void> {
        final C4352d f11717b;
        boolean f11718c;
        final /* synthetic */ az f11719d;

        class C43511 implements C4350j<ao> {
            final /* synthetic */ C4348b f11721a;

            C43511(C4348b c4348b) {
                this.f11721a = c4348b;
            }

            public /* bridge */ /* synthetic */ void mo7019a(C4325i c4325i) {
                m14772a();
            }

            public void m14772a() {
                this.f11721a.f11718c = true;
            }
        }

        public /* synthetic */ Object call() throws Exception {
            return mo7016a();
        }

        private C4348b(az azVar, C4352d c4352d) {
            this.f11719d = azVar;
            this.f11717b = c4352d;
            C4484g.m16084a().m16091a(this, ao.class, C4486k.m16096a(new C43511(this)).m16095a());
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void mo7016a() {
            /*
            r4 = this;
            r3 = 0;
            r0 = 0;
        L_0x0002:
            r1 = r4.f11719d;	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            r1 = r1.f11727b;	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            r1 = r1.m14498e();	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            if (r1 == 0) goto L_0x001e;
        L_0x000e:
            r2 = r4.f11717b;	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            r1 = r4.m14766a(r1, r2);	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            if (r1 == 0) goto L_0x001e;
        L_0x0016:
            r0 = com.yandex.metrica.impl.ob.C4484g.m16084a();
            r0.m16090a(r4);
        L_0x001d:
            return r3;
        L_0x001e:
            r1 = r4.mo7017b();	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            r0 = r0 + 1;
            if (r1 == 0) goto L_0x002d;
        L_0x0026:
            r1 = r4.f11718c;	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
            if (r1 != 0) goto L_0x002d;
        L_0x002a:
            r1 = 3;
            if (r0 < r1) goto L_0x0002;
        L_0x002d:
            r0 = com.yandex.metrica.impl.ob.C4484g.m16084a();
            r0.m16090a(r4);
            goto L_0x001d;
        L_0x0035:
            r0 = move-exception;
            r0 = com.yandex.metrica.impl.ob.C4484g.m16084a();
            r0.m16090a(r4);
            goto L_0x001d;
        L_0x003e:
            r0 = move-exception;
            r1 = com.yandex.metrica.impl.ob.C4484g.m16084a();
            r1.m16090a(r4);
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.az.b.a():java.lang.Void");
        }

        boolean mo7017b() {
            this.f11719d.f11727b.m14493a();
            synchronized (this.f11719d.f11728c) {
                if (!this.f11719d.f11727b.m14497d()) {
                    try {
                        this.f11719d.f11728c.wait(500, 0);
                    } catch (InterruptedException e) {
                        this.f11719d.f11728c.notifyAll();
                        this.f11719d.f11729d.shutdownNow();
                    }
                }
            }
            return true;
        }

        private boolean m14766a(IMetricaService iMetricaService, C4352d c4352d) {
            try {
                this.f11719d.f11726a.mo7014a(iMetricaService, c4352d.m14779b(), c4352d.f11723b);
                return true;
            } catch (RemoteException e) {
                return false;
            }
        }
    }

    private class C4349a extends C4348b {
        final /* synthetic */ az f11720a;

        public /* synthetic */ Object call() throws Exception {
            return mo7016a();
        }

        private C4349a(az azVar, C4352d c4352d) {
            this.f11720a = azVar;
            super(c4352d);
        }

        public Void mo7016a() {
            this.f11720a.f11727b.m14495b();
            return super.mo7016a();
        }

        boolean mo7017b() {
            C4352d c4352d = this.b;
            Context b = this.f11720a.f11726a.mo7015b();
            Intent c = be.m14892c(b);
            c.putExtras(c4352d.f11722a.m15052a(c4352d.f11723b.mo7025c()));
            b.startService(c);
            return false;
        }
    }

    public static class C4352d {
        private C4372h f11722a;
        private aw f11723b;
        private boolean f11724c = false;
        private C4345c f11725d;

        C4352d(C4372h c4372h, aw awVar) {
            this.f11722a = c4372h;
            this.f11723b = awVar;
        }

        C4352d m14777a(C4345c c4345c) {
            this.f11725d = c4345c;
            return this;
        }

        C4352d m14778a(boolean z) {
            this.f11724c = z;
            return this;
        }

        aw m14776a() {
            return this.f11723b;
        }

        C4372h m14779b() {
            return this.f11725d != null ? this.f11725d.mo7012a(this.f11722a) : this.f11722a;
        }

        boolean m14780c() {
            return this.f11724c;
        }

        public String toString() {
            return "ReportToSend{mReport=" + this.f11722a + ", mEnvironment=" + this.f11723b + ", mCrash=" + this.f11724c + ", mAction=" + this.f11725d + '}';
        }
    }

    public az(C4347s c4347s) {
        this.f11726a = c4347s;
        this.f11727b = c4347s.mo7013a();
        this.f11727b.m14494a((C4309a) this);
    }

    public Future<Void> m14785a(C4352d c4352d) {
        return this.f11729d.submit(c4352d.m14780c() ? new C4349a(c4352d) : new C4348b(c4352d));
    }

    public void mo7020a() {
        synchronized (this.f11728c) {
            this.f11728c.notifyAll();
        }
    }
}
