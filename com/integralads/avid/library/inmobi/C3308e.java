package com.integralads.avid.library.inmobi;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.integralads.avid.library.inmobi.p125e.C3307a;
import com.integralads.avid.library.inmobi.session.internal.C3333a;

/* compiled from: AvidStateWatcher */
public class C3308e {
    private static C3308e f8446a = new C3308e();
    private Context f8447b;
    private BroadcastReceiver f8448c;
    private boolean f8449d;
    private boolean f8450e;
    private C3302a f8451f;

    /* compiled from: AvidStateWatcher */
    public interface C3302a {
        void mo6328a(boolean z);
    }

    /* compiled from: AvidStateWatcher */
    class C33051 extends BroadcastReceiver {
        final /* synthetic */ C3308e f8440a;

        C33051(C3308e c3308e) {
            this.f8440a = c3308e;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    this.f8440a.m11268a(true);
                } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                    this.f8440a.m11268a(false);
                } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                    KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
                    if (keyguardManager != null && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        this.f8440a.m11268a(false);
                    }
                }
            }
        }
    }

    public static C3308e m11266a() {
        return f8446a;
    }

    public void m11272a(Context context) {
        m11270f();
        this.f8447b = context;
        m11269e();
    }

    public void m11274b() {
        this.f8449d = true;
        m11271g();
    }

    public void m11275c() {
        m11270f();
        this.f8447b = null;
        this.f8449d = false;
        this.f8450e = false;
        this.f8451f = null;
    }

    public boolean m11276d() {
        return !this.f8450e;
    }

    public void m11273a(C3302a c3302a) {
        this.f8451f = c3302a;
    }

    private void m11268a(boolean z) {
        if (this.f8450e != z) {
            this.f8450e = z;
            if (this.f8449d) {
                m11271g();
                if (this.f8451f != null) {
                    this.f8451f.mo6328a(m11276d());
                }
            }
        }
    }

    private void m11269e() {
        this.f8448c = new C33051(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.f8447b.registerReceiver(this.f8448c, intentFilter);
    }

    private void m11270f() {
        if (this.f8447b != null && this.f8448c != null) {
            this.f8447b.unregisterReceiver(this.f8448c);
            this.f8448c = null;
        }
    }

    private void m11271g() {
        boolean z = !this.f8450e;
        for (C3333a a : C3307a.m11255a().m11261b()) {
            a.m11395a(z);
        }
    }
}
