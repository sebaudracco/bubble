package com.oneaudience.sdk.p135c;

import java.lang.reflect.Method;

public class C3823a {
    private static final String f9188a = C3823a.class.getSimpleName();

    public static String m12227a() {
        Class[] clsArr = new Class[0];
        try {
            Class cls = Class.forName("com.facebook.AccessToken");
            Method declaredMethod = cls != null ? cls.getDeclaredMethod("getCurrentAccessToken", clsArr) : null;
            Object invoke = declaredMethod != null ? declaredMethod.invoke(cls, null) : null;
            declaredMethod = invoke != null ? cls.getDeclaredMethod("getToken", clsArr) : null;
            return declaredMethod != null ? (String) declaredMethod.invoke(invoke, null) : null;
        } catch (Throwable e) {
            C3833d.m12250b(f9188a, "Can't get Facebook Access Token", e);
            return null;
        }
    }
}
