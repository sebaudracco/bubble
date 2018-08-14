package com.vungle.publisher;

import com.vungle.publisher.bw.b;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class py extends pi {
    VungleInitListener f3264a;
    @Inject
    bw f3265b;

    @Singleton
    /* compiled from: vungle */
    public static class C1669a {
        @Inject
        Provider<py> f3263a;

        @Inject
        C1669a() {
        }

        public py m4562a(VungleInitListener vungleInitListener) {
            py pyVar = (py) this.f3263a.get();
            pyVar.f3264a = vungleInitListener;
            return pyVar;
        }
    }

    @Inject
    py() {
    }

    public void onEvent(qk event) {
        m4563a(pz.m4566a(this));
    }

    /* synthetic */ void m4564a() {
        if (this.f3264a != null) {
            this.f3264a.onSuccess();
            this.f3264a = null;
            unregister();
        }
    }

    public void onEvent(qj event) {
        m4563a(qa.m4567a(this, event));
    }

    /* synthetic */ void m4565a(qj qjVar) {
        if (this.f3264a != null) {
            this.f3264a.onFailure(qjVar.a);
            this.f3264a = null;
            unregister();
        }
    }

    private void m4563a(Runnable runnable) {
        this.f3265b.m3474a(runnable, b.p);
    }
}
