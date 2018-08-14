package com.integralads.avid.library.adcolony;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;

public class AvidStateWatcher {
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private static AvidStateWatcher f8309a = new AvidStateWatcher();
    private Context f8310b;
    private BroadcastReceiver f8311c;
    private boolean f8312d;
    private boolean f8313e;
    private AvidStateWatcherListener f8314f;

    public interface AvidStateWatcherListener {
        void onAppStateChanged(boolean z);
    }

    class C32821 extends BroadcastReceiver {
        final /* synthetic */ AvidStateWatcher f8308a;

        C32821(AvidStateWatcher avidStateWatcher) {
            this.f8308a = avidStateWatcher;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    this.f8308a.m11108b(true);
                } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                    this.f8308a.m11108b(false);
                } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                    KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
                    if (keyguardManager != null && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        this.f8308a.m11108b(false);
                    }
                }
            }
        }
    }

    public static AvidStateWatcher getInstance() {
        return f8309a;
    }

    public void init(Context context) {
        m11109c();
        this.f8310b = context;
        m11107b();
    }

    public void start() {
        this.f8312d = true;
        m11110d();
    }

    public void stop() {
        m11109c();
        this.f8310b = null;
        this.f8312d = false;
        this.f8313e = false;
        this.f8314f = null;
    }

    public boolean isActive() {
        return !this.f8313e;
    }

    public AvidStateWatcherListener getStateWatcherListener() {
        return this.f8314f;
    }

    public void setStateWatcherListener(AvidStateWatcherListener stateWatcherListener) {
        this.f8314f = stateWatcherListener;
    }

    private void m11108b(boolean z) {
        if (this.f8313e != z) {
            this.f8313e = z;
            if (this.f8312d) {
                m11110d();
                if (this.f8314f != null) {
                    this.f8314f.onAppStateChanged(isActive());
                }
            }
        }
    }

    private void m11107b() {
        this.f8311c = new C32821(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.f8310b.registerReceiver(this.f8311c, intentFilter);
    }

    private void m11109c() {
        if (this.f8310b != null && this.f8311c != null) {
            this.f8310b.unregisterReceiver(this.f8311c);
            this.f8311c = null;
        }
    }

    private void m11110d() {
        boolean z = !this.f8313e;
        for (InternalAvidAdSession screenMode : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            screenMode.setScreenMode(z);
        }
    }

    @VisibleForTesting
    BroadcastReceiver m11111a() {
        return this.f8311c;
    }

    @VisibleForTesting
    void m11112a(boolean z) {
        this.f8313e = z;
    }
}
