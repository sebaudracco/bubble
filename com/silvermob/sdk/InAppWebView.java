package com.silvermob.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class InAppWebView extends Activity {
    public static final String URL_EXTRA = "extra_url";

    class C39521 extends WebViewClient {
        C39521() {
        }

        public void onPageFinished(WebView view, String url) {
            Log.d("InAppWebView", "Loaded: " + url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText((Activity) view.getContext(), "MRAID error: " + description, 0).show();
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url == null) {
                return false;
            }
            String host = Uri.parse(url).getHost();
            if ((!url.startsWith("http:") && !url.startsWith("https:")) || "play.google.com".equals(host) || "market.android.com".equals(host) || url.endsWith(".apk")) {
                try {
                    InAppWebView.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                } catch (ActivityNotFoundException e) {
                    Log.e("SilverMob: InAppWebView", "Unable to start activity for " + url + ". " + "Ensure that your phone can handle this intent.");
                }
                InAppWebView.this.finish();
                return true;
            }
            view.loadUrl(url);
            return true;
        }
    }

    class C39532 extends WebChromeClient {
        C39532() {
        }

        public void onProgressChanged(WebView view, int progress) {
            Activity a = (Activity) view.getContext();
            a.setTitle("Loading...");
            a.setProgress(progress * 100);
            if (progress == 100) {
                a.setTitle(view.getUrl());
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(2);
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(2, -1);
        initializeWebView(getIntent());
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initializeWebView(Intent intent) {
        WebView webView = new WebView(this);
        setContentView(webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webView.setWebViewClient(new C39521());
        webView.setWebChromeClient(new C39532());
        webView.loadUrl(intent.getStringExtra(Const.REDIRECT_URI));
    }
}
