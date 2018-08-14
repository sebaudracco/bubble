package com.adcolony.sdk;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import org.json.JSONObject;

class C0792u extends GLSurfaceView {
    C0796v f1455a;
    int f1456b;
    int f1457c;
    int f1458d;
    int f1459e;
    int f1460f;
    String f1461g;
    boolean f1462h;
    C0673c f1463i;
    af f1464j;
    boolean f1465k;

    class C07901 implements ah {
        final /* synthetic */ C0792u f1453a;

        C07901(C0792u c0792u) {
            this.f1453a = c0792u;
        }

        public void mo1863a(af afVar) {
            if (this.f1453a.m1418a(afVar)) {
                this.f1453a.m1421c(afVar);
            }
        }
    }

    class C07912 implements ah {
        final /* synthetic */ C0792u f1454a;

        C07912(C0792u c0792u) {
            this.f1454a = c0792u;
        }

        public void mo1863a(af afVar) {
            if (this.f1454a.m1418a(afVar)) {
                this.f1454a.m1419b(afVar);
            }
        }
    }

    C0792u(Context context, af afVar, int i, C0673c c0673c) {
        super(context);
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        this.f1456b = i;
        this.f1464j = afVar;
        this.f1463i = c0673c;
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
        C0802y.m1472b(a2, "view_id", this.f1456b);
        C0802y.m1462a(a2, "ad_session_id", this.f1461g);
        C0802y.m1472b(a2, "container_x", this.f1457c + x);
        C0802y.m1472b(a2, "container_y", this.f1458d + y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f1463i.m1060d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f1463i.m1057c(), a2).m695b();
                break;
            case 1:
                if (!this.f1463i.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f1461g));
                }
                new af("AdContainer.on_touch_ended", this.f1463i.m1057c(), a2).m695b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f1463i.m1057c(), a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f1463i.m1057c(), a2).m695b();
                break;
            case 5:
                int action2 = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action2)) + this.f1457c);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action2)) + this.f1458d);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action2));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action2));
                new af("AdContainer.on_touch_began", this.f1463i.m1057c(), a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action)) + this.f1457c);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action)) + this.f1458d);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                if (!this.f1463i.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f1461g));
                }
                new af("AdContainer.on_touch_ended", this.f1463i.m1057c(), a2).m695b();
                break;
        }
        return true;
    }

    void m1417a() {
        JSONObject c = this.f1464j.m698c();
        this.f1461g = C0802y.m1468b(c, "ad_session_id");
        this.f1457c = C0802y.m1473c(c, "x");
        this.f1458d = C0802y.m1473c(c, "y");
        this.f1459e = C0802y.m1473c(c, "width");
        this.f1460f = C0802y.m1473c(c, "height");
        this.f1462h = C0802y.m1477d(c, "transparent");
        setEGLConfigChooser(8, 8, 8, 8, 16, 8);
        if (this.f1462h) {
            getHolder().setFormat(-3);
            setZOrderOnTop(true);
        } else {
            getHolder().setFormat(1);
        }
        this.f1455a = new C0796v(this, true, this.f1461g);
        setRenderer(this.f1455a);
        this.f1463i.m1080n().add(C0594a.m604a("RenderView.set_visible", new C07901(this), true));
        this.f1463i.m1080n().add(C0594a.m604a("RenderView.set_bounds", new C07912(this), true));
        this.f1463i.m1082o().add("RenderView.set_visible");
        this.f1463i.m1082o().add("RenderView.set_bounds");
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f1459e, this.f1460f);
        layoutParams.setMargins(this.f1457c, this.f1458d, 0, 0);
        layoutParams.gravity = 0;
        this.f1463i.addView(this, layoutParams);
    }

    boolean m1420b() {
        if (this.f1465k) {
            return false;
        }
        this.f1465k = true;
        this.f1455a.m1428a();
        return true;
    }

    protected void finalize() throws Throwable {
        m1420b();
    }

    boolean m1418a(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "id") == this.f1456b && C0802y.m1473c(c, "container_id") == this.f1463i.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f1463i.m1053b())) {
            return true;
        }
        return false;
    }

    void m1419b(af afVar) {
        JSONObject c = afVar.m698c();
        this.f1457c = C0802y.m1473c(c, "x");
        this.f1458d = C0802y.m1473c(c, "y");
        this.f1459e = C0802y.m1473c(c, "width");
        this.f1460f = C0802y.m1473c(c, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f1457c, this.f1458d, 0, 0);
        layoutParams.width = this.f1459e;
        layoutParams.height = this.f1460f;
        setLayoutParams(layoutParams);
        getHolder().setFixedSize(this.f1459e, this.f1460f);
    }

    void m1421c(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
