package com.fyber.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appnext.core.Ad;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.common.GpsHelper;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: HostInfo */
public class C2656g {
    private static C2656g f6596a;
    private WindowManager f6597b;
    private ConnectivityManager f6598c;
    private int f6599d;
    private int f6600e;
    private float f6601f;
    private float f6602g;
    private boolean f6603h = false;
    private String f6604i;
    private String f6605j;
    private String f6606k;
    private String f6607l;
    private String f6608m;
    private boolean f6609n = true;
    private String f6610o;
    private LocationManager f6611p;
    private List<String> f6612q;
    private CountDownLatch f6613r = new CountDownLatch(1);

    /* compiled from: HostInfo */
    static class C2651a implements C2648n {
        private Map<String, String> f6592a;

        C2651a() {
        }

        public final synchronized Map<String, String> mo4003a() {
            Map<String, String> emptyMap;
            if (C2656g.f6596a == null) {
                emptyMap = Collections.emptyMap();
            } else {
                if (this.f6592a == null) {
                    this.f6592a = new HashMap();
                    this.f6592a.put("app_bundle_name", C2656g.f6596a.f6607l);
                    this.f6592a.put("app_version", C2656g.f6596a.f6606k);
                }
                emptyMap = this.f6592a;
            }
            return emptyMap;
        }
    }

    /* compiled from: HostInfo */
    static class C2652b implements C2648n {
        private final Map<String, String> f6593a = new HashMap();

        C2652b() {
            this.f6593a.put("os_version", VERSION.RELEASE);
            this.f6593a.put("phone_version", Build.MANUFACTURER + BridgeUtil.UNDERLINE_STR + Build.MODEL);
            this.f6593a.put("manufacturer", Build.MANUFACTURER);
            this.f6593a.put(SchemaSymbols.ATTVAL_LANGUAGE, Locale.getDefault().toString());
        }

        public final synchronized Map<String, String> mo4003a() {
            if (C2656g.f6596a != null) {
                this.f6593a.put("carrier_name", C2656g.f6596a.f6605j);
                this.f6593a.put("carrier_country", C2656g.f6596a.f6604i);
                this.f6593a.put("network_connection_type", C2656g.m8494i(C2656g.f6596a));
            }
            return this.f6593a;
        }
    }

    /* compiled from: HostInfo */
    static class C2653c implements C2648n {
        private Map<String, String> f6594a;

        C2653c() {
        }

        public final synchronized Map<String, String> mo4003a() {
            Map<String, String> emptyMap;
            if (C2656g.f6596a == null) {
                emptyMap = Collections.emptyMap();
            } else {
                if (this.f6594a == null) {
                    this.f6594a = new HashMap();
                    String e = C2656g.f6596a.m8495j();
                    if (StringUtils.nullOrEmpty(e)) {
                        this.f6594a.put("android_id", C2656g.f6596a.m8500b());
                        this.f6594a.put("google_ad_id_limited_tracking_enabled", null);
                    } else {
                        this.f6594a.put("google_ad_id_limited_tracking_enabled", Boolean.toString(C2656g.f6596a.m8497k().booleanValue()));
                    }
                    this.f6594a.put("google_ad_id", e);
                }
                emptyMap = this.f6594a;
            }
            return emptyMap;
        }
    }

    /* compiled from: HostInfo */
    static class C2654d implements C2648n {
        C2654d() {
        }

        public final synchronized Map<String, String> mo4003a() {
            Map<String, String> emptyMap;
            if (C2656g.f6596a == null) {
                emptyMap = Collections.emptyMap();
            } else {
                emptyMap = Collections.singletonMap("orientation", C2656g.f6596a.m8501c());
            }
            return emptyMap;
        }
    }

    /* compiled from: HostInfo */
    static class C2655e implements C2648n {
        private Map<String, String> f6595a;

        C2655e() {
        }

        public final synchronized Map<String, String> mo4003a() {
            Map<String, String> emptyMap;
            if (C2656g.f6596a == null) {
                emptyMap = Collections.emptyMap();
            } else {
                if (this.f6595a == null) {
                    this.f6595a = new HashMap();
                    this.f6595a.put("screen_width", Integer.toString(C2656g.f6596a.f6599d));
                    this.f6595a.put("screen_height", Integer.toString(C2656g.f6596a.f6600e));
                    this.f6595a.put("screen_density_x", Float.toString(C2656g.f6596a.f6601f));
                    this.f6595a.put("screen_density_y", Float.toString(C2656g.f6596a.f6602g));
                }
                emptyMap = this.f6595a;
            }
            return emptyMap;
        }
    }

