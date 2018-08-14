package com.mobfox.sdk.logging;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import com.google.android.gms.measurement.AppMeasurement;
import com.loopj.android.http.RequestParams;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.MobFoxRequest;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class MobFoxReport implements UncaughtExceptionHandler {
    public static final String DOMAIN = "http://sdk-logs.matomy.com";
    public static final String PATHNAME = "gelf";
    public static final int PORT = 12201;
    protected static boolean isRegistered = false;
    Context context;
    UncaughtExceptionHandler defaultHandler;
    String ua = "";

    class C35751 implements AsyncCallback {
        C35751() {
        }

        public void onComplete(int code, Object response, Map<String, List<String>> map) {
            Log.d(Constants.MOBFOX_GRAYLOG, "grey log on complete, code: " + code);
        }

        public void onError(Exception e) {
            Log.d(Constants.MOBFOX_GRAYLOG, "grey log on error");
        }
    }

    public enum SHORT_MESSAGE {
        CRASH,
        ANDROID
    }

    protected void setDefaultHandler(UncaughtExceptionHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

    public static synchronized void register(Context c) {
        synchronized (MobFoxReport.class) {
            if (!isRegistered) {
                MobFoxReport crashCatcher = new MobFoxReport(c);
                UncaughtExceptionHandler defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
                if (defaultHandler != null) {
                    crashCatcher.setDefaultHandler(defaultHandler);
                }
                Thread.setDefaultUncaughtExceptionHandler(crashCatcher);
                isRegistered = true;
            }
        }
    }

    protected MobFoxReport(Context context) {
        this.context = context;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            this.ua = new WebView(context).getSettings().getUserAgentString();
        }
    }

    public void uncaughtException(Thread t, Throwable e) {
        postCrash(this.context, e, new C35751());
        if (this.defaultHandler != null) {
            this.defaultHandler.uncaughtException(t, e);
        }
    }

    public static JSONArray getErrorStack(Exception e) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (StackTraceElement ste : e.getStackTrace()) {
                jSONArray.put(ste.getClassName() + ", " + ste.getFileName() + ":" + ste.getLineNumber() + " >> " + ste.getMethodName() + "()");
            }
            return jSONArray;
        } catch (Exception e2) {
            return new JSONArray();
        }
    }

    public static String getErrorMessage(Exception e) {
        try {
            return e.getMessage();
        } catch (Exception e2) {
            return "";
        }
    }

    public static String getUserAgent(Context context) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            return "";
        }
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable th) {
            return "";
        }
    }

    public static String getCurrentThreadName() {
        try {
            return Thread.currentThread().getName();
        } catch (Exception e) {
            return "";
        }
    }

    public static String getCause(Exception e) {
        try {
            return e.getCause().toString();
        } catch (Exception e2) {
            return "";
        }
    }

    public static String getPublisherPackage(Context context) {
        try {
            return context.getPackageName();
        } catch (Exception e) {
            return "";
        }
    }

    public static JSONObject getLogJson(Context context) {
        JSONObject json = new JSONObject();
        try {
            json.put("short_message", SHORT_MESSAGE.ANDROID.toString());
            json.put("current_thread", getCurrentThreadName());
            json.put("ua", getUserAgent(context));
            json.put("publisher_package", getPublisherPackage(context));
            json.put("host", "MobFox.Android");
            json.put("sdk_version", Constants.MOBFOX_SDK_VERSION);
        } catch (Exception e) {
        }
        return json;
    }

    public static void post(Context context, JSONObject data, AsyncCallback cb) {
        try {
            MobFoxRequest req = new MobFoxRequest(context, "http://sdk-logs.matomy.com:12201/gelf").setHeader("Content-Type", RequestParams.APPLICATION_JSON);
            req.setData(data);
            req.post(cb);
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_GRAYLOG, e.getMessage());
        }
    }

    public static void postException(Context context, Throwable t, AsyncCallback cb) {
        Exception e = new Exception(t);
        try {
            JSONObject log = getLogJson(context);
            try {
                log.put("cause", getCause(e));
                log.put("stack", getErrorStack(e));
                log.put("error_message", getErrorMessage(e));
                log.put("short_message", SHORT_MESSAGE.ANDROID.toString());
                log.put("facility", "exception");
            } catch (Exception e2) {
            }
            post(context, log, cb);
        } catch (Exception e3) {
            Log.d(Constants.MOBFOX_GRAYLOG, "incomplete");
        }
    }

    public static void postCrash(Context context, Throwable t, AsyncCallback cb) {
        Exception e = new Exception(t);
        try {
            JSONObject log = getLogJson(context);
            try {
                log.put("cause", getCause(e));
                log.put("stack", getErrorStack(e));
                log.put("error_message", getErrorMessage(e));
                log.put("short_message", SHORT_MESSAGE.CRASH.toString());
                log.put("facility", AppMeasurement.CRASH_ORIGIN);
            } catch (Exception e2) {
            }
            post(context, log, cb);
        } catch (Exception e3) {
            Log.d(Constants.MOBFOX_GRAYLOG, "incomplete");
        }
    }
}
