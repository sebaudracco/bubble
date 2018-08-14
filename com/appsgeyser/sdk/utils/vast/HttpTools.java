package com.appsgeyser.sdk.utils.vast;

import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTools {
    private static final String TAG = HttpTools.class.getName();

    public static void httpGetURL(final String url) {
        if (TextUtils.isEmpty(url)) {
            VASTLog.m2417w(TAG, "url is null or empty");
        } else {
            new Thread() {
                public void run() {
                    HttpURLConnection conn = null;
                    try {
                        VASTLog.m2416v(HttpTools.TAG, "connection to URL:" + url);
                        URL httpUrl = new URL(url);
                        HttpURLConnection.setFollowRedirects(true);
                        conn = (HttpURLConnection) httpUrl.openConnection();
                        conn.setConnectTimeout(5000);
                        conn.setRequestProperty("Connection", "close");
                        conn.setRequestMethod("GET");
                        VASTLog.m2416v(HttpTools.TAG, "response code:" + conn.getResponseCode() + ", for URL:" + url);
                        if (conn != null) {
                            try {
                                conn.disconnect();
                            } catch (Exception e) {
                            }
                        }
                    } catch (Exception e2) {
                        VASTLog.m2417w(HttpTools.TAG, url + ": " + e2.getMessage() + ":" + e2.toString());
                        if (conn != null) {
                            try {
                                conn.disconnect();
                            } catch (Exception e3) {
                            }
                        }
                    } catch (Throwable th) {
                        if (conn != null) {
                            try {
                                conn.disconnect();
                            } catch (Exception e4) {
                            }
                        }
                    }
                }
            }.start();
        }
    }
}
