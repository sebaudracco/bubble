package com.vungle.publisher.env;

import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.view.WindowManager;
import android.webkit.WebView;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Tasks;
import com.google.gson.JsonObject;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.lm;
import com.vungle.publisher.log.Logger;
import com.vungle.publisher.zb;
import com.vungle.publisher.zj;
import com.vungle.publisher.zo;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AndroidDevice implements i {
    static int f2883a = 5000;
    final AtomicBoolean f2884b = new AtomicBoolean();
    String f2885c;
    String f2886d;
    @Inject
    lm f2887e;
    @Inject
    WindowManager f2888f;
    @Inject
    Context f2889g;
    @Inject
    SharedPreferences f2890h;
    @Inject
    DeviceIdStrategy f2891i;
    @Inject
    String f2892j;
    private final String f2893k = VERSION.RELEASE;
    private boolean f2894l;

    void m3881h() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0075 in list [B:35:0x006c]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r6 = this;
        r0 = r6.f2884b;	 Catch:{ all -> 0x0043 }
        r0 = r0.get();	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x0066;	 Catch:{ all -> 0x0043 }
    L_0x0008:
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0043 }
        r2 = f2883a;	 Catch:{ all -> 0x0043 }
        r2 = (long) r2;	 Catch:{ all -> 0x0043 }
        r0 = r0 + r2;	 Catch:{ all -> 0x0043 }
        r2 = r6.f2884b;	 Catch:{ all -> 0x0043 }
        monitor-enter(r2);	 Catch:{ all -> 0x0043 }
        r3 = "VungleDevice";	 Catch:{ InterruptedException -> 0x0035 }
        r4 = "waiting for device ID";	 Catch:{ InterruptedException -> 0x0035 }
        com.vungle.publisher.log.Logger.d(r3, r4);	 Catch:{ InterruptedException -> 0x0035 }
    L_0x001c:
        r3 = r6.f2884b;	 Catch:{ InterruptedException -> 0x0035 }
        r3 = r3.get();	 Catch:{ InterruptedException -> 0x0035 }
        if (r3 != 0) goto L_0x0054;	 Catch:{ InterruptedException -> 0x0035 }
    L_0x0024:
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ InterruptedException -> 0x0035 }
        r3 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r3 >= 0) goto L_0x0054;
    L_0x002c:
        r3 = r6.f2884b;	 Catch:{ InterruptedException -> 0x0035 }
        r4 = f2883a;	 Catch:{ InterruptedException -> 0x0035 }
        r4 = (long) r4;	 Catch:{ InterruptedException -> 0x0035 }
        r3.wait(r4);	 Catch:{ InterruptedException -> 0x0035 }
        goto L_0x001c;
    L_0x0035:
        r3 = move-exception;
        r3 = "VungleDevice";	 Catch:{ InterruptedException -> 0x0035 }
        r4 = "interrupted while awaiting device ID";	 Catch:{ InterruptedException -> 0x0035 }
        com.vungle.publisher.log.Logger.v(r3, r4);	 Catch:{ InterruptedException -> 0x0035 }
        goto L_0x001c;	 Catch:{ InterruptedException -> 0x0035 }
    L_0x0040:
        r0 = move-exception;	 Catch:{ InterruptedException -> 0x0035 }
        monitor-exit(r2);	 Catch:{ InterruptedException -> 0x0035 }
        throw r0;	 Catch:{ all -> 0x0043 }
    L_0x0043:
        r0 = move-exception;
        r1 = r6.m3882i();
        if (r1 != 0) goto L_0x0053;
    L_0x004a:
        r1 = "VungleDevice";
        r2 = "no device ID available";
        com.vungle.publisher.log.Logger.w(r1, r2);
    L_0x0053:
        throw r0;
    L_0x0054:
        monitor-exit(r2);	 Catch:{ InterruptedException -> 0x0035 }
        r0 = r6.f2884b;	 Catch:{ all -> 0x0043 }
        r0 = r0.get();	 Catch:{ all -> 0x0043 }
        if (r0 == 0) goto L_0x0076;	 Catch:{ all -> 0x0043 }
    L_0x005d:
        r0 = "VungleDevice";	 Catch:{ all -> 0x0043 }
        r1 = "obtained device ID";	 Catch:{ all -> 0x0043 }
        com.vungle.publisher.log.Logger.d(r0, r1);	 Catch:{ all -> 0x0043 }
    L_0x0066:
        r0 = r6.m3882i();
        if (r0 != 0) goto L_0x0075;
    L_0x006c:
        r0 = "VungleDevice";
        r1 = "no device ID available";
        com.vungle.publisher.log.Logger.w(r0, r1);
    L_0x0075:
        return;
    L_0x0076:
        r0 = new com.vungle.publisher.env.j;	 Catch:{ all -> 0x0043 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0043 }
        r1.<init>();	 Catch:{ all -> 0x0043 }
        r2 = "timeout after ";	 Catch:{ all -> 0x0043 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0043 }
        r2 = f2883a;	 Catch:{ all -> 0x0043 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0043 }
        r2 = " ms";	 Catch:{ all -> 0x0043 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0043 }
        r1 = r1.toString();	 Catch:{ all -> 0x0043 }
        r0.<init>(r1);	 Catch:{ all -> 0x0043 }
        throw r0;	 Catch:{ all -> 0x0043 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.env.AndroidDevice.h():void");
    }

    @Inject
    AndroidDevice() {
        Injector.c().m4200a(this);
    }

    public String m3868a() {
        m3881h();
        return this.f2885c;
    }

    boolean m3874b() {
        return !TextUtils.isEmpty(this.f2885c);
    }

    void m3870a(String str) {
        this.f2885c = str;
        m3875c();
        m3880g();
    }

    void m3875c() {
        if (m3874b() && m3879f()) {
            m3878e();
        }
    }

    public String m3877d() {
        m3881h();
        if (m3879f() && m3874b()) {
            Logger.w(Logger.DEVICE_TAG, "have advertising and Android ID");
            m3878e();
        }
        return this.f2886d;
    }

    void m3878e() {
        Logger.i(Logger.DEVICE_TAG, "clearing Android ID");
        this.f2886d = null;
    }

    boolean m3879f() {
        return !TextUtils.isEmpty(this.f2886d);
    }

    void m3873b(String str) {
        if (m3874b()) {
            Logger.w(Logger.DEVICE_TAG, "have advertising id - not setting androidId");
            return;
        }
        Logger.d(Logger.DEVICE_TAG, "setting android ID " + str);
        this.f2886d = str;
        m3880g();
    }

    void m3880g() {
        if (!this.f2884b.getAndSet(true)) {
            synchronized (this.f2884b) {
                this.f2884b.notifyAll();
            }
        }
    }

    boolean m3882i() {
        return m3874b() || m3879f();
    }

    public boolean m3883j() {
        return this.f2894l;
    }

    void m3871a(boolean z) {
        this.f2894l = z;
    }

    public Float m3884k() {
        Float f = null;
        try {
            f = Float.valueOf(this.f2887e.m4340b());
        } catch (Throwable e) {
            Logger.d(Logger.DEVICE_TAG, "error getting volume info", e);
        }
        return f;
    }

    public boolean m3885l() {
        boolean equals = "mounted".equals(Environment.getExternalStorageState());
        boolean a = zj.a(this.f2889g);
        if (a && equals) {
            Logger.v(Logger.DEVICE_TAG, "external storage writable");
        } else {
            Logger.w(Logger.DEVICE_TAG, "external storage not writable");
        }
        return a && equals;
    }

    public boolean m3886m() {
        return m3876c(Logger.DEVICE_TAG);
    }

    boolean m3876c(String str) {
        boolean z;
        Throwable e;
        Throwable th;
        try {
            int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.f2889g);
            z = isGooglePlayServicesAvailable == 0;
            if (!z) {
                try {
                    Logger.w(str, "Google Play Services not available: " + GooglePlayServicesUtil.getErrorString(isGooglePlayServicesAvailable));
                } catch (IllegalStateException e2) {
                    e = e2;
                    Logger.w(Logger.CONFIG_TAG, zb.a(e));
                    return z;
                } catch (NoClassDefFoundError e3) {
                    e = e3;
                    Logger.d(str, e.getClass().getSimpleName() + ": " + e.getMessage());
                    Logger.v(str, e);
                    return z;
                }
            }
        } catch (Throwable e4) {
            th = e4;
            z = false;
            e = th;
            Logger.w(Logger.CONFIG_TAG, zb.a(e));
            return z;
        } catch (Throwable e42) {
            th = e42;
            z = false;
            e = th;
            Logger.d(str, e.getClass().getSimpleName() + ": " + e.getMessage());
            Logger.v(str, e);
            return z;
        }
        return z;
    }

    public void m3887n() {
        this.f2891i.d(this);
    }

    public String m3888o() {
        return this.f2890h.getString("defaultUserAgent", null);
    }

    public void m3869a(WebView webView) {
        this.f2890h.edit().putString("defaultUserAgent", zo.m4932a(webView)).apply();
    }

    public boolean m3872a(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public Long m3889p() {
        Long valueOf;
        try {
            File file = new File(this.f2892j);
            StatFs statFs = new StatFs(file.getPath());
            if (VERSION.SDK_INT >= 26) {
                StorageManager storageManager = (StorageManager) this.f2889g.getSystemService(StorageManager.class);
                if (storageManager != null) {
                    valueOf = Long.valueOf(storageManager.getCacheQuotaBytes(storageManager.getUuidForPath(file)));
                    if (VERSION.SDK_INT >= 18) {
                        return Long.valueOf((long) (statFs.getBlockSize() * statFs.getAvailableBlocks()));
                    }
                    return Long.valueOf(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
                }
            }
            valueOf = null;
            try {
                if (VERSION.SDK_INT >= 18) {
                    return Long.valueOf((long) (statFs.getBlockSize() * statFs.getAvailableBlocks()));
                }
                return Long.valueOf(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
            } catch (Exception e) {
                Logger.e(Logger.DEVICE_TAG, "error getting available bytes");
                return valueOf;
            }
        } catch (Exception e2) {
            valueOf = null;
            Logger.e(Logger.DEVICE_TAG, "error getting available bytes");
            return valueOf;
        }
    }

    public String m3890q() {
        return Build.FINGERPRINT;
    }

    public int m3891r() {
        return VERSION.SDK_INT;
    }

    public boolean m3892s() {
        boolean z = true;
        boolean z2 = false;
        try {
            if (VERSION.SDK_INT < 26) {
                if (Secure.getInt(this.f2889g.getContentResolver(), "install_non_market_apps") != 1) {
                    z = false;
                }
                z2 = z;
            } else if (this.f2889g.checkCallingOrSelfPermission("android.permission.REQUEST_INSTALL_PACKAGES") == 0) {
                z2 = this.f2889g.getApplicationContext().getPackageManager().canRequestPackageInstalls();
            }
        } catch (Throwable e) {
            Logger.e(Logger.DEVICE_TAG, "isInstallNonMarketAppsEnabled Settings not found", e);
        }
        Logger.v(Logger.DEVICE_TAG, "isInstallNonMarketAppsEnabled: " + z2);
        return z2;
    }

    public boolean m3893t() {
        boolean hasSystemFeature;
        boolean z = false;
        String str;
        if (w.a) {
            str = "amazon.hardware.fire_tv";
            hasSystemFeature = this.f2889g.getApplicationContext().getPackageManager().hasSystemFeature("amazon.hardware.fire_tv");
        } else if (VERSION.SDK_INT >= 23) {
            hasSystemFeature = ((UiModeManager) this.f2889g.getSystemService("uimode")).getCurrentModeType() == 4;
        } else {
            str = DeviceProperties.FEATURE_TV_1;
            str = "android.hardware.touchscreen";
            if (this.f2889g.getApplicationContext().getPackageManager().hasSystemFeature(DeviceProperties.FEATURE_TV_1) || !this.f2889g.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
                z = true;
            }
            hasSystemFeature = z;
        }
        Logger.v(Logger.DEVICE_TAG, "isTV: " + hasSystemFeature);
        return hasSystemFeature;
    }

    public JsonObject m3894u() {
        Location location;
        Exception e;
        JsonObject jsonObject;
        if (w.a) {
            LocationManager locationManager = (LocationManager) this.f2889g.getSystemService("location");
            location = null;
            for (String lastKnownLocation : locationManager.getProviders(true)) {
                Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                if (lastKnownLocation2 == null || (location != null && lastKnownLocation2.getAccuracy() >= location.getAccuracy())) {
                    lastKnownLocation2 = location;
                }
                location = lastKnownLocation2;
            }
        } else {
            try {
                Location location2;
                if (this.f2889g.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode < 11011000) {
                    Logger.w(Logger.DEVICE_TAG, "Play services version less than 11.0.11 !");
                    location2 = null;
                } else {
                    try {
                        try {
                            location2 = (Location) Tasks.await(LocationServices.getFusedLocationProviderClient(this.f2889g).getLastLocation(), 100, TimeUnit.MILLISECONDS);
                        } catch (ExecutionException e2) {
                            e = e2;
                            Logger.w(Logger.DEVICE_TAG, e.getCause());
                            location2 = null;
                            location = location2;
                            if (location != null) {
                                return null;
                            }
                            jsonObject = new JsonObject();
                            jsonObject.addProperty("accuracy", String.valueOf(location.getAccuracy()));
                            jsonObject.addProperty("latitude", String.valueOf(location.getLatitude()));
                            jsonObject.addProperty("longitude", String.valueOf(location.getLongitude()));
                            jsonObject.addProperty("speed", String.valueOf(location.getSpeed()));
                            jsonObject.addProperty("timestamp", Long.valueOf(location.getTime()));
                            return jsonObject;
                        } catch (InterruptedException e3) {
                            e = e3;
                            Logger.w(Logger.DEVICE_TAG, e.getCause());
                            location2 = null;
                            location = location2;
                            if (location != null) {
                                return null;
                            }
                            jsonObject = new JsonObject();
                            jsonObject.addProperty("accuracy", String.valueOf(location.getAccuracy()));
                            jsonObject.addProperty("latitude", String.valueOf(location.getLatitude()));
                            jsonObject.addProperty("longitude", String.valueOf(location.getLongitude()));
                            jsonObject.addProperty("speed", String.valueOf(location.getSpeed()));
                            jsonObject.addProperty("timestamp", Long.valueOf(location.getTime()));
                            return jsonObject;
                        } catch (TimeoutException e4) {
                            e = e4;
                            Logger.w(Logger.DEVICE_TAG, e.getCause());
                            location2 = null;
                            location = location2;
                            if (location != null) {
                                return null;
                            }
                            jsonObject = new JsonObject();
                            jsonObject.addProperty("accuracy", String.valueOf(location.getAccuracy()));
                            jsonObject.addProperty("latitude", String.valueOf(location.getLatitude()));
                            jsonObject.addProperty("longitude", String.valueOf(location.getLongitude()));
                            jsonObject.addProperty("speed", String.valueOf(location.getSpeed()));
                            jsonObject.addProperty("timestamp", Long.valueOf(location.getTime()));
                            return jsonObject;
                        }
                    } catch (NoClassDefFoundError e5) {
                        Logger.w(Logger.DEVICE_TAG, "Location Play services FusedLocationProviderClient classes not present, cant get Loc data");
                        location2 = null;
                        location = location2;
                        if (location != null) {
                            return null;
                        }
                        jsonObject = new JsonObject();
                        jsonObject.addProperty("accuracy", String.valueOf(location.getAccuracy()));
                        jsonObject.addProperty("latitude", String.valueOf(location.getLatitude()));
                        jsonObject.addProperty("longitude", String.valueOf(location.getLongitude()));
                        jsonObject.addProperty("speed", String.valueOf(location.getSpeed()));
                        jsonObject.addProperty("timestamp", Long.valueOf(location.getTime()));
                        return jsonObject;
                    } catch (NoSuchMethodError e6) {
                        Logger.w(Logger.DEVICE_TAG, "Location Play services FusedLocationProviderClient classes not present, cant get Loc data");
                        location2 = null;
                        location = location2;
                        if (location != null) {
                            return null;
                        }
                        jsonObject = new JsonObject();
                        jsonObject.addProperty("accuracy", String.valueOf(location.getAccuracy()));
                        jsonObject.addProperty("latitude", String.valueOf(location.getLatitude()));
                        jsonObject.addProperty("longitude", String.valueOf(location.getLongitude()));
                        jsonObject.addProperty("speed", String.valueOf(location.getSpeed()));
                        jsonObject.addProperty("timestamp", Long.valueOf(location.getTime()));
                        return jsonObject;
                    }
                }
                location = location2;
            } catch (NameNotFoundException e7) {
                Logger.w(Logger.DEVICE_TAG, "Play services not present, No Loc data !");
                location = null;
            }
        }
        if (location != null) {
            return null;
        }
        jsonObject = new JsonObject();
        jsonObject.addProperty("accuracy", String.valueOf(location.getAccuracy()));
        jsonObject.addProperty("latitude", String.valueOf(location.getLatitude()));
        jsonObject.addProperty("longitude", String.valueOf(location.getLongitude()));
        jsonObject.addProperty("speed", String.valueOf(location.getSpeed()));
        jsonObject.addProperty("timestamp", Long.valueOf(location.getTime()));
        return jsonObject;
    }
}
