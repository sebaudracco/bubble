package com.fyber.cache;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.fyber.Fyber;
import com.fyber.cache.internal.C2553a;
import com.fyber.cache.internal.C2553a.C2552a;
import com.fyber.cache.internal.C2554b;
import com.fyber.cache.internal.C2555c;
import com.fyber.cache.internal.C2559f;
import com.fyber.cache.internal.C2560g;
import com.fyber.cache.internal.C2561h;
import com.fyber.cache.internal.ConfigurationBroadcastReceiver;
import com.fyber.utils.C2642b;
import com.fyber.utils.C2646d;
import com.fyber.utils.C2657h;
import com.fyber.utils.C2661i;
import com.fyber.utils.C2661i.C2660a;
import com.fyber.utils.C2661i.C2660a.C2658a;
import com.fyber.utils.C2661i.C2660a.C2659b;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CacheVideoDownloadService extends Service {
    private static ConfigurationBroadcastReceiver f6385a;
    private C2549b f6386b;
    private C2550c f6387c;
    private C2548a f6388d;
    private boolean f6389e = false;
    private volatile int f6390f = C2551d.f6381a;
    private volatile boolean f6391g = false;
    private BroadcastReceiver f6392h = new C25471(this);

    class C25471 extends BroadcastReceiver {
        final /* synthetic */ CacheVideoDownloadService f6372a;
        private boolean f6373b = true;

        C25471(CacheVideoDownloadService cacheVideoDownloadService) {
            this.f6372a = cacheVideoDownloadService;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getExtras().get("networkInfo");
                if (networkInfo == null) {
                    return;
                }
                if (this.f6373b) {
                    this.f6373b = false;
                } else if (networkInfo.getState() == State.CONNECTED) {
                    C2552a e = CacheVideoDownloadService.m8138e(this.f6372a);
                    if (e != null) {
                        this.f6372a.f6388d.removeMessages(1310);
                        FyberLogger.m8451i("CacheVideoDownloadService", "Network connection changed to " + e.name());
                        this.f6372a.f6388d.sendMessageDelayed(this.f6372a.f6388d.obtainMessage(1310, e), 1000);
                    }
                } else {
                    FyberLogger.m8451i("CacheVideoDownloadService", "Connectivity lost");
                    if (!this.f6372a.f6388d.hasMessages(1300)) {
                        this.f6372a.f6388d.sendMessageAtFrontOfQueue(this.f6372a.f6388d.obtainMessage(1300));
                    }
                }
            }
        }
    }

    private final class C2548a extends Handler {
        final /* synthetic */ CacheVideoDownloadService f6374a;
        private SparseArray<C2561h> f6375b = new SparseArray();
        private int f6376c = 0;

        public C2548a(CacheVideoDownloadService cacheVideoDownloadService, Looper looper) {
            this.f6374a = cacheVideoDownloadService;
            super(looper);
        }

        public final void handleMessage(Message message) {
            boolean z = false;
            switch (message.what) {
                case 200:
                    if (this.f6374a.f6390f != C2551d.f6381a) {
                        return;
                    }
                    if (m8124b()) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "No more videos to download \\m/");
                        this.f6374a.f6386b.sendEmptyMessage(100);
                        return;
                    } else if (!this.f6374a.f6391g) {
                        m8123a();
                        return;
                    } else {
                        return;
                    }
                case 1100:
                    C2553a b;
                    int b2;
                    Object obj = message.obj;
                    this.f6374a.f6390f = C2551d.f6381a;
                    this.f6376c = 0;
                    this.f6375b.clear();
                    if (obj != null) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "Cache config received, parsing...");
                        C2560g a = C2560g.m8182a(obj.toString());
                        C2554b a2 = a.m8183a();
                        if (a2 != null) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "Creating cache configuration object");
                            C2553a a3 = a2.m8147a();
                            CacheManager.m8105a().m8114a(a3);
                            List b3 = a.m8184b();
                            C2552a e = CacheVideoDownloadService.m8138e(this.f6374a);
                            if (e != null) {
                                FyberLogger.m8451i("CacheVideoDownloadService", "Max downloading this many videos on this network type: " + a3.m8142a(e).m8161a());
                                int c = a3.m8146c();
                                if (b3.isEmpty()) {
                                    z = true;
                                } else {
                                    m8122a(b3);
                                    m8125c();
                                    CacheManager.m8105a().m8117c().m8176a(c);
                                    sendEmptyMessage(200);
                                }
                            } else {
                                FyberLogger.m8451i("CacheVideoDownloadService", "Network connectivity was lost or this device has issues accessing network data info.");
                                FyberLogger.m8451i("CacheVideoDownloadService", "Videos will not be queued for download.");
                            }
                        } else {
                            z = true;
                        }
                        b = CacheManager.m8105a().m8116b();
                        if (b == C2553a.f6397a) {
                            b2 = b.m8145b();
                            FyberLogger.m8451i("CacheVideoDownloadService", "Cache configuration exists, refresh interval is " + b2);
                        } else {
                            b2 = 3600;
                        }
                        CacheVideoDownloadService.m8134b(this.f6374a, b2);
                        if (z) {
                            this.f6374a.f6386b.sendEmptyMessage(100);
                            return;
                        }
                        return;
                    }
                    z = true;
                    b = CacheManager.m8105a().m8116b();
                    if (b == C2553a.f6397a) {
                        b2 = 3600;
                    } else {
                        b2 = b.m8145b();
                        FyberLogger.m8451i("CacheVideoDownloadService", "Cache configuration exists, refresh interval is " + b2);
                    }
                    CacheVideoDownloadService.m8134b(this.f6374a, b2);
                    if (z) {
                        this.f6374a.f6386b.sendEmptyMessage(100);
                        return;
                    }
                    return;
                case 1160:
                    String obj2 = message.obj.toString();
                    int i = message.arg1;
                    int i2 = message.arg2;
                    C2559f c2 = CacheManager.m8105a().m8117c();
                    C2555c a4 = c2.m8174a(obj2);
                    if (a4 != null) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "Updating entry " + a4.m8155b() + " with state " + i2);
                        a4.m8153a(i2);
                        c2.m8177b();
                        if (i2 == 2) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "Reason - File fully downloaded");
                            m8121a(i);
                        } else if (i2 == 4) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "Reason - File not downloadable (file not found or no space left)");
                            m8121a(i);
                        } else if (a4.m8157d() > 10) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "Reason - Maximum retry count reached");
                            m8121a(i);
                        }
                    } else {
                        FyberLogger.m8451i("CacheVideoDownloadService", "There was no cache entry for the url: " + obj2);
                        m8121a(i);
                    }
                    if (!message.getData().getBoolean("canceled", false)) {
                        sendEmptyMessage(200);
                        return;
                    }
                    return;
                case 1300:
                    this.f6374a.f6386b.sendEmptyMessage(HttpStatus.SC_MULTIPLE_CHOICES);
                    return;
                case 1310:
                    sendEmptyMessage(200);
                    return;
                default:
                    return;
            }
        }

        private void m8121a(int i) {
            FyberLogger.m8451i("CacheVideoDownloadService", "Removing videos to cache, entry num " + i);
            this.f6375b.put(i, null);
            this.f6376c++;
        }

        private void m8122a(List<C2561h> list) {
            int c = CacheManager.m8105a().m8116b().m8146c();
            int i = 0;
            for (C2561h c2561h : list) {
                if (i == c) {
                    FyberLogger.m8451i("CacheVideoDownloadService", String.format(Locale.ENGLISH, "There are %d videos to download and the maximum cache slots size is %d", new Object[]{Integer.valueOf(list.size()), Integer.valueOf(c)}));
                    FyberLogger.m8451i("CacheVideoDownloadService", "Trimming the list of new videos to download to " + c);
                    return;
                }
                this.f6375b.put(i, c2561h);
                i++;
            }
        }

        private boolean m8123a() {
            C2552a e = CacheVideoDownloadService.m8138e(this.f6374a);
            if (e != null) {
                FyberLogger.m8451i("CacheVideoDownloadService", "Queuing video for network " + e.name());
                CacheManager a = CacheManager.m8105a();
                C2553a b = a.m8116b();
                int c = b.m8146c();
                C2559f c2 = a.m8117c();
                int a2 = b.m8142a(e).m8161a();
                int i = 0;
                while (i < this.f6375b.size() && i < a2) {
                    C2561h c2561h = (C2561h) this.f6375b.get(i);
                    if (c2561h != null) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "Queuing video entry for ad_id " + c2561h.m8185a() + " and URL " + c2561h.m8186b());
                        boolean z = c2.m8174a(c2561h.m8186b()) == null;
                        C2555c a3 = c2.m8173a(c2561h);
                        int c3 = a3.m8156c();
                        if (c3 == 0 || c3 == 1) {
                            if (z) {
                                c2.m8178b(c2.m8175a().size() - c);
                            }
                            a3.m8153a(3);
                            Message obtainMessage = this.f6374a.f6387c.obtainMessage(200, a3);
                            obtainMessage.arg1 = i;
                            obtainMessage.sendToTarget();
                            return true;
                        } else if (c3 == 4) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "The file is marked as NOT_DOWNLOADABLE. Removing it from the current download list.");
                            m8121a(i);
                        } else if (c3 == 2) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "The file is marked as DOWNLOAD_COMPLETED. Removing it from the current download list.");
                            m8121a(i);
                        } else {
                            FyberLogger.m8451i("CacheVideoDownloadService", "This cache entry will not be queued for download. Current state: " + c3);
                        }
                    }
                    i++;
                }
            }
            FyberLogger.m8451i("CacheVideoDownloadService", "No videos to be queued for download at the moment");
            if (m8124b()) {
                this.f6374a.f6386b.sendEmptyMessage(100);
            }
            return false;
        }

        private boolean m8124b() {
            return this.f6376c == this.f6375b.size();
        }

        private void m8125c() {
            FyberLogger.m8451i("CacheVideoDownloadService", "Checking videos already available locally...");
            C2559f c = CacheManager.m8105a().m8117c();
            for (int i = 0; i < this.f6375b.size(); i++) {
                C2561h c2561h = (C2561h) this.f6375b.get(i);
                if (c2561h != null) {
                    FyberLogger.m8451i("CacheVideoDownloadService", "Video entry for ad_id " + c2561h.m8185a() + " and url " + c2561h.m8186b());
                    C2555c a = c.m8174a(c2561h.m8186b());
                    if (a != null) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "A cache entry already exists for url - " + a.m8155b());
                        if (a.m8154a(c2561h)) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "Video entry successfully added to cache entry");
                        } else {
                            FyberLogger.m8451i("CacheVideoDownloadService", "The video entry was already part of this cache entry");
                        }
                        a.m8158e();
                        c.m8177b();
                        if (a.m8156c() == 2) {
                            FyberLogger.m8451i("CacheVideoDownloadService", "Cache entry is already fully downloaded");
                            FyberLogger.m8451i("CacheVideoDownloadService", "Removing URL " + a.m8155b() + " from the new downloads list");
                            this.f6375b.put(i, null);
                            this.f6376c++;
                        }
                    }
                }
            }
        }
    }

    private final class C2549b extends Handler {
        final /* synthetic */ CacheVideoDownloadService f6377a;

        public C2549b(CacheVideoDownloadService cacheVideoDownloadService, Looper looper) {
            this.f6377a = cacheVideoDownloadService;
            super(looper);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 10:
                    this.f6377a.f6390f = C2551d.f6383c;
                    m8126a();
                    if (!this.f6377a.f6389e) {
                        CacheVideoDownloadService.m8136c(this.f6377a);
                    }
                    this.f6377a.f6387c.sendEmptyMessage(10);
                    return;
                case 100:
                    m8126a();
                    Intent intent = new Intent(CacheManager.m8109b(this.f6377a.getApplicationContext()));
                    intent.putExtra(OnVideoCachedListener.EXTRA_VIDEOS_AVAILABLE, CacheManager.hasCachedVideos());
                    this.f6377a.sendBroadcast(intent);
                    this.f6377a.stopSelf();
                    return;
                case HttpStatus.SC_MULTIPLE_CHOICES /*300*/:
                    m8126a();
                    return;
                default:
                    return;
            }
        }

        private void m8126a() {
            if (this.f6377a.f6387c != null) {
                this.f6377a.f6387c.m8129a();
            }
        }
    }

    private final class C2550c extends Handler {
        final /* synthetic */ CacheVideoDownloadService f6378a;
        private C2660a f6379b;
        private boolean f6380c = false;

        public C2550c(CacheVideoDownloadService cacheVideoDownloadService, Looper looper) {
            this.f6378a = cacheVideoDownloadService;
            super(looper);
        }

        public final void handleMessage(Message message) {
            Exception e;
            switch (message.what) {
                case 10:
                    Object obj;
                    FyberLogger.m8451i("CacheVideoDownloadService", "Download handler - Downloading config...");
                    try {
                        String e2 = C2672t.m8534a(C2646d.m8469a("precaching"), Fyber.getConfigs().m7608i()).m8547e();
                        FyberLogger.m8451i("CacheVideoDownloadService", "Download handler - Config will be fetched from - " + e2);
                        C2657h c2657h = (C2657h) ((C2657h) C2657h.m8506b(e2).m8461a(C2642b.m8457d())).mo4005a();
                        int b = c2657h.m8464b();
                        if (b >= 200 && b < HttpStatus.SC_MULTIPLE_CHOICES) {
                            obj = (String) c2657h.m8466c();
                            this.f6378a.f6388d.obtainMessage(1100, obj).sendToTarget();
                            return;
                        }
                    } catch (NullPointerException e3) {
                        e = e3;
                        FyberLogger.m8450e("CacheVideoDownloadService", "An error occurred", e);
                        obj = null;
                        this.f6378a.f6388d.obtainMessage(1100, obj).sendToTarget();
                        return;
                    } catch (IOException e4) {
                        e = e4;
                        FyberLogger.m8450e("CacheVideoDownloadService", "An error occurred", e);
                        obj = null;
                        this.f6378a.f6388d.obtainMessage(1100, obj).sendToTarget();
                        return;
                    }
                    obj = null;
                    this.f6378a.f6388d.obtainMessage(1100, obj).sendToTarget();
                    return;
                case 200:
                    this.f6378a.f6391g = true;
                    FyberLogger.m8451i("CacheVideoDownloadService", "Download handler - Downloading video...");
                    C2555c c2555c = (C2555c) message.obj;
                    int a = m8127a(c2555c);
                    FyberLogger.m8451i("CacheVideoDownloadService", "Download handler - Video state = " + a);
                    this.f6378a.f6391g = false;
                    Message obtainMessage = this.f6378a.f6388d.obtainMessage(1160, message.arg1, a, c2555c.m8155b());
                    if (this.f6380c) {
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("canceled", true);
                        obtainMessage.setData(bundle);
                        this.f6380c = false;
                    }
                    obtainMessage.sendToTarget();
                    return;
                default:
                    return;
            }
        }

        private synchronized int m8127a(C2555c c2555c) {
            int i;
            FyberLogger.m8451i("CacheVideoDownloadService", "Downloading video from URL: " + c2555c.m8155b());
            File a = c2555c.m8152a();
            if (C2550c.m8128a(a)) {
                if (a.canWrite()) {
                    try {
                        boolean z = c2555c.m8156c() == 1 || a.length() > 0;
                        this.f6379b = (C2660a) C2661i.m8510a(c2555c.m8155b(), a).m8514a(z).mo4007e().m8466c();
                        try {
                            if (this.f6379b.m8508a()) {
                                this.f6379b = null;
                                CacheManager.m8105a().m8119d().m8165b();
                                i = 2;
                            }
                        } catch (C2658a e) {
                            this.f6379b = null;
                            i = 4;
                            return i;
                        } catch (C2659b e2) {
                            this.f6379b = null;
                            i = 4;
                            return i;
                        }
                    } catch (IOException e3) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "Video downloading from URL: " + c2555c.m8155b() + " has been interrupted.");
                        FyberLogger.m8449e("CacheVideoDownloadService", "An error occurred while downloading the videos: " + e3.getMessage());
                    }
                } else {
                    FyberLogger.m8451i("CacheVideoDownloadService", "No permission granted to write to: " + a.getAbsolutePath());
                }
                this.f6379b = null;
                i = 1;
            } else {
                i = 4;
            }
            return i;
        }

        private static boolean m8128a(@NonNull File file) {
            if (!file.exists()) {
                try {
                    if (!file.createNewFile()) {
                        FyberLogger.m8451i("CacheVideoDownloadService", "Cache File with path: " + file.getAbsolutePath() + " has not been created");
                        return false;
                    }
                } catch (IOException e) {
                    FyberLogger.m8451i("CacheVideoDownloadService", "impossible to create cache File with path: " + file.getAbsolutePath());
                    FyberLogger.m8451i("CacheVideoDownloadService", "error creating cache File: " + e.getMessage());
                    return false;
                }
            }
            return true;
        }

        public final void m8129a() {
            removeMessages(200);
            removeMessages(10);
            if (this.f6379b != null) {
                this.f6380c = true;
                FyberLogger.m8451i("CacheVideoDownloadService", "Download handler - canceling downloads");
                this.f6379b.m8509b();
                this.f6379b = null;
            }
        }
    }

    private enum C2551d {
        ;
        
        public static final int f6381a = 0;
        public static final int f6382b = 0;
        public static final int f6383c = 0;

        static {
            f6381a = 1;
            f6382b = 2;
            f6383c = 3;
            f6384d = new int[]{f6381a, f6382b, f6383c};
        }
    }

    static /* synthetic */ void m8136c(CacheVideoDownloadService cacheVideoDownloadService) {
        if (!cacheVideoDownloadService.f6389e) {
            HandlerThread handlerThread = new HandlerThread("ServiceDownloadThread", 1);
            handlerThread.start();
            cacheVideoDownloadService.f6387c = new C2550c(cacheVideoDownloadService, handlerThread.getLooper());
            handlerThread = new HandlerThread("ServiceDecisionThread", 1);
            handlerThread.start();
            cacheVideoDownloadService.f6388d = new C2548a(cacheVideoDownloadService, handlerThread.getLooper());
            cacheVideoDownloadService.getApplicationContext().registerReceiver(cacheVideoDownloadService.f6392h, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            cacheVideoDownloadService.f6389e = true;
        }
    }

    public void onCreate() {
        super.onCreate();
        if (f6385a == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(m8132a());
            f6385a = new ConfigurationBroadcastReceiver();
            getApplicationContext().registerReceiver(f6385a, intentFilter);
        }
        HandlerThread handlerThread = new HandlerThread("ServiceDispatcherThread", 1);
        handlerThread.start();
        this.f6386b = new C2549b(this, handlerThread.getLooper());
        if (CacheManager.m8105a().m8117c().m8180d()) {
            CacheManager.m8105a().m8115a(true);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        FyberLogger.m8451i("CacheVideoDownloadService", "onStartCommand called on the service");
        if (intent != null) {
            int intExtra = intent.getIntExtra("action.to.perform", 0);
            if (CacheManager.m8105a().m8117c().m8180d() && (this.f6389e || intExtra == 0 || this.f6390f != C2551d.f6381a)) {
                switch (intExtra) {
                    case 10:
                        this.f6390f = C2551d.f6382b;
                        this.f6386b.sendEmptyMessage(HttpStatus.SC_MULTIPLE_CHOICES);
                        break;
                    case 20:
                        intExtra = this.f6390f;
                        this.f6390f = C2551d.f6381a;
                        if (!CacheManager.m8105a().m8120e()) {
                            if (intExtra != C2551d.f6383c && this.f6389e) {
                                this.f6388d.sendEmptyMessage(200);
                                break;
                            }
                            this.f6386b.sendEmptyMessage(10);
                            break;
                        }
                        this.f6390f = C2551d.f6383c;
                        break;
                        break;
                    default:
                        if (Fyber.getConfigs().m7607h()) {
                            if (!CacheManager.m8105a().m8120e()) {
                                this.f6386b.sendEmptyMessage(10);
                                break;
                            }
                            this.f6390f = C2551d.f6383c;
                            break;
                        }
                        FyberLogger.m8451i("CacheVideoDownloadService", "The SDK appears to not have been started yet...");
                        this.f6386b.sendEmptyMessage(100);
                        break;
                }
            }
            this.f6386b.sendEmptyMessage(100);
        } else {
            FyberLogger.m8449e("CacheVideoDownloadService", "Intent is null. Service is unable to start.");
            if (this.f6386b != null) {
                this.f6386b.sendEmptyMessage(100);
            } else {
                stopSelf();
            }
        }
        return 2;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        FyberLogger.m8451i("CacheVideoDownloadService", "The service will shutdown");
        CacheManager.m8105a().m8115a(false);
        if (this.f6386b != null) {
            this.f6386b.getLooper().quit();
        }
        if (this.f6389e) {
            this.f6388d.getLooper().quit();
            this.f6387c.getLooper().quit();
            getApplicationContext().unregisterReceiver(this.f6392h);
        }
    }

    private String m8132a() {
        return getPackageName() + ".cache.DONE_PRECACHING";
    }

    static /* synthetic */ C2552a m8138e(CacheVideoDownloadService cacheVideoDownloadService) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) cacheVideoDownloadService.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            switch (activeNetworkInfo.getType()) {
                case 0:
                    return C2552a.CELLULAR;
                case 1:
                    return C2552a.f6394a;
            }
        }
        return null;
    }

    static /* synthetic */ void m8134b(CacheVideoDownloadService cacheVideoDownloadService, int i) {
        Intent intent = new Intent(cacheVideoDownloadService.m8132a());
        if (i < HttpStatus.SC_MULTIPLE_CHOICES) {
            i = HttpStatus.SC_MULTIPLE_CHOICES;
        }
        intent.putExtra("refresh.interval", i);
        FyberLogger.m8451i("CacheVideoDownloadService", "Creating broadcast receiver with refresh interval = " + i);
        cacheVideoDownloadService.sendBroadcast(intent);
    }
}
