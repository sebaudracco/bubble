package com.inmobi.ads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.inmobi.ads.C3046c.C3044h;
import com.inmobi.ads.ViewableAd.AdEvent;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.moat.analytics.mobile.inm.MoatFactory;
import com.moat.analytics.mobile.inm.WebAdTracker;
import java.lang.ref.WeakReference;
import java.util.Map;

/* compiled from: MoatTrackedHtmlAd */
public class C3098x extends br {
    private static final String f7575b = C3098x.class.getSimpleName();
    @NonNull
    private final WeakReference<Activity> f7576c;
    @NonNull
    private final ViewableAd f7577d;
    @NonNull
    private final Map<String, Object> f7578e;
    private WebAdTracker f7579f;

    public C3098x(@NonNull Activity activity, @NonNull ViewableAd viewableAd, @NonNull Map<String, Object> map) {
        this.f7576c = new WeakReference(activity);
        this.f7577d = viewableAd;
        this.f7578e = map;
    }

    @Nullable
    public View mo6225a() {
        return this.f7577d.mo6225a();
    }

    @Nullable
    public View mo6226a(View view, ViewGroup viewGroup, boolean z) {
        return this.f7577d.mo6226a(view, viewGroup, z);
    }

    @Nullable
    public View mo6249b() {
        return this.f7577d.mo6249b();
    }

    public void mo6228a(@NonNull C3044h c3044h, @Nullable View... viewArr) {
        try {
            View b = mo6249b();
            if (b != null) {
                Activity activity = (Activity) this.f7576c.get();
                if (c3044h.m9702g() && activity != null && ((Boolean) this.f7578e.get("enabled")).booleanValue()) {
                    if (this.f7579f == null) {
                        this.f7579f = MoatFactory.create(activity).createWebAdTracker((WebView) b);
                    }
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7575b, "Moat init result for HTML : " + this.f7579f.track());
                }
                this.f7577d.mo6228a(c3044h, viewArr);
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7575b, "Exception in startTrackingForImpression with message : " + e.getMessage());
        } finally {
            this.f7577d.mo6228a(c3044h, viewArr);
        }
    }

    public void mo6229c() {
        this.f7577d.mo6229c();
    }

    public void mo6227a(AdEvent adEvent) {
        this.f7577d.mo6227a(adEvent);
    }

    public void mo6230d() {
        this.f7579f = null;
        this.f7576c.clear();
        super.mo6230d();
        this.f7577d.mo6230d();
    }
}
