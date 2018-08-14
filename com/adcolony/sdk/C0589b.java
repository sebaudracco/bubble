package com.adcolony.sdk;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout.LayoutParams;
import com.adcolony.sdk.aa.C0595a;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;

class C0589b extends Activity {
    final int f376a = 0;
    final int f377b = 1;
    C0673c f378c;
    int f379d = -1;
    String f380e;
    int f381f;
    boolean f382g;
    boolean f383h;
    boolean f384i;
    boolean f385j;
    boolean f386k;
    boolean f387l;
    C0691f f388m;

    class C06401 implements ah {
        final /* synthetic */ C0589b f756a;

        C06401(C0589b c0589b) {
            this.f756a = c0589b;
        }

        public void mo1863a(af afVar) {
            this.f756a.mo1845a(afVar);
        }
    }

    class C06412 implements ah {
        final /* synthetic */ C0589b f757a;

        C06412(C0589b c0589b) {
            this.f757a = c0589b;
        }

        public void mo1863a(af afVar) {
            JSONObject c = afVar.m698c();
            if (C0802y.m1468b(c, "id").equals(this.f757a.f380e)) {
                this.f757a.m544a(C0802y.m1473c(c, "orientation"));
            }
        }
    }

    C0589b() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!C0594a.m612b() || C0594a.m605a().m1289s() == null) {
            finish();
            return;
        }
        C0740l a = C0594a.m605a();
        this.f384i = false;
        this.f378c = a.m1289s();
        this.f378c.m1056b(false);
        if (az.m900g()) {
            this.f378c.m1056b(true);
        }
        this.f380e = this.f378c.m1053b();
        this.f381f = this.f378c.m1057c();
        this.f388m = (C0691f) C0594a.m605a().m1283m().m1160g().get(this.f380e);
        this.f385j = a.m1271d().getMultiWindowEnabled();
        if (this.f385j) {
            getWindow().addFlags(2048);
            getWindow().clearFlags(1024);
        } else {
            getWindow().addFlags(1024);
            getWindow().clearFlags(2048);
        }
        requestWindowFeature(1);
        getWindow().getDecorView().setBackgroundColor(-16777216);
        if (a.m1271d().getKeepScreenOn()) {
            getWindow().addFlags(128);
        }
        ViewParent parent = this.f378c.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(this.f378c);
        }
        setContentView(this.f378c);
        this.f378c.m1080n().add(C0594a.m604a("AdSession.finish_fullscreen_ad", new C06401(this), true));
        this.f378c.m1080n().add(C0594a.m604a("AdSession.change_orientation", new C06412(this), true));
        this.f378c.m1082o().add("AdSession.finish_fullscreen_ad");
        this.f378c.m1082o().add("AdSession.change_orientation");
        m544a(this.f379d);
        if (this.f378c.m1087t()) {
            m543a();
            return;
        }
        JSONObject a2 = C0802y.m1453a();
        C0802y.m1462a(a2, "id", this.f378c.m1053b());
        C0802y.m1472b(a2, "screen_width", this.f378c.m1084q());
        C0802y.m1472b(a2, "screen_height", this.f378c.m1083p());
        new C0595a().m622a("AdSession.on_fullscreen_ad_started").m624a(aa.f478b);
        new af("AdSession.on_fullscreen_ad_started", this.f378c.m1057c(), a2).m695b();
        this.f378c.m1059c(true);
    }

    public void onPause() {
        super.onPause();
        m546a(this.f383h);
        this.f383h = false;
    }

    void m546a(boolean z) {
        this.f388m = (C0691f) C0594a.m605a().m1283m().m1160g().get(this.f380e);
        Iterator it = this.f378c.m1063e().entrySet().iterator();
        while (it.hasNext() && !isFinishing()) {
            ba baVar = (ba) ((Entry) it.next()).getValue();
            if (!baVar.m965j() && baVar.m964i().isPlaying()) {
                baVar.m961f();
            }
        }
        if (this.f388m != null) {
            this.f388m.m1161a();
        }
        AdColonyInterstitial u = C0594a.m605a().m1291u();
        if (u != null && u.m577g() && u.m578h().m1187e() != null && z && this.f386k) {
            u.m578h().m1184b("pause");
        }
    }

    public void onResume() {
        super.onResume();
        m543a();
        m547b(this.f383h);
        this.f383h = true;
        this.f387l = true;
    }

    void m547b(boolean z) {
        for (Entry value : this.f378c.m1063e().entrySet()) {
            ba baVar = (ba) value.getValue();
            if (!(baVar.m965j() || baVar.m964i().isPlaying() || C0594a.m605a().m1288r().m1344c())) {
                baVar.m960e();
            }
        }
        if (this.f388m != null) {
            this.f388m.m1163b();
        }
        AdColonyInterstitial u = C0594a.m605a().m1291u();
        if (u != null && u.m577g() && u.m578h().m1187e() != null) {
            if ((!z || (z && !this.f386k)) && this.f387l) {
                u.m578h().m1184b("resume");
            }
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus && this.f383h) {
            C0594a.m605a().m1282l().m762c(true);
            m547b(this.f383h);
            this.f386k = true;
        } else if (!hasFocus && this.f383h) {
            new C0595a().m622a("Activity is active but window does not have focus, pausing.").m624a(aa.f480d);
            C0594a.m605a().m1282l().m760b(true);
            m546a(this.f383h);
            this.f386k = false;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (C0594a.m612b() && this.f378c != null && !this.f382g) {
            if ((VERSION.SDK_INT < 24 || !az.m900g()) && !this.f378c.m1086s()) {
                JSONObject a = C0802y.m1453a();
                C0802y.m1462a(a, "id", this.f378c.m1053b());
                new af("AdSession.on_error", this.f378c.m1057c(), a).m695b();
                this.f384i = true;
            }
        }
    }

    public void onConfigurationChanged(Configuration new_config) {
        super.onConfigurationChanged(new_config);
        m543a();
    }

    public void onBackPressed() {
        JSONObject a = C0802y.m1453a();
        C0802y.m1462a(a, "id", this.f378c.m1053b());
        new af("AdSession.on_back_button", this.f378c.m1057c(), a).m695b();
    }

    void m543a() {
        C0740l a = C0594a.m605a();
        if (this.f378c == null) {
            this.f378c = a.m1289s();
        }
        if (this.f378c != null) {
            this.f378c.m1056b(false);
            if (az.m900g()) {
                this.f378c.m1056b(true);
            }
            int q = a.f1295c.m1326q();
            int r = this.f385j ? a.f1295c.m1327r() - az.m886b(C0594a.m613c()) : a.f1295c.m1327r();
            if (q > 0 && r > 0) {
                JSONObject a2 = C0802y.m1453a();
                C0802y.m1472b(a2, "screen_width", q);
                C0802y.m1472b(a2, "screen_height", r);
                C0802y.m1462a(a2, "ad_session_id", this.f378c.m1053b());
                C0802y.m1472b(a2, "id", this.f378c.m1060d());
                this.f378c.setLayoutParams(new LayoutParams(q, r));
                this.f378c.m1054b(q);
                this.f378c.m1047a(r);
                new af("AdContainer.on_orientation_change", this.f378c.m1057c(), a2).m695b();
            }
        }
    }

    void mo1845a(af afVar) {
        int c = C0802y.m1473c(afVar.m698c(), "status");
        if ((c == 5 || c == 0 || c == 6 || c == 1) && !this.f382g) {
            C0740l a = C0594a.m605a();
            C0753o r = a.m1288r();
            a.m1265b(afVar);
            if (r.m1343b() != null) {
                r.m1343b().dismiss();
                r.m1341a(null);
            }
            if (!this.f384i) {
                finish();
            }
            this.f382g = true;
            ((ViewGroup) getWindow().getDecorView()).removeAllViews();
            a.m1270c(false);
            JSONObject a2 = C0802y.m1453a();
            C0802y.m1462a(a2, "id", this.f378c.m1053b());
            new af("AdSession.on_close", this.f378c.m1057c(), a2).m695b();
            a.m1256a(null);
            a.m1253a(null);
            a.m1255a(null);
            C0594a.m605a().m1283m().m1155c().remove(this.f378c.m1053b());
        }
    }

    void m544a(int i) {
        switch (i) {
            case 0:
                setRequestedOrientation(7);
                break;
            case 1:
                setRequestedOrientation(6);
                break;
            default:
                setRequestedOrientation(4);
                break;
        }
        this.f379d = i;
    }
}
