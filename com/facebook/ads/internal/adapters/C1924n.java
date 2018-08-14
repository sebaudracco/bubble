package com.facebook.ads.internal.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity$BackButtonInterceptor;
import com.facebook.ads.internal.adapters.C1920m.C1919a;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p059a.C1874b;
import com.facebook.ads.internal.view.C1923a;
import com.facebook.ads.internal.view.C1923a.C1832a;
import com.facebook.ads.internal.view.p034b.C2186d;
import com.facebook.ads.internal.view.p053e.p054b.C2241t;
import com.facebook.ads.internal.view.p053e.p083a.C2222a;
import com.facebook.ads.internal.view.p053e.p083a.C2223b;
import com.facebook.ads.internal.view.p053e.p084d.C2336d;
import com.facebook.ads.internal.view.p053e.p085c.C2265a.C2264a;
import com.facebook.ads.internal.view.p053e.p085c.C2281d;
import com.facebook.ads.internal.view.p053e.p085c.C2281d.C2280a;
import com.facebook.ads.internal.view.p053e.p085c.C2289g;
import com.facebook.ads.internal.view.p053e.p085c.C2303j;
import com.facebook.ads.internal.view.p053e.p085c.C2305k;
import com.facebook.ads.internal.view.p053e.p085c.C2311l;
import com.facebook.ads.internal.view.p053e.p085c.C2316n;
import com.facebook.ads.internal.view.p080c.C2189a;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.silvermob.sdk.Const.BannerType;
import java.util.HashMap;
import org.json.JSONObject;

public class C1924n extends C1914l implements OnTouchListener, C1923a {
    static final /* synthetic */ boolean f4346i = (!C1924n.class.desiredAssertionStatus());
    private static final String f4347j = C1924n.class.getSimpleName();
    private int f4348A = -12286980;
    private boolean f4349B = false;
    @Nullable
    private C2222a f4350C;
    final int f4351f = 64;
    final int f4352g = 64;
    final int f4353h = 16;
    @Nullable
    private C1832a f4354k;
    @Nullable
    private Activity f4355l;
    private AudienceNetworkActivity$BackButtonInterceptor f4356m = new C19211(this);
    private final OnTouchListener f4357n = new C19222(this);
    private C1919a f4358o = C1919a.UNSPECIFIED;
    private C2189a f4359p;
    private TextView f4360q;
    private TextView f4361r;
    private ImageView f4362s;
    @Nullable
    private C2264a f4363t;
    private C2316n f4364u;
    private ViewGroup f4365v;
    private C2281d f4366w;
    private C2303j f4367x;
    private int f4368y = -1;
    private int f4369z = -10525069;

    class C19211 implements AudienceNetworkActivity$BackButtonInterceptor {
        final /* synthetic */ C1924n f4344a;

        C19211(C1924n c1924n) {
            this.f4344a = c1924n;
        }

        public boolean interceptBackButton() {
            if (this.f4344a.f4367x == null) {
                return false;
            }
            if (!this.f4344a.f4367x.m7269a()) {
                return true;
            }
            if (!(this.f4344a.f4367x.getSkipSeconds() == 0 || this.f4344a.b == null)) {
                this.f4344a.b.m7110e();
            }
            if (this.f4344a.b == null) {
                return false;
            }
            this.f4344a.b.m7111f();
            return false;
        }
    }

    class C19222 implements OnTouchListener {
        final /* synthetic */ C1924n f4345a;

