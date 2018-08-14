package com.facebook.ads.internal.p056q.p076c;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.internal.p064g.C1986b;
import com.facebook.ads.internal.p071p.p072a.C2048a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.stripe.android.model.Card;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class C2145d {
    private static String f5126a = null;
    private static final Set<String> f5127b = new HashSet(1);
    private static final Set<String> f5128c = new HashSet(2);

    public enum C2144a {
        f5118a(0),
        NONE(0),
        MOBILE_INTERNET(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        MOBILE_4G(4);
        
        public final int f5125g;

        private C2144a(int i) {
            this.f5125g = i;
        }
    }

    static {
        f5127b.add("1ww8E0AYsR2oX5lndk2hwp2Uosk=\n");
        f5128c.add("toZ2GRnRjC9P5VVUdCpOrFH8lfQ=\n");
        f5128c.add("3lKvjNsfmrn+WmfDhvr2iVh/yRs=\n");
        f5128c.add("B08QtE4yLCdli4rptyqAEczXOeA=\n");
        f5128c.add("XZXI6anZbdKf+taURdnyUH5ipgM=\n");
    }

    public static C2048a m6860a(Context context) {
        return C2145d.m6861a(context, true);
    }

    public static C2048a m6861a(Context context, boolean z) {
        C2048a c2048a = new C2048a();
        C2145d.m6863a(context, c2048a, z);
        return c2048a;
    }

    private static String m6862a(Context context, String str, String str2) {
        Class cls = Class.forName(str);
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            String str3 = (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
            return str3;
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    private static void m6863a(Context context, C2048a c2048a, boolean z) {
        C1986b c1986b = new C1986b(context);
        c2048a.m6592c(30000);
        c2048a.m6590b(3);
        c2048a.m6574a("user-agent", C2145d.m6868c(context, z) + " [FBAN/AudienceNetworkForAndroid;FBSN/" + "Android" + ";FBSV/" + C1986b.f4639a + ";FBAB/" + c1986b.m6275f() + ";FBAV/" + c1986b.m6276g() + ";FBBV/" + c1986b.m6277h() + ";FBVS/" + "4.28.1" + ";FBLC/" + Locale.getDefault().toString() + "]");
    }

    public static boolean m6864a() {
        Object urlPrefix = AdInternalSettings.getUrlPrefix();
        return !TextUtils.isEmpty(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    public static C2048a m6865b(Context context) {
        return C2145d.m6866b(context, true);
    }

    public static C2048a m6866b(Context context, boolean z) {
        C2048a c2048a = new C2048a();
        C2145d.m6863a(context, c2048a, z);
        if (!C2145d.m6864a()) {
            c2048a.m6591b(f5128c);
            c2048a.m6584a(f5127b);
        }
        return c2048a;
    }

    public static C2144a m6867c(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            return C2144a.f5118a;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return C2144a.NONE;
        }
        if (activeNetworkInfo.getType() != 0) {
            return C2144a.MOBILE_INTERNET;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return C2144a.MOBILE_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return C2144a.MOBILE_3G;
            case 13:
                return C2144a.MOBILE_4G;
            default:
                return C2144a.f5118a;
        }
    }

    private static String m6868c(Context context, boolean z) {
        if (context == null) {
            return Card.UNKNOWN;
        }
        if (z) {
            return System.getProperty("http.agent");
        }
        if (f5126a != null) {
            return f5126a;
        }
        synchronized (C2145d.class) {
            if (f5126a != null) {
                String str = f5126a;
                return str;
            }
            if (VERSION.SDK_INT >= 17) {
                try {
                    f5126a = C2145d.m6869d(context);
                    str = f5126a;
                    return str;
                } catch (Exception e) {
                }
            }
            try {
                f5126a = C2145d.m6862a(context, "android.webkit.WebSettings", "android.webkit.WebView");
            } catch (Exception e2) {
                try {
                    f5126a = C2145d.m6862a(context, "android.webkit.WebSettingsClassic", "android.webkit.WebViewClassic");
                } catch (Exception e3) {
                    WebView webView = new WebView(context.getApplicationContext());
                    f5126a = webView.getSettings().getUserAgentString();
                    webView.destroy();
                }
            }
            return f5126a;
        }
    }

    @TargetApi(17)
    private static String m6869d(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
}
