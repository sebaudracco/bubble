package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import com.inmobi.ads.AdContainer.EventType;
import com.inmobi.ads.AdContainer.RenderingProperties;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.C3055h.C2910a;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.ads.ai.C2898b;
import com.inmobi.ads.ai.C3003a;
import com.inmobi.ads.be.C2909a;
import com.inmobi.ads.p111b.C3028a;
import com.inmobi.commons.core.configs.C3045a;
import com.inmobi.commons.core.configs.C3121b;
import com.inmobi.commons.core.configs.C3121b.C2911b;
import com.inmobi.commons.core.configs.C3125f;
import com.inmobi.commons.core.configs.C3128g;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3159a;
import com.inmobi.commons.core.utilities.info.C3160b;
import com.inmobi.commons.core.utilities.info.C3162d;
import com.inmobi.commons.core.utilities.info.C3164f;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.commons.core.utilities.uid.C3168c;
import com.inmobi.commons.core.utilities.uid.C3169d;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderView.C2912a;
import com.inmobi.rendering.p118a.C3213c;
import com.inmobi.signals.C3277o;
import com.mopub.mobileads.VastResourceXmlManager;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONObject;

@UiThread
public abstract class AdUnit implements C2909a, C2910a, C2911b, C2912a {
    private static final String f6711b = AdUnit.class.getSimpleName();
    private MonetizationContext f6712A;
    private be f6713B;
    private C2908e f6714C;
    private ConcurrentHashMap<Integer, WeakReference<C2905b>> f6715D = new ConcurrentHashMap(8, 0.9f, 3);
    protected long f6716a = 0;
    private AdState f6717c;
    private WeakReference<Activity> f6718d;
    private WeakReference<Context> f6719e;
    private long f6720f;
    private String f6721g;
    private Map<String, String> f6722h;
    private C3046c f6723i;
    private String f6724j;
    private C3055h f6725k;
    private bo f6726l;
    private String f6727m;
    private String f6728n;
    private AdMarkupType f6729o;
    private long f6730p;
    private boolean f6731q = false;
    private WeakReference<C2905b> f6732r;
    private RenderView f6733s;
    private bf f6734t;
    private long f6735u;
    private ai f6736v;
    private C2904a f6737w;
    private ExecutorService f6738x;
    private Runnable f6739y;
    private Set<bi> f6740z;

    class C28942 implements Runnable {
        final /* synthetic */ AdUnit f6694a;

        C28942(AdUnit adUnit) {
            this.f6694a = adUnit;
        }

