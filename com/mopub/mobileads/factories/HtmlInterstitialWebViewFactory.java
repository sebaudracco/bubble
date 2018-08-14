package com.mopub.mobileads.factories;

import android.content.Context;
import com.mopub.common.AdReport;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.HtmlInterstitialWebView;

public class HtmlInterstitialWebViewFactory {
    protected static HtmlInterstitialWebViewFactory instance = new HtmlInterstitialWebViewFactory();

    public static HtmlInterstitialWebView create(Context context, AdReport adReport, CustomEventInterstitialListener customEventInterstitialListener, boolean isScrollable, String redirectUrl, String clickthroughUrl) {
        return instance.internalCreate(context, adReport, customEventInterstitialListener, isScrollable, redirectUrl, clickthroughUrl);
    }

    public HtmlInterstitialWebView internalCreate(Context context, AdReport adReport, CustomEventInterstitialListener customEventInterstitialListener, boolean isScrollable, String redirectUrl, String clickthroughUrl) {
        HtmlInterstitialWebView htmlInterstitialWebView = new HtmlInterstitialWebView(context, adReport);
        htmlInterstitialWebView.init(customEventInterstitialListener, isScrollable, redirectUrl, clickthroughUrl, adReport.getDspCreativeId());
        return htmlInterstitialWebView;
    }

    @Deprecated
    public static void setInstance(HtmlInterstitialWebViewFactory factory) {
        instance = factory;
    }
}
