package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p056q.p076c.C2141a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p070r.C2154a;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import java.util.Map;

public class C1933s extends C1884b {
    private static final String f4453c = C1933s.class.getSimpleName();
    private final C2141a f4454d;
    private final C2012c f4455e;
    private C1931r f4456f;
    private boolean f4457g;

    class C19321 implements Runnable {
        final /* synthetic */ C1933s f4452a;

        C19321(C1933s c1933s) {
            this.f4452a = c1933s;
        }

        public void run() {
            if (this.f4452a.f4454d.m6856c()) {
                Log.w(C1933s.f4453c, "Webview already destroyed, cannot activate");
            } else {
                this.f4452a.f4454d.loadUrl(BridgeUtil.JAVASCRIPT_STR + this.f4452a.f4456f.m6049e());
            }
        }
    }

    public C1933s(Context context, C2012c c2012c, C2141a c2141a, C2154a c2154a, C1885c c1885c) {
        super(context, c1885c, c2154a);
        this.f4455e = c2012c;
        this.f4454d = c2141a;
    }

    public void m6058a(C1931r c1931r) {
        this.f4456f = c1931r;
    }

    protected void mo3624a(Map<String, String> map) {
        if (this.f4456f != null && !TextUtils.isEmpty(this.f4456f.mo3642c())) {
            this.f4455e.mo3709a(this.f4456f.mo3642c(), map);
        }
    }

    public synchronized void m6060b() {
        if (!(this.f4457g || this.f4456f == null)) {
            this.f4457g = true;
            if (!(this.f4454d == null || TextUtils.isEmpty(this.f4456f.m6049e()))) {
                this.f4454d.post(new C19321(this));
            }
        }
    }
}
