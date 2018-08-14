package com.adcolony.sdk;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.File;
import org.json.JSONObject;

class C0800w extends ImageView {
    private int f1494a;
    private int f1495b;
    private int f1496c;
    private int f1497d;
    private int f1498e;
    private boolean f1499f;
    private boolean f1500g;
    private boolean f1501h;
    private String f1502i;
    private String f1503j;
    private af f1504k;
    private C0673c f1505l;

    class C07971 implements ah {
        final /* synthetic */ C0800w f1491a;

        C07971(C0800w c0800w) {
            this.f1491a = c0800w;
        }

        public void mo1863a(af afVar) {
            if (this.f1491a.m1435a(afVar)) {
                this.f1491a.m1441d(afVar);
            }
        }
    }

    class C07982 implements ah {
        final /* synthetic */ C0800w f1492a;

        C07982(C0800w c0800w) {
            this.f1492a = c0800w;
        }

        public void mo1863a(af afVar) {
            if (this.f1492a.m1435a(afVar)) {
                this.f1492a.m1437b(afVar);
            }
        }
    }

    class C07993 implements ah {
        final /* synthetic */ C0800w f1493a;

        C07993(C0800w c0800w) {
            this.f1493a = c0800w;
        }

        public void mo1863a(af afVar) {
            if (this.f1493a.m1435a(afVar)) {
                this.f1493a.m1439c(afVar);
            }
        }
    }

    C0800w(Context context, af afVar, int i, C0673c c0673c) {
        super(context);
        this.f1494a = i;
        this.f1504k = afVar;
        this.f1505l = c0673c;
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
        C0802y.m1472b(a2, "view_id", this.f1494a);
        C0802y.m1462a(a2, "ad_session_id", this.f1503j);
        C0802y.m1472b(a2, "container_x", this.f1495b + x);
        C0802y.m1472b(a2, "container_y", this.f1496c + y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f1505l.getId());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f1505l.m1057c(), a2).m695b();
                break;
            case 1:
                if (!this.f1505l.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f1503j));
                }
                if (x > 0 && x < this.f1497d && y > 0 && y < this.f1498e) {
                    new af("AdContainer.on_touch_ended", this.f1505l.m1057c(), a2).m695b();
                    break;
                }
                new af("AdContainer.on_touch_cancelled", this.f1505l.m1057c(), a2).m695b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f1505l.m1057c(), a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f1505l.m1057c(), a2).m695b();
                break;
            case 5:
                int action2 = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action2)) + this.f1495b);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action2)) + this.f1496c);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action2));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action2));
                new af("AdContainer.on_touch_began", this.f1505l.m1057c(), a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                x = (int) event.getX(action);
                y = (int) event.getY(action);
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action)) + this.f1495b);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action)) + this.f1496c);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                if (!this.f1505l.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f1503j));
                }
                if (x > 0 && x < this.f1497d && y > 0 && y < this.f1498e) {
                    new af("AdContainer.on_touch_ended", this.f1505l.m1057c(), a2).m695b();
                    break;
                }
                new af("AdContainer.on_touch_cancelled", this.f1505l.m1057c(), a2).m695b();
                break;
                break;
        }
        return true;
    }

    private boolean m1435a(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "id") == this.f1494a && C0802y.m1473c(c, "container_id") == this.f1505l.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f1505l.m1053b())) {
            return true;
        }
        return false;
    }

    void m1443a() {
        JSONObject c = this.f1504k.m698c();
        this.f1503j = C0802y.m1468b(c, "ad_session_id");
        this.f1495b = C0802y.m1473c(c, "x");
        this.f1496c = C0802y.m1473c(c, "y");
        this.f1497d = C0802y.m1473c(c, "width");
        this.f1498e = C0802y.m1473c(c, "height");
        this.f1502i = C0802y.m1468b(c, "filepath");
        this.f1499f = C0802y.m1477d(c, "dpi");
        this.f1500g = C0802y.m1477d(c, "invert_y");
        this.f1501h = C0802y.m1477d(c, "wrap_content");
        setImageURI(Uri.fromFile(new File(this.f1502i)));
        if (this.f1499f) {
            float o = (C0594a.m605a().m1284n().m1324o() * ((float) this.f1498e)) / ((float) getDrawable().getIntrinsicHeight());
            this.f1498e = (int) (((float) getDrawable().getIntrinsicHeight()) * o);
            this.f1497d = (int) (o * ((float) getDrawable().getIntrinsicWidth()));
            this.f1495b -= this.f1497d;
            this.f1496c = this.f1500g ? this.f1496c + this.f1498e : this.f1496c - this.f1498e;
        }
        setVisibility(4);
        LayoutParams layoutParams = this.f1501h ? new FrameLayout.LayoutParams(-2, -2) : new FrameLayout.LayoutParams(this.f1497d, this.f1498e);
        layoutParams.setMargins(this.f1495b, this.f1496c, 0, 0);
        layoutParams.gravity = 0;
        this.f1505l.addView(this, layoutParams);
        this.f1505l.m1080n().add(C0594a.m604a("ImageView.set_visible", new C07971(this), true));
        this.f1505l.m1080n().add(C0594a.m604a("ImageView.set_bounds", new C07982(this), true));
        this.f1505l.m1080n().add(C0594a.m604a("ImageView.set_image", new C07993(this), true));
        this.f1505l.m1082o().add("ImageView.set_visible");
        this.f1505l.m1082o().add("ImageView.set_bounds");
        this.f1505l.m1082o().add("ImageView.set_image");
    }

    private void m1437b(af afVar) {
        JSONObject c = afVar.m698c();
        this.f1495b = C0802y.m1473c(c, "x");
        this.f1496c = C0802y.m1473c(c, "y");
        this.f1497d = C0802y.m1473c(c, "width");
        this.f1498e = C0802y.m1473c(c, "height");
        if (this.f1499f) {
            float o = (C0594a.m605a().m1284n().m1324o() * ((float) this.f1498e)) / ((float) getDrawable().getIntrinsicHeight());
            this.f1498e = (int) (((float) getDrawable().getIntrinsicHeight()) * o);
            this.f1497d = (int) (o * ((float) getDrawable().getIntrinsicWidth()));
            this.f1495b -= this.f1497d;
            this.f1496c -= this.f1498e;
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f1495b, this.f1496c, 0, 0);
        layoutParams.width = this.f1497d;
        layoutParams.height = this.f1498e;
        setLayoutParams(layoutParams);
    }

    private void m1439c(af afVar) {
        this.f1502i = C0802y.m1468b(afVar.m698c(), "filepath");
        setImageURI(Uri.fromFile(new File(this.f1502i)));
    }

    private void m1441d(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    int[] m1444b() {
        return new int[]{this.f1495b, this.f1496c, this.f1497d, this.f1498e};
    }
}
