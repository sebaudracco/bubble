package com.mobfox.sdk.bannerads;

import android.content.Context;
import android.location.Location;
import android.net.http.HttpResponseCache;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventBannerListener;
import com.mobfox.sdk.logging.MobFoxReport;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.MobFoxRequest;
import com.mobfox.sdk.runnables.Repeater;
import com.mobfox.sdk.runnables.Timeout;
import com.mobfox.sdk.services.MobFoxAdIdService;
import com.mobfox.sdk.services.MobFoxAdIdService.Listener;
import com.mobfox.sdk.services.MobFoxLocationService;
import com.mobfox.sdk.tagbanner.TagParams;
import com.mobfox.sdk.utils.Utils;
import com.mobfox.sdk.webview.MobFoxWebView;
import com.mobfox.sdk.webview.MobFoxWebViewLoadAdListener;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xml.serialize.LineSeparator;
import org.json.JSONException;
import org.json.JSONObject;

public class Banner extends RelativeLayout {
    public static boolean DEBUG_MODE = false;
    private static final long LAYOUT_READY_INTERVAL = 200;
    public static String LOAD_START = "MobFoxBanner >> banner load start";
    static String O_ANDADVID = "";
    private static final int WATERFALLS_QUERY_TIMEOUT = 1000;
    static int dev_js = 1;
    public static boolean loc = false;
    static boolean secure = false;
    static Boolean warmedUp = new Boolean(false);
    String adFormat = "banner";
    int adspace_height = 0;
    int adspace_strict = 0;
    int adspace_width = 0;
    boolean auto_pilot = true;
    String autoplay = SchemaSymbols.ATTVAL_TRUE;
    int banner_pos = 0;
    Context context;
    boolean debug = false;
    int demo_age = 0;
    String demo_gender = "";
    String demo_keywords = "";
    int dev_dnt = 0;
    Handler handler;
    boolean hasLayout = false;
    EventIterator iterator;
    BannerListener listener;
    MobFoxWebViewLoadAdListener loadAdListener;
    long loadStart = 0;
    Location location;
    MobFoxLocationService locationService;
    public MobFoxWebView mobFoxWebView = null;
    float r_floor = 0.0f;
    private int refresh = 0;
    Repeater repeater;
    String rt = "android_app";
    String f9030s = "";
    Banner self;
    boolean skip = false;
    public boolean smart = false;
    boolean start_muted = false;
    String sub_bundle_id = "";
    Timeout timeout = new Timeout(null, null);
    String type = "waterfall";
    int v_dur_max = 0;
    int v_dur_min = 0;
    String waterfalls = "";

    class C35421 implements MobFoxWebViewLoadAdListener {
        C35421() {
        }

        public void onError(MobFoxWebView wv, Exception e) {
            if (e.getMessage() != null) {
                Log.d(Constants.MOBFOX_BANNER, "weblistener error: " + e.getMessage());
            }
            Banner.logTime(Banner.LOAD_START, Banner.this.loadStart);
            Banner.this.timeout.cancel();
            MobFoxReport.postException(Banner.this.context, e, null);
            if (Banner.this.listener != null) {
                Banner.this.listener.onBannerError(Banner.this.self, e);
            }
        }

