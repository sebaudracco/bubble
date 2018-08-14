package com.silvermob.sdk;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.webkit.WebView;
import com.bgjd.ici.p025b.C1408j.C1404b;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialAd extends Ad {
    private static HashMap<Long, InterstitialAd> sRunningAds = new HashMap();
    private AdResponse mAdResonse;
    private Context mContext;
    private InterstitialListener mListener;
    private Handler mShowHandler;
    private int mSkipTimeout = 5;
    private Long mTimestamp;

    public interface InterstitialAdLoadListener {
        void onAdLoaded();

        void onError();

        void onNoAd();
    }

    public interface InterstitialListener {
        void onClicked();

        void onClosed();

        void onError();

        void onShown();
    }

    class C39632 implements Runnable {
        C39632() {
        }

        public void run() {
            Intent intent = new Intent(InterstitialAd.this.mContext, InterstitalActivity.class);
            intent.putExtra(Const.AD_RESPONSE, InterstitialAd.this.mAdResonse);
            intent.putExtra(Const.AD_SKIP_TIMEOUT, InterstitialAd.this.mSkipTimeout);
            InterstitialAd.this.mContext.startActivity(intent);
        }
    }

    class C39643 implements Runnable {
        C39643() {
        }

        public void run() {
            InterstitialAd.this.mListener.onClicked();
        }
    }

    class C39654 implements Runnable {
        C39654() {
        }

        public void run() {
            InterstitialAd.this.mListener.onClosed();
        }
    }

    class C39665 implements Runnable {
        C39665() {
        }

        public void run() {
            InterstitialAd.this.mListener.onShown();
        }
    }

    public boolean isForeground() {
        String PackageName = this.mContext.getPackageName();
        ActivityManager manager = (ActivityManager) this.mContext.getSystemService(C1404b.aw);
        if (manager != null) {
            return ((RunningTaskInfo) manager.getRunningTasks(1).get(0)).topActivity.getPackageName().equals(PackageName);
        }
        return false;
    }

    public InterstitialAd(Context ctx) {
        this.mContext = ctx;
        this.mTimestamp = Long.valueOf(System.currentTimeMillis());
        Tools.initLocationServices(ctx);
    }

    private Long getTimestamp() {
        return this.mTimestamp;
    }

    public void setSkipTimeout(int val) {
        this.mSkipTimeout = val;
    }

    public void requestInterstitialAd(final String placementId, final InterstitialAdLoadListener listener) {
        final Handler handler = new Handler();
        ((Activity) this.mContext).runOnUiThread(new Runnable() {
            public void run() {
                final String ua = new WebView(InterstitialAd.this.mContext).getSettings().getUserAgentString();
                new Thread(new Runnable() {

                    class C39571 implements Runnable {
                        C39571() {
                        }

                        public void run() {
                            listener.onAdLoaded();
                        }
                    }

                    class C39582 implements Runnable {
                        C39582() {
                        }

                        public void run() {
                            listener.onNoAd();
                        }
                    }

                    class C39593 implements Runnable {
                        C39593() {
                        }

                        public void run() {
                            listener.onError();
                        }
                    }

                    class C39604 implements Runnable {
                        C39604() {
                        }

                        public void run() {
                            listener.onError();
                        }
                    }

                    public void run() {
                        String targetingParams = Tools.getTargetingDataAsGetParams(InterstitialAd.this.mContext);
                        try {
                            JSONObject response = Tools.jsonRequest("http://silvermob.com/marketplace/api/ads?c=sdk&v=1.0&size=320x480&format=json&ua=" + URLEncoder.encode(ua, "UTF-8") + "&pid=" + placementId + "&" + targetingParams + "&_ts=" + Long.valueOf(System.currentTimeMillis() / 1000).toString());
                            if (response == null) {
                                handler.post(new C39593());
                            } else if (response.has("htmlString")) {
                                InterstitialAd.this.mAdResonse = new AdResponse(response.getString("htmlString"), "320x480", InterstitialAd.this.mTimestamp);
                                handler.post(new C39571());
                            } else {
                                handler.post(new C39582());
                            }
                        } catch (JSONException e) {
                            handler.post(new C39604());
                        } catch (IOException e2) {
                            handler.post(new C39604());
                        }
                    }
                }).start();
            }
        });
    }

    public void showInterstitialAd(InterstitialListener listener) {
        if (this.mAdResonse == null || !isForeground()) {
            listener.onError();
            return;
        }
        this.mShowHandler = new Handler();
        this.mListener = listener;
        sRunningAds.put(getTimestamp(), this);
        ((Activity) this.mContext).runOnUiThread(new C39632());
    }

    private void notifyAdClicked() {
        if (this.mListener != null) {
            this.mShowHandler.post(new C39643());
        }
    }

    static void notifyAdClicked(Long timeStamp) {
        InterstitialAd ad = (InterstitialAd) sRunningAds.get(timeStamp);
        if (ad != null) {
            ad.notifyAdClicked();
        }
    }

    private void notifyAdClosed() {
        if (this.mListener != null) {
            this.mShowHandler.post(new C39654());
        }
    }

    static void notifyAdClosed(Long timeStamp) {
        InterstitialAd ad = (InterstitialAd) sRunningAds.get(timeStamp);
        if (ad != null) {
            ad.notifyAdClosed();
        }
    }

    private void notifyAdShown() {
        if (this.mListener != null) {
            this.mShowHandler.post(new C39665());
        }
    }

    static void notifyAdShown(Long timeStamp) {
        InterstitialAd ad = (InterstitialAd) sRunningAds.get(timeStamp);
        if (ad != null) {
            ad.notifyAdShown();
        }
    }
}
