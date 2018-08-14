package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.BaseLifecycleListener;
import com.mopub.common.LifecycleListener;
import com.mopub.common.logging.MoPubLog;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.VungleInitListener;
import com.vungle.publisher.VunglePub;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.inject.Injector;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VungleRouter {
    private static final String ROUTER_TAG = "Vungle Router: ";
    private static final String VERSION = "5.3.2";
    private static VungleRouter instance = new VungleRouter();
    private static SDKInitState sInitState = SDKInitState.NOTINITIALIZED;
    private static final LifecycleListener sLifecycleListener = new C36751();
    private static VunglePub sVunglePub;
    private static Map<String, VungleRouterListener> sVungleRouterListeners = new HashMap();
    private static Map<String, VungleRouterListener> sWaitingList = new HashMap();
    private final VungleAdEventListener vungleDefaultListener = new C36773();

    static class C36751 extends BaseLifecycleListener {
        C36751() {
        }

        public void onPause(@NonNull Activity activity) {
            super.onPause(activity);
            VungleRouter.sVunglePub.onPause();
        }

        public void onResume(@NonNull Activity activity) {
            super.onResume(activity);
            VungleRouter.sVunglePub.onResume();
        }
    }

    class C36762 implements VungleInitListener {
        C36762() {
        }

        public void onSuccess() {
            MoPubLog.m12061d("Vungle Router: SDK is initialized successfully.");
            VungleRouter.sInitState = SDKInitState.INITIALIZED;
            VungleRouter.sVunglePub.clearAndSetEventListeners(VungleRouter.this.vungleDefaultListener);
            VungleRouter.this.clearWaitingList();
        }

        public void onFailure(Throwable throwable) {
            MoPubLog.m12069w("Vungle Router: Initialization is failed.");
            VungleRouter.sInitState = SDKInitState.NOTINITIALIZED;
        }
    }

    class C36773 implements VungleAdEventListener {
        C36773() {
        }

        public void onAdEnd(@NonNull String placementReferenceId, boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            MoPubLog.m12061d("Vungle Router: onAdEnd - Placement ID: " + placementReferenceId);
            VungleRouterListener targetListener = (VungleRouterListener) VungleRouter.sVungleRouterListeners.get(placementReferenceId);
            if (targetListener != null) {
                targetListener.onAdEnd(placementReferenceId, wasSuccessfulView, wasCallToActionClicked);
            } else {
                MoPubLog.m12069w("Vungle Router: onAdEnd - VungleRouterListener is not found for Placement ID: " + placementReferenceId);
            }
        }

        public void onAdStart(@NonNull String placementReferenceId) {
            MoPubLog.m12061d("Vungle Router: onAdStart - Placement ID: " + placementReferenceId);
            VungleRouterListener targetListener = (VungleRouterListener) VungleRouter.sVungleRouterListeners.get(placementReferenceId);
            if (targetListener != null) {
                targetListener.onAdStart(placementReferenceId);
            } else {
                MoPubLog.m12069w("Vungle Router: onAdStart - VungleRouterListener is not found for Placement ID: " + placementReferenceId);
            }
        }

        public void onUnableToPlayAd(@NonNull String placementReferenceId, String reason) {
            MoPubLog.m12061d("Vungle Router: onUnableToPlayAd - Placement ID: " + placementReferenceId);
            VungleRouterListener targetListener = (VungleRouterListener) VungleRouter.sVungleRouterListeners.get(placementReferenceId);
            if (targetListener != null) {
                targetListener.onUnableToPlayAd(placementReferenceId, reason);
            } else {
                MoPubLog.m12069w("Vungle Router: onUnableToPlayAd - VungleRouterListener is not found for Placement ID: " + placementReferenceId);
            }
        }

        public void onAdAvailabilityUpdate(@NonNull String placementReferenceId, boolean isAdAvailable) {
            MoPubLog.m12061d("Vungle Router: onAdAvailabilityUpdate - Placement ID: " + placementReferenceId);
            VungleRouterListener targetListener = (VungleRouterListener) VungleRouter.sVungleRouterListeners.get(placementReferenceId);
            if (targetListener != null) {
                targetListener.onAdAvailabilityUpdate(placementReferenceId, isAdAvailable);
            } else {
                MoPubLog.m12069w("Vungle Router: onAdAvailabilityUpdate - VungleRouterListener is not found for Placement ID: " + placementReferenceId);
            }
        }
    }

    private enum SDKInitState {
        NOTINITIALIZED,
        INITIALIZING,
        INITIALIZED
    }

    private VungleRouter() {
        Injector injector = Injector.getInstance();
        injector.setWrapperFramework(WrapperFramework.mopub);
        injector.setWrapperFrameworkVersion("5.3.2".replace('.', '_'));
        sVunglePub = VunglePub.getInstance();
    }

    public static VungleRouter getInstance() {
        return instance;
    }

    public LifecycleListener getLifecycleListener() {
        return sLifecycleListener;
    }

    public void initVungle(Context context, String vungleAppId, String[] placementReferenceIds) {
        sVunglePub.init(context, vungleAppId, placementReferenceIds, new C36762());
        sInitState = SDKInitState.INITIALIZING;
    }

    public boolean isVungleInitialized() {
        if (sInitState == SDKInitState.NOTINITIALIZED) {
            return false;
        }
        if (sInitState == SDKInitState.INITIALIZING || sInitState == SDKInitState.INITIALIZED) {
            return true;
        }
        return sVunglePub.isInitialized();
    }

    public void loadAdForPlacement(String placementId, VungleRouterListener routerListener) {
        switch (sInitState) {
            case NOTINITIALIZED:
                MoPubLog.m12069w("Vungle Router: There should not be this case. loadAdForPlacement is called before initialization starts.");
                return;
            case INITIALIZING:
                sWaitingList.put(placementId, routerListener);
                return;
            case INITIALIZED:
                addRouterListener(placementId, routerListener);
                sVunglePub.loadAd(placementId);
                return;
            default:
                return;
        }
    }

    private void addRouterListener(String placementId, VungleRouterListener routerListener) {
        sVungleRouterListeners.put(placementId, routerListener);
    }

    public void removeRouterListener(String placementId) {
        sVungleRouterListeners.remove(placementId);
    }

    public boolean isAdPlayableForPlacement(String placementId) {
        return sVunglePub.isAdPlayable(placementId);
    }

    public void playAdForPlacement(String placementId, AdConfig adConfig) {
        if (sVunglePub.isAdPlayable(placementId)) {
            sVunglePub.playAd(placementId, adConfig);
        } else {
            MoPubLog.m12069w("Vungle Router: There should not be this case. playAdForPlacement is called before an ad is loaded for Placement ID: " + placementId);
        }
    }

    private void clearWaitingList() {
        for (Entry<String, VungleRouterListener> entry : sWaitingList.entrySet()) {
            sVunglePub.loadAd((String) entry.getKey());
            sVungleRouterListeners.put(entry.getKey(), entry.getValue());
        }
        sWaitingList.clear();
    }
}
