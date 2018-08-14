package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.adcolony.sdk.aa.C0595a;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import org.json.JSONObject;

class ax extends Button {
    private C0673c f711A;
    private af f712B;
    private final int f713a = 0;
    private final int f714b = 1;
    private final int f715c = 2;
    private final int f716d = 3;
    private final int f717e = 1;
    private final int f718f = 2;
    private final int f719g = 3;
    private final int f720h = 0;
    private final int f721i = 1;
    private final int f722j = 2;
    private final int f723k = 1;
    private final int f724l = 2;
    private int f725m;
    private int f726n;
    private int f727o;
    private int f728p;
    private int f729q;
    private int f730r;
    private int f731s;
    private int f732t;
    private int f733u;
    private int f734v;
    private String f735w;
    private String f736x;
    private String f737y;
    private String f738z;

    class C06291 implements ah {
        final /* synthetic */ ax f702a;

        C06291(ax axVar) {
            this.f702a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f702a.m841c(afVar)) {
                this.f702a.m849k(afVar);
            }
        }
    }

    class C06302 implements ah {
        final /* synthetic */ ax f703a;

        C06302(ax axVar) {
            this.f703a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f703a.m841c(afVar)) {
                this.f703a.m839a(afVar);
            }
        }
    }

    class C06313 implements ah {
        final /* synthetic */ ax f704a;

        C06313(ax axVar) {
            this.f704a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f704a.m841c(afVar)) {
                this.f704a.m842d(afVar);
            }
        }
    }

    class C06324 implements ah {
        final /* synthetic */ ax f705a;

        C06324(ax axVar) {
            this.f705a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f705a.m841c(afVar)) {
                this.f705a.m844f(afVar);
            }
        }
    }

    class C06335 implements ah {
        final /* synthetic */ ax f706a;

        C06335(ax axVar) {
            this.f706a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f706a.m841c(afVar)) {
                this.f706a.m843e(afVar);
            }
        }
    }

    class C06346 implements ah {
        final /* synthetic */ ax f707a;

        C06346(ax axVar) {
            this.f707a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f707a.m841c(afVar)) {
                this.f707a.m848j(afVar);
            }
        }
    }

    class C06357 implements ah {
        final /* synthetic */ ax f708a;

        C06357(ax axVar) {
            this.f708a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f708a.m841c(afVar)) {
                this.f708a.m845g(afVar);
            }
        }
    }

    class C06368 implements ah {
        final /* synthetic */ ax f709a;

        C06368(ax axVar) {
            this.f709a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f709a.m841c(afVar)) {
                this.f709a.m846h(afVar);
            }
        }
    }

    class C06379 implements ah {
        final /* synthetic */ ax f710a;

        C06379(ax axVar) {
            this.f710a = axVar;
        }

        public void mo1863a(af afVar) {
            if (this.f710a.m841c(afVar)) {
                this.f710a.m840b(afVar);
            }
        }
    }

    ax(Context context, af afVar, int i, C0673c c0673c) {
        super(context);
        this.f725m = i;
        this.f712B = afVar;
        this.f711A = c0673c;
    }

    ax(Context context, int i, af afVar, int i2, C0673c c0673c) {
        super(context, null, i);
        this.f725m = i2;
        this.f712B = afVar;
        this.f711A = c0673c;
    }

    void m839a(af afVar) {
        JSONObject c = afVar.m698c();
        this.f733u = C0802y.m1473c(c, "x");
        this.f734v = C0802y.m1473c(c, "y");
        setGravity(m837a(true, this.f733u) | m837a(false, this.f734v));
    }

    int m837a(boolean z, int i) {
        switch (i) {
            case 0:
                if (z) {
                    return 1;
                }
                return 16;
            case 1:
                if (z) {
                    return 3;
                }
                return 48;
            case 2:
                if (z) {
                    return 5;
                }
                return 80;
            default:
                return 17;
        }
    }

    void m840b(af afVar) {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "text", getText().toString());
        afVar.m694a(a).m695b();
    }

    public boolean onTouchEvent(MotionEvent event) {
        C0740l a = C0594a.m605a();
        C0690d m = a.m1283m();
        int action = event.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        int x = (int) event.getX();
        int y = (int) event.getY();
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1472b(a2, "view_id", this.f725m);
        C0802y.m1462a(a2, "ad_session_id", this.f738z);
        C0802y.m1472b(a2, "container_x", this.f726n + x);
        C0802y.m1472b(a2, "container_y", this.f727o + y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f711A.getId());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f711A.m1057c(), a2).m695b();
                break;
            case 1:
                if (!this.f711A.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f738z));
                }
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    new af("AdContainer.on_touch_ended", this.f711A.m1057c(), a2).m695b();
                    break;
                }
                new af("AdContainer.on_touch_cancelled", this.f711A.m1057c(), a2).m695b();
                break;
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f711A.m1057c(), a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f711A.m1057c(), a2).m695b();
                break;
            case 5:
                int action2 = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action2)) + this.f726n);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action2)) + this.f727o);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action2));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action2));
                new af("AdContainer.on_touch_began", this.f711A.m1057c(), a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                x = (int) event.getX(action);
                y = (int) event.getY(action);
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action)) + this.f726n);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action)) + this.f727o);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                if (!this.f711A.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f738z));
                }
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    new af("AdContainer.on_touch_ended", this.f711A.m1057c(), a2).m695b();
                    break;
                }
                new af("AdContainer.on_touch_cancelled", this.f711A.m1057c(), a2).m695b();
                break;
                break;
        }
        return true;
    }

    boolean m841c(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "id") == this.f725m && C0802y.m1473c(c, "container_id") == this.f711A.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f711A.m1053b())) {
            return true;
        }
        return false;
    }

    void m838a() {
        LayoutParams layoutParams;
        int o;
        int o2;
        JSONObject c = this.f712B.m698c();
        this.f738z = C0802y.m1468b(c, "ad_session_id");
        this.f726n = C0802y.m1473c(c, "x");
        this.f727o = C0802y.m1473c(c, "y");
        this.f728p = C0802y.m1473c(c, "width");
        this.f729q = C0802y.m1473c(c, "height");
        this.f731s = C0802y.m1473c(c, "font_family");
        this.f730r = C0802y.m1473c(c, "font_style");
        this.f732t = C0802y.m1473c(c, "font_size");
        this.f735w = C0802y.m1468b(c, FacebookAdapter.KEY_BACKGROUND_COLOR);
        this.f736x = C0802y.m1468b(c, "font_color");
        this.f737y = C0802y.m1468b(c, "text");
        this.f733u = C0802y.m1473c(c, "align_x");
        this.f734v = C0802y.m1473c(c, "align_y");
        C0740l a = C0594a.m605a();
        if (this.f737y.equals("")) {
            this.f737y = CtaButton.DEFAULT_CTA_TEXT;
        }
        setVisibility(4);
        if (C0802y.m1477d(c, "wrap_content")) {
            layoutParams = new FrameLayout.LayoutParams(-2, -2);
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.f728p, this.f729q);
        }
        layoutParams.gravity = 0;
        setText(this.f737y);
        setTextSize((float) this.f732t);
        if (C0802y.m1477d(c, "overlay")) {
            this.f726n = 0;
            this.f727o = 0;
            o = (int) (a.f1295c.m1324o() * 6.0f);
            o2 = (int) (a.f1295c.m1324o() * 6.0f);
            int o3 = (int) (a.f1295c.m1324o() * 4.0f);
            setPadding(o3, o3, o3, o3);
            layoutParams.gravity = 85;
        } else {
            o2 = 0;
            o = 0;
        }
        layoutParams.setMargins(this.f726n, this.f727o, o, o2);
        this.f711A.addView(this, layoutParams);
        switch (this.f731s) {
            case 0:
                setTypeface(Typeface.DEFAULT);
                break;
            case 1:
                setTypeface(Typeface.SERIF);
                break;
            case 2:
                setTypeface(Typeface.SANS_SERIF);
                break;
            case 3:
                setTypeface(Typeface.MONOSPACE);
                break;
        }
        switch (this.f730r) {
            case 0:
                setTypeface(getTypeface(), 0);
                break;
            case 1:
                setTypeface(getTypeface(), 1);
                break;
            case 2:
                setTypeface(getTypeface(), 2);
                break;
            case 3:
                setTypeface(getTypeface(), 3);
                break;
        }
        setGravity(m837a(true, this.f733u) | m837a(false, this.f734v));
        if (!this.f735w.equals("")) {
            setBackgroundColor(az.m899g(this.f735w));
        }
        if (!this.f736x.equals("")) {
            setTextColor(az.m899g(this.f736x));
        }
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_visible", new C06291(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_bounds", new C06313(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_font_color", new C06324(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_background_color", new C06335(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_typeface", new C06346(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_font_size", new C06357(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_font_style", new C06368(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.get_text", new C06379(this), true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.set_text", new ah(this) {
            final /* synthetic */ ax f701a;

            {
                this.f701a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f701a.m841c(afVar)) {
                    this.f701a.m847i(afVar);
                }
            }
        }, true));
        this.f711A.m1080n().add(C0594a.m604a("TextView.align", new C06302(this), true));
        this.f711A.m1082o().add("TextView.set_visible");
        this.f711A.m1082o().add("TextView.set_bounds");
        this.f711A.m1082o().add("TextView.set_font_color");
        this.f711A.m1082o().add("TextView.set_background_color");
        this.f711A.m1082o().add("TextView.set_typeface");
        this.f711A.m1082o().add("TextView.set_font_size");
        this.f711A.m1082o().add("TextView.set_font_style");
        this.f711A.m1082o().add("TextView.get_text");
        this.f711A.m1082o().add("TextView.set_text");
        this.f711A.m1082o().add("TextView.align");
        new C0595a().m622a("TextView added to layout").m624a(aa.f480d);
    }

    void m842d(af afVar) {
        JSONObject c = afVar.m698c();
        this.f726n = C0802y.m1473c(c, "x");
        this.f727o = C0802y.m1473c(c, "y");
        this.f728p = C0802y.m1473c(c, "width");
        this.f729q = C0802y.m1473c(c, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f726n, this.f727o, 0, 0);
        layoutParams.width = this.f728p;
        layoutParams.height = this.f729q;
        setLayoutParams(layoutParams);
    }

    void m843e(af afVar) {
        this.f735w = C0802y.m1468b(afVar.m698c(), FacebookAdapter.KEY_BACKGROUND_COLOR);
        setBackgroundColor(az.m899g(this.f735w));
    }

    void m844f(af afVar) {
        this.f736x = C0802y.m1468b(afVar.m698c(), "font_color");
        setTextColor(az.m899g(this.f736x));
    }

    void m845g(af afVar) {
        this.f732t = C0802y.m1473c(afVar.m698c(), "font_size");
        setTextSize((float) this.f732t);
    }

    void m846h(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "font_style");
        this.f730r = c;
        switch (c) {
            case 0:
                setTypeface(getTypeface(), 0);
                return;
            case 1:
                setTypeface(getTypeface(), 1);
                return;
            case 2:
                setTypeface(getTypeface(), 2);
                return;
            case 3:
                setTypeface(getTypeface(), 3);
                return;
            default:
                return;
        }
    }

    void m847i(af afVar) {
        this.f737y = C0802y.m1468b(afVar.m698c(), "text");
        setText(this.f737y);
    }

    void m848j(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "font_family");
        this.f731s = c;
        switch (c) {
            case 0:
                setTypeface(Typeface.DEFAULT);
                return;
            case 1:
                setTypeface(Typeface.SERIF);
                return;
            case 2:
                setTypeface(Typeface.SANS_SERIF);
                return;
            case 3:
                setTypeface(Typeface.MONOSPACE);
                return;
            default:
                return;
        }
    }

    void m849k(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
