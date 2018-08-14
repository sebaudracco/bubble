package com.appsgeyser.sdk.ads.sdk;

import android.content.Context;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ui.AppsgeyserProgressDialog;
import java.util.HashMap;
import java.util.Map.Entry;

abstract class SdkWrapper {
    static final String KEY_APPNEXT = "APPNEXT";
    static final String KEY_SILVERMOB = "SILVERMOB";
    protected Context context;
    private boolean isActive = false;
    HashMap<String, String> parameters;
    protected AppsgeyserProgressDialog progressDialog;

    protected enum AdType {
        FULLSCREEN,
        NATIVE
    }

    public abstract void getNativeAd();

    public abstract boolean isAdSupported(AdType adType);

    public abstract void showFsBanner();

    SdkWrapper() {
    }

    void addExtras(HashMap<String, String> extras) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        for (Entry<String, String> item : extras.entrySet()) {
            this.parameters.put(item.getKey(), item.getValue());
        }
    }

    void startSession(Context context) {
        this.progressDialog = new AppsgeyserProgressDialog(context);
        if (this.isActive) {
            stopSession();
        }
        this.context = context;
        this.isActive = true;
        InternalEntryPoint.getInstance().getFullScreenBanner(context).close();
    }

    void stopSession() {
        this.isActive = false;
    }

    boolean isActive() {
        return this.isActive;
    }
}
