package com.vungle.publisher;

import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import com.appnext.core.Ad;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

/* compiled from: vungle */
public final class zk {
    private static final char[] f11391a = "0123456789abcdef".toCharArray();

    public static boolean m14215a(String str) {
        return (str == null || str.trim().isEmpty()) ? false : true;
    }

    public static <T> String m14213a(T... tArr) {
        return m14212a(", ", (Object[]) tArr);
    }

    public static String m14210a(Iterable<?> iterable) {
        return m14211a(", ", (Iterable) iterable);
    }

    public static <T> String m14212a(String str, T... tArr) {
        return m14211a(str, tArr == null ? null : Arrays.asList(tArr));
    }

    public static String m14211a(String str, Iterable<?> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(str);
            }
            stringBuilder.append(next);
        }
        return stringBuilder.toString();
    }

    public static String m14209a(Enum<?> enumR) {
        return enumR == null ? null : enumR.name();
    }

    public static String m14217b(Object[] objArr) {
        return "[" + m14213a(objArr) + "]";
    }

    public static Uri m14216b(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("input text cannot be null");
        }
        try {
            return Uri.parse(str);
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid url parameter: " + str);
        }
    }

    public static Uri m14208a(String str, String str2) throws IllegalArgumentException {
        if (str == null) {
            return m14216b(str2);
        }
        return m14216b(str);
    }

    public static boolean m14218c(String str) throws IllegalArgumentException {
        if (SchemaSymbols.ATTVAL_TRUE.equals(str)) {
            return true;
        }
        if (SchemaSymbols.ATTVAL_FALSE.equals(str)) {
            return false;
        }
        throw new IllegalArgumentException("invalid boolean parameter: " + str);
    }

    public static String m14219d(String str) {
        if (str == null) {
            return str;
        }
        try {
            return new String(Base64.decode(str, 0));
        } catch (Exception e) {
            throw new IllegalArgumentException("error decoding base64 string: " + str);
        }
    }

    public static Set<String> m14214a(Uri uri) {
        Set<String> hashSet = new HashSet();
        try {
            if (VERSION.SDK_INT >= 11) {
                return uri.getQueryParameterNames();
            }
            String query = uri.getQuery();
            if (query == null) {
                return hashSet;
            }
            for (String split : query.split("&")) {
                hashSet.add(split.split("=")[0]);
            }
            return hashSet;
        } catch (Exception e) {
            throw new IllegalArgumentException("error getting query param names");
        }
    }

    public static ry m14220e(String str) throws IllegalArgumentException {
        if (Ad.ORIENTATION_PORTRAIT.equals(str)) {
            return ry.PORTRAIT;
        }
        if (Ad.ORIENTATION_LANDSCAPE.equals(str)) {
            return ry.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return ry.NONE;
        }
        throw new IllegalArgumentException("invalid orientation: " + str);
    }

    public static boolean m14221f(String str) {
        return str == null || str.length() <= 0;
    }
}
