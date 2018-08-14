package com.vungle.publisher;

import de.greenrobot.event.EventBus;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class qg {
    private final EventBus f3269a = new EventBus();

    @Inject
    qg() {
    }

    public void m4568a(Object obj) {
        this.f3269a.post(obj);
    }

    public void m4569b(Object obj) {
        this.f3269a.register(obj);
    }

    public void m4570c(Object obj) {
        this.f3269a.registerSticky(obj);
    }

    public void m4571d(Object obj) {
        this.f3269a.unregister(obj);
    }
}
