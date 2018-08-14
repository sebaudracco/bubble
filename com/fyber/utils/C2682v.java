package com.fyber.utils;

/* compiled from: UrlSchemeHelper */
public abstract class C2682v {
    public static boolean m8582a(String str) {
        return StringUtils.notNullNorEmpty(str) && (str.startsWith("http://") || str.startsWith("https://"));
    }
}
