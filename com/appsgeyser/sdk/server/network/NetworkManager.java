package com.appsgeyser.sdk.server.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RedirectError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.appsgeyser.sdk.PausedContentInfoActivity;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.server.RequestQueueHolder;
import com.appsgeyser.sdk.server.implementation.OnRequestDoneListener;
import cz.msebera.android.httpclient.HttpStatus;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class NetworkManager {
    private static final NetworkManager INSTANCE = new NetworkManager();

    private class HandleRedirectRetryPolicy extends DefaultRetryPolicy {
        HandleRedirectRetryPolicy(int initialTimeoutMs, int maxRedirectsCount, float backoffMultiplier) {
            super(initialTimeoutMs, maxRedirectsCount, backoffMultiplier);
        }

        public void retry(VolleyError error) throws VolleyError {
            if (error instanceof RedirectError) {
                super.retry(error);
                return;
            }
            throw error;
        }
    }

    public enum RequestType {
        AFTERINSTALL,
        USAGE,
        UPDATE,
        CLICK,
        APPMODE,
        CONFIG_PHP,
        NATIVE_ADS,
        IMPRESSION
    }

    public static NetworkManager getInstance() {
        return INSTANCE;
    }

    public void sendRequestAsync(@NonNull final String requestUrl, @NonNull final Integer tag, @NonNull Context context, @Nullable final OnRequestDoneListener onResponseListener, @Nullable final ErrorListener onErrorListener) {
        sendRequestAsync(requestUrl, tag, context, new Listener<String>() {
            public void onResponse(String response) {
                if (onResponseListener != null) {
                    onResponseListener.onRequestDone(requestUrl, tag.intValue(), response);
                }
            }
        }, new ErrorListener() {
            public void onErrorResponse(VolleyError arg0) {
                if (onErrorListener != null) {
                    onErrorListener.onErrorResponse(arg0);
                }
            }
        });
    }

    public void sendRequestAsync(@NonNull String requestUrl, @NonNull Integer tag, @NonNull Context context, @Nullable Listener<String> onResponseListener, @Nullable ErrorListener onErrorListener) {
        HttpURLConnection.setFollowRedirects(true);
        StringRequest stringRequest = new StringRequest(requestUrl, onResponseListener, onErrorListener);
        stringRequest.setRetryPolicy(new HandleRedirectRetryPolicy(10000, 5, 1.0f));
        stringRequest.setTag(tag);
        RequestQueueHolder.getInstance(context).getQueue().add(stringRequest);
    }

    public ErrorListener getDefaultErrorListener(final Integer tag, final Context context) {
        return new ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                if (tag.intValue() == RequestType.USAGE.ordinal()) {
                    if (volleyError != null && volleyError.networkResponse != null && volleyError.networkResponse.statusCode == HttpStatus.SC_FORBIDDEN) {
                        Configuration.getInstance(context).getSettingsCoder().savePrefBoolean(Constants.PREFS_BAN_APP, true);
                        PausedContentInfoActivity.startPausedContentInfoActivity(context);
                    }
                } else if (volleyError != null) {
                    volleyError.printStackTrace();
                    if (volleyError.getMessage() != null) {
                        System.err.println(volleyError.getMessage());
                    } else {
                        System.err.println("volley request error");
                    }
                }
            }
        };
    }

    public OnRequestDoneListener getEmptyRequestDoneListener(@NonNull final Context context) {
        return new OnRequestDoneListener() {
            public void onRequestDone(String requestUrl, int tag, String response) {
                if (tag == RequestType.USAGE.ordinal()) {
                    Configuration.getInstance(context).getSettingsCoder().savePrefBoolean(Constants.PREFS_BAN_APP, false);
                }
            }
        };
    }

    public Map<String, List<String>> loadHeaders(String url) {
        try {
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection con = (HttpURLConnection) new URL(url + "&test=1").openConnection();
            con.setRequestMethod("HEAD");
            int iResp = con.getResponseCode();
            Map<String, List<String>> resp = con.getHeaderFields();
            if (iResp != 200) {
                return null;
            }
            return resp;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isOnline(Context context) {
        NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
