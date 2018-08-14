package com.mobfox.sdk.networking;

import android.graphics.Bitmap;
import java.util.List;
import java.util.Map;

public interface AsyncCallbackBitmap {
    void onComplete(int i, Bitmap bitmap, Map<String, List<String>> map);

    void onError(Exception exception);
}
