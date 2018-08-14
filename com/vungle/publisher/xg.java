package com.vungle.publisher;

import com.vungle.publisher.ct.C1594a;
import com.vungle.publisher.env.C1612k;
import com.vungle.publisher.env.C1613o;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.log.C1654g;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Scheduler;
import rx.exceptions.Exceptions;
import rx.schedulers.Schedulers;

/* compiled from: vungle */
public class xg {
    Scheduler f3431a = Schedulers.computation();
    @Inject
    uz f3432b;
    @Inject
    xq f3433c;
    @Inject
    uw f3434d;
    @Inject
    yg$a f3435e;
    @Inject
    xd f3436f;
    @Inject
    C1614r f3437g;
    @Inject
    yd f3438h;
    @Inject
    C1612k f3439i;
    @Inject
    qg f3440j;
    @Inject
    C1613o f3441k;
    @Inject
    bw f3442l;
    @Inject
    C1594a f3443m;
    @Inject
    wd$a f3444n;
    @Inject
    C1654g f3445o;

    public Observable<dr<?>> m4850a(String str) {
        return qw.m4583a(str).delay(this.f3437g.m3950c(str), TimeUnit.MILLISECONDS, this.f3431a).subscribeOn(Schedulers.io()).flatMap(xh.m4856a(this)).doOnNext(xi.m4857a(this, str)).flatMap(this.f3435e).filter(xj.m4858a()).flatMap(xk.m4859a(this)).flatMap(xl.m4860a(this, str)).flatMap(this.f3433c).doOnError(xm.m4861a(this)).retryWhen(this.f3438h.m4898a("ad prep chain failure"));
    }

    /* synthetic */ Observable m4855b(String str) {
        try {
            return Observable.just(this.f3444n.a(str));
        } catch (Throwable e) {
            throw Exceptions.propagate(e);
        }
    }

    /* synthetic */ void m4853a(String str, wd wdVar) {
        if (wdVar.n()) {
            this.f3439i.m3906a(wdVar.i());
            return;
        }
        this.f3440j.m4568a(new bl(str));
        this.f3437g.m3943a(str, wdVar.j().longValue());
        this.f3437g.m3947b(null);
    }

    static /* synthetic */ Boolean m4847b(wc wcVar) {
        return Boolean.valueOf(wcVar != null);
    }

    /* synthetic */ Observable m4849a(wc wcVar) {
        return Observable.zip(this.f3436f.m4846a(wcVar), Observable.just(wcVar.l()), xn.m4862a());
    }

    /* synthetic */ Observable m4851a(String str, SimpleEntry simpleEntry) {
        dr drVar = (dr) simpleEntry.getKey();
        ct a = this.f3443m.m3591a(str, drVar.d());
        this.f3443m.m3595a(a);
        return Observable.just(drVar);
    }

    /* synthetic */ void m4854a(Throwable th) {
        this.f3445o.f3084b.severe("Prepare ERR: " + th.getMessage());
    }

    Observable<dr<?>> m4852a(Observable<? extends dr<?>> observable) {
        return observable.flatMap(this.f3433c);
    }

    public Observable<dr<?>> m4848a(dr<?> drVar) {
        return m4852a(Observable.just(drVar));
    }
}
