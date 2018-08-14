package com.appnext.ads.fullscreen;

import android.content.Context;
import android.content.Intent;
import com.appnext.ads.AdsError;
import com.appnext.ads.C0893a;
import com.appnext.base.p023b.C1042c;
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
import com.appnext.core.callbacks.OnVideoEnded;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.File;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONObject;

public abstract class Video extends Ad {
    protected static final int FULL_SCREEN_VIDEO = 1;
    protected static final int REWARDED_VIDEO = 2;
    protected static final String TID = "301";
    protected static final String VID = "2.2.5.468";
    public static final String VIDEO_LENGTH_LONG = "30";
    public static final String VIDEO_LENGTH_SHORT = "15";
    private static boolean cacheVideo = true;
    protected static Video currentAd;
    private static boolean init = false;
    private static boolean streamingEnable = false;
    protected boolean fq_status = false;
    protected long rnd;
    private int rollCaptionTime = -2;
    private boolean showCta = true;
    private int type;
    private OnAdError userOnAdError;
    private OnVideoEnded videoEnded;
    private String videoLength = "15";

    class C09101 implements C0909a {
        final /* synthetic */ Video eH;

        C09101(Video video) {
            this.eH = video;
        }

        public void mo1968a(HashMap<String, Object> hashMap) {
            C1134l.db().m2371c(Integer.parseInt(this.eH.getConfig().get("banner_expiration_time")));
        }

        public void error(String str) {
            C1134l.db().m2371c(Integer.parseInt(this.eH.getConfig().get("banner_expiration_time")));
        }
    }

    class C09112 implements Runnable {
        final /* synthetic */ Video eH;

        C09112(Video video) {
            this.eH = video;
        }

        public void run() {
            try {
                String a = C1128g.m2336a("https://admin.appnext.com/AdminService.asmx/checkA?z=" + this.eH.getPlacementID() + "&callback=", null);
                Video.fq = new JSONObject(a.substring(a.indexOf("(") + 1, a.lastIndexOf(")"))).getBoolean("status");
                this.eH.fq_status = Video.fq;
                C1128g.m2333W("fq " + this.eH.fq_status);
                Video.checked_fq = true;
            } catch (Throwable th) {
                C1128g.m2351c(th);
            }
        }
    }

    class C09123 implements Runnable {
        final /* synthetic */ Video eH;

        C09123(Video video) {
            this.eH = video;
        }

        public void run() {
            if (!Video.init) {
                Video.init = true;
                C1128g.m2348b(new File(this.eH.context.getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo + "tmp/"));
            }
        }
    }

    class C09134 implements OnAdError {
        final /* synthetic */ Video eH;

        C09134(Video video) {
            this.eH = video;
        }

