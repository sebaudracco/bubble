package com.mobfox.sdk.customevents;

import android.content.Context;
import java.util.Map;

public interface CustomEventBanner {
    void loadAd(Context context, CustomEventBannerListener customEventBannerListener, String str, Map<String, Object> map);
}
