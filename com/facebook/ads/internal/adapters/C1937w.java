package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.AdError;

public class C1937w extends BroadcastReceiver {
    private String f4481a;
    private Context f4482b;
    private InterstitialAdapterListener f4483c;
    private InterstitialAdapter f4484d;

    public C1937w(Context context, String str, InterstitialAdapter interstitialAdapter, InterstitialAdapterListener interstitialAdapterListener) {
        this.f4482b = context;
        this.f4481a = str;
        this.f4483c = interstitialAdapterListener;
        this.f4484d = interstitialAdapter;
    }

    public void m6119a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.impression.logged:" + this.f4481a);
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.f4481a);
        intentFilter.addAction("com.facebook.ads.interstitial.dismissed:" + this.f4481a);
        intentFilter.addAction("com.facebook.ads.interstitial.clicked:" + this.f4481a);
        intentFilter.addAction("com.facebook.ads.interstitial.error:" + this.f4481a);
        LocalBroadcastManager.getInstance(this.f4482b).registerReceiver(this, intentFilter);
    }

    public void m6120b() {
        try {
            LocalBroadcastManager.getInstance(this.f4482b).unregisterReceiver(this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        Object obj = intent.getAction().split(":")[0];
        if (this.f4483c != null && obj != null) {
            if ("com.facebook.ads.interstitial.clicked".equals(obj)) {
                this.f4483c.onInterstitialAdClicked(this.f4484d, null, true);
            } else if ("com.facebook.ads.interstitial.dismissed".equals(obj)) {
                this.f4483c.onInterstitialAdDismissed(this.f4484d);
            } else if ("com.facebook.ads.interstitial.displayed".equals(obj)) {
                this.f4483c.onInterstitialAdDisplayed(this.f4484d);
            } else if ("com.facebook.ads.interstitial.impression.logged".equals(obj)) {
                this.f4483c.onInterstitialLoggingImpression(this.f4484d);
            } else if ("com.facebook.ads.interstitial.error".equals(obj)) {
                this.f4483c.onInterstitialError(this.f4484d, AdError.INTERNAL_ERROR);
            }
        }
    }
}
