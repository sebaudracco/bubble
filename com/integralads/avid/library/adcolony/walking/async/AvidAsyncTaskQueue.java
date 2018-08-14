package com.integralads.avid.library.adcolony.walking.async;

import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.walking.async.AvidAsyncTask.AvidAsyncTaskListener;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AvidAsyncTaskQueue implements AvidAsyncTaskListener {
    private static final int f8414a = 1;
    private final BlockingQueue<Runnable> f8415b = new LinkedBlockingQueue();
    private final ThreadPoolExecutor f8416c = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, this.f8415b);
    private final ArrayDeque<AvidAsyncTask> f8417d = new ArrayDeque();
    private AvidAsyncTask f8418e = null;

    public void submitTask(AvidAsyncTask task) {
        task.setListener(this);
        this.f8417d.add(task);
        if (this.f8418e == null) {
            m11175c();
        }
    }

    private void m11175c() {
        this.f8418e = (AvidAsyncTask) this.f8417d.poll();
        if (this.f8418e != null) {
            this.f8418e.start(this.f8416c);
        }
    }

    public void onTaskCompleted(AvidAsyncTask task) {
        this.f8418e = null;
        m11175c();
    }

    @VisibleForTesting
    AvidAsyncTask m11176a() {
        return this.f8418e;
    }

    @VisibleForTesting
    ArrayDeque<AvidAsyncTask> m11177b() {
        return this.f8417d;
    }
}
