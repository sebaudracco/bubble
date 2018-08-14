package com.mobfox.sdk.webview;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.mobfox.sdk.constants.Constants;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.net.HttpURLConnection;
import java.net.URL;

public class MobFoxWebViewClient extends BridgeWebViewClient {
    public static final int MAX_AD_REQUEST_COUNT = 50;
    int adRequestCount = 0;
    Listener listener;

    public interface Listener {
        void onAutoRedirect(WebView webView, String str);

        void onClick(String str);

        void onError(Exception exception);
    }

    public MobFoxWebViewClient(MobFoxWebView wv, Listener listener) {
        super(wv);
        this.listener = listener;
    }

    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        if (VERSION.SDK_INT >= 21) {
            String url = request.getUrl().toString();
            if (url.contains(MobFoxWebView.DOMAIN)) {
                try {
                    String mime_type;
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setUseCaches(true);
                    String content_type = connection.getContentType();
                    String separator = HTTP.CHARSET_PARAM;
                    int pos = content_type.indexOf(HTTP.CHARSET_PARAM);
                    if (pos >= 0) {
                        mime_type = content_type.substring(0, pos);
                    } else {
                        mime_type = content_type;
                    }
                    return new WebResourceResponse(mime_type, pos >= 0 ? content_type.substring(HTTP.CHARSET_PARAM.length() + pos) : "UTF-8", connection.getInputStream());
                } catch (Exception e) {
                    Log.d(Constants.MOBFOX_WEBVIEW, "shouldInterceptRequest error " + e.toString());
                }
            }
        }
        return null;
    }

    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (url.contains(MobFoxWebView.DOMAIN)) {
            try {
                String mime_type;
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                String content_type = connection.getContentType();
                String separator = HTTP.CHARSET_PARAM;
                int pos = content_type.indexOf(HTTP.CHARSET_PARAM);
                if (pos >= 0) {
                    mime_type = content_type.substring(0, pos);
                } else {
                    mime_type = content_type;
                }
                return new WebResourceResponse(mime_type, pos >= 0 ? content_type.substring(HTTP.CHARSET_PARAM.length() + pos) : "UTF-8", connection.getInputStream());
            } catch (Exception e) {
                Log.d(Constants.MOBFOX_WEBVIEW, "shouldInterceptRequest error " + e.toString());
            }
        }
        return null;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.indexOf("data:") == 0 || url.indexOf(BridgeUtil.YY_OVERRIDE_SCHEMA) == 0) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        if (((MobFoxWebView) view).userInteraction) {
            this.listener.onClick(url);
            return true;
        }
        this.listener.onAutoRedirect(view, url);
        return true;
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        Log.d(Constants.MOBFOX_WEBVIEW, error.toString());
        super.onReceivedSslError(view, handler, error);
    }

    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        this.listener.onError(new Exception(description));
        Log.d(Constants.MOBFOX_WEBVIEW, String.format("webview error: %s, req: %s", new Object[]{description, failingUrl}));
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @TargetApi(23)
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        Log.d(Constants.MOBFOX_WEBVIEW, String.format("webview error: %s, req: %s", new Object[]{error.toString(), request.getUrl().toString()}));
        super.onReceivedError(view, request, error);
    }

    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }
}
