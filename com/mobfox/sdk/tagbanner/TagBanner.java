package com.mobfox.sdk.tagbanner;

import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.RelativeLayout.LayoutParams;
import com.mobfox.sdk.bannerads.LayoutUtils;
import com.mobfox.sdk.bannerads.SizeUtils;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.services.MobFoxAdIdService;
import com.mobfox.sdk.utils.Utils;

public class TagBanner extends WebView {
    static String O_ANDADVID = "";
    int adHeight;
    int adWidth;
    String adapterName;
    Context context;
    String invHash;
    Listener listener;
    Handler mainHandler;
    TagParams tagParams;
    boolean userInteraction = false;

    public TagBanner(Context c, int width, int height, String inHash) {
        super(c);
        this.context = c;
        this.adWidth = width;
        this.adHeight = height;
        this.invHash = inHash;
        this.tagParams = new TagParams();
        this.listener = new EmptyListener(this, null);
        this.mainHandler = new Handler(this.context.getMainLooper());
        this.adapterName = "core";
        init();
    }

    protected void init() {
        setLayoutParams(new LayoutParams(LayoutUtils.convertDpToPixel((float) this.adWidth, this.context), LayoutUtils.convertDpToPixel((float) this.adHeight, this.context)));
        setWebViewSettings(this);
        getAdvId();
        Utils.postDMP(this.context, this);
        Utils.startPreCaching(this.context);
    }

    private void setWebViewSettings(TagBanner self) {
        self.setBackgroundColor(0);
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        self.getSettings().setJavaScriptEnabled(true);
        self.getSettings().setAppCacheEnabled(true);
        self.getSettings().setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 21) {
            self.getSettings().setMixedContentMode(2);
        }
        if (VERSION.SDK_INT >= 17) {
            self.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        self.setWebViewClient(new 1(this, self));
        self.setWebChromeClient(new 2(this));
        self.setOnTouchListener(new 3(this, self));
        self.addJavascriptInterface(this, "mobfox");
    }

    @JavascriptInterface
    public void onSuccess() {
        this.mainHandler.post(new 4(this, this.context, this));
    }

    @JavascriptInterface
    public void onFail(String reason) {
        reportError(this, reason);
    }

    private void reportError(TagBanner adapter, String reason) {
        this.mainHandler.post(new 5(this, this.context, reason, adapter));
    }

    public void load(TagParams params) {
        try {
            if (this.invHash == null || this.invHash.isEmpty()) {
                this.listener.onBannerError(this, "please set inventory hash before load()");
                return;
            }
            setDefaultParams();
            this.tagParams.mergeParams(params);
            String url = this.tagParams.getTagUrlQuery();
            Log.d(Constants.MOBFOX_BANNER, "Loading url " + url);
            loadUrl(url);
        } catch (Throwable e) {
            String reason = Constants.MOBFOX_BANNER;
            if (e.getLocalizedMessage() == null || e.getLocalizedMessage().isEmpty()) {
                reason = reason + " error in load";
            } else {
                reason = reason + e.getLocalizedMessage();
            }
            reportError(this, reason);
            Log.d(Constants.MOBFOX_BANNER, reason);
        }
    }

    private void setDefaultParams() {
        int dev_dnt = 1;
        if (!(O_ANDADVID == null || O_ANDADVID.isEmpty())) {
            dev_dnt = 0;
            this.tagParams.setTagParam("o_andadvid", O_ANDADVID);
        }
        Point nearest = SizeUtils.getNearestFitSupported(new Point(this.adWidth, this.adHeight));
        this.tagParams.setTagParam("adspace_width", nearest.x);
        this.tagParams.setTagParam("adspace_height", nearest.y);
        this.tagParams.setTagParam("s", this.invHash);
        this.tagParams.setTagParam("v", "3.2.7d_" + this.adapterName);
        this.tagParams.setTagParam(TagParams.C_MRID, 1);
        this.tagParams.setTagParam("rt", "android_app");
        this.tagParams.setTagParam("dev_dnt", dev_dnt);
        this.tagParams.setTagParam(TagParams.DEV_JS, 1);
        this.tagParams.setTagParam("sub_bundle_id", Utils.getBundleId(this.context));
    }

    protected void getAdvId() {
        if (O_ANDADVID == null || O_ANDADVID.isEmpty()) {
            new MobFoxAdIdService(new 6(this), this.context).execute();
        }
    }

    public void setListener(Listener newListener) {
        if (newListener == null) {
            this.listener = new EmptyListener(this, null);
        } else {
            this.listener = newListener;
        }
    }

    public void setSecure(boolean secure) {
        if (secure) {
            this.tagParams.setTagParam(TagParams.IMP_SECURE, 1);
        } else {
            this.tagParams.setTagParam(TagParams.IMP_SECURE, 0);
        }
    }

    public void setAdapter(String name) {
        if (name != null && !name.isEmpty()) {
            this.adapterName = name;
        }
    }
}
