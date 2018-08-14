package com.facebook.ads.internal.p061c;

import java.lang.reflect.Method;

public class C1954d {
    public static Object m6159a(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static Method m6160a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method m6161a(String str, String str2, Class<?>... clsArr) {
        try {
            return C1954d.m6160a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
