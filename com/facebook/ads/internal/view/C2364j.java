package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
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
import com.facebook.ads.internal.view.C2360i.C2358a;
import com.facebook.ads.internal.view.component.C2203d;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C2364j extends C2363m {
    private final C2130s f5841f = new C2130s();
    @Nullable
    private LinearLayout f5842g;
    private String f5843h;
    private long f5844i;
    private String f5845j;
    private List<C2358a> f5846k;
    @Nullable
    private C2203d f5847l;
    @Nullable
    private RecyclerView f5848m;
    private C2154a f5849n;
    private int f5850o;
    private int f5851p;

    class C23611 extends C2025a {
        final /* synthetic */ C2364j f5831a;

        C23611(C2364j c2364j) {
            this.f5831a = c2364j;
        }

        public void mo3725a() {
            Map hashMap = new HashMap();
            if (!this.f5831a.f5841f.m6823b()) {
                this.f5831a.f5841f.m6821a();
                if (this.f5831a.getAudienceNetworkListener() != null) {
                    this.f5831a.getAudienceNetworkListener().mo3561a("com.facebook.ads.interstitial.impression.logged");
                }
                if (!TextUtils.isEmpty(this.f5831a.f5843h)) {
                    this.f5831a.f5849n.m6919a(hashMap);
                    hashMap.put("touch", C2119j.m6798a(this.f5831a.f5841f.m6826e()));
                    this.f5831a.b.mo3709a(this.f5831a.f5843h, hashMap);
                }
            }
        }
    }

    public C2364j(Context context, C2012c c2012c) {
        super(context, c2012c);
    }

    private void m7469a(C1936v c1936v) {
        this.f5843h = c1936v.m6108a();
        this.f5845j = c1936v.m6113f();
        this.f5850o = c1936v.m6117j();
        this.f5851p = c1936v.m6118k();
        List d = c1936v.m6111d();
        this.f5846k = new ArrayList(d.size());
        for (int i = 0; i < d.size(); i++) {
            C1886d c1886d = (C1886d) d.get(i);
            this.f5846k.add(new C2358a(i, d.size(), c1886d.m5762f(), c1886d.m5757a(), c1886d.m5759c(), c1886d.m5760d(), c1886d.m5761e()));
        }
    }

    public void mo3824a() {
        if (this.f5842g != null) {
            this.f5842g.removeAllViews();
            this.f5842g = null;
        }
        if (this.f5848m != null) {
            this.f5848m.removeAllViews();
            this.f5848m = null;
        }
        if (this.f5847l != null) {
            this.f5847l.removeAllViews();
            this.f5847l = null;
        }
    }

    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        C1936v c1936v = (C1936v) intent.getSerializableExtra("ad_data_bundle");
        super.m7467a(audienceNetworkActivity, c1936v);
        m7469a(c1936v);
        setUpLayout(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.f5844i = System.currentTimeMillis();
    }

    public void mo3684a(Bundle bundle) {
    }

    public void mo3686i() {
    }

    public void mo3687j() {
    }

    public void onConfigurationChanged(Configuration configuration) {
        mo3824a();
        setUpLayout(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        C1999b.m6321a(C1998a.m6316a(this.f5844i, C1996a.XOUT, this.f5845j));
        if (!TextUtils.isEmpty(this.f5843h)) {
            Map hashMap = new HashMap();
            this.f5849n.m6919a(hashMap);
            hashMap.put("touch", C2119j.m6798a(this.f5841f.m6826e()));
            this.b.mo3717h(this.f5843h, hashMap);
        }
        mo3824a();
        this.f5849n.m6920b();
        this.f5849n = null;
        this.f5846k = null;
    }

    public void setUpLayout(int i) {
        int min;
        int i2;
        int i3;
        boolean z;
        this.f5842g = new LinearLayout(getContext());
        if (i == 1) {
            this.f5842g.setGravity(17);
        } else {
            this.f5842g.setGravity(48);
        }
        this.f5842g.setLayoutParams(new LayoutParams(-1, -1));
        this.f5842g.setOrientation(1);
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        int i4 = displayMetrics.widthPixels;
        int i5 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        if (i == 1) {
            min = Math.min(i4 - ((int) (32.0f * f)), i5 / 2);
            i2 = (i4 - min) / 8;
            i3 = i2 * 4;
            z = false;
        } else {
            min = i5 - ((int) (BitmapDescriptorFactory.HUE_GREEN * f));
            i2 = (int) (8.0f * f);
            i3 = i2 * 2;
            z = true;
        }
        this.f5848m = new RecyclerView(getContext());
        this.f5848m.setLayoutParams(new LayoutParams(-1, -2));
        this.f5848m.setAdapter(new C2360i(this.f5846k, this.b, this.f5841f, getAudienceNetworkListener(), i == 1 ? this.d : this.e, this.f5843h, min, i2, i3, z));
        final LayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        this.f5848m.setLayoutManager(linearLayoutManager);
        this.f5849n = new C2154a(this.f5848m, 1, new C23611(this));
        this.f5849n.m6918a(this.f5850o);
        this.f5849n.m6921b(this.f5851p);
        if (i == 1) {
            new PagerSnapHelper().attachToRecyclerView(this.f5848m);
            this.f5848m.addOnScrollListener(new OnScrollListener(this) {
                final /* synthetic */ C2364j f5833b;

                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    int findFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    if (findFirstCompletelyVisibleItemPosition != -1) {
                        if (this.f5833b.f5847l != null) {
                            this.f5833b.f5847l.m7046a(findFirstCompletelyVisibleItemPosition);
                        }
                        if (findFirstCompletelyVisibleItemPosition != findFirstVisibleItemPosition) {
                            linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition).setAlpha(0.5f);
                        }
                        linearLayoutManager.findViewByPosition(findFirstCompletelyVisibleItemPosition).setAlpha(1.0f);
                        if (findFirstCompletelyVisibleItemPosition != findLastVisibleItemPosition) {
                            linearLayoutManager.findViewByPosition(findLastVisibleItemPosition).setAlpha(0.5f);
                        }
                    } else if (i > 0) {
                        if (this.f5833b.f5847l != null) {
                            this.f5833b.f5847l.m7046a(findLastVisibleItemPosition);
                        }
                        linearLayoutManager.findViewByPosition(findLastVisibleItemPosition).setAlpha(1.0f);
                    } else {
                        if (this.f5833b.f5847l != null) {
                            this.f5833b.f5847l.m7046a(findFirstVisibleItemPosition);
                        }
                        linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition).setAlpha(1.0f);
                    }
                }
            });
            this.f5847l = new C2203d(getContext(), i == 1 ? this.d : this.e, this.f5846k.size());
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (8.0f * f));
            layoutParams.setMargins(0, (int) (12.0f * f), 0, 0);
            this.f5847l.setLayoutParams(layoutParams);
        }
        this.f5842g.addView(this.f5848m);
        if (this.f5847l != null) {
            this.f5842g.addView(this.f5847l);
        }
        m7466a(this.f5842g, false, i);
        this.f5849n.m6917a();
    }
}
