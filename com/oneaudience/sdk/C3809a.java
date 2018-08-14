package com.oneaudience.sdk;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.p137b.C3824a;
import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

abstract class C3809a<Params, Progress, Result> {
    public static final Executor f9146a = new ThreadPoolExecutor(f9151f, f9152g, 1, TimeUnit.SECONDS, f9154i, f9153h, new DiscardOldestPolicy());
    public static final Executor f9147b = (C3824a.m12228a() ? new C3781c() : Executors.newSingleThreadExecutor(f9153h));
    public static final Executor f9148c = Executors.newFixedThreadPool(2, f9153h);
    private static final String f9149d = C3809a.class.getSimpleName();
    private static final int f9150e = Runtime.getRuntime().availableProcessors();
    private static final int f9151f = (f9150e + 1);
    private static final int f9152g = ((f9150e * 2) + 1);
    private static final ThreadFactory f9153h = new C37731();
    private static final BlockingQueue<Runnable> f9154i = new LinkedBlockingQueue(10);
    private static final C3779b f9155j = new C3779b();
    private static volatile Executor f9156k = f9147b;
    private final C3774e<Params, Result> f9157l = new C37752(this);
    private final FutureTask<Result> f9158m = new FutureTask<Result>(this, this.f9157l) {
        final /* synthetic */ C3809a f9059a;

        protected void done() {
            try {
                this.f9059a.m12189c(get());
            } catch (Throwable e) {
                C3833d.m12247a(C3809a.f9149d, "", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.f9059a.m12189c(null);
            }
        }
    };
    private volatile C3782d f9159n = C3782d.PENDING;
    private final AtomicBoolean f9160o = new AtomicBoolean();
    private final AtomicBoolean f9161p = new AtomicBoolean();

    static class C37731 implements ThreadFactory {
        private final AtomicInteger f9056a = new AtomicInteger(1);

        C37731() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f9056a.getAndIncrement());
        }
    }

    private static abstract class C3774e<Params, Result> implements Callable<Result> {
        Params[] f9057b;

        private C3774e() {
        }
    }

    class C37752 extends C3774e<Params, Result> {
        final /* synthetic */ C3809a f9058a;

        C37752(C3809a c3809a) {
            this.f9058a = c3809a;
            super();
        }

        public Result call() {
            this.f9058a.f9161p.set(true);
            Process.setThreadPriority(10);
            return this.f9058a.m12190d(this.f9058a.mo6814a(this.b));
        }
    }

    private static class C3778a<Data> {
        final C3809a f9061a;
        final Data[] f9062b;

        C3778a(C3809a c3809a, Data... dataArr) {
            this.f9061a = c3809a;
            this.f9062b = dataArr;
        }
    }

    private static class C3779b extends Handler {
        private C3779b() {
        }

        public void handleMessage(Message message) {
            C3778a c3778a = (C3778a) message.obj;
            switch (message.what) {
                case 1:
                    c3778a.f9061a.m12192e(c3778a.f9062b[0]);
                    return;
                case 2:
                    c3778a.f9061a.m12199b(c3778a.f9062b);
                    return;
                default:
                    return;
            }
        }
    }

    @TargetApi(11)
    private static class C3781c implements Executor {
        final ArrayDeque<Runnable> f9065a;
        Runnable f9066b;

        private C3781c() {
            this.f9065a = new ArrayDeque();
        }

        protected synchronized void m12079a() {
            Runnable runnable = (Runnable) this.f9065a.poll();
            this.f9066b = runnable;
            if (runnable != null) {
                C3809a.f9146a.execute(this.f9066b);
            }
        }

        public synchronized void execute(final Runnable runnable) {
            this.f9065a.offer(new Runnable(this) {
                final /* synthetic */ C3781c f9064b;

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        this.f9064b.m12079a();
                    }
                }
            });
            if (this.f9066b == null) {
                m12079a();
            }
        }
    }

    public enum C3782d {
        PENDING,
        RUNNING,
        FINISHED
    }

    private void m12189c(Result result) {
        if (!this.f9161p.get()) {
            m12190d(result);
        }
    }

    private Result m12190d(Result result) {
        f9155j.obtainMessage(1, new C3778a(this, result)).sendToTarget();
        return result;
    }

    private void m12192e(Result result) {
        if (m12200c()) {
            mo6818b((Object) result);
        } else {
            mo6816a((Object) result);
        }
        this.f9159n = C3782d.FINISHED;
    }

    public final C3809a<Params, Progress, Result> m12193a(Executor executor, Params... paramsArr) {
        if (this.f9159n != C3782d.PENDING) {
            switch (this.f9159n) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f9159n = C3782d.RUNNING;
        mo6815a();
        this.f9157l.f9057b = paramsArr;
        executor.execute(this.f9158m);
        return this;
    }

    protected abstract Result mo6814a(Params... paramsArr);

    protected void mo6815a() {
    }

    protected void mo6816a(Result result) {
    }

    protected void mo6817b() {
    }

    protected void mo6818b(Result result) {
        mo6817b();
    }

    protected void m12199b(Progress... progressArr) {
    }

    public final boolean m12200c() {
        return this.f9160o.get();
    }
}
