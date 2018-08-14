package com.oneaudience.sdk.p135c;

public class C3837h {
    public static Object m12269a() {
        Object obj = null;
        C3833d.m12256e("TWITTER_UTILS", "twitter getSessionUserId");
        try {
            Class cls = Class.forName("com.twitter.sdk.android.core.TwitterCore");
            Class cls2 = Class.forName("com.twitter.sdk.android.core.OAuthSigning");
            if (!(cls == null || cls2 == null)) {
                Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                Object invoke2 = invoke.getClass().getMethod("getSessionManager", new Class[0]).invoke(invoke, new Object[0]);
                invoke2 = invoke2.getClass().getMethod("getActiveSession", new Class[0]).invoke(invoke2, new Object[0]);
                invoke = invoke.getClass().getMethod("getApiClient", new Class[]{invoke2.getClass()}).invoke(invoke, new Object[]{invoke2});
                invoke = invoke.getClass().getMethod("getAccountService", new Class[0]).invoke(invoke, new Object[0]);
                invoke = invoke.getClass().getMethod("verifyCredentials", new Class[]{Boolean.class, Boolean.class, Boolean.class}).invoke(invoke, new Object[]{Boolean.valueOf(true), Boolean.valueOf(false), Boolean.valueOf(true)});
                invoke = invoke.getClass().getMethod("execute", new Class[0]).invoke(invoke, new Object[0]);
                obj = invoke.getClass().getMethod("body", new Class[0]).invoke(invoke, new Object[0]);
            }
        } catch (Throwable e) {
            C3833d.m12250b("TWITTER_UTILS", "Can't get Twitter Access Token", e);
        }
        return obj;
    }
}
