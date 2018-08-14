package com.elephant.data.p037d;

import android.content.Context;
import android.os.Process;
import android.webkit.WebView;
import java.util.concurrent.BlockingQueue;

final class C1773l implements Runnable {
    private BlockingQueue f3709a;
    private Context f3710b;
    private WebView f3711c;
    private boolean f3712d = true;

    public C1773l(BlockingQueue blockingQueue, Context context) {
        this.f3709a = blockingQueue;
        this.f3710b = context;
    }

    public final void run() {
        Process.setThreadPriority(10);
        while (true) {
            if (!this.f3712d) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
            try {
                C1769g c1769g = (C1769g) this.f3709a.take();
                if (!(c1769g == null || c1769g == null)) {
                    this.f3712d = false;
                    C1768f.f3697c.post(new C1774m(this, c1769g));
                }
            } catch (InterruptedException e2) {
            }
        }
    }
}