        public void adError(String str) {
            String str2 = "";
            Object obj = -1;
            switch (str.hashCode()) {
                case -2026653947:
                    if (str.equals(AppnextError.INTERNAL_ERROR)) {
                        int i = 1;
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
            C1128g.m2341a(this.eH.getTID(), this.eH.getVID(), this.eH.getAUID(), this.eH.getPlacementID(), this.eH.getSessionId(), str2, this.eH.type == 1 ? "fullscreen" : "rewarded", "", "");
            if (this.eH.userOnAdError != null) {
                this.eH.userOnAdError.adError(str);
            }
        }
    }

    class C09155 implements C0914a {
        final /* synthetic */ Video eH;

        C09155(Video video) {
            this.eH = video;
        }

        public <T> void mo1971a(T t) {
            AppnextAd b = C0920b.ag().m1909b(this.eH.context, this.eH);
            if (this.eH.getOnAdLoadedCallback() == null) {
                return;
            }
            if (b != null) {
                this.eH.getOnAdLoadedCallback().adLoaded(b.getBannerID());
            } else if (this.eH.getOnAdErrorCallback() != null) {
                this.eH.getOnAdErrorCallback().adError(AppnextError.NO_ADS);
            }
        }

        public void error(String str) {
            if (this.eH.getOnAdErrorCallback() != null) {
                this.eH.getOnAdErrorCallback().adError(str);
            }
        }
    }

    public Video(Context context, int i, String str) {
        super(context, str);
        this.type = i;
        this.rnd = (long) new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
        init();
    }

    public Video(Context context, int i, String str, VideoConfig videoConfig) {
        super(context, str);
        this.type = i;
        this.rnd = (long) new Random(System.currentTimeMillis()).nextInt();
        init();
        if (videoConfig != null) {
            setPostback(videoConfig.getPostback());
            setCategories(videoConfig.getCategories());
            setOrientation(videoConfig.getOrientation());
            if (videoConfig.ap()) {
                setShowCta(videoConfig.isShowCta());
            }
            setRollCaptionTime(videoConfig.getRollCaptionTime());
            setVideoLength(videoConfig.getVideoLength());
            if (videoConfig.ao()) {
                setMute(videoConfig.getMute());
            }
            setMinVideoLength(videoConfig.getMinVideoLength());
            setMaxVideoLength(videoConfig.getMaxVideoLength());
        }
    }

    private void init() {
        getConfig().m1920a(new C09101(this));
        if (checked_fq) {
            this.fq_status = Ad.fq;
        } else {
            new Thread(new C09112(this)).start();
        }
        new Thread(new C09123(this)).start();
        super.setOnAdErrorCallback(new C09134(this));
    }

    public void showAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (C1128g.m2354g(this.context, "android.permission.INTERNET")) {
            int aP = C1128g.aP(getConfig().get("min_internet_connection_video"));
            int aP2 = C1128g.aP(C1128g.m2361x(this.context));
            if (aP2 == -1) {
                if (getOnAdErrorCallback() != null) {
                    getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
                }
            } else if (aP2 >= aP) {
                C1128g.m2341a(getTID(), getVID(), getAUID(), getPlacementID(), getSessionId(), C0893a.ch, this.type == 1 ? "fullscreen" : "rewarded", "", "");
                if (C0920b.ag().m1913g(this)) {
                    Intent intent = new Intent(this.context, FullscreenActivity.class);
                    intent.setFlags(ErrorDialogData.BINDER_CRASH);
                    intent.putExtra("id", getPlacementID());
                    intent.putExtra("type", this.type);
                    currentAd = this;
                    this.context.startActivity(intent);
                    return;
                }
                if (getOnAdErrorCallback() != null) {
                    getOnAdErrorCallback().adError(AdsError.AD_NOT_READY);
                }
                C0920b.ag().m1868a(this.context, (Ad) this, getPlacementID(), null);
                C1128g.m2341a(getTID(), getVID(), getAUID(), getPlacementID(), getSessionId(), C0893a.AD_NOT_READY, this.type == 1 ? "fullscreen" : "rewarded", "", "");
            } else if (getOnAdErrorCallback() != null) {
                getOnAdErrorCallback().adError(AppnextError.SLOW_CONNECTION);
            }
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    public void loadAd() {
        if (getPlacementID().equals("")) {
            throw new IllegalArgumentException("Placement ID cannot be empty");
        } else if (C1128g.m2354g(this.context, "android.permission.INTERNET")) {
            C0920b.ag().m1868a(this.context, (Ad) this, getPlacementID(), new C09155(this));
        } else if (getOnAdErrorCallback() != null) {
            getOnAdErrorCallback().adError(AppnextError.CONNECTION_ERROR);
        }
    }

    public void getECPM(final OnECPMLoaded onECPMLoaded) {
        if (onECPMLoaded == null) {
            throw new IllegalArgumentException("Callback cannot be null");
        }
        C0920b.ag().m1869a(this.context, this, getPlacementID(), new C0914a(this) {
            final /* synthetic */ Video eH;

            public <T> void mo1971a(T t) {
                AppnextAd b = C0920b.ag().m1909b(this.eH.context, this.eH);
                if (b != null) {
                    onECPMLoaded.ecpm(new ECPM(b.getECPM(), b.getPPR(), b.getBannerID()));
                } else {
                    onECPMLoaded.error(AppnextError.NO_ADS);
                }
            }

            public void error(String str) {
                onECPMLoaded.error(str);
            }
        }, false);
    }

    protected C0921q getConfig() {
        if (this.type == 2) {
            return C0940f.al();
        }
        if (this.type == 1) {
            return C0922c.aj();
        }
        return null;
    }

    public boolean isAdLoaded() {
        if (getPlacementID().equals("")) {
            return false;
        }
        return C0920b.ag().m1913g(this);
    }

    public OnVideoEnded getOnVideoEndedCallback() {
        return this.videoEnded;
    }

    public void setOnVideoEndedCallback(OnVideoEnded onVideoEnded) {
        this.videoEnded = onVideoEnded;
    }

    public String getVideoLength() {
        return this.videoLength;
    }

    public void setVideoLength(String str) {
        if (str.equals("15") || str.equals("30")) {
            this.videoLength = str;
            return;
        }
        throw new IllegalArgumentException("Wrong video length");
    }

    public static void setCacheVideo(boolean z) {
        cacheVideo = z;
    }

    public static boolean getCacheVideo() {
        return cacheVideo;
    }

    public static void setStreamingMode(boolean z) {
        streamingEnable = z;
    }

    public static boolean isStreamingModeEnabled() {
        return streamingEnable;
    }

    public void setOnAdErrorCallback(OnAdError onAdError) {
        this.userOnAdError = onAdError;
    }

    public OnAdError getOnAdErrorCallback() {
        return super.getOnAdErrorCallback();
    }

    public int getRollCaptionTime() {
        return this.rollCaptionTime;
    }

    public void setRollCaptionTime(int i) {
        if (i == -1 || (i >= 3 && i <= 10)) {
            this.rollCaptionTime = i;
        }
    }

    public void setParams(String str, String str2) {
        getConfig().m1921b(str, str2);
    }

    public boolean isShowCta() {
        return this.showCta;
    }

    public void setShowCta(boolean z) {
        this.showCta = z;
    }

    public String getVID() {
        return "2.2.5.468";
    }

    public String getTID() {
        return TID;
    }

    protected String getSessionId() {
        return super.getSessionId();
    }

    public void destroy() {
        super.destroy();
        this.videoEnded = null;
        try {
            C1128g.m2348b(new File(this.context.getFilesDir().getAbsolutePath() + C1042c.jn + C1042c.jo + "tmp/vid" + this.rnd + BridgeUtil.SPLIT_MARK));
        } catch (Throwable th) {
        }
    }

    protected void finalize() throws Throwable {
        try {
            destroy();
        } catch (Throwable th) {
        } finally {
            super.finalize();
        }
    }
}
