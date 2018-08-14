package com.mobfox.sdk.customevents;

import android.content.Context;
import java.util.Map;

public interface CustomEventInterstitial {
    void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, String str, Map<String, Object> map);

    void showInterstitial();
}