        public void onAdResponse(MobFoxWebView wv, final JSONObject adResp) {
            Log.d(Constants.MOBFOX_BANNER, "ad response");
            Banner.logTime("ad response", Banner.this.loadStart);
            if (!Banner.this.timeout.isTimeout()) {
                Map<String, Object> params = new HashMap();
                params.put("width", Integer.valueOf(Banner.this.adspace_width));
                params.put("height", Integer.valueOf(Banner.this.adspace_height));
                if (Banner.this.demo_age > 0) {
                    params.put("demo_age", Integer.valueOf(Banner.this.demo_age));
                }
                if (Banner.this.demo_gender.length() > 0) {
                    params.put("demo_gender", Banner.this.demo_gender);
                }
                if (Banner.this.demo_keywords.length() > 0) {
                    params.put("demo_keywords", Banner.this.demo_keywords);
                }
                Banner.this.iterator = new EventIterator(Banner.this.context, wv, adResp, params);
                if (Banner.this.iterator.hasNext()) {
                    Banner.this.iterator.callNextEvent(new CustomEventBannerListener() {
                        public void onBannerError(View banner, Exception e) {
                            if (!Banner.this.timeout.isTimeout()) {
                                Banner.this.timeout.cancel();
                                if (e.getMessage().equals("onAutoRedirect")) {
                                    try {
                                        String requestID = adResp.getString("requestID");
                                        Log.d(Constants.MOBFOX_BANNER, "requestID " + requestID);
                                        JSONObject log = MobFoxReport.getLogJson(Banner.this.context);
                                        log.put("requestID", requestID);
                                        MobFoxReport.post(Banner.this.context, log, null);
                                        return;
                                    } catch (JSONException e2) {
                                        return;
                                    }
                                }
                                Banner.logTime(Banner.LOAD_START, Banner.this.loadStart);
                                if (!e.getMessage().equals("onNoAd")) {
                                    MobFoxReport.postException(Banner.this.context, e, null);
                                    if (Banner.this.iterator.hasNext()) {
                                        Banner.this.iterator.callNextEvent(this);
                                    } else if (Banner.this.listener != null) {
                                        Banner.this.listener.onBannerError(banner, e);
                                    }
                                } else if (Banner.this.listener != null) {
                                    Banner.this.listener.onNoFill(Banner.this.self);
                                }
                            }
                        }

                        public void onBannerLoaded(View banner) {
                            Banner.logTime("ad rendered", Banner.this.loadStart);
                            if (!Banner.this.timeout.isTimeout()) {
                                Log.d(Constants.MOBFOX_BANNER, "banner loaded");
                                Banner.this.timeout.cancel();
                                Banner.this.show(banner);
                                if (Banner.this.listener != null) {
                                    Banner.this.listener.onBannerLoaded(Banner.this.self);
                                }
                            }
                        }

                        public void onBannerClosed(View banner) {
                            Log.d(Constants.MOBFOX_BANNER, "banner closed");
                            Banner.this.self.removeAllViews();
                            if (Banner.this.listener != null) {
                                Banner.this.listener.onBannerClosed(banner);
                            }
                        }

                        public void onBannerFinished() {
                            Log.d(Constants.MOBFOX_BANNER, "banner finished");
                            if (Banner.this.listener != null) {
                                Banner.this.listener.onBannerFinished();
                            }
                        }

                        public void onBannerClicked(View banner) {
                            Log.d(Constants.MOBFOX_BANNER, "banner clicked");
                            if (Banner.this.listener != null) {
                                Banner.this.listener.onBannerClicked(banner);
                            }
                        }
                    });
                }
            }
        }

        public void onNoAd(MobFoxWebView wv) {
            Log.d(Constants.MOBFOX_BANNER, "on no ad");
            Banner.logTime(Banner.LOAD_START, Banner.this.loadStart);
            Banner.this.timeout.cancel();
            if (Banner.this.listener != null) {
                Banner.this.listener.onNoFill(Banner.this.self);
            }
        }
    }

    class C35432 implements Callable {
        C35432() {
        }

        public Object call() throws Exception {
            if (Banner.this.listener == null) {
                return Boolean.valueOf(false);
            }
            Banner.this.listener.onBannerError(Banner.this.self, new Exception("timeout"));
            return Boolean.valueOf(true);
        }
    }

    class C35443 implements Callable {
        C35443() {
        }

        public Object call() throws Exception {
            Banner.this.load();
            return Boolean.valueOf(true);
        }
    }

    class C35454 implements Runnable {
        C35454() {
        }

        public void run() {
            if (Banner.this.hasLayout) {
                Banner.this.load(Banner.this.self.f9030s);
            } else {
                Banner.this.handler.postDelayed(this, 200);
            }
        }
    }

    class C35465 implements AsyncCallback {
        C35465() {
        }

