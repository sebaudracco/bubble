package com.fyber.ads.videos.mediation;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.fyber.cache.CacheManager;
import com.fyber.exceptions.C2565a;
import com.fyber.mediation.AdFormatMediationAdapter;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.p108b.C2580a;
import com.fyber.utils.FyberLogger;
import java.util.Map;
import mf.org.apache.xerces.impl.Constants;

public abstract class RewardedVideoMediationAdapter<V extends MediationAdapter> extends AdFormatMediationAdapter<Boolean, C2565a> {
    public static final int START_TIMEOUT_DELAY = 4500;
    public static final int VALIDATION_TIMEOUT_DELAY = 4500;
    private C2465a f6200a;
    private Map<String, String> f6201b;
    private boolean f6202c = false;
    private Handler f6203d;
    protected V mAdapter;

    class C24741 implements Callback {
        final /* synthetic */ RewardedVideoMediationAdapter f6199a;

        C24741(RewardedVideoMediationAdapter rewardedVideoMediationAdapter) {
            this.f6199a = rewardedVideoMediationAdapter;
        }

        public final boolean handleMessage(Message message) {
            switch (message.what) {
                case 2:
                    this.f6199a.sendVideoEvent(TPNVideoEvent.Timeout);
                    break;
            }
            return true;
        }
    }

    public abstract void startPrecaching();

    public abstract void startVideo(Activity activity);

    public abstract void videosAvailable(Context context);

    public RewardedVideoMediationAdapter(V v) {
        this.mAdapter = v;
        this.f6203d = new Handler(Looper.getMainLooper(), new C24741(this));
    }

    public final void m7855a(Activity activity, C2465a c2465a, Map<String, String> map) {
        this.f6202c = false;
        this.f6200a = c2465a;
        this.f6201b = map;
        this.f6203d.sendEmptyMessageDelayed(2, 4500);
        startVideo(activity);
    }

    protected boolean isPrecachingDisabled() {
        return CacheManager.m8105a().m8120e();
    }

    protected void sendValidationEvent(TPNVideoValidationResult tPNVideoValidationResult) {
        if (this.providerRequesterListener == null) {
            FyberLogger.m8451i("RewardedVideoMediationAdapter", "No provider request listener");
        } else if (tPNVideoValidationResult == TPNVideoValidationResult.Success) {
            this.providerRequesterListener.setAdAvailable(Boolean.TRUE, this.request);
        } else if (tPNVideoValidationResult == TPNVideoValidationResult.NoVideoAvailable) {
            this.providerRequesterListener.setAdNotAvailable(this.request);
        } else {
            this.providerRequesterListener.setAdError(new C2565a(Constants.VALIDATION_FEATURE, tPNVideoValidationResult != null ? tPNVideoValidationResult.toString() : "Validation"), this.request);
        }
    }

    protected void sendVideoEvent(TPNVideoEvent tPNVideoEvent) {
        if (this.f6200a != null) {
            if (tPNVideoEvent.equals(TPNVideoEvent.Started)) {
                this.f6203d.removeMessages(2);
            }
            this.f6200a.mo3894a(getName(), getVersion(), tPNVideoEvent, this.f6201b);
            return;
        }
        FyberLogger.m8451i("RewardedVideoMediationAdapter", "No video event listener");
    }

    protected void clearVideoEvent() {
        this.f6200a = null;
        this.f6201b = null;
    }

    protected void setVideoPlayed() {
        sendVideoEvent(TPNVideoEvent.Finished);
        this.f6202c = true;
    }

    protected void notifyVideoStarted() {
        sendVideoEvent(TPNVideoEvent.Started);
    }

    protected void notifyCloseEngagement() {
        sendVideoEvent(this.f6202c ? TPNVideoEvent.Closed : TPNVideoEvent.Aborted);
        clearVideoEvent();
    }

    protected void notifyVideoError() {
        sendVideoEvent(TPNVideoEvent.Error);
        clearVideoEvent();
    }

    protected String getName() {
        return this.mAdapter.getName();
    }

    protected String getVersion() {
        return this.mAdapter.getVersion();
    }

    public void isAdAvailable(@NonNull Context context, @NonNull C2580a c2580a) {
        this.request = c2580a;
        videosAvailable(context);
    }
}
