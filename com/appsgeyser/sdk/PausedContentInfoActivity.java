package com.appsgeyser.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.server.network.NetworkManager;
import org.telegram.tgnet.ConnectionsManager;

public class PausedContentInfoActivity extends Activity {

    class C11941 extends WebViewClient {
        C11941() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        @TargetApi(21)
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public static void startPausedContentInfoActivity(@NonNull Context context) {
        Intent intent = new Intent(context, PausedContentInfoActivity.class);
        intent.setFlags(ConnectionsManager.FileTypeFile);
        context.startActivity(intent);
    }

    public static void checkBanApp(Context context) {
        boolean banApp = new PreferencesCoder(context).getPrefBoolean(Constants.PREFS_BAN_APP, false);
        if ((!NetworkManager.isOnline(context) && banApp) || banApp) {
            startPausedContentInfoActivity(context);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1195R.layout.appsgeysersdk_paused_content_activity);
        Log.d("PausedContentInfo", "created pausedActivity");
        WebView webView = (WebView) findViewById(C1195R.id.webView);
        if (NetworkManager.isOnline(this)) {
            webView.setWebViewClient(new C11941());
            webView.loadUrl(Constants.PAUSED_CONTENT_INFO_URL + Configuration.getInstance(this).getApplicationId());
            return;
        }
        webView.setVisibility(8);
        ((FrameLayout) findViewById(C1195R.id.ban_view)).setVisibility(0);
    }

    public void onBackPressed() {
    }
}
