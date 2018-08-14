package com.mobfox.sdk.networking;

import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public interface AsyncCallbackJSON {
    void onComplete(int i, JSONObject jSONObject, Map<String, List<String>> map);

    void onError(Exception exception);
}
