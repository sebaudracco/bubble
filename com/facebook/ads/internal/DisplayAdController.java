package com.facebook.ads.internal;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSettings.TestAdType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.NativeAd;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.BannerAdapter;
import com.facebook.ads.internal.adapters.BannerAdapterListener;
import com.facebook.ads.internal.adapters.C1830a;
import com.facebook.ads.internal.adapters.C1891x;
import com.facebook.ads.internal.adapters.C1894f;
import com.facebook.ads.internal.adapters.C1913u;
import com.facebook.ads.internal.adapters.C1939y;
import com.facebook.ads.internal.adapters.InterstitialAdapter;
import com.facebook.ads.internal.adapters.InterstitialAdapterListener;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.adapters.ac;
import com.facebook.ads.internal.adapters.ae;
import com.facebook.ads.internal.adapters.af;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p056q.p057a.C1866w;
import com.facebook.ads.internal.p056q.p057a.C2110d;
import com.facebook.ads.internal.p056q.p057a.C2120k;
import com.facebook.ads.internal.p056q.p057a.C2123n;
import com.facebook.ads.internal.p056q.p076c.C2147e;
import com.facebook.ads.internal.p056q.p077d.C2150a;
import com.facebook.ads.internal.p056q.p078e.C2151a;
import com.facebook.ads.internal.p058o.C2033b;
import com.facebook.ads.internal.p058o.C2038c;
import com.facebook.ads.internal.p058o.C2038c.C1870a;
import com.facebook.ads.internal.p058o.C2043g;
import com.facebook.ads.internal.p065h.C1987a;
import com.facebook.ads.internal.p065h.C1989c;
import com.facebook.ads.internal.p065h.C1990d;
import com.facebook.ads.internal.p065h.C1991e;
import com.facebook.ads.internal.p066i.C1992a;
import com.facebook.ads.internal.p066i.C1995c;
import com.facebook.ads.internal.p068l.C2005a;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p069m.C2014d;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2098b;
import com.facebook.ads.internal.protocol.C2100c;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.C2105h;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.protocol.f;
import com.facebook.ads.p051a.C1835a;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisplayAdController implements C1870a {
    private static final String f4093b = DisplayAdController.class.getSimpleName();
    private static final Handler f4094h = new Handler(Looper.getMainLooper());
    private static boolean f4095i = false;
    private boolean f4096A;
    private final C2012c f4097B;
    private final EnumSet<CacheFlag> f4098C;
    protected C1830a f4099a;
    private final Context f4100c;
    private final String f4101d;
    private final AdPlacementType f4102e;
    private final C2038c f4103f;
    private final Handler f4104g;
    private final Runnable f4105j;
    private final Runnable f4106k;
    private volatile boolean f4107l;
    private boolean f4108m;
    private volatile boolean f4109n;
    private AdAdapter f4110o;
    private AdAdapter f4111p;
    private View f4112q;
    private C1989c f4113r;
    private C2033b f4114s;
    private f f4115t;
    private C2101d f4116u;
    private e f4117v;
    private int f4118w;
    private boolean f4119x;
    private int f4120y;
    private final C1869c f4121z;

    class C18615 implements Runnable {
        final /* synthetic */ DisplayAdController f4084a;

        C18615(DisplayAdController displayAdController) {
            this.f4084a = displayAdController;
        }

        public void run() {
            try {
                this.f4084a.m5602m();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C18626 implements C1835a {
        final /* synthetic */ DisplayAdController f4085a;

        C18626(DisplayAdController displayAdController) {
            this.f4085a = displayAdController;
        }

        public void mo3564a(C1913u c1913u) {
            this.f4085a.f4111p = c1913u;
            this.f4085a.f4109n = false;
            this.f4085a.f4099a.mo3553a((AdAdapter) c1913u);
        }

        public void mo3565a(C1913u c1913u, View view) {
            this.f4085a.f4099a.mo3552a(view);
        }

        public void mo3566a(C1913u c1913u, AdError adError) {
            this.f4085a.f4099a.mo3554a(new C2097a(adError.getErrorCode(), adError.getErrorMessage()));
        }

        public void mo3567b(C1913u c1913u) {
            this.f4085a.f4099a.mo3551a();
        }

        public void mo3568c(C1913u c1913u) {
            this.f4085a.f4099a.mo3555b();
        }

        public void mo3569d(C1913u c1913u) {
            this.f4085a.f4099a.mo3563c();
        }
    }

    class C18637 implements af {
        final /* synthetic */ DisplayAdController f4086a;

        C18637(DisplayAdController displayAdController) {
            this.f4086a = displayAdController;
        }

        public void mo3605a() {
            this.f4086a.f4099a.mo3592g();
        }

        public void mo3606a(ae aeVar) {
            this.f4086a.f4111p = aeVar;
            this.f4086a.f4099a.mo3553a((AdAdapter) aeVar);
        }

        public void mo3607a(ae aeVar, AdError adError) {
            this.f4086a.f4099a.mo3554a(new C2097a(AdErrorType.INTERNAL_ERROR, null));
            this.f4086a.m5575a((AdAdapter) aeVar);
            this.f4086a.m5599l();
        }

        public void mo3608b(ae aeVar) {
            this.f4086a.f4099a.mo3551a();
        }

        public void mo3609c(ae aeVar) {
            this.f4086a.f4099a.mo3555b();
        }

        public void mo3610d(ae aeVar) {
            this.f4086a.f4099a.mo3591f();
        }

        public void mo3611e(ae aeVar) {
            this.f4086a.f4099a.mo3593h();
        }

        public void mo3612f(ae aeVar) {
            this.f4086a.f4099a.mo3594i();
        }
    }

    private static final class C1867a extends C1866w<DisplayAdController> {
        public C1867a(DisplayAdController displayAdController) {
            super(displayAdController);
        }

        public void run() {
            DisplayAdController displayAdController = (DisplayAdController) m5564a();
            if (displayAdController != null) {
                displayAdController.f4107l = false;
                displayAdController.m5587c(null);
            }
        }
    }

    private static final class C1868b extends C1866w<DisplayAdController> {
        public C1868b(DisplayAdController displayAdController) {
            super(displayAdController);
        }

        public void run() {
            DisplayAdController displayAdController = (DisplayAdController) m5564a();
            if (displayAdController != null) {
                displayAdController.m5604n();
            }
        }
    }

    private class C1869c extends BroadcastReceiver {
        final /* synthetic */ DisplayAdController f4092a;

        private C1869c(DisplayAdController displayAdController) {
            this.f4092a = displayAdController;
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                this.f4092a.m5605o();
            } else if ("android.intent.action.SCREEN_ON".equals(action)) {
                this.f4092a.m5604n();
            }
        }
    }

    static {
        C2110d.m6771a();
    }

    public DisplayAdController(Context context, String str, f fVar, AdPlacementType adPlacementType, e eVar, C2101d c2101d, int i, boolean z) {
        this(context, str, fVar, adPlacementType, eVar, c2101d, i, z, EnumSet.of(CacheFlag.NONE));
    }

    public DisplayAdController(Context context, String str, f fVar, AdPlacementType adPlacementType, e eVar, C2101d c2101d, int i, boolean z, EnumSet<CacheFlag> enumSet) {
        this.f4104g = new Handler();
        this.f4119x = false;
        this.f4120y = -1;
        this.f4100c = context.getApplicationContext();
        this.f4101d = str;
        this.f4115t = fVar;
        this.f4102e = adPlacementType;
        this.f4117v = eVar;
        this.f4116u = c2101d;
        this.f4118w = i;
        this.f4121z = new C1869c();
        this.f4098C = enumSet;
        this.f4103f = new C2038c(this.f4100c);
        this.f4103f.m6538a((C1870a) this);
        this.f4105j = new C1867a(this);
        this.f4106k = new C1868b(this);
        this.f4108m = z;
        m5594i();
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.f4100c);
            }
        } catch (Throwable e) {
            Log.w(f4093b, "Failed to initialize CookieManager.", e);
        }
        C1992a.m6302a(this.f4100c).m6303a();
        this.f4097B = C2014d.m6413a(this.f4100c);
    }

    private Map<String, String> m5570a(long j) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("delay", String.valueOf(System.currentTimeMillis() - j));
        return hashMap;
    }

    private void m5575a(AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }

    private void m5576a(final BannerAdapter bannerAdapter, C1989c c1989c, Map<String, Object> map) {
        final Runnable c18648 = new Runnable(this) {
            final /* synthetic */ DisplayAdController f4088b;

            public void run() {
                this.f4088b.m5575a(bannerAdapter);
                this.f4088b.m5599l();
            }
        };
        this.f4104g.postDelayed(c18648, (long) c1989c.m6286a().m6301j());
        bannerAdapter.loadBannerAd(this.f4100c, this.f4097B, this.f4117v, new BannerAdapterListener(this) {
            final /* synthetic */ DisplayAdController f4090b;

            public void onBannerAdClicked(BannerAdapter bannerAdapter) {
                this.f4090b.f4099a.mo3551a();
            }

            public void onBannerAdExpanded(BannerAdapter bannerAdapter) {
                this.f4090b.m5605o();
            }

            public void onBannerAdLoaded(BannerAdapter bannerAdapter, View view) {
                if (bannerAdapter == this.f4090b.f4110o) {
                    this.f4090b.f4104g.removeCallbacks(c18648);
                    AdAdapter f = this.f4090b.f4111p;
                    this.f4090b.f4111p = bannerAdapter;
                    this.f4090b.f4112q = view;
                    if (this.f4090b.f4109n) {
                        this.f4090b.f4099a.mo3552a(view);
                        this.f4090b.m5575a(f);
                        this.f4090b.m5604n();
                        return;
                    }
                    this.f4090b.f4099a.mo3553a((AdAdapter) bannerAdapter);
                }
            }

            public void onBannerAdMinimized(BannerAdapter bannerAdapter) {
                this.f4090b.m5604n();
            }

            public void onBannerError(BannerAdapter bannerAdapter, AdError adError) {
                if (bannerAdapter == this.f4090b.f4110o) {
                    this.f4090b.f4104g.removeCallbacks(c18648);
                    this.f4090b.m5575a((AdAdapter) bannerAdapter);
                    this.f4090b.m5599l();
                }
            }

            public void onBannerLoggingImpression(BannerAdapter bannerAdapter) {
                this.f4090b.f4099a.mo3555b();
            }
        }, map);
    }

    private void m5577a(final InterstitialAdapter interstitialAdapter, C1989c c1989c, Map<String, Object> map) {
        final Runnable anonymousClass10 = new Runnable(this) {
            final /* synthetic */ DisplayAdController f4065b;

            public void run() {
                this.f4065b.m5575a(interstitialAdapter);
                this.f4065b.m5599l();
            }
        };
        this.f4104g.postDelayed(anonymousClass10, (long) c1989c.m6286a().m6301j());
        interstitialAdapter.loadInterstitialAd(this.f4100c, new InterstitialAdapterListener(this) {
            final /* synthetic */ DisplayAdController f4067b;

            public void onInterstitialAdClicked(InterstitialAdapter interstitialAdapter, String str, boolean z) {
                this.f4067b.f4099a.mo3551a();
                Object obj = !TextUtils.isEmpty(str) ? 1 : null;
                if (z && obj != null) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    if (!(this.f4067b.f4114s.f4832b instanceof Activity)) {
                        intent.addFlags(ErrorDialogData.BINDER_CRASH);
                    }
                    intent.setData(Uri.parse(str));
                    this.f4067b.f4114s.f4832b.startActivity(intent);
                }
            }

            public void onInterstitialAdDismissed(InterstitialAdapter interstitialAdapter) {
                this.f4067b.f4099a.mo3571e();
            }

            public void onInterstitialAdDisplayed(InterstitialAdapter interstitialAdapter) {
                this.f4067b.f4099a.mo3570d();
            }

            public void onInterstitialAdLoaded(InterstitialAdapter interstitialAdapter) {
                if (interstitialAdapter == this.f4067b.f4110o) {
                    if (interstitialAdapter == null) {
                        C2150a.m6888a(new C2098b(AdErrorType.NO_ADAPTER_ON_LOAD, "Adapter is null on loadInterstitialAd"), this.f4067b.f4100c);
                        onInterstitialError(interstitialAdapter, AdError.INTERNAL_ERROR);
                        return;
                    }
                    this.f4067b.f4104g.removeCallbacks(anonymousClass10);
                    this.f4067b.f4111p = interstitialAdapter;
                    this.f4067b.f4099a.mo3553a((AdAdapter) interstitialAdapter);
                    this.f4067b.m5604n();
                }
            }

            public void onInterstitialError(InterstitialAdapter interstitialAdapter, AdError adError) {
                if (interstitialAdapter == this.f4067b.f4110o) {
                    this.f4067b.f4104g.removeCallbacks(anonymousClass10);
                    this.f4067b.m5575a((AdAdapter) interstitialAdapter);
                    this.f4067b.m5599l();
                    this.f4067b.f4099a.mo3554a(new C2097a(adError.getErrorCode(), adError.getErrorMessage()));
                }
            }

            public void onInterstitialLoggingImpression(InterstitialAdapter interstitialAdapter) {
                this.f4067b.f4099a.mo3555b();
            }
        }, map, this.f4097B, this.f4098C);
    }

    private void m5578a(ab abVar, C1989c c1989c, C1987a c1987a, Map<String, Object> map) {
        final long currentTimeMillis = System.currentTimeMillis();
        final ab abVar2 = abVar;
        final C1987a c1987a2 = c1987a;
        Runnable anonymousClass12 = new Runnable(this) {
            final /* synthetic */ DisplayAdController f4071d;

            public void run() {
                this.f4071d.m5575a(abVar2);
                if (abVar2 instanceof C1891x) {
                    C2110d.m6772a(this.f4071d.f4100c, C1939y.m6121a(((C1891x) abVar2).mo3634F()) + " Failed. Ad request timed out");
                }
                Map a = this.f4071d.m5570a(currentTimeMillis);
                a.put("error", "-1");
                a.put(NotificationCompat.CATEGORY_MESSAGE, "timeout");
                this.f4071d.m5581a(c1987a2.m6281a(C1991e.REQUEST), a);
                this.f4071d.m5599l();
            }
        };
        this.f4104g.postDelayed(anonymousClass12, (long) c1989c.m6286a().m6301j());
        final Runnable runnable = anonymousClass12;
        final long j = currentTimeMillis;
        final C1987a c1987a3 = c1987a;
        abVar.mo3636a(this.f4100c, new ac(this) {
            boolean f4074a = false;
            boolean f4075b = false;
            boolean f4076c = false;
            final /* synthetic */ DisplayAdController f4080g;

            public void mo3601a(ab abVar) {
                if (abVar == this.f4080g.f4110o) {
                    this.f4080g.f4104g.removeCallbacks(runnable);
                    this.f4080g.f4111p = abVar;
                    this.f4080g.f4099a.mo3553a((AdAdapter) abVar);
                    if (!this.f4074a) {
                        this.f4074a = true;
                        this.f4080g.m5581a(c1987a3.m6281a(C1991e.REQUEST), this.f4080g.m5570a(j));
                    }
                }
            }

            public void mo3602a(ab abVar, C2097a c2097a) {
                if (abVar == this.f4080g.f4110o) {
                    this.f4080g.f4104g.removeCallbacks(runnable);
                    this.f4080g.m5575a((AdAdapter) abVar);
                    if (!this.f4074a) {
                        this.f4074a = true;
                        Map a = this.f4080g.m5570a(j);
                        a.put("error", String.valueOf(c2097a.m6748a().getErrorCode()));
                        a.put(NotificationCompat.CATEGORY_MESSAGE, String.valueOf(c2097a.m6749b()));
                        this.f4080g.m5581a(c1987a3.m6281a(C1991e.REQUEST), a);
                    }
                    this.f4080g.m5599l();
                }
            }

            public void mo3603b(ab abVar) {
                if (!this.f4075b) {
                    this.f4075b = true;
                    this.f4080g.m5581a(c1987a3.m6281a(C1991e.IMPRESSION), null);
                }
            }

            public void mo3604c(ab abVar) {
                if (!this.f4076c) {
                    this.f4076c = true;
                    this.f4080g.m5581a(c1987a3.m6281a(C1991e.CLICK), null);
                }
                if (this.f4080g.f4099a != null) {
                    this.f4080g.f4099a.mo3551a();
                }
            }
        }, this.f4097B, map, NativeAd.getViewTraversalPredicate());
    }

    private void m5579a(ae aeVar, C1989c c1989c, Map<String, Object> map) {
        aeVar.mo3694a(this.f4100c, new C18637(this), map, this.f4119x);
    }

    private void m5580a(C1913u c1913u, C1989c c1989c, Map<String, Object> map) {
        c1913u.mo3676a(this.f4100c, new C18626(this), map, this.f4097B, this.f4098C);
    }

    private void m5581a(List<String> list, Map<String, String> map) {
        if (list != null && !list.isEmpty()) {
            for (String str : list) {
                new C2147e(this.f4100c, map).execute(new String[]{str});
            }
        }
    }

    private void m5587c(String str) {
        String str2 = null;
        try {
            C2105h c2105h = new C2105h(this.f4100c, str, this.f4101d, this.f4115t);
            Context context = this.f4100c;
            C1995c c1995c = new C1995c(this.f4100c, false);
            String str3 = this.f4101d;
            C2120k c2120k = this.f4117v != null ? new C2120k(this.f4117v.b(), this.f4117v.a()) : null;
            f fVar = this.f4115t;
            C2101d c2101d = this.f4116u;
            if (AdSettings.getTestAdType() != TestAdType.DEFAULT) {
                str2 = AdSettings.getTestAdType().getAdTypeString();
            }
            this.f4114s = new C2033b(context, c1995c, str3, c2120k, fVar, c2101d, str2, C1894f.m5819a(C2100c.m6752a(this.f4115t).m6753a()), this.f4118w, AdSettings.isTestMode(this.f4100c), AdSettings.isChildDirected(), c2105h, C2123n.m6808a(C2005a.m6352m(this.f4100c)));
            this.f4103f.m6537a(this.f4114s);
        } catch (C2098b e) {
            mo3620a(C2097a.m6747a(e));
        }
    }

    private void m5594i() {
        if (!this.f4108m) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.f4100c.registerReceiver(this.f4121z, intentFilter);
            this.f4096A = true;
        }
    }

    private void m5596j() {
        if (this.f4096A) {
            try {
                this.f4100c.unregisterReceiver(this.f4121z);
                this.f4096A = false;
            } catch (Throwable e) {
                C1999b.m6321a(C1998a.m6318a(e, "Error unregistering screen state receiever"));
            }
        }
    }

    private AdPlacementType m5597k() {
        return this.f4102e != null ? this.f4102e : this.f4117v == null ? AdPlacementType.NATIVE : this.f4117v == e.b ? AdPlacementType.INTERSTITIAL : AdPlacementType.BANNER;
    }

    private synchronized void m5599l() {
        f4094h.post(new C18615(this));
    }

    private void m5602m() {
        this.f4110o = null;
        C1989c c1989c = this.f4113r;
        C1987a d = c1989c.m6290d();
        if (d == null) {
            this.f4099a.mo3554a(C2097a.m6746a(AdErrorType.NO_FILL, ""));
            m5604n();
            return;
        }
        String a = d.m6280a();
        AdAdapter a2 = C1894f.m5818a(a, c1989c.m6286a().m6293b());
        if (a2 == null) {
            Log.e(f4093b, "Adapter does not exist: " + a);
            m5599l();
        } else if (m5597k() != a2.getPlacementType()) {
            this.f4099a.mo3554a(C2097a.m6746a(AdErrorType.INTERNAL_ERROR, ""));
        } else {
            this.f4110o = a2;
            Map hashMap = new HashMap();
            C1990d a3 = c1989c.m6286a();
            hashMap.put("data", d.m6282b());
            hashMap.put("definition", a3);
            hashMap.put("placementId", this.f4101d);
            hashMap.put(AudienceNetworkActivity.REQUEST_TIME, Long.valueOf(a3.m6292a()));
            if (this.f4114s == null) {
                this.f4099a.mo3554a(C2097a.m6746a(AdErrorType.UNKNOWN_ERROR, "environment is empty"));
                return;
            }
            switch (a2.getPlacementType()) {
                case INTERSTITIAL:
                    m5577a((InterstitialAdapter) a2, c1989c, hashMap);
                    return;
                case BANNER:
                    m5576a((BannerAdapter) a2, c1989c, hashMap);
                    return;
                case NATIVE:
                    m5578a((ab) a2, c1989c, d, hashMap);
                    return;
                case INSTREAM:
                    m5580a((C1913u) a2, c1989c, hashMap);
                    return;
                case REWARDED_VIDEO:
                    m5579a((ae) a2, c1989c, hashMap);
                    return;
                default:
                    Log.e(f4093b, "attempt unexpected adapter type");
                    return;
            }
        }
    }

    private void m5604n() {
        if (!this.f4108m && !this.f4107l) {
            switch (m5597k()) {
                case INTERSTITIAL:
                    if (!C2151a.m6889a(this.f4100c)) {
                        this.f4104g.postDelayed(this.f4106k, 1000);
                        break;
                    }
                    break;
                case BANNER:
                    boolean a = C2154a.m6899a(this.f4112q, this.f4113r == null ? 1 : this.f4113r.m6286a().m6297f()).m6923a();
                    if (!(this.f4112q == null || a)) {
                        this.f4104g.postDelayed(this.f4106k, 1000);
                        return;
                    }
                default:
                    return;
            }
            long c = this.f4113r == null ? 30000 : this.f4113r.m6286a().m6294c();
            if (c > 0) {
                this.f4104g.postDelayed(this.f4105j, c);
                this.f4107l = true;
            }
        }
    }

    private void m5605o() {
        if (this.f4107l) {
            this.f4104g.removeCallbacks(this.f4105j);
            this.f4107l = false;
        }
    }

    private Handler m5606p() {
        return !m5607q() ? this.f4104g : f4094h;
    }

    private static synchronized boolean m5607q() {
        boolean z;
        synchronized (DisplayAdController.class) {
            z = f4095i;
        }
        return z;
    }

    protected static synchronized void setMainThreadForced(boolean z) {
        synchronized (DisplayAdController.class) {
            Log.d(f4093b, "DisplayAdController changed main thread forced from " + f4095i + " to " + z);
            f4095i = z;
        }
    }

    public C1990d m5608a() {
        return this.f4113r == null ? null : this.f4113r.m6286a();
    }

    public void m5609a(int i) {
        this.f4120y = i;
    }

    public void m5610a(RewardData rewardData) {
        if (this.f4111p == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        } else if (this.f4111p.getPlacementType() != AdPlacementType.REWARDED_VIDEO) {
            throw new IllegalStateException("can only set on rewarded video ads");
        } else {
            ((ae) this.f4111p).m5746a(rewardData);
        }
    }

    public void m5611a(C1830a c1830a) {
        this.f4099a = c1830a;
    }

    public synchronized void mo3619a(final C2043g c2043g) {
        m5606p().post(new Runnable(this) {
            final /* synthetic */ DisplayAdController f4073b;

            public void run() {
                C1989c a = c2043g.mo3732a();
                if (a == null || a.m6286a() == null) {
                    throw new IllegalStateException("invalid placement in response");
                }
                this.f4073b.f4113r = a;
                this.f4073b.m5599l();
            }
        });
    }

    public synchronized void mo3620a(final C2097a c2097a) {
        m5606p().post(new Runnable(this) {
            final /* synthetic */ DisplayAdController f4082b;

            public void run() {
                this.f4082b.f4099a.mo3554a(c2097a);
                if (!this.f4082b.f4108m && !this.f4082b.f4107l) {
                    switch (c2097a.m6748a().getErrorCode()) {
                        case 1000:
                        case 1002:
                            switch (this.f4082b.m5597k()) {
                                case BANNER:
                                    this.f4082b.f4104g.postDelayed(this.f4082b.f4105j, 30000);
                                    this.f4082b.f4107l = true;
                                    return;
                                default:
                                    return;
                            }
                        default:
                            return;
                    }
                }
            }
        });
    }

    public void m5614a(String str) {
        m5587c(str);
    }

    public void m5615a(boolean z) {
        this.f4119x = z;
    }

    public void m5616b() {
        if (this.f4111p == null) {
            C2150a.m6888a(new C2098b(AdErrorType.NO_ADAPTER_ON_START, "Adapter is null on startAd"), this.f4100c);
            this.f4099a.mo3554a(C2097a.m6746a(AdErrorType.INTERNAL_ERROR, AdErrorType.INTERNAL_ERROR.getDefaultErrorMessage()));
        } else if (this.f4109n) {
            throw new IllegalStateException("ad already started");
        } else {
            this.f4109n = true;
            switch (this.f4111p.getPlacementType()) {
                case INTERSTITIAL:
                    ((InterstitialAdapter) this.f4111p).show();
                    return;
                case BANNER:
                    if (this.f4112q != null) {
                        this.f4099a.mo3552a(this.f4112q);
                        m5604n();
                        return;
                    }
                    return;
                case NATIVE:
                    ab abVar = (ab) this.f4111p;
                    if (abVar.c_()) {
                        this.f4099a.mo3723a(abVar);
                        return;
                    }
                    throw new IllegalStateException("ad is not ready or already displayed");
                case INSTREAM:
                    ((C1913u) this.f4111p).mo3677e();
                    return;
                case REWARDED_VIDEO:
                    ae aeVar = (ae) this.f4111p;
                    aeVar.m5744a(this.f4120y);
                    aeVar.mo3695b();
                    return;
                default:
                    Log.e(f4093b, "start unexpected adapter type");
                    return;
            }
        }
    }

    public void m5617b(String str) {
        m5605o();
        m5587c(str);
    }

    public void m5618b(boolean z) {
        m5596j();
        if (z || this.f4109n) {
            m5605o();
            m5575a(this.f4111p);
            this.f4103f.m6536a();
            this.f4112q = null;
            this.f4109n = false;
        }
    }

    public void m5619c() {
        m5618b(false);
    }

    public void m5620d() {
        if (this.f4109n) {
            m5605o();
        }
    }

    public void m5621e() {
        if (this.f4109n) {
            m5604n();
        }
    }

    public void m5622f() {
        this.f4108m = true;
        m5605o();
    }

    public C2012c m5623g() {
        return this.f4097B;
    }

    public AdAdapter m5624h() {
        return this.f4111p;
    }
}
