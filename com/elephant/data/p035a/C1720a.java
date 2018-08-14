package com.elephant.data.p035a;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Looper;
import com.elephant.data.p044e.p045a.C1777a;
import com.elephant.data.p044e.p045a.C1778b;
import com.elephant.data.p048g.C1814b;
import java.io.IOException;

public class C1720a {
    private final String f3532a;

    private C1720a(String str, boolean z) {
        this.f3532a = str;
    }

    public static C1720a m4968a(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(C1814b.kt);
        }
        try {
            context.getPackageManager().getPackageInfo(C1814b.ku, 0);
            ServiceConnection c1721b = new C1721b();
            Intent intent = new Intent(C1814b.kv);
            intent.setPackage(C1814b.kw);
            if (context.bindService(intent, c1721b, 1)) {
                try {
                    C1722c c1722c = new C1722c(c1721b.m4972a());
                    C1720a c1720a = new C1720a(c1722c.m4973a(), c1722c.m4974a(true));
                    context.unbindService(c1721b);
                    return c1720a;
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    context.unbindService(c1721b);
                }
            } else {
                throw new IOException(C1814b.kx);
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    public static boolean m4969a(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static synchronized C1777a m4970b(Context context) {
        C1777a c1778b;
        synchronized (C1720a.class) {
            c1778b = new C1778b(context);
        }
        return c1778b;
    }

    public String m4971a() {
        return this.f3532a;
    }
}
