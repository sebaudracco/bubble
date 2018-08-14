package com.integralads.avid.library.adcolony;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.DownloadAvidTask.DownloadAvidTaskListener;
import com.integralads.avid.library.adcolony.utils.NetworkUtils;
import com.stepleaderdigital.reveal.Reveal;

public class AvidLoader implements DownloadAvidTaskListener {
    private static final int f8297a = 2000;
    private static final String f8298b = "https://mobile-static.adsafeprotected.com/avid-v2.js";
    private static AvidLoader f8299c = new AvidLoader();
    private AvidLoaderListener f8300d;
    private DownloadAvidTask f8301e;
    private Context f8302f;
    private TaskExecutor f8303g = new TaskExecutor(this);
    private TaskRepeater f8304h;
    private final Runnable f8305i = new C32811(this);

    class C32811 implements Runnable {
        final /* synthetic */ AvidLoader f8293a;

        C32811(AvidLoader avidLoader) {
            this.f8293a = avidLoader;
        }

        public void run() {
            if (this.f8293a.f8302f == null || !NetworkUtils.isNetworkAvailable(this.f8293a.f8302f)) {
                this.f8293a.m11094d();
            } else {
                this.f8293a.m11092c();
            }
        }
    }

    public interface AvidLoaderListener {
        void onAvidLoaded();
    }

    public class TaskExecutor {
        final /* synthetic */ AvidLoader f8294a;

        public TaskExecutor(AvidLoader this$0) {
            this.f8294a = this$0;
        }

        public void executeTask(DownloadAvidTask task) {
            if (VERSION.SDK_INT >= 11) {
                this.f8294a.f8301e.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{AvidLoader.f8298b});
                return;
            }
            this.f8294a.f8301e.execute(new String[]{AvidLoader.f8298b});
        }
    }

    public class TaskRepeater {
        final /* synthetic */ AvidLoader f8295a;
        private Handler f8296b = new Handler();

        public TaskRepeater(AvidLoader this$0) {
            this.f8295a = this$0;
        }

        public void repeatLoading() {
            this.f8296b.postDelayed(this.f8295a.f8305i, Reveal.CHECK_DELAY);
        }

        public void cleanup() {
            this.f8296b.removeCallbacks(this.f8295a.f8305i);
        }
    }

    public static AvidLoader getInstance() {
        return f8299c;
    }

    public void registerAvidLoader(Context context) {
        this.f8302f = context;
        this.f8304h = new TaskRepeater(this);
        m11092c();
    }

    public void unregisterAvidLoader() {
        if (this.f8304h != null) {
            this.f8304h.cleanup();
            this.f8304h = null;
        }
        this.f8300d = null;
        this.f8302f = null;
    }

    public void setListener(AvidLoaderListener listener) {
        this.f8300d = listener;
    }

    public AvidLoaderListener getListener() {
        return this.f8300d;
    }

    private void m11092c() {
        if (!AvidBridge.isAvidJsReady() && this.f8301e == null) {
            this.f8301e = new DownloadAvidTask();
            this.f8301e.setListener(this);
            this.f8303g.executeTask(this.f8301e);
        }
    }

    private void m11094d() {
        if (this.f8304h != null) {
            this.f8304h.repeatLoading();
        }
    }

    public void onLoadAvid(String avidJs) {
        this.f8301e = null;
        AvidBridge.setAvidJs(avidJs);
        if (this.f8300d != null) {
            this.f8300d.onAvidLoaded();
        }
    }

    public void failedToLoadAvid() {
        this.f8301e = null;
        m11094d();
    }

    @VisibleForTesting
    DownloadAvidTask m11097a() {
        return this.f8301e;
    }

    @VisibleForTesting
    TaskRepeater m11100b() {
        return this.f8304h;
    }

    @VisibleForTesting
    void m11099a(TaskRepeater taskRepeater) {
        this.f8304h = taskRepeater;
    }

    @VisibleForTesting
    void m11098a(TaskExecutor taskExecutor) {
        this.f8303g = taskExecutor;
    }

    @VisibleForTesting
    static void m11089a(AvidLoader avidLoader) {
        f8299c = avidLoader;
    }
}
