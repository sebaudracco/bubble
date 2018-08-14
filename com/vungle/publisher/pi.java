package com.vungle.publisher;

import com.vungle.publisher.log.Logger;
import javax.inject.Inject;

/* compiled from: vungle */
public class pi implements qi {
    private boolean f2678a;
    @Inject
    public qg eventBus;

    @Inject
    protected pi() {
    }

    public void register() {
        if (this.f2678a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening");
        this.eventBus.m4569b(this);
        this.f2678a = true;
    }

    public void registerSticky() {
        if (this.f2678a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening sticky");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening sticky");
        this.eventBus.m4570c(this);
        this.f2678a = true;
    }

    public void unregister() {
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " unregistered");
        this.eventBus.m4571d(this);
        this.f2678a = false;
    }
}
