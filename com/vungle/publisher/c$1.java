package com.vungle.publisher;

import rx.Observer;

/* compiled from: vungle */
class c$1 implements Observer<dr<?>> {
    final /* synthetic */ String f9826a;
    final /* synthetic */ c f9827b;

    c$1(c cVar, String str) {
        this.f9827b = cVar;
        this.f9826a = str;
    }

    public /* synthetic */ void onNext(Object obj) {
        m12905a((dr) obj);
    }

    public void onCompleted() {
        this.f9827b.r.b.info("ad observable onComplete");
        this.f9827b.a.a(false);
    }

    public void onError(Throwable exception) {
        this.f9827b.r.b.info("ad observable onError");
        this.f9827b.a.a(false);
        this.f9827b.a.b(null);
        if (this.f9826a.contentEquals(this.f9827b.m.e())) {
            this.f9827b.r.b.info("ad observable onError , queuing it for retry at end of the queue !");
            this.f9827b.a.a(this.f9826a);
        } else {
            this.f9827b.r.b.info("ad observable onError , ignoring this No retries !");
        }
        this.f9827b.d.a(new al());
    }

    public void m12905a(dr<?> drVar) {
        this.f9827b.r.b.info("ad observable onNext");
        this.f9827b.a.a(false);
        this.f9827b.a.b(null);
        this.f9827b.d.a(new al());
        this.f9827b.d.a(new am(this.f9826a));
    }
}
