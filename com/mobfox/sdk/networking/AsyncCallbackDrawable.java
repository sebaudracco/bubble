package com.mobfox.sdk.networking;

import android.graphics.drawable.Drawable;
import java.util.List;
import java.util.Map;

public interface AsyncCallbackDrawable {
    void onComplete(int i, Drawable drawable, Map<String, List<String>> map);

    void onError(Exception exception);
}
