package com.moat.analytics.mobile.mpub;

import android.os.Build.VERSION;
import android.util.Base64;
import android.util.Log;
import com.moat.analytics.mobile.mpub.C3438m.C34372;
import com.moat.analytics.mobile.mpub.C3450r.C3449e;
import com.moat.analytics.mobile.mpub.C3460t.C3456a;
import java.net.URLEncoder;
import java.util.Locale;

final class C3442o extends Exception {
    private static Exception f8768 = null;
    private static final Long f8769 = Long.valueOf(60000);
    private static Long f8770;

    C3442o(String str) {
        super(str);
    }

    static String m11755(String str, Exception exception) {
        if (exception instanceof C3442o) {
            return str + " failed: " + exception.getMessage();
        }
        return str + " failed unexpectedly";
    }

    static void m11756(Exception exception) {
        if (C3460t.m11803().f8825) {
            Log.e("MoatException", Log.getStackTraceString(exception));
        } else {
            C3442o.m11754(exception);
        }
    }

    private static void m11754(Exception exception) {
        int i = 1;
        try {
            if (C3460t.m11803().f8824 == C3456a.f8806) {
                int i2 = C3460t.m11803().f8827;
                if (i2 != 0) {
                    if (i2 >= 100 || ((double) i2) / 100.0d >= Math.random()) {
                        String str = "";
                        String str2 = "";
                        String str3 = "";
                        String str4 = "";
                        StringBuilder stringBuilder = new StringBuilder("https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1");
                        StringBuilder stringBuilder2 = new StringBuilder("&zt=");
                        if (!(exception instanceof C3442o)) {
                            i = 0;
                        }
                        stringBuilder.append(stringBuilder2.append(i).toString());
                        stringBuilder.append("&zr=" + i2);
                        try {
                            String str5;
                            StringBuilder stringBuilder3 = new StringBuilder("&zm=");
                            if (exception.getMessage() == null) {
                                str5 = "null";
                            } else {
                                str5 = URLEncoder.encode(Base64.encodeToString(exception.getMessage().getBytes("UTF-8"), 0), "UTF-8");
                            }
                            stringBuilder.append(stringBuilder3.append(str5).toString());
                            stringBuilder.append("&k=" + URLEncoder.encode(Base64.encodeToString(Log.getStackTraceString(exception).getBytes("UTF-8"), 0), "UTF-8"));
                        } catch (Exception e) {
                        }
                        try {
                            str = BuildConfig.NAMESPACE;
                            stringBuilder.append("&zMoatMMAKv=35d482907bc2811c2e46b96f16eb5f9fe00185f3");
                            str3 = BuildConfig.JMMAK_VERSION;
                            C3449e ˊ = C3450r.m11776();
                            stringBuilder.append("&zMoatMMAKan=" + ˊ.m11771());
                            str2 = ˊ.m11770();
                            str4 = str;
                            str = str2;
                            str2 = str3;
                            str3 = Integer.toString(VERSION.SDK_INT);
                        } catch (Exception e2) {
                            String str6 = str4;
                            str4 = str;
                            str = str2;
                            str2 = str3;
                            str3 = str6;
                        }
                        stringBuilder.append("&d=Android:" + str4 + ":" + str + ":-");
                        stringBuilder.append("&bo=" + str2);
                        stringBuilder.append("&bd=" + str3);
                        Long valueOf = Long.valueOf(System.currentTimeMillis());
                        stringBuilder.append("&t=" + valueOf);
                        stringBuilder.append("&de=" + String.format(Locale.ROOT, "%.0f", new Object[]{Double.valueOf(Math.floor(Math.random() * Math.pow(10.0d, 12.0d)))}));
                        stringBuilder.append("&cs=0");
                        if (f8770 == null || valueOf.longValue() - f8770.longValue() > f8769.longValue()) {
                            new C34372(stringBuilder.toString()).start();
                            f8770 = valueOf;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            f8768 = exception;
        } catch (Exception e3) {
        }
    }

    static void m11757() {
        if (f8768 != null) {
            C3442o.m11754(f8768);
            f8768 = null;
        }
    }
}
