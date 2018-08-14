package com.moat.analytics.mobile.mpub;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import java.util.Map;

public abstract class MoatFactory {
    @UiThread
    public abstract <T> T createCustomTracker(C3408l<T> c3408l);

    @UiThread
    public abstract NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull Map<String, String> map);

    @UiThread
    public abstract NativeVideoTracker createNativeVideoTracker(String str);

    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup);

    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull WebView webView);

    public static MoatFactory create() {
        try {
            return new C3436k();
        } catch (Exception e) {
            C3442o.m11756(e);
            return new com.moat.analytics.mobile.mpub.NoOp.MoatFactory();
        }
    }
}
