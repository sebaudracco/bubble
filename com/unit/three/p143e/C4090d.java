package com.unit.three.p143e;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.WindowManager;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.unit.three.p141c.C4078f;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.GZIPOutputStream;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

public class C4090d {
    private static String f9471a;
    private static int f9472b = -2;
    private static String f9473c = "";
    private static boolean f9474d;
    private static String f9475e = "";
    private static String f9476f;

    public static String m12621a() {
        return System.getProperty("http.agent");
    }

    private static String m12622a(String str, boolean z, String str2) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
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

    public static String m12623a(HttpURLConnection httpURLConnection) {
        InputStreamReader inputStreamReader;
        String readLine;
        BufferedReader bufferedReader;
        Throwable th;
        JSONObject jSONObject;
        InputStreamReader inputStreamReader2 = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader2;
        try {
            InputStream errorStream = httpURLConnection.getErrorStream();
            inputStreamReader = new InputStreamReader(errorStream == null ? httpURLConnection.getInputStream() : errorStream);
            try {
                bufferedReader2 = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            stringBuilder.append(readLine).append('\n');
                        } else {
                            try {
                                break;
                            } catch (Throwable th2) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                bufferedReader2.close();
                inputStreamReader.close();
            } catch (Throwable th4) {
                Throwable th5 = th4;
                bufferedReader2 = null;
                th = th5;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable th6) {
                        throw th;
                    }
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                throw th;
            }
        } catch (Throwable th7) {
            bufferedReader2 = null;
            th = th7;
            inputStreamReader = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            throw th;
        }
        readLine = stringBuilder.toString();
        try {
            jSONObject = new JSONObject(readLine);
            return readLine;
        } catch (JSONException e) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("string_response", readLine);
                return jSONObject.toString();
            } catch (JSONException e2) {
                return new JSONObject().toString();
            }
        }
    }

    public static String m12624a(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        Exception e;
        Throwable th;
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
                return byteArrayOutputStream.toString("ISO-8859-1");
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

    public static boolean m12625a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            boolean z;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    z = true;
                    return z;
                }
            }
            z = false;
            return z;
        } catch (NoSuchFieldError e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean m12626a(Context context, String str) {
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

    public static boolean m12627a(String str) {
        return str.startsWith("https://");
    }

    private static long m12628b(Context context, String str) {
        long j = 0;
        try {
            if (C4090d.m12626a(context, str)) {
                j = context.getApplicationContext().getPackageManager().getPackageInfo(str, 0).firstInstallTime;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return j;
    }

    public static String m12629b() {
        Locale locale = Locale.getDefault();
        return String.format("%s_%s", new Object[]{locale.getLanguage().toLowerCase(), locale.getCountry().toLowerCase()});
    }

    public static String m12630b(Context context) {
        return C4078f.m12569b(context);
    }

    public static String m12631b(String str) {
        return str.startsWith("https://") ? "http" + str.substring(5) : str;
    }

    private static String m12632b(byte[] bArr) {
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m12633c() {
        /*
        r0 = "/proc/meminfo";
        r2 = 0;
        r1 = 0;
        r5 = new java.io.FileReader;	 Catch:{ Exception -> 0x0038, all -> 0x004c }
        r5.<init>(r0);	 Catch:{ Exception -> 0x0038, all -> 0x004c }
        r4 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0038, all -> 0x004c }
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4.<init>(r5, r0);	 Catch:{ Exception -> 0x0038, all -> 0x004c }
        r0 = r4.readLine();	 Catch:{ Exception -> 0x005e, all -> 0x0059 }
        r1 = "\\s+";
        r0 = r0.split(r1);	 Catch:{ Exception -> 0x005e, all -> 0x0059 }
        r1 = 1;
        r0 = r0[r1];	 Catch:{ Exception -> 0x005e, all -> 0x0059 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x005e, all -> 0x0059 }
        r0 = r0.intValue();	 Catch:{ Exception -> 0x005e, all -> 0x0059 }
        r0 = (long) r0;
        r4.close();	 Catch:{ Exception -> 0x0064, all -> 0x0059 }
        r4.close();	 Catch:{ IOException -> 0x0033 }
    L_0x002f:
        r2 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r0 = r0 / r2;
        return r0;
    L_0x0033:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002f;
    L_0x0038:
        r0 = move-exception;
        r6 = r0;
        r7 = r2;
        r2 = r6;
        r3 = r1;
        r0 = r7;
    L_0x003e:
        r2.printStackTrace();	 Catch:{ all -> 0x005b }
        if (r3 == 0) goto L_0x002f;
    L_0x0043:
        r3.close();	 Catch:{ IOException -> 0x0047 }
        goto L_0x002f;
    L_0x0047:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x002f;
    L_0x004c:
        r0 = move-exception;
        r4 = r1;
    L_0x004e:
        if (r4 == 0) goto L_0x0053;
    L_0x0050:
        r4.close();	 Catch:{ IOException -> 0x0054 }
    L_0x0053:
        throw r0;
    L_0x0054:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0053;
    L_0x0059:
        r0 = move-exception;
        goto L_0x004e;
    L_0x005b:
        r0 = move-exception;
        r4 = r3;
        goto L_0x004e;
    L_0x005e:
        r0 = move-exception;
        r6 = r0;
        r0 = r2;
        r2 = r6;
        r3 = r4;
        goto L_0x003e;
    L_0x0064:
        r2 = move-exception;
        r3 = r4;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.three.e.d.c():long");
    }

    public static String m12634c(Context context) {
        return C4078f.m12569b(context);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m12635c(java.lang.String r5) {
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
        r0 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x002e }
        r2 = "ISO-8859-1";
        r2 = r5.getBytes(r2);	 Catch:{ Exception -> 0x002e }
        r0.<init>(r2);	 Catch:{ Exception -> 0x002e }
        r2 = new java.util.zip.GZIPInputStream;	 Catch:{ Exception -> 0x002e }
        r2.<init>(r0);	 Catch:{ Exception -> 0x002e }
        r0 = 256; // 0x100 float:3.59E-43 double:1.265E-321;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x002e }
    L_0x0023:
        r3 = r2.read(r0);	 Catch:{ Exception -> 0x002e }
        if (r3 < 0) goto L_0x003a;
    L_0x0029:
        r4 = 0;
        r1.write(r0, r4, r3);	 Catch:{ Exception -> 0x002e }
        goto L_0x0023;
    L_0x002e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0040 }
        r1.close();	 Catch:{ Exception -> 0x0045 }
    L_0x0035:
        r5 = r1.toString();
        goto L_0x0008;
    L_0x003a:
        r1.close();	 Catch:{ Exception -> 0x003e }
        goto L_0x0035;
    L_0x003e:
        r0 = move-exception;
        goto L_0x0035;
    L_0x0040:
        r0 = move-exception;
        r1.close();	 Catch:{ Exception -> 0x0047 }
    L_0x0044:
        throw r0;
    L_0x0045:
        r0 = move-exception;
        goto L_0x0035;
    L_0x0047:
        r1 = move-exception;
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.three.e.d.c(java.lang.String):java.lang.String");
    }

    public static String m12636d() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return (((((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String m12637d(Context context) {
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

    public static final String m12638d(String str) {
        return C4090d.m12622a(str, true, "UTF-8");
    }

    public static int m12639e() {
        try {
            return Runtime.getRuntime().availableProcessors();
        } catch (Throwable th) {
            th.printStackTrace();
            return 3;
        }
    }

    public static int m12640e(Context context) {
        if (f9472b < 0) {
            if (context == null) {
                f9472b = 0;
            } else {
                try {
                    f9472b = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (NameNotFoundException e) {
                    f9472b = 0;
                }
            }
        }
        return f9472b;
    }

    public static String m12641e(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            byte[] digest = instance.digest();
            for (int i : digest) {
                int i2;
                if (i2 < 0) {
                    i2 += 256;
                }
                if (i2 < 16) {
                    stringBuffer.append(SchemaSymbols.ATTVAL_FALSE_0);
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            str = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return str;
    }

    public static String m12642f() {
        if (TextUtils.isEmpty(f9475e)) {
            CharSequence f = C4090d.m12644f("ro.yunos.model");
            f9475e = f;
            if (TextUtils.isEmpty(f)) {
                f9475e = Build.MODEL;
            }
        }
        return f9475e;
    }

    public static String m12643f(Context context) {
        if (f9471a == null) {
            if (context == null) {
                f9471a = "0.0";
            } else {
                try {
                    f9471a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                } catch (NameNotFoundException e) {
                    f9471a = "0.0";
                }
            }
        }
        return f9471a;
    }

    private static String m12644f(String str) {
        try {
            return (String) Class.forName(C1404b.f2144v).getDeclaredMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, ""});
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

    public static String m12645g() {
        try {
            return TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception e) {
            return "";
        }
    }

    public static String m12646g(Context context) {
        return context.getApplicationInfo().packageName;
    }

    private static boolean m12647g(String str) {
        boolean z = false;
        try {
            z = new File(str).exists();
        } catch (Exception e) {
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m12648h(android.content.Context r3) {
        /*
        r0 = "connectivity";
        r0 = r3.getSystemService(r0);	 Catch:{ Exception -> 0x007d }
        r0 = (android.net.ConnectivityManager) r0;	 Catch:{ Exception -> 0x007d }
        if (r0 == 0) goto L_0x0026;
    L_0x000b:
        r0 = r0.getActiveNetworkInfo();	 Catch:{ Exception -> 0x007d }
        if (r0 == 0) goto L_0x0026;
    L_0x0011:
        r1 = r0.isAvailable();	 Catch:{ Exception -> 0x007d }
        if (r1 == 0) goto L_0x0026;
    L_0x0017:
        r1 = r0.getState();	 Catch:{ Exception -> 0x007d }
        r2 = android.net.NetworkInfo.State.CONNECTED;	 Catch:{ Exception -> 0x007d }
        if (r1 != r2) goto L_0x0026;
    L_0x001f:
        r1 = r0.getType();	 Catch:{ Exception -> 0x007d }
        switch(r1) {
            case 0: goto L_0x002e;
            case 1: goto L_0x002a;
            case 2: goto L_0x0035;
            case 3: goto L_0x0035;
            case 4: goto L_0x0035;
            case 5: goto L_0x0035;
            case 6: goto L_0x0035;
            default: goto L_0x0026;
        };
    L_0x0026:
        r0 = "unknown";
    L_0x0029:
        return r0;
    L_0x002a:
        r0 = "WIFI";
        goto L_0x0029;
    L_0x002e:
        r0 = r0.getSubtype();	 Catch:{ Exception -> 0x007d }
        switch(r0) {
            case 0: goto L_0x0079;
            case 1: goto L_0x0039;
            case 2: goto L_0x003d;
            case 3: goto L_0x004d;
            case 4: goto L_0x0041;
            case 5: goto L_0x0051;
            case 6: goto L_0x0055;
            case 7: goto L_0x0045;
            case 8: goto L_0x0059;
            case 9: goto L_0x005d;
            case 10: goto L_0x0061;
            case 11: goto L_0x0049;
            case 12: goto L_0x0065;
            case 13: goto L_0x0071;
            case 14: goto L_0x0069;
            case 15: goto L_0x006d;
            case 16: goto L_0x0075;
            default: goto L_0x0035;
        };
    L_0x0035:
        r0 = "unknown";
        goto L_0x0029;
    L_0x0039:
        r0 = "GPRS";
        goto L_0x0029;
    L_0x003d:
        r0 = "EDGE";
        goto L_0x0029;
    L_0x0041:
        r0 = "CDMA";
        goto L_0x0029;
    L_0x0045:
        r0 = "1xRTT";
        goto L_0x0029;
    L_0x0049:
        r0 = "IDEN";
        goto L_0x0029;
    L_0x004d:
        r0 = "UMTS";
        goto L_0x0029;
    L_0x0051:
        r0 = "EVDO0";
        goto L_0x0029;
    L_0x0055:
        r0 = "EVDOA";
        goto L_0x0029;
    L_0x0059:
        r0 = "HSUPA";
        goto L_0x0029;
    L_0x005d:
        r0 = "HSUPA";
        goto L_0x0029;
    L_0x0061:
        r0 = "HSPA";
        goto L_0x0029;
    L_0x0065:
        r0 = "EVDOB";
        goto L_0x0029;
    L_0x0069:
        r0 = "EHRPD";
        goto L_0x0029;
    L_0x006d:
        r0 = "HSPAP";
        goto L_0x0029;
    L_0x0071:
        r0 = "LTE";
        goto L_0x0029;
    L_0x0075:
        r0 = "GSM";
        goto L_0x0029;
    L_0x0079:
        r0 = "unknown";
        goto L_0x0029;
    L_0x007d:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = "unknown";
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unit.three.e.d.h(android.content.Context):java.lang.String");
    }

    public static boolean m12649h() {
        String str = "su";
        List arrayList = new ArrayList();
        for (String str2 : Arrays.asList(System.getenv("PATH").split(":"))) {
            String str22;
            if (!str22.endsWith(BridgeUtil.SPLIT_MARK)) {
                str22 = str22 + BridgeUtil.SPLIT_MARK;
            }
            if (C4090d.m12647g(str22 + str)) {
                arrayList.add(str22);
            }
        }
        return arrayList.size() > 0;
    }

    public static String m12650i(Context context) {
        if (context == null) {
            return "";
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        int width = windowManager.getDefaultDisplay().getWidth();
        return width + "x" + windowManager.getDefaultDisplay().getHeight();
    }

    public static int m12651j(Context context) {
        return ((context.getResources().getConfiguration().screenLayout & 15) >= 3 ? 1 : 0) != 0 ? 1 : 0;
    }

    public static String m12652k(Context context) {
        if (!f9474d) {
            if (context != null) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    CharSequence networkOperator = telephonyManager.getNetworkOperator();
                    f9473c = networkOperator;
                    if (TextUtils.isEmpty(networkOperator)) {
                        f9473c = "";
                    }
                }
            }
            f9474d = true;
        }
        return f9473c;
    }

    public static long m12653l(Context context) {
        if (context == null) {
            return 0;
        }
        Object packageName = context.getPackageName();
        if (TextUtils.isEmpty(packageName) || context == null) {
            return 0;
        }
        long b = C4090d.m12628b(context, packageName);
        return b >= 1 ? ((System.currentTimeMillis() - b) / 86400000) + 1 : 0;
    }

    public static String m12654m(Context context) {
        if (!TextUtils.isEmpty(f9476f)) {
            return f9476f;
        }
        synchronized (C4090d.class) {
            String str;
            if (!TextUtils.isEmpty(f9476f)) {
                str = f9476f;
                return str;
            } else if (context == null) {
                str = "";
                return str;
            } else {
                try {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    File file = new File(context.getApplicationInfo().sourceDir);
                    InputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[4096];
                    long length = file.length();
                    long j = 0;
                    while (j < length && !Thread.interrupted()) {
                        int read = fileInputStream.read(bArr);
                        j += (long) read;
                        instance.update(bArr, 0, read);
                    }
                    str = C4090d.m12632b(instance.digest());
                    if (str != null) {
                        str = str.toUpperCase();
                        f9476f = str;
                        return str;
                    }
                    str = "";
                    return str;
                } catch (Exception e) {
                    return "";
                }
            }
        }
    }
}
