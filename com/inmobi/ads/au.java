package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.AdContainer.C2892a;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.NativeStrandVideoView.OnQuartileCompletedListener.Quartile;
import com.inmobi.ads.NativeV2Asset.AssetActionOnClick;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.ads.ai.C2898b;
import com.inmobi.ads.ai.C3003a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.integralads.avid.library.inmobi.session.C3327e;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

@TargetApi(15)
/* compiled from: NativeV2VideoAdContainer */
public class au extends ai {
    private static final String f7187i = au.class.getSimpleName();
    private WeakReference<View> f7188j;
    private ai f7189k;
    private final C2892a f7190l = new C30161(this);
    private final C3018b f7191m = new C30192(this);

    /* compiled from: NativeV2VideoAdContainer */
    class C30161 implements C2892a {
        final /* synthetic */ au f7176a;

        C30161(au auVar) {
            this.f7176a = auVar;
        }

        public void mo6201a() {
            Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "onAdScreenDisplayFailed");
            C2898b i = this.f7176a.m9337i();
            if (i != null) {
                i.mo6089a();
            }
        }

        public void mo6202a(@NonNull Object obj) {
            if (this.f7176a.m9343o() != null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "onAdScreenDisplayed");
                aw awVar = (aw) obj;
                awVar.m9027v().put("didRequestFullScreen", Boolean.valueOf(true));
                awVar.m9027v().put("isFullScreen", Boolean.valueOf(true));
                awVar.m9027v().put("shouldAutoPlay", Boolean.valueOf(true));
                if (awVar.m9014i() != null) {
                    awVar.m9014i().m9027v().put("didRequestFullScreen", Boolean.valueOf(true));
                    awVar.m9014i().m9027v().put("isFullScreen", Boolean.valueOf(true));
                    awVar.m9014i().m9027v().put("shouldAutoPlay", Boolean.valueOf(true));
                }
                if (PlacementType.PLACEMENT_TYPE_INLINE == this.f7176a.m9330d()) {
                    this.f7176a.getViewableAd().mo6227a(AdEvent.AD_EVENT_ENTER_FULLSCREEN);
                    awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_FULLSCREEN, this.f7176a.m9414i(awVar));
                }
                C2898b i = this.f7176a.m9337i();
                if (i != null) {
                    i.mo6092b();
                }
            }
        }

        public void mo6203b(@NonNull Object obj) {
            Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "onAdScreenDismissed");
            aw awVar = (aw) obj;
            awVar.m9027v().put("didRequestFullScreen", Boolean.valueOf(false));
            awVar.m9027v().put("isFullScreen", Boolean.valueOf(false));
            if (awVar.m9014i() != null) {
                awVar.m9014i().m9027v().put("didRequestFullScreen", Boolean.valueOf(false));
                awVar.m9014i().m9027v().put("isFullScreen", Boolean.valueOf(false));
                awVar.m9014i().m8994a(null);
            }
            awVar.m8994a(null);
            if (this.f7176a.m9330d() == PlacementType.PLACEMENT_TYPE_INLINE) {
                this.f7176a.getViewableAd().mo6227a(AdEvent.AD_EVENT_EXIT_FULLSCREEN);
                if (this.f7176a.m9336h() != null) {
                    this.f7176a.m9336h().getViewableAd().mo6227a(AdEvent.AD_EVENT_VIDEO_RESUME_INLINE);
                }
                awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN, this.f7176a.m9414i(awVar));
            } else {
                this.f7176a.getViewableAd().mo6227a(AdEvent.AD_EVENT_CLOSED);
            }
            C2898b i = this.f7176a.m9337i();
            if (i != null) {
                i.mo6095e();
            }
        }
    }

    /* compiled from: NativeV2VideoAdContainer */
    interface C3018b {
        void mo6204a(NativeStrandVideoView nativeStrandVideoView, boolean z);
    }

    /* compiled from: NativeV2VideoAdContainer */
    class C30192 implements C3018b {
        final /* synthetic */ au f7181a;

        C30192(au auVar) {
            this.f7181a = auVar;
        }

        public void mo6204a(final NativeStrandVideoView nativeStrandVideoView, final boolean z) {
            final aw awVar = (aw) nativeStrandVideoView.getTag();
            if (awVar != null) {
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ C30192 f7180d;

                    public void run() {
                        if (!((Boolean) awVar.m9027v().get("isFullScreen")).booleanValue()) {
                            awVar.m9027v().put("visible", Boolean.valueOf(z));
                            if (z) {
                                awVar.m9027v().put("lastVisibleTimestamp", Long.valueOf(SystemClock.uptimeMillis()));
                                if (this.f7180d.f7181a.e) {
                                    nativeStrandVideoView.pause();
                                } else if (1 == nativeStrandVideoView.getState()) {
                                    nativeStrandVideoView.getMediaPlayer().m9283b(3);
                                } else if (2 == nativeStrandVideoView.getState() || (4 == nativeStrandVideoView.getState() && !this.f7180d.f7181a.e)) {
                                    nativeStrandVideoView.start();
                                }
                            } else if (3 == nativeStrandVideoView.getState()) {
                                nativeStrandVideoView.pause();
                            }
                        }
                    }
                });
            }
        }
    }

    /* compiled from: NativeV2VideoAdContainer */
    final class C3021a extends Thread {
        final /* synthetic */ au f7185a;
        private WeakReference<au> f7186b;

        public C3021a(au auVar, @NonNull au auVar2) {
            this.f7185a = auVar;
            this.f7186b = new WeakReference(auVar2);
        }

        public void run() {
            if (this.f7185a.m9343o() == null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "Activity is null. No end card will be created.");
                return;
            }
            ai aiVar = (au) this.f7186b.get();
            if (aiVar != null && !aiVar.mo6176c()) {
                try {
                    NativeV2DataModel k = aiVar.m9339k();
                    if (this.f7185a.m9343o() == null || k.m9086f().length() == 0) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "No companion ads");
                        return;
                    }
                    Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "Building data model for companion ads ...");
                    JSONObject a = k.m9075a(0);
                    if (a != null) {
                        NativeV2DataModel nativeV2DataModel = new NativeV2DataModel(this.f7185a.m9330d(), a, k, null, null);
                        if (nativeV2DataModel.m9094n()) {
                            ai a2 = C3003a.m9299a(this.f7185a.m9343o(), new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), nativeV2DataModel, this.f7185a.m9334f(), this.f7185a.m9335g(), null);
                            Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "End-card container built successfully ...");
                            a2.m9320a(aiVar);
                            aiVar.m9427d(a2);
                            return;
                        }
                        Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "Invalid data model for end-card container! End card will not be shown ...");
                    }
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, au.f7187i, "Encountered unexpected error in EndCardBuilder: " + e.getMessage());
                    Logger.m10359a(InternalLogLevel.INTERNAL, "InMobi", "SDK encountered error while inflating ad");
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
        }
    }

    au(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull NativeV2DataModel nativeV2DataModel, @NonNull String str, @NonNull String str2, @Nullable Set<bi> set) {
        super(context, renderingProperties, nativeV2DataModel, str, str2, set);
        this.a = nativeV2DataModel;
    }

    public void destroy() {
        if (!mo6176c()) {
            if (getVideoContainerView() != null) {
                NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) getVideoContainerView();
                if (nativeStrandVideoWrapper != null) {
                    nativeStrandVideoWrapper.getVideoView().m8912a(true);
                }
            }
            if (this.f7189k != null) {
                this.f7189k.destroy();
                this.f7189k = null;
            }
            super.destroy();
        }
    }

    void mo6205a(View view) {
        if (!m9341m() && !mo6176c() && (view instanceof NativeStrandVideoView)) {
            NativeStrandVideoView nativeStrandVideoView = (NativeStrandVideoView) view;
            this.d = true;
            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "A viewable impression is reported on the video ad.");
            Map hashMap = new HashMap();
            hashMap.put("type", PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().m8613a() ? SchemaSymbols.ATTVAL_INT : "inlban");
            hashMap.put("clientRequestId", m9335g());
            hashMap.put("impId", m9342n());
            C3135c.m10255a().m10280a("ads", "ViewableBeaconFired", hashMap);
            m9413h((aw) nativeStrandVideoView.getTag());
            this.c.mo6227a(AdEvent.AD_EVENT_IMPRESSION_RECORDED);
        }
    }

    private void m9413h(@NonNull aw awVar) {
        if (!((Boolean) awVar.m9027v().get("didImpressionFire")).booleanValue()) {
            List<ah> f = awVar.m9011f();
            Map i = m9414i(awVar);
            List arrayList = new ArrayList();
            for (ah ahVar : f) {
                if (C3000a.TRACKER_EVENT_TYPE_VIDEO_RENDER == ahVar.m9297c()) {
                    if (ahVar.m9295b().startsWith("http")) {
                        awVar.m8996a(ahVar, i);
                    }
                    List<C3000a> list = (List) ahVar.m9293a().get("referencedEvents");
                    for (C3000a a : list) {
                        awVar.m8995a(a, i);
                    }
                }
            }
            if (arrayList.isEmpty()) {
                awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_PLAY, i);
                awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_CREATIVE_VIEW, i);
                awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_RENDER, i);
            }
            awVar.m9027v().put("didImpressionFire", Boolean.valueOf(true));
            if (m9337i() != null) {
                m9337i().mo6093c();
            }
        }
    }

    public void m9427d(@NonNull ai aiVar) {
        this.f7189k = aiVar;
    }

    boolean mo6208x() {
        return PlacementType.PLACEMENT_TYPE_INLINE == m9330d() && m9343o() != null;
    }

    public ViewableAd getViewableAd() {
        Context l = m9340l();
        if (this.c == null && l != null) {
            this.c = new C3089v(l, this, new bu(this));
            if (this.b != null) {
                if (l instanceof Activity) {
                    try {
                        Activity activity = (Activity) l;
                        for (bi biVar : this.b) {
                            switch (biVar.f7267a) {
                                case AD_TRACKER_TYPE_IAS:
                                    C3327e c3327e = (C3327e) biVar.f7268b.get("avidAdSession");
                                    if (c3327e == null) {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Did not find a AVID video ad session; the IAS decorator will not be applied.");
                                        break;
                                    }
                                    this.c = new C3082r(activity, this.c, this, c3327e);
                                    break;
                                case AD_TRACKER_TYPE_MOAT:
                                    this.c = new C3101z(activity, this.c, this, biVar.f7268b);
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Exception occurred while creating the video viewable ad : " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                    }
                } else {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "inlban");
                    hashMap.put("impId", m9334f());
                    C3135c.m10255a().m10280a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.c;
    }

    @NonNull
    public C2892a getFullScreenEventsListener() {
        return this.f7190l;
    }

    @Nullable
    public View getVideoContainerView() {
        return this.f7188j == null ? null : (View) this.f7188j.get();
    }

    C3018b m9434y() {
        return this.f7191m;
    }

    boolean mo6207q() {
        return !this.g;
    }

    protected void mo6206b(@NonNull NativeV2Asset nativeV2Asset) {
        AssetActionOnClick l = nativeV2Asset.m9017l();
        if (AssetActionOnClick.ASSET_ACTION_ON_CLICK_NONE != l) {
            NativeStrandVideoWrapper nativeStrandVideoWrapper;
            switch (l) {
                case ASSET_ACTION_ON_CLICK_REPLAY:
                    try {
                        if (AssetType.ASSET_TYPE_VIDEO != nativeV2Asset.m8986a()) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Action " + AssetActionOnClick.ASSET_ACTION_ON_CLICK_REPLAY + " not valid for asset of type: " + nativeV2Asset.m8986a());
                            return;
                        }
                        nativeStrandVideoWrapper = (NativeStrandVideoWrapper) getVideoContainerView();
                        if (nativeStrandVideoWrapper != null && m9338j() != null) {
                            View j = m9338j();
                            ViewGroup viewGroup = (ViewGroup) j.getParent();
                            if (viewGroup != null) {
                                viewGroup.removeView(j);
                            }
                            nativeStrandVideoWrapper.getVideoView().m8916e();
                            nativeStrandVideoWrapper.getVideoView().start();
                            m9333e(j);
                            return;
                        }
                        return;
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Encountered unexpected error in handling replay action on video: " + e.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in replaying video");
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return;
                    }
                case ASSET_ACTION_ON_CLICK_EXIT:
                    try {
                        m9347s();
                        return;
                    } catch (Throwable e2) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Encountered unexpected error in handling exit action on video: " + e2.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in exiting video");
                        C3135c.m10255a().m10279a(new C3132b(e2));
                        return;
                    }
                case ASSET_ACTION_ON_CLICK_FULLSCREEN:
                    try {
                        if (PlacementType.PLACEMENT_TYPE_INLINE == m9330d()) {
                            nativeStrandVideoWrapper = (NativeStrandVideoWrapper) getVideoContainerView();
                            if (nativeStrandVideoWrapper != null) {
                                NativeStrandVideoView videoView = nativeStrandVideoWrapper.getVideoView();
                                aw awVar = (aw) videoView.getTag();
                                if (videoView.getState() != 1) {
                                    try {
                                        m9425b(awVar, videoView);
                                        return;
                                    } catch (Throwable e22) {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "SDK encountered unexpected error in handling the onVideoRequestedFullScreen event; " + e22.getMessage());
                                        C3135c.m10255a().m10279a(new C3132b(e22));
                                        return;
                                    }
                                }
                                return;
                            }
                            return;
                        }
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Invalid action! Online inline videos can be expanded to fullscreen!");
                        return;
                    } catch (Throwable e222) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Encountered unexpected error in handling fullscreen action on video: " + e222.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in expanding video to fullscreen");
                        C3135c.m10255a().m10279a(new C3132b(e222));
                        return;
                    }
                default:
                    try {
                        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN != m9330d()) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Invalid action! Skipping video is only supported in fullscreen ads");
                            return;
                        } else if (AssetType.ASSET_TYPE_VIDEO != nativeV2Asset.m8986a()) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Action " + AssetActionOnClick.ASSET_ACTION_ON_CLICK_SKIP + " not valid for asset of type: " + nativeV2Asset.m8986a());
                            return;
                        } else {
                            nativeStrandVideoWrapper = (NativeStrandVideoWrapper) getVideoContainerView();
                            if (nativeStrandVideoWrapper != null) {
                                nativeStrandVideoWrapper.getVideoView().m8915d();
                                nativeStrandVideoWrapper.getVideoView().m8914c();
                                m9412c(nativeV2Asset);
                                m9331d(m9338j());
                                return;
                            }
                            return;
                        }
                    } catch (Throwable e2222) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Action " + AssetActionOnClick.ASSET_ACTION_ON_CLICK_SKIP + " not valid for asset of type: " + nativeV2Asset.m8986a());
                        C3135c.m10255a().m10279a(new C3132b(e2222));
                        return;
                    }
            }
        }
    }

    private void m9412c(NativeV2Asset nativeV2Asset) {
        if (this.f7189k != null) {
            try {
                if (m9338j() != null) {
                    try {
                        ViewGroup viewGroup = (ViewGroup) m9338j();
                        View a = this.f7189k.getViewableAd().mo6226a(null, viewGroup, false);
                        if (a != null) {
                            viewGroup.addView(a);
                            a.setClickable(true);
                            m9411a("ads", "EndCardDisplayed", new HashMap());
                            Map i = m9414i((aw) nativeV2Asset);
                            bl g = ((aw) nativeV2Asset).m9441D().mo6223g();
                            if (g != null) {
                                for (ah a2 : g.m9527a(C3000a.TRACKER_EVENT_TYPE_CREATIVE_VIEW)) {
                                    nativeV2Asset.m8996a(a2, i);
                                }
                                return;
                            }
                            return;
                        }
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Could not inflate the end card. Closing the ad");
                        m9347s();
                        return;
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in inflating end card: " + e.getMessage());
                        m9347s();
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return;
                    }
                }
                return;
            } catch (Throwable e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Encountered unexpected error in showEndCard(NativeV2Asset) method: " + e2.getMessage());
                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "SDK encountered unexpected error in showing end card");
                C3135c.m10255a().m10279a(new C3132b(e2));
                return;
            }
        }
        m9347s();
        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "End card container is null; end card will not be shown");
        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "Failed to show end card");
    }

    void m9422a(@NonNull aw awVar, @NonNull NativeStrandVideoView nativeStrandVideoView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "onVideoViewCreated");
        NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) nativeStrandVideoView.getParent();
        this.f7188j = new WeakReference(nativeStrandVideoWrapper);
        NativeStrandVideoController mediaController = nativeStrandVideoWrapper.getVideoView().getMediaController();
        if (mediaController != null) {
            mediaController.setVideoAd(this);
        }
    }

    void m9425b(@NonNull aw awVar, @NonNull NativeStrandVideoView nativeStrandVideoView) {
        if (!mo6176c() && m9332e() != null && !((Boolean) awVar.m9027v().get("didRequestFullScreen")).booleanValue()) {
            awVar.m9027v().put("didRequestFullScreen", Boolean.valueOf(true));
            awVar.m9027v().put("seekPosition", Integer.valueOf(nativeStrandVideoView.getCurrentPosition()));
            awVar.m9027v().put("lastMediaVolume", Integer.valueOf(nativeStrandVideoView.getVolume()));
            nativeStrandVideoView.getMediaPlayer().pause();
            nativeStrandVideoView.getMediaPlayer().m9281a(4);
            awVar.m9027v().put("isFullScreen", Boolean.valueOf(true));
            awVar.m9027v().put("seekPosition", Integer.valueOf(nativeStrandVideoView.getMediaPlayer().getCurrentPosition()));
            m9344p();
        }
    }

    public void m9419a(@NonNull aw awVar) {
        if (!mo6176c() && ((Boolean) awVar.m9027v().get("didRequestFullScreen")).booleanValue()) {
            awVar.m9027v().put("didRequestFullScreen", Boolean.valueOf(false));
            if (awVar.m9014i() != null) {
                awVar.m9014i().m9027v().put("didRequestFullScreen", Boolean.valueOf(false));
            }
            m9347s();
        }
    }

    void m9420a(aw awVar, int i) {
        if (!mo6176c()) {
            Map hashMap = new HashMap();
            hashMap.put("errorCode", i + "");
            hashMap.put("reason", "Video Player Error");
            hashMap.put("url", awVar.m9441D().mo6219b());
            m9411a("ads", "VideoError", hashMap);
            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Moat onVideoError + " + i);
            awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_ERROR, m9414i(awVar));
            this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_ERROR);
        }
    }

    void m9435z() {
        this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_PREPARED);
    }

    void m9424b(aw awVar) {
        if (!mo6176c()) {
            Map hashMap;
            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "onVideoPlayed");
            if (PlacementType.PLACEMENT_TYPE_INLINE == m9330d()) {
                if (((Integer) awVar.m9027v().get("currentMediaVolume")).intValue() > 0 && ((Integer) awVar.m9027v().get("lastMediaVolume")).intValue() == 0) {
                    m9430f(awVar);
                }
                if (((Integer) awVar.m9027v().get("currentMediaVolume")).intValue() == 0 && ((Integer) awVar.m9027v().get("lastMediaVolume")).intValue() > 0) {
                    m9429e(awVar);
                }
            }
            if (!((Boolean) awVar.m9027v().get("didStartPlaying")).booleanValue()) {
                awVar.m9027v().put("didStartPlaying", Boolean.valueOf(true));
                getViewableAd().mo6227a(AdEvent.AD_EVENT_VIDEO_PLAYED);
                if (PlacementType.PLACEMENT_TYPE_INLINE == m9330d()) {
                    awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_PLAY, m9414i(awVar));
                }
                try {
                    hashMap = new HashMap();
                    hashMap.put("isCached", SchemaSymbols.ATTVAL_TRUE_1);
                    m9411a("ads", "VideoPlayed", hashMap);
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                }
            }
            if (m9330d() == PlacementType.PLACEMENT_TYPE_INLINE && !((Boolean) awVar.m9027v().get("didImpressionFire")).booleanValue()) {
                awVar.m9027v().put("didImpressionFire", Boolean.valueOf(true));
                awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_RENDER, m9414i(awVar));
                awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_CREATIVE_VIEW, m9414i(awVar));
                this.c.mo6227a(AdEvent.AD_EVENT_IMPRESSION_RECORDED);
                hashMap = new HashMap();
                hashMap.put("type", "inlban");
                hashMap.put("clientRequestId", m9335g());
                hashMap.put("impId", m9342n());
                m9411a("ads", "AdRendered", hashMap);
                this.a.m9079b().m8995a(C3000a.TRACKER_EVENT_TYPE_RENDER, m9414i(awVar));
                ai b = m9324b((ai) this);
                if (b != null) {
                    C2898b i = b.m9337i();
                    if (i != null) {
                        i.mo6093c();
                    }
                }
            }
        }
    }

    void m9426c(aw awVar) {
        if (!mo6176c()) {
            m9326b(m9338j());
            awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_PAUSE, m9414i(awVar));
            this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_PAUSED);
        }
    }

    void m9428d(aw awVar) {
        if (!mo6176c()) {
            m9328c(m9338j());
            awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_RESUME, m9414i(awVar));
            this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_RESUMED);
        }
    }

    void m9429e(aw awVar) {
        if (!mo6176c()) {
            awVar.m9027v().put("lastMediaVolume", Integer.valueOf(0));
            awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_MUTE, m9414i(awVar));
            this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_MUTE);
        }
    }

    void m9430f(aw awVar) {
        if (!mo6176c()) {
            awVar.m9027v().put("lastMediaVolume", Integer.valueOf(15));
            awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_UNMUTE, m9414i(awVar));
            this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_UNMUTE);
        }
    }

    void m9431g(aw awVar) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Video completed; rewards, if any, will be unlocked and end-card displayed");
        if (((Boolean) awVar.m9027v().get("didSignalVideoCompleted")).booleanValue()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Ignoring callback onAdRewardsUnlocked(), as it is only fired after first time video is played.");
        } else {
            m9346r();
        }
        m9411a("ads", "EndCardRequested", new HashMap());
        if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == m9330d()) {
            m9412c((NativeV2Asset) awVar);
        }
    }

    void m9421a(aw awVar, Quartile quartile) {
        if (!mo6176c()) {
            Map hashMap;
            switch (quartile) {
                case Q1:
                    awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_Q1, m9414i(awVar));
                    hashMap = new HashMap();
                    hashMap.put("url", awVar.m9441D().mo6219b());
                    hashMap.put("isCached", SchemaSymbols.ATTVAL_TRUE_1);
                    m9411a("ads", "VideoQ1Completed", hashMap);
                    this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_QUARTILE_1);
                    return;
                case Q2:
                    awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_Q2, m9414i(awVar));
                    hashMap = new HashMap();
                    hashMap.put("url", awVar.m9441D().mo6219b());
                    hashMap.put("isCached", SchemaSymbols.ATTVAL_TRUE_1);
                    m9411a("ads", "VideoQ2Completed", hashMap);
                    this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_QUARTILE_2);
                    return;
                case Q3:
                    awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_Q3, m9414i(awVar));
                    hashMap = new HashMap();
                    hashMap.put("url", awVar.m9441D().mo6219b());
                    hashMap.put("isCached", SchemaSymbols.ATTVAL_TRUE_1);
                    m9411a("ads", "VideoQ3Completed", hashMap);
                    this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_QUARTILE_3);
                    return;
                case Q4:
                    awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_Q4, m9414i(awVar));
                    hashMap = new HashMap();
                    hashMap.put("url", awVar.m9441D().mo6219b());
                    hashMap.put("isCached", SchemaSymbols.ATTVAL_TRUE_1);
                    m9411a("ads", "VideoQ4Completed", hashMap);
                    this.c.mo6227a(AdEvent.AD_EVENT_VIDEO_PLAY_COMPLETED);
                    return;
                default:
                    return;
            }
        }
    }

    private void m9411a(String str, String str2, Map<String, Object> map) {
        try {
            map.put("clientRequestId", m9335g());
            map.put("impId", m9334f());
            C3135c.m10255a().m10280a(str, str2, map);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7187i, "Error in sendTelemetryEvent : (" + e.getMessage() + ")");
        }
    }

    private Map<String, String> m9414i(@NonNull aw awVar) {
        ak akVar = (ak) awVar.m9026u();
        Map<String, String> hashMap = new HashMap(4);
        NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) this.f7188j.get();
        if (nativeStrandVideoWrapper != null) {
            hashMap.put("$MD", String.valueOf((int) Math.round((((double) nativeStrandVideoWrapper.getVideoView().getDuration()) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) / 1000.0d)));
        }
        hashMap.put("[ERRORCODE]", VastErrorCode.MEDIA_PLAY_ERROR.getId().toString());
        hashMap.put("[CONTENTPLAYHEAD]", m9417a(((Integer) awVar.m9027v().get("seekPosition")).intValue()));
        hashMap.put("[CACHEBUSTING]", m9415A());
        hashMap.put("[ASSETURI]", awVar.m9441D().mo6219b());
        hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
        hashMap.put("$LTS", String.valueOf(this.a.m9079b().m9364y()));
        if (akVar != null) {
            hashMap.put("$STS", String.valueOf(akVar.m9364y()));
        }
        return hashMap;
    }

    @VisibleForTesting
    String m9415A() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i == 0) {
            i = (secureRandom.nextInt() & Integer.MAX_VALUE) % 10;
        }
        stringBuilder.append(i);
        for (i = 1; i < 8; i++) {
            stringBuilder.append((secureRandom.nextInt() & Integer.MAX_VALUE) % 10);
        }
        return stringBuilder.toString();
    }

    @VisibleForTesting
    String m9417a(int i) {
        return String.format(Locale.US, "%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toHours((long) i)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) i) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long) i))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) i) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) i))), Long.valueOf(((long) i) - (TimeUnit.MILLISECONDS.toSeconds((long) i) * 1000))});
    }

    void m9416B() {
        new C3021a(this, this).start();
    }
}
