package com.unit.two.p151f;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.unit.two.p147c.C4096a;
import com.unit.two.p147c.C4099d;
import com.unit.two.p147c.C4103h;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.zip.GZIPOutputStream;

public final class C4145l {
    public static final String f9704a = C4096a.f9499J;
    public static final String f9705b = C4096a.f9501L;
    private static volatile String f9706c;
    private static String f9707d = null;
    private static int f9708e = -2;
    private static String f9709f;
    private static boolean f9710g;
    private static String f9711h = "";
    private static String f9712i = "";
    private static String f9713j = C4096a.f9497H;
    private static String f9714k = C4096a.f9498I;
    private static String f9715l = C4096a.f9500K;

    static {
        String str = C4096a.f9496G;
    }

    private static long m12820a(Context context, String str) {
        long j = 0;
        try {
            if (C4145l.m12830b(context, str)) {
                j = context.getApplicationContext().getPackageManager().getPackageInfo(str, 0).firstInstallTime;
            }
        } catch (Throwable th) {
        }
        return j;
    }

    public static String m12821a() {
        return new String(C4103h.f9561a, 2, 16);
    }

    public static String m12822a(Context context) {
        if (!TextUtils.isEmpty(f9706c)) {
            return f9706c;
        }
        C4150q.m12871a(new C4146m(context));
        if (TextUtils.isEmpty(f9706c)) {
            try {
                return System.getProperty(C4096a.f9506Q);
            } catch (Throwable th) {
            }
        }
        return f9706c;
    }

