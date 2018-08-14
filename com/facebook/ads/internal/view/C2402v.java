package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.p053e.C2249b;
import com.facebook.ads.internal.view.p053e.C2323c;
import com.facebook.ads.internal.view.p053e.p054b.C1841k;
import com.facebook.ads.internal.view.p053e.p054b.C1842i;
import com.facebook.ads.internal.view.p053e.p054b.C1844c;
import com.facebook.ads.internal.view.p053e.p054b.C1846e;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2230d;
import com.facebook.ads.internal.view.p053e.p054b.C2231f;
import com.facebook.ads.internal.view.p053e.p054b.C2232g;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2238p;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p085c.C2270b;
import com.facebook.ads.internal.view.p080c.C2190b;

public class C2402v implements C1923a {
    private final C1841k f5970a = new C23961(this);
    private final C1842i f5971b = new C23972(this);
    private final C1844c f5972c = new C23983(this);
    private final C1846e f5973d = new C23994(this);
    private final AudienceNetworkActivity f5974e;
    private final C2012c f5975f;
    private final C2249b f5976g;
    private final C1832a f5977h;
    private C2323c f5978i;
    private int f5979j;

    class C23961 extends C1841k {
        final /* synthetic */ C2402v f5963a;

        C23961(C2402v c2402v) {
            this.f5963a = c2402v;
        }

        public void m7569a(C2234j c2234j) {
            this.f5963a.f5977h.mo3562a("videoInterstitalEvent", c2234j);
        }
    }

    class C23972 extends C1842i {
        final /* synthetic */ C2402v f5964a;

        C23972(C2402v c2402v) {
            this.f5964a = c2402v;
        }

        public void m7571a(C2233h c2233h) {
            this.f5964a.f5977h.mo3562a("videoInterstitalEvent", c2233h);
        }
    }

    class C23983 extends C1844c {
        final /* synthetic */ C2402v f5965a;

        C23983(C2402v c2402v) {
            this.f5965a = c2402v;
        }

        public void m7573a(C2229b c2229b) {
            this.f5965a.f5977h.mo3562a("videoInterstitalEvent", c2229b);
        }
    }

    class C23994 extends C1846e {
        final /* synthetic */ C2402v f5966a;

        C23994(C2402v c2402v) {
            this.f5966a = c2402v;
        }

        public void m7575a(C2230d c2230d) {
            this.f5966a.f5974e.finish();
        }
    }

    class C24016 implements OnClickListener {
        final /* synthetic */ C2402v f5969a;

        C24016(C2402v c2402v) {
            this.f5969a = c2402v;
        }

        public void onClick(View view) {
            this.f5969a.f5977h.mo3561a("performCtaClick");
        }
    }

    public C2402v(final AudienceNetworkActivity audienceNetworkActivity, C2012c c2012c, C1832a c1832a) {
        this.f5974e = audienceNetworkActivity;
        this.f5975f = c2012c;
        this.f5976g = new C2249b(audienceNetworkActivity);
        this.f5976g.m7105a(new C2270b(audienceNetworkActivity));
        this.f5976g.getEventBus().m6328a(this.f5970a, this.f5971b, this.f5972c, this.f5973d);
        this.f5977h = c1832a;
        this.f5976g.setIsFullScreen(true);
        this.f5976g.setVolume(1.0f);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.f5976g.setLayoutParams(layoutParams);
        c1832a.mo3560a(this.f5976g);
        View c2219d = new C2219d(audienceNetworkActivity);
        c2219d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ C2402v f5968b;

            public void onClick(View view) {
                audienceNetworkActivity.finish();
            }
        });
        c1832a.mo3560a(c2219d);
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (!(stringExtra == null || stringExtra.isEmpty())) {
            View c2190b = new C2190b(audienceNetworkActivity, stringExtra);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int i = (int) (C2133v.f5083b * 16.0f);
            layoutParams.setMargins(i, i, i, i);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            c2190b.setLayoutParams(layoutParams);
            c2190b.setOnClickListener(new C24016(this));
            this.f5977h.mo3560a(c2190b);
        }
        this.f5979j = intent.getIntExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, 0);
        this.f5978i = new C2323c((Context) audienceNetworkActivity, this.f5975f, this.f5976g, intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN), intent.getBundleExtra(AudienceNetworkActivity.VIDEO_LOGGER));
        this.f5976g.setVideoMPD(intent.getStringExtra(AudienceNetworkActivity.VIDEO_MPD));
        this.f5976g.setVideoURI(intent.getStringExtra(AudienceNetworkActivity.VIDEO_URL));
        if (this.f5979j > 0) {
            this.f5976g.m7102a(this.f5979j);
        }
        if (intent.getBooleanExtra("autoplay", false)) {
            this.f5976g.m7104a(C2222a.USER_STARTED);
        }
    }

    public void mo3684a(Bundle bundle) {
    }

    public void m7580a(View view) {
        this.f5976g.setControlsAnchorView(view);
    }

    public void mo3686i() {
        this.f5977h.mo3562a("videoInterstitalEvent", new C2231f());
        this.f5976g.m7107a(false);
    }

    public void mo3687j() {
        this.f5977h.mo3562a("videoInterstitalEvent", new C2232g());
        this.f5976g.m7104a(C2222a.USER_STARTED);
    }

    public void onDestroy() {
        this.f5977h.mo3562a("videoInterstitalEvent", new C2238p(this.f5979j, this.f5976g.getCurrentPosition()));
        this.f5978i.m7327b(this.f5976g.getCurrentPosition());
        this.f5976g.m7111f();
        this.f5976g.m7116k();
    }

    public void setListener(C1832a c1832a) {
    }
}
