package com.elephant.data.p048g;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.elephant.data.ElephantBroadcastReceiver;
import com.elephant.data.ElephantDataProvider;
import com.elephant.data.p035a.C1720a;
import com.elephant.data.p046f.C1801a;
import com.elephant.data.p048g.p050b.C1812a;
import com.elephant.data.p048g.p050b.C1813b;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

public final class C1816d {
    public static final String f3911a = C1814b.f3890i;
    private static final String f3912b = C1814b.f3892j;
    private static String f3913c = C1814b.f3895m;
    private static String f3914d = C1814b.f3896n;
    private static String f3915e = C1814b.f3897o;
    private static String f3916f = "";
    private static String f3917g;
    private static String f3918h = null;
    private static String f3919i;
    private static int f3920j = -2;
    private static final Map f3921k = new HashMap();
    private static List f3922l;

    static {
        String str = C1814b.f3889h;
        str = C1814b.f3893k;
        str = C1814b.f3894l;
    }

    public static int m5278a(Context context, String str) {
        int i = -1;
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    i = context.getPackageManager().getPackageInfo(str, 128).versionCode;
                }
            } catch (Exception e) {
            }
        }
        return i;
    }

    public static String m5279a(Context context) {
        return context.getApplicationInfo().packageName;
    }

    public static String m5280a(String str) {
        Exception e;
        Throwable th;
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gZIPInputStream = null;
        GZIPInputStream gZIPInputStream2;
        try {
            gZIPInputStream2 = new GZIPInputStream(new ByteArrayInputStream(str.getBytes(C1814b.f3899q)));
            try {
                byte[] bArr = new byte[256];
                while (true) {
                    int read = gZIPInputStream2.read(bArr);
                    if (read >= 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                gZIPInputStream2.close();
                try {
                    byteArrayOutputStream.close();
                } catch (Exception e3) {
                }
            } catch (Exception e4) {
                e2 = e4;
                try {
                    e2.printStackTrace();
                    if (gZIPInputStream2 != null) {
                        try {
                            gZIPInputStream2.close();
                        } catch (Exception e22) {
                            e22.printStackTrace();
                        }
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e5) {
                    }
                    return byteArrayOutputStream.toString();
                } catch (Throwable th2) {
                    th = th2;
                    gZIPInputStream = gZIPInputStream2;
                    if (gZIPInputStream != null) {
                        try {
                            gZIPInputStream.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e7) {
                    }
                    throw th;
                }
            }
        } catch (Exception e8) {
            e22 = e8;
            gZIPInputStream2 = null;
            e22.printStackTrace();
            if (gZIPInputStream2 != null) {
                gZIPInputStream2.close();
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toString();
        } catch (Throwable th3) {
            th = th3;
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
        return byteArrayOutputStream.toString();
    }

    public static String m5281a(String str, int i) {
        String str2 = null;
        try {
            InputStream openStream = new URL(str).openStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[i];
            int read = openStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            if (read == i) {
                str2 = Arrays.toString(byteArrayOutputStream.toByteArray());
                if (openStream != null) {
                    openStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2;
    }

    private static String m5282a(String str, boolean z, String str2) {
        try {
            MessageDigest instance = MessageDigest.getInstance(C1814b.f3901s);
            if (TextUtils.isEmpty(str2)) {
                instance.update(str.getBytes());
            } else {
                instance.update(str.getBytes(str2));
            }
            byte[] digest = instance.digest();
            String str3 = "";
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                String toHexString = Integer.toHexString(digest[i]);
                if (toHexString.length() > 2) {
                    toHexString = toHexString.substring(toHexString.length() - 2);
                } else if (toHexString.length() != 2) {
                    int length = 2 - toHexString.length();
                    StringBuilder stringBuilder2 = new StringBuilder(toHexString);
                    char[] cArr = new char[length];
                    Arrays.fill(cArr, '0');
                    stringBuilder2.insert(0, cArr);
                    toHexString = stringBuilder2.toString();
                }
                stringBuilder.append(toHexString);
                if (i < digest.length - 1) {
                    stringBuilder.append(str3);
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String m5283a(JSONObject jSONObject, String str, String str2) {
        return jSONObject.isNull(str) ? null : jSONObject.optString(str, null);
    }

    public static String m5284a(byte[] bArr) {
        Exception e;
        Throwable th;
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.flush();
                try {
                    gZIPOutputStream.close();
                } catch (Exception e2) {
                }
                return byteArrayOutputStream.toString(C1814b.f3898p);
            } catch (Exception e3) {
                e = e3;
                try {
                    throw e;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Exception e4) {
            e = e4;
            gZIPOutputStream = null;
            throw e;
        } catch (Throwable th3) {
            th = th3;
            gZIPOutputStream = null;
            if (gZIPOutputStream != null) {
                try {
                    gZIPOutputStream.close();
                } catch (Exception e5) {
                }
            }
            throw th;
        }
    }

    public static boolean m5285a() {
        String str = C1814b.f3902t;
        List arrayList = new ArrayList();
        for (String str2 : Arrays.asList(System.getenv(C1814b.f3905w).split(C1814b.f3906x))) {
            String str22;
            if (!str22.endsWith(C1814b.f3903u)) {
                str22 = str22 + C1814b.f3904v;
            }
            if (C1816d.m5312g(str22 + str)) {
                arrayList.add(str22);
            }
        }
        return arrayList.size() > 0;
    }

    public static boolean m5286a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str2)) {
            return false;
        }
        if (!(str2.startsWith(f3914d) || str2.startsWith(f3915e) || (!str2.startsWith(f3913c) && (str2.startsWith(C1814b.f3874T) || str2.startsWith(C1814b.f3875U))))) {
            if (str2.startsWith(f3913c)) {
                str2 = str2.replace(f3913c, C1814b.f3876V);
            }
            str2 = f3915e + str2;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                Object obj = null;
                for (ResolveInfo resolveInfo : queryIntentActivities) {
                    Object obj2;
                    ActivityInfo activityInfo = resolveInfo != null ? resolveInfo.activityInfo : null;
                    String str3 = activityInfo != null ? activityInfo.packageName : null;
                    String str4 = activityInfo != null ? activityInfo.name : null;
                    if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || obj != null) {
                        obj2 = obj;
                    } else {
                        intent.setClassName(str3, str4);
                        obj2 = 1;
                    }
                    if (str3.equals(str)) {
                        intent.setClassName(str3, str4);
                        break;
                    }
                    obj = obj2;
                }
            }
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m5287a(Context context, String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str) || !C1816d.m5292b(context, f3911a)) {
            return false;
        }
        try {
            if (!str.startsWith(f3913c)) {
                if (str.startsWith(f3914d)) {
                    str = str.substring(str.indexOf(C1814b.f3868N) + C1814b.f3869O.length());
                } else if (str.startsWith(f3915e)) {
                    str = str.substring(str.indexOf(C1814b.f3870P) + C1814b.f3871Q.length());
                } else if (str.startsWith(C1814b.f3872R) || str.startsWith(C1814b.f3873S)) {
                    return C1816d.m5307f(context, str);
                }
                str = f3913c + str;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setPackage(f3911a);
            if (context instanceof Activity) {
                intent.addFlags(1073741824);
            } else {
                intent.addFlags(ErrorDialogData.BINDER_CRASH);
                intent.addFlags(32768);
                intent.addFlags(524288);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            C1816d.m5307f(context, str);
            return false;
        }
    }

    public static int m5288b() {
        return VERSION.SDK_INT;
    }

    public static int m5289b(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static final String m5290b(String str) {
        return C1816d.m5282a(str, true, C1814b.f3900r);
    }

    private static String m5291b(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            stringBuilder.append(Integer.toHexString((bArr[i] >> 4) & 15));
            stringBuilder.append(Integer.toHexString(bArr[i] & 15));
        }
        return stringBuilder.toString();
    }

    public static boolean m5292b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1024);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String m5293c() {
        if (TextUtils.isEmpty(f3916f)) {
            CharSequence h = C1816d.m5315h(C1814b.f3907y);
            f3916f = h;
            if (TextUtils.isEmpty(h)) {
                f3916f = Build.MODEL;
            }
        }
        return f3916f;
    }

    public static String m5294c(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                Object toUpperCase = telephonyManager.getSimCountryIso().toUpperCase();
                return TextUtils.isEmpty(toUpperCase) ? Locale.getDefault().getCountry().toString().toUpperCase() : toUpperCase;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        String str2 = str;
        if (TextUtils.isEmpty(toUpperCase)) {
        }
    }

    public static String m5295c(Context context, String str) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    MessageDigest instance = MessageDigest.getInstance(C1814b.f3867M);
                    File file = new File(context.getPackageManager().getApplicationInfo(str, 128).sourceDir);
                    InputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[4096];
                    long length = file.length();
                    long j = 0;
                    while (j < length && !Thread.interrupted()) {
                        int read = fileInputStream.read(bArr);
                        j += (long) read;
                        instance.update(bArr, 0, read);
                    }
                    String b = C1816d.m5291b(instance.digest());
                    return b != null ? b.toUpperCase() : "";
                }
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }

    public static boolean m5296c(String str) {
        return str.startsWith(C1814b.f3860F);
    }

    public static long m5297d(Context context) {
        long j = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    public static long m5298d(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return 0;
        }
        long i = C1816d.m5317i(context, str);
        return i >= 1 ? ((System.currentTimeMillis() - i) / 86400000) + 1 : 0;
    }

    public static String m5299d() {
        Locale locale = Locale.getDefault();
        return String.format(C1814b.f3864J, new Object[]{locale.getLanguage().toLowerCase(), locale.getCountry().toLowerCase()});
    }

    public static String m5300d(String str) {
        return str.startsWith(C1814b.f3860F) ? C1814b.f3861G + str.substring(5) : str;
    }

    public static String m5301e() {
        try {
            return TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception e) {
            return "";
        }
    }

    public static String m5302e(Context context) {
        return C1816d.m5318i(context);
    }

    public static void m5303e(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
                if (launchIntentForPackage != null) {
                    context.startActivity(launchIntentForPackage);
                }
            } catch (Throwable th) {
            }
        }
    }

    public static boolean m5304e(String str) {
        return TextUtils.isEmpty(str) ? false : str.startsWith(f3913c) || str.startsWith(f3914d) || str.startsWith(f3915e);
    }

    public static String m5305f() {
        return VERSION.RELEASE;
    }

    public static String m5306f(Context context) {
        try {
            if (!TextUtils.isEmpty(f3917g)) {
                return f3917g;
            }
            if (VERSION.SDK_INT >= 17) {
                f3917g = WebSettings.getDefaultUserAgent(context);
            } else {
                try {
                    Class cls = Class.forName(C1814b.f3856B);
                    Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(C1814b.f3857C)});
                    declaredConstructor.setAccessible(true);
                    f3917g = (String) cls.getMethod(C1814b.f3858D, new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
                } catch (Exception e) {
                    f3917g = System.getProperty(C1814b.f3859E);
                }
            }
            return f3917g;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean m5307f(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!(str.startsWith(f3914d) || str.startsWith(f3915e) || (!str.startsWith(f3913c) && (str.startsWith(C1814b.f3877W) || str.startsWith(C1814b.f3878X))))) {
            if (str.startsWith(f3913c)) {
                str = str.replace(f3913c, C1814b.f3879Y);
            }
            str = f3915e + str;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                ActivityInfo activityInfo = queryIntentActivities.get(0) != null ? ((ResolveInfo) queryIntentActivities.get(0)).activityInfo : null;
                Object obj = activityInfo != null ? activityInfo.packageName : null;
                Object obj2 = activityInfo != null ? activityInfo.name : null;
                if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2))) {
                    intent.setClassName(obj, obj2);
                }
            }
            if (context instanceof Activity) {
                intent.addFlags(1073741824);
            } else {
                intent.addFlags(ErrorDialogData.BINDER_CRASH);
                intent.addFlags(32768);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m5308f(String str) {
        Long l = (Long) f3921k.get(str);
        f3921k.put(str, Long.valueOf(System.currentTimeMillis()));
        return l == null ? false : System.currentTimeMillis() - l.longValue() < 86400000;
    }

    public static String m5309g() {
        return VERSION.SDK;
    }

    public static boolean m5310g(Context context) {
        try {
            return context.getPackageManager().getReceiverInfo(new ComponentName(context, ElephantBroadcastReceiver.class), 65536) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean m5311g(Context context, String str) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
            return (packageInfo.applicationInfo.flags & 1) > 0 && (packageInfo.applicationInfo.flags & 128) == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean m5312g(String str) {
        boolean z = false;
        try {
            z = new File(str).exists();
        } catch (Exception e) {
        }
        return z;
    }

    public static int m5313h() {
        return Calendar.getInstance().get(11);
    }

    public static synchronized String m5314h(Context context, String str) {
        String string;
        Exception e;
        synchronized (C1816d.class) {
            if (context != null) {
                if (!TextUtils.isEmpty(str)) {
                    String str2 = "";
                    try {
                        string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
                        if (string == null) {
                            try {
                                int i = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt(str, 0);
                                if (i > 0) {
                                    string = String.valueOf(i);
                                }
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                return string;
                            }
                        }
                    } catch (Exception e3) {
                        Exception exception = e3;
                        string = str2;
                        e = exception;
                        e.printStackTrace();
                        return string;
                    }
                }
            }
            string = null;
        }
        return string;
    }

    private static String m5315h(String str) {
        try {
            return (String) Class.forName(C1814b.f3908z).getDeclaredMethod(C1814b.f3855A, new Class[]{String.class, String.class}).invoke(null, new Object[]{str, ""});
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        }
        return "";
    }

    public static boolean m5316h(Context context) {
        try {
            return context.getPackageManager().getProviderInfo(new ComponentName(context, ElephantDataProvider.class), 65536) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    private static long m5317i(Context context, String str) {
        long j = 0;
        try {
            if (C1816d.m5292b(context, str)) {
                j = context.getApplicationContext().getPackageManager().getPackageInfo(str, 0).firstInstallTime;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return j;
    }

    public static String m5318i(Context context) {
        C1821i.m5345a();
        if (!C1813b.m5272i(context) && !C1801a.m5177a(context).m5183a()) {
            return C1814b.f3863I;
        }
        if (f3918h == null) {
            try {
                CharSequence string = C1812a.m5243a(context).getString(f3912b, "");
                f3918h = string;
                if (TextUtils.isEmpty(string)) {
                    f3918h = C1720a.m4968a(context).m4971a();
                    Editor edit = C1812a.m5243a(context).edit();
                    edit.putString(f3912b, f3918h);
                    edit.commit();
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return TextUtils.isEmpty(f3918h) ? C1814b.f3862H : f3918h;
    }

    public static int m5319j(Context context) {
        if (f3920j < 0) {
            if (context == null) {
                f3920j = 0;
            } else {
                try {
                    f3920j = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (NameNotFoundException e) {
                    f3920j = 0;
                }
            }
        }
        return f3920j;
    }

    public static String m5320k(Context context) {
        if (f3919i == null) {
            if (context == null) {
                f3919i = C1814b.f3865K;
            } else {
                try {
                    f3919i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                } catch (NameNotFoundException e) {
                    f3919i = C1814b.f3866L;
                }
            }
        }
        return f3919i;
    }

    public static long m5321l(Context context) {
        return context == null ? 0 : C1816d.m5298d(context, context.getPackageName());
    }

    public static boolean m5322m(Context context) {
        return C1816d.m5292b(context, f3911a);
    }

    public static boolean m5323n(Context context) {
        if (context == null) {
            return false;
        }
        boolean z;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    z = true;
                    return z;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public static boolean m5324o(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return (activeNetworkInfo == null || activeNetworkInfo.getType() != 0 || (Proxy.getDefaultHost() == null && Proxy.getHost(context) == null)) ? false : true;
    }

    public static int m5325p(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        if (simOperator != null) {
            if (simOperator.startsWith(C1814b.f3880Z) || simOperator.startsWith(C1814b.aa)) {
                return 0;
            }
            if (simOperator.startsWith(C1814b.ab)) {
                return 1;
            }
            if (simOperator.startsWith(C1814b.ac)) {
                return 2;
            }
        }
        return -1;
    }

    public static int m5326q(Context context) {
        int i;
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        String typeName = activeNetworkInfo.getTypeName();
                        if (typeName.equalsIgnoreCase(C1814b.ad)) {
                            return 4;
                        }
                        if (typeName.equalsIgnoreCase(C1814b.ae)) {
                            Object obj;
                            switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                                case 0:
                                    obj = null;
                                    break;
                                case 1:
                                    obj = null;
                                    break;
                                case 2:
                                    obj = null;
                                    break;
                                case 3:
                                    i = 1;
                                    break;
                                case 4:
                                    obj = null;
                                    break;
                                case 5:
                                    i = 1;
                                    break;
                                case 6:
                                    i = 1;
                                    break;
                                case 7:
                                    obj = null;
                                    break;
                                case 8:
                                    i = 1;
                                    break;
                                case 9:
                                    i = 1;
                                    break;
                                case 10:
                                    i = 1;
                                    break;
                                case 11:
                                    obj = null;
                                    break;
                                case 12:
                                    i = 1;
                                    break;
                                case 13:
                                    i = 1;
                                    break;
                                case 14:
                                    i = 1;
                                    break;
                                case 15:
                                    i = 1;
                                    break;
                                default:
                                    obj = null;
                                    break;
                            }
                            i = obj != null ? 2 : 1;
                            return i;
                        }
                    }
                }
            } catch (Exception e) {
                return -1;
            }
        }
        i = -1;
        return i;
    }

    public static String m5327r(Context context) {
        return Proxy.getHost(context);
    }

    public static int m5328s(Context context) {
        return Proxy.getPort(context);
    }

    public static final String m5329t(Context context) {
        return context.getPackageName() + C1814b.af;
    }

    public static boolean m5330u(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (packageManager.checkPermission(C1814b.ag, context.getPackageName()) == 0) && (packageManager.checkPermission(C1814b.ah, context.getPackageName()) == 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List m5331v(Context context) {
        if (f3922l != null) {
            return f3922l;
        }
        try {
            String str = C1814b.ai;
            String str2 = C1814b.aj;
            Intent intent = new Intent(C1814b.ak);
            intent.addCategory(str);
            intent.addCategory(str2);
            intent.setDataAndType(Uri.parse(C1814b.al), null);
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 32);
            if (queryIntentActivities.size() <= 0) {
                return null;
            }
            f3922l = new ArrayList();
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo != null) {
                    try {
                        f3922l.add(((ResolveInfo) queryIntentActivities.get(0)).activityInfo.packageName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return f3922l;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String m5332w(Context context) {
        return C1811a.m5238a(context);
    }
}