    public static C2656g m8482a(Context context) {
        if (f6596a == null) {
            synchronized (C2656g.class) {
                if (f6596a == null) {
                    f6596a = new C2656g(context);
                }
            }
        }
        return f6596a;
    }

    private C2656g(final Context context) {
        if (context == null) {
            throw new RuntimeException("A context is required to initialize HostInfo");
        }
        boolean z;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(this, "AdvertisingIdRetriever") {
                final /* synthetic */ C2656g f6591b;

                public final void run() {
                    this.f6591b.m8485b(context);
                }
            }.start();
        } else {
            m8485b(context);
        }
        this.f6605j = "";
        this.f6604i = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            this.f6605j = telephonyManager.getNetworkOperatorName();
            this.f6604i = telephonyManager.getNetworkCountryIso();
        } catch (SecurityException e) {
        }
        try {
            this.f6598c = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (RuntimeException e2) {
        }
        if (this.f6600e == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.f6597b = (WindowManager) context.getSystemService("window");
            this.f6597b.getDefaultDisplay().getMetrics(displayMetrics);
            this.f6599d = displayMetrics.widthPixels;
            this.f6600e = displayMetrics.heightPixels;
            this.f6601f = displayMetrics.xdpi;
            this.f6602g = displayMetrics.ydpi;
        }
        try {
            this.f6606k = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e3) {
            this.f6606k = "";
        }
        Configuration configuration = context.getResources().getConfiguration();
        int d = m8502d();
        if (((d == 0 || d == 2) && configuration.orientation == 2) || ((d == 1 || d == 3) && configuration.orientation == 1)) {
            z = true;
        } else {
            z = false;
        }
        this.f6603h = z;
        List linkedList = new LinkedList();
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
            linkedList.add("gps");
            linkedList.add("passive");
        }
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) {
            linkedList.add("network");
        }
        if (!linkedList.isEmpty()) {
            this.f6611p = (LocationManager) context.getSystemService("location");
            this.f6612q = linkedList;
        }
        this.f6607l = context.getPackageName();
    }

    public final boolean m8499a() {
        if (this.f6598c == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = this.f6598c.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    private void m8485b(Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            Method method = invoke.getClass().getMethod("getId", new Class[0]);
            Method method2 = invoke.getClass().getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]);
            this.f6608m = method.invoke(invoke, new Object[0]).toString();
            this.f6609n = ((Boolean) method2.invoke(invoke, new Object[0])).booleanValue();
        } catch (Exception e) {
            FyberLogger.m8450e("HostInfo", e.getLocalizedMessage(), e);
            this.f6610o = Secure.getString(context.getContentResolver(), "android_id");
            if (this.f6610o == null) {
                this.f6610o = "";
            }
        }
        this.f6613r.countDown();
    }

    private String m8495j() {
        try {
            this.f6613r.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        return this.f6608m;
    }

    public final String m8500b() {
        return this.f6610o;
    }

    private Boolean m8497k() {
        try {
            this.f6613r.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        return Boolean.valueOf(this.f6609n);
    }

    public final String m8501c() {
        String[] strArr = new String[]{Ad.ORIENTATION_PORTRAIT, Ad.ORIENTATION_LANDSCAPE, Ad.ORIENTATION_PORTRAIT, Ad.ORIENTATION_LANDSCAPE, Ad.ORIENTATION_PORTRAIT};
        int d = m8502d();
        if (this.f6603h) {
            d++;
        }
        return strArr[d];
    }

    public final int m8502d() {
        return this.f6597b.getDefaultDisplay().getRotation();
    }

    public final boolean m8503e() {
        return this.f6603h;
    }

    public static boolean m8490f() {
        return C2665m.m8523a(14);
    }

    public final LocationManager m8504g() {
        return this.f6611p;
    }

    public final List<String> m8505h() {
        return this.f6612q;
    }

    static /* synthetic */ String m8494i(C2656g c2656g) {
        if (c2656g.f6598c != null) {
            NetworkInfo activeNetworkInfo = c2656g.f6598c.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType() == 1 ? "wifi" : "cellular";
            }
        }
        return "";
    }
}
