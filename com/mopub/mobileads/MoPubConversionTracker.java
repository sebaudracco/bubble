package com.mopub.mobileads;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.mopub.common.BaseUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.SharedPreferencesHelper;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.TrackingRequest;
import com.mopub.network.TrackingRequest.Listener;
import com.mopub.volley.VolleyError;

public class MoPubConversionTracker {
    private Context mContext;
    private String mIsTrackedKey;
    private String mPackageName;
    private SharedPreferences mSharedPreferences;

    class C36561 implements Listener {
        C36561() {
        }

        public void onResponse(@NonNull String url) {
            MoPubConversionTracker.this.mSharedPreferences.edit().putBoolean(MoPubConversionTracker.this.mIsTrackedKey, true).commit();
        }

        public void onErrorResponse(VolleyError volleyError) {
        }
    }

    private class ConversionUrlGenerator extends BaseUrlGenerator {
        private ConversionUrlGenerator() {
        }

        public String generateUrlString(String serverHostname) {
            initUrlString(serverHostname, Constants.CONVERSION_TRACKING_HANDLER);
            setApiVersion("6");
            setPackageId(MoPubConversionTracker.this.mPackageName);
            setAppVersion(ClientMetadata.getInstance(MoPubConversionTracker.this.mContext).getAppVersion());
            appendAdvertisingInfoTemplates();
            return getFinalUrlString();
        }

        private void setPackageId(String packageName) {
            addParam("id", packageName);
        }
    }

    public void reportAppOpen(Context context) {
        if (context != null) {
            this.mContext = context;
            this.mPackageName = this.mContext.getPackageName();
            this.mIsTrackedKey = this.mPackageName + " tracked";
            this.mSharedPreferences = SharedPreferencesHelper.getSharedPreferences(this.mContext);
            if (isAlreadyTracked()) {
                MoPubLog.m12061d("Conversion already tracked");
            } else {
                TrackingRequest.makeTrackingHttpRequest(new ConversionUrlGenerator().generateUrlString(Constants.HOST), this.mContext, new C36561());
            }
        }
    }

    private boolean isAlreadyTracked() {
        return this.mSharedPreferences.getBoolean(this.mIsTrackedKey, false);
    }
}
