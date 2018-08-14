package com.cuebiq.cuebiqsdk.task;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.api.GDPRConsentRequest;
import com.cuebiq.cuebiqsdk.injection.Injection;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.wrapper.GDPRConsent;
import com.cuebiq.cuebiqsdk.utils.AdvertisingIdClient.AdInfo;
import com.cuebiq.cuebiqsdk.utils.GDPRPopupConstants;
import com.mopub.common.GpsHelper;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class GAIDRunnable implements Runnable {
    private final Context mContext;
    private final GAIDHandler mHandler = new GAIDHandler(this);
    private final OnGAIDListener mOnGAIDListener;

    class GAIDHandler extends Handler {
        private final WeakReference<GAIDRunnable> mGAIDRunnable;

        GAIDHandler(GAIDRunnable gAIDRunnable) {
            super(Looper.getMainLooper());
            this.mGAIDRunnable = new WeakReference(gAIDRunnable);
        }

        public void handleMessage(Message message) {
            GAIDRunnable gAIDRunnable = (GAIDRunnable) this.mGAIDRunnable.get();
            if (gAIDRunnable != null) {
                gAIDRunnable.handleSuccess(message);
            }
        }
    }

    public interface OnGAIDListener {
        void onGoogleAdvID(String str, boolean z);
    }

    public GAIDRunnable(Context context, OnGAIDListener onGAIDListener) {
        this.mContext = context;
        this.mOnGAIDListener = onGAIDListener;
    }

    private void checkGoogleAdvIDChangesForGDPR(String str) throws IOException {
        String retrieveGoogleAdvID = PersistenceManagerFactory.get().retrieveGoogleAdvID(this.mContext);
        if (!"".equals(retrieveGoogleAdvID) && !retrieveGoogleAdvID.equals(str)) {
            if (Injection.provideNetworkLayer().callSync(new GDPRConsentRequest(PersistenceManagerFactory.get().retrieveAppKey(this.mContext), new GDPRConsent(str, GDPRPopupConstants.GAID_IDFA_CHANGE.getPopupValue()))).isSuccessful()) {
                PersistenceManagerFactory.get().saveGDPRConsentSentSuccesfully(this.mContext, true);
            } else {
                PersistenceManagerFactory.get().saveGDPRConsentSentSuccesfully(this.mContext, false);
            }
        }
    }

    private void handleSuccess(Message message) {
        if (message.what == 1) {
            AdInfo adInfo = (AdInfo) message.obj;
            this.mOnGAIDListener.onGoogleAdvID(adInfo.getId(), adInfo.isLimitAdTrackingEnabled());
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{this.mContext});
            Boolean bool = (Boolean) invoke.getClass().getDeclaredMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0]);
            PersistenceManagerFactory.get().setIsGAIDDisabled(this.mContext, bool.booleanValue());
            CuebiqSDKImpl.log("GAI Checker -> GAID Disabled: " + bool);
            String str = (String) invoke.getClass().getDeclaredMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            CuebiqSDKImpl.log("GAI Checker -> GAID: " + str);
            checkGoogleAdvIDChangesForGDPR(str);
            PersistenceManagerFactory.get().saveGoogleAdvID(this.mContext, str);
            if (this.mOnGAIDListener != null) {
                if (this.mHandler != null) {
                    this.mHandler.obtainMessage(1, new AdInfo(str, bool.booleanValue())).sendToTarget();
                    return;
                }
                return;
            }
        } catch (Throwable th) {
            CuebiqSDKImpl.log("GAI Checker -> Google Play Store App not available");
            PersistenceManagerFactory.get().setIsGAIDDisabled(this.mContext, false);
        }
        if (this.mOnGAIDListener != null && this.mHandler != null) {
            this.mHandler.obtainMessage(0, null).sendToTarget();
        }
    }
}
