package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity$BackButtonInterceptor;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p076c.C2142b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.p079a.C2164a;
import com.facebook.ads.internal.view.p079a.C2164a.C2163a;
import com.facebook.ads.internal.view.p079a.C2165b;
import com.facebook.ads.internal.view.p079a.C2168c.C2167a;
import com.facebook.ads.internal.view.p079a.C2174f;
import com.facebook.ads.internal.view.p079a.C2174f.C2173a;

@TargetApi(19)
public class C2187b implements C1923a {
    private static final String f5300a = C2187b.class.getSimpleName();
    private final AudienceNetworkActivity f5301b;
    private final C2164a f5302c;
    private final C2174f f5303d;
    private final C2165b f5304e;
    private final C2012c f5305f;
    private final AudienceNetworkActivity$BackButtonInterceptor f5306g = new C21751(this);
    private String f5307h;
    private String f5308i;
    private long f5309j;
    private boolean f5310k = true;
    private long f5311l = -1;
    private boolean f5312m = true;

    class C21751 implements AudienceNetworkActivity$BackButtonInterceptor {
        final /* synthetic */ C2187b f5243a;

        C21751(C2187b c2187b) {
            this.f5243a = c2187b;
        }

        public boolean interceptBackButton() {
            if (!this.f5243a.f5303d.canGoBack()) {
                return false;
            }
            this.f5243a.f5303d.goBack();
            return true;
        }
    }

    class C21773 implements C2173a {
        final /* synthetic */ C2187b f5246a;

        C21773(C2187b c2187b) {
            this.f5246a = c2187b;
        }

        public void mo3766a(int i) {
            if (this.f5246a.f5310k) {
                this.f5246a.f5304e.setProgress(i);
            }
        }

        public void mo3767a(String str) {
            this.f5246a.f5310k = true;
            this.f5246a.f5302c.setUrl(str);
        }

        public void mo3768b(String str) {
            this.f5246a.f5302c.setTitle(str);
        }

        public void mo3769c(String str) {
            this.f5246a.f5304e.setProgress(100);
            this.f5246a.f5310k = false;
        }
    }

    public C2187b(final AudienceNetworkActivity audienceNetworkActivity, C2012c c2012c, C1832a c1832a) {
        this.f5301b = audienceNetworkActivity;
        this.f5305f = c2012c;
        int i = (int) (2.0f * C2133v.f5083b);
        this.f5302c = new C2164a(audienceNetworkActivity);
        this.f5302c.setId(View.generateViewId());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.f5302c.setLayoutParams(layoutParams);
        this.f5302c.setListener(new C2163a(this) {
            final /* synthetic */ C2187b f5245b;

            public void mo3765a() {
                audienceNetworkActivity.finish();
            }
        });
        c1832a.mo3560a(this.f5302c);
        this.f5303d = new C2174f(audienceNetworkActivity);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.f5302c.getId());
        layoutParams.addRule(12);
        this.f5303d.setLayoutParams(layoutParams);
        this.f5303d.setListener(new C21773(this));
        c1832a.mo3560a(this.f5303d);
        this.f5304e = new C2165b(audienceNetworkActivity, null, 16842872);
        layoutParams = new RelativeLayout.LayoutParams(-1, i);
        layoutParams.addRule(3, this.f5302c.getId());
        this.f5304e.setLayoutParams(layoutParams);
        this.f5304e.setProgress(0);
        c1832a.mo3560a(this.f5304e);
        audienceNetworkActivity.addBackButtonInterceptor(this.f5306g);
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f5311l < 0) {
            this.f5311l = System.currentTimeMillis();
        }
        if (bundle == null) {
            this.f5307h = intent.getStringExtra(AudienceNetworkActivity.BROWSER_URL);
            this.f5308i = intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN);
            this.f5309j = intent.getLongExtra(AudienceNetworkActivity.HANDLER_TIME, -1);
        } else {
            this.f5307h = bundle.getString(AudienceNetworkActivity.BROWSER_URL);
            this.f5308i = bundle.getString(AudienceNetworkActivity.CLIENT_TOKEN);
            this.f5309j = bundle.getLong(AudienceNetworkActivity.HANDLER_TIME, -1);
        }
        String str = this.f5307h != null ? this.f5307h : "about:blank";
        this.f5302c.setUrl(str);
        this.f5303d.loadUrl(str);
    }

    public void mo3684a(Bundle bundle) {
        bundle.putString(AudienceNetworkActivity.BROWSER_URL, this.f5307h);
    }

    public void mo3686i() {
        this.f5303d.onPause();
        if (this.f5312m) {
            this.f5312m = false;
            this.f5305f.mo3715f(this.f5308i, new C2167a(this.f5303d.getFirstUrl()).m6940a(this.f5309j).m6942b(this.f5311l).m6943c(this.f5303d.getResponseEndMs()).m6944d(this.f5303d.getDomContentLoadedMs()).m6945e(this.f5303d.getScrollReadyMs()).m6946f(this.f5303d.getLoadFinishMs()).m6947g(System.currentTimeMillis()).m6941a().m6948a());
        }
    }

    public void mo3687j() {
        this.f5303d.onResume();
    }

    public void onDestroy() {
        this.f5301b.removeBackButtonInterceptor(this.f5306g);
        C2142b.m6858a(this.f5303d);
        this.f5303d.destroy();
    }

    public void setListener(C1832a c1832a) {
    }
}
