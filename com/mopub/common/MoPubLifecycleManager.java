package com.mopub.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

public class MoPubLifecycleManager implements LifecycleListener {
    private static MoPubLifecycleManager sInstance;
    @NonNull
    private final Set<LifecycleListener> mLifecycleListeners = new HashSet();
    @NonNull
    private final WeakReference<Activity> mMainActivity;

    private MoPubLifecycleManager(Activity mainActivity) {
        this.mMainActivity = new WeakReference(mainActivity);
    }

    @NonNull
    public static synchronized MoPubLifecycleManager getInstance(Activity mainActivity) {
        MoPubLifecycleManager moPubLifecycleManager;
        synchronized (MoPubLifecycleManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubLifecycleManager(mainActivity);
            }
            moPubLifecycleManager = sInstance;
        }
        return moPubLifecycleManager;
    }

    public void addLifecycleListener(@Nullable LifecycleListener listener) {
        if (listener != null && this.mLifecycleListeners.add(listener)) {
            Activity activity = (Activity) this.mMainActivity.get();
            if (activity != null) {
                listener.onCreate(activity);
                listener.onStart(activity);
            }
        }
    }

    public void onCreate(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onCreate(activity);
        }
    }

    public void onStart(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onStart(activity);
        }
    }

    public void onPause(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onPause(activity);
        }
    }

    public void onResume(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onResume(activity);
        }
    }

    public void onRestart(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onRestart(activity);
        }
    }

    public void onStop(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onStop(activity);
        }
    }

    public void onDestroy(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onDestroy(activity);
        }
    }

    public void onBackPressed(@NonNull Activity activity) {
        for (LifecycleListener lifecycleListener : this.mLifecycleListeners) {
            lifecycleListener.onBackPressed(activity);
        }
    }
}
