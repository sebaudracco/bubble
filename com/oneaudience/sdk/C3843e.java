package com.oneaudience.sdk;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.C3835f;
import com.oneaudience.sdk.p135c.p136a.C3822b;
import com.oneaudience.sdk.p135c.p137b.C3827d;
import com.stripe.android.model.Card;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;

public class C3843e {
    private static final String f9202a = C3843e.class.getSimpleName();

    public static final class C3842a {
        public static String m12278a(String str) {
            try {
                for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    if (str != null) {
                        if (networkInterface.getName().equalsIgnoreCase(str)) {
                        }
                    }
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < hardwareAddress.length; i++) {
                        stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    return stringBuilder.toString();
                }
            } catch (Throwable e) {
                C3833d.m12250b(C3843e.f9202a, "Error getting MAC address", e);
            }
            return null;
        }

        public static String[] m12279a() {
            try {
                C3833d.m12252c(C3843e.f9202a, "Taking Local IP Address...");
                for (NetworkInterface inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                    for (InetAddress inetAddress : Collections.list(inetAddresses.getInetAddresses())) {
                        if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                            String toUpperCase = inetAddress.getHostAddress().toUpperCase();
                            return new String[]{toUpperCase, inetAddresses.getName()};
                        }
                    }
                }
            } catch (Throwable e) {
                C3833d.m12250b(C3843e.f9202a, "Error getting local IP address", e);
            }
            return null;
        }
    }

    private C3843e() {
    }

    public static int m12280a(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String m12282a(Context context, SharedPreferences sharedPreferences) {
        Object string;
        if (sharedPreferences != null) {
            string = sharedPreferences.getString("app_key", "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
        }
        if (context != null) {
            try {
                string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("oneAudienceAppKey");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            } catch (Exception e) {
                C3843e.m12284a(context, sharedPreferences, "Failed to load app key from meta data: " + e.getMessage());
            }
        }
        C3833d.m12254d(f9202a, "Could't get saved app key");
        return null;
    }

    public static void m12283a(Context context, long j) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, OneAudienceReceiver.class);
        intent.setAction("com.oneaudience.action.CONFIGURATION");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
        C3833d.m12253c(f9202a, "configuration properties after sync -> Next Time: %1$s, new refresh interval: %2$s", C3827d.m12235a(System.currentTimeMillis() + j), C3827d.m12236b(j));
        alarmManager.set(0, r2, broadcast);
    }

    public static void m12284a(final Context context, final SharedPreferences sharedPreferences, final Object obj) {
        try {
            if (!TextUtils.isEmpty(sharedPreferences.getString("report_error_url", "http://robocop.oneaudience.com/reporterror"))) {
                final C3844f c3844f = new C3844f();
                new Thread(new Runnable() {
                    public void run() {
                        C3822b a = new C3820b().m12225a(c3844f.m12292a(context, sharedPreferences, obj));
                        C3833d.m12255d(C3843e.f9202a, "Error Reporting code : %d", Integer.valueOf(a.f9185a));
                    }
                }).start();
            }
        } catch (Exception e) {
        }
    }

    public static boolean m12285a(Context context, String str) {
        return (context == null || context.getApplicationContext() == null || context.getApplicationContext().checkCallingOrSelfPermission(str) != 0) ? false : true;
    }

    public static boolean m12286a(Context context, String str, boolean z) {
        if (context != null) {
            try {
                z = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(str, z);
            } catch (Exception e) {
                C3833d.m12255d(f9202a, "Couldn't find meta data for key: %s ", str);
            }
        }
        return z;
    }

    public static String m12287b(Context context) {
        if (context == null) {
            return Card.UNKNOWN;
        }
        try {
            int i = context.getApplicationInfo().labelRes;
            if (i != 0) {
                return context.getString(i);
            }
        } catch (Exception e) {
        }
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (NameNotFoundException e2) {
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : Card.UNKNOWN);
    }

    public static boolean m12288b(Context context, SharedPreferences sharedPreferences) {
        return (sharedPreferences != null && C3843e.m12286a(context, "showEula", false)) ? sharedPreferences.getBoolean(C1404b.f2104H, false) : true;
    }

    public static String m12289c(Context context) {
        try {
            return C3835f.m12264a(context) ? "debug" : context.getPackageManager().getInstallerPackageName(context.getPackageName());
        } catch (Exception e) {
            return null;
        }
    }

    public static String m12290d(Context context) {
        String str = "";
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            str = invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]).toString();
        } catch (Throwable th) {
            C3833d.m12254d(f9202a, "unable to get AAID.");
        }
        return str;
    }

    public static String m12291e(Context context) {
        if (C3843e.m12285a(context, "android.permission.READ_LOGS")) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String str = "";
                while (true) {
                    str = bufferedReader.readLine();
                    if (str == null) {
                        return stringBuilder.toString();
                    }
                    stringBuilder.append(str);
                }
            } catch (Exception e) {
            }
        }
        return "";
    }
}
