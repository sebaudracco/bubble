package com.moat.analytics.mobile.inm;

import java.util.concurrent.ThreadFactory;

class as implements ThreadFactory {
    final /* synthetic */ ar f8552a;

    as(ar arVar) {
        this.f8552a = arVar;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "MoatStatus");
        thread.setDaemon(true);
        return thread;
    }
}
