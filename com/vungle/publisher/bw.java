package com.vungle.publisher;

import android.os.HandlerThread;
import android.os.Message;
import com.vungle.publisher.log.Logger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class bw {
    @Inject
    zl f2717a;
    private final a f2718b;
    private final c f2719c;
    private final c f2720d;
    private final c f2721e;
    private final BlockingQueue<Runnable> f2722f = new PriorityBlockingQueue();

    @Inject
    bw() {
        HandlerThread handlerThread = new HandlerThread("VungleAsyncMasterThread");
        handlerThread.start();
        this.f2719c = new c(this, 2, 2, 30, new LinkedBlockingQueue(), "VungleAsyncClientEventThread-");
        this.f2719c.allowCoreThreadTimeOut(true);
        this.f2720d = new c(this, 2, 2, 30, new LinkedBlockingQueue(), "VungleAsyncExternalNetworkRequestThread-");
        this.f2720d.allowCoreThreadTimeOut(true);
        this.f2718b = new a(this, handlerThread.getLooper());
        this.f2721e = new c(this, 2, 2, 30, this.f2722f, "VungleAsyncMainThread-");
        this.f2721e.allowCoreThreadTimeOut(true);
    }

    public void m3472a(Runnable runnable) {
        m3474a(runnable, b.n);
    }

    public void m3474a(Runnable runnable, b bVar) {
        this.f2718b.sendMessage(m3477b(runnable, bVar));
    }

    public void m3475a(Runnable runnable, b bVar, long j) {
        m3479b(runnable, bVar, j - this.f2717a.m4931a());
    }

    public void m3473a(Runnable runnable, long j) {
        m3479b(runnable, b.n, j);
    }

    public void m3479b(Runnable runnable, b bVar, long j) {
        Logger.d(Logger.ASYNC_TAG, "scheduling " + bVar + " delayed " + j + " ms");
        this.f2718b.sendMessageDelayed(m3477b(runnable, bVar), j);
    }

    public void m3478b(Runnable runnable, long j) {
        m3476a(runnable, b.n, 0, j);
    }

    public void m3476a(Runnable runnable, b bVar, long j, long j2) {
        this.f2718b.sendMessageDelayed(m3480c(runnable, bVar, j2), j);
    }

    Message m3477b(Runnable runnable, b bVar) {
        a aVar = this.f2718b;
        int ordinal = bVar.ordinal();
        aVar.getClass();
        return aVar.obtainMessage(ordinal, new a(aVar, runnable, bVar));
    }

    Message m3480c(Runnable runnable, b bVar, long j) {
        a aVar = this.f2718b;
        int ordinal = bVar.ordinal();
        aVar.getClass();
        return aVar.obtainMessage(ordinal, new a(aVar, runnable, bVar, j));
    }

    public void m3471a(b bVar) {
        this.f2718b.removeMessages(bVar.ordinal());
    }
}