        public void run() {
            try {
                if (this.f6694a.f6737w == null) {
                    this.f6694a.f6737w = new C2904a(this.f6694a);
                }
                if (C3155d.m10406a()) {
                    C3277o.m10989a().m10998i();
                    this.f6694a.m8702I();
                    C3045a c3128g = new C3128g();
                    C3121b.m10178a().m10190a(c3128g, null);
                    if (!c3128g.m10243h()) {
                        this.f6694a.f6735u = System.currentTimeMillis();
                        try {
                            if (this.f6694a.f6713B == null) {
                                this.f6694a.f6713B = new be(this.f6694a);
                            }
                            this.f6694a.f6728n = this.f6694a.f6713B.m9510a(this.f6694a.mo6143T());
                            return;
                        } catch (C3028a e) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, e.getMessage());
                            if (!this.f6694a.f6713B.m9512a()) {
                                this.f6694a.mo6109c(this.f6694a.f6720f, new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST));
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                this.f6694a.mo6109c(this.f6694a.m8736b(), new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE));
            } catch (Throwable e2) {
                Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
                Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, "Prefetch failed with unexpected error: " + e2.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e2));
            }
        }
    }

    class C28953 implements Runnable {
        final /* synthetic */ AdUnit f6695a;

        C28953(AdUnit adUnit) {
            this.f6695a = adUnit;
        }

        public void run() {
            String str;
            int E = this.f6695a.mo6251E();
            switch (E) {
                case -2:
                    str = "Loading an ad resulted in an unexpected error";
                    break;
                case -1:
                    str = "Ad request skipped as monetization is disabled";
                    break;
                case 0:
                    str = "Fresh ad requested";
                    break;
                case 1:
                    str = "Returning pre-cached ad";
                    break;
                default:
                    str = "Unknown return value (" + E + ") from #doAdLoadWork()";
                    break;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, str);
        }
    }

    class C29015 implements Runnable {
        final /* synthetic */ AdUnit f6705a;

        C29015(AdUnit adUnit) {
            this.f6705a = adUnit;
        }

        public void run() {
            this.f6705a.mo6254O();
        }
    }

    public enum AdCreativeType {
        AD_CREATIVE_TYPE_UNSUPPORTED_OR_UNKNOWN("unknown"),
        AD_CREATIVE_TYPE_DISPLAY("nonvideo"),
        AD_CREATIVE_TYPE_VIDEO("video");
        
        private final String f6709a;

        private AdCreativeType(String str) {
            this.f6709a = str;
        }
    }

    public enum AdMarkupType {
        AD_MARKUP_TYPE_UNKNOWN,
        AD_MARKUP_TYPE_INM_HTML,
        AD_MARKUP_TYPE_INM_JSON,
        AD_MARKUP_TYPE_PUB_JSON
    }

    public enum AdState {
        STATE_CREATED,
        STATE_LOADING,
        STATE_AVAILABLE,
        STATE_FAILED,
        STATE_LOADED,
        STATE_READY,
        STATE_ATTACHED,
        STATE_RENDERED,
        STATE_ACTIVE,
        STATE_PREFETCHED
    }

    public enum AdTrackerType {
        AD_TRACKER_TYPE_NONE,
        AD_TRACKER_TYPE_MOAT,
        AD_TRACKER_TYPE_INMOBI,
        AD_TRACKER_TYPE_IAS,
        AD_TRACKER_TYPE_DV,
        AD_TRACKER_TYPE_UNKNOWN
    }

    static final class C2904a extends Handler {
        private WeakReference<AdUnit> f6710a;

        public C2904a(AdUnit adUnit) {
            super(Looper.getMainLooper());
            this.f6710a = new WeakReference(adUnit);
        }

        public void handleMessage(Message message) {
            if (this.f6710a != null && this.f6710a.get() != null) {
                Bundle data = message.getData();
                long j = data.getLong("placementId");
                AdUnit adUnit = (AdUnit) this.f6710a.get();
                switch (message.what) {
                    case 1:
                        adUnit.mo6257b(j, data.getBoolean("adAvailable"), (C2968a) message.obj, data.getLong("insertionTs"));
                        return;
                    case 2:
                        adUnit.mo6149b(j, (C2968a) message.obj);
                        return;
                    case 3:
                        return;
                    case 4:
                        adUnit.mo6164b(j, data.getBoolean("assetAvailable"));
                        return;
                    case 11:
                        adUnit.mo6252K();
                        return;
                    case 12:
                        adUnit.mo6253L();
                        return;
                    case 13:
                        adUnit.mo6258d(j, (InMobiAdRequestStatus) message.obj);
                        return;
                    case 14:
                        adUnit.mo6256b(j);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    interface C2905b {
        void mo6119a();

        void mo6120a(AdUnit adUnit);

        void mo6121a(AdUnit adUnit, InMobiAdRequestStatus inMobiAdRequestStatus);

        void mo6122a(AdUnit adUnit, boolean z);

        void mo6123a(@NonNull Map<Object, Object> map);

        void mo6124b();

        void mo6125b(@NonNull Map<Object, Object> map);

        void mo6126c();

        void mo6127d();

        void mo6128e();

        void mo6129f();

        void mo6130g();
    }

    public static class C2906c {
        private static Map<String, Object> m8652b(String str) {
            Map<String, Object> hashMap = new HashMap();
            Object obj = -1;
            switch (str.hashCode()) {
                case 112202875:
                    if (str.equals("video")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 1425678798:
                    if (str.equals("nonvideo")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case 1:
                    obj = AdCreativeType.AD_CREATIVE_TYPE_DISPLAY;
                    break;
                case 2:
                    obj = AdCreativeType.AD_CREATIVE_TYPE_VIDEO;
                    break;
                default:
                    obj = AdCreativeType.AD_CREATIVE_TYPE_UNSUPPORTED_OR_UNKNOWN;
                    break;
            }
            hashMap.put(VastResourceXmlManager.CREATIVE_TYPE, obj);
            return hashMap;
        }
    }

    static class C2907d {
        @NonNull
        static HashMap<String, String> m8653a(@NonNull String str, @NonNull String str2, JSONArray jSONArray, JSONArray jSONArray2) {
            int i;
            int i2 = 0;
            HashMap<String, String> hashMap = new HashMap();
            if (jSONArray != null) {
                try {
                    int length = jSONArray.length();
                    for (i = 0; i < length; i++) {
                        hashMap.put(str + (i + 1), jSONArray.getString(i));
                    }
                } catch (Throwable e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, "Exception while parsing map details for Moat : " + e.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e));
                }
            }
            if (jSONArray2 != null) {
                i = jSONArray2.length();
                while (i2 < i) {
                    hashMap.put(str2 + (i2 + 1), jSONArray2.getString(i2));
                    i2++;
                }
            }
            return hashMap;
        }

        @Nullable
        private static Map<String, Object> m8655b(@NonNull JSONArray jSONArray) {
            try {
                JSONObject jSONObject;
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("moat")) {
                        jSONObject = jSONObject2.getJSONObject("moat");
                        break;
                    }
                }
                jSONObject = null;
                if (jSONObject == null) {
                    return null;
                }
                Map<String, Object> hashMap = new HashMap();
                hashMap.put("enabled", Boolean.valueOf(jSONObject.getBoolean("enabled")));
                hashMap.put("instrumentVideo", Boolean.valueOf(jSONObject.optBoolean("instrumentVideo", false)));
                hashMap.put("partnerCode", jSONObject.optString("partnerCode", null));
                hashMap.put("clientLevels", jSONObject.optJSONArray("clientLevels"));
                hashMap.put("clientSlicers", jSONObject.optJSONArray("clientSlicers"));
                return hashMap;
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, "Exception while parsing MoatParams from response : " + e.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e));
                return null;
            }
        }
    }

    interface C2908e {
        void mo6116a(@NonNull AdUnit adUnit);

        void mo6117a(@NonNull AdUnit adUnit, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    protected abstract boolean mo6140F();

    protected abstract void mo6150b(C2968a c2968a);

    protected abstract String mo6151d();

    protected abstract String mo6152f();

    protected abstract PlacementType mo6153h();

    public void mo6102a(C3045a c3045a) {
        this.f6723i = (C3046c) c3045a;
        C3135c.m10255a().m10281a(this.f6723i.mo6231a(), this.f6723i.m9729n());
    }

    public AdUnit(Context context, long j, C2905b c2905b) {
        this.f6719e = new WeakReference(context);
        this.f6720f = j;
        this.f6732r = new WeakReference(c2905b);
        mo6142S();
        m8720a(AdState.STATE_CREATED);
    }

    public AdUnit(Activity activity, long j, C2905b c2905b) {
        this.f6718d = new WeakReference(activity);
        this.f6720f = j;
        this.f6732r = new WeakReference(c2905b);
        mo6142S();
        m8720a(AdState.STATE_CREATED);
    }

    @Nullable
    public final Context m8711a() {
        if (this.f6718d == null || this.f6718d.get() == null) {
            return this.f6719e == null ? null : (Context) this.f6719e.get();
        } else {
            return (Context) this.f6718d.get();
        }
    }

    public void mo6146a(Context context) {
        this.f6719e = new WeakReference(context);
    }

    public final long m8736b() {
        return this.f6720f;
    }

    public final AdState m8748c() {
        return this.f6717c;
    }

    protected String m8758e() {
        return "sdkJson";
    }

    Set<bi> m8764g() {
        return this.f6740z;
    }

    protected Map<String, String> mo6166i() {
        return null;
    }

    protected String m8769j() {
        return this.f6724j;
    }

    protected AdMarkupType m8770k() {
        return this.f6729o;
    }

    long m8771l() {
        return this.f6730p;
    }

    protected String m8772m() {
        return this.f6727m;
    }

    protected String m8773n() {
        return this.f6728n;
    }

    protected void m8774o() {
        this.f6724j = null;
    }

    protected void m8720a(AdState adState) {
        this.f6717c = adState;
    }

    protected final C3046c m8775p() {
        return this.f6723i;
    }

    @Nullable
    protected final C2905b m8776q() {
        return (C2905b) this.f6732r.get();
    }

    protected final Map<Integer, WeakReference<C2905b>> m8777r() {
        return this.f6715D;
    }

    @Nullable
    protected final C2905b m8712a(int i) {
        if (this.f6715D.get(Integer.valueOf(i)) == null) {
            return null;
        }
        return (C2905b) ((WeakReference) this.f6715D.get(Integer.valueOf(i))).get();
    }

    @Nullable
    protected final C2905b m8737b(int i) {
        if (this.f6715D.get(Integer.valueOf(i)) == null) {
            return null;
        }
        return (C2905b) ((WeakReference) this.f6715D.remove(Integer.valueOf(i))).get();
    }

    final void m8721a(C2905b c2905b) {
        this.f6732r = new WeakReference(c2905b);
    }

    private void mo6142S() {
        this.f6729o = AdMarkupType.AD_MARKUP_TYPE_UNKNOWN;
        this.f6725k = new C3055h(this);
        this.f6723i = new C3046c();
        C3121b.m10178a().m10190a(new C3125f(), null);
        C3121b.m10178a().m10190a(this.f6723i, (C2911b) this);
        this.f6734t = new bf(this);
        this.f6740z = new HashSet();
        this.f6738x = Executors.newSingleThreadExecutor();
        m8697D();
        C3135c.m10255a().m10281a(this.f6723i.mo6231a(), this.f6723i.m9729n());
    }

    @NonNull
    protected final C3055h m8778s() {
        return this.f6725k;
    }

    @Nullable
    protected AdContainer mo6167t() {
        AdState c = m8748c();
        switch (m8770k()) {
            case AD_MARKUP_TYPE_INM_HTML:
                if (AdState.STATE_CREATED == c || AdState.STATE_LOADING == c || AdState.STATE_FAILED == c) {
                    return null;
                }
                return mo6245v();
            case AD_MARKUP_TYPE_PUB_JSON:
                if (AdState.STATE_CREATED == c || AdState.STATE_LOADING == c || AdState.STATE_FAILED == c) {
                    return null;
                }
                return mo6245v();
            case AD_MARKUP_TYPE_INM_JSON:
                if (AdState.STATE_CREATED == c || AdState.STATE_LOADING == c || AdState.STATE_FAILED == c || AdState.STATE_AVAILABLE == c) {
                    return null;
                }
                return m8780u();
            default:
                return null;
        }
    }

    @Nullable
    protected ai m8780u() {
        return this.f6736v;
    }

    @NonNull
    protected RenderView mo6245v() {
        if ((this.f6733s == null || this.f6733s.mo6176c()) && m8711a() != null) {
            this.f6733s = new RenderView(m8711a(), new RenderingProperties(mo6153h()), this.f6740z, m8772m());
            this.f6733s.m10627a((C2912a) this, m8775p().m9726k(), m8775p().m9727l());
        }
        return this.f6733s;
    }

    public boolean mo6148a(C2968a c2968a) {
        boolean z = false;
        String str = "pubContent";
        str = "markupType";
        str = VastResourceXmlManager.CREATIVE_TYPE;
        try {
            JSONObject jSONObject = new JSONObject(c2968a.m9123c());
            this.f6730p = c2968a.m9126f();
            this.f6727m = c2968a.m9125e();
            this.f6728n = c2968a.m9122b();
            this.f6729o = mo6165g(jSONObject.optString("markupType"));
            if (AdMarkupType.AD_MARKUP_TYPE_UNKNOWN != this.f6729o) {
                if (AdMarkupType.AD_MARKUP_TYPE_INM_JSON == this.f6729o) {
                    this.f6724j = jSONObject.getJSONObject("pubContent").toString();
                } else {
                    this.f6724j = jSONObject.getString("pubContent").trim();
                }
                if (!(this.f6724j == null || this.f6724j.length() == 0 || this.f6729o == AdMarkupType.AD_MARKUP_TYPE_UNKNOWN)) {
                    this.f6724j = this.f6724j.replace("@__imm_aft@", String.valueOf(System.currentTimeMillis() - this.f6735u));
                    z = true;
                }
                if (this.f6740z.isEmpty()) {
                    bi biVar;
                    if (jSONObject.has("viewability")) {
                        biVar = new bi(AdTrackerType.AD_TRACKER_TYPE_MOAT);
                        biVar.f7268b = C2907d.m8655b(jSONObject.getJSONArray("viewability"));
                        this.f6740z.add(biVar);
                        if (biVar.f7268b != null) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Read out Moat params: " + biVar.f7268b.toString());
                        }
                    }
                    if (jSONObject.has("metaInfo")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("metaInfo");
                        str = "unknown";
                        if (jSONObject2.has(VastResourceXmlManager.CREATIVE_TYPE)) {
                            str = jSONObject2.getString(VastResourceXmlManager.CREATIVE_TYPE);
                        }
                        if (jSONObject2.has("iasEnabled") && jSONObject2.getBoolean("iasEnabled")) {
                            biVar = new bi(AdTrackerType.AD_TRACKER_TYPE_IAS);
                            biVar.f7268b = C2906c.m8652b(str);
                            if (biVar.f7268b != null) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Read out IAS params: " + biVar.f7268b.toString());
                            }
                            this.f6740z.add(biVar);
                        }
                    }
                }
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f6711b, "Exception while parsing received ad.", e);
            C3135c.m10255a().m10279a(new C3132b(e));
        } catch (Throwable e2) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f6711b, "Invalid Base64 encoding in received ad.", e2);
            C3135c.m10255a().m10279a(new C3132b(e2));
        }
        return z;
    }

    private AdMarkupType mo6165g(@Nullable String str) {
        if (str == null || str.length() == 0) {
            return AdMarkupType.AD_MARKUP_TYPE_INM_HTML;
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -1084172778:
                if (str.equals("inmobiJson")) {
                    obj = 3;
                    break;
                }
                break;
            case -236368507:
                if (str.equals("pubJson")) {
                    obj = 2;
                    break;
                }
                break;
            case 3213227:
                if (str.equals("html")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 1:
                return AdMarkupType.AD_MARKUP_TYPE_INM_HTML;
            case 2:
                return AdMarkupType.AD_MARKUP_TYPE_PUB_JSON;
            case 3:
                return AdMarkupType.AD_MARKUP_TYPE_INM_JSON;
            default:
                return AdMarkupType.AD_MARKUP_TYPE_UNKNOWN;
        }
    }

    public void mo6100a(long j, boolean z) {
        if (this.f6737w != null) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            Bundle bundle = new Bundle();
            bundle.putLong("placementId", j);
            bundle.putBoolean("assetAvailable", z);
            obtain.setData(bundle);
            this.f6737w.sendMessage(obtain);
        }
    }

    protected void mo6164b(long j, boolean z) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Asset availability changed (" + z + ") for placement ID (" + j + ")");
    }

    public final void mo6101a(long j, boolean z, C2968a c2968a, long j2) {
        if (this.f6737w != null) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = c2968a;
            Bundle bundle = new Bundle();
            bundle.putLong("placementId", j);
            bundle.putLong("insertionTs", j2);
            bundle.putBoolean("adAvailable", z);
            obtain.setData(bundle);
            this.f6737w.sendMessage(obtain);
        }
    }

    @UiThread
    protected void mo6257b(long j, boolean z, C2968a c2968a, long j2) {
        if (j == m8736b() && AdState.STATE_LOADING == m8748c() && z) {
            this.f6730p = j2;
        }
    }

    public final void mo6099a(long j, C2968a c2968a) {
        if (m8711a() != null && this.f6737w != null) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            Bundle bundle = new Bundle();
            bundle.putLong("placementId", j);
            obtain.setData(bundle);
            obtain.obj = c2968a;
            this.f6737w.sendMessage(obtain);
        }
    }

    @UiThread
    protected void mo6149b(long j, @NonNull C2968a c2968a) {
        if (j != m8736b() || m8748c() != AdState.STATE_LOADING) {
            return;
        }
        if (mo6148a(c2968a)) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Ad fetch successful");
            m8720a(AdState.STATE_AVAILABLE);
            return;
        }
        m8752c("ParsingFailed");
        m8724a(new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR), true);
    }

    public final void mo6098a(final long j, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (m8711a() != null && this.f6737w != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ AdUnit f6693c;

                public void run() {
                    try {
                        this.f6693c.m8739b(j, inMobiAdRequestStatus);
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                        Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, "onAdFetchFailed with error: " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                    }
                }
            });
        }
    }

    protected void m8739b(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
        try {
            if (j == m8736b()) {
                Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "Failed to fetch ad for placement id: " + this.f6720f + ", reason phrase available in onAdLoadFailed callback.");
                m8724a(inMobiAdRequestStatus, true);
                if (StatusCode.INTERNAL_ERROR == inMobiAdRequestStatus.getStatusCode()) {
                    m8752c("InternalError");
                }
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Unable to load ad; SDK encountered an internal error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Handling ad fetch failed encountered an unexpected error: " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
    }

    protected void m8724a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z) {
        if (m8748c() == AdState.STATE_LOADING && z) {
            m8720a(AdState.STATE_FAILED);
        }
        C2905b q = m8776q();
        if (q != null) {
            q.mo6121a(this, inMobiAdRequestStatus);
        }
        if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NO_FILL) {
            m8752c("NoFill");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.SERVER_ERROR) {
            m8752c("ServerError");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.NETWORK_UNREACHABLE) {
            m8752c("NetworkUnreachable");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.AD_ACTIVE) {
            m8752c("AdActive");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_PENDING) {
            m8752c("RequestPending");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_INVALID) {
            m8752c("RequestInvalid");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.REQUEST_TIMED_OUT) {
            m8752c("RequestTimedOut");
        } else if (inMobiAdRequestStatus.getStatusCode() == StatusCode.EARLY_REFRESH_REQUEST) {
            m8752c("EarlyRefreshRequest");
        }
    }

    public void m8725a(@NonNull bo boVar) {
        this.f6726l = boVar;
    }

    @Nullable
    public bo m8782w() {
        return this.f6726l;
    }

    public void m8730a(String str) {
        this.f6721g = str;
    }

    public void mo6255a(MonetizationContext monetizationContext) {
        this.f6712A = monetizationContext;
    }

    public MonetizationContext mo6259x() {
        return this.f6712A;
    }

    public String m8784y() {
        return this.f6721g;
    }

    public void m8733a(Map<String, String> map) {
        this.f6722h = map;
    }

    public Map<String, String> m8785z() {
        return this.f6722h;
    }

    @UiThread
    public void mo6139A() {
        m8731a("ads", "AdLoadRequested");
        if (!C3155d.m10406a()) {
            m8724a(new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE), true);
        } else if (!mo6140F()) {
            this.f6738x.execute(m8696C());
        }
    }

    @UiThread
    void mo6250B() {
        m8731a("ads", "AdPrefetchRequested");
        this.f6738x.execute(new C28942(this));
    }

    protected Runnable m8696C() {
        return this.f6739y;
    }

    protected void m8697D() {
        this.f6739y = new C28953(this);
    }

    protected int mo6251E() {
        try {
            this.f6717c = AdState.STATE_LOADING;
            C3277o.m10989a().m10998i();
            m8702I();
            C3045a c3128g = new C3128g();
            C3121b.m10178a().m10190a(c3128g, null);
            if (c3128g.m10243h()) {
                m8731a("ads", "LoadAfterMonetizationDisabled");
                Logger.m10359a(InternalLogLevel.ERROR, f6711b, "SDK will not perform this load operation as monetization has been disabled. Please contact InMobi for further info.");
                return -1;
            }
            m8726a(mo6143T());
            return 0;
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.ERROR, "InMobi", "Unable to load ad; SDK encountered an unexpected error");
            Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Load failed with unexpected error: " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
            return -2;
        }
    }

    protected void m8734a(boolean z) {
        this.f6731q = z;
    }

    protected boolean m8700G() {
        return this.f6731q;
    }

    protected void m8713a(final int i, @NonNull String str, @Nullable final Runnable runnable, @Nullable final Looper looper) {
        if (AdMarkupType.AD_MARKUP_TYPE_INM_HTML == m8770k() || AdMarkupType.AD_MARKUP_TYPE_PUB_JSON == m8770k()) {
            this.f6716a = SystemClock.elapsedRealtime();
            mo6245v().m10628a(str);
            mo6144U();
        } else if (AdMarkupType.AD_MARKUP_TYPE_INM_JSON == m8770k()) {
            new Thread(this) {
                final /* synthetic */ AdUnit f6704d;

                class C28992 implements C2898b {
                    final /* synthetic */ C29004 f6700a;

                    class C28971 implements Runnable {
                        final /* synthetic */ C28992 f6699a;

                        C28971(C28992 c28992) {
                            this.f6699a = c28992;
                        }

                        public void run() {
                            this.f6699a.f6700a.f6704d.m8731a("ads", "IntClosed");
                            this.f6699a.f6700a.f6704d.mo6141J();
                            this.f6699a.f6700a.f6704d.f6736v = null;
                            C2905b b = this.f6699a.f6700a.f6704d.m8737b(i);
                            if (b != null) {
                                b.mo6128e();
                            }
                        }
                    }

                    C28992(C29004 c29004) {
                        this.f6700a = c29004;
                    }

                    public void mo6089a() {
                        C2905b a = this.f6700a.f6704d.m8712a(i);
                        if (a != null) {
                            a.mo6124b();
                        }
                    }

                    public void mo6092b() {
                        this.f6700a.f6704d.m8731a("ads", "AdRendered");
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "Successfully displayed Interstitial for placement id: " + this.f6700a.f6704d.m8736b());
                        C2905b a = this.f6700a.f6704d.m8712a(i);
                        if (a != null) {
                            a.mo6127d();
                        }
                    }

                    public void mo6093c() {
                    }

                    public void mo6094d() {
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "Ad interaction for placement id: " + this.f6700a.f6704d.m8736b());
                        C2905b a = this.f6700a.f6704d.m8712a(i);
                        if (a != null) {
                            a.mo6123a(new HashMap());
                        }
                    }

                    public void mo6095e() {
                        Logger.m10359a(InternalLogLevel.DEBUG, "InMobi", "Interstitial ad dismissed for placement id: " + this.f6700a.f6704d.m8736b());
                        new Handler(Looper.getMainLooper()).post(new C28971(this));
                    }

                    public void mo6091a(Map<String, String> map) {
                        C2905b a = this.f6700a.f6704d.m8712a(i);
                        if (a != null) {
                            a.mo6125b(new HashMap(map));
                        }
                    }

                    public void mo6096f() {
                        C2905b a = this.f6700a.f6704d.m8712a(i);
                        if (a != null) {
                            a.mo6129f();
                        }
                    }

                    public void mo6090a(String str, String str2, Map<String, Object> map) {
                        this.f6700a.f6704d.m8753c(str, str2, map);
                    }
                }

                private void m8637a(int i, @NonNull ai aiVar) {
                    this.f6704d.f6736v = aiVar;
                    this.f6704d.m8709P();
                    if (runnable != null && looper != null) {
                        new Handler(looper).post(runnable);
                    }
                }

                private void m8638a(final int i, @NonNull final String str) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ C29004 f6698c;

                        public void run() {
                            this.f6698c.f6704d.m8720a(AdState.STATE_FAILED);
                            this.f6698c.f6704d.m8752c(str);
                            C2905b a = this.f6698c.f6704d.m8712a(i);
                            if (a == null) {
                                return;
                            }
                            if (SchemaSymbols.ATTVAL_INT.equals(this.f6698c.f6704d.mo6151d())) {
                                a.mo6124b();
                            } else {
                                a.mo6121a(this.f6698c.f6704d, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
                            }
                        }
                    });
                }

                public void run() {
                    try {
                        this.f6704d.f6716a = SystemClock.elapsedRealtime();
                        NativeV2DataModel nativeV2DataModel = new NativeV2DataModel(this.f6704d.mo6153h(), new JSONObject(this.f6704d.m8769j()), this.f6704d.m8775p().m9731p(), this.f6704d.m8782w());
                        if (!nativeV2DataModel.m9094n() || this.f6704d.m8711a() == null) {
                            m8638a(i, "DataModelValidationFailed");
                            return;
                        }
                        ai a = C3003a.m9299a(this.f6704d.m8711a(), new RenderingProperties(this.f6704d.mo6153h()), nativeV2DataModel, this.f6704d.m8772m(), this.f6704d.m8773n(), this.f6704d.m8764g());
                        if (a == null) {
                            m8638a(i, "DataModelValidationFailed");
                            return;
                        }
                        a.m9319a(new C28992(this));
                        m8637a(i, a);
                    } catch (Throwable e) {
                        m8638a(i, "InternalError");
                        C3135c.m10255a().m10279a(new C3132b(e));
                    } catch (Throwable e2) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, AdUnit.f6711b, "Encountered unexpected error in loading ad markup into container: " + e2.getMessage());
                        m8638a(i, "InternalError");
                        C3135c.m10255a().m10279a(new C3132b(e2));
                    }
                }
            }.start();
        }
    }

    protected void m8701H() {
        AdContainer t = mo6167t();
        if (t != null) {
            t.mo6174a(EventType.EVENT_TYPE_AD_SERVED, null);
        }
    }

    void m8702I() {
        C3168c.m10513a().m10526e();
    }

    private C3056i mo6143T() {
        C3056i c3056i = new C3056i();
        c3056i.m9826b(this.f6721g);
        c3056i.m9823a(this.f6722h);
        c3056i.m9818a(this.f6720f);
        c3056i.m9829c(mo6151d());
        c3056i.m9820a(m8775p().m9714a(mo6151d()));
        c3056i.m9827b(mo6166i());
        c3056i.m9831d(m8758e());
        c3056i.m9822a(this.f6723i.m9720e());
        c3056i.m9825b(this.f6723i.m9724i());
        c3056i.m9817a(this.f6723i.m9722g());
        c3056i.m9833e(mo6152f());
        c3056i.m9819a(mo6259x());
        c3056i.m9821a(new C3169d(this.f6723i.m9709r().m10168a()));
        return c3056i;
    }

    void m8726a(C3056i c3056i) {
        this.f6735u = System.currentTimeMillis();
        if (this.f6737w == null) {
            this.f6737w = new C2904a(this);
        }
        try {
            this.f6728n = this.f6725k.m9808a(c3056i);
            if (m8700G() && !this.f6725k.m9812a()) {
                m8731a("ads", "AdPreLoadRequested");
            }
        } catch (C3028a e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, e.getMessage());
            if (!this.f6725k.m9812a()) {
                mo6098a(this.f6720f, new InMobiAdRequestStatus(StatusCode.EARLY_REFRESH_REQUEST));
            }
        }
    }

    protected void mo6141J() {
        this.f6727m = null;
        this.f6740z.clear();
        AdContainer t = mo6167t();
        if (t != null) {
            t.destroy();
        }
        m8720a(AdState.STATE_CREATED);
        this.f6729o = AdMarkupType.AD_MARKUP_TYPE_UNKNOWN;
    }

    public final void mo6103a(RenderView renderView) {
        if (m8711a() != null && this.f6737w != null) {
            this.f6737w.sendEmptyMessage(11);
        }
    }

    protected void mo6252K() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Render view signaled ad ready");
    }

    public final void mo6106b(RenderView renderView) {
        if (m8711a() != null && this.f6737w != null) {
            this.f6737w.sendEmptyMessage(12);
        }
    }

    protected void mo6253L() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Render view signaled ad failed");
        m8752c("RenderFailed");
    }

    public void mo6110c(RenderView renderView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "RenderView completed loading ad content");
    }

    public void mo6111d(RenderView renderView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Renderview visible");
    }

    public void mo6112e(RenderView renderView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Ad failed to display");
        if (AdState.STATE_RENDERED == m8748c()) {
            m8720a(AdState.STATE_FAILED);
            if (m8776q() != null) {
                m8776q().mo6124b();
            }
        }
    }

    public void mo6113f(RenderView renderView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Ad displayed");
    }

    public void mo6114g(RenderView renderView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Ad dismissed");
    }

    public void mo6104a(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Ad reward action completed. Params:" + (hashMap == null ? null : hashMap.toString()));
        if (m8776q() != null) {
            m8776q().mo6125b(hashMap);
        }
    }

    public void mo6107b(RenderView renderView, @NonNull HashMap<Object, Object> hashMap) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Ad interaction. Params:" + (hashMap == null ? null : hashMap.toString()));
        m8731a("ads", "AdInteracted");
        if (m8776q() != null) {
            m8776q().mo6123a((Map) hashMap);
        }
    }

    public void mo6115h(RenderView renderView) {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "User left application");
        if (m8776q() != null) {
            m8776q().mo6129f();
        }
    }

    private void mo6144U() {
        m8706M();
        this.f6734t.sendEmptyMessageDelayed(0, (long) (m8775p().m9726k().m9675i() * 1000));
    }

    protected void m8706M() {
        this.f6734t.removeMessages(0);
    }

    protected void m8707N() {
        new Handler(Looper.getMainLooper()).post(new C29015(this));
    }

    protected void mo6254O() {
        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Renderview timed out.");
        m8752c("RenderTimeOut");
        if (m8748c() == AdState.STATE_AVAILABLE) {
            m8720a(AdState.STATE_FAILED);
            if (m8776q() != null) {
                m8776q().mo6121a(this, new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR));
            }
        }
    }

    protected void m8746b(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        m8753c("ads", "AdLoadRejected", hashMap);
    }

    protected void m8709P() {
        Map hashMap = new HashMap();
        hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f6716a));
        m8753c("ads", "AdLoadSuccessful", hashMap);
    }

    protected void m8752c(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        hashMap.put("loadLatency", Long.valueOf(SystemClock.elapsedRealtime() - this.f6716a));
        m8753c("ads", "AdLoadFailed", hashMap);
    }

    protected void m8757d(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        m8753c("ads", "AdShowFailed", hashMap);
    }

    protected void m8760e(String str) {
        Map hashMap = new HashMap();
        hashMap.put("errorCode", str);
        m8753c("ads", "AdPrefetchRejected", hashMap);
    }

    public void m8763f(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ AdUnit f6707b;

            public void run() {
                Map hashMap = new HashMap();
                hashMap.putAll(DisplayInfo.m10423c());
                hashMap.putAll(C3160b.m10433a());
                hashMap.put("event-name", "sdkpubreq");
                hashMap.put("event-type", str);
                hashMap.put("adtype", this.f6707b.mo6151d());
                hashMap.put("im-plid", String.valueOf(this.f6707b.m8736b()));
                hashMap.put("event-id", UUID.randomUUID().toString());
                hashMap.putAll(C3159a.m10427a().m10431c());
                hashMap.putAll(C3162d.m10441a());
                hashMap.putAll(C3164f.m10487a().m10495d());
                hashMap.putAll(new C3169d(this.f6707b.m8775p().m9709r().m10168a()).m10538a());
                C3213c.m10698a().m10712a(this.f6707b.m8775p().m9721f(), hashMap, true);
            }
        }).start();
    }

    public void mo6108b(String str, String str2, Map<String, Object> map) {
        m8753c(str, str2, map);
    }

    public final void mo6105a(String str, String str2, Map<String, Object> map) {
        m8753c(str, str2, map);
    }

    public final void m8731a(String str, String str2) {
        m8753c(str, str2, new HashMap());
    }

    public final void m8753c(String str, String str2, Map<String, Object> map) {
        Object valueOf;
        Map hashMap = new HashMap();
        hashMap.put("type", mo6151d());
        hashMap.put("plId", Long.valueOf(m8736b()));
        hashMap.put("impId", m8772m());
        String str3 = "isPreloaded";
        if (m8700G()) {
            valueOf = String.valueOf(1);
        } else {
            valueOf = String.valueOf(0);
        }
        hashMap.put(str3, valueOf);
        hashMap.put("networkType", mo6145V());
        if (map.get("clientRequestId") == null) {
            hashMap.put("clientRequestId", m8773n());
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        try {
            C3135c.m10255a().m10280a(str, str2, hashMap);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Error in submitting telemetry event : (" + e.getMessage() + ")");
        }
    }

    private String mo6145V() {
        switch (C3160b.m10434b()) {
            case 0:
                return "carrier";
            case 1:
                return "wifi";
            default:
                return "NIL";
        }
    }

    void m8750c(@NonNull C2968a c2968a) {
        if (c2968a instanceof bq) {
            bq bqVar = (bq) c2968a;
            Context a = m8711a();
            boolean h = m8775p().m9728m().m9703h();
            for (bi biVar : m8764g()) {
                if (h && AdTrackerType.AD_TRACKER_TYPE_IAS == biVar.f7267a && AdCreativeType.AD_CREATIVE_TYPE_VIDEO == biVar.f7268b.get(VastResourceXmlManager.CREATIVE_TYPE)) {
                    try {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Processing AVID meta data to bootstrap impression tracking for this ad response");
                        aw awVar = (aw) new NativeV2DataModel(mo6153h(), new JSONObject(m8769j()), m8775p().m9731p(), new bo(bqVar.m9583j(), bqVar.m9584k(), bqVar.m9585l(), bqVar.m9586m(), bqVar.m9587n(), m8775p().m9731p())).m9072a(AssetType.ASSET_TYPE_VIDEO).get(0);
                        if (a != null) {
                            Set hashSet = new HashSet();
                            for (ah ahVar : awVar.m9011f()) {
                                if (C3000a.TRACKER_EVENT_TYPE_IAS == ahVar.m9297c()) {
                                    hashSet.add(m8689a(ahVar.m9295b(), ahVar.m9298d()));
                                }
                            }
                            if (hashSet.size() != 0) {
                                biVar.f7268b.put("avidAdSession", C3082r.m9958a(a, hashSet));
                                biVar.f7268b.put("deferred", Boolean.valueOf(true));
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "AVID video ad session created and JavaScript resources injected");
                            }
                        }
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f6711b, "Setting up impression tracking for AVID encountered an unexpected error: " + e.getMessage());
                        C3135c.m10255a().m10279a(new C3132b(e));
                    }
                }
            }
        }
    }

    @Nullable
    static String m8689a(String str, Map<String, String> map) {
        if (!(map == null || str == null)) {
            for (String str2 : map.keySet()) {
                str = str.replace(str2, (CharSequence) map.get(str2));
            }
        }
        return str;
    }

    public void m8722a(C2908e c2908e) {
        this.f6714C = c2908e;
    }

    public C2908e m8710Q() {
        return this.f6714C;
    }

    public void mo6097a(long j) {
        m8731a("ads", "AdPrefetchSuccessful");
        if (this.f6737w != null) {
            Message obtain = Message.obtain();
            obtain.what = 14;
            Bundle bundle = new Bundle();
            bundle.putLong("placementId", j);
            obtain.setData(bundle);
            this.f6737w.sendMessage(obtain);
        }
    }

    public void mo6109c(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (StatusCode.EARLY_REFRESH_REQUEST == inMobiAdRequestStatus.getStatusCode()) {
            m8760e("EarlyRefreshRequest");
        } else if (StatusCode.NETWORK_UNREACHABLE == inMobiAdRequestStatus.getStatusCode()) {
            m8760e("NetworkUnreachable");
        } else {
            m8731a("ads", "AdPrefetchFailed");
        }
        if (this.f6737w != null) {
            Message obtain = Message.obtain();
            obtain.what = 13;
            obtain.obj = inMobiAdRequestStatus;
            Bundle bundle = new Bundle();
            bundle.putLong("placementId", j);
            obtain.setData(bundle);
            this.f6737w.sendMessage(obtain);
        }
    }

    public void mo6256b(long j) {
        if (this.f6714C != null) {
            this.f6714C.mo6116a(this);
        }
    }

    public void mo6258d(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (this.f6714C != null) {
            this.f6714C.mo6117a(this, inMobiAdRequestStatus);
        }
    }
}
