package com.silvermob.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout.LayoutParams;
import java.io.IOException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class BannerAd extends Ad {
    private AdResponse mAdResponse;
    private BannerView mBanner;
    private Context mContext;
    private Handler mHandler;
    private boolean mIsAppActive = true;
    private BannerAdListener mListener;
    private String mPlacementId;
    private int mRefreshTimeout = 20;
    private Runnable reloadBanner = new C39463();

    public interface BannerAdListener {
        void onAdLoaded(View view);

        void onClicked();

        void onError();

        void onNoAd();

        void onShown();
    }

    class C39463 implements Runnable {
        C39463() {
        }

        public void run() {
            if (BannerAd.this.mIsAppActive) {
                BannerAd.this.requestBanner(BannerAd.this.mPlacementId, BannerAd.this.mListener);
            }
            BannerAd.this.mHandler.postDelayed(this, (long) (BannerAd.this.mRefreshTimeout * 1000));
        }
    }

    public BannerAd(Context ctx) {
        this.mContext = ctx;
    }

    public void setRefreshTimeout(int val) {
        if (val >= 0) {
            this.mRefreshTimeout = val;
        }
    }

    private void updateBannerView(final BannerAdListener listener) {
        if (this.mBanner == null) {
            this.mBanner = new BannerView(this.mContext, this.mAdResponse, 320, 50, false, new BannerAdViewListener() {
                public void onLoad() {
                    if (listener != null) {
                        listener.onShown();
                    }
                }

                public void onRefreshed() {
                }

                public void onPause() {
                    BannerAd.this.mIsAppActive = false;
                }

                public void onResume() {
                    BannerAd.this.mIsAppActive = true;
                }

                public void onClick() {
                    if (listener != null) {
                        listener.onClicked();
                    }
                }
            });
            float scale = this.mContext.getResources().getDisplayMetrics().density;
            this.mBanner.setLayoutParams(new LayoutParams((int) ((((float) 320) * scale) + 0.5f), (int) ((((float) 50) * scale) + 0.5f), 17));
        }
    }

    private void requestBanner(final String placementId, final BannerAdListener listener) {
        final Handler handler = new Handler();
        ((Activity) this.mContext).runOnUiThread(new Runnable() {
            public void run() {
                final String ua = new WebView(BannerAd.this.mContext).getSettings().getUserAgentString();
                new Thread(new Runnable() {

                    class C39422 implements Runnable {
                        C39422() {
                        }

                        public void run() {
                            if (BannerAd.this.mAdResponse == null) {
                                listener.onNoAd();
                            }
                        }
                    }

                    class C39433 implements Runnable {
                        C39433() {
                        }

                        public void run() {
                            listener.onError();
                        }
                    }

                    public void run() {
                        String targetingParams = Tools.getTargetingDataAsGetParams(BannerAd.this.mContext);
                        try {
                            JSONObject response = Tools.jsonRequest("http://silvermob.com/marketplace/api/ads?c=sdk&v=1.0&size=320x50&format=json&ua=" + URLEncoder.encode(ua, "UTF-8") + "&pid=" + placementId + "&" + targetingParams + "&_ts=" + Long.valueOf(System.currentTimeMillis() / 1000).toString());
                            if (response.has("htmlString")) {
                                final String code = response.getString("htmlString");
                                handler.post(new Runnable() {
                                    public void run() {
                                        BannerAd.this.mAdResponse = new AdResponse(code, "320x50");
                                        if (BannerAd.this.mBanner == null) {
                                            BannerAd.this.updateBannerView(listener);
                                            listener.onAdLoaded(BannerAd.this.mBanner);
                                        } else {
                                            BannerAd.this.mBanner.updateAdRsponse(BannerAd.this.mAdResponse);
                                        }
                                        BannerAd.this.mBanner.showContent();
                                    }
                                });
                                return;
                            }
                            handler.post(new C39422());
                        } catch (JSONException e) {
                            handler.post(new C39433());
                        } catch (IOException e2) {
                            handler.post(new C39433());
                        }
                    }
                }).start();
            }
        });
    }

    public void requestBannerAd(String placementId, BannerAdListener listener) {
        this.mPlacementId = placementId;
        this.mListener = listener;
        requestBanner(placementId, listener);
        this.mHandler = new Handler();
        if (this.mRefreshTimeout != 0) {
            this.mHandler.postDelayed(this.reloadBanner, 100);
        }
    }
}
