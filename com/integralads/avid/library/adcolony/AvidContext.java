package com.integralads.avid.library.adcolony;

import android.content.Context;

public class AvidContext {
    private static final AvidContext f8291a = new AvidContext();
    private String f8292b;

    public static AvidContext getInstance() {
        return f8291a;
    }

    public void init(Context context) {
        if (this.f8292b == null) {
            this.f8292b = context.getApplicationContext().getPackageName();
        }
    }

    public String getBundleId() {
        return this.f8292b;
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
