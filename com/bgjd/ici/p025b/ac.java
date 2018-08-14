package com.bgjd.ici.p025b;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Environment;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.bgjd.ici.p029d.C1461l;
import com.bgjd.ici.p030e.C1487j;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ac {
    public static boolean m2861a() {
        return ac.m2880f() || ac.m2882g() || ac.m2884h() || ac.m2883g("su");
    }

    public static boolean m2862a(Context context, String str) {
        if (ac.m2877e()) {
            return ac.m2869b(context, str);
        }
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String[] m2864a(Context context) {
        String[] strArr = new String[2];
        if (VERSION.SDK_INT >= 14) {
            strArr[0] = System.getProperty("http.proxyHost");
            strArr[1] = System.getProperty("http.proxyPort");
            strArr[1] = strArr[1] != null ? strArr[1] : "-1";
        } else {
            strArr[0] = Proxy.getHost(context);
            strArr[1] = String.valueOf(Proxy.getPort(context));
        }
        if (strArr[0] != null && strArr[1] != null) {
            return strArr;
        }
        try {
            try {
                Object invoke = ConnectivityManager.class.getMethod("getProxy", new Class[0]).invoke((ConnectivityManager) context.getSystemService("connectivity"), new Object[0]);
                if (invoke == null) {
                    return null;
                }
                return ac.m2865a(invoke);
            } catch (Exception e) {
                return null;
            }
        } catch (NoSuchMethodException e2) {
            return null;
        } catch (Exception e3) {
            return null;
        }
    }

    public static boolean m2868b(Context context) {
        boolean z;
        if ((context.getResources().getConfiguration().screenLayout & 15) == 4) {
            z = true;
        } else {
            z = false;
        }
        boolean z2;
        if ((context.getResources().getConfiguration().screenLayout & 15) == 3) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    public static String[] m2865a(Object obj) throws Exception {
        r1 = new String[3];
        Class cls = Class.forName("android.net.ProxyProperties");
        r1[0] = (String) cls.getMethod("getHost", new Class[0]).invoke(obj, new Object[0]);
        r1[1] = String.valueOf((Integer) cls.getMethod("getPort", new Class[0]).invoke(obj, new Object[0]));
        r1[2] = (String) cls.getMethod("getExclusionList", new Class[0]).invoke(obj, new Object[0]);
        if (r1[0] != null) {
            return r1;
        }
        return null;
    }

    public static final boolean m2867b() {
        int i;
        if (Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk_x86") || Build.PRODUCT.equals("vbox86p")) {
            i = 1;
        } else {
            i = 0;
        }
        if (Build.MANUFACTURER.equals("unknown") || Build.MANUFACTURER.equals("Genymotion")) {
            i++;
        }
        if (Build.BRAND.equals("generic") || Build.BRAND.equals("generic_x86")) {
            i++;
        }
        if (Build.DEVICE.equals("generic") || Build.DEVICE.equals("generic_x86") || Build.DEVICE.equals("vbox86p")) {
            i++;
        }
        if (Build.MODEL.equals("sdk") || Build.MODEL.equals("google_sdk") || Build.MODEL.equals("Android SDK built for x86")) {
            i++;
        }
        if (Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86")) {
            i++;
        }
        if (Build.FINGERPRINT.contains("generic/sdk/generic") || Build.FINGERPRINT.contains("generic_x86/sdk_x86/generic_x86") || Build.FINGERPRINT.contains("generic/google_sdk/generic") || Build.FINGERPRINT.contains("generic/vbox86p/vbox86p")) {
            i++;
        }
        if (i > 4) {
            return true;
        }
        return false;
    }

    private static boolean m2880f() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean m2882g() {
        for (String file : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean m2884h() {
        /*
        r0 = 1;
        r1 = 0;
        r2 = 0;
        r3 = java.lang.Runtime.getRuntime();	 Catch:{ Throwable -> 0x003b, all -> 0x0044 }
        r4 = 2;
        r4 = new java.lang.String[r4];	 Catch:{ Throwable -> 0x003b, all -> 0x0044 }
        r5 = 0;
        r6 = "/system/xbin/which";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x003b, all -> 0x0044 }
        r5 = 1;
        r6 = "su";
        r4[r5] = r6;	 Catch:{ Throwable -> 0x003b, all -> 0x0044 }
        r2 = r3.exec(r4);	 Catch:{ Throwable -> 0x003b, all -> 0x0044 }
        r3 = new java.io.BufferedReader;	 Catch:{ Throwable -> 0x004b, all -> 0x0044 }
        r4 = new java.io.InputStreamReader;	 Catch:{ Throwable -> 0x004b, all -> 0x0044 }
        r5 = r2.getInputStream();	 Catch:{ Throwable -> 0x004b, all -> 0x0044 }
        r4.<init>(r5);	 Catch:{ Throwable -> 0x004b, all -> 0x0044 }
        r3.<init>(r4);	 Catch:{ Throwable -> 0x004b, all -> 0x0044 }
        r3 = r3.readLine();	 Catch:{ Throwable -> 0x004b, all -> 0x0044 }
        if (r3 == 0) goto L_0x0034;
    L_0x002e:
        if (r2 == 0) goto L_0x0033;
    L_0x0030:
        r2.destroy();
    L_0x0033:
        return r0;
    L_0x0034:
        if (r2 == 0) goto L_0x0039;
    L_0x0036:
        r2.destroy();
    L_0x0039:
        r0 = r1;
        goto L_0x0033;
    L_0x003b:
        r0 = move-exception;
        r0 = r2;
    L_0x003d:
        if (r0 == 0) goto L_0x0042;
    L_0x003f:
        r0.destroy();
    L_0x0042:
        r0 = r1;
        goto L_0x0033;
    L_0x0044:
        r0 = move-exception;
        if (r2 == 0) goto L_0x004a;
    L_0x0047:
        r2.destroy();
    L_0x004a:
        throw r0;
    L_0x004b:
        r0 = move-exception;
        r0 = r2;
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bgjd.ici.b.ac.h():boolean");
    }

    public static String m2859a(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes());
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String m2860a(String str, String str2) {
        if (str == null) {
            try {
                str = "unknown";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        MessageDigest instance = MessageDigest.getInstance(str2);
        instance.update(str.getBytes("UTF-8"));
        byte[] digest = instance.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : digest) {
            stringBuilder.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    public static String m2866b(String str) {
        return ac.m2860a(str, "SHA-1");
    }

    public static String m2870c(String str) {
        return ac.m2860a(str, "SHA-256");
    }

    private static boolean m2883g(String str) {
        for (String str2 : new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"}) {
            if (new File(str2 + str).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean m2872c(Context context) {
        try {
            if ((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static boolean m2875d(Context context) {
        String packageName = context.getPackageName();
        if (packageName == null || !packageName.endsWith(".debug")) {
            return false;
        }
        return true;
    }

    public static boolean m2878e(Context context) {
        if (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) {
            return true;
        }
        return false;
    }

    public static boolean m2871c() {
        boolean z;
        String externalStorageState = Environment.getExternalStorageState();
        boolean z2;
        if ("mounted".equals(externalStorageState)) {
            z2 = true;
            z = true;
        } else if ("mounted_ro".equals(externalStorageState)) {
            z2 = false;
            z = true;
        } else {
            z2 = false;
            z = false;
        }
        if (z && r2) {
            return true;
        }
        return false;
    }

    public static boolean m2863a(String str, File file) throws IOException {
        if (str == null || str == "" || file == null) {
            return false;
        }
        String a = ac.m2858a(file);
        if (a != null) {
            return a.equalsIgnoreCase(str);
        }
        return false;
    }

    public static String m2858a(File file) {
        String str = null;
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            try {
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        instance.update(bArr, 0, read);
                    } catch (Throwable e) {
                        throw new RuntimeException("Unable to process file for MD5", e);
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable e2) {
                            throw new RuntimeException("Unable to close input stream for MD5 calculation", e2);
                        }
                    }
                }
                str = new BigInteger(1, instance.digest()).toString(16);
                str = String.format("%32s", new Object[]{str}).replace(' ', '0');
                try {
                    fileInputStream.close();
                } catch (Throwable e22) {
                    throw new RuntimeException("Unable to close input stream for MD5 calculation", e22);
                }
            } catch (FileNotFoundException e3) {
            }
        } catch (NoSuchAlgorithmException e4) {
        }
        return str;
    }

    public static String m2874d(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes(), 0, str.length());
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static C1461l m2873d() {
        Pattern compile = Pattern.compile("([a-zA-Z]+):\\s*(\\d+)");
        C1461l c1461l = new C1461l();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
            while (true) {
                CharSequence readLine = randomAccessFile.readLine();
                if (readLine == null) {
                    break;
                }
                Matcher matcher = compile.matcher(readLine);
                if (matcher.find()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    if (group.equalsIgnoreCase("MemTotal")) {
                        c1461l.f2305a = Long.parseLong(group2);
                    } else if (group.equalsIgnoreCase("MemFree") || group.equalsIgnoreCase("Buffers") || group.equalsIgnoreCase("Cached") || group.equalsIgnoreCase("SwapFree")) {
                        c1461l.f2306b += Long.parseLong(group2);
                    }
                }
            }
            randomAccessFile.close();
            c1461l.f2305a *= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            c1461l.f2306b *= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return c1461l;
    }

    public static boolean m2877e() {
        return VERSION.SDK_INT >= 23;
    }

    public static boolean m2869b(Context context, String str) {
        boolean z = true;
        try {
            if (context.checkPermission(str, Process.myPid(), Process.myUid()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            try {
                if (((Integer) Class.forName("android.support.v4.content.ContextCompat").getDeclaredMethod("checkSelfPermission", new Class[]{Context.class, String.class}).invoke(null, new Object[]{context, str})).intValue() != 0) {
                    z = false;
                }
                return z;
            } catch (Exception e2) {
                return false;
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static Class<?> m2857a(C1487j c1487j, C1395h c1395h, ClassLoader classLoader) {
        Class<?> cls = null;
        try {
            File file = new File(c1395h.getContext().getFilesDir(), c1487j.m3158j() + ".jar");
            cls = new DexClassLoader(file.getAbsolutePath(), c1395h.getContext().getDir("dex", 0).getAbsolutePath(), null, classLoader).loadClass(c1487j.m3157i());
        } catch (ClassNotFoundException e) {
        } catch (IllegalArgumentException e2) {
        }
        return cls;
    }

    public static String m2876e(String str) {
        return str.replaceAll("\\p{Cntrl}", "").replaceAll(",|:|'|\"|\\\\", " ").replaceAll("(\\r|\\n|\\r\\n)+", " ");
    }

    public static String m2879f(String str) {
        return str.replaceAll("\\p{Cntrl}", "").replaceAll(",|'|\"|\\\\", " ").replaceAll("(\\r|\\n|\\r\\n)+", " ");
    }

    public static boolean m2881f(Context context) {
        boolean z;
        try {
            Class.forName("org.altbeacon.beacon.BeaconConsumer");
            z = true;
        } catch (ClassNotFoundException e) {
            z = false;
        }
        for (RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(C1404b.aw)).getRunningServices(Integer.MAX_VALUE)) {
            if (z && C1404b.aP.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
