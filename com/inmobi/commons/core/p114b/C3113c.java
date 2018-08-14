package com.inmobi.commons.core.p114b;

import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.lang.Thread.UncaughtExceptionHandler;

/* compiled from: InMobiCrashHandler */
public class C3113c implements UncaughtExceptionHandler {
    private static final String f7617b = C3113c.class.getSimpleName();
    private static boolean f7618c = false;
    private static C3112a f7619d;
    private UncaughtExceptionHandler f7620a;

    /* compiled from: InMobiCrashHandler */
    static class C3112a implements C2911b {
        C3112a() {
        }

        public void mo6102a(C3045a c3045a) {
            C3135c.m10255a().m10281a(c3045a.mo6231a(), ((C3109a) c3045a).m10118e());
        }
    }

    private C3113c(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f7620a = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        if (m10130a(th)) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7617b, "Crash due to inmobi, will report it");
            try {
                C3135c.m10255a().m10279a(new C3111b(thread, th));
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7617b, "Error in submitting telemetry event : (" + th.getMessage() + ")");
            }
        }
        this.f7620a.uncaughtException(thread, th);
    }

    private boolean m10130a(Throwable th) {
        for (StackTraceElement className : th.getStackTrace()) {
            if (className.getClassName().contains("com.inmobi.")) {
                return true;
            }
        }
        return false;
    }

    public static synchronized void m10129a() {
        synchronized (C3113c.class) {
            if (!f7618c) {
                C3045a c3109a = new C3109a();
                f7619d = new C3112a();
                C3121b.m10178a().m10190a(c3109a, f7619d);
                C3135c.m10255a().m10281a(c3109a.mo6231a(), c3109a.m10118e());
                Thread.setDefaultUncaughtExceptionHandler(new C3113c(Thread.getDefaultUncaughtExceptionHandler()));
                f7618c = true;
            }
        }
    }
}
