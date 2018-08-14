package com.mobfox.sdk.networking;

import java.util.List;
import java.util.Map;

public interface AsyncCallback {
    void onComplete(int i, Object obj, Map<String, List<String>> map);

    void onError(Exception exception);
}
