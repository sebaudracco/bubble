package com.unit.two.p147c;

import android.content.Context;
import android.os.Looper;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.unit.two.p145a.C4092a;

public class C4112o {
    private static C4112o f9578a = null;
    private WebView f9579b;
    private String f9580c;
    private volatile int f9581d = 50;
    private volatile int f9582e = 0;

    static {
        String str = C4096a.dU;
    }

    private C4112o(Context context) {
        if (C4112o.m12701a()) {
            try {
                this.f9579b = new WebView(context);
                if (this.f9579b != null) {
                    WebSettings settings = this.f9579b.getSettings();
                    settings.setJavaScriptEnabled(true);
                    settings.setJavaScriptCanOpenWindowsAutomatically(true);
                    settings.setBlockNetworkImage(false);
                    this.f9579b.requestFocus();
                    this.f9579b.addJavascriptInterface(new C4115r(), C4096a.dV);
                    this.f9579b.setWebViewClient(new C4113p(this));
                }
            } catch (Throwable th) {
            }
        }
    }

    private static C4112o m12698a(Context context) {
        if (f9578a == null) {
            synchronized (C4112o.class) {
                if (f9578a == null) {
                    f9578a = new C4112o(context);
                }
            }
        }
        return f9578a;
    }

    public static void m12699a(Context context, C4092a c4092a, boolean z) {
        int c = c4092a.m12658c();
        C4112o a = C4112o.m12698a(context);
        a.f9580c = c4092a.m12656a().f9664e;
        if (c > 0) {
            a.f9581d = c;
        }
        c4092a.m12660e();
        String str = c4092a.m12656a().f9663d;
        try {
            c4092a.m12656a();
            int d = c4092a.m12659d();
            C4116s c4116s = new C4116s(context, str, c);
            if (a.f9579b != null && C4112o.m12701a()) {
                try {
                    a.f9579b.loadUrl(C4096a.dZ);
                    a.f9579b.clearView();
                    a.f9582e = 0;
                } catch (Throwable th) {
                }
                if (a.f9580c != null) {
                    if (str == null || !str.startsWith(C4096a.dW)) {
                        a.f9579b.loadDataWithBaseURL(null, str, C4096a.dX, C4096a.dY, null);
                    } else if (str.contains(C4104i.f9565c)) {
                        a.f9579b.loadUrl(str);
                    }
                    if (z) {
                        a.f9579b.postDelayed(new C4114q(a), (long) (d * 1000));
                    }
                }
            }
        } catch (Throwable th2) {
        }
    }

    private static boolean m12701a() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    static /* synthetic */ void m12705e(C4112o c4112o) {
        try {
            c4112o.f9579b.loadUrl(C4096a.ea);
            c4112o.f9579b.clearView();
        } catch (Throwable th) {
        }
    }
}
