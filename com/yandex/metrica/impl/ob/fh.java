package com.yandex.metrica.impl.ob;

import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.coremedia.iso.boxes.UserBox;
import com.yandex.metrica.impl.ob.fu.C4471b;
import com.yandex.metrica.impl.ob.fu.C4473a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class fh {
    private static final String f12391a = fh.class.getSimpleName();
    private fb f12392b;
    private fb f12393c;
    private fs f12394d;
    private Map<String, String> f12395e = new HashMap();
    private fi f12396f;
    private String f12397g;
    private fj f12398h;
    private long f12399i;
    private final ReentrantLock f12400j = new ReentrantLock();

    class C44721 implements C4471b<JSONObject> {
        final /* synthetic */ fh f12389a;

        C44721(fh fhVar) {
            this.f12389a = fhVar;
        }

        public void m16011a(JSONObject jSONObject) {
            this.f12389a.m16019a(jSONObject);
            this.f12389a.f12396f = null;
        }
    }

    class C44742 implements C4473a {
        final /* synthetic */ fh f12390a;

        C44742(fh fhVar) {
            this.f12390a = fhVar;
        }

        public void mo7102a(fr frVar) {
            Log.i(fh.f12391a, "can't update pins on schedule: " + frVar.getMessage());
            this.f12390a.m16022h();
            this.f12390a.f12396f = null;
        }
    }

    fh(fe feVar, ey eyVar, fs fsVar, fd fdVar) {
        this.f12392b = eyVar.mo7093c();
        this.f12393c = eyVar.mo7091a();
        this.f12394d = fsVar;
        this.f12397g = fdVar.m15993b();
        this.f12395e.put("app_id", feVar.m16001c());
        this.f12395e.put("app_platform", "android_" + VERSION.RELEASE);
        this.f12395e.put("manufacturer", Build.MANUFACTURER);
        this.f12395e.put("model", Build.MODEL);
        this.f12395e.put("app_version", feVar.m15999a());
        this.f12399i = fdVar.m15991a();
    }

    ReentrantLock m16025a() {
        return this.f12400j;
    }

    synchronized boolean m16027b() {
        boolean a;
        if (m16024j()) {
            Log.i(f12391a, "starting pins update on error");
            JSONObject g = m16021g();
            if (g != null) {
                a = m16019a(g);
            } else {
                m16022h();
            }
        }
        a = false;
        return a;
    }

    synchronized void m16028c() {
        if (m16029d() && m16024j()) {
            Log.i(f12391a, "starting pins update on schedule");
            this.f12396f = m16023i();
            this.f12394d.m16073a(this.f12396f, new C44721(this), new C44742(this));
        }
    }

    synchronized void m16026a(fj fjVar) {
        this.f12398h = fjVar;
    }

    boolean m16029d() {
        return !m16030e() && (m16017a(this.f12392b, this.f12399i) || m16017a(this.f12393c, this.f12399i));
    }

    boolean m16030e() {
        return this.f12396f != null;
    }

    private JSONObject m16021g() {
        Exception e;
        try {
            fv a = fv.m16074a();
            this.f12394d.m16073a(m16023i(), a, a);
            return (JSONObject) a.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        } catch (TimeoutException e4) {
            e = e4;
        }
        Log.i(f12391a, "can't update pins on error: " + e.getMessage());
        return null;
    }

    private boolean m16019a(JSONObject jSONObject) {
        try {
            m16016a(jSONObject.getJSONArray("pins-sha256"), this.f12392b);
            m16016a(jSONObject.getJSONArray("blacklist"), this.f12393c);
            Log.i(f12391a, "pins have been updated");
            return true;
        } catch (JSONException e) {
            Log.i(f12391a, "can't update pins: " + e.getMessage());
            return false;
        }
    }

    private static void m16016a(JSONArray jSONArray, fb fbVar) throws JSONException {
        fbVar.m15984a();
        for (int i = 0; i < jSONArray.length(); i++) {
            fbVar.m15985a(jSONArray.getString(i));
        }
    }

    private void m16022h() {
        this.f12392b.m15988d();
        this.f12393c.m15988d();
    }

    private static boolean m16017a(fb fbVar, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - fbVar.m15987c() >= j || currentTimeMillis < fbVar.m15987c();
    }

    private fi m16023i() {
        CharSequence a = this.f12398h.mo7067a();
        if (TextUtils.isEmpty(a)) {
            this.f12395e.remove(UserBox.TYPE);
        } else {
            this.f12395e.put(UserBox.TYPE, a);
        }
        return new fi(this.f12397g, this.f12395e);
    }

    private boolean m16024j() {
        return this.f12394d != null;
    }
}
