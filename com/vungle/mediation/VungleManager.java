package com.vungle.mediation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.VungleAdEventListener;
import com.vungle.publisher.VungleInitListener;
import com.vungle.publisher.VunglePub;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.inject.Injector;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class VungleManager implements VungleAdEventListener {
    private static final String PLAYING_PLACEMENT = "placementID";
    private static final String TAG = VungleManager.class.getSimpleName();
    private static final String VERSION = "5.3.2";
    private static VungleManager sInstance;
    private String mAppId;
    private String mCurrentPlayId = null;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mIsInitialising = false;
    private Map<String, VungleListener> mListeners = new HashMap();
    private String[] mPlacements;
    private VunglePub mVunglePub;

    class C41571 implements VungleInitListener {

        class C41551 implements Runnable {
            C41551() {
            }

            public void run() {
                VungleManager.this.mIsInitialising = false;
                VungleManager.this.mVunglePub.clearAndSetEventListeners(VungleManager.this);
                for (VungleListener cb : VungleManager.this.mListeners.values()) {
                    if (cb.isWaitingInit()) {
                        cb.setWaitingInit(false);
                        cb.onInitialized(VungleManager.this.mVunglePub.isInitialized());
                    }
                }
            }
        }

        class C41562 implements Runnable {
            C41562() {
            }

            public void run() {
                VungleManager.this.mIsInitialising = false;
                for (VungleListener cb : VungleManager.this.mListeners.values()) {
                    if (cb.isWaitingInit()) {
                        cb.setWaitingInit(false);
                        cb.onInitialized(VungleManager.this.mVunglePub.isInitialized());
                    }
                }
            }
        }

        C41571() {
        }

        public void onSuccess() {
            VungleManager.this.mHandler.post(new C41551());
        }

        public void onFailure(Throwable throwable) {
            VungleManager.this.mHandler.post(new C41562());
        }
    }

    static VungleManager getInstance(String appId, String[] placements) {
        if (sInstance == null) {
            sInstance = new VungleManager(appId, placements);
        }
        return sInstance;
    }

    private VungleManager(String appId, String[] placements) {
        Injector injector = Injector.getInstance();
        injector.setWrapperFramework(WrapperFramework.admob);
        injector.setWrapperFrameworkVersion("5.3.2".replace('.', '_'));
        this.mAppId = appId;
        this.mPlacements = placements;
        this.mVunglePub = VunglePub.getInstance();
    }

    boolean isInitialized() {
        return this.mVunglePub.isInitialized();
    }

    String findPlacement(Bundle networkExtras, Bundle serverParameters) {
        String placement = null;
        if (networkExtras != null && networkExtras.containsKey("playPlacement")) {
            placement = networkExtras.getString("playPlacement");
        }
        if (serverParameters != null && serverParameters.containsKey(PLAYING_PLACEMENT)) {
            if (placement != null) {
                Log.i(TAG, "'placementID' had a value in both serverParameters and networkExtras. Used one from serverParameters");
            }
            placement = serverParameters.getString(PLAYING_PLACEMENT);
        }
        if (placement != null) {
            return placement;
        }
        placement = this.mPlacements[0];
        Log.i(TAG, String.format("'placementID' not specified. Used first from 'allPlacements': %s", new Object[]{placement}));
        return placement;
    }

    void init(Context context) {
        if (this.mVunglePub.isInitialized()) {
            for (VungleListener cb : this.mListeners.values()) {
                if (cb.isWaitingInit()) {
                    cb.setWaitingInit(false);
                    cb.onInitialized(this.mVunglePub.isInitialized());
                }
            }
        } else if (!this.mIsInitialising) {
            this.mIsInitialising = true;
            this.mVunglePub.init(context, this.mAppId, this.mPlacements, new C41571());
        }
    }

    void removeListener(String id) {
        if (this.mListeners.containsKey(id)) {
            this.mListeners.remove(id);
        }
    }

    void addListener(String id, VungleListener listener) {
        removeListener(id);
        this.mListeners.put(id, listener);
    }

    void playAd(String placement, AdConfig cfg, String id) {
        if (this.mCurrentPlayId == null) {
            this.mCurrentPlayId = id;
            this.mVunglePub.playAd(placement, cfg);
        }
    }

    void onPause() {
        this.mVunglePub.onPause();
    }

    void onResume() {
        this.mVunglePub.onResume();
    }

    boolean isAdPlayable(String placement) {
        return this.mVunglePub.isAdPlayable(placement);
    }

    void loadAd(String placement) {
        if (this.mVunglePub.isAdPlayable(placement)) {
            notifyAdIsReady(placement);
        } else {
            this.mVunglePub.loadAd(placement);
        }
    }

    private void notifyAdIsReady(String placement) {
        for (VungleListener cb : this.mListeners.values()) {
            try {
                if (cb.getWaitingForPlacement() != null && cb.getWaitingForPlacement().equals(placement)) {
                    cb.onAdAvailable();
                    cb.waitForAd(null);
                }
            } catch (Exception exception) {
                Log.w(TAG, exception);
            }
        }
    }

    public void onAdEnd(@NonNull final String placement, final boolean wasSuccessfulView, final boolean wasCallToActionClicked) {
        this.mHandler.post(new Runnable() {
            public void run() {
                for (Entry<String, VungleListener> entry : VungleManager.this.mListeners.entrySet()) {
                    try {
                        if (VungleManager.this.mCurrentPlayId == null || VungleManager.this.mCurrentPlayId.equals(entry.getKey())) {
                            ((VungleListener) entry.getValue()).onAdEnd(placement, wasSuccessfulView, wasCallToActionClicked);
                        }
                    } catch (Exception exception) {
                        Log.w(VungleManager.TAG, exception);
                    }
                }
                VungleManager.this.mCurrentPlayId = null;
            }
        });
    }

    public void onAdStart(@NonNull final String placement) {
        this.mHandler.post(new Runnable() {
            public void run() {
                for (Entry<String, VungleListener> entry : VungleManager.this.mListeners.entrySet()) {
                    try {
                        if (VungleManager.this.mCurrentPlayId == null || VungleManager.this.mCurrentPlayId.equals(entry.getKey())) {
                            ((VungleListener) entry.getValue()).onAdStart(placement);
                        }
                    } catch (Exception exception) {
                        Log.w(VungleManager.TAG, exception);
                    }
                }
            }
        });
    }

    public void onUnableToPlayAd(@NonNull final String placement, String reason) {
        this.mHandler.post(new Runnable() {
            public void run() {
                for (Entry<String, VungleListener> entry : VungleManager.this.mListeners.entrySet()) {
                    try {
                        if (VungleManager.this.mCurrentPlayId == null || VungleManager.this.mCurrentPlayId.equals(entry.getKey())) {
                            ((VungleListener) entry.getValue()).onAdFail(placement);
                        }
                    } catch (Exception exception) {
                        Log.w(VungleManager.TAG, exception);
                    }
                }
                VungleManager.this.mCurrentPlayId = null;
            }
        });
    }

    public void onAdAvailabilityUpdate(@NonNull final String placement, boolean isAdAvailable) {
        if (isAdAvailable) {
            this.mHandler.post(new Runnable() {
                public void run() {
                    VungleManager.this.notifyAdIsReady(placement);
                }
            });
        }
    }
}
