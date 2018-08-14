package com.inmobi.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.inmobi.ads.AdContainer.C2892a;
import com.inmobi.ads.AdContainer.EventType;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.AdUnit.AdMarkupType;
import com.inmobi.ads.NativeV2Asset.AssetActionOnClick;
import com.inmobi.ads.NativeV2Asset.AssetInteractionMode;
import com.inmobi.ads.NativeV2Asset.AssetReferencedCreative;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3153b;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.rendering.InMobiAdActivity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONObject;

/* compiled from: NativeV2AdContainer */
public class ai implements ActivityLifecycleCallbacks, AdContainer {
    private static final String f7112i = ai.class.getSimpleName();
    @NonNull
    private static ai f7113m;
    @NonNull
    protected NativeV2DataModel f7114a;
    @Nullable
    protected Set<bi> f7115b;
    protected ViewableAd f7116c;
    protected boolean f7117d;
    protected boolean f7118e;
    @Nullable
    protected C2898b f7119f;
    boolean f7120g = false;
    @Nullable
    WeakReference<Activity> f7121h;
    @NonNull
    private RenderingProperties f7122j;
    @NonNull
    private final String f7123k;
    @NonNull
    private final String f7124l;
    @NonNull
    private Map<Integer, Integer> f7125n = new HashMap();
    @NonNull
    private List<NativeV2Asset> f7126o = new ArrayList();
    private boolean f7127p = false;
    @NonNull
    private ai f7128q;
    @NonNull
    private WeakReference<Context> f7129r;

    /* compiled from: NativeV2AdContainer */
    interface C2898b {
        void mo6089a();

        void mo6090a(String str, String str2, Map<String, Object> map);

        void mo6091a(Map<String, String> map);

        void mo6092b();

        void mo6093c();

        void mo6094d();

        void mo6095e();

        void mo6096f();
    }

    /* compiled from: NativeV2AdContainer */
    static final class C3003a {
        static ai m9299a(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull NativeV2DataModel nativeV2DataModel, @NonNull String str, @NonNull String str2, @Nullable Set<bi> set) {
            if (!nativeV2DataModel.m9095o().contains(AssetType.ASSET_TYPE_VIDEO)) {
                ai.f7113m = new ai(context, renderingProperties, nativeV2DataModel, str, str2, set);
            } else if (VERSION.SDK_INT >= 15) {
                ai.f7113m = new au(context, renderingProperties, nativeV2DataModel, str, str2, set);
            }
            return ai.f7113m;
        }
    }

    @NonNull
    public /* synthetic */ Object getDataModel() {
        return m9339k();
    }

