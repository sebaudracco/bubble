package com.appnext.ads.interstitial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.appnext.ads.AdsError;
import com.appnext.ads.C0893a;
import com.appnext.core.Ad;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C0919c.C0914a;
import com.appnext.core.C0921q;
import com.appnext.core.C0921q.C0909a;
import com.appnext.core.C1128g;
import com.appnext.core.C1134l;
import com.appnext.core.ECPM;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnECPMLoaded;
import com.appnext.core.webview.AppnextWebView;
import com.appnext.core.webview.C0978a;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;
import org.telegram.tgnet.ConnectionsManager;

public class Interstitial extends Ad {
    private static final String JS_URL = "https://cdn.appnext.com/tools/sdk/interstitial/v69/script.min.js";
    protected static final String TID = "301";
    public static final String TYPE_MANAGED = "managed";
    public static final String TYPE_STATIC = "static";
    public static final String TYPE_VIDEO = "video";
    protected static final String VID = "2.2.5.468";
    protected static Interstitial currentAd;
    private boolean autoPlay = true;
    private String buttonColor = "";
    private boolean calledShow = false;
    private Boolean canClose;
    private boolean configLoaded = false;
    private String creativeType = TYPE_MANAGED;
    protected boolean fq_status = false;
    private boolean setAutoPlay = false;
    private boolean setCanClose = false;
    private String skipText = "";
    private String titleText = "";
    private OnAdError userOnAdError;

    class C09551 implements Runnable {
        final /* synthetic */ Interstitial eJ;

        C09551(Interstitial interstitial) {
            this.eJ = interstitial;
        }

        public void run() {
            try {
                String a = C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/checkA?z=" + this.eJ.getPlacementID() + "&callback=", null);
                Interstitial.fq = new JSONObject(a.substring(a.indexOf("(") + 1, a.lastIndexOf(")"))).getBoolean("status");
                this.eJ.fq_status = Interstitial.fq;
                C1128g.m2333W("fq " + this.eJ.fq_status);
                Interstitial.checked_fq = true;
            } catch (Throwable th) {
                C1128g.m2351c(th);
            }
        }
    }

    class C09562 implements OnAdError {
        final /* synthetic */ Interstitial eJ;

        C09562(Interstitial interstitial) {
            this.eJ = interstitial;
        }

