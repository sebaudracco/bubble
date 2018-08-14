package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.C1886d;
import com.facebook.ads.internal.adapters.C1936v;
import com.facebook.ads.internal.p052j.C1998a;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p052j.C1999b;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2130s;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.p070r.C2154a;
import com.facebook.ads.internal.p070r.C2154a.C2025a;
import com.facebook.ads.internal.view.component.p081a.C2197c;
import com.facebook.ads.internal.view.p034b.C2186d;
import java.util.HashMap;
import java.util.Map;

public class C2366k extends C2363m {
    private final C1936v f5853f;
    private final C2154a f5854g;
    private final C2130s f5855h = new C2130s();
    private final C2025a f5856i;
    private long f5857j;

    class C23651 extends C2025a {
        final /* synthetic */ C2366k f5852a;

        C23651(C2366k c2366k) {
            this.f5852a = c2366k;
        }

        public void mo3725a() {
            if (!this.f5852a.f5855h.m6823b()) {
                this.f5852a.f5855h.m6821a();
                this.f5852a.b.mo3709a(this.f5852a.f5853f.m6108a(), new HashMap());
                if (this.f5852a.getAudienceNetworkListener() != null) {
                    this.f5852a.getAudienceNetworkListener().mo3561a("com.facebook.ads.interstitial.impression.logged");
                }
            }
        }
    }

    public C2366k(Context context, C1936v c1936v, C2012c c2012c) {
        super(context, c2012c);
        this.f5853f = c1936v;
        this.f5856i = new C23651(this);
        this.f5854g = new C2154a(this, 100, this.f5856i);
        this.f5854g.m6918a(c1936v.m6117j());
        this.f5854g.m6921b(c1936v.m6118k());
    }

    private void setUpContent(int i) {
        C1886d c1886d = (C1886d) this.f5853f.m6111d().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        new C2186d(imageView).m7001a(c1886d.m5764h(), c1886d.m5763g()).m7004a(c1886d.m5762f());
        View a = C2197c.m7028a(getContext(), this.b, getAudienceNetworkListener(), imageView, this.d, this.e, a, i, c1886d.m5763g(), c1886d.m5764h());
        a.mo3771a(c1886d.m5758b(), c1886d.m5759c(), c1886d.m5760d(), c1886d.m5761e(), this.f5853f.m6108a(), ((double) c1886d.m5764h()) / ((double) c1886d.m5763g()));
        m7466a(a, a.mo3770a(), i);
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.m7467a(audienceNetworkActivity, this.f5853f);
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.f5857j = System.currentTimeMillis();
    }

    public void mo3684a(Bundle bundle) {
    }

    public void mo3686i() {
    }

    public void mo3687j() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (this.f5853f != null) {
            C1999b.m6321a(C1998a.m6316a(this.f5857j, C1996a.XOUT, this.f5853f.m6113f()));
            if (!TextUtils.isEmpty(this.f5853f.m6108a())) {
                Map hashMap = new HashMap();
                this.f5854g.m6919a(hashMap);
                hashMap.put("touch", C2119j.m6798a(this.f5855h.m6826e()));
                this.b.mo3717h(this.f5853f.m6108a(), hashMap);
            }
        }
        super.onDestroy();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f5855h.m6822a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.f5854g == null) {
            return;
        }
        if (i == 0) {
            this.f5854g.m6917a();
        } else if (i == 8) {
            this.f5854g.m6920b();
        }
    }
}
