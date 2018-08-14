package com.vungle.publisher;

import com.bgjd.ici.p025b.C1408j.C1404b;
import com.vungle.publisher.log.Logger;

/* compiled from: vungle */
public class yy {
    public static String m14182a(String str) {
        try {
            return (String) Class.forName(C1404b.f2144v).getDeclaredMethod("get", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Throwable e) {
            Logger.m13648w(Logger.CONFIG_TAG, "error getting Android system property " + str, e);
            return null;
        }
    }
}
