package com.inmobi.signals;

import android.content.Context;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3280p.C3278a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CarbWorker */
public class C3266g {
    private static final String f8213a = C3266g.class.getSimpleName();
    private C3278a f8214b;
    private boolean f8215c = false;
    private C3248a f8216d = new C3248a();
    private C3263e f8217e = new C3263e();

    /* compiled from: CarbWorker */
    class C32651 implements Runnable {
        final /* synthetic */ C3266g f8212a;

        C32651(C3266g c3266g) {
            this.f8212a = c3266g;
        }

        public void run() {
            C3261c a = this.f8212a.m10939c();
            if (!a.m10917a()) {
                this.f8212a.f8216d.m10860a(System.currentTimeMillis());
                if (!a.m10921e()) {
                    this.f8212a.m10932a(a, this.f8212a.m10931a(a.m10918b()));
                }
            }
            this.f8212a.f8215c = false;
        }
    }

    public synchronized void m10940a(C3278a c3278a) {
        this.f8214b = c3278a;
        if (this.f8215c || !m10934a()) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8213a, "Carb worker did not admit Carb start request.");
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f8213a, "Starting Carb worker");
            this.f8215c = true;
            m10938b();
        }
    }

    private boolean m10934a() {
        long b = this.f8216d.m10861b();
        if (b != 0 && System.currentTimeMillis() - b < ((long) (this.f8214b.m11018d() * 1000))) {
            return false;
        }
        return true;
    }

    private void m10938b() {
        new Thread(new C32651(this)).start();
    }

    private C3261c m10939c() {
        C3260b c3260b = new C3260b(this.f8214b.m11016b(), this.f8214b.m11019e(), this.f8214b.m11020f(), C3277o.m10989a().m10993d());
        c3260b.m9750a(this.f8214b.m11022h());
        c3260b.m9753b(this.f8214b.m11021g() * 1000);
        c3260b.m9755c(this.f8214b.m11021g() * 1000);
        return this.f8217e.m10924a(c3260b);
    }

    private List<C3262d> m10931a(List<C3262d> list) {
        List<C3262d> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!m10936a(((C3262d) list.get(i)).m10922a())) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    private boolean m10936a(String str) {
        Context b = C3105a.m10078b();
        if (b == null) {
            return false;
        }
        try {
            b.getPackageManager().getPackageInfo(str, 256);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void m10932a(C3261c c3261c, List<C3262d> list) {
        C3264f c3264f = new C3264f(this.f8214b.m11017c(), this.f8214b.m11019e(), this.f8214b.m11020f(), C3277o.m10989a().m10993d(), list, c3261c);
        c3264f.m9753b(this.f8214b.m11021g() * 1000);
        c3264f.m9755c(this.f8214b.m11021g() * 1000);
        this.f8217e.m10925a(c3264f);
    }
}
