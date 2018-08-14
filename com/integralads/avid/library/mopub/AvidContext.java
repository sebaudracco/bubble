package com.integralads.avid.library.mopub;

import android.content.Context;

public class AvidContext {
    private static final AvidContext instance = new AvidContext();
    private String bundleId;

    public static AvidContext getInstance() {
        return instance;
    }

    public void init(Context context) {
        if (this.bundleId == null) {
            this.bundleId = context.getApplicationContext().getPackageName();
        }
    }

    public String getBundleId() {
        return this.bundleId;
    }

    public String getAvidVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public String getAvidReleaseDate() {
        return BuildConfig.RELEASE_DATE;
    }

    public String getPartnerName() {
        return BuildConfig.SDK_NAME;
    }
}
