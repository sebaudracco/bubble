package com.bgjd.ici.p025b;

import android.util.Log;

public final class C1402i {
    private static String f2058a = "MKT";

    public static void m2899a(String str, String... strArr) {
        try {
            Log.d(f2058a, C1402i.m2902c(str, strArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m2901b(String str, String... strArr) {
        try {
            Log.i(f2058a, C1402i.m2902c(str, strArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m2898a(String str, Throwable th, String... strArr) {
        try {
            Log.e(f2058a, C1402i.m2900b(str, th, strArr) + "\n" + th.getMessage());
            th.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String m2902c(String str, String... strArr) {
        return C1402i.m2900b(str, null, strArr);
    }

    private static String m2900b(String str, Throwable th, String... strArr) {
        int i;
        if (strArr == null) {
            i = 1;
        } else {
            i = strArr.length + 1;
        }
        StringBuilder stringBuilder = new StringBuilder(i);
        if (str != null) {
            stringBuilder.append("(").append(str).append(")");
        }
        stringBuilder.append("[");
        if (strArr == null || strArr.length == 0) {
            stringBuilder.append("null log message");
        } else if (strArr.length == 1) {
            stringBuilder.append(strArr[0]);
        } else {
            int length = strArr.length;
            for (i = 0; i < length; i++) {
                if (i > 0) {
                    stringBuilder.append(" | ");
                }
                stringBuilder.append(strArr[i]);
            }
        }
        if (th != null) {
            stringBuilder.append("{{").append(th.getMessage()).append("}}");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