        public void onComplete(int code, Object response, Map<String, List<String>> map) {
            Banner.this.waterfalls = response.toString();
            Banner.this.mobFoxWebView.setWaterfalls(Banner.this.waterfalls.replace("\n", "").replace(Utils.FILE_SEPARATOR, "").replace(LineSeparator.Macintosh, ""));
            try {
                JSONObject pub = new JSONObject(Banner.this.waterfalls);
                if (pub.has("debug")) {
                    Banner.DEBUG_MODE = pub.getBoolean("debug");
                } else {
                    Banner.DEBUG_MODE = false;
                }
            } catch (JSONException e) {
            }
        }

        public void onError(Exception e) {
            Banner.this.mobFoxWebView.setWaterfalls("");
            if (e.getMessage() != null) {
                Log.d(Constants.MOBFOX_BANNER, "on waterfalls fetch " + e.getMessage());
                if (!e.getMessage().contains(MobFoxWebView.WATERFALL_CONNECTION_EXCEPTION)) {
                    MobFoxReport.postException(Banner.this.context, e, null);
                    return;
                }
                return;
            }
            Log.d(Constants.MOBFOX_BANNER, "on waterfalls fetch error");
        }
    }

    class C35476 implements Listener {
        C35476() {
        }

        public void onFinish(String adv_id) {
            if (adv_id == null) {
                Banner.O_ANDADVID = "";
            } else {
                Banner.O_ANDADVID = adv_id;
            }
        }
    }

    class C35508 implements MobFoxLocationService.Listener {
        C35508() {
        }

        public void onLocation(Location location) {
            Banner.this.setLocation(location);
        }

        public void onError(String err) {
            Log.d(Constants.MOBFOX_BANNER, NotificationCompat.CATEGORY_ERROR);
        }
    }

    public static void warmUp(Context context) {
        synchronized (warmedUp) {
            try {
                if (HttpResponseCache.getInstalled() == null) {
                    HttpResponseCache.install(new File(context.getCacheDir(), "mobfox-http"), 10485760);
                }
                if (warmedUp.booleanValue()) {
                    return;
                }
                new MobFoxRequest(context, MobFoxWebView.getMobfoxUrl()).get(null);
                new MobFoxRequest(context, MobFoxWebView.getMobfoxUrlVideo()).get(null);
                new MobFoxRequest(context, MobFoxWebView.getMobfoxUrlBridge()).get(null);
                warmedUp = Boolean.valueOf(true);
            } catch (Throwable t) {
                Log.d(Constants.MOBFOX_BANNER, "error init cache", t);
            }
        }
    }

    public void getAttrs(AttributeSet attrs) {
        try {
            String packname = "http://schemas.android.com/apk/lib/com.mobfox.sdk";
            if (attrs != null) {
                this.smart = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/lib/com.mobfox.sdk", "smart", false);
                loc = attrs.getAttributeBooleanValue("http://schemas.android.com/apk/lib/com.mobfox.sdk", "enableLocation", false);
            }
        } catch (Throwable e) {
            Log.d(Constants.MOBFOX_BANNER, "attrs err " + e.toString());
        }
    }

    public Banner(Context context) {
        super(context);
        this.context = context;
        this.self = this;
        this.handler = new Handler(context.getMainLooper());
        setUp();
        init();
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.self = this;
        this.handler = new Handler(context.getMainLooper());
        getAttrs(attrs);
        setUp();
        init();
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.self = this;
        this.handler = new Handler(context.getMainLooper());
        getAttrs(attrs);
        setUp();
        init();
    }

    public Banner(Context context, int width, int height) {
        super(context);
        this.context = context;
        this.adspace_width = width;
        this.adspace_height = height;
        setLayoutParams(new LayoutParams(LayoutUtils.convertDpToPixel((float) width, context), LayoutUtils.convertDpToPixel((float) height, context)));
        this.self = this;
        this.handler = new Handler(context.getMainLooper());
        setUp();
        init();
    }

    protected void setUp() {
        MobFoxReport.register(this.context);
        this.loadAdListener = new C35421();
    }

