package com.fyber.p094b;

import android.support.annotation.NonNull;
import com.fyber.utils.C2657h;
import com.fyber.utils.C2672t;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: NetworkOperation */
public abstract class C2478g<V> implements Runnable, Callable<V> {
    private String f6210a;
    private Map<String, String> f6211b;
    protected boolean f6212c = true;
    protected C2672t f6213d;

    protected abstract V mo3899a(C2657h c2657h) throws Exception;

    protected abstract V mo3901b(IOException iOException);

    protected abstract String b_();

    protected C2478g(@NonNull C2672t c2672t) {
        this.f6213d = c2672t;
    }

    protected C2478g(@NonNull String str, Map<String, String> map) {
        this.f6210a = str;
        this.f6211b = map;
    }

    public void run() {
        try {
            m7869c();
        } catch (Exception e) {
            FyberLogger.m8450e(b_(), "An error occurred", e);
            mo3901b(e);
        } catch (Exception e2) {
            FyberLogger.m8450e(b_(), "An error occurred", e2);
        }
    }

    public V call() throws Exception {
        if (!this.f6212c) {
            return m7869c();
        }
        try {
            return m7869c();
        } catch (Exception e) {
            FyberLogger.m8450e(b_(), "An error occurred", e);
            return mo3901b(e);
        }
    }

    private V m7869c() throws Exception {
        if (!a_()) {
            return null;
        }
        if (StringUtils.nullOrEmpty(this.f6210a)) {
            this.f6210a = this.f6213d.m8547e();
        }
        FyberLogger.m8448d(b_(), "sending request to " + this.f6210a);
        return mo3899a((C2657h) ((C2657h) C2657h.m8506b(this.f6210a).m8461a(this.f6211b)).mo4005a());
    }

    protected boolean a_() {
        return true;
    }
}
