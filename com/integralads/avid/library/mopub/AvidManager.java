package com.integralads.avid.library.mopub;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import com.integralads.avid.library.mopub.AvidLoader.AvidLoaderListener;
import com.integralads.avid.library.mopub.AvidStateWatcher.AvidStateWatcherListener;
import com.integralads.avid.library.mopub.activity.AvidActivityStack;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistryListener;
import com.integralads.avid.library.mopub.session.AbstractAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

public class AvidManager implements AvidLoaderListener, AvidStateWatcherListener, AvidAdSessionRegistryListener {
    private static AvidManager avidManagerInstance = new AvidManager();
    private static Context context;

    public static AvidManager getInstance() {
        return avidManagerInstance;
    }

    public void init(Context context) {
        if (context == null) {
            context = context.getApplicationContext();
            AvidStateWatcher.getInstance().init(context);
            AvidAdSessionRegistry.getInstance().setListener(this);
            AvidJSONUtil.init(context);
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

    private void start() {
        AvidStateWatcher.getInstance().setStateWatcherListener(this);
        AvidStateWatcher.getInstance().start();
        if (AvidStateWatcher.getInstance().isActive()) {
            AvidTreeWalker.getInstance().start();
        }
    }

    private void stop() {
        AvidActivityStack.getInstance().cleanup();
        AvidTreeWalker.getInstance().stop();
        AvidStateWatcher.getInstance().stop();
        AvidLoader.getInstance().unregisterAvidLoader();
        context = null;
    }

    private boolean isActive() {
        return !AvidAdSessionRegistry.getInstance().isEmpty();
    }

    private void notifyAvidReady() {
        AvidAdSessionRegistry.getInstance().setListener(null);
        for (InternalAvidAdSession internalAvidAdSession : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            internalAvidAdSession.getAvidBridgeManager().onAvidJsReady();
        }
        AvidAdSessionRegistry.getInstance().setListener(this);
    }

    public void onAvidLoaded() {
        if (isActive()) {
            notifyAvidReady();
            if (AvidAdSessionRegistry.getInstance().hasActiveSessions()) {
                start();
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
            AvidLoader.getInstance().registerAvidLoader(context);
        }
    }

    public void registryHasActiveSessionsChanged(AvidAdSessionRegistry registry) {
        if (registry.hasActiveSessions() && AvidBridge.isAvidJsReady()) {
            start();
        } else {
            stop();
        }
    }

    @VisibleForTesting
    static void setAvidManagerInstance(AvidManager instance) {
        avidManagerInstance = instance;
    }
}
