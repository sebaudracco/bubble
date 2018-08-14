package com.adcolony.sdk;

import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import android.widget.VideoView;
import com.adcolony.sdk.aa.C0595a;
import com.integralads.avid.library.adcolony.session.AvidManagedVideoAdSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

class C0673c extends FrameLayout {
    private AvidManagedVideoAdSession f869A;
    boolean f870a;
    boolean f871b;
    Context f872c;
    VideoView f873d;
    private HashMap<Integer, ba> f874e;
    private HashMap<Integer, ax> f875f;
    private HashMap<Integer, C0792u> f876g;
    private HashMap<Integer, bb> f877h;
    private HashMap<Integer, C0698h> f878i;
    private HashMap<Integer, C0768r> f879j;
    private HashMap<Integer, C0800w> f880k;
    private HashMap<Integer, Boolean> f881l;
    private HashMap<Integer, View> f882m;
    private int f883n;
    private int f884o;
    private int f885p;
    private int f886q;
    private String f887r;
    private float f888s = 0.0f;
    private double f889t = 0.0d;
    private long f890u = 0;
    private ArrayList<ah> f891v;
    private ArrayList<String> f892w;
    private boolean f893x;
    private boolean f894y;
    private boolean f895z;

    class C06641 implements ah {
        final /* synthetic */ C0673c f858a;

