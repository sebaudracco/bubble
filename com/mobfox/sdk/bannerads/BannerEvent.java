package com.mobfox.sdk.bannerads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventBanner;
import com.mobfox.sdk.customevents.CustomEventBannerListener;
import com.mobfox.sdk.webview.MobFoxWebView;
import com.mobfox.sdk.webview.MobFoxWebViewRenderAdListener;
import java.util.Map;
import org.json.JSONObject;

public class BannerEvent implements CustomEventBanner {
    View banner;
    JSONObject respObj;
    MobFoxWebView webView;

    public BannerEvent(MobFoxWebView webView, JSONObject respObj) {
        this.webView = webView;
        this.respObj = respObj;
    }

    public void loadAd(final Context context, @NonNull final CustomEventBannerListener listener, String networkID, Map<String, Object> map) {
        this.webView.setRenderAdListener(new MobFoxWebViewRenderAdListener() {
            public void onError(MobFoxWebView wv, Exception e) {
                listener.onBannerError(wv, e);
            }

            public void onAdClick(MobFoxWebView wv, String clickURL) {
                try {
                    Intent launchBrowser = new Intent("android.intent.action.VIEW");
                    launchBrowser.setData(Uri.parse(clickURL));
                    launchBrowser.setFlags(ErrorDialogData.BINDER_CRASH);
                    context.startActivity(launchBrowser);
                    listener.onBannerClicked(wv);
                } catch (Exception e) {
                    Log.d(Constants.MOBFOX_BANNER, "launch browser exception");
                    listener.onBannerError(wv, e);
                } catch (Throwable e2) {
                    Log.d(Constants.MOBFOX_BANNER, "launch browser exception");
                    listener.onBannerError(wv, new Exception(e2.getMessage()));
                }
            }

            public void onVideoAdFinished(MobFoxWebView wv) {
                listener.onBannerFinished();
            }

            public void onAdClosed(MobFoxWebView wv) {
                listener.onBannerClosed(wv);
            }

            public void onAutoRedirect(MobFoxWebView wv, String url) {
                listener.onBannerError(wv, new Exception("onAutoRedirect"));
            }

            public void onRendered(MobFoxWebView wv, String data) {
                if (data.isEmpty()) {
                    Banner.logTime("rendered!", wv.loadBannerStarted);
                    listener.onBannerLoaded(BannerEvent.this.banner);
                    return;
                }
                listener.onBannerError(wv, new Exception(data));
            }
        });
        this.banner = this.webView;
        this.webView.renderAd(this.respObj);
        listener.onBannerLoaded(this.webView);
    }
}
