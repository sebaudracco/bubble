package com.vungle.publisher;

import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public class um implements Runnable {
    private final ub f11145a;

    public um(ub ubVar) {
        this.f11145a = ubVar;
    }

    public void run() {
        try {
            Logger.m13635d(Logger.NETWORK_TAG, "executing " + this.f11145a);
            this.f11145a.d();
        } catch (Throwable e) {
            Logger.m13639e(Logger.NETWORK_TAG, "error processing transaction: " + this.f11145a, e);
        }
    }
}