        C06641(C0673c c0673c) {
            this.f858a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f858a.m1052a(afVar)) {
                this.f858a.m1048a(this.f858a.m1066g(afVar));
            }
        }
    }

    class C06652 implements ah {
        final /* synthetic */ C0673c f859a;

        C06652(C0673c c0673c) {
            this.f859a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f859a.m1052a(afVar)) {
                this.f859a.m1065f(afVar);
            }
        }
    }

    class C06663 implements ah {
        final /* synthetic */ C0673c f860a;

        C06663(C0673c c0673c) {
            this.f860a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f860a.m1052a(afVar)) {
                this.f860a.m1048a(this.f860a.m1058c(afVar));
            }
        }
    }

    class C06674 implements ah {
        final /* synthetic */ C0673c f861a;

        C06674(C0673c c0673c) {
            this.f861a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f861a.m1052a(afVar)) {
                this.f861a.m1061d(afVar);
            }
        }
    }

    class C06707 implements ah {
        final /* synthetic */ C0673c f866a;

        C06707(C0673c c0673c) {
            this.f866a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f866a.m1052a(afVar)) {
                this.f866a.m1069h(afVar);
            }
        }
    }

    class C06718 implements ah {
        final /* synthetic */ C0673c f867a;

        C06718(C0673c c0673c) {
            this.f867a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f867a.m1052a(afVar)) {
                this.f867a.m1048a(this.f867a.m1070i(afVar));
            }
        }
    }

    class C06729 implements ah {
        final /* synthetic */ C0673c f868a;

        C06729(C0673c c0673c) {
            this.f868a = c0673c;
        }

        public void mo1863a(af afVar) {
            if (this.f868a.m1052a(afVar)) {
                this.f868a.m1073j(afVar);
            }
        }
    }

    C0673c(Context context, String str) {
        super(context);
        this.f872c = context;
        this.f887r = str;
        setBackgroundColor(-16777216);
    }

    boolean m1052a(af afVar) {
        JSONObject c = afVar.m698c();
        if (C0802y.m1473c(c, "container_id") == this.f885p && C0802y.m1468b(c, "ad_session_id").equals(this.f887r)) {
            return true;
        }
        return false;
    }

    void m1055b(af afVar) {
        this.f874e = new HashMap();
        this.f875f = new HashMap();
        this.f876g = new HashMap();
        this.f877h = new HashMap();
        this.f878i = new HashMap();
        this.f879j = new HashMap();
        this.f880k = new HashMap();
        this.f881l = new HashMap();
        this.f882m = new HashMap();
        this.f891v = new ArrayList();
        this.f892w = new ArrayList();
        JSONObject c = afVar.m698c();
        this.f885p = C0802y.m1473c(c, "id");
        this.f883n = C0802y.m1473c(c, "width");
        this.f884o = C0802y.m1473c(c, "height");
        this.f886q = C0802y.m1473c(c, "module_id");
        this.f871b = C0802y.m1477d(c, "viewability_enabled");
        this.f893x = this.f885p == 1;
        C0740l a = C0594a.m605a();
        if (this.f883n == 0 && this.f884o == 0) {
            this.f883n = a.f1295c.m1326q();
            this.f884o = a.m1271d().getMultiWindowEnabled() ? a.f1295c.m1327r() - az.m886b(C0594a.m613c()) : a.f1295c.m1327r();
        } else {
            setLayoutParams(new LayoutParams(this.f883n, this.f884o));
        }
        this.f891v.add(C0594a.m604a("VideoView.create", new C06641(this), true));
        this.f891v.add(C0594a.m604a("VideoView.destroy", new C06707(this), true));
        this.f891v.add(C0594a.m604a("WebView.create", new C06718(this), true));
        this.f891v.add(C0594a.m604a("WebView.destroy", new C06729(this), true));
        this.f891v.add(C0594a.m604a("RenderView.create", new ah(this) {
            final /* synthetic */ C0673c f853a;

            {
                this.f853a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f853a.m1052a(afVar)) {
                    this.f853a.m1048a(this.f853a.m1078m(afVar));
                }
            }
        }, true));
        this.f891v.add(C0594a.m604a("RenderView.destroy", new ah(this) {
            final /* synthetic */ C0673c f854a;

            {
                this.f854a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f854a.m1052a(afVar)) {
                    this.f854a.m1081n(afVar);
                }
            }
        }, true));
        this.f891v.add(C0594a.m604a("TextView.create", new ah(this) {
            final /* synthetic */ C0673c f855a;

            {
                this.f855a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f855a.m1052a(afVar)) {
                    this.f855a.m1048a(this.f855a.m1074k(afVar));
                }
            }
        }, true));
        this.f891v.add(C0594a.m604a("TextView.destroy", new ah(this) {
            final /* synthetic */ C0673c f856a;

            {
                this.f856a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f856a.m1052a(afVar)) {
                    this.f856a.m1077l(afVar);
                }
            }
        }, true));
        this.f891v.add(C0594a.m604a("ImageView.create", new ah(this) {
            final /* synthetic */ C0673c f857a;

            {
                this.f857a = r1;
            }

            public void mo1863a(af afVar) {
                if (this.f857a.m1052a(afVar)) {
                    this.f857a.m1048a(this.f857a.m1062e(afVar));
                }
            }
        }, true));
        this.f891v.add(C0594a.m604a("ImageView.destroy", new C06652(this), true));
        this.f891v.add(C0594a.m604a("ColorView.create", new C06663(this), true));
        this.f891v.add(C0594a.m604a("ColorView.destroy", new C06674(this), true));
        this.f892w.add("VideoView.create");
        this.f892w.add("VideoView.destroy");
        this.f892w.add("WebView.create");
        this.f892w.add("WebView.destroy");
        this.f892w.add("RenderView.create");
        this.f892w.add("RenderView.destroy");
        this.f892w.add("TextView.create");
        this.f892w.add("TextView.destroy");
        this.f892w.add("ImageView.create");
        this.f892w.add("ImageView.destroy");
        this.f892w.add("ColorView.create");
        this.f892w.add("ColorView.destroy");
        this.f873d = new VideoView(this.f872c);
        this.f873d.setVisibility(8);
        addView(this.f873d);
        if (this.f871b) {
            m1045d(C0802y.m1477d(afVar.m698c(), "advanced_viewability"));
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & 255;
        if (action != 0 && action != 1 && action != 3 && action != 2 && action != 5 && action != 6) {
            return false;
        }
        C0740l a = C0594a.m605a();
        C0690d m = a.m1283m();
        int x = (int) event.getX();
        int y = (int) event.getY();
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1472b(a2, "view_id", -1);
        C0802y.m1462a(a2, "ad_session_id", this.f887r);
        C0802y.m1472b(a2, "container_x", x);
        C0802y.m1472b(a2, "container_y", y);
        C0802y.m1472b(a2, "view_x", x);
        C0802y.m1472b(a2, "view_y", y);
        C0802y.m1472b(a2, "id", this.f885p);
        switch (action) {
            case 0:
                new af("AdContainer.on_touch_began", this.f886q, a2).m695b();
                break;
            case 1:
                if (!this.f893x) {
                    a.m1255a((bc) m.m1159f().get(this.f887r));
                }
                new af("AdContainer.on_touch_ended", this.f886q, a2).m695b();
                break;
            case 2:
                new af("AdContainer.on_touch_moved", this.f886q, a2).m695b();
                break;
            case 3:
                new af("AdContainer.on_touch_cancelled", this.f886q, a2).m695b();
                break;
            case 5:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", (int) event.getX(action));
                C0802y.m1472b(a2, "container_y", (int) event.getY(action));
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                new af("AdContainer.on_touch_began", this.f886q, a2).m695b();
                break;
            case 6:
                action = (event.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                C0802y.m1472b(a2, "container_x", (int) event.getX(action));
                C0802y.m1472b(a2, "container_y", (int) event.getY(action));
                C0802y.m1472b(a2, "view_x", (int) event.getX(action));
                C0802y.m1472b(a2, "view_y", (int) event.getY(action));
                C0802y.m1472b(a2, "x", (int) event.getX(action));
                C0802y.m1472b(a2, "y", (int) event.getY(action));
                if (!this.f893x) {
                    a.m1255a((bc) m.m1159f().get(this.f887r));
                }
                new af("AdContainer.on_touch_ended", this.f886q, a2).m695b();
                break;
        }
        return true;
    }

    C0698h m1058c(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0698h c0698h = new C0698h(this.f872c, afVar, c, this);
        c0698h.m1192a();
        this.f878i.put(Integer.valueOf(c), c0698h);
        this.f882m.put(Integer.valueOf(c), c0698h);
        return c0698h;
    }

    boolean m1061d(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0698h c0698h = (C0698h) this.f878i.remove(Integer.valueOf(c));
        if (((View) this.f882m.remove(Integer.valueOf(c))) == null || c0698h == null) {
            C0594a.m605a().m1283m().m1151a(afVar.m699d(), "" + c);
            return false;
        }
        removeView(c0698h);
        return true;
    }

    C0800w m1062e(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0800w c0800w = new C0800w(this.f872c, afVar, c, this);
        c0800w.m1443a();
        this.f880k.put(Integer.valueOf(c), c0800w);
        this.f882m.put(Integer.valueOf(c), c0800w);
        return c0800w;
    }

    boolean m1065f(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0800w c0800w = (C0800w) this.f880k.remove(Integer.valueOf(c));
        if (((View) this.f882m.remove(Integer.valueOf(c))) == null || c0800w == null) {
            C0594a.m605a().m1283m().m1151a(afVar.m699d(), "" + c);
            return false;
        }
        removeView(c0800w);
        return true;
    }

    ba m1066g(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        ba baVar = new ba(this.f872c, afVar, c, this);
        baVar.m957b();
        this.f874e.put(Integer.valueOf(c), baVar);
        this.f882m.put(Integer.valueOf(c), baVar);
        return baVar;
    }

    boolean m1069h(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        ba baVar = (ba) this.f874e.remove(Integer.valueOf(c));
        if (((View) this.f882m.remove(Integer.valueOf(c))) == null || baVar == null) {
            C0594a.m605a().m1283m().m1151a(afVar.m699d(), "" + c);
            return false;
        }
        if (baVar.m963h()) {
            baVar.m959d();
        }
        baVar.m956a();
        removeView(baVar);
        return true;
    }

    bb m1070i(af afVar) {
        bb bbVar;
        JSONObject c = afVar.m698c();
        int c2 = C0802y.m1473c(c, "id");
        boolean d = C0802y.m1477d(c, "is_module");
        C0740l a = C0594a.m605a();
        if (d) {
            bbVar = (bb) a.m1294x().get(Integer.valueOf(C0802y.m1473c(c, "module_id")));
            if (bbVar == null) {
                new C0595a().m622a("Module WebView created with invalid id").m624a(aa.f483g);
                return null;
            }
            bbVar.m988a(afVar, c2, this);
        } else {
            bbVar = new bb(this.f872c, afVar, c2, a.m1287q().m709d(), this);
        }
        this.f877h.put(Integer.valueOf(c2), bbVar);
        this.f882m.put(Integer.valueOf(c2), bbVar);
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1472b(a2, "module_id", bbVar.mo1841a());
        afVar.m694a(a2).m695b();
        return bbVar;
    }

    boolean m1073j(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0740l a = C0594a.m605a();
        View view = (View) this.f882m.remove(Integer.valueOf(c));
        bb bbVar = (bb) this.f877h.remove(Integer.valueOf(c));
        if (bbVar == null || view == null) {
            a.m1283m().m1151a(afVar.m699d(), "" + c);
            return false;
        }
        a.m1287q().m700a(bbVar.mo1841a());
        removeView(bbVar);
        return true;
    }

    View m1074k(af afVar) {
        JSONObject c = afVar.m698c();
        int c2 = C0802y.m1473c(c, "id");
        View c0768r;
        if (C0802y.m1477d(c, "editable")) {
            c0768r = new C0768r(this.f872c, afVar, c2, this);
            c0768r.m1368a();
            this.f879j.put(Integer.valueOf(c2), c0768r);
            this.f882m.put(Integer.valueOf(c2), c0768r);
            this.f881l.put(Integer.valueOf(c2), Boolean.valueOf(true));
            return c0768r;
        } else if (C0802y.m1477d(c, "button")) {
            c0768r = new ax(this.f872c, 16974145, afVar, c2, this);
            c0768r.m838a();
            this.f875f.put(Integer.valueOf(c2), c0768r);
            this.f882m.put(Integer.valueOf(c2), c0768r);
            this.f881l.put(Integer.valueOf(c2), Boolean.valueOf(false));
            return c0768r;
        } else {
            c0768r = new ax(this.f872c, afVar, c2, this);
            c0768r.m838a();
            this.f875f.put(Integer.valueOf(c2), c0768r);
            this.f882m.put(Integer.valueOf(c2), c0768r);
            this.f881l.put(Integer.valueOf(c2), Boolean.valueOf(false));
            return c0768r;
        }
    }

    boolean m1077l(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        View view = (View) this.f882m.remove(Integer.valueOf(c));
        if (((Boolean) this.f881l.remove(Integer.valueOf(this.f885p))).booleanValue()) {
            View view2 = (TextView) this.f879j.remove(Integer.valueOf(c));
        } else {
            TextView textView = (TextView) this.f875f.remove(Integer.valueOf(c));
        }
        if (view == null || view2 == null) {
            C0594a.m605a().m1283m().m1151a(afVar.m699d(), "" + c);
            return false;
        }
        removeView(view2);
        return true;
    }

    C0792u m1078m(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0792u c0792u = new C0792u(this.f872c, afVar, c, this);
        c0792u.m1417a();
        this.f876g.put(Integer.valueOf(c), c0792u);
        this.f882m.put(Integer.valueOf(c), c0792u);
        return c0792u;
    }

    boolean m1081n(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "id");
        C0792u c0792u = (C0792u) this.f876g.remove(Integer.valueOf(c));
        if (((View) this.f882m.remove(Integer.valueOf(c))) == null || c0792u == null) {
            C0594a.m605a().m1283m().m1151a(afVar.m699d(), "" + c);
            return false;
        }
        c0792u.m1420b();
        removeView(c0792u);
        return true;
    }

    private void m1045d(final boolean z) {
        final Runnable c06685 = new Runnable(this) {
            final /* synthetic */ C0673c f863b;

            public void run() {
                if (this.f863b.f890u == 0) {
                    this.f863b.f890u = System.currentTimeMillis();
                }
                float a = bh.m1017a((View) this.f863b.getParent(), C0594a.m613c(), true, z, true);
                double b = az.m885b(az.m869a(C0594a.m613c()));
                long currentTimeMillis = System.currentTimeMillis();
                if (this.f863b.f890u + 200 < currentTimeMillis) {
                    this.f863b.f890u = currentTimeMillis;
                    if (!(this.f863b.f888s == a && this.f863b.f889t == b)) {
                        this.f863b.m1041a(a, b);
                    }
                    this.f863b.f888s = a;
                    this.f863b.f889t = b;
                }
            }
        };
        new Thread(new Runnable(this) {
            final /* synthetic */ C0673c f865b;

            public void run() {
                while (!this.f865b.f870a) {
                    az.m880a(c06685);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private void m1041a(@FloatRange(from = 0.0d, to = 100.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) double d) {
        JSONObject a = C0802y.m1453a();
        C0802y.m1472b(a, "id", this.f885p);
        C0802y.m1462a(a, "ad_session_id", this.f887r);
        C0802y.m1460a(a, "exposure", (double) f);
        C0802y.m1460a(a, "volume", d);
        new af("AdContainer.on_exposure_change", this.f886q, a).m695b();
    }

    void m1046a() {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f887r);
        new af("AdSession.on_error", this.f886q, a).m695b();
    }

    String m1053b() {
        return this.f887r;
    }

    int m1057c() {
        return this.f886q;
    }

    int m1060d() {
        return this.f885p;
    }

    HashMap<Integer, ba> m1063e() {
        return this.f874e;
    }

    HashMap<Integer, ax> m1064f() {
        return this.f875f;
    }

    HashMap<Integer, C0792u> m1067g() {
        return this.f876g;
    }

    HashMap<Integer, bb> m1068h() {
        return this.f877h;
    }

    HashMap<Integer, C0698h> m1071i() {
        return this.f878i;
    }

    HashMap<Integer, C0768r> m1072j() {
        return this.f879j;
    }

    HashMap<Integer, C0800w> m1075k() {
        return this.f880k;
    }

    HashMap<Integer, Boolean> m1076l() {
        return this.f881l;
    }

    HashMap<Integer, View> m1079m() {
        return this.f882m;
    }

    ArrayList<ah> m1080n() {
        return this.f891v;
    }

    ArrayList<String> m1082o() {
        return this.f892w;
    }

    int m1083p() {
        return this.f884o;
    }

    void m1047a(int i) {
        this.f884o = i;
    }

    int m1084q() {
        return this.f883n;
    }

    void m1054b(int i) {
        this.f883n = i;
    }

    boolean m1085r() {
        return this.f893x;
    }

    void m1051a(boolean z) {
        this.f893x = z;
    }

    boolean m1086s() {
        return this.f895z;
    }

    void m1056b(boolean z) {
        this.f895z = z;
    }

    boolean m1087t() {
        return this.f894y;
    }

    void m1059c(boolean z) {
        this.f894y = z;
    }

    void m1049a(AvidManagedVideoAdSession avidManagedVideoAdSession) {
        this.f869A = avidManagedVideoAdSession;
        m1050a(this.f882m);
    }

    void m1050a(Map map) {
        if (this.f869A != null && map != null) {
            for (Entry value : map.entrySet()) {
                this.f869A.registerFriendlyObstruction((View) value.getValue());
            }
        }
    }

    void m1048a(View view) {
        if (this.f869A != null && view != null) {
            this.f869A.registerFriendlyObstruction(view);
        }
    }
}
