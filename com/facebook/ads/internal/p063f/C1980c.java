package com.facebook.ads.internal.p063f;

import android.content.Context;
import android.os.Process;
import android.support.annotation.Nullable;
import com.facebook.ads.BuildConfig;
import com.facebook.ads.internal.p056q.p057a.C2122m;
import com.facebook.ads.internal.p056q.p057a.C2124p;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Map;

public class C1980c implements UncaughtExceptionHandler {
    private final UncaughtExceptionHandler f4623a;
    private final Context f4624b;
    private final Map<String, String> f4625c;

    public C1980c(@Nullable UncaughtExceptionHandler uncaughtExceptionHandler, Context context, Map<String, String> map) {
        this.f4623a = uncaughtExceptionHandler;
        if (context == null) {
            throw new IllegalArgumentException("Missing Context");
        }
        this.f4624b = context.getApplicationContext();
        this.f4625c = map;
    }

    private void m6244a(Thread thread, Throwable th) {
        if (this.f4623a != null) {
            this.f4623a.uncaughtException(thread, th);
            return;
        }
        try {
            Process.killProcess(Process.myPid());
        } catch (Throwable th2) {
        }
        try {
            System.exit(10);
        } catch (Throwable th3) {
        }
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String a = C2124p.m6810a(th);
            if (a != null && a.contains(BuildConfig.APPLICATION_ID)) {
                C1981e.m6249a(new C1977d(C2122m.m6805b(), C2122m.m6806c(), new C1979b(a, this.f4625c).m6243a()), this.f4624b);
            }
        } catch (Exception e) {
        }
        m6244a(thread, th);
    }
}
