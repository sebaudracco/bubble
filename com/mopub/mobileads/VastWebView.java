package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.ads.AudienceNetworkActivity;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.Utils;
import com.mopub.network.Networking;

class VastWebView extends BaseWebView {
    @Nullable
    VastWebViewClickListener mVastWebViewClickListener;

    VastWebView(Context context) {
        super(context);
        disableScrollingAndZoom();
        getSettings().setJavaScriptEnabled(true);
        enablePlugins(true);
        setBackgroundColor(0);
        setOnTouchListener(new VastWebViewOnTouchListener(this));
        setId((int) Utils.generateUniqueId());
    }

    void loadData(String data) {
        loadDataWithBaseURL(Networking.getBaseUrlScheme() + "://" + Constants.HOST + BridgeUtil.SPLIT_MARK, data, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
    }

    void setVastWebViewClickListener(@NonNull VastWebViewClickListener vastWebViewClickListener) {
        this.mVastWebViewClickListener = vastWebViewClickListener;
    }

    private void disableScrollingAndZoom() {
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        getSettings().setSupportZoom(false);
        setScrollBarStyle(0);
    }

    @NonNull
    static VastWebView createView(@NonNull Context context, @NonNull VastResource vastResource) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastResource);
        VastWebView webView = new VastWebView(context);
        vastResource.initializeWebView(webView);
        return webView;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    VastWebViewClickListener getVastWebViewClickListener() {
        return this.mVastWebViewClickListener;
    }
}
