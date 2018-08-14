package com.mobfox.sdk.customevents;

import android.content.Context;
import android.view.View;
import com.mobfox.sdk.nativeads.Tracker;
import java.util.List;
import java.util.Map;

public interface CustomEventNative {
    void load(Context context, CustomEventNativeListener customEventNativeListener, String str, List<Tracker> list, Map<String, Object> map);

    void registerViewForInteraction(View view);
}
