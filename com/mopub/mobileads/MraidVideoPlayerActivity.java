package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.IntentActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Reflection;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mraid.MraidVideoViewController;

public class MraidVideoPlayerActivity extends BaseVideoPlayerActivity implements BaseVideoViewControllerListener {
    private static final String NATIVE_VIDEO_VIEW_CONTROLLER = "com.mopub.nativeads.NativeVideoViewController";
    @Nullable
    private BaseVideoViewController mBaseVideoController;
    private long mBroadcastIdentifier;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        this.mBroadcastIdentifier = getBroadcastIdentifierFromIntent(getIntent());
        try {
            this.mBaseVideoController = createVideoViewController(savedInstanceState);
            this.mBaseVideoController.onCreate();
        } catch (IllegalStateException e) {
            BaseBroadcastReceiver.broadcastAction(this, this.mBroadcastIdentifier, IntentActions.ACTION_INTERSTITIAL_FAIL);
            finish();
        }
    }

    protected void onPause() {
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onPause();
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onResume();
        }
    }

    protected void onDestroy() {
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onDestroy();
        }
        super.onDestroy();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onSaveInstanceState(outState);
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onConfigurationChanged(newConfig);
        }
    }

    public void onBackPressed() {
        if (this.mBaseVideoController != null && this.mBaseVideoController.backButtonEnabled()) {
            super.onBackPressed();
            this.mBaseVideoController.onBackPressed();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.mBaseVideoController != null) {
            this.mBaseVideoController.onActivityResult(requestCode, resultCode, data);
        }
    }

    private BaseVideoViewController createVideoViewController(Bundle savedInstanceState) throws IllegalStateException {
        String clazz = getIntent().getStringExtra(BaseVideoPlayerActivity.VIDEO_CLASS_EXTRAS_KEY);
        if (FullAdType.VAST.equals(clazz)) {
            return new VastVideoViewController(this, getIntent().getExtras(), savedInstanceState, this.mBroadcastIdentifier, this);
        } else if (AdType.MRAID.equals(clazz)) {
            return new MraidVideoViewController(this, getIntent().getExtras(), savedInstanceState, this);
        } else {
            if ("native".equals(clazz)) {
                Class[] constructorParameterClasses = new Class[]{Context.class, Bundle.class, Bundle.class, BaseVideoViewControllerListener.class};
                Object[] constructorParameterValues = new Object[]{this, getIntent().getExtras(), savedInstanceState, this};
                if (Reflection.classFound(NATIVE_VIDEO_VIEW_CONTROLLER)) {
                    try {
                        return (BaseVideoViewController) Reflection.instantiateClassWithConstructor(NATIVE_VIDEO_VIEW_CONTROLLER, BaseVideoViewController.class, constructorParameterClasses, constructorParameterValues);
                    } catch (Exception e) {
                        throw new IllegalStateException("Missing native video module");
                    }
                }
                throw new IllegalStateException("Missing native video module");
            }
            throw new IllegalStateException("Unsupported video type: " + clazz);
        }
    }

    public void onSetContentView(View view) {
        setContentView(view);
    }

    public void onSetRequestedOrientation(int requestedOrientation) {
        setRequestedOrientation(requestedOrientation);
    }

    public void onFinish() {
        finish();
    }

    public void onStartActivityForResult(Class<? extends Activity> clazz, int requestCode, Bundle extras) {
        if (clazz != null) {
            try {
                startActivityForResult(Intents.getStartActivityIntent(this, clazz, extras), requestCode);
            } catch (ActivityNotFoundException e) {
                MoPubLog.d("Activity " + clazz.getName() + " not found. Did you declare it in your AndroidManifest.xml?");
            }
        }
    }

    protected static long getBroadcastIdentifierFromIntent(Intent intent) {
        return intent.getLongExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, -1);
    }

    @Deprecated
    BaseVideoViewController getBaseVideoViewController() {
        return this.mBaseVideoController;
    }

    @Deprecated
    void setBaseVideoViewController(BaseVideoViewController baseVideoViewController) {
        this.mBaseVideoController = baseVideoViewController;
    }
}
