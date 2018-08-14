package com.fyber.p094b;

import android.support.annotation.NonNull;
import com.fyber.Fyber;
import com.fyber.utils.C2657h;
import com.fyber.utils.FyberLogger;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* compiled from: RemoteFileOperation */
public final class C2508i implements Callable<String> {
    private String f6231a;

    public final /* synthetic */ Object call() throws Exception {
        return m7985a();
    }

    public static Future<String> m7986a(@NonNull String str) {
        if (Fyber.getConfigs().m7607h()) {
            return Fyber.getConfigs().m7599a(new C2508i(str));
        }
        return null;
    }

    private C2508i(@NonNull String str) {
        this.f6231a = str;
    }

    private String m7985a() throws Exception {
        try {
            return (String) ((C2657h) C2657h.m8506b(this.f6231a).mo4005a()).m8466c();
        } catch (Exception e) {
            FyberLogger.m8450e("RemoteFileOperation", e.getMessage(), e);
            return null;
        }
    }
}
