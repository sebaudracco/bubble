package com.appsgeyser.sdk.ads.sdk;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.BaseSecureJsInterface;
import com.appsgeyser.sdk.ads.FullScreenBanner.BannerTypes;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class JavascriptSdkController extends BaseSecureJsInterface {
    private final Context context;

    protected JavascriptSdkController(Context context) {
        this.context = context;
    }

    private HashMap<String, String> jsonStringToMap(String json) {
        HashMap<String, String> map = new HashMap();
        try {
            JSONObject jObject = new JSONObject(json);
            Iterator<?> keys = jObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                map.put(key, jObject.getString(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @JavascriptInterface
    public void startSessionForSdk(String sdkName, String jsonExtras) {
        SdkWrapper wrapper = SdkWrapperFactory.getInstance().getWrapperByKey(sdkName);
        wrapper.addExtras(jsonStringToMap(jsonExtras));
        if (this.context != null) {
            InternalEntryPoint.getInstance().getFullScreenBanner(this.context).setBannerType(BannerTypes.SDK);
            wrapper.startSession(this.context);
        }
    }

    @JavascriptInterface
    public void stopSessionForSdk(String sdkName) {
        SdkWrapperFactory.getInstance().getWrapperByKey(sdkName).stopSession();
    }

    @JavascriptInterface
    public boolean isSdkActive(String sdkName) {
        return SdkWrapperFactory.getInstance().getWrapperByKey(sdkName).isActive();
    }

    @JavascriptInterface
    public void showFSBannerForSdk(String sdkName) {
        SdkWrapperFactory.getInstance().getWrapperByKey(sdkName).showFsBanner();
    }

    @JavascriptInterface
    public void setFSBannerCallbacksForSdk(String sdkName, String onAdReadyJSCallback, String onAdFailedJSCallback, String onAdClickedJSCallback, String onAdCLosedJSCallback) {
    }
}
