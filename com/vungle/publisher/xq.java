package com.vungle.publisher;

import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.vungle.publisher.cn.c;
import com.vungle.publisher.cz.C1596b;
import com.vungle.publisher.eb.C1603b;
import com.vungle.publisher.ei.a;
import com.vungle.publisher.el.C1607a;
import com.vungle.publisher.log.Logger;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* compiled from: vungle */
public class xq implements Func1<dr<?>, Observable<? extends dr<?>>> {
    @Inject
    C1596b f3455a;
    @Inject
    xy f3456b;
    @Inject
    C1603b f3457c;
    @Inject
    C1607a f3458d;

    public /* synthetic */ Object call(Object obj) {
        return m4866a((dr) obj);
    }

    @Inject
    xq() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public rx.Observable<? extends com.vungle.publisher.dr<?>> m4866a(com.vungle.publisher.dr<?> r7) {
        /*
        r6 = this;
        if (r7 != 0) goto L_0x000b;
    L_0x0002:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "no ad to prepare ";
        r0.<init>(r1);
        throw r0;
    L_0x000b:
        r1 = r7.d();
        r0 = r7.a_();
        r2 = r7.g();
        r3 = "VunglePrepare";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "run PrepareAdRunnable. adId = ";
        r4 = r4.append(r5);
        r4 = r4.append(r1);
        r5 = ", adType = ";
        r4 = r4.append(r5);
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.vungle.publisher.log.Logger.d(r3, r0);
        r0 = "VunglePrepare";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0083 }
        r3.<init>();	 Catch:{ Exception -> 0x0083 }
        r4 = "local ad not prepared. has status: ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0083 }
        r3 = r3.append(r2);	 Catch:{ Exception -> 0x0083 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0083 }
        com.vungle.publisher.log.Logger.d(r0, r3);	 Catch:{ Exception -> 0x0083 }
        r0 = com.vungle.publisher.xq.1.a;	 Catch:{ Exception -> 0x0083 }
        r3 = r2.ordinal();	 Catch:{ Exception -> 0x0083 }
        r0 = r0[r3];	 Catch:{ Exception -> 0x0083 }
        switch(r0) {
            case 1: goto L_0x0069;
            case 2: goto L_0x0069;
            case 3: goto L_0x00aa;
            case 4: goto L_0x00d4;
            case 5: goto L_0x0101;
            default: goto L_0x0061;
        };	 Catch:{ Exception -> 0x0083 }
    L_0x0061:
        r6.m4873b(r7);	 Catch:{ Exception -> 0x0083 }
        r0 = r6.m4877e(r7);	 Catch:{ Exception -> 0x0083 }
    L_0x0068:
        return r0;
    L_0x0069:
        r0 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x0083 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0083 }
        r3.<init>();	 Catch:{ Exception -> 0x0083 }
        r4 = "ad status: ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0083 }
        r2 = r3.append(r2);	 Catch:{ Exception -> 0x0083 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0083 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0083 }
        throw r0;	 Catch:{ Exception -> 0x0083 }
    L_0x0083:
        r0 = move-exception;
        r2 = "VunglePrepare";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "error processing ad.id: ";
        r3 = r3.append(r4);
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.vungle.publisher.log.Logger.e(r2, r1, r0);
        rx.exceptions.Exceptions.propagate(r0);
        r0 = new java.lang.RuntimeException;
        r1 = "could not prepare ad";
        r0.<init>(r1);
        throw r0;
    L_0x00aa:
        r0 = "VunglePrepare";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0083 }
        r2.<init>();	 Catch:{ Exception -> 0x0083 }
        r3 = "ad already ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0083 }
        r3 = com.vungle.publisher.cn.c.f;	 Catch:{ Exception -> 0x0083 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0083 }
        r3 = ", recycling: ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0083 }
        r2 = r2.append(r1);	 Catch:{ Exception -> 0x0083 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0083 }
        com.vungle.publisher.log.Logger.d(r0, r2);	 Catch:{ Exception -> 0x0083 }
        r6.m4875c(r7);	 Catch:{ Exception -> 0x0083 }
    L_0x00d4:
        r0 = "VunglePrepare";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0083 }
        r2.<init>();	 Catch:{ Exception -> 0x0083 }
        r3 = "ad already ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0083 }
        r3 = com.vungle.publisher.cn.c.e;	 Catch:{ Exception -> 0x0083 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0083 }
        r3 = ": ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0083 }
        r2 = r2.append(r1);	 Catch:{ Exception -> 0x0083 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0083 }
        com.vungle.publisher.log.Logger.d(r0, r2);	 Catch:{ Exception -> 0x0083 }
        r0 = rx.Observable.just(r7);	 Catch:{ Exception -> 0x0083 }
        goto L_0x0068;
    L_0x0101:
        r6.m4881i(r7);	 Catch:{ Exception -> 0x0083 }
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.xq.a(com.vungle.publisher.dr):rx.Observable<? extends com.vungle.publisher.dr<?>>");
    }

    void m4873b(dr<?> drVar) {
        this.f3455a.m3632a((dr) drVar);
        drVar.a(c.d);
        drVar.f_();
    }

    void m4875c(dr<?> drVar) {
        String d = drVar.d();
        Logger.d(Logger.PREPARE_TAG, "re-verify prepare_retry_count " + drVar.j() + " for " + "ad" + " " + d);
        m4876d(drVar);
        for (gd a : drVar.k_()) {
            m4871a(a);
        }
        c cVar = c.e;
        Logger.i(Logger.PREPARE_TAG, "re-verified ad and set to " + cVar + ": " + d);
        this.f3455a.m3632a((dr) drVar).a_(Long.valueOf(-1));
        drVar.a(cVar);
        drVar.f_();
    }

    void m4876d(dr<?> drVar) {
        if (!drVar.l_()) {
            throw new RuntimeException("invalid ad structure");
        }
    }

    void m4871a(gd<?> gdVar) {
        if (!gdVar.n()) {
            throw new RuntimeException(gdVar.o() + " re-verification failed for " + "ad_id" + " " + gdVar.f());
        }
    }

    <C extends dr<?>> Observable<C> m4877e(C c) {
        m4876d(c);
        List k_ = c.k_();
        if (!m4882j(c)) {
            return m4867a(k_).doOnNext(m4880h(c)).doOnError(m4879g(c)).flatMap(m4878f(c));
        }
        Logger.d(Logger.PREPARE_TAG, "skipping prepare as ad_token_hash already present");
        m4883k(c);
        m4874b(k_);
        return Observable.just(c);
    }

    Observable<? extends List<gd<?>>> m4867a(List<? extends gd<?>> list) {
        if (list == null || list.size() <= 0) {
            return Observable.just(new ArrayList());
        }
        return Observable.from((Iterable) list).observeOn(Schedulers.io()).flatMap(this.f3456b).doOnError(m4868a()).doOnNext(m4872b()).buffer(list.size());
    }

    public <C extends dr<?>> Func1<List<gd<?>>, Observable<C>> m4878f(C c) {
        return xr.m4884a(c);
    }

    static /* synthetic */ Observable m4864b(dr drVar, List list) {
        for (gd j : list) {
            if (j.j() != a.c) {
                throw new RuntimeException("prepared ad somehow has non-ready viewables");
            }
        }
        return Observable.just(drVar);
    }

    public Action1<Throwable> m4868a() {
        return xs.m4885a();
    }

    public Action1<Throwable> m4879g(dr<?> drVar) {
        return xt.m4886a(this, drVar);
    }

    /* synthetic */ void m4869a(dr drVar, Throwable th) {
        Logger.w(Logger.PREPARE_TAG, "ad prep error: " + th);
        if (th instanceof qn) {
            Logger.w(Logger.PREPARE_TAG, "deleting all ads due to IO failure");
            this.f3457c.m3743b();
            return;
        }
        Logger.d(Logger.PREPARE_TAG, "marking current ad as " + c.b);
        drVar.a(c.b);
        drVar.f_();
    }

    public Action1<gd<?>> m4872b() {
        return xu.m4887a();
    }

    public Action1<List<gd<?>>> m4880h(dr<?> drVar) {
        return xv.m4888a(this, drVar);
    }

    /* synthetic */ void m4870a(dr drVar, List list) {
        Logger.i(Logger.PREPARE_TAG, "ad ready " + drVar.d());
        drVar.a(c.e);
        this.f3455a.m3634a(drVar, Long.valueOf(System.currentTimeMillis()));
        drVar.f_();
    }

    boolean m4881i(dr<?> drVar) {
        String d = drVar.d();
        c g = drVar.g();
        c cVar;
        if (g == c.b) {
            cVar = c.d;
            long currentTimeMillis = System.currentTimeMillis();
            long k = drVar.k();
            if (currentTimeMillis < k) {
                Logger.d(Logger.PREPARE_TAG, "clock change detected; updating ad.id " + d + " " + "status" + " from " + g + " to " + cVar);
                drVar.a(cVar);
            } else {
                currentTimeMillis = (currentTimeMillis - k) / 60000;
                if (currentTimeMillis >= 1440) {
                    Logger.d(Logger.PREPARE_TAG, "retrying " + c.b + " " + "ad" + "." + "id" + " " + d + " after " + currentTimeMillis + BridgeUtil.SPLIT_MARK + Settings.ANALYTICS_COVERAGE_CHECKER_COUNTER + " minutes; updating " + "status" + " from " + g + " to " + cVar);
                    drVar.a(cVar);
                } else {
                    throw new RuntimeException("could not update failed status");
                }
            }
        }
        cVar = g;
        drVar.f_();
        if (cVar != c.b) {
            return true;
        }
        return false;
    }

    public boolean m4882j(dr<?> drVar) {
        if (this.f3458d.a((String) drVar.m_().c_(), drVar.m_().m(), new c[]{c.e})) {
            return true;
        }
        return false;
    }

    void m4883k(dr<?> drVar) {
        c cVar = c.e;
        Logger.v(Logger.PREPARE_TAG, "updating and reusing cacheables for ad and set to " + cVar + ": " + drVar.d());
        this.f3455a.m3632a((dr) drVar).a_(Long.valueOf(-1));
        drVar.a(cVar);
        drVar.f_();
    }

    void m4874b(List<? extends gd<?>> list) {
        for (gd b : list) {
            b.b(a.c);
        }
    }
}
