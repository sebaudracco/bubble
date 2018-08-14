package com.fyber.reporters.p109a;

import android.util.Log;
import com.fyber.utils.FyberLogger;

/* compiled from: ReporterResult */
public abstract class C2582c {
    public abstract void mo3983a();

    protected abstract String mo3984b();

    public final void m8247a(int i) {
        String str = "Report was unsuccessful. Response code: " + i;
        if (FyberLogger.isLogging()) {
            FyberLogger.m8451i(mo3984b(), str);
        } else {
            Log.i(mo3984b(), str);
        }
    }
}
