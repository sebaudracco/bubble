package com.inmobi.commons.core.utilities;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

/* compiled from: NetworkUtils */
public class C3155d {
    private static final String f7781a = C3155d.class.getSimpleName();

    public static boolean m10406a() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C3105a.m10078b().getSystemService("connectivity")).getActiveNetworkInfo();
            return (activeNetworkInfo == null || !activeNetworkInfo.isConnected() || C3155d.m10409b()) ? false : true;
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7781a, "SDK encountered unexpected error in checking network availability; " + e.getMessage());
            return false;
        }
    }

    private static boolean m10409b() {
        try {
            PowerManager powerManager = (PowerManager) C3105a.m10078b().getSystemService("power");
            if (VERSION.SDK_INT > 22) {
                return powerManager.isDeviceIdleMode();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7781a, "SDK encountered unexpected error in checking idle mode; " + e.getMessage());
        }
        return false;
    }

    public static String m10403a(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(str);
            }
            stringBuilder.append(String.format(Locale.US, "%s=%s", new Object[]{C3155d.m10401a((String) entry.getKey()), C3155d.m10401a((String) entry.getValue())}));
        }
        return stringBuilder.toString();
    }

    public static String m10401a(String str) {
        String str2 = "";
        try {
            str2 = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return str2;
    }

    public static void m10405a(Map<String, String> map) {
        if (map != null) {
            Iterator it = map.entrySet().iterator();
            Map hashMap = new HashMap();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.getValue() == null || ((String) entry.getValue()).trim().length() == 0 || entry.getKey() == null || ((String) entry.getKey()).trim().length() == 0) {
                    it.remove();
                } else if (((String) entry.getKey()).equals(((String) entry.getKey()).trim())) {
                    hashMap.put(entry.getKey(), ((String) entry.getValue()).trim());
                } else {
                    it.remove();
                    hashMap.put(((String) entry.getKey()).trim(), ((String) entry.getValue()).trim());
                }
            }
            map.putAll(hashMap);
        }
    }

    public static String m10402a(String str, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Entry entry : map.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public static byte[] m10408a(@NonNull byte[] bArr) {
        Closeable gZIPInputStream;
        Throwable e;
        Throwable th;
        byte[] bArr2 = null;
        Closeable byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            try {
                bArr2 = C3155d.m10407a((InputStream) gZIPInputStream);
                C3155d.m10404a(byteArrayInputStream);
                C3155d.m10404a(gZIPInputStream);
            } catch (IOException e2) {
                e = e2;
                try {
                    Logger.m10360a(InternalLogLevel.DEBUG, f7781a, "Failed to decompress response", e);
                    C3155d.m10404a(byteArrayInputStream);
                    C3155d.m10404a(gZIPInputStream);
                    return bArr2;
                } catch (Throwable th2) {
                    th = th2;
                    C3155d.m10404a(byteArrayInputStream);
                    C3155d.m10404a(gZIPInputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            gZIPInputStream = bArr2;
            Logger.m10360a(InternalLogLevel.DEBUG, f7781a, "Failed to decompress response", e);
            C3155d.m10404a(byteArrayInputStream);
            C3155d.m10404a(gZIPInputStream);
            return bArr2;
        } catch (Throwable e4) {
            gZIPInputStream = bArr2;
            th = e4;
            C3155d.m10404a(byteArrayInputStream);
            C3155d.m10404a(gZIPInputStream);
            throw th;
        }
        return bArr2;
    }

    public static byte[] m10407a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } finally {
                byteArrayOutputStream.close();
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        return bArr;
    }

    public static void m10404a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Logger.m10360a(InternalLogLevel.INTERNAL, f7781a, "Failed to close closable", e);
            }
        }
    }
}
