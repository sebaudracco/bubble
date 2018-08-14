package com.adcolony.sdk;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdColonyAdViewActivity extends C0589b {
    bc f389n;
    boolean f390o;

    public AdColonyAdViewActivity() {
        this.f389n = !C0594a.m612b() ? null : C0594a.m605a().m1290t();
        this.f390o = this.f389n instanceof AdColonyNativeAdView;
    }

    public /* bridge */ /* synthetic */ void onBackPressed() {
        super.onBackPressed();
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public /* bridge */ /* synthetic */ void onDestroy() {
        super.onDestroy();
    }

    public /* bridge */ /* synthetic */ void onPause() {
        super.onPause();
    }

    public /* bridge */ /* synthetic */ void onResume() {
        super.onResume();
    }

    public /* bridge */ /* synthetic */ void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void onCreate(Bundle bundle) {
        this.d = this.f389n == null ? 0 : this.f389n.f421b;
        super.onCreate(bundle);
        if (C0594a.m612b() && this.f389n != null) {
            C0594a.m605a().m1272d(true);
            C0592e listener = this.f389n.getListener();
            if (listener != null && (listener instanceof AdColonyNativeAdViewListener)) {
                ((AdColonyNativeAdViewListener) listener).onOpened((AdColonyNativeAdView) this.f389n);
            }
        }
    }

    void mo1845a(af afVar) {
        super.mo1845a(afVar);
        if (this.f389n.getExpandedContainer() != null) {
            JSONObject f = C0802y.m1480f(afVar.m698c(), "v4iap");
            JSONArray g = C0802y.m1481g(f, "product_ids");
            C0592e listener = this.f389n.getListener();
            if (listener != null) {
                if (this.f390o) {
                    ((AdColonyNativeAdViewListener) listener).onClosed((AdColonyNativeAdView) this.f389n);
                    if (f != null && g.length() > 0) {
                        ((AdColonyNativeAdViewListener) listener).onIAPEvent((AdColonyNativeAdView) this.f389n, C0802y.m1474c(g, 0), C0802y.m1473c(f, "engagement_type"));
                    }
                } else {
                    ((bd) listener).m1007c(this.f389n);
                    if (f != null && g.length() > 0) {
                        ((bd) listener).m1005a(this.f389n, C0802y.m1474c(g, 0), C0802y.m1473c(f, "engagement_type"));
                    }
                }
            }
            ((ViewGroup) this.f389n.getExpandedContainer().getParent()).removeView(this.f389n.getExpandedContainer());
            C0594a.m605a().m1283m().m1147a(this.f389n.getExpandedContainer());
            this.f389n.setExpandedContainer(null);
            System.gc();
        }
    }
}