    protected void init() {
        warmUp(this.context);
        getAdvId();
        loadJs();
        getLayout();
        getLocation();
        this.sub_bundle_id = Utils.getBundleId(this.context);
        Utils.postDMP(this.context, this.mobFoxWebView);
        Utils.startPreCaching(this.context);
    }

    public void load() {
        this.loadStart = System.currentTimeMillis();
        if (this.self.f9030s == null || this.self.f9030s.isEmpty()) {
            Log.d(Constants.MOBFOX_BANNER, "please set inventory hash before load()");
            if (this.self.listener != null) {
                this.self.listener.onBannerError(this.self, new Exception("please set inventory hash before load()"));
            } else {
                return;
            }
        }
        this.timeout.cancel();
        this.timeout = new Timeout(this.context, new C35432());
        this.handler.postDelayed(this.timeout, 5000);
        if (this.repeater != null) {
            this.repeater.stop();
        }
        if (this.refresh > 0) {
            this.repeater = new Repeater(this.context, this.handler, (long) this.refresh, new C35443());
            this.repeater.start();
        }
        load(this.self.f9030s);
    }

    protected void show(View banner) {
        this.self.removeAllViews();
        this.self.addView(banner);
    }

    protected void load(String invh) {
        if (this.hasLayout) {
            if (this.smart) {
                if (this.adspace_height == 50) {
                    this.adspace_width = 320;
                } else if (this.adspace_height == 90) {
                    this.adspace_width = 728;
                } else {
                    Log.d(Constants.MOBFOX_BANNER, "smart banner supports 50, 90 heights");
                    if (this.listener != null) {
                        this.timeout.cancel();
                        this.listener.onBannerError(this.self, new Exception("smart banner supports 50, 90 heights"));
                        return;
                    }
                    return;
                }
                LayoutUtils.setSmartDimensions(this.context, this.self, this.adspace_height);
            }
            JSONObject params = makeParams();
            if (params != null) {
                loadBanner(params);
                return;
            }
            return;
        }
        this.handler.postDelayed(new C35454(), 200);
    }