    public static String m12823a(String str) {
        try {
            byte[] bytes = str.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 3);
            }
            return new String(bytes, C4096a.f9509T);
        } catch (Exception e) {
            return null;
        }
    }

    private static String m12824a(String str, boolean z, String str2) {
        try {
            MessageDigest instance = MessageDigest.getInstance(C4096a.f9513X);
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

    public static String m12825a(Map map, int i) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        Builder builder = new Builder();
        for (Entry entry : map.entrySet()) {
            builder.appendQueryParameter((String) entry.getKey(), String.valueOf(entry.getValue()));
        }
        return builder.toString().substring(1);
    }

    public static String m12826a(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        Exception e;
        Throwable th;
        String str = null;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.flush();
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e2) {
                    }
                    str = byteArrayOutputStream.toString(C4096a.f9511V);
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
                gZIPOutputStream = str;
                throw e;
            } catch (Throwable th3) {
                th = th3;
                Object obj = str;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e5) {
                    }
                }
                throw th;
            }
        } catch (Exception e6) {
        }
        return str;
    }

    public static int m12827b(Context context) {
        try {
            synchronized (context) {
                if (C4145l.m12861r(context)) {
                    int i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                    return i;
                }
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public static String m12828b() {
        return new String(C4103h.f9562b, 3, 6);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m12829b(java.lang.String r5) {
        /*
        if (r5 == 0) goto L_0x0008;
    L_0x0002:
        r0 = r5.length();
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return r5;
    L_0x0009:
        r1 = new java.io.ByteArrayOutputStream;
        r1.<init>();
        r0 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        r2 = com.unit.two.p147c.C4096a.f9510U;	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        r2 = r5.getBytes(r2);	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        r0.<init>(r2);	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        r2 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        r2.<init>(r0);	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        r0 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x002d, all -> 0x003c }
    L_0x0022:
        r3 = r2.read(r0);	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        if (r3 < 0) goto L_0x0036;
    L_0x0028:
        r4 = 0;
        r1.write(r0, r4, r3);	 Catch:{ Exception -> 0x002d, all -> 0x003c }
        goto L_0x0022;
    L_0x002d:
        r0 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x0041 }
    L_0x0031:
        r5 = r1.toString();
        goto L_0x0008;
    L_0x0036:
        r1.close();	 Catch:{ Exception -> 0x003a }
        goto L_0x0031;
    L_0x003a:
        r0 = move-exception;
        goto L_0x0031;
    L_0x003c:
        r0 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x0043 }
    L_0x0040:
        throw r0;
    L_0x0041:
        r0 = move-exception;
        goto L_0x0031;
    L_0x0043:
        r1 = move-exception;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.two.f.l.b(java.lang.String):java.lang.String");
    }

    private static boolean m12830b(Context context, String str) {
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

    public static String m12831c(String str) {
        return C4145l.m12824a(str, true, C4096a.f9512W);
    }

    public static boolean m12832c() {
        String str = C4096a.aa;
        List arrayList = new ArrayList();
        for (String str2 : Arrays.asList(System.getenv(C4096a.ad).split(C4096a.ae))) {
            String str22;
            if (!str22.endsWith(C4096a.ab)) {
                str22 = str22 + C4096a.ac;
            }
            if (C4145l.m12851i(str22 + str)) {
                arrayList.add(str22);
            }
        }
        return arrayList.size() > 0;
    }

    public static boolean m12833c(Context context) {
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
            z = false;
        } catch (Exception e) {
            z = false;
        }
        return z;
    }

    public static String m12834d() {
        Locale locale = Locale.getDefault();
        return String.format(C4096a.aj, new Object[]{locale.getLanguage().toLowerCase(), locale.getCountry().toLowerCase()});
    }

    public static String m12835d(Context context) {
        return context.getApplicationInfo().packageName;
    }

    public static String m12836d(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(C4096a.f9514Y);
            instance.update(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            byte[] digest = instance.digest();
            for (int i : digest) {
                int i2;
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append(C4096a.f9515Z);
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            str = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return str;
    }

    public static long m12837e() {
        long j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(C4096a.an), 8192);
            j = (long) Integer.valueOf(bufferedReader.readLine().split(C4096a.ao)[1]).intValue();
            bufferedReader.close();
        } catch (IOException e) {
        }
        return j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public static String m12838e(Context context) {
        return C4145l.m12850i(context);
    }

    public static boolean m12839e(String str) {
        return str.startsWith(C4096a.af);
    }

    public static String m12840f() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
        } catch (Exception e) {
            return "";
        }
    }

    public static String m12841f(Context context) {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                Object toUpperCase = telephonyManager.getSimCountryIso().toUpperCase();
                return TextUtils.isEmpty(toUpperCase) ? Locale.getDefault().getCountry().toString().toUpperCase() : toUpperCase;
            }
        } catch (Throwable th) {
        }
        String str2 = str;
        if (TextUtils.isEmpty(toUpperCase)) {
        }
    }

    public static String m12842f(String str) {
        return str.startsWith(C4096a.af) ? C4096a.ag + str.substring(5) : str;
    }

    public static int m12843g() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static long m12844g(Context context) {
        long currentTimeMillis = ((System.currentTimeMillis() - C4145l.m12863t(context)) / 86400000) + 1;
        return currentTimeMillis > 0 ? currentTimeMillis : 1;
    }

    public static final String m12845g(String str) {
        return C4145l.m12824a(str, true, C4096a.aI);
    }

    public static long m12846h(Context context) {
        if (context == null) {
            return 0;
        }
        String packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName) || context == null) {
            return 0;
        }
        long a = C4145l.m12820a(context, packageName);
        return a >= 1 ? ((System.currentTimeMillis() - a) / 86400000) + 1 : 0;
    }

    public static String m12847h() {
        if (TextUtils.isEmpty(f9712i)) {
            CharSequence j = C4145l.m12853j(C4096a.ap);
            f9712i = j;
            if (TextUtils.isEmpty(j)) {
                f9712i = Build.MODEL;
            }
        }
        return f9712i;
    }

    public static String m12849i() {
        try {
            return TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception e) {
            return "";
        }
    }

    public static String m12850i(Context context) {
        C4150q.m12870a();
        if (!C4138e.m12771b(context) && !C4099d.m12676a(context).m12682a()) {
            return C4096a.ai;
        }
        if (f9707d == null) {
            try {
                CharSequence a = C4144k.m12794a(context);
                f9707d = a;
                if (TextUtils.isEmpty(a)) {
                    f9707d = C4134a.m12764a(context).m12765a();
                    C4144k.m12797a(context, f9707d);
                }
            } catch (Exception e) {
            }
        }
        return TextUtils.isEmpty(f9707d) ? C4096a.ah : f9707d;
    }

    private static boolean m12851i(String str) {
        boolean z = false;
        try {
            z = new File(str).exists();
        } catch (Exception e) {
        }
        return z;
    }

    public static int m12852j(Context context) {
        if (f9708e < 0) {
            if (context == null) {
                f9708e = 0;
            } else {
                try {
                    f9708e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (Exception e) {
                    f9708e = 0;
                }
            }
        }
        return f9708e;
    }

    private static String m12853j(String str) {
        try {
            return (String) Class.forName(C4096a.aq).getDeclaredMethod(C4096a.ar, new Class[]{String.class, String.class}).invoke(null, new Object[]{str, ""});
        } catch (SecurityException e) {
        } catch (IllegalArgumentException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (IllegalAccessException e5) {
        } catch (InvocationTargetException e6) {
        }
        return "";
    }

    public static String m12854k(Context context) {
        if (f9709f == null) {
            if (context == null) {
                f9709f = C4096a.ak;
            } else {
                try {
                    f9709f = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                } catch (Exception e) {
                    f9709f = C4096a.al;
                }
            }
        }
        return f9709f;
    }

    public static String m12855l(Context context) {
        if (context == null) {
            return "";
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        return width + C4096a.am + windowManager.getDefaultDisplay().getHeight();
    }

    public static int m12856m(Context context) {
        return ((context.getResources().getConfiguration().screenLayout & 15) >= 3 ? 1 : 0) != 0 ? 1 : 0;
    }

    public static String m12857n(Context context) {
        if (!f9710g) {
            if (context != null) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    CharSequence networkOperator = telephonyManager.getNetworkOperator();
                    f9711h = networkOperator;
                    if (TextUtils.isEmpty(networkOperator)) {
                        f9711h = "";
                    }
                }
            }
            f9710g = true;
        }
        return f9711h;
    }

    public static boolean m12858o(Context context) {
        return C4145l.m12830b(context, f9713j);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m12859p(android.content.Context r3) {
        /*
        r0 = "connectivity";
        r0 = r3.getSystemService(r0);	 Catch:{ Exception -> 0x0069 }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ Exception -> 0x0069 }
        if (r0 == 0) goto L_0x0026;
    L_0x000b:
        r0 = r0.getActiveNetworkInfo();	 Catch:{ Exception -> 0x0069 }
        if (r0 == 0) goto L_0x0026;
    L_0x0011:
        r1 = r0.isAvailable();	 Catch:{ Exception -> 0x0069 }
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r0.getState();	 Catch:{ Exception -> 0x0069 }
        r2 = android.net.NetworkInfo.State.CONNECTED;	 Catch:{ Exception -> 0x0069 }
        if (r1 != r2) goto L_0x0026;
    L_0x001f:
        r1 = r0.getType();	 Catch:{ Exception -> 0x0069 }
        switch(r1) {
            case 0: goto L_0x002c;
            case 1: goto L_0x0029;
            case 2: goto L_0x0033;
            case 3: goto L_0x0033;
            case 4: goto L_0x0033;
            case 5: goto L_0x0033;
            case 6: goto L_0x0033;
            default: goto L_0x0026;
        };
    L_0x0026:
        r0 = f9715l;
    L_0x0028:
        return r0;
    L_0x0029:
        r0 = f9714k;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x002c:
        r0 = r0.getSubtype();	 Catch:{ Exception -> 0x0069 }
        switch(r0) {
            case 0: goto L_0x0066;
            case 1: goto L_0x0036;
            case 2: goto L_0x0039;
            case 3: goto L_0x0045;
            case 4: goto L_0x003c;
            case 5: goto L_0x0048;
            case 6: goto L_0x004b;
            case 7: goto L_0x003f;
            case 8: goto L_0x004e;
            case 9: goto L_0x0051;
            case 10: goto L_0x0054;
            case 11: goto L_0x0042;
            case 12: goto L_0x0057;
            case 13: goto L_0x0060;
            case 14: goto L_0x005a;
            case 15: goto L_0x005d;
            case 16: goto L_0x0063;
            default: goto L_0x0033;
        };	 Catch:{ Exception -> 0x0069 }
    L_0x0033:
        r0 = f9715l;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0036:
        r0 = com.unit.two.p147c.C4096a.as;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0039:
        r0 = com.unit.two.p147c.C4096a.at;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x003c:
        r0 = com.unit.two.p147c.C4096a.au;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x003f:
        r0 = com.unit.two.p147c.C4096a.av;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0042:
        r0 = com.unit.two.p147c.C4096a.aw;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0045:
        r0 = com.unit.two.p147c.C4096a.ax;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0048:
        r0 = com.unit.two.p147c.C4096a.ay;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x004b:
        r0 = com.unit.two.p147c.C4096a.az;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x004e:
        r0 = com.unit.two.p147c.C4096a.aA;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0051:
        r0 = com.unit.two.p147c.C4096a.aB;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0054:
        r0 = com.unit.two.p147c.C4096a.aC;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0057:
        r0 = com.unit.two.p147c.C4096a.aD;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x005a:
        r0 = com.unit.two.p147c.C4096a.aE;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x005d:
        r0 = com.unit.two.p147c.C4096a.aF;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0060:
        r0 = com.unit.two.p147c.C4096a.aG;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0063:
        r0 = com.unit.two.p147c.C4096a.aH;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0066:
        r0 = f9715l;	 Catch:{ Exception -> 0x0069 }
        goto L_0x0028;
    L_0x0069:
        r0 = move-exception;
        r0 = f9715l;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.two.f.l.p(android.content.Context):java.lang.String");
    }

    public static String m12860q(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getState() == State.CONNECTED) {
                    switch (activeNetworkInfo.getType()) {
                        case 0:
                            return f9704a;
                        case 1:
                            return f9714k;
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            return f9715l;
                    }
                }
            }
            return f9715l;
        } catch (Exception e) {
            return f9715l;
        }
    }

    private static boolean m12861r(Context context) {
        try {
            String s = C4145l.m12862s(context);
            if (s != null) {
                return s.equals(context.getPackageName());
            }
        } catch (Exception e) {
        }
        return false;
    }

    private static String m12862s(Context context) {
        Process exec;
        BufferedReader bufferedReader;
        Process process;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        int myPid = Process.myPid();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(C1404b.aw)).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        try {
            BufferedReader bufferedReader3;
            exec = Runtime.getRuntime().exec(C4096a.f9507R + myPid);
            try {
                bufferedReader3 = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            } catch (Throwable th2) {
                th = th2;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e) {
                    }
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
            try {
                if (bufferedReader3.readLine() != null) {
                    String readLine = bufferedReader3.readLine();
                    if (readLine != null) {
                        String[] split = readLine.split(C4096a.f9508S, Integer.MAX_VALUE);
                        readLine = split[split.length - 1];
                        try {
                            bufferedReader3.close();
                        } catch (IOException e2) {
                        }
                        if (exec == null) {
                            return readLine;
                        }
                        exec.destroy();
                        return readLine;
                    }
                }
                try {
                    bufferedReader3.close();
                } catch (IOException e3) {
                }
                if (exec != null) {
                    exec.destroy();
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader2 = bufferedReader3;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (exec != null) {
                    exec.destroy();
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            exec = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (exec != null) {
                exec.destroy();
            }
            throw th;
        }
        return null;
    }

    private static long m12863t(Context context) {
        long j = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (Exception e) {
            return j;
        }
    }
}
