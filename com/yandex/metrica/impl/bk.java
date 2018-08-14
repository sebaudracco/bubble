package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import java.io.Closeable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class bk {
    public static String m14973a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            return "0.0";
        }
    }

    public static int m14990b(Context context, String str) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    public static String m14975a(String str, Throwable th) {
        String a = m14976a(th);
        if (TextUtils.isEmpty(str)) {
            return a;
        }
        return str + ":\n" + a;
    }

    static String m14976a(Throwable th) {
        String str = "";
        if (th == null) {
            return str;
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        str = stringWriter.toString();
        printWriter.close();
        return str;
    }

    public static boolean m14985a(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean m14991b(int i) {
        return VERSION.SDK_INT > i;
    }

    public static void m14980a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static void m14984a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    public static void m14978a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public static void m14981a(Object obj, String str) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null.", new Object[]{str, str}));
        }
    }

    public static void m14983a(String str, String str2) throws IllegalArgumentException {
        if (bi.m14957a(str)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null/empty.", new Object[]{str2, str2}));
        }
    }

    public static boolean m14986a(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static void m14982a(String str) {
        m14983a(str, "API Key");
        try {
            UUID.fromString(str);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s = %s. Please, read official documentation how to obtain one: %s", new Object[]{"API Key", str, "https://tech.yandex.com/metrica-mobile-sdk/doc/mobile-sdk-dg/concepts/android-initialize-docpage/"}));
        }
    }

    public static List<ResolveInfo> m14977a(Context context, String str, String str2) {
        List<ResolveInfo> arrayList = new ArrayList();
        try {
            Intent intent = new Intent(str, null);
            intent.addCategory(str2);
            arrayList = context.getPackageManager().queryIntentActivities(intent, 0);
        } catch (Exception e) {
        }
        return arrayList;
    }

    public static PackageInfo m14993c(Context context, String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception e) {
        }
        return packageInfo;
    }

    public static String m14974a(PackageManager packageManager, String str, String str2, String str3) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            Object obj = bundle != null ? bundle.get(str2) : null;
            if (obj != null) {
                str3 = obj.toString();
            }
        } catch (Exception e) {
        }
        return str3;
    }

    public static void m14979a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Exception e) {
            }
        }
    }

    public static boolean m14988a(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean m14987a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static String m14972a(Context context, CounterConfiguration counterConfiguration, String str) {
        return counterConfiguration.m14236D() ? counterConfiguration.m14277j() : m14974a(context.getPackageManager(), str, "metrica:api:key", null);
    }

    public static boolean m14992b(String str) {
        return (bi.m14957a(str) || "-1".equals(str)) ? false : true;
    }

    public static String[] m14989a(long[] jArr) {
        String[] strArr = new String[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            strArr[i] = String.valueOf(jArr[i]);
        }
        return strArr;
    }
}
