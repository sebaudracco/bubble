package com.facebook.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.internal.C1883a;
import com.facebook.ads.internal.C1883a.C1852a;
import com.facebook.ads.internal.adapters.ab;
import com.facebook.ads.internal.p055d.C1850a;
import com.facebook.ads.internal.p055d.C1960b;
import com.facebook.ads.internal.protocol.C2097a;
import com.facebook.ads.internal.protocol.C2101d;
import com.facebook.ads.internal.protocol.f;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class NativeAdsManager {
    private static final String f4038a = NativeAdsManager.class.getSimpleName();
    private static final C2101d f4039b = C2101d.ADS;
    private final Context f4040c;
    private final String f4041d;
    private final int f4042e;
    private final List<NativeAd> f4043f;
    private int f4044g;
    private Listener f4045h;
    private C1883a f4046i;
    private boolean f4047j;
    private boolean f4048k;

    public interface Listener {
        void onAdError(AdError adError);

        void onAdsLoaded();
    }

    public NativeAdsManager(Context context, String str, int i) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        this.f4040c = context;
        this.f4041d = str;
        this.f4042e = Math.max(i, 0);
        this.f4043f = new ArrayList(i);
        this.f4044g = -1;
        this.f4048k = false;
        this.f4047j = false;
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(context);
            }
        } catch (Throwable e) {
            Log.w(f4038a, "Failed to initialize CookieManager.", e);
        }
    }

    public void disableAutoRefresh() {
        this.f4047j = true;
        if (this.f4046i != null) {
            this.f4046i.m5669c();
        }
    }

    public int getUniqueNativeAdCount() {
        return this.f4043f.size();
    }

    public boolean isLoaded() {
        return this.f4048k;
    }

    public void loadAds() {
        loadAds(EnumSet.of(NativeAd$MediaCacheFlag.NONE));
    }

    public void loadAds(final EnumSet<NativeAd$MediaCacheFlag> enumSet) {
        f fVar = f.j;
        int i = this.f4042e;
        if (this.f4046i != null) {
            this.f4046i.m5668b();
        }
        this.f4046i = new C1883a(this.f4040c, this.f4041d, fVar, null, f4039b, i, enumSet);
        if (this.f4047j) {
            this.f4046i.m5669c();
        }
        this.f4046i.m5665a(new C1852a(this) {
            final /* synthetic */ NativeAdsManager f4037b;

            public void mo3589a(C2097a c2097a) {
                if (this.f4037b.f4045h != null) {
                    this.f4037b.f4045h.onAdError(AdError.getAdErrorFromWrapper(c2097a));
                }
            }

            public void mo3590a(final List<ab> list) {
                C1960b c1960b = new C1960b(this.f4037b.f4040c);
                for (ab abVar : list) {
                    if (enumSet.contains(NativeAd$MediaCacheFlag.ICON) && abVar.mo3652l() != null) {
                        c1960b.m6171a(abVar.mo3652l().m6473a(), abVar.mo3652l().m6475c(), abVar.mo3652l().m6474b());
                    }
                    if (enumSet.contains(NativeAd$MediaCacheFlag.IMAGE) && abVar.mo3653m() != null) {
                        c1960b.m6171a(abVar.mo3653m().m6473a(), abVar.mo3653m().m6475c(), abVar.mo3653m().m6474b());
                    }
                    if (enumSet.contains(NativeAd$MediaCacheFlag.VIDEO) && !TextUtils.isEmpty(abVar.mo3665x())) {
                        c1960b.m6170a(abVar.mo3665x());
                    }
                }
                c1960b.m6169a(new C1850a(this) {
                    final /* synthetic */ C18531 f4035b;

                    private void m5506c() {
                        this.f4035b.f4037b.f4048k = true;
                        this.f4035b.f4037b.f4043f.clear();
                        this.f4035b.f4037b.f4044g = 0;
                        for (ab nativeAd : list) {
                            this.f4035b.f4037b.f4043f.add(new NativeAd(this.f4035b.f4037b.f4040c, nativeAd, null));
                        }
                        if (this.f4035b.f4037b.f4045h != null) {
                            this.f4035b.f4037b.f4045h.onAdsLoaded();
                        }
                    }

                    public void mo3587a() {
                        m5506c();
                    }

                    public void mo3588b() {
                        m5506c();
                    }
                });
            }
        });
        this.f4046i.m5664a();
    }

    public NativeAd nextNativeAd() {
        if (this.f4043f.size() == 0) {
            return null;
        }
        int i = this.f4044g;
        this.f4044g = i + 1;
        NativeAd nativeAd = (NativeAd) this.f4043f.get(i % this.f4043f.size());
        return i >= this.f4043f.size() ? new NativeAd(nativeAd) : nativeAd;
    }

    public void setListener(Listener listener) {
        this.f4045h = listener;
    }
}
