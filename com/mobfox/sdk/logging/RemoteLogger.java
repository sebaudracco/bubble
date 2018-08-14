package com.mobfox.sdk.logging;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import com.loopj.android.http.RequestParams;
import com.mobfox.sdk.bannerads.Banner;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.MobFoxRequest;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public class RemoteLogger {
    public static final String DOMAIN = "http://sdk-logs.matomy.com";
    public static final String PATHNAME = "gelf";
    public static final int PORT = 12201;

    public enum Message {
        NO_FILL,
        LOADED,
        ERROR,
        REQUEST,
        REQUEST_INTERSTITIAL,
        NO_FILL_INTERSTITIAL,
        LOADED_INTERSTITIAL,
        ERROR_INTERSTITIAL,
        SHOWN_INTERSTITIAL
    }

    public static JSONObject getJSON(Context context, String invh, String short_message, String facility, String extraData) {
        try {
            JSONObject json = new JSONObject();
            json.put("ua", new WebView(context).getSettings().getUserAgentString());
            json.put("id", UUID.randomUUID().toString());
            json.put("host", context.getPackageName());
            json.put("core", Constants.MOBFOX_SDK_VERSION);
            json.put("invh", invh);
            json.put("message", short_message);
            json.put("facility", facility);
            json.put("extra", extraData);
            json.put("platform", "android");
            return json;
        } catch (Throwable th) {
            return null;
        }
    }

    public static JSONObject getJSON(Context context, String invh, String short_message, String facility, String extraData, Exception err) {
        try {
            JSONArray stack = new JSONArray();
            for (StackTraceElement ste : err.getStackTrace()) {
                stack.put(ste.getClassName() + ", " + ste.getFileName() + ":" + ste.getLineNumber() + " >> " + ste.getMethodName() + "()");
            }
            JSONObject json = new JSONObject();
            json.put("ua", new WebView(context).getSettings().getUserAgentString());
            json.put("error", err.toString());
            json.put("id", UUID.randomUUID().toString());
            json.put("host", context.getPackageName());
            json.put("core", stack);
            json.put("invh", invh);
            json.put("message", short_message);
            json.put("facility", facility);
            json.put("extra", extraData);
            json.put("platform", "android");
            return json;
        } catch (Throwable th) {
            return null;
        }
    }

    public static void post(Context context, String invh, String short_message, String facility, String pubId, Exception err) {
        if (Banner.DEBUG_MODE) {
            try {
                postToGrayLog(context, getJSON(context, invh, short_message, facility, pubId, err), null);
            } catch (Throwable throwable) {
                Log.d(Constants.MOBFOX_BANNER, "send log failed: " + throwable.toString());
            }
        }
    }

    public static void post(Context context, String invh, String short_message, String facility, String pubId) {
        if (Banner.DEBUG_MODE) {
            try {
                postToGrayLog(context, getJSON(context, invh, short_message, facility, pubId), null);
            } catch (Throwable throwable) {
                Log.d(Constants.MOBFOX_BANNER, "send log failed: " + throwable.toString());
            }
        }
    }

    public static void postToGrayLog(Context context, JSONObject data, AsyncCallback cb) {
        try {
            MobFoxRequest req = new MobFoxRequest(context, "http://sdk-logs.matomy.com:12201/gelf").setHeader("Content-Type", RequestParams.APPLICATION_JSON);
            req.setData(data);
            req.post(cb);
        } catch (Throwable e) {
            Log.d(Constants.MOBFOX_GRAYLOG, e.toString());
        }
    }
}
