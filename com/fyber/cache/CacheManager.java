package com.fyber.cache;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.fyber.Fyber;
import com.fyber.cache.internal.C2553a;
import com.fyber.cache.internal.C2555c;
import com.fyber.cache.internal.C2557e;
import com.fyber.cache.internal.C2559f;
import com.fyber.mediation.C2573a;
import com.fyber.utils.FyberLogger;

public class CacheManager {
    private static CacheManager f6365a = new CacheManager();
    private C2559f f6366b = C2559f.f6414a;
    private C2553a f6367c = C2553a.f6397a;
    private C2557e f6368d = C2557e.f6411a;
    private boolean f6369e = false;
    private boolean f6370f = false;
    private boolean f6371g = false;

    private CacheManager() {
    }

    public static CacheManager m8105a() {
        return f6365a;
    }

    public final void m8113a(Context context) {
        final Context applicationContext = context.getApplicationContext();
        Fyber.getConfigs().m7600a(new Runnable(this) {
            final /* synthetic */ CacheManager f6364b;

            public final void run() {
                if (this.f6364b.f6366b.equals(C2559f.f6414a)) {
                    this.f6364b.f6366b = new C2559f(applicationContext);
                    this.f6364b.f6368d = new C2557e(applicationContext);
                }
                if (this.f6364b.f6366b.m8180d()) {
                    applicationContext.startService(new Intent(applicationContext, CacheVideoDownloadService.class));
                    return;
                }
                FyberLogger.m8451i("CacheManager", "Cache is not enabled.");
            }
        });
    }

    public final C2553a m8116b() {
        return this.f6367c;
    }

    public final C2559f m8117c() {
        return this.f6366b;
    }

    public final C2557e m8119d() {
        return this.f6368d;
    }

    public final void m8114a(C2553a c2553a) {
        this.f6367c = c2553a;
    }

    public final void m8115a(boolean z) {
        this.f6369e = z;
    }

    public final Uri m8112a(String str, Context context) {
        Uri fromFile;
        FyberLogger.m8451i("CacheManager", "Getting URI for URL - " + str);
        C2555c a = f6365a.f6366b.m8174a(str);
        if (a != null && a.m8156c() == 2 && a.m8152a().exists()) {
            fromFile = Uri.fromFile(a.m8152a());
        } else {
            this.f6371g = true;
            m8110d(context);
            fromFile = Uri.parse(str);
            if (fromFile.isRelative()) {
                fromFile = fromFile.buildUpon().scheme("http").build();
            }
        }
        FyberLogger.m8451i("CacheManager", "URI = " + fromFile);
        return fromFile;
    }

    public static void startPrecaching(Context context) {
        resumeDownloads(context);
        C2573a.f6454a.m8223b();
    }

    public static void resumeDownloads(Context context) {
        f6365a.f6370f = false;
        f6365a.m8111e(context);
    }

    public static void pauseDownloads(Context context) {
        f6365a.f6370f = true;
        f6365a.m8110d(context);
    }

    public static void unregisterOnVideoCachedListener(OnVideoCachedListener onVideoCachedListener, Context context) {
        try {
            context.unregisterReceiver(onVideoCachedListener);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static String m8109b(Context context) {
        return context.getApplicationContext().getPackageName() + ".FyberDownloadsFinished";
    }

    public final boolean m8120e() {
        return this.f6370f || this.f6371g;
    }

    public final void m8118c(Context context) {
        this.f6371g = false;
        m8111e(context);
    }

    private void m8110d(Context context) {
        if (this.f6369e) {
            Context applicationContext = context.getApplicationContext();
            FyberLogger.m8451i("CacheManager", "The download service is running, let's cancel current downloads");
            Intent intent = new Intent(applicationContext, CacheVideoDownloadService.class);
            intent.putExtra("action.to.perform", 10);
            applicationContext.startService(intent);
        }
    }

    private void m8111e(Context context) {
        if (this.f6369e) {
            Context applicationContext = context.getApplicationContext();
            Intent intent = new Intent(applicationContext, CacheVideoDownloadService.class);
            intent.putExtra("action.to.perform", 20);
            applicationContext.startService(intent);
        }
    }

    public static boolean hasCachedVideos() {
        return f6365a.f6366b != C2559f.f6414a && f6365a.f6366b.m8179c() > 0;
    }

    public static void registerOnVideoCachedListener(OnVideoCachedListener onVideoCachedListener, Context context) {
        context.registerReceiver(onVideoCachedListener, new IntentFilter(m8109b(context)));
    }
}
