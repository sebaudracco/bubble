package com.adcolony.sdk;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import org.json.JSONObject;

class C0698h extends View {
    private int f986a;
    private int f987b;
    private int f988c;
    private int f989d;
    private int f990e;
    private String f991f;
    private String f992g;
    private C0673c f993h;
    private af f994i;

    class C06951 implements ah {
        final /* synthetic */ C0698h f983a;

        C06951(C0698h c0698h) {
            this.f983a = c0698h;
        }

        public void mo1863a(af afVar) {
            if (this.f983a.m1193a(afVar)) {
                this.f983a.m1194b(afVar);
            }
        }
    }

    class C06962 implements ah {
        final /* synthetic */ C0698h f984a;

        C06962(C0698h c0698h) {
            this.f984a = c0698h;
        }

        public void mo1863a(af afVar) {
            if (this.f984a.m1193a(afVar)) {
                this.f984a.m1196d(afVar);
            }
        }
    }

    class C06973 implements ah {
        final /* synthetic */ C0698h f985a;

        C06973(C0698h c0698h) {
            this.f985a = c0698h;
        }

        public void mo1863a(af afVar) {
            if (this.f985a.m1193a(afVar)) {
                this.f985a.m1195c(afVar);
            }
        }
    }

    C0698h(Context context, af afVar, int i, C0673c c0673c) {
        super(context);
        this.f993h = c0673c;
        this.f994i = afVar;
        this.f986a = i;
    }

    boolean m1193a(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "id") == this.f986a && C0802y.m1473c(c, "container_id") == this.f993h.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f993h.m1053b())) {
            return true;
        }
        return false;
    }

    void m1192a() {
        JSONObject c = this.f994i.m698c();
        this.f992g = C0802y.m1468b(c, "ad_session_id");
        this.f987b = C0802y.m1473c(c, "x");
        this.f988c = C0802y.m1473c(c, "y");
        this.f989d = C0802y.m1473c(c, "width");
        this.f990e = C0802y.m1473c(c, "height");
        this.f991f = C0802y.m1468b(c, "color");
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f989d, this.f990e);
        layoutParams.setMargins(this.f987b, this.f988c, 0, 0);
        layoutParams.gravity = 0;
        this.f993h.addView(this, layoutParams);
        setBackgroundColor(az.m899g(this.f991f));
        this.f993h.m1080n().add(C0594a.m604a("ColorView.set_bounds", new C06951(this), true));
        this.f993h.m1080n().add(C0594a.m604a("ColorView.set_visible", new C06962(this), true));
        this.f993h.m1080n().add(C0594a.m604a("ColorView.set_color", new C06973(this), true));
        this.f993h.m1082o().add("ColorView.set_bounds");
        this.f993h.m1082o().add("ColorView.set_visible");
        this.f993h.m1082o().add("ColorView.set_color");
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
        C0802y.m1472b(a2, "view_id", this.f986a);
        C0802y.m1462a(a2, "ad_session_id", this.f992g);
        C0802y.m1472b(a2, "container_x", this.f987b + x);
        C0802y.m1472b(a2, "container_y", this.f988c + y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f993h.m1060d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f993h.m1057c(), a2).m695b();
                break;
            case 1:
                if (!this.f993h.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f992g));
                }
                new af("AdContainer.on_touch_ended", this.f993h.m1057c(), a2).m695b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f993h.m1057c(), a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f993h.m1057c(), a2).m695b();
                break;
            case 5:
                int action2 = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action2)) + this.f987b);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action2)) + this.f988c);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action2));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action2));
                new af("AdContainer.on_touch_began", this.f993h.m1057c(), a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action)) + this.f987b);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action)) + this.f988c);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                if (!this.f993h.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f992g));
                }
                new af("AdContainer.on_touch_ended", this.f993h.m1057c(), a2).m695b();
                break;
        }
        return true;
    }

    void m1194b(af afVar) {
        JSONObject c = afVar.m698c();
        this.f987b = C0802y.m1473c(c, "x");
        this.f988c = C0802y.m1473c(c, "y");
        this.f989d = C0802y.m1473c(c, "width");
        this.f990e = C0802y.m1473c(c, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f987b, this.f988c, 0, 0);
        layoutParams.width = this.f989d;
        layoutParams.height = this.f990e;
        setLayoutParams(layoutParams);
    }

    void m1195c(af afVar) {
        setBackgroundColor(az.m899g(C0802y.m1468b(afVar.m698c(), "color")));
    }

    void m1196d(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
