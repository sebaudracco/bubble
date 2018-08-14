package com.facebook.ads.internal.p068l;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.BuildConfig;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.Iterator;
import org.json.JSONObject;
import org.telegram.messenger.exoplayer2.ExoPlayerLibraryInfo;

public class C2005a {
    private static C2005a f4710a;
    private final SharedPreferences f4711b;

    public C2005a(Context context) {
        this.f4711b = context.getApplicationContext().getSharedPreferences("com.facebook.ads.FEATURE_CONFIG", 0);
    }

    public static boolean m6339a(Context context) {
        return VERSION.SDK_INT >= 14 && C2005a.m6341b(BuildConfig.APPLICATION_ID, ExoPlayerLibraryInfo.TAG) && C2005a.m6358s(context).m6363a("adnw_enable_exoplayer", false);
    }

    public static boolean m6340b(Context context) {
        return VERSION.SDK_INT >= 18 && C2005a.m6358s(context).m6363a("adnw_enable_debug_overlay", false);
    }

    private static boolean m6341b(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean m6342c(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_block_lockscreen", false);
    }

    public static boolean m6343d(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_android_memory_opt", false);
    }

    public static boolean m6344e(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_android_disable_blur", false);
    }

    public static boolean m6345f(Context context) {
        return VERSION.SDK_INT >= 19 && C2005a.m6358s(context).m6363a("adnw_enable_iab", false);
    }

    public static boolean m6346g(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_debug_logging", false);
    }

    public static long m6347h(Context context) {
        return C2005a.m6358s(context).m6360a("unified_logging_immediate_delay_ms", 500);
    }

    public static long m6348i(Context context) {
        return ((long) C2005a.m6358s(context).m6359a("unified_logging_dispatch_interval_seconds", (int) HttpStatus.SC_MULTIPLE_CHOICES)) * 1000;
    }

    public static int m6349j(Context context) {
        return C2005a.m6358s(context).m6359a("unified_logging_event_limit", -1);
    }

    public static boolean m6350k(Context context) {
        return C2005a.m6358s(context).m6361a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    public static int m6351l(Context context) {
        return C2005a.m6358s(context).m6359a("minimum_elapsed_time_after_impression", -1);
    }

    public static int m6352m(Context context) {
        return C2005a.m6358s(context).m6359a("stack_trace_sample_rate", 0);
    }

    public static boolean m6353n(Context context) {
        return C2005a.m6358s(context).m6363a("visible_area_check_enabled", false);
    }

    public static int m6354o(Context context) {
        return C2005a.m6358s(context).m6359a("visible_area_percentage", 50);
    }

    public static boolean m6355p(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_measurement_method", false);
    }

    public static boolean m6356q(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_top_activity_viewability", false);
    }

    public static boolean m6357r(Context context) {
        return C2005a.m6358s(context).m6363a("adnw_enhanced_viewability_area_check", false);
    }

    private static C2005a m6358s(Context context) {
        if (f4710a == null) {
            synchronized (C2005a.class) {
                if (f4710a == null) {
                    f4710a = new C2005a(context);
                }
            }
        }
        return f4710a;
    }

    public int m6359a(String str, int i) {
        String string = this.f4711b.getString(str, String.valueOf(i));
        return (string == null || string.equals("null")) ? i : Integer.valueOf(string).intValue();
    }

    public long m6360a(String str, long j) {
        String string = this.f4711b.getString(str, String.valueOf(j));
        return (string == null || string.equals("null")) ? j : Long.valueOf(string).longValue();
    }

    @Nullable
    public String m6361a(String str, String str2) {
        String string = this.f4711b.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    public void m6362a(@Nullable String str) {
        if (str != null && !str.isEmpty() && !str.equals("[]")) {
            Editor edit = this.f4711b.edit();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                edit.putString(str2, jSONObject.getString(str2));
            }
            edit.commit();
        }
    }

    public boolean m6363a(String str, boolean z) {
        String string = this.f4711b.getString(str, String.valueOf(z));
        return (string == null || string.equals("null")) ? z : Boolean.valueOf(string).booleanValue();
    }
}
