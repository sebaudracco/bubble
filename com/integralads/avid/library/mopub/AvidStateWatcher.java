package com.integralads.avid.library.mopub;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;

public class AvidStateWatcher {
    public static final String CONTEXT_AVID_AD_SESSION_ID = "avidAdSessionId";
    public static final String CONTEXT_AVID_LIBRARY_VERSION = "avidLibraryVersion";
    public static final String CONTEXT_BUNDLE_IDENTIFIER = "bundleIdentifier";
    public static final String CONTEXT_PARTNER = "partner";
    public static final String CONTEXT_PARTNER_VERSION = "partnerVersion";
    private static AvidStateWatcher avidStateWatcher = new AvidStateWatcher();
    private Context context;
    private boolean isScreenOff;
    private boolean isStarted;
    private BroadcastReceiver receiver;
    private AvidStateWatcherListener stateWatcherListener;

    public interface AvidStateWatcherListener {
        void onAppStateChanged(boolean z);
    }

    class C33421 extends BroadcastReceiver {
        C33421() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    AvidStateWatcher.this.onScreenModeChanged(true);
                } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                    AvidStateWatcher.this.onScreenModeChanged(false);
                } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                    KeyguardManager km = (KeyguardManager) context.getSystemService("keyguard");
                    if (km != null && !km.inKeyguardRestrictedInputMode()) {
                        AvidStateWatcher.this.onScreenModeChanged(false);
                    }
                }
            }
        }
    }

    public static AvidStateWatcher getInstance() {
        return avidStateWatcher;
    }

    public void init(Context context) {
        unregisterReceiver();
        this.context = context;
        registerReceiver();
    }

    public void start() {
        this.isStarted = true;
        notifyScreenModeChanged();
    }

    public void stop() {
        unregisterReceiver();
        this.context = null;
        this.isStarted = false;
        this.isScreenOff = false;
        this.stateWatcherListener = null;
    }

    public boolean isActive() {
        return !this.isScreenOff;
    }

    public AvidStateWatcherListener getStateWatcherListener() {
        return this.stateWatcherListener;
    }

    public void setStateWatcherListener(AvidStateWatcherListener stateWatcherListener) {
        this.stateWatcherListener = stateWatcherListener;
    }

    private void onScreenModeChanged(boolean isScreenOff) {
        if (this.isScreenOff != isScreenOff) {
            this.isScreenOff = isScreenOff;
            if (this.isStarted) {
                notifyScreenModeChanged();
                if (this.stateWatcherListener != null) {
                    this.stateWatcherListener.onAppStateChanged(isActive());
                }
            }
        }
    }

    private void registerReceiver() {
        this.receiver = new C33421();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.SCREEN_ON");
        filter.addAction("android.intent.action.USER_PRESENT");
        this.context.registerReceiver(this.receiver, filter);
    }

    private void unregisterReceiver() {
        if (this.context != null && this.receiver != null) {
            this.context.unregisterReceiver(this.receiver);
            this.receiver = null;
        }
    }

    private void notifyScreenModeChanged() {
        boolean isScreenOn = !this.isScreenOff;
        for (InternalAvidAdSession internalAvidAdSession : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            internalAvidAdSession.setScreenMode(isScreenOn);
        }
    }

    @VisibleForTesting
    BroadcastReceiver getReceiver() {
        return this.receiver;
    }

    @VisibleForTesting
    void setScreenOff(boolean isScreenOff) {
        this.isScreenOff = isScreenOff;
    }
}