    protected void loadBanner(JSONObject params) {
        try {
            this.mobFoxWebView.loadAd(params.toString());
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_BANNER, "webView loadBanner error");
            if (this.listener != null) {
                this.listener.onBannerError(this.self, e);
            }
        } catch (Throwable e2) {
            Log.d(Constants.MOBFOX_BANNER, "webView loadBanner error");
            if (this.listener != null) {
                this.listener.onBannerError(this.self, new Exception(e2.getMessage()));
            }
        }
    }

    protected void getWaterfalls(String invh) {
        MobFoxRequest request = new MobFoxRequest(this.context, Constants.WATERFALLS_URL);
        request.setTimeout(1000);
        request.setParam("p", invh);
        request.get(new C35465());
    }

    protected void getBannerPosition() {
        if (isInterstitial()) {
            this.banner_pos = 7;
            return;
        }
        try {
            if (LayoutUtils.aboveTheFold(this.context, this.self)) {
                this.banner_pos = 1;
            } else {
                this.banner_pos = 3;
            }
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_BANNER, "above the fold exception");
        } catch (Throwable th) {
            Log.d(Constants.MOBFOX_BANNER, "above the fold exception");
        }
    }

    private boolean isInterstitial() {
        if ((this.adspace_height == SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT && this.adspace_width == 320) || (this.adspace_height == 320 && this.adspace_width == SizeUtils.DEFAULT_INTERSTITIAL_HEIGHT)) {
            return true;
        }
        return false;
    }

    protected void getAdvId() {
        if (O_ANDADVID.isEmpty()) {
            new MobFoxAdIdService(new C35476(), this.context).execute();
        }
    }

    protected void loadJs() {
        this.mobFoxWebView = new MobFoxWebView(this.context, this.loadAdListener);
        if (this.adspace_width <= 0 || this.adspace_height <= 0) {
            this.mobFoxWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        } else {
            this.mobFoxWebView.setLayoutParams(new LayoutParams(LayoutUtils.convertDpToPixel((float) this.adspace_width, this.context), LayoutUtils.convertDpToPixel((float) this.adspace_height, this.context)));
        }
    }

    protected void getLayout() {
        if (this.adspace_width <= 0 || this.adspace_height <= 0) {
            final DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
            try {
                int width = (int) (((float) this.self.getWidth()) / displayMetrics.density);
                int height = (int) (((float) this.self.getHeight()) / displayMetrics.density);
                if (width > 0 && height > 0) {
                    this.self.adspace_width = width;
                    this.self.adspace_height = height;
                    Log.d(Constants.MOBFOX_BANNER, "adspace_width: " + this.self.adspace_width + "\nadspace_height: " + this.self.adspace_height);
                    this.hasLayout = true;
                    getBannerPosition();
                    return;
                }
            } catch (Exception e) {
                Log.d(Constants.MOBFOX_BANNER, "get layout error");
            } catch (Throwable th) {
                Log.d(Constants.MOBFOX_BANNER, "get layout error");
            }
            this.self.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

                class C35481 implements Runnable {
                    C35481() {
                    }

                    public void run() {
                        Banner.this.hasLayout = true;
                    }
                }

                public void onGlobalLayout() {
                    if (VERSION.SDK_INT >= 16) {
                        Banner.this.self.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    Banner.this.self.adspace_width = (int) (((float) Banner.this.self.getWidth()) / displayMetrics.density);
                    Banner.this.self.adspace_height = (int) (((float) Banner.this.self.getHeight()) / displayMetrics.density);
                    if (Banner.this.self.adspace_height > 245 && Banner.this.adspace_height < 255) {
                        Banner.this.self.adspace_height = 250;
                    }
                    Banner.this.self.post(new C35481());
                    Banner.this.getBannerPosition();
                }
            });
            return;
        }
        this.hasLayout = true;
        getBannerPosition();
    }

    protected void getLocation() {
        if (loc) {
            this.locationService = new MobFoxLocationService(new C35508(), this.context);
            this.locationService.execute();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.locationService != null) {
            this.locationService.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    protected JSONObject makeParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("s", this.f9030s);
            jsonObject.put("o_andadvid", O_ANDADVID);
            jsonObject.put("type", this.type);
            jsonObject.put("adFormat", this.adFormat);
            jsonObject.put("autoplay", this.autoplay);
            jsonObject.put("skip", this.skip ? SchemaSymbols.ATTVAL_TRUE : "");
            jsonObject.put("debug", this.debug ? SchemaSymbols.ATTVAL_TRUE : "");
            jsonObject.put("rt", this.rt);
            jsonObject.put("adspace_width", this.adspace_width);
            jsonObject.put("adspace_height", this.adspace_height);
            jsonObject.put("adspace_strict", this.adspace_strict);
            jsonObject.put("sub_bundle_id", this.sub_bundle_id);
            jsonObject.put("auto_pilot", this.auto_pilot);
            jsonObject.put("v", Constants.MOBFOX_SDK_VERSION);
            jsonObject.put(ClientCookie.SECURE_ATTR, secure);
            jsonObject.put("start_muted", this.start_muted);
            jsonObject.put(TagParams.DEV_JS, dev_js);
            if (this.v_dur_min > 0) {
                jsonObject.put("v_dur_min", this.v_dur_min);
            }
            if (this.v_dur_max > 0) {
                jsonObject.put("v_dur_max", this.v_dur_max);
            }
            if (this.r_floor > 0.0f) {
                jsonObject.put("r_floor", (double) this.r_floor);
            }
            if (this.banner_pos > 0) {
                jsonObject.put("banner_pos", this.banner_pos);
            }
            if (O_ANDADVID.isEmpty()) {
                this.dev_dnt = 1;
            }
            jsonObject.put("dev_dnt", this.dev_dnt);
            if (this.demo_gender.length() > 0) {
                jsonObject.put("demo_gender", this.demo_gender);
            }
            if (this.demo_age > 0) {
                jsonObject.put("demo_age", this.demo_age);
            }
            if (this.demo_keywords.length() > 0) {
                jsonObject.put("demo_keywords", this.demo_keywords);
            }
            if (this.location != null) {
                jsonObject.put("latitude", this.location.getLatitude());
                jsonObject.put("longitude", this.location.getLongitude());
            }
            Log.d(Constants.MOBFOX_BANNER, "request params: " + jsonObject.toString());
            return jsonObject;
        } catch (JSONException e) {
            Log.d(Constants.MOBFOX_BANNER, "build request exception");
            return null;
        }
    }

    public static void logTime(String title, long start) {
        try {
            Log.d(Constants.MOBFOX_TIME, title + " time: " + (System.currentTimeMillis() - start));
        } catch (Exception e) {
        }
    }

    public MobFoxWebViewLoadAdListener getLoadAdListener() {
        return this.loadAdListener;
    }

    public boolean isTimeout() {
        return this.timeout.isTimeout();
    }

    public void cancelTimeout() {
        this.timeout.cancel();
    }

    public String getO_andadvid() {
        return O_ANDADVID;
    }

    public MobFoxWebView getMobFoxWebView() {
        return this.mobFoxWebView;
    }

    public String getDemo_keywords() {
        return this.demo_keywords;
    }

    public String getDemo_gender() {
        return this.demo_gender;
    }

    public int getDemo_age() {
        return this.demo_age;
    }

    public String getType() {
        return this.type;
    }

    public EventIterator getIterator() {
        return this.iterator;
    }

    public int getAdHeight() {
        return this.adspace_height;
    }

    public void setInventoryHash(String invh) {
        this.f9030s = invh;
        getWaterfalls(this.f9030s);
    }

    public void setLoadAdListener(MobFoxWebViewLoadAdListener loadAdListener) {
        this.loadAdListener = loadAdListener;
        if (loadAdListener != null) {
            this.mobFoxWebView.setLoadAdListener(loadAdListener);
        }
    }

    public void setListener(BannerListener listener) {
        this.listener = listener;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAdFormat(String adFormat) {
        this.adFormat = adFormat;
    }

    public void setAutoplay(String autoplay) {
        this.autoplay = autoplay;
    }

    public void setDemo_gender(String demo_gender) {
        this.demo_gender = demo_gender;
    }

    public void setDemo_keywords(String demo_keywords) {
        this.demo_keywords = demo_keywords;
    }

    public void setRefresh(int refresh) {
        if (refresh < 5) {
            Log.d(Constants.MOBFOX_BANNER, "refresh interval must be bigger than 4 seconds");
        } else {
            this.refresh = refresh * 1000;
        }
    }

    public void setAdspace_strict(int adspace_strict) {
        this.adspace_strict = adspace_strict;
    }

    public void setV_dur_min(int v_dur_min) {
        this.v_dur_min = v_dur_min;
    }

    public void setV_dur_max(int v_dur_max) {
        this.v_dur_max = v_dur_max;
    }

    public void setR_floor(float r_floor) {
        this.r_floor = r_floor;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public static void setSecure(boolean secure) {
        secure = secure;
        MobFoxWebView.setSecure(secure);
    }

    public void setStart_muted(boolean start_muted) {
        this.start_muted = start_muted;
    }

    public void setDemo_age(int demo_age) {
        this.demo_age = demo_age;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static void setLoc(boolean loc) {
        loc = loc;
    }

    public static void setDev_js(int dev_js) {
        dev_js = dev_js;
    }

    public void setSmart(boolean smart) {
        this.smart = smart;
    }

    public void onPause() {
        if (this.repeater != null) {
            this.repeater.stop();
        }
        if (this.mobFoxWebView != null) {
            this.mobFoxWebView.onPause();
        }
    }

    public void onResume() {
        if (this.repeater != null) {
            this.repeater.start();
        }
        if (this.mobFoxWebView != null) {
            this.mobFoxWebView.onResume();
        }
    }
}
