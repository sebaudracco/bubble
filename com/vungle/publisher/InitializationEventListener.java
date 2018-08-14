package com.vungle.publisher;

import com.stepleaderdigital.reveal.Reveal;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.log.C1654g;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class InitializationEventListener extends pi {
    @Inject
    bw f2685a;
    @Inject
    vc f2686b;
    @Inject
    C1586a f2687c;
    @Inject
    C1614r f2688d;
    private final zs f2689e = new zs();

    @Singleton
    /* compiled from: vungle */
    static class C1586a extends pi {
        @Inject
        C1591c f2679a;
        @Inject
        yk f2680b;
        @Inject
        C1614r f2681c;
        @Inject
        C1654g f2682d;
        @Inject
        vc f2683e;
        @Inject
        bw f2684f;

        @Inject
        C1586a() {
        }

        public void onEvent(td event) {
            this.f2681c.f2919b.set(false);
            if (!event.a()) {
                this.eventBus.m4568a(new qj(new Throwable(event.b())));
            } else if (this.f2681c.f2918a.compareAndSet(false, true)) {
                unregister();
                this.eventBus.m4568a(new qk());
                this.f2680b.m4910a();
                this.f2679a.m3488a();
                this.f2681c.m3952d();
                this.f2684f.m3478b(C1588b.m3466a(this), 600000);
            }
        }

        /* synthetic */ void m3458a() {
            this.f2682d.m4349a(this.f2683e);
        }
    }

    @Inject
    InitializationEventListener() {
    }

    public void onEvent(pf event) {
        Logger.d(Logger.DEVICE_TAG, "device ID available");
        m3459a(1);
    }

    public void onEvent(cm event) {
        Logger.d(Logger.DATABASE_TAG, "on database ready");
        m3459a(0);
    }

    public void onEvent(pg event) {
        Logger.d(Logger.DEVICE_TAG, "webview user agent updated");
        m3459a(2);
    }

    private void m3459a(int i) {
        if (this.f2689e.a(i, 1) != 7) {
            return;
        }
        if (this.f2688d.f2919b.compareAndSet(false, true)) {
            unregister();
            this.f2685a.m3473a(C1587a.m3465a(this), (long) Reveal.CHECK_DELAY);
            return;
        }
        Logger.d(Logger.EVENT_TAG, "Already processing initialize, going to drop this request");
    }

    /* synthetic */ void m3460a() {
        this.f2687c.register();
        this.f2688d.m3957g();
        qw.m4586a();
        this.f2686b.m4725a();
    }
}