    ai(@NonNull Context context, @NonNull RenderingProperties renderingProperties, @NonNull NativeV2DataModel nativeV2DataModel, @NonNull String str, @NonNull String str2, @Nullable Set<bi> set) {
        this.f7122j = renderingProperties;
        this.f7114a = nativeV2DataModel;
        this.f7123k = str;
        this.f7124l = str2;
        this.f7128q = this;
        this.f7117d = false;
        this.f7127p = false;
        if (set != null) {
            this.f7115b = new HashSet(set);
        }
        this.f7114a.m9079b().m9361a(System.currentTimeMillis());
        Map c = m9306c(this.f7114a.m9079b());
        mo6174a(EventType.EVENT_TYPE_AD_LOADED, c);
        mo6174a(EventType.EVENT_TYPE_AD_SERVED, c);
        this.f7129r = new WeakReference(context);
        if (context instanceof Activity) {
            ((Activity) context).getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    public void mo6174a(EventType eventType, Map<String, String> map) {
        if (!mo6176c()) {
            switch (eventType) {
                case EVENT_TYPE_FILL_REQUEST:
                    return;
                case EVENT_TYPE_AD_LOADED:
                    m9304b(this.f7114a.m9079b(), (Map) map);
                    return;
                case EVENT_TYPE_AD_SERVED:
                    m9307c(this.f7114a.m9079b(), map);
                    return;
                default:
                    return;
            }
        }
    }

    public void setRequestedScreenOrientation() {
        Activity o = m9343o();
        if (o != null && !mo6176c()) {
            switch (m9339k().m9083c()) {
                case ORIENTATION_PORTRAIT:
                    o.setRequestedOrientation(1);
                    return;
                case ORIENTATION_LANDSCAPE:
                    o.setRequestedOrientation(0);
                    return;
                default:
                    o.setRequestedOrientation(o.getRequestedOrientation());
                    return;
            }
        }
    }

    public RenderingProperties getRenderingProperties() {
        return this.f7122j;
    }

    @Nullable
    public C2892a getFullScreenEventsListener() {
        return null;
    }

    public void mo6175b() {
    }

    public void setExitAnimation(int i) {
    }

    public void mo6173a() {
    }

    public AdMarkupType getMarkupType() {
        return AdMarkupType.AD_MARKUP_TYPE_INM_JSON;
    }

    public PlacementType m9330d() {
        return this.f7122j.m8613a();
    }

    @Nullable
    public Context m9332e() {
        return (Context) this.f7129r.get();
    }

    public String m9334f() {
        return this.f7123k;
    }

    public String m9335g() {
        return this.f7124l;
    }

    @NonNull
    public ai m9336h() {
        return this.f7128q;
    }

    public void m9320a(@NonNull ai aiVar) {
        this.f7128q = aiVar;
    }

    @Nullable
    public C2898b m9337i() {
        return this.f7119f;
    }

    public void m9319a(@NonNull C2898b c2898b) {
        this.f7119f = c2898b;
    }

    @Nullable
    public View m9338j() {
        return this.f7116c == null ? null : this.f7116c.mo6249b();
    }

    private void m9300a(int i, @NonNull ak akVar) {
        if (!mo6176c()) {
            this.f7125n.put(Integer.valueOf(i), Integer.valueOf(1));
            akVar.m9361a(System.currentTimeMillis());
            if (m9341m()) {
                m9308d(akVar, m9306c((NativeV2Asset) akVar));
            } else {
                this.f7126o.add(akVar);
            }
        }
    }

    public ViewableAd getViewableAd() {
        Context l = m9340l();
        if (this.f7116c == null && l != null) {
            this.f7116c = new C3088u(l, this, new bt(this));
            if (this.f7115b != null) {
                if (l instanceof Activity) {
                    try {
                        Activity activity = (Activity) l;
                        for (bi biVar : this.f7115b) {
                            switch (biVar.f7267a) {
                                case AD_TRACKER_TYPE_MOAT:
                                    biVar.f7268b.put("zMoatIID", UUID.randomUUID().toString());
                                    this.f7116c = new C3099y(activity, this.f7116c, biVar.f7268b);
                                    break;
                                default:
                                    break;
                            }
                        }
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Exception occurred while creating the Display viewable ad : " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                    }
                } else {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "inlban");
                    hashMap.put("impId", this.f7123k);
                    C3135c.m10255a().m10280a("ads", "TrackersForService", hashMap);
                }
            }
        }
        return this.f7116c;
    }

    @Nullable
    public View getVideoContainerView() {
        return null;
    }

    @NonNull
    public NativeV2DataModel m9339k() {
        return this.f7114a;
    }

    public boolean mo6176c() {
        return this.f7127p;
    }

    public void destroy() {
        if (!this.f7127p) {
            this.f7127p = true;
            this.f7119f = null;
            this.f7126o.clear();
            if (this.f7116c != null) {
                this.f7116c.mo6229c();
                this.f7116c.mo6230d();
            }
            m9348t();
            this.f7129r.clear();
            if (this.f7121h != null) {
                this.f7121h.clear();
            }
            f7113m = null;
            this.f7114a = null;
        }
    }

    @Nullable
    protected Context m9340l() {
        return PlacementType.PLACEMENT_TYPE_INLINE == m9330d() ? m9332e() : m9343o();
    }

    protected boolean m9341m() {
        return this.f7117d;
    }

    protected String m9342n() {
        return this.f7123k;
    }

    @Nullable
    protected NativeV2Asset m9313a(@Nullable NativeV2DataModel nativeV2DataModel, @NonNull NativeV2Asset nativeV2Asset) {
        if (nativeV2DataModel == null) {
            return null;
        }
        String r = nativeV2Asset.m9023r();
        String s = nativeV2Asset.m9024s();
        if (C3153b.m10394a((Context) this.f7129r.get(), r) || C3153b.m10394a((Context) this.f7129r.get(), s)) {
            return nativeV2Asset;
        }
        String[] split = r.split("\\|");
        NativeV2Asset b = nativeV2DataModel.m9078b(split[0]);
        if (b == null) {
            return m9313a(nativeV2DataModel.m9085e(), nativeV2Asset);
        }
        if (b.equals(nativeV2Asset)) {
            return null;
        }
        if (1 == split.length || 2 == split.length) {
            b.m8992a(AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_LINEAR);
            return b;
        }
        if (split.length > 2) {
            b.m8992a(m9339k().m9069a(split[2]));
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Referenced asset (" + b.m9005c() + ")");
        return b;
    }

    @Nullable
    protected NativeV2Asset m9323b(@Nullable NativeV2DataModel nativeV2DataModel, @NonNull NativeV2Asset nativeV2Asset) {
        if (nativeV2DataModel == null) {
            return null;
        }
        String q = nativeV2Asset.m9022q();
        if (q == null || q.length() == 0) {
            nativeV2Asset.m8988a(AssetActionOnClick.ASSET_ACTION_ON_CLICK_NONE);
            return nativeV2Asset;
        }
        String[] split = q.split("\\|");
        if (1 == split.length) {
            nativeV2Asset.m8988a(m9303b(split[0]));
            return nativeV2Asset;
        }
        NativeV2Asset b = nativeV2DataModel.m9078b(split[0]);
        if (b == null) {
            return m9323b(nativeV2DataModel.m9085e(), nativeV2Asset);
        }
        if (b.equals(nativeV2Asset)) {
            return null;
        }
        b.m8988a(m9303b(split[1]));
        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Referenced asset (" + b.m9005c() + ")");
        return b;
    }

    private AssetActionOnClick m9303b(String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -934641255:
                if (trim.equals("reload")) {
                    obj = 3;
                    break;
                }
                break;
            case -934524953:
                if (trim.equals("replay")) {
                    obj = 4;
                    break;
                }
                break;
            case 0:
                if (trim.equals("")) {
                    obj = 1;
                    break;
                }
                break;
            case 3127582:
                if (trim.equals("exit")) {
                    obj = 5;
                    break;
                }
                break;
            case 3532159:
                if (trim.equals("skip")) {
                    obj = 2;
                    break;
                }
                break;
            case 110066619:
                if (trim.equals("fullscreen")) {
                    obj = 6;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return AssetActionOnClick.ASSET_ACTION_ON_CLICK_SKIP;
            case 3:
            case 4:
                return AssetActionOnClick.ASSET_ACTION_ON_CLICK_REPLAY;
            case 5:
                return AssetActionOnClick.ASSET_ACTION_ON_CLICK_EXIT;
            case 6:
                return AssetActionOnClick.ASSET_ACTION_ON_CLICK_FULLSCREEN;
            default:
                return AssetActionOnClick.ASSET_ACTION_ON_CLICK_NONE;
        }
    }

    protected void m9318a(NativeV2Asset nativeV2Asset) {
        if (!mo6176c()) {
            mo6208x();
            NativeV2Asset a = m9313a(this.f7114a, nativeV2Asset);
            if (a != null) {
                Map c = m9306c(a);
                m9301a(a, c);
                if (!a.equals(nativeV2Asset)) {
                    m9301a(nativeV2Asset, c);
                }
                a.m8991a(nativeV2Asset.m9015j());
                m9309e(a, c);
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Couldn't find an asset reference for this asset click URL");
                m9301a(nativeV2Asset, m9306c(nativeV2Asset));
            }
            ai b = m9324b(this);
            if (b != null) {
                if (!nativeV2Asset.m9023r().trim().isEmpty()) {
                    C2898b i = b.m9337i();
                    if (i != null) {
                        i.mo6094d();
                    }
                }
                a = m9323b(this.f7114a, nativeV2Asset);
                if (a != null) {
                    mo6206b(a);
                } else {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Couldn't find an asset reference for this asset action! Ignoring the asset action ...");
                }
            }
        }
    }

    protected void m9315a(int i, NativeV2Asset nativeV2Asset) {
        if (this.f7125n.get(Integer.valueOf(i)) == null && !mo6176c()) {
            mo6208x();
            m9300a(i, (ak) nativeV2Asset);
        }
    }

    public void setFullScreenActivityContext(Activity activity) {
        this.f7121h = new WeakReference(activity);
    }

    @Nullable
    public Activity m9343o() {
        return this.f7121h == null ? null : (Activity) this.f7121h.get();
    }

    private void mo6208x() {
        ak b = this.f7114a.m9080b(0);
        if (this.f7125n.get(Integer.valueOf(0)) == null && b != null) {
            m9300a(0, b);
        }
    }

    void mo6205a(View view) {
        if (!m9341m() && !mo6176c()) {
            this.f7117d = true;
            Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "A viewable impression is reported on ad view.");
            this.f7114a.m9079b().m8995a(C3000a.TRACKER_EVENT_TYPE_RENDER, m9306c(this.f7114a.m9079b()));
            Map hashMap = new HashMap();
            hashMap.put("type", PlacementType.PLACEMENT_TYPE_FULLSCREEN == getRenderingProperties().m8613a() ? SchemaSymbols.ATTVAL_INT : "inlban");
            hashMap.put("clientRequestId", m9335g());
            hashMap.put("impId", m9342n());
            C3135c.m10255a().m10280a("ads", "AdRendered", hashMap);
            C3135c.m10255a().m10280a("ads", "ViewableBeaconFired", hashMap);
            mo6208x();
            for (NativeV2Asset nativeV2Asset : this.f7126o) {
                m9308d(nativeV2Asset, m9306c(nativeV2Asset));
            }
            this.f7126o.clear();
            ai b = m9324b(this);
            if (b != null) {
                C2898b i = b.m9337i();
                if (i != null) {
                    i.mo6093c();
                }
            }
        }
    }

    private void m9301a(@NonNull NativeV2Asset nativeV2Asset, @Nullable Map<String, String> map) {
        m9302a("ads", "ReportClick", new HashMap());
        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Click impression record requested");
        if (AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_COMPANION == nativeV2Asset.m9019n()) {
            bl g = ((aw) nativeV2Asset).m9441D().mo6223g();
            if (g == null || (g.m9533d() == null && nativeV2Asset.m9023r() != null)) {
                nativeV2Asset.m8995a(C3000a.TRACKER_EVENT_TYPE_CLICK, (Map) map);
                return;
            } else if (g.m9532c().size() > 0) {
                for (ah a : g.m9527a(C3000a.TRACKER_EVENT_TYPE_CLICK)) {
                    nativeV2Asset.m8996a(a, (Map) map);
                }
                return;
            } else {
                return;
            }
        }
        nativeV2Asset.m8995a(C3000a.TRACKER_EVENT_TYPE_CLICK, (Map) map);
    }

    private void m9304b(@NonNull NativeV2Asset nativeV2Asset, @Nullable Map<String, String> map) {
        nativeV2Asset.m8995a(C3000a.TRACKER_EVENT_TYPE_LOAD, (Map) map);
    }

    private void m9307c(@NonNull NativeV2Asset nativeV2Asset, @Nullable Map<String, String> map) {
        nativeV2Asset.m8995a(C3000a.TRACKER_EVENT_TYPE_CLIENT_FILL, (Map) map);
    }

    private Map<String, String> m9306c(@NonNull NativeV2Asset nativeV2Asset) {
        Map<String, String> hashMap = new HashMap(3);
        hashMap.put("$LTS", String.valueOf(this.f7114a.m9079b().m9364y()));
        ak a = this.f7114a.m9071a(nativeV2Asset);
        long currentTimeMillis = System.currentTimeMillis();
        if (!(a == null || 0 == a.m9364y())) {
            currentTimeMillis = a.m9364y();
        }
        hashMap.put("$STS", String.valueOf(currentTimeMillis));
        hashMap.put("$TS", String.valueOf(System.currentTimeMillis()));
        return hashMap;
    }

    private void m9308d(@Nullable NativeV2Asset nativeV2Asset, @Nullable Map<String, String> map) {
        if (nativeV2Asset == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Cannot report page view impression on null page container! Ignoring ...");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", nativeV2Asset.m9012g());
            jSONObject.put("asset", nativeV2Asset.m9009e());
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        Map hashMap = new HashMap();
        hashMap.put("type", "inlban");
        hashMap.put("impId", this.f7123k);
        hashMap.put("pageJson", jSONObject);
        C3135c.m10255a().m10280a("ads", "PageRendered", hashMap);
        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Page-view impression record request");
        nativeV2Asset.m8995a(C3000a.TRACKER_EVENT_TYPE_PAGE_VIEW, (Map) map);
    }

    @TargetApi(15)
    protected void mo6206b(@NonNull NativeV2Asset nativeV2Asset) {
        AssetActionOnClick l = nativeV2Asset.m9017l();
        if (AssetActionOnClick.ASSET_ACTION_ON_CLICK_NONE != l) {
            switch (l) {
                case ASSET_ACTION_ON_CLICK_REPLAY:
                    try {
                        if (VERSION.SDK_INT < 15) {
                            return;
                        }
                        if (AssetType.ASSET_TYPE_VIDEO != nativeV2Asset.m8986a()) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Action " + AssetActionOnClick.ASSET_ACTION_ON_CLICK_REPLAY + " not valid for asset of type: " + nativeV2Asset.m8986a());
                            return;
                        }
                        ai h = m9336h();
                        if (h != null && (h instanceof au)) {
                            NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) h.getVideoContainerView();
                            if (nativeStrandVideoWrapper != null && m9338j() != null) {
                                NativeStrandVideoView videoView = nativeStrandVideoWrapper.getVideoView();
                                aw awVar = (aw) videoView.getTag();
                                View j = m9338j();
                                ViewGroup viewGroup = (ViewGroup) j.getParent();
                                if (viewGroup != null) {
                                    viewGroup.removeView(j);
                                }
                                if (awVar != null) {
                                    if (awVar.m9448z()) {
                                        videoView.m8916e();
                                    } else {
                                        videoView.m8915d();
                                    }
                                } else if (PlacementType.PLACEMENT_TYPE_FULLSCREEN == m9330d()) {
                                    videoView.m8916e();
                                } else {
                                    videoView.m8915d();
                                }
                                videoView.start();
                                m9331d(h.m9338j());
                                m9333e(h.m9338j());
                                return;
                            }
                            return;
                        }
                        return;
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Encountered unexpected error in handling replay action on video: " + e.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi Native Strand", "SDK encountered unexpected error in replaying video");
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return;
                    }
                case ASSET_ACTION_ON_CLICK_EXIT:
                    try {
                        m9347s();
                        return;
                    } catch (Throwable e2) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Encountered unexpected error in handling exit action on video: " + e2.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi Native Strand", "SDK encountered unexpected error in exiting video");
                        C3135c.m10255a().m10279a(new C3132b(e2));
                        return;
                    }
                case ASSET_ACTION_ON_CLICK_FULLSCREEN:
                    try {
                        if (PlacementType.PLACEMENT_TYPE_INLINE == m9330d()) {
                            m9344p();
                            return;
                        }
                        return;
                    } catch (Throwable e22) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Encountered unexpected error in handling fullscreen action " + e22.getMessage());
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi Native Strand", "SDK encountered unexpected error in launching fullscreen ad");
                        C3135c.m10255a().m10279a(new C3132b(e22));
                        return;
                    }
                default:
                    C3135c.m10255a().m10280a("ads", "onAssetSkipped", null);
                    return;
            }
        }
    }

    private NativeStrandTimerView m9310f(View view) {
        if (view != null) {
            return (NativeStrandTimerView) view.findViewWithTag("timerView");
        }
        return null;
    }

    protected void m9326b(View view) {
        NativeStrandTimerView f = m9310f(view);
        if (f != null) {
            f.m8865c();
        }
    }

    protected void m9328c(View view) {
        NativeStrandTimerView f = m9310f(view);
        if (f != null) {
            f.m8866d();
        }
    }

    protected void m9331d(View view) {
        NativeStrandTimerView f = m9310f(view);
        if (f != null) {
            f.m8864b();
        }
    }

    protected void m9333e(View view) {
        NativeStrandTimerView f = m9310f(view);
        if (f != null) {
            f.m8862a();
        }
    }

    protected void m9344p() {
        NativeV2DataModel k = m9339k();
        if (k.m9086f().length() != 0) {
            JSONObject a = k.m9075a(0);
            if (a != null) {
                NativeV2DataModel nativeV2DataModel = new NativeV2DataModel(m9330d(), a, k, null, null);
                Context e = m9332e();
                if (nativeV2DataModel.m9094n() && e != null) {
                    AdContainer a2 = C3003a.m9299a(e, new RenderingProperties(PlacementType.PLACEMENT_TYPE_INLINE), nativeV2DataModel, m9334f(), m9335g(), this.f7115b);
                    a2.m9320a(this);
                    if (m9337i() != null) {
                        a2.m9319a(m9337i());
                    }
                    int a3 = InMobiAdActivity.m10547a(a2);
                    Intent intent = new Intent(m9332e(), InMobiAdActivity.class);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_INDEX", a3);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 102);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_IS_FULL_SCREEN", true);
                    intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_CONTAINER_TYPE", 201);
                    C3105a.m10072a(m9332e(), intent);
                }
            }
        }
    }

    boolean mo6207q() {
        return false;
    }

    void m9346r() {
        if (mo6207q()) {
            this.f7120g = true;
            C2898b i = m9337i();
            if (i != null && m9339k().m9087g() != null) {
                i.mo6091a(m9339k().m9087g());
            }
        }
    }

    void m9347s() {
        try {
            if (!mo6176c()) {
                Object b = m9324b(this);
                if (b != null) {
                    Activity activity;
                    b.m9346r();
                    InMobiAdActivity.m10553a(b);
                    if (b instanceof au) {
                        NativeStrandVideoWrapper nativeStrandVideoWrapper = (NativeStrandVideoWrapper) ((au) b).getVideoContainerView();
                        if (nativeStrandVideoWrapper != null) {
                            NativeStrandVideoView videoView = nativeStrandVideoWrapper.getVideoView();
                            aw awVar = (aw) videoView.getTag();
                            awVar.m9027v().put("seekPosition", Integer.valueOf(videoView.getCurrentPosition()));
                            awVar.m9027v().put("lastMediaVolume", Integer.valueOf(videoView.getVolume()));
                            if (awVar.m9014i() != null) {
                                ((aw) awVar.m9014i()).m9442a(awVar);
                            }
                        }
                    }
                    if (b.f7121h == null) {
                        activity = null;
                    } else {
                        activity = (Activity) b.f7121h.get();
                    }
                    if (activity != null && (activity instanceof InMobiAdActivity)) {
                        ((InMobiAdActivity) activity).m10560a(true);
                        activity.finish();
                    }
                }
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Encountered unexpected error in handling exit action on video: " + e.getMessage());
            Logger.m10359a(InternalLogLevel.DEBUG, "InMobi Native Strand", "SDK encountered unexpected error in exiting video");
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    private void m9302a(String str, String str2, Map<String, Object> map) {
        ai b = m9324b(this);
        if (b != null) {
            C2898b i = b.m9337i();
            if (i != null) {
                i.mo6090a(str, str2, map);
                return;
            } else {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "InteractionCallback is null. Discarding telemetry event : [" + str2 + " ]");
                return;
            }
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Target container is null. Discarding telemetry event : [" + str2 + " ]");
    }

    ai m9324b(@Nullable ai aiVar) {
        if (aiVar == null) {
            return null;
        }
        return (aiVar.m9343o() != null || aiVar == aiVar.m9336h()) ? aiVar : m9324b(aiVar.m9336h());
    }

    private void m9309e(@NonNull NativeV2Asset nativeV2Asset, @Nullable Map<String, String> map) {
        if (nativeV2Asset.m8986a() == AssetType.ASSET_TYPE_VIDEO || nativeV2Asset.m9013h()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Asset interaction requested");
            AssetInteractionMode j = nativeV2Asset.m9015j();
            if (AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION != j) {
                String d;
                String r = nativeV2Asset.m9023r();
                if (AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_COMPANION == nativeV2Asset.m9019n()) {
                    bl g = ((aw) nativeV2Asset).m9441D().mo6223g();
                    if (!(g == null || g.m9533d() == null || g.m9533d().trim().isEmpty())) {
                        d = g.m9533d();
                        if (TextUtils.isEmpty(d)) {
                            d = C3155d.m10402a(d, (Map) map);
                            if (AssetInteractionMode.ASSET_INTERACTION_MODE_IN_APP == j) {
                                m9322a(d, nativeV2Asset.m9024s());
                            } else if (C3153b.m10397a(d)) {
                                m9322a(d, null);
                            } else {
                                m9321a(d);
                            }
                            if (this.f7116c != null) {
                                this.f7116c.mo6227a(AdEvent.AD_EVENT_CLICK_THRU);
                                return;
                            }
                            return;
                        }
                        Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Invalid url:" + d);
                    }
                }
                d = r;
                if (TextUtils.isEmpty(d)) {
                    d = C3155d.m10402a(d, (Map) map);
                    if (AssetInteractionMode.ASSET_INTERACTION_MODE_IN_APP == j) {
                        m9322a(d, nativeV2Asset.m9024s());
                    } else if (C3153b.m10397a(d)) {
                        m9322a(d, null);
                    } else {
                        m9321a(d);
                    }
                    if (this.f7116c != null) {
                        this.f7116c.mo6227a(AdEvent.AD_EVENT_CLICK_THRU);
                        return;
                    }
                    return;
                }
                Logger.m10359a(InternalLogLevel.INTERNAL, f7112i, "Invalid url:" + d);
            }
        }
    }

    protected void m9321a(String str) {
        Context e = m9332e();
        if (e != null) {
            InMobiAdActivity.m10552a(null);
            Intent intent = new Intent(e, InMobiAdActivity.class);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", 100);
            intent.putExtra("com.inmobi.rendering.InMobiAdActivity.IN_APP_BROWSER_URL", str);
            e.startActivity(intent);
        }
    }

    protected void m9322a(@NonNull String str, @Nullable String str2) {
        if (m9332e() != null && C3153b.m10395a(m9332e(), str, str2)) {
            ai b = m9324b(this);
            if (b != null) {
                C2898b i = b.m9337i();
                if (i != null) {
                    i.mo6096f();
                }
            }
        }
    }

    public void m9348t() {
        Context e = m9332e();
        if (e instanceof Activity) {
            ((Activity) e).getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        Object o = m9343o();
        if (o == null) {
            o = m9332e();
        }
        if (o != null && o.equals(activity)) {
            m9349u();
        }
    }

    void m9349u() {
        this.f7118e = false;
        m9328c(m9338j());
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        Object o = m9343o();
        if (o == null) {
            o = m9332e();
        }
        if (o != null && o.equals(activity)) {
            m9350v();
        }
    }

    void m9350v() {
        this.f7118e = true;
        m9326b(m9338j());
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
