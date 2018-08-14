package com.fyber.p086a;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.requesters.RequestError;
import com.fyber.utils.StringUtils;

/* compiled from: Credentials */
public final class C2408a {
    public static C2408a f5990a = new C2408a("", "");
    private final String f5991b;
    private String f5992c;
    private final String f5993d;

    /* compiled from: Credentials */
    public static class C2407a {
        private String f5987a;
        private String f5988b;
        private String f5989c;

        public C2407a(@NonNull String str) {
            this.f5987a = StringUtils.trim(str);
        }

        public final C2407a m7586a(String str) {
            this.f5987a = StringUtils.trim(str);
            return this;
        }

        public final C2407a m7588b(String str) {
            this.f5988b = str;
            return this;
        }

        public final C2407a m7589c(String str) {
            this.f5989c = StringUtils.trim(str);
            return this;
        }

        public final C2408a m7587a() {
            return new C2408a();
        }
    }

    private C2408a(String str, String str2) {
        this.f5991b = str;
        this.f5992c = str2;
        this.f5993d = null;
    }

    private C2408a(C2407a c2407a) {
        this.f5991b = c2407a.f5987a;
        this.f5992c = c2407a.f5988b;
        this.f5993d = c2407a.f5989c;
    }

    public final String m7591a() {
        return this.f5991b;
    }

    public final String m7593b() {
        return this.f5992c;
    }

    public final void m7592a(@NonNull String str) {
        if (StringUtils.nullOrEmpty(str)) {
            throw new IllegalArgumentException("Invalid userId");
        } else if (Fyber.getConfigs().m7607h()) {
            this.f5992c = str;
        } else {
            throw new IllegalStateException(RequestError.SDK_NOT_STARTED.getDescription());
        }
    }

    public final String m7594c() {
        return this.f5993d;
    }

    public static boolean m7590b(String str) {
        return StringUtils.notNullNorEmpty(str) && str.length() > 16;
    }

    public final String toString() {
        String str = "AppId - %s\nUserId - %s\nSecurityToken - %s";
        Object[] objArr = new Object[3];
        objArr[0] = this.f5991b;
        objArr[1] = StringUtils.notNullNorEmpty(this.f5992c) ? this.f5992c : "N/A";
        objArr[2] = StringUtils.notNullNorEmpty(this.f5993d) ? this.f5993d : "N/A";
        return String.format(str, objArr);
    }
}
