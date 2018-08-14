package com.integralads.avid.library.adcolony;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.adcolony.AvidLoader.AvidLoaderListener;
import com.integralads.avid.library.adcolony.AvidStateWatcher.AvidStateWatcherListener;
import com.integralads.avid.library.adcolony.activity.AvidActivityStack;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistryListener;
import com.integralads.avid.library.adcolony.session.AbstractAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidJSONUtil;

public class AvidManager implements AvidLoaderListener, AvidStateWatcherListener, AvidAdSessionRegistryListener {
    private static AvidManager f8306a = new AvidManager();
    private static Context f8307b;

    public static AvidManager getInstance() {
        return f8306a;
    }

    public void init(Context context) {
        if (f8307b == null) {
            f8307b = context.getApplicationContext();
            AvidStateWatcher.getInstance().init(f8307b);
            AvidAdSessionRegistry.getInstance().setListener(this);
            AvidJSONUtil.init(f8307b);
        }
    }

    public void registerAvidAdSession(AbstractAvidAdSession avidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistry.getInstance().registerAvidAdSession(avidAdSession, internalAvidAdSession);
    }

    public AbstractAvidAdSession findAvidAdSessionById(String avidAdSessionId) {
        return AvidAdSessionRegistry.getInstance().findAvidAdSessionById(avidAdSessionId);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String avidAdSessionId) {
        return AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(avidAdSessionId);
    }

    public void registerActivity(Activity activity) {
        AvidActivityStack.getInstance().addActivity(activity);
    }

    private void m11101a() {
        AvidStateWatcher.getInstance().setStateWatcherListener(this);
        AvidStateWatcher.getInstance().start();
        if (AvidStateWatcher.getInstance().isActive()) {
            AvidTreeWalker.getInstance().start();
        }
    }

    private void m11103b() {
        AvidActivityStack.getInstance().cleanup();
        AvidTreeWalker.getInstance().stop();
        AvidStateWatcher.getInstance().stop();
        AvidLoader.getInstance().unregisterAvidLoader();
        f8307b = null;
    }

    private boolean m11104c() {
        return !AvidAdSessionRegistry.getInstance().isEmpty();
    }

    private void m11105d() {
        AvidAdSessionRegistry.getInstance().setListener(null);
        for (InternalAvidAdSession avidBridgeManager : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            avidBridgeManager.getAvidBridgeManager().onAvidJsReady();
        }
        AvidAdSessionRegistry.getInstance().setListener(this);
    }

    public void onAvidLoaded() {
        if (m11104c()) {
            m11105d();
            if (AvidAdSessionRegistry.getInstance().hasActiveSessions()) {
                m11101a();
            }
        }
    }

    public void onAppStateChanged(boolean isActive) {
        if (isActive) {
            AvidTreeWalker.getInstance().start();
        } else {
            AvidTreeWalker.getInstance().pause();
        }
    }

    public void registryHasSessionsChanged(AvidAdSessionRegistry registry) {
        if (!registry.isEmpty() && !AvidBridge.isAvidJsReady()) {
            AvidLoader.getInstance().setListener(this);
            AvidLoader.getInstance().registerAvidLoader(f8307b);
        }
    }

    public void registryHasActiveSessionsChanged(AvidAdSessionRegistry registry) {
        if (registry.hasActiveSessions() && AvidBridge.isAvidJsReady()) {
            m11101a();
        } else {
            m11103b();
        }
    }

    @VisibleForTesting
    static void m11102a(AvidManager avidManager) {
        f8306a = avidManager;
    }
}
