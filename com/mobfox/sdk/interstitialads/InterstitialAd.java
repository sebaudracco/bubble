package com.mobfox.sdk.interstitialads;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.mobfox.sdk.bannerads.Banner;
import com.mobfox.sdk.bannerads.BannerListener;
import com.mobfox.sdk.bannerads.LayoutUtils;
import com.mobfox.sdk.bannerads.SizeUtils;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventInterstitial;
import com.mobfox.sdk.customevents.CustomEventInterstitialListener;
import com.mobfox.sdk.logging.MobFoxReport;
import com.mobfox.sdk.webview.MobFoxWebView;
import com.mobfox.sdk.webview.MobFoxWebViewLoadAdListener;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialAd {
    static boolean secure = false;
    Banner banner;
    BannerListener bannerListener;
    Context context = null;
    Handler handler;
    CustomEventInterstitial interstitial;
    protected EventIterator iterator;
    InterstitialAdListener listener = null;
    MobFoxWebViewLoadAdListener loadAdListener;
    private final Object lock = new Object();
    boolean ready = false;
    String f9031s = null;
    InterstitialAd self;

    class C35641 implements MobFoxWebViewLoadAdListener {
        C35641() {
        }

        public void onError(MobFoxWebView wv, Exception e) {
            if (e.getMessage() != null) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "on error " + e.getMessage());
            } else {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial ad on error");
            }
            InterstitialAd.this.banner.cancelTimeout();
            if (InterstitialAd.this.listener != null) {
                InterstitialAd.this.listener.onInterstitialFailed(InterstitialAd.this.self, e);
            }
        }

        public void onAdResponse(MobFoxWebView wv, final JSONObject adResp) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "on ad resp");
            if (!InterstitialAd.this.banner.isTimeout()) {
                InterstitialAd.this.banner.cancelTimeout();
                Map<String, Object> params = new HashMap();
                if (InterstitialAd.this.banner.getDemo_age() > 0) {
                    params.put("demo_age", Integer.valueOf(InterstitialAd.this.banner.getDemo_age()));
                }
                if (InterstitialAd.this.banner.getDemo_gender().length() > 0) {
                    params.put("demo_gender", InterstitialAd.this.banner.getDemo_gender());
                }
                if (InterstitialAd.this.banner.getDemo_keywords().length() > 0) {
                    params.put("demo_keywords", InterstitialAd.this.banner.getDemo_keywords());
                }
                InterstitialAd.this.iterator = new EventIterator(InterstitialAd.this.context, wv, adResp, params);
                if (InterstitialAd.this.iterator.hasNext()) {
                    InterstitialAd.this.iterator.callNextEvent(new CustomEventInterstitialListener() {
                        public void onInterstitialLoaded(CustomEventInterstitial interstitial) {
                            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial loaded");
                            InterstitialAd.this.ready = true;
                            InterstitialAd.this.self.interstitial = interstitial;
                            if (InterstitialAd.this.listener != null) {
                                InterstitialAd.this.listener.onInterstitialLoaded(InterstitialAd.this.self);
                            }
                        }

                        public void onInterstitialFailed(CustomEventInterstitial interstitial, Exception e) {
                            InterstitialAd.this.banner.cancelTimeout();
                            if (e.getMessage().equals("onAutoRedirect")) {
                                try {
                                    String requestID = adResp.getString("requestID");
                                    Log.d(Constants.MOBFOX_INTERSTITIAL, "requestID " + requestID);
                                    JSONObject log = MobFoxReport.getLogJson(InterstitialAd.this.context);
                                    log.put("requestID", requestID);
                                    MobFoxReport.post(InterstitialAd.this.context, log, null);
                                    return;
                                } catch (JSONException e2) {
                                    return;
                                }
                            }
                            if (e.getMessage() != null) {
                                Log.d(Constants.MOBFOX_INTERSTITIAL, "on interstitial failed " + e.getMessage());
                            } else {
                                Log.d(Constants.MOBFOX_INTERSTITIAL, "on interstitial failed");
                            }
                            if (InterstitialAd.this.iterator.hasNext()) {
                                InterstitialAd.this.iterator.callNextEvent(this);
                            } else if (InterstitialAd.this.listener != null) {
                                InterstitialAd.this.listener.onInterstitialFailed(InterstitialAd.this.self, new Exception("no ads in response"));
                            }
                        }

                        public void onInterstitialClosed(CustomEventInterstitial interstitial) {
                            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial closed");
                            if (InterstitialAd.this.listener != null) {
                                InterstitialAd.this.listener.onInterstitialClosed(InterstitialAd.this.self);
                            }
                        }

                        public void onInterstitialFinished() {
                            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial finished");
                            if (InterstitialAd.this.listener != null) {
                                InterstitialAd.this.listener.onInterstitialFinished();
                            }
                        }

                        public void onInterstitialClicked(CustomEventInterstitial interstitial) {
                            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial clicked");
                            if (InterstitialAd.this.listener != null) {
                                InterstitialAd.this.listener.onInterstitialClicked(InterstitialAd.this.self);
                            }
                        }

                        public void onInterstitialShown(CustomEventInterstitial interstitial) {
                            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial shown");
                            if (InterstitialAd.this.listener != null) {
                                InterstitialAd.this.listener.onInterstitialShown(InterstitialAd.this.self);
                            }
                        }
                    });
                }
            }
        }

        public void onNoAd(MobFoxWebView wv) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "no fill");
            InterstitialAd.this.banner.cancelTimeout();
            if (InterstitialAd.this.listener != null) {
                InterstitialAd.this.listener.onInterstitialFailed(InterstitialAd.this.self, new Exception("no fill"));
            }
        }
    }

    class C35652 implements BannerListener {
        C35652() {
        }

        public void onBannerError(View banner, Exception e) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "on banner error");
            if (InterstitialAd.this.listener != null) {
                InterstitialAd.this.listener.onInterstitialFailed(InterstitialAd.this.self, e);
            }
        }

        public void onBannerLoaded(View banner) {
        }

        public void onBannerClosed(View banner) {
        }

        public void onBannerFinished() {
        }

        public void onBannerClicked(View banner) {
        }

        public void onNoFill(View banner) {
        }
    }

    class C35663 implements Runnable {
        C35663() {
        }

        public void run() {
            InterstitialAd.this.interstitial.showInterstitial();
        }
    }

    protected void setup() {
        this.loadAdListener = new C35641();
        this.banner.setLoadAdListener(this.loadAdListener);
    }

    public InterstitialAd(Context context) {
        this.context = context;
        this.handler = new Handler(context.getMainLooper());
        this.self = this;
        try {
            Point nearest = SizeUtils.getNearestSupported(LayoutUtils.getScreenSize(context));
            int width = nearest.x;
            int height = nearest.y;
            int orientation = context.getResources().getConfiguration().orientation;
            if (orientation == 1 || orientation == 0) {
                this.banner = createAd(context, width, height);
            }
            if (orientation == 2) {
                this.banner = createAd(context, height, width);
            }
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "create ad");
            this.banner = createAd(context, 320, SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT);
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "create banner throwable");
            this.banner = createAd(context, 320, SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT);
        }
        this.banner.setSkip(true);
        this.banner.setAdFormat("interstitial");
        setup();
        this.bannerListener = new C35652();
    }

    protected Banner createAd(Context context, int width, int height) {
        try {
            return new Banner(context, width, height);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "create ad exception " + e.getMessage());
            }
            return null;
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "create ad throwable");
            return null;
        }
    }

    protected void setOrientation(Context context, int orientation) {
        if (orientation == 1) {
            ((Activity) context).setRequestedOrientation(1);
        }
        if (orientation == 2) {
            ((Activity) context).setRequestedOrientation(0);
        }
    }

    public void load() {
        if (this.self.f9031s == null) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "please set inventory hash before load()");
            if (this.self.listener != null) {
                this.listener.onInterstitialFailed(this.self, new Exception("please set inventory hash before load()"));
            }
        } else if (this.self.listener == null) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "please set interstitial listener before load()");
        } else {
            this.banner.setInventoryHash(this.self.f9031s);
            this.banner.setListener(this.self.bannerListener);
            this.banner.load();
        }
    }

    public void show() {
        if (this.interstitial == null) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "please call show() from onInterstitialLoaded");
            if (this.listener != null) {
                this.listener.onInterstitialFailed(this.self, new Exception("please call show() from onInterstitialLoaded"));
                return;
            }
            return;
        }
        new Handler(this.context.getMainLooper()).post(new C35663());
    }

    public void onPause() {
        if (this.banner != null) {
            this.banner.onPause();
        }
    }

    public void onResume() {
        if (this.banner != null) {
            this.banner.onResume();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.banner != null) {
            this.banner.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public Banner getBanner() {
        return this.banner;
    }

    public EventIterator getIterator() {
        return this.iterator;
    }

    public boolean isReady() {
        return this.ready;
    }

    public static void setSecure(boolean secure) {
        secure = secure;
        Banner.setSecure(secure);
    }

    public void setInventoryHash(String s) {
        this.f9031s = s;
    }

    public void setListener(InterstitialAdListener listener) {
        this.listener = listener;
    }

    public void setType(String type) {
        this.banner.setType(type);
    }

    public void setSkip(boolean skip) {
        this.banner.setSkip(skip);
    }

    public void setStart_muted(boolean start_muted) {
        this.banner.setStart_muted(start_muted);
    }

    public static void getLocation(boolean loc) {
        Banner.setLoc(loc);
    }
}