        C19222(C1924n c1924n) {
            this.f4345a = c1924n;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1) {
                if (this.f4345a.f4367x == null) {
                    this.f4345a.f4355l.finish();
                } else if (this.f4345a.f4367x.m7269a()) {
                    if (!(this.f4345a.f4367x.getSkipSeconds() == 0 || this.f4345a.b == null)) {
                        this.f4345a.b.m7110e();
                    }
                    if (this.f4345a.b != null) {
                        this.f4345a.b.m7111f();
                    }
                    this.f4345a.f4355l.finish();
                }
            }
            return true;
        }
    }

    private void m5919a(int i) {
        View linearLayout;
        float f = C2133v.f5083b;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (56.0f * f), (int) (56.0f * f));
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        int i2 = (int) (16.0f * f);
        this.f4367x.setPadding(i2, i2, i2, i2);
        this.f4367x.setLayoutParams(layoutParams);
        C2280a c2280a = mo3685h() ? C2280a.FADE_OUT_ON_PLAY : C2280a.VISIBLE;
        int id = this.b.getId();
        if (i == 1 && (m5924m() || m5925n())) {
            Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
            gradientDrawable.setCornerRadius(0.0f);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams2.addRule(10);
            this.b.setLayoutParams(layoutParams2);
            m5920a(this.b);
            m5920a(this.f4367x);
            if (this.f4363t != null) {
                m5920a(this.f4363t);
            }
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, (int) (((float) (((((this.f4359p != null ? 64 : 0) + 60) + 16) + 16) + 16)) * f));
            layoutParams3.addRule(12);
            View relativeLayout = new RelativeLayout(this.d);
            if (VERSION.SDK_INT >= 16) {
                relativeLayout.setBackground(gradientDrawable);
            } else {
                relativeLayout.setBackgroundDrawable(gradientDrawable);
            }
            relativeLayout.setLayoutParams(layoutParams3);
            relativeLayout.setPadding(i2, 0, i2, (int) (((float) (((this.f4359p != null ? 64 : 0) + 16) + 16)) * f));
            this.f4365v = relativeLayout;
            if (!this.f4349B) {
                this.f4366w.m7202a(relativeLayout, c2280a);
            }
            m5920a(relativeLayout);
            if (this.f4364u != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                layoutParams.addRule(12);
                layoutParams.topMargin = (int) (-6.0f * f);
                this.f4364u.setLayoutParams(layoutParams);
                m5920a(this.f4364u);
            }
            if (this.f4359p != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                layoutParams.bottomMargin = (int) (16.0f * f);
                layoutParams.leftMargin = (int) (16.0f * f);
                layoutParams.rightMargin = (int) (16.0f * f);
                layoutParams.addRule(1);
                layoutParams.addRule(12);
                this.f4359p.setLayoutParams(layoutParams);
                m5920a(this.f4359p);
            }
            if (this.f4362s != null) {
                layoutParams = new RelativeLayout.LayoutParams((int) (BitmapDescriptorFactory.HUE_YELLOW * f), (int) (BitmapDescriptorFactory.HUE_YELLOW * f));
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                this.f4362s.setLayoutParams(layoutParams);
                m5921a(relativeLayout, this.f4362s);
            }
            if (this.f4360q != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.bottomMargin = (int) (36.0f * f);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                this.f4360q.setEllipsize(TruncateAt.END);
                this.f4360q.setGravity(GravityCompat.START);
                this.f4360q.setLayoutParams(layoutParams);
                this.f4360q.setMaxLines(1);
                this.f4360q.setPadding((int) (72.0f * f), 0, 0, 0);
                this.f4360q.setTextColor(-1);
                this.f4360q.setTextSize(RadialCountdown.TEXT_SIZE_SP);
                m5921a(relativeLayout, this.f4360q);
            }
            if (this.f4361r != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                layoutParams.bottomMargin = (int) (4.0f * f);
                this.f4361r.setEllipsize(TruncateAt.END);
                this.f4361r.setGravity(GravityCompat.START);
                this.f4361r.setLayoutParams(layoutParams);
                this.f4361r.setMaxLines(1);
                this.f4361r.setPadding((int) (72.0f * f), 0, 0, 0);
                this.f4361r.setTextColor(-1);
                m5921a(relativeLayout, this.f4361r);
            }
            ((ViewGroup) this.b.getParent()).setBackgroundColor(-16777216);
        } else if (i == 1) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(10);
            this.b.setLayoutParams(layoutParams);
            m5920a(this.b);
            m5920a(this.f4367x);
            if (this.f4363t != null) {
                m5920a(this.f4363t);
            }
            linearLayout = new LinearLayout(this.d);
            this.f4365v = linearLayout;
            linearLayout.setGravity(112);
            linearLayout.setOrientation(1);
            r4 = new RelativeLayout.LayoutParams(-1, -1);
            r4.leftMargin = (int) (33.0f * f);
            r4.rightMargin = (int) (33.0f * f);
            r4.topMargin = (int) (8.0f * f);
            if (this.f4359p == null) {
                r4.bottomMargin = (int) (16.0f * f);
            } else {
                r4.bottomMargin = (int) (80.0f * f);
            }
            r4.addRule(3, id);
            linearLayout.setLayoutParams(r4);
            m5920a(linearLayout);
            if (this.f4359p != null) {
                r4 = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                r4.bottomMargin = (int) (16.0f * f);
                r4.leftMargin = (int) (33.0f * f);
                r4.rightMargin = (int) (33.0f * f);
                r4.addRule(1);
                r4.addRule(12);
                this.f4359p.setLayoutParams(r4);
                m5920a(this.f4359p);
            }
            if (this.f4360q != null) {
                r4 = new LinearLayout.LayoutParams(-2, -2);
                r4.weight = 2.0f;
                r4.gravity = 17;
                this.f4360q.setEllipsize(TruncateAt.END);
                this.f4360q.setGravity(17);
                this.f4360q.setLayoutParams(r4);
                this.f4360q.setMaxLines(2);
                this.f4360q.setPadding(0, 0, 0, 0);
                this.f4360q.setTextColor(this.f4369z);
                this.f4360q.setTextSize(24.0f);
                m5921a(linearLayout, this.f4360q);
            }
            if (this.f4362s != null) {
                r4 = new LinearLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                r4.weight = 0.0f;
                r4.gravity = 17;
                this.f4362s.setLayoutParams(r4);
                m5921a(linearLayout, this.f4362s);
            }
            if (this.f4361r != null) {
                r4 = new LinearLayout.LayoutParams(-1, -2);
                r4.weight = 2.0f;
                r4.gravity = 16;
                this.f4361r.setEllipsize(TruncateAt.END);
                this.f4361r.setGravity(16);
                this.f4361r.setLayoutParams(r4);
                this.f4361r.setMaxLines(2);
                this.f4361r.setPadding(0, 0, 0, 0);
                this.f4361r.setTextColor(this.f4369z);
                m5921a(linearLayout, this.f4361r);
            }
            if (this.f4364u != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                layoutParams.addRule(3, id);
                this.f4364u.setLayoutParams(layoutParams);
                m5920a(this.f4364u);
            }
            ((ViewGroup) this.b.getParent()).setBackgroundColor(this.f4368y);
        } else if (!m5926o() || m5925n()) {
            Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -15658735});
            gradientDrawable2.setCornerRadius(0.0f);
            this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            m5920a(this.b);
            m5920a(this.f4367x);
            if (this.f4363t != null) {
                m5920a(this.f4363t);
            }
            LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, (int) (124.0f * f));
            layoutParams4.addRule(12);
            View relativeLayout2 = new RelativeLayout(this.d);
            if (VERSION.SDK_INT >= 16) {
                relativeLayout2.setBackground(gradientDrawable2);
            } else {
                relativeLayout2.setBackgroundDrawable(gradientDrawable2);
            }
            relativeLayout2.setLayoutParams(layoutParams4);
            relativeLayout2.setPadding(i2, 0, i2, i2);
            this.f4365v = relativeLayout2;
            if (!this.f4349B) {
                this.f4366w.m7202a(relativeLayout2, c2280a);
            }
            m5920a(relativeLayout2);
            if (this.f4359p != null) {
                layoutParams = new RelativeLayout.LayoutParams((int) (110.0f * f), (int) (56.0f * f));
                layoutParams.rightMargin = (int) (16.0f * f);
                layoutParams.bottomMargin = (int) (16.0f * f);
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                this.f4359p.setLayoutParams(layoutParams);
                m5920a(this.f4359p);
            }
            if (this.f4362s != null) {
                layoutParams = new RelativeLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                layoutParams.bottomMargin = (int) (8.0f * f);
                this.f4362s.setLayoutParams(layoutParams);
                m5921a(relativeLayout2, this.f4362s);
            }
            if (this.f4360q != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.bottomMargin = (int) (48.0f * f);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                this.f4360q.setEllipsize(TruncateAt.END);
                this.f4360q.setGravity(GravityCompat.START);
                this.f4360q.setLayoutParams(layoutParams);
                this.f4360q.setMaxLines(1);
                this.f4360q.setPadding((int) (80.0f * f), 0, this.f4359p != null ? (int) (126.0f * f) : 0, 0);
                this.f4360q.setTextColor(-1);
                this.f4360q.setTextSize(24.0f);
                m5921a(relativeLayout2, this.f4360q);
            }
            if (this.f4361r != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                this.f4361r.setEllipsize(TruncateAt.END);
                this.f4361r.setGravity(GravityCompat.START);
                this.f4361r.setLayoutParams(layoutParams);
                this.f4361r.setMaxLines(2);
                this.f4361r.setTextColor(-1);
                this.f4361r.setPadding((int) (80.0f * f), 0, this.f4359p != null ? (int) (126.0f * f) : 0, 0);
                m5921a(relativeLayout2, this.f4361r);
            }
            if (this.f4364u != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                layoutParams.addRule(12);
                this.f4364u.setLayoutParams(layoutParams);
                m5920a(this.f4364u);
            }
            ((ViewGroup) this.b.getParent()).setBackgroundColor(-16777216);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-2, -1);
            layoutParams.addRule(9);
            this.b.setLayoutParams(layoutParams);
            m5920a(this.b);
            m5920a(this.f4367x);
            if (this.f4363t != null) {
                m5920a(this.f4363t);
            }
            linearLayout = new LinearLayout(this.d);
            this.f4365v = linearLayout;
            linearLayout.setGravity(112);
            linearLayout.setOrientation(1);
            r4 = new RelativeLayout.LayoutParams(-1, -1);
            r4.leftMargin = (int) (16.0f * f);
            r4.rightMargin = (int) (16.0f * f);
            r4.topMargin = (int) (8.0f * f);
            r4.bottomMargin = (int) (80.0f * f);
            r4.addRule(1, id);
            linearLayout.setLayoutParams(r4);
            m5920a(linearLayout);
            if (this.f4364u != null) {
                r4 = new RelativeLayout.LayoutParams(-1, (int) (6.0f * f));
                r4.addRule(5, id);
                r4.addRule(7, id);
                r4.addRule(3, id);
                r4.topMargin = (int) (-6.0f * f);
                this.f4364u.setLayoutParams(r4);
                m5920a(this.f4364u);
            }
            if (this.f4360q != null) {
                r4 = new LinearLayout.LayoutParams(-2, -2);
                r4.weight = 2.0f;
                r4.gravity = 17;
                this.f4360q.setEllipsize(TruncateAt.END);
                this.f4360q.setGravity(17);
                this.f4360q.setLayoutParams(r4);
                this.f4360q.setMaxLines(10);
                this.f4360q.setPadding(0, 0, 0, 0);
                this.f4360q.setTextColor(this.f4369z);
                this.f4360q.setTextSize(24.0f);
                m5921a(linearLayout, this.f4360q);
            }
            if (this.f4362s != null) {
                r4 = new LinearLayout.LayoutParams((int) (64.0f * f), (int) (64.0f * f));
                r4.weight = 0.0f;
                r4.gravity = 17;
                this.f4362s.setLayoutParams(r4);
                m5921a(linearLayout, this.f4362s);
            }
            if (this.f4361r != null) {
                r4 = new LinearLayout.LayoutParams(-1, -2);
                r4.weight = 2.0f;
                r4.gravity = 16;
                this.f4361r.setEllipsize(TruncateAt.END);
                this.f4361r.setGravity(17);
                this.f4361r.setLayoutParams(r4);
                this.f4361r.setMaxLines(10);
                this.f4361r.setPadding(0, 0, 0, 0);
                this.f4361r.setTextColor(this.f4369z);
                m5921a(linearLayout, this.f4361r);
            }
            if (this.f4359p != null) {
                layoutParams = new RelativeLayout.LayoutParams(-1, (int) (64.0f * f));
                layoutParams.bottomMargin = (int) (16.0f * f);
                layoutParams.leftMargin = (int) (16.0f * f);
                layoutParams.rightMargin = (int) (16.0f * f);
                layoutParams.addRule(1);
                layoutParams.addRule(12);
                layoutParams.addRule(1, id);
                this.f4359p.setLayoutParams(layoutParams);
                m5920a(this.f4359p);
            }
            ((ViewGroup) this.b.getParent()).setBackgroundColor(this.f4368y);
        }
        linearLayout = this.b.getRootView();
        if (linearLayout != null) {
            linearLayout.setOnTouchListener(this);
        }
    }

    private void m5920a(View view) {
        if (this.f4354k != null) {
            this.f4354k.mo3560a(view);
        }
    }

    private void m5921a(@Nullable ViewGroup viewGroup, @Nullable View view) {
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    private void m5923b(View view) {
        if (view != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
            }
        }
    }

    private boolean m5924m() {
        return ((double) (this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f)) <= 0.9d;
    }

    private boolean m5925n() {
        if (this.b.getVideoHeight() <= 0) {
            return false;
        }
        Rect rect = new Rect();
        this.f4355l.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        if (rect.width() > rect.height()) {
            return ((float) (rect.width() - ((rect.height() * this.b.getVideoWidth()) / this.b.getVideoHeight()))) - (192.0f * C2133v.f5083b) < 0.0f;
        } else {
            return ((((float) (rect.height() - ((rect.width() * this.b.getVideoHeight()) / this.b.getVideoWidth()))) - (C2133v.f5083b * 64.0f)) - (C2133v.f5083b * 64.0f)) - (40.0f * C2133v.f5083b) < 0.0f;
        }
    }

    private boolean m5926o() {
        float videoWidth = this.b.getVideoHeight() > 0 ? ((float) this.b.getVideoWidth()) / ((float) this.b.getVideoHeight()) : -1.0f;
        return ((double) videoWidth) > 0.9d && ((double) videoWidth) < 1.1d;
    }

    private void m5927p() {
        m5923b(this.b);
        m5923b(this.f4359p);
        m5923b(this.f4360q);
        m5923b(this.f4361r);
        m5923b(this.f4362s);
        m5923b(this.f4364u);
        m5923b(this.f4365v);
        m5923b(this.f4367x);
        if (this.f4363t != null) {
            m5923b(this.f4363t);
        }
    }

    protected void mo3682a() {
        if (this.c == null) {
            Log.e(f4347j, "Unable to add UI without a valid ad response.");
            return;
        }
        C2223b c2289g;
        String string = this.c.getString("ct");
        String optString = this.c.getJSONObject("context").optString("orientation");
        if (!optString.isEmpty()) {
            this.f4358o = C1919a.m5903a(Integer.parseInt(optString));
        }
        if (this.c.has("layout") && !this.c.isNull("layout")) {
            JSONObject jSONObject = this.c.getJSONObject("layout");
            this.f4368y = (int) jSONObject.optLong("bgColor", (long) this.f4368y);
            this.f4369z = (int) jSONObject.optLong("textColor", (long) this.f4369z);
            this.f4348A = (int) jSONObject.optLong("accentColor", (long) this.f4348A);
            this.f4349B = jSONObject.optBoolean("persistentAdDetails", this.f4349B);
        }
        JSONObject jSONObject2 = this.c.getJSONObject("text");
        this.b.setId(VERSION.SDK_INT >= 17 ? View.generateViewId() : C2133v.m6830a());
        int c = m5883c();
        Context context = this.d;
        if (c < 0) {
            c = 0;
        }
        this.f4367x = new C2303j(context, c, this.f4348A);
        this.f4367x.setOnTouchListener(this.f4357n);
        this.b.m7105a(this.f4367x);
        if (this.c.has("cta") && !this.c.isNull("cta")) {
            JSONObject jSONObject3 = this.c.getJSONObject("cta");
            this.f4359p = new C2189a(this.d, jSONObject3.getString("url"), jSONObject3.getString("text"), this.f4348A, this.b, this.a, string);
            C1874b.m5632a(this.d, this.a, string, Uri.parse(jSONObject3.getString("url")), new HashMap());
        }
        if (this.c.has("icon") && !this.c.isNull("icon")) {
            jSONObject = this.c.getJSONObject("icon");
            this.f4362s = new ImageView(this.d);
            new C2186d(this.f4362s).m7001a((int) (C2133v.f5083b * 64.0f), (int) (C2133v.f5083b * 64.0f)).m7004a(jSONObject.getString("url"));
        }
        if (this.c.has(BannerType.IMAGE) && !this.c.isNull(BannerType.IMAGE)) {
            jSONObject = this.c.getJSONObject(BannerType.IMAGE);
            c2289g = new C2289g(this.d);
            this.b.m7105a(c2289g);
            c2289g.setImage(jSONObject.getString("url"));
        }
        CharSequence optString2 = jSONObject2.optString("title");
        if (!optString2.isEmpty()) {
            this.f4360q = new TextView(this.d);
            this.f4360q.setText(optString2);
            this.f4360q.setTypeface(Typeface.defaultFromStyle(1));
        }
        optString2 = jSONObject2.optString(FacebookAdapter.KEY_SUBTITLE_ASSET);
        if (!optString2.isEmpty()) {
            this.f4361r = new TextView(this.d);
            this.f4361r.setText(optString2);
            this.f4361r.setTextSize(16.0f);
        }
        this.f4364u = new C2316n(this.d);
        this.b.m7105a(this.f4364u);
        Object d = m5884d();
        if (!TextUtils.isEmpty(d)) {
            this.f4363t = new C2264a(this.d, "AdChoices", d, new float[]{0.0f, 0.0f, 8.0f, 0.0f}, string);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            this.f4363t.setLayoutParams(layoutParams);
        }
        this.b.m7105a(new C2305k(this.d));
        c2289g = new C2311l(this.d);
        this.b.m7105a(c2289g);
        C2280a c2280a = mo3685h() ? C2280a.FADE_OUT_ON_PLAY : C2280a.VISIBLE;
        this.b.m7105a(new C2281d(c2289g, c2280a));
        this.f4366w = new C2281d(new RelativeLayout(this.d), c2280a);
        this.b.m7105a(this.f4366w);
    }

    @TargetApi(17)
    public void mo3683a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        this.f4355l = audienceNetworkActivity;
        if (f4346i || this.f4354k != null) {
            audienceNetworkActivity.addBackButtonInterceptor(this.f4356m);
            m5927p();
            m5919a(this.f4355l.getResources().getConfiguration().orientation);
            if (mo3685h()) {
                mo3677e();
                return;
            } else {
                m5886f();
                return;
            }
        }
        throw new AssertionError();
    }

    public void m5930a(Configuration configuration) {
        m5927p();
        m5919a(configuration.orientation);
    }

    public void mo3684a(Bundle bundle) {
    }

    protected boolean mo3685h() {
        if (f4346i || this.c != null) {
            try {
                return this.c.getJSONObject("video").getBoolean("autoplay");
            } catch (Throwable e) {
                Log.w(String.valueOf(C1924n.class), "Invalid JSON", e);
                return true;
            }
        }
        throw new AssertionError();
    }

    public void mo3686i() {
        if (this.b != null && this.b.getState() == C2336d.STARTED) {
            this.f4350C = this.b.getVideoStartReason();
            this.b.m7107a(false);
        }
    }

    public void mo3687j() {
        if (this.b != null && this.f4350C != null) {
            this.b.m7104a(this.f4350C);
        }
    }

    public C1919a m5935k() {
        return this.f4358o;
    }

    public void m5936l() {
        if (this.f4355l != null) {
            this.f4355l.finish();
        }
    }

    public void onDestroy() {
        if (!(this.c == null || this.a == null)) {
            Object optString = this.c.optString("ct");
            if (!TextUtils.isEmpty(optString)) {
                this.a.mo3717h(optString, new HashMap());
            }
        }
        if (this.b != null) {
            this.b.m7111f();
        }
        C1920m.m5908a((C1923a) this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.b != null) {
            this.b.getEventBus().m6327a(new C2241t(view, motionEvent));
        }
        return true;
    }

    public void setListener(C1832a c1832a) {
        this.f4354k = c1832a;
    }
}
