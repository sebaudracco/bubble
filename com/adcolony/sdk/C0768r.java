package com.adcolony.sdk;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.google.ads.mediation.facebook.FacebookAdapter;
import org.json.JSONObject;

class C0768r extends EditText {
    private C0673c f1383A;
    private af f1384B;
    private final int f1385a = 0;
    private final int f1386b = 1;
    private final int f1387c = 2;
    private final int f1388d = 3;
    private final int f1389e = 1;
    private final int f1390f = 2;
    private final int f1391g = 3;
    private final int f1392h = 0;
    private final int f1393i = 1;
    private final int f1394j = 2;
    private final int f1395k = 1;
    private final int f1396l = 2;
    private int f1397m;
    private int f1398n;
    private int f1399o;
    private int f1400p;
    private int f1401q;
    private int f1402r;
    private int f1403s;
    private int f1404t;
    private int f1405u;
    private int f1406v;
    private String f1407w;
    private String f1408x;
    private String f1409y;
    private String f1410z;

    class C07591 implements ah {
        final /* synthetic */ C0768r f1374a;

        C07591(C0768r c0768r) {
            this.f1374a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1374a.m1370b(afVar)) {
                this.f1374a.m1379k(afVar);
            }
        }
    }

    class C07602 implements ah {
        final /* synthetic */ C0768r f1375a;

        C07602(C0768r c0768r) {
            this.f1375a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1375a.m1370b(afVar)) {
                this.f1375a.m1369a(afVar);
            }
        }
    }

    class C07613 implements ah {
        final /* synthetic */ C0768r f1376a;

        C07613(C0768r c0768r) {
            this.f1376a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1376a.m1370b(afVar)) {
                this.f1376a.m1372d(afVar);
            }
        }
    }

    class C07624 implements ah {
        final /* synthetic */ C0768r f1377a;

        C07624(C0768r c0768r) {
            this.f1377a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1377a.m1370b(afVar)) {
                this.f1377a.m1374f(afVar);
            }
        }
    }

    class C07635 implements ah {
        final /* synthetic */ C0768r f1378a;

        C07635(C0768r c0768r) {
            this.f1378a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1378a.m1370b(afVar)) {
                this.f1378a.m1373e(afVar);
            }
        }
    }

    class C07646 implements ah {
        final /* synthetic */ C0768r f1379a;

        C07646(C0768r c0768r) {
            this.f1379a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1379a.m1370b(afVar)) {
                this.f1379a.m1378j(afVar);
            }
        }
    }

    class C07657 implements ah {
        final /* synthetic */ C0768r f1380a;

        C07657(C0768r c0768r) {
            this.f1380a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1380a.m1370b(afVar)) {
                this.f1380a.m1375g(afVar);
            }
        }
    }

    class C07668 implements ah {
        final /* synthetic */ C0768r f1381a;

        C07668(C0768r c0768r) {
            this.f1381a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1381a.m1370b(afVar)) {
                this.f1381a.m1376h(afVar);
            }
        }
    }

    class C07679 implements ah {
        final /* synthetic */ C0768r f1382a;

        C07679(C0768r c0768r) {
            this.f1382a = c0768r;
        }

        public void mo1863a(af afVar) {
            if (this.f1382a.m1370b(afVar)) {
                this.f1382a.m1371c(afVar);
            }
        }
    }

    C0768r(Context context, af afVar, int i, C0673c c0673c) {
        super(context);
        this.f1397m = i;
        this.f1384B = afVar;
        this.f1383A = c0673c;
    }

    void m1369a(af afVar) {
        JSONObject c = afVar.m698c();
        this.f1405u = C0802y.m1473c(c, "x");
        this.f1406v = C0802y.m1473c(c, "y");
        setGravity(m1367a(true, this.f1405u) | m1367a(false, this.f1406v));
    }

    int m1367a(boolean z, int i) {
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
        C0802y.m1472b(a2, "view_id", this.f1397m);
        C0802y.m1462a(a2, "ad_session_id", this.f1407w);
        C0802y.m1472b(a2, "container_x", this.f1398n + x);
        C0802y.m1472b(a2, "container_y", this.f1399o + y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f1383A.m1060d());
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f1383A.m1057c(), a2).m695b();
                break;
            case 1:
                if (!this.f1383A.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f1407w));
                }
                new af("AdContainer.on_touch_ended", this.f1383A.m1057c(), a2).m695b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f1383A.m1057c(), a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f1383A.m1057c(), a2).m695b();
                break;
            case 5:
                int action2 = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action2)) + this.f1398n);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action2)) + this.f1399o);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action2));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action2));
                new af("AdContainer.on_touch_began", this.f1383A.m1057c(), a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", ((int) event.getX(action)) + this.f1398n);
                C0802y.m1472b(a2, "container_y", ((int) event.getY(action)) + this.f1399o);
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                if (!this.f1383A.m1085r()) {
                    a.m1255a((bc) m.m1159f().get(this.f1407w));
                }
                new af("AdContainer.on_touch_ended", this.f1383A.m1057c(), a2).m695b();
                break;
        }
        return true;
    }

    boolean m1370b(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "id") == this.f1397m && C0802y.m1473c(c, "container_id") == this.f1383A.m1060d() && C0802y.m1468b(c, "ad_session_id").equals(this.f1383A.m1053b())) {
            return true;
        }
        return false;
    }

    void m1368a() {
        JSONObject c = this.f1384B.m698c();
        this.f1407w = C0802y.m1468b(c, "ad_session_id");
        this.f1398n = C0802y.m1473c(c, "x");
        this.f1399o = C0802y.m1473c(c, "y");
        this.f1400p = C0802y.m1473c(c, "width");
        this.f1401q = C0802y.m1473c(c, "height");
        this.f1403s = C0802y.m1473c(c, "font_family");
        this.f1402r = C0802y.m1473c(c, "font_style");
        this.f1404t = C0802y.m1473c(c, "font_size");
        this.f1408x = C0802y.m1468b(c, FacebookAdapter.KEY_BACKGROUND_COLOR);
        this.f1409y = C0802y.m1468b(c, "font_color");
        this.f1410z = C0802y.m1468b(c, "text");
        this.f1405u = C0802y.m1473c(c, "align_x");
        this.f1406v = C0802y.m1473c(c, "align_y");
        setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.f1400p, this.f1401q);
        layoutParams.setMargins(this.f1398n, this.f1399o, 0, 0);
        layoutParams.gravity = 0;
        this.f1383A.addView(this, layoutParams);
        switch (this.f1403s) {
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
        switch (this.f1402r) {
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
        setText(this.f1410z);
        setTextSize((float) this.f1404t);
        setGravity(m1367a(true, this.f1405u) | m1367a(false, this.f1406v));
        if (!this.f1408x.equals("")) {
            setBackgroundColor(az.m899g(this.f1408x));
        }
        if (!this.f1409y.equals("")) {
            setTextColor(az.m899g(this.f1409y));
        }
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_visible", new C07591(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_bounds", new C07613(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_font_color", new C07624(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_background_color", new C07635(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_typeface", new C07646(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_font_size", new C07657(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_font_style", new C07668(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.get_text", new C07679(this), true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.set_text", new ah(this) {
            final /* synthetic */ C0768r f1373a;

            {
                this.f1373a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f1373a.m1370b(afVar)) {
                    this.f1373a.m1377i(afVar);
                }
            }
        }, true));
        this.f1383A.m1080n().add(C0594a.m604a("TextView.align", new C07602(this), true));
        this.f1383A.m1082o().add("TextView.set_visible");
        this.f1383A.m1082o().add("TextView.set_bounds");
        this.f1383A.m1082o().add("TextView.set_font_color");
        this.f1383A.m1082o().add("TextView.set_background_color");
        this.f1383A.m1082o().add("TextView.set_typeface");
        this.f1383A.m1082o().add("TextView.set_font_size");
        this.f1383A.m1082o().add("TextView.set_font_style");
        this.f1383A.m1082o().add("TextView.get_text");
        this.f1383A.m1082o().add("TextView.set_text");
        this.f1383A.m1082o().add("TextView.align");
    }

    void m1371c(af afVar) {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "text", getText().toString());
        afVar.m694a(a).m695b();
    }

    void m1372d(af afVar) {
        JSONObject c = afVar.m698c();
        this.f1398n = C0802y.m1473c(c, "x");
        this.f1399o = C0802y.m1473c(c, "y");
        this.f1400p = C0802y.m1473c(c, "width");
        this.f1401q = C0802y.m1473c(c, "height");
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.setMargins(this.f1398n, this.f1399o, 0, 0);
        layoutParams.width = this.f1400p;
        layoutParams.height = this.f1401q;
        setLayoutParams(layoutParams);
    }

    void m1373e(af afVar) {
        this.f1408x = C0802y.m1468b(afVar.m698c(), FacebookAdapter.KEY_BACKGROUND_COLOR);
        setBackgroundColor(az.m899g(this.f1408x));
    }

    void m1374f(af afVar) {
        this.f1409y = C0802y.m1468b(afVar.m698c(), "font_color");
        setTextColor(az.m899g(this.f1409y));
    }

    void m1375g(af afVar) {
        this.f1404t = C0802y.m1473c(afVar.m698c(), "font_size");
        setTextSize((float) this.f1404t);
    }

    void m1376h(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "font_style");
        this.f1402r = c;
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

    void m1377i(af afVar) {
        this.f1410z = C0802y.m1468b(afVar.m698c(), "text");
        setText(this.f1410z);
    }

    void m1378j(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "font_family");
        this.f1403s = c;
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

    void m1379k(af afVar) {
        if (C0802y.m1477d(afVar.m698c(), "visible")) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }
}