        public void adError(String str) {
            String str2 = "";
            Object obj = -1;
            switch (str.hashCode()) {
                case -2026653947:
                    if (str.equals(AppnextError.INTERNAL_ERROR)) {
                        obj = 1;
                        break;
                    }
                    break;
                case -1958363695:
                    if (str.equals(AppnextError.NO_ADS)) {
                        obj = 2;
                        break;
                    }
                    break;
                case -1477010874:
                    if (str.equals(AppnextError.CONNECTION_ERROR)) {
                        obj = null;
                        break;
                    }
                    break;
                case -507110949:
                    if (str.equals(AppnextError.NO_MARKET)) {
                        obj = 3;
                        break;
                    }
                    break;
                case 297538105:
                    if (str.equals(AdsError.AD_NOT_READY)) {
                        obj = 6;
                        break;
                    }
                    break;
                case 350741825:
                    if (str.equals("Timeout")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 844170097:
                    if (str.equals(AppnextError.SLOW_CONNECTION)) {
                        obj = 4;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    str2 = C0893a.cy;
                    break;
                case 1:
                    str2 = C0893a.cC;
                    break;
                case 2:
                    str2 = C0893a.cB;
                    break;
                case 3:
                    str2 = C0893a.cD;
                    break;
                case 4:
                    str2 = C0893a.cz;
                    break;
                case 5:
                    str2 = C0893a.cE;
                    break;
                case 6:
                    str2 = C0893a.cA;
                    break;
            }
            C1128g.m2341a(this.eJ.getTID(), this.eJ.getVID(), this.eJ.getAUID(), this.eJ.getPlacementID(), this.eJ.getSessionId(), str2, "current_interstitial", "", "");
            if (this.eJ.userOnAdError != null) {
                this.eJ.userOnAdError.adError(str);
            }
        }
    }

    class C09593 implements Runnable {
        final /* synthetic */ Interstitial eJ;

        class C09571 implements Runnable {
            final /* synthetic */ C09593 eK;

            C09571(C09593 c09593) {
                this.eK = c09593;
            }

            public void run() {
                if (this.eK.eJ.getOnAdErrorCallback() != null) {
                    this.eK.eJ.getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
                }
            }
        }

        class C09582 implements C0914a {
            final /* synthetic */ C09593 eK;

            C09582(C09593 c09593) {
                this.eK = c09593;
            }

            public <T> void mo1971a(T t) {
                AppnextAd appnextAd = null;
                try {
                    appnextAd = C0977a.au().m2017a(this.eK.eJ.context, (ArrayList) t, this.eK.eJ.getCreative(), this.eK.eJ);
                } catch (Throwable th) {
                    if (this.eK.eJ.getOnAdErrorCallback() != null) {
                        this.eK.eJ.getOnAdErrorCallback().adError(AppnextError.NO_ADS);
                    }
                }
                if (appnextAd != null) {
                    if (this.eK.eJ.getOnAdLoadedCallback() != null) {
                        this.eK.eJ.getOnAdLoadedCallback().adLoaded(appnextAd.getBannerID());
                    }
                } else if (this.eK.eJ.getOnAdErrorCallback() != null) {
                    this.eK.eJ.getOnAdErrorCallback().adError(AppnextError.NO_ADS);
                }
            }

            public void error(String str) {
                if (this.eK.eJ.getOnAdErrorCallback() != null) {
                    this.eK.eJ.getOnAdErrorCallback().adError(str);
                }
            }
        }

        C09593(Interstitial interstitial) {
            this.eJ = interstitial;
        }

        public void run() {
            try {
                if (this.eJ.context.checkPermission("android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) != 0) {
                    C1128g.m2336a("http://www.appnext.com/myid.html", null);
                } else {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.eJ.context.getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                        throw new IOException();
                    }
                }
                C0977a.au().m2020a(this.eJ.context, this.eJ, this.eJ.getPlacementID(), new C09582(this), this.eJ.getCreative());
            } catch (Throwable th) {
                new Handler(Looper.getMainLooper()).post(new C09571(this));
            }
        }
    }

    class C09615 implements C0909a {
        final /* synthetic */ Interstitial eJ;

        C09615(Interstitial interstitial) {
            this.eJ = interstitial;
        }

        public void mo1968a(HashMap<String, Object> hashMap) {
            this.eJ.configLoaded = true;
            C1134l.db().m2371c(Integer.parseInt(this.eJ.getConfig().get("banner_expiration_time")));
            if (this.eJ.calledShow) {
                this.eJ.showAd();
            }
        }

        public void error(String str) {
            this.eJ.configLoaded = true;
            C1134l.db().m2371c(Integer.parseInt(this.eJ.getConfig().get("banner_expiration_time")));
            if (this.eJ.calledShow) {
                this.eJ.showAd();
            }
        }
    }

    public Interstitial(Context context, String str) {
        super(context, str);
        init();
    }

    public Interstitial(Context context, String str, InterstitialConfig interstitialConfig) {
        super(context, str);
        init();
        if (interstitialConfig != null) {
            setPostback(interstitialConfig.getPostback());
            setCategories(interstitialConfig.getCategories());
            setButtonColor(interstitialConfig.getButtonColor());
            if (interstitialConfig.aw()) {
                setBackButtonCanClose(interstitialConfig.isBackButtonCanClose());
            }
            setSkipText(interstitialConfig.getSkipText());
            if (interstitialConfig.av()) {
                setAutoPlay(interstitialConfig.isAutoPlay());
            }
            setCreativeType(interstitialConfig.getCreativeType());
            setOrientation(interstitialConfig.getOrientation());
            if (interstitialConfig.ao()) {
                setMute(interstitialConfig.getMute());
            }
            setMinVideoLength(interstitialConfig.getMinVideoLength());
            setMaxVideoLength(interstitialConfig.getMaxVideoLength());
        }
    }

    private void init() {
        loadConfig();
        AppnextWebView.m2400D(this.context).m2410a(getPageUrl(), null);
        if (checked_fq) {
            this.fq_status = Ad.fq;
        } else {
            new Thread(new C09551(this)).start();
        }
        super.setOnAdErrorCallback(new C09562(this));
    }

    public void loadAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (C1128g.m2354g(this.context, "android.permission.INTERNET")) {
            new Thread(new C09593(this)).start();
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    public void showAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (C1128g.m2354g(this.context, "android.permission.INTERNET")) {
            int aP = C1128g.aP(getConfig().get("min_internet_connection"));
            int aP2 = C1128g.aP(C1128g.m2361x(this.context));
            if (aP2 == -1) {
                if (getOnAdErrorCallback() != null) {
                    getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
                }
            } else if (aP2 >= aP) {
                currentAd = this;
                Intent activityIntent = getActivityIntent();
                if (activityIntent == null) {
                    throw new IllegalArgumentException("null intent");
                }
                this.context.startActivity(activityIntent);
            } else if (getOnAdErrorCallback() != null) {
                getOnAdErrorCallback().adError(AppnextError.SLOW_CONNECTION);
            }
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    public void getECPM(final OnECPMLoaded onECPMLoaded) {
        if (onECPMLoaded == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        C0977a.au().m1869a(this.context, this, getPlacementID(), new C0914a(this) {
            final /* synthetic */ Interstitial eJ;

            public <T> void mo1971a(T t) {
                AppnextAd a = C0977a.au().m2017a(this.eJ.context, (ArrayList) t, this.eJ.getCreative(), this.eJ);
                if (a != null) {
                    onECPMLoaded.ecpm(new ECPM(a.getECPM(), a.getPPR(), a.getBannerID()));
                } else {
                    onECPMLoaded.error(AppnextError.NO_ADS);
                }
            }

            public void error(String str) {
                onECPMLoaded.error(str);
            }
        }, false);
    }

    private String getCreative() {
        int aP = C1128g.aP(getConfig().get("min_internet_connection"));
        int aP2 = C1128g.aP(getConfig().get("min_internet_connection_video"));
        int aP3 = C1128g.aP(C1128g.m2361x(this.context));
        if (aP3 < aP || aP3 >= aP2) {
            return getCreativeType();
        }
        return "static";
    }

    protected Intent getActivityIntent() {
        Intent intent = new Intent(this.context, InterstitialActivity.class);
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        intent.addFlags(ConnectionsManager.FileTypeFile);
        intent.putExtra("id", getPlacementID());
        if (this.setAutoPlay) {
            intent.putExtra("auto_play", this.autoPlay);
        }
        if (this.setCanClose) {
            intent.putExtra("can_close", isBackButtonCanClose());
        }
        if (this.setMeute) {
            intent.putExtra("mute", getMute());
        }
        intent.putExtra("cat", getCategories());
        intent.putExtra("pbk", getPostback());
        intent.putExtra("b_color", getButtonColor());
        intent.putExtra("skip_title", getSkipText());
        intent.putExtra("creative", getCreative());
        return intent;
    }

    public boolean isAdLoaded() {
        return !getPlacementID().equals("") && C0977a.au().m2029e(this);
    }

    protected C0921q getConfig() {
        return C0980c.ax();
    }

    private void loadConfig() {
        getConfig().m1920a(new C09615(this));
    }

    public void setOnAdErrorCallback(OnAdError onAdError) {
        this.userOnAdError = onAdError;
    }

    public OnAdError getOnAdErrorCallback() {
        return super.getOnAdErrorCallback();
    }

    public void setCreativeType(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Wrong creative type");
        } else if (str.equals(TYPE_MANAGED) || str.equals("static") || str.equals("video")) {
            this.creativeType = str;
        } else {
            throw new IllegalArgumentException("Wrong creative type");
        }
    }

    public String getCreativeType() {
        return this.creativeType;
    }

    public void setBackButtonCanClose(boolean z) {
        this.setCanClose = true;
        this.canClose = Boolean.valueOf(z);
    }

    public boolean isBackButtonCanClose() {
        return this.canClose == null ? false : this.canClose.booleanValue();
    }

    public boolean isAutoPlay() {
        return this.autoPlay;
    }

    public void setAutoPlay(boolean z) {
        this.setAutoPlay = true;
        this.autoPlay = z;
    }

    public void setSkipText(String str) {
        if (str == null) {
            str = "";
        }
        this.skipText = str;
    }

    public String getSkipText() {
        return this.skipText;
    }

    public String getButtonColor() {
        return this.buttonColor;
    }

    public void setButtonColor(String str) {
        if (str == null || str.equals("")) {
            this.buttonColor = "";
            return;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            Color.parseColor(str);
            this.buttonColor = str;
        } catch (Throwable th) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Unknown color");
        }
    }

    private boolean hasVideo(AppnextAd appnextAd) {
        return (appnextAd.getVideoUrl().equals("") && appnextAd.getVideoUrlHigh().equals("") && appnextAd.getVideoUrl30Sec().equals("") && appnextAd.getVideoUrlHigh30Sec().equals("")) ? false : true;
    }

    public void setParams(String str, String str2) {
        getConfig().m1921b(str, str2);
    }

    protected String getSessionId() {
        return super.getSessionId();
    }

    protected String getPageUrl() {
        return JS_URL;
    }

    protected C0978a getFallback() {
        return new C0979b();
    }

    public String getVID() {
        return "2.2.5.468";
    }

    public String getTID() {
        return TID;
    }

    public String getAUID() {
        return "600";
    }

    public void destroy() {
        super.destroy();
        currentAd = null;
    }
}
